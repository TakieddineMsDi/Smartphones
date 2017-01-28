package Isamm.ing.java.Smartphones.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Classes.Encription;
import Isamm.ing.java.Smartphones.Models.Administrateurs;
import Isamm.ing.java.Smartphones.Models.Clients;

import com.mysql.jdbc.Statement;

public class AdministrateursDAO {

	public static int add(String Param, Object Param1) {
		if (Param.equals("this")) {
			String res = "";
			Administrateurs administrateur = (Administrateurs) Param1;
			if (Param1 == null) {
				return DBAccessControl.Error;
			}
			if (administrateur.getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
			int StatutMail = checkEmail(administrateur.getEmail());
			int StatutUsername = checkUsername(administrateur.getUsername());
			res += StatutMail + "" + StatutUsername;
			if (StatutMail == DBAccessControl.Cool
					&& StatutUsername == DBAccessControl.Cool) {
				return DBAccessControl.executeUpdate(
						"INSERT INTO "
								+ Administrateurs.getTableName()
								+ "("
								+ Administrateurs.getFields()
								+ ") "
								+ "VALUES ("
								+ administrateur.getID()
								+ ",'"
								+ administrateur.getUsername()
								+ "','"
								+ Encription.encrypt(
										administrateur.getPassword(),
										Encription.getKey("password")) + "','"
								+ administrateur.getEmail() + "','"
								+ administrateur.getNom() + "','"
								+ administrateur.getPrenom() + "','"
								+ administrateur.getAdresse() + "','"
								+ administrateur.getDateInscription() + "',"
								+ administrateur.getLevel() + ")",
						"AdministrateursDAO.addAdminnistrateur()");
			}
			if (res.contains("" + DBAccessControl.Error)) {
				return DBAccessControl.Error;
			} else if (res.contains("" + DBAccessControl.EmailUsed)
					|| res.contains("" + DBAccessControl.UsernameUsed)) {
				if (StatutMail == DBAccessControl.EmailUsed
						&& StatutUsername == DBAccessControl.UsernameUsed) {
					return DBAccessControl.UsernameAndEmailused;
				} else {
					if (StatutUsername == DBAccessControl.UsernameUsed) {
						return DBAccessControl.UsernameUsed;
					}
					if (StatutMail == DBAccessControl.EmailUsed) {
						return DBAccessControl.EmailUsed;
					}
				}
			}
		} else if (Param.equals("list")) {
			List<Administrateurs> list = (List<Administrateurs>) Param1;
			if (list == null) {
				return DBAccessControl.Error;
			}
			if (list.get(0).getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
			for (Administrateurs administrateur : list) {
				int res = add("this", administrateur);
				if (res != DBAccessControl.Cool) {
					return res;
				}
			}
		}
		return DBAccessControl.Cool;
	}

	public static int checkEmail(String Email) {
		Administrateurs admin = ((Administrateurs) get("Email", "=", Email, "",
				"", "", "", "Unique"));
		if (admin == null) {
			return DBAccessControl.Error;
		} else if (admin.getID() != DBAccessControl.Empty) {
			return DBAccessControl.EmailUsed;
		} else {
			return DBAccessControl.Cool;
		}
	}

	public static int checkEmail(String Email, int ID) {
		Administrateurs admin = ((Administrateurs) get("Email&ID", "=&<>",
				Email + "&" + ID, "AND", "", "", "", "Unique"));
		if (admin == null) {
			return DBAccessControl.Error;
		} else if (admin.getID() != DBAccessControl.Empty) {
			return DBAccessControl.EmailUsed;
		} else {
			return DBAccessControl.Cool;
		}
	}

	public static int checkUsername(String Param) {
		Administrateurs admin = ((Administrateurs) get("Username", "=", Param,
				"", "", "", "", "Unique"));
		if (admin == null) {
			return DBAccessControl.Error;
		} else if (admin.getID() != DBAccessControl.Empty) {
			return DBAccessControl.UsernameUsed;
		} else {
			return DBAccessControl.Cool;
		}
	}

	public static int checkUsername(String Username, int ID) {
		Administrateurs admin = ((Administrateurs) get("Username&ID", "=&<>",
				Username + "&" + ID, "AND", "", "", "", "Unique"));
		if (admin == null) {
			return DBAccessControl.Error;
		} else if (admin.getID() != DBAccessControl.Empty) {
			return DBAccessControl.UsernameUsed;
		} else {
			return DBAccessControl.Cool;
		}
	}

	public static int delete(Administrateurs Administrateur) {
		if (Administrateur.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (Administrateur == null) {
			return DBAccessControl.Error;
		}
		return delete("ID", "=", "" + Administrateur.getID(), "", "", "", "");
	}

	public static int delete(List<Administrateurs> administrateurs) {
		if (administrateurs.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (administrateurs == null) {
			return DBAccessControl.Error;
		}
		for (Administrateurs admininistrateur : administrateurs) {
			if (delete(admininistrateur) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int delete(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateQuery(
				Administrateurs.getTableName(), "delete", Fields, Operators,
				Values, Links, SortBy, Order, Limt),
				"AdministrateursDAO.delete");
	}

	public static Object get(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt, String size) {
		ResultSet rs = null;
		Statement smt = null;
		if (DBAccessControl.connection == null)
			DBAccessControl.connect();
		List<Administrateurs> list = new ArrayList<Administrateurs>();
		Administrateurs administrateur = null;
		try {
			smt = (Statement) DBAccessControl.connection.createStatement();
			rs = smt.executeQuery(DBAccessControl.generateQuery(
					Administrateurs.getTableName(), "select", Fields,
					Operators, Values, Links, SortBy, Order, Limt));
			if (size.equals("Unique")) {
				while (rs.next()) {
					administrateur = new Administrateurs(rs.getInt(1),
							rs.getString(2), Encription.decrypt(
									rs.getString(3),
									Encription.getKey("password")),
							rs.getString(4), rs.getString(5), rs.getString(6),
							rs.getString(7), rs.getDate(8), rs.getInt(9));
					return administrateur;
				}
				administrateur = new Administrateurs();
				administrateur.setID(DBAccessControl.Empty);
				return administrateur;
			} else if (size.equals("List")) {
				while (rs.next()) {
					administrateur = (Administrateurs) get("ID", "=",
							"" + rs.getInt(1), Links, SortBy, Order, Limt,
							"Unique");
					list.add(administrateur);
				}
				if (list.size() != 0) {
					return list;
				} else {
					administrateur = (Administrateurs) get("ID", "=", "0",
							Links, SortBy, Order, Limt, "Unique");
					list.add(administrateur);
					return list;
				}
			} else {
				System.out.println("Wrong parameters");
				return null;
			}
		} catch (Exception E) {
			System.out.println("Exception à AdministrateursDAO.get() "
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
						.println("Exception à AdministrateursDAO.get() finally "
								+ E.getMessage());
			}
		}
	}

	public static Administrateurs login(String ID, String pass) {
		if(ID==null||pass==null||ID.equals("")||pass.equals(""))
		{
			return (Administrateurs) get(
					"ID",
					"=","0", "", "",
					"", "", "Unique");
		}
		Administrateurs Admin = (Administrateurs) get(
				"Username&Password",
				"=&=",
				          ID
						+ "&"
						+ Encription.encrypt(pass,
								Encription.getKey("password")), "AND", "",
				"", "", "Unique");
		return Admin;
	}

	public static int save(Administrateurs New) {
		Administrateurs old = (Administrateurs) get("ID", "=",
				"" + New.getID(), "", "", "", "", "Unique");
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
			int mail = DBAccessControl.Cool, user = DBAccessControl.Cool;
			if (!old.getUsername().equals(New.getUsername())) {
				user = checkUsername(New.getUsername(), old.getID());
				if (user == DBAccessControl.Cool) {
					res += update(old, "Username", New.getUsername());
				} else {
					res += user;
				}
			}
			if (!old.getEmail().equals(New.getEmail())) {
				mail = checkEmail(New.getEmail(), old.getID());
				if (mail == DBAccessControl.Cool) {
					res += update(old, "Email", New.getEmail());
				} else {
					res += mail;
				}
			}
			if (!old.getPassword().equals(New.getPassword())) {
				res += update(
						old,
						"Password",
						Encription.encrypt(New.getPassword(),
								Encription.getKey("password")));
			}
			if (!old.getNom().equals(New.getNom())) {
				res += update(old, "Nom", New.getNom());
			}
			if (!old.getPrenom().equals(New.getPrenom())) {
				res += update(old, "Prenom", New.getPrenom());
			}
			if (!old.getAdresse().equals(New.getAdresse())) {
				res += update(old, "Adresse", New.getAdresse());
			}
			if (!(old.getLevel() == New.getLevel())) {
				res += update(old, "Level", "" + New.getLevel());
			}
			if (!(old.getDateInscription().equals(New.getDateInscription()))) {
				res += update(old, "DateInscription",
						"" + New.getDateInscription());
			}
			if (res.contains("" + DBAccessControl.Error)) {
				return DBAccessControl.Error;
			} else if (res.contains("" + DBAccessControl.EmailUsed)
					|| res.contains("" + DBAccessControl.UsernameUsed)) {
				if (mail == DBAccessControl.EmailUsed
						&& user == DBAccessControl.UsernameUsed) {
					return DBAccessControl.UsernameAndEmailused;
				} else {
					if (user == DBAccessControl.UsernameUsed) {
						return user;
					}
					if (mail == DBAccessControl.EmailUsed) {
						return mail;
					}
				}
			}
			return DBAccessControl.Cool;

		}
	}

	public static int save(List<Administrateurs> New) {
		if (New.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (New == null) {
			return DBAccessControl.Error;
		}
		for (Administrateurs administrateur : New) {
			if (administrateur.save() == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int update(Administrateurs administrateur, String Fields2,
			String Values2) {
		if (administrateur.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (administrateur == null) {
			return DBAccessControl.Error;
		}
		if (update("ID", "=", "" + administrateur.getID(), Fields2, Values2,
				"", "", "", "") == DBAccessControl.Error) {
			return DBAccessControl.Error;
		}
		return DBAccessControl.Cool;
	}

	public static int update(List<Administrateurs> administrateurs,
			String Fields2, String Values2) {
		if (administrateurs.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (administrateurs == null) {
			return DBAccessControl.Error;
		}
		for (Administrateurs administrateur : administrateurs) {
			if (update(administrateur, Fields2, Values2) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int update(String Fields, String Operators, String Values,
			String Fields2, String Values2, String Links, String SortBy,
			String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateUpdate(
				Administrateurs.getTableName(), Fields, Operators, Values,
				Fields2, Values2, Links, SortBy, Order, Limt),
				"AdministrateursDAO.update");
	}

	public AdministrateursDAO() {
	}
}
