package Isamm.ing.java.Smartphones.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Models.Countries;
import Isamm.ing.java.Smartphones.Models.Ville;

import com.mysql.jdbc.Statement;

public class VilleDAO {
public VilleDAO()
{
	
}
public static int add(String Param, Object Param1) {
	if (Param.equals("this")) {
		Ville ville = (Ville) Param1;
		if (ville == null) {
			return DBAccessControl.Error;
		}
		if (ville.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		return DBAccessControl.executeUpdate(
				"INSERT INTO " + Ville.getTableName() + "("
						+ ville.getFields() + ") VALUES ("
						+ ville.getID() + ",'" + ville.getIDCountry()
						+ "','"+ville.getLibelle()+"','" + ville.getStatut() + "')",
				"VilleDAO.add()");
	} else if (Param.equals("list")) {
		List<Ville> list = (List<Ville>) Param1;
		if (list == null) {
			return DBAccessControl.Error;
		}
		if (list.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		for (Ville ville : list) {
			if (add("this", ville) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
	} else {
		return DBAccessControl.WrongChoise;
	}
	return DBAccessControl.Cool;
}

public static int delete(Ville country) {
	if (country.getID() == DBAccessControl.Empty) {
		return DBAccessControl.Empty;
	}
	if (country == null) {
		return DBAccessControl.Error;
	}
	return delete("ID", "=", "" + country.getID(), "", "", "", "");
}

public static int delete(List<Ville> countries) {
	if (countries.get(0).getID() == DBAccessControl.Empty) {
		return DBAccessControl.Empty;
	}
	if (countries == null) {
		return DBAccessControl.Error;
	}
	for (Ville country : countries) {
		if (delete(country) == -1) {
			return DBAccessControl.Error;
		}
	}
	return DBAccessControl.Cool;
}

public static int delete(String Fields, String Operators, String Values,
		String Links, String SortBy, String Order, String Limt) {
	return DBAccessControl.executeUpdate(DBAccessControl.generateQuery(
			Ville.getTableName(), "delete", Fields, Operators, Values,
			Links, SortBy, Order, Limt), "VilleDAO.delete");
}

public static Object get(String Fields, String Operators, String Values,
		String Links, String SortBy, String Order, String Limt, String size) {
	ResultSet rs = null;
	Statement smt = null;
	if (DBAccessControl.connection == null)
		DBAccessControl.connect();
	List<Ville> list = new ArrayList<Ville>();
	Ville country = null;
	try {
		smt = (Statement) DBAccessControl.connection.createStatement();
		rs = smt.executeQuery(DBAccessControl.generateQuery(
				Ville.getTableName(), "select", Fields, Operators,
				Values, Links, SortBy, Order, Limt));
		if (size.equals("Unique")) {
			while (rs.next()) {
				country = new Ville(rs.getInt(1),rs.getInt(2), rs.getString(3),
						rs.getInt(4));
				return country;
			}
			country = new Ville();
			country.setID(DBAccessControl.Empty);
			return country;
		} else if (size.equals("List")) {
			while (rs.next()) {
				country = (Ville) get("ID", "=", "" + rs.getInt(1),
						Links, SortBy, Order, Limt, "Unique");
				list.add(country);
			}
			if (list.size() != 0) {
				return list;
			} else {
				country = (Ville) get("ID", "=", "0", Links, SortBy,
						Order, Limt, "Unique");
				list.add(country);
				return list;
			}
		} else {
			System.out.println("Wrong parameters");
			return null;
		}
	} catch (Exception E) {
		System.out.println("Exception à VilleDAO.get() "
				+ E.getMessage());
		return null;
	} finally {
		try {
			if (smt != null)
				smt.close();
			if (rs != null)
				rs.close();
		} catch (Exception E) {
			System.out.println("Exception à VilleDAO.get() finally "
					+ E.getMessage());
		}
	}
}

public static int save(Ville New) {
	Ville old = (Ville) get("ID", "=", "" + New.getID(), "", "",
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
		if (old.getIDCountry() != New.getIDCountry()) {
			res += update(old, "IDCountry", ""+New.getIDCountry());
		}
		if (old.getLibelle().equals(New.getLibelle())) {
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

public static int save(List<Ville> New) {
	if (New.get(0).getID() == DBAccessControl.Empty) {
		return DBAccessControl.Empty;
	}
	if (New == null) {
		return DBAccessControl.Error;
	}
	for (Ville count : New) {
		if (count.save() == DBAccessControl.Error) {
			return DBAccessControl.Error;
		}
	}
	return DBAccessControl.Cool;
}

public static int update(Ville Country, String Fields2,
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

public static int update(List<Ville> countries, String Fields2,
		String Values2) {
	if (countries.get(0).getID() == DBAccessControl.Empty) {
		return DBAccessControl.Empty;
	}
	if (countries == null) {
		return DBAccessControl.Error;
	}
	for (Ville country : countries) {
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
			Ville.getTableName(), Fields, Operators, Values, Fields2,
			Values2, Links, SortBy, Order, Limt), "VilleDAO.update");
}
}
