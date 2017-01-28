package Isamm.ing.java.Smartphones.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Models.Actualites;

import com.mysql.jdbc.Statement;

public class ActualitesDAO {

	@SuppressWarnings("unchecked")
	public static int add(String Param, Object Param1) {
		if (Param.equals("this")) {
			Actualites actualite = (Actualites) Param1;
			if (actualite == null) {
				return DBAccessControl.Error;
			}
			if (actualite.getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
			return DBAccessControl.executeUpdate(
					"INSERT INTO " + Actualites.getTableName() + "("
							+ Actualites.getFields() + ") VALUES ("
							+ actualite.getID() + ",'" + actualite.getTitre()
							+ "','" + actualite.getDescription() + "','"
							+ actualite.getURL() + "','" + actualite.getDate()
							+ "'," + actualite.getIDAdmin() + ","
							+ actualite.getStatut() + ")",
					"ActualitesDAO.addActualite()");
		} else if (Param.equals("list")) {
			List<Actualites> list = (List<Actualites>) Param1;
			if (list == null) {
				return DBAccessControl.Error;
			}
			if (list.get(0).getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
			for (Actualites actualite : list) {
				if (add("this", actualite) == DBAccessControl.Error) {
					return DBAccessControl.Error;
				}
			}
		} else {
			return DBAccessControl.WrongChoise;
		}
		return DBAccessControl.Cool;
	}

	@SuppressWarnings("unused")
	public static int delete(Actualites actualite) {
		if (actualite.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (actualite == null) {
			return DBAccessControl.Error;
		}
		return delete("ID", "=", "" + actualite.getID(), "", "", "", "");
	}

	@SuppressWarnings("unused")
	public static int delete(List<Actualites> actualites) {
		if (actualites.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (actualites == null) {
			return DBAccessControl.Error;
		}
		for (Actualites actualite : actualites) {
			if (delete(actualite) == -1) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int delete(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateQuery(
				Actualites.getTableName(), "delete", Fields, Operators, Values,
				Links, SortBy, Order, Limt), "ActualitesDAO.delete");
	}

	public static Object get(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt, String size) {
		ResultSet rs = null;
		Statement smt = null;
		if (DBAccessControl.connection == null)
			DBAccessControl.connect();
		List<Actualites> list = new ArrayList<Actualites>();
		Actualites actualite = null;
		try {
			smt = (Statement) DBAccessControl.connection.createStatement();
			rs = smt.executeQuery(DBAccessControl.generateQuery(
					Actualites.getTableName(), "select", Fields, Operators,
					Values, Links, SortBy, Order, Limt));
			if (size.equals("Unique")) {
				while (rs.next()) {
					actualite = new Actualites(rs.getInt(1), rs.getString(2),
							rs.getString(3), rs.getString(4), rs.getDate(5),
							rs.getInt(6), rs.getInt(7));
					return actualite;
				}
				actualite = new Actualites();
				actualite.setID(DBAccessControl.Empty);
				return actualite;
			} else if (size.equals("List")) {
				while (rs.next()) {
					actualite = (Actualites) get("ID", "=", "" + rs.getInt(1),
							Links, SortBy, Order, Limt, "Unique");
					list.add(actualite);
				}
				if (list.size() != 0) {
					return list;
				} else {
					actualite = (Actualites) get("ID", "=", "0", Links, SortBy,
							Order, Limt, "Unique");
					list.add(actualite);
					return list;
				}
			} else {
				System.out.println("Wrong parameters");
				return null;
			}
		} catch (Exception E) {
			System.out.println("Exception à ActualitesDAO.get() "
					+ E.getMessage());
			return null;
		} finally {
			try {
				if (smt != null)
					smt.close();
				if (rs != null)
					rs.close();
			} catch (Exception E) {
				System.out.println("Exception à ActualitesDAO.get() finally "
						+ E.getMessage());
			}
		}
	}

	public static int save(Actualites New) {
		Actualites old = (Actualites) get("ID", "=", "" + New.getID(), "", "",
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
			if (!old.getDescription().equals(New.getDescription())) {
				res += update(old, "Description", New.getDescription());
			}
			if (!old.getTitre().equals(New.getTitre())) {
				res += update(old, "Titre", New.getTitre());
			}
			if (!old.getURL().equals(New.getURL())) {
				res += update(old, "URL", New.getURL());
			}
			if (old.getStatut() != New.getStatut()) {
				res += update(old, "Statut", "" + New.getStatut());
			}
			if (!old.getDate().equals(New.getDate())) {
				res += update(old, "Date", "" + New.getDate());
			}
			if (old.getIDAdmin() != New.getIDAdmin()) {
				res += update(old, "IDAdmin", "" + New.getIDAdmin());
			}
			if (res.contains("" + DBAccessControl.Error)) {
				return DBAccessControl.Error;
			} else {
				return DBAccessControl.Cool;
			}
		}
	}

	public static int save(List<Actualites> New) {
		if (New.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (New == null) {
			return DBAccessControl.Error;
		}
		for (Actualites act : New) {
			if (act.save() == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int update(Actualites actualite, String Fields2,
			String Values2) {
		if (actualite.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (actualite == null) {
			return DBAccessControl.Error;
		}
		if (update("ID", "=", "" + actualite.getID(), Fields2, Values2, "", "",
				"", "") == DBAccessControl.Error) {
			return DBAccessControl.Error;
		}
		return DBAccessControl.Cool;
	}

	public static int update(List<Actualites> actualites, String Fields2,
			String Values2) {
		if (actualites.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (actualites == null) {
			return DBAccessControl.Error;
		}
		for (Actualites actualite : actualites) {
			if (update(actualite, Fields2, Values2) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int update(String Fields, String Operators, String Values,
			String Fields2, String Values2, String Links, String SortBy,
			String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateUpdate(
				Actualites.getTableName(), Fields, Operators, Values, Fields2,
				Values2, Links, SortBy, Order, Limt), "ActualitesDAO.update");
	}

	public ActualitesDAO() {
	}
}