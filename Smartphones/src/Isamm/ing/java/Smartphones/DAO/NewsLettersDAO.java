package Isamm.ing.java.Smartphones.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Classes.Encription;
import Isamm.ing.java.Smartphones.Models.Administrateurs;
import Isamm.ing.java.Smartphones.Models.Clients;
import Isamm.ing.java.Smartphones.Models.Marques;
import Isamm.ing.java.Smartphones.Models.NewsLetters;

import com.mysql.jdbc.Statement;

public class NewsLettersDAO {

	// addNewsLettre("this",NewsLetters)
	// addNewsLettre("list",List<NewsLetters>)
	public static int add(String Param, Object Param1) {
		if (Param.equals("this")) {
			NewsLetters newsletters = (NewsLetters) Param1;
			if (newsletters == null) {
				return DBAccessControl.Error;
			}
			if (newsletters.getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
				return DBAccessControl.executeUpdate(
						"INSERT INTO "+NewsLetters.getTableName()+"("+NewsLetters.getFields()+") VALUES ("
								+ newsletters.getID() + ",'"
								+ newsletters.getIDClient() + "','"
								+ newsletters.getIDMarque() + "')",
						"NewsLettersDAO.addNewsLettre(" + Param + "," + Param1
								+ ")");
		} else if (Param.equals("list")) {
			List<NewsLetters> list = (List<NewsLetters>) Param1;
			if (list == null) {
				return DBAccessControl.Error;
			}
			if (list.get(0).getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
				for (NewsLetters newsletters : list) {
					if (add("this", newsletters) == DBAccessControl.Error) {
						return DBAccessControl.Error;
					}
				}
		}
		return DBAccessControl.Cool;
	}
	
	public static int delete(NewsLetters newsletter) {
		if (newsletter.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (newsletter == null) {
			return DBAccessControl.Error;
		}
		return delete("ID", "=", "" + newsletter.getID(), "", "", "", "");
	}

	public static int delete(List<NewsLetters> newsletters) {
		if (newsletters.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (newsletters == null) {
			return DBAccessControl.Error;
		}
		for (NewsLetters newsletter : newsletters) {
			if (delete(newsletter) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int delete(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateQuery(
				NewsLetters.getTableName(), "delete", Fields, Operators,
				Values, Links, SortBy, Order, Limt),
				"NewsLettersDAO.delete");
	}
	
	public static Object get(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt, String size) {
		ResultSet rs = null;
		Statement smt = null;
		if (DBAccessControl.connection == null)
			DBAccessControl.connect();
		List<NewsLetters> list = new ArrayList<NewsLetters>();
		NewsLetters newsletter = null;
		try {
			smt = (Statement) DBAccessControl.connection.createStatement();
			rs = smt.executeQuery(DBAccessControl.generateQuery(
					NewsLetters.getTableName(), "select", Fields,
					Operators, Values, Links, SortBy, Order, Limt));
			if (size.equals("Unique")) {
				while (rs.next()) {
					newsletter = new NewsLetters(rs.getInt(1), rs.getInt(2),
							rs.getInt(3));
					return newsletter;
				}
				newsletter = new NewsLetters();
				newsletter.setID(DBAccessControl.Empty);
				return newsletter;
			} else if (size.equals("List")) {
				while (rs.next()) {
					newsletter = (NewsLetters) get("ID", "=",
							"" + rs.getInt(1), Links, SortBy, Order, Limt,
							"Unique");
					list.add(newsletter);
				}
				if (list.size() != 0) {
					return list;
				} else {
					newsletter = (NewsLetters) get("ID", "=", "0",
							Links, SortBy, Order, Limt, "Unique");
					list.add(newsletter);
					return list;
				}
			} else {
				System.out.println("Wrong parameters");
				return null;
			}
		} catch (Exception E) {
			System.out.println("Exception à NewsLettersDAO.get() "
					+ E.getMessage());
			return null;
		} finally {
			try {
				if (smt != null)
					smt.close();
				if (rs != null)
					rs.close();
			} catch (Exception E) {
				System.out
						.println("Exception à NewsLettersDAO.get() finally "
								+ E.getMessage());
			}
		}
	}
	
	public static int update(NewsLetters newsletter, String Fields2,
			String Values2) {
		if (newsletter.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (newsletter == null) {
			return DBAccessControl.Error;
		}
		if (update("ID", "=", "" + newsletter.getID(), Fields2, Values2,
				"", "", "", "") == DBAccessControl.Error) {
			return DBAccessControl.Error;
		}
		return DBAccessControl.Cool;
	}

	public static int update(List<NewsLetters> newsletters,
			String Fields2, String Values2) {
		if (newsletters.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (newsletters == null) {
			return DBAccessControl.Error;
		}
		for (NewsLetters newsletter : newsletters) {
			if (update(newsletter, Fields2, Values2) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int update(String Fields, String Operators, String Values,
			String Fields2, String Values2, String Links, String SortBy,
			String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateUpdate(
				NewsLetters.getTableName(), Fields, Operators, Values,
				Fields2, Values2, Links, SortBy, Order, Limt),
				"NewsLettersDAO.update");
	}

	public static boolean same(Clients client) {
		if(client == null)
			return false;
		if(client.getNewNewsLetters() == null)
			return false;

		List<Marques> News = toMarques(client.getNewNewsLetters());
		List<Marques> Olds = toMarques(client.getOldNewsLetters());
		if (News == null || Olds == null) {
			return false;
		}
		if(News.size()!=Olds.size())
		{
			return false;
		}
		for (Marques m : Olds) {
			if (subscribed(m.getID(), News) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean subscribed(int ID, List<Marques> list) {
		if (list == null) {
			return false;
		}
		if(list.get(0).getID() == DBAccessControl.Empty){
			return false;
		}
		for (Marques m : list) {
			if (m.getID() == ID) {
				return true;
			}
		}
		return false;
	}

	public static List<Marques> toMarques(List<NewsLetters> newsletters) {
		if (newsletters == null) {
			return null;
		}
		List<Marques> list = new ArrayList<Marques>();
			for (NewsLetters news : newsletters) {
				list.add((Marques) MarquesDAO.get("ID", "=", ""+news.getIDMarque(), "", "", "", "", "Unique"));
			}
			return list;
	}
}
