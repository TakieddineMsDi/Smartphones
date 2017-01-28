package Isamm.ing.java.Smartphones.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Models.Actualites;
import Isamm.ing.java.Smartphones.Models.Countries;

import com.mysql.jdbc.Statement;

public class CountriesDAO {
public CountriesDAO()
{
	
}
public static int add(String Param, Object Param1) {
	if (Param.equals("this")) {
		Countries country = (Countries) Param1;
		if (country == null) {
			return DBAccessControl.Error;
		}
		if (country.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		return DBAccessControl.executeUpdate(
				"INSERT INTO " + Countries.getTableName() + "("
						+ Countries.getFields() + ") VALUES ("
						+ country.getID() + ",'" + country.getLibelle()
						+ "','" + country.getStatut() + "')",
				"CountriesDAO.add()");
	} else if (Param.equals("list")) {
		List<Countries> list = (List<Countries>) Param1;
		if (list == null) {
			return DBAccessControl.Error;
		}
		if (list.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		for (Countries country : list) {
			if (add("this", country) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
	} else {
		return DBAccessControl.WrongChoise;
	}
	return DBAccessControl.Cool;
}

public static int delete(Countries country) {
	if (country.getID() == DBAccessControl.Empty) {
		return DBAccessControl.Empty;
	}
	if (country == null) {
		return DBAccessControl.Error;
	}
	return delete("ID", "=", "" + country.getID(), "", "", "", "");
}

public static int delete(List<Countries> countries) {
	if (countries.get(0).getID() == DBAccessControl.Empty) {
		return DBAccessControl.Empty;
	}
	if (countries == null) {
		return DBAccessControl.Error;
	}
	for (Countries country : countries) {
		if (delete(country) == -1) {
			return DBAccessControl.Error;
		}
	}
	return DBAccessControl.Cool;
}

public static int delete(String Fields, String Operators, String Values,
		String Links, String SortBy, String Order, String Limt) {
	return DBAccessControl.executeUpdate(DBAccessControl.generateQuery(
			Countries.getTableName(), "delete", Fields, Operators, Values,
			Links, SortBy, Order, Limt), "CountriesDAO.delete");
}

public static Object get(String Fields, String Operators, String Values,
		String Links, String SortBy, String Order, String Limt, String size) {
	ResultSet rs = null;
	Statement smt = null;
	if (DBAccessControl.connection == null)
		DBAccessControl.connect();
	List<Countries> list = new ArrayList<Countries>();
	Countries country = null;
	try {
		System.out.println(DBAccessControl.generateQuery(
				Countries.getTableName(), "select", Fields, Operators,
				Values, Links, SortBy, Order, Limt));
		smt = (Statement) DBAccessControl.connection.createStatement();
		rs = smt.executeQuery(DBAccessControl.generateQuery(
				Countries.getTableName(), "select", Fields, Operators,
				Values, Links, SortBy, Order, Limt));
		if (size.equals("Unique")) {
			while (rs.next()) {
				country = new Countries(rs.getInt(1), rs.getString(2),
						rs.getInt(3));
				return country;
			}
			country = new Countries();
			country.setID(DBAccessControl.Empty);
			return country;
		} else if (size.equals("List")) {
			while (rs.next()) {
				country = (Countries) get("ID", "=", "" + rs.getInt(1),
						Links, SortBy, Order, Limt, "Unique");
				list.add(country);
			}
			if (list.size() != 0) {
				return list;
			} else {
				country = (Countries) get("ID", "=", "0", Links, SortBy,
						Order, Limt, "Unique");
				list.add(country);
				return list;
			}
		} else {
			System.out.println("Wrong parameters");
			return null;
		}
	} catch (Exception E) {
		System.out.println("Exception à CountriesDAO.get() "
				+ E.getMessage()+", ");
		E.printStackTrace();
		return null;
	} finally {
		try {
			if (smt != null)
				smt.close();
			if (rs != null)
				rs.close();
		} catch (Exception E) {
			System.out.println("Exception à CountriesDAO.get() finally "
					+ E.getMessage());
		}
	}
}

public static int save(Countries New) {
	Countries old = (Countries) get("ID", "=", "" + New.getID(), "", "",
			"", "", "Unique");
	if (old == null || New == null) {
		return DBAccessControl.Error;
	}
	if (New.getID() == DBAccessControl.Empty
			|| old.getID() == DBAccessControl.Empty)
		return DBAccessControl.Empty;
	if (old.equals(New))
		return DBAccessControl.NoChange;
	else {
		String res = "";
		if (!old.getLibelle().equals(New.getLibelle())) {
			res += update(old, "Libelle", New.getLibelle());
		}
		if (old.getStatut() != New.getStatut()) {
			res += update(old, "Statut", "" + New.getStatut());
		}
		if (res.contains("" + DBAccessControl.Error)) {
			return DBAccessControl.Error;
		} else {
			return DBAccessControl.Cool;
		}
	}
}

public static int save(List<Countries> New) {
	if (New.get(0).getID() == DBAccessControl.Empty) {
		return DBAccessControl.Empty;
	}
	if (New == null) {
		return DBAccessControl.Error;
	}
	for (Countries count : New) {
		if (count.save() == DBAccessControl.Error) {
			return DBAccessControl.Error;
		}
	}
	return DBAccessControl.Cool;
}

public static int update(Countries Country, String Fields2,
		String Values2) {
	if (Country.getID() == DBAccessControl.Empty) {
		return DBAccessControl.Empty;
	}
	if (Country == null) {
		return DBAccessControl.Error;
	}
	if (update("ID", "=", "" + Country.getID(), Fields2, Values2, "", "",
			"", "") == DBAccessControl.Error) {
		return DBAccessControl.Error;
	}
	return DBAccessControl.Cool;
}

public static int update(List<Countries> countries, String Fields2,
		String Values2) {
	if (countries.get(0).getID() == DBAccessControl.Empty) {
		return DBAccessControl.Empty;
	}
	if (countries == null) {
		return DBAccessControl.Error;
	}
	for (Countries country : countries) {
		if (update(country, Fields2, Values2) == DBAccessControl.Error) {
			return DBAccessControl.Error;
		}
	}
	return DBAccessControl.Cool;
}

public static int update(String Fields, String Operators, String Values,
		String Fields2, String Values2, String Links, String SortBy,
		String Order, String Limt) {
	return DBAccessControl.executeUpdate(DBAccessControl.generateUpdate(
			Countries.getTableName(), Fields, Operators, Values, Fields2,
			Values2, Links, SortBy, Order, Limt), "CountriesDAO.update");
}
}
