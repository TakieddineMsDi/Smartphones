package Isamm.ing.java.Smartphones.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Models.Marques;

public class MarquesDAO {
	
	public static int noOfRecords = 0;
	
	public static int add(String Param, Object Param1) {
		if (Param.equals("this")) {
			Marques marque = (Marques) Param1;
			if (marque == null) {
				return DBAccessControl.Error;
			}
			if (marque.getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
			return DBAccessControl
					.executeUpdate(
							"INSERT INTO "+Marques.getTableName()+"("+Marques.getFields()+") VALUES ("
									+ marque.getID()
									+ ",'"
									+ marque.getMarque()
									+ "','"
									+ marque.getDescription()
									+ "','"
									+ marque.getLogo()
									+ "','"
									+ marque.getStatut()
									+ "')",
							"TypeCardDAO.addMarque("+Param+","+Param1+")");
		} else if (Param.equals("list")) {
			List<Marques> list = (List<Marques>) Param1;
			if (list == null) {
				return DBAccessControl.Error;
			}
			if (list.get(0).getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
				for (Marques marque : list) {
					if (add("this", marque) == DBAccessControl.Error) {
						return DBAccessControl.Error;
					}
				}
		} else {
			return DBAccessControl.WrongChoise;
		}
		return DBAccessControl.Cool;
	}

	public static int delete(Marques marque) {
		if (marque.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (marque == null) {
			return DBAccessControl.Error;
		}
		return delete("ID", "=", "" + marque.getID(), "", "", "", "");
	}

	public static int delete(List<Marques> marques) {
		if (marques.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (marques == null) {
			return DBAccessControl.Error;
		}
		for (Marques marque : marques) {
			if (delete("ID", "=", "" + marque.getID(), "", "", "", "") == -1) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int delete(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateQuery(
				Marques.getTableName(), "delete", Fields, Operators, Values, Links,
				SortBy, Order, Limt), "MarquesDAO.delete");
	}

	public static Object get(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt, String size) {
		ResultSet rs = null;
		Statement smt = null;
		if (DBAccessControl.connection == null)
			DBAccessControl.connect();
		List<Marques> list = new ArrayList<Marques>();
		Marques marque = null;
		try {
			smt = (Statement) DBAccessControl.connection.createStatement();
			rs = smt.executeQuery(DBAccessControl.generateQuery(Marques.getTableName(),
					"select", Fields, Operators, Values, Links, SortBy, Order,
					Limt));
			if (size.equals("Unique")) {
				while (rs.next()) {
					marque = new Marques(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
					return marque;
				}
				marque = new Marques();
				marque.setID(DBAccessControl.Empty);
				return marque;
			} else if (size.equals("List")) {
				while (rs.next()) {
					marque = (Marques) get("ID", "=", "" + rs.getInt(1),
							Links, SortBy, Order, Limt, "Unique");
					list.add(marque);
				}
				if (list.size() != 0) {
					return list;
				} else {
					marque = (Marques) get("ID", "=", "0", Links, SortBy,
							Order, Limt, "Unique");
					list.add(marque);
					return list;
				}
			} else {
				System.out.println("Wrong parameters");
				return null;
			}
		} catch (Exception E) {
			System.out.println("Exception à MarquesDAO.get() "
					+ E.getMessage());
			return null;
		} finally {
			try {
				if (smt != null)
					smt.close();
				if (rs != null)
					rs.close();
			} catch (Exception E) {
				System.out.println("Exception à MarquesDAO.get() finally "
						+ E.getMessage());
			}
		}
	}

	public static int save(Marques New) {
		Marques old = (Marques) get("ID", "=", "" + New.getID(), "", "",
				"", "", "Unique");
		if (old == null || New == null) {
			return DBAccessControl.Error;
		}
		if (New.getID() == DBAccessControl.Empty || old.getID() == DBAccessControl.Empty)
			return DBAccessControl.Empty;
		if (old.equals(New))
			return DBAccessControl.NoChange;
		else {
			String res = "";
			if (!old.getDescription().equals(New.getDescription())) {
				res += update(old,"Description",New.getDescription());
			}
			if (!old.getMarque().equals(New.getMarque())) {
				res += update(old,"Marque",New.getMarque());
			}
			if (!old.getLogo().equals(New.getLogo())) {
				res += update(old,"Logo",New.getLogo());
			}
			if (old.getStatut() != New.getStatut()) {
				res += update(old,"Statut",""+New.getStatut());
			}
			if (res.contains(""+DBAccessControl.Error)) {
				return DBAccessControl.Error;
			} else {
				return DBAccessControl.Cool;
			}
		}
	}

	public static int save(List<Marques> New) {
		if (New.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (New == null) {
			return DBAccessControl.Error;
		}
		for (Marques marque : New) {
			if (marque.save() == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int update(List<Marques> marques, String Fields2,
			String Values2) {
		if (marques.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (marques == null) {
			return DBAccessControl.Error;
		}
		for (Marques marque : marques) {
			if (update(marque,Fields2,Values2) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}
	
	public static int update(Marques marque, String Fields2,
			String Values2) {
		if (marque.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (marque == null) {
			return DBAccessControl.Error;
		}
			if (update("ID", "=", "" + marque.getID(), Fields2, Values2, "",
					"", "", "") == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		return DBAccessControl.Cool;
	}

	public static int update(String Fields, String Operators, String Values,
			String Fields2, String Values2, String Links, String SortBy,
			String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateUpdate(
				Marques.getTableName(), Fields, Operators, Values, Fields2, Values2,
				Links, SortBy, Order, Limt), "MarquesDAO.update");
	}
	
}
