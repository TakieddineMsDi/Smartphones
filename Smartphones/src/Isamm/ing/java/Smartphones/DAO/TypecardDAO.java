//Final
package Isamm.ing.java.Smartphones.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Models.Typecard;

import com.mysql.jdbc.Statement;

public class TypecardDAO {
	public static int noOfRecords = 0;

	public static int add(String Param, Object Param1) {
		if (Param.equals("this")) {
			Typecard typecard = (Typecard) Param1;
			if (typecard == null) {
				return DBAccessControl.Error;
			}
			if (typecard.getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
			return DBAccessControl.executeUpdate(
					"INSERT INTO "+Typecard.getTableName()+"("+Typecard.getFields()+") VALUES ("
							+ typecard.getID() + ",'" + typecard.getLibelle()
							+ "','" + typecard.getDescription() + "','"
							+ typecard.getStatut() + "')",
					"TypeCardDAO.addTypeCard(" + Param + "," + Param1 + ")");
		} else if (Param.equals("list")) {
			List<Typecard> list = (List<Typecard>) Param1;
			if (list == null) {
				return DBAccessControl.Error;
			}
			if (list.get(0).getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
			for (Typecard actualite : list) {
				if (add("this", actualite) == DBAccessControl.Error) {
					return DBAccessControl.Error;
				}
			}
		} else {
			return DBAccessControl.WrongChoise;
		}
		return DBAccessControl.Cool;
	}

	public static int delete(List<Typecard> typecards) {
		if (typecards.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (typecards == null) {
			return DBAccessControl.Error;
		}
		for (Typecard typecard : typecards) {
			if (delete("ID", "=", "" + typecard.getID(), "", "", "", "") == -1) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int delete(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateQuery(
				Typecard.getTableName(), "delete", Fields, Operators, Values,
				Links, SortBy, Order, Limt), "TypeCardDAO.delete");
	}

	public static int delete(Typecard typecard) {
		if (typecard.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (typecard == null) {
			return DBAccessControl.Error;
		}
		return delete("ID", "=", "" + typecard.getID(), "", "", "", "");
	}

	public static Object get(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt, String size) {
		ResultSet rs = null;
		Statement smt = null;
		if (DBAccessControl.connection == null)
			DBAccessControl.connect();
		List<Typecard> list = new ArrayList<Typecard>();
		Typecard typecard = null;
		try {
			smt = (Statement) DBAccessControl.connection.createStatement();
			rs = smt.executeQuery(DBAccessControl.generateQuery(
					Typecard.getTableName(), "select", Fields, Operators,
					Values, Links, SortBy, Order, Limt));
			if (size.equals("Unique")) {
				while (rs.next()) {
					typecard = new Typecard(rs.getInt(1), rs.getString(2),
							rs.getString(3), rs.getInt(4));
					return typecard;
				}
				typecard = new Typecard();
				typecard.setID(DBAccessControl.Empty);
				return typecard;
			} else if (size.equals("List")) {
				while (rs.next()) {
					typecard = (Typecard) get("ID", "=", "" + rs.getInt(1),
							Links, SortBy, Order, Limt, "Unique");
					list.add(typecard);
				}
				if (list.size() != 0) {
					return list;
				} else {
					typecard = (Typecard) get("ID", "=", "0", Links, SortBy,
							Order, Limt, "Unique");
					list.add(typecard);
					return list;
				}
			} else {
				System.out.println("Wrong parameters");
				return null;
			}
		} catch (Exception E) {
			System.out.println("Exception à TypeCardDAO.get() "
					+ E.getMessage());
			return null;
		} finally {
			try {
				if (smt != null)
					smt.close();
				if (rs != null)
					rs.close();
			} catch (Exception E) {
				System.out.println("Exception à TypeCardDAO.get() finally "
						+ E.getMessage());
			}
		}
	}

	public static int save(List<Typecard> New) {
		if (New.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (New == null) {
			return DBAccessControl.Error;
		}
		for (Typecard typecard : New) {
			if (typecard.save() == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int save(Typecard New) {
		Typecard old = (Typecard) get("ID", "=", "" + New.getID(), "", "", "",
				"", "Unique");
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
			if (!old.getDescription().equals(New.getDescription())) {
				res += update(old, "Description", New.getDescription());
			}
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

	public static int update(List<Typecard> typecards, String Fields2,
			String Values2) {
		if (typecards.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (typecards == null) {
			return DBAccessControl.Error;
		}
		for (Typecard typecard : typecards) {
			if (update(typecard, Fields2, Values2) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int update(String Fields, String Operators, String Values,
			String Fields2, String Values2, String Links, String SortBy,
			String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateUpdate(
				Typecard.getTableName(), Fields, Operators, Values, Fields2,
				Values2, Links, SortBy, Order, Limt), "TypecardDAO.update");
	}

	public static int update(Typecard typecard, String Fields2, String Values2) {
		if (typecard.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (typecard == null) {
			return DBAccessControl.Error;
		}
		if (update("ID", "=", "" + typecard.getID(), Fields2, Values2, "", "",
				"", "") == DBAccessControl.Error) {
			return DBAccessControl.Error;
		}
		return DBAccessControl.Cool;
	}

	public TypecardDAO() {

	}
}
