package Isamm.ing.java.Smartphones.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Classes.Encription;
import Isamm.ing.java.Smartphones.Models.Clients;

import com.mysql.jdbc.Statement;

public class ClientsDAO {

	public static int add(String Param, Object Param1) {
		if (Param.equals("this")) {
			String res = "";
			Clients Client = (Clients) Param1;
			if (Param1 == null) {
				return DBAccessControl.Error;
			}
			if (Client.getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
			if (Client == null) {
				return DBAccessControl.Error;
			}
			int StatutMail = checkEmail(Client.getEmail());
			int StatutUsername = checkUsername(Client.getUsername());
			res += StatutMail + "" + StatutUsername;
			if (StatutMail == DBAccessControl.Cool
					&& StatutUsername == DBAccessControl.Cool) {
				int Result = DBAccessControl.executeUpdate(
						"INSERT INTO "
								+ Clients.getTableName()+ "("
								+ Clients.getFields()
								+ ") "+ "VALUES ("
								+ Client.getID()+ ",'"
								+ Client.getUsername()+ "','"
								+ Encription.encrypt(Client.getPassword(),Encription.getKey("password")) + "','"
								+ Client.getEmail() + "','"
								+ Client.getNom()+ "','"
								+ Client.getPrenom() + "','"
								+ Client.getVille() + "','"
								+ Client.getAdresse() + "','"
								+ Client.getTypeCard() + "','"
								+ Client.getNumeroCard() + "','"
								+ Client.getNewsLettres() + "','"
								+ Client.getDateInscription() + "','"
								+ Client.getAllowedToSubmit() + "','"
								+ Client.getActif() + "','"
								+ Client.getCountry()+"','"
								+ Client.getAge()+"','"
								+ Client.getGender()+"')",
						"ClientsDAO.AddClient()");
				res += Result;
				if (Result == DBAccessControl.Cool) {
					if(Client.getNewNewsLetters()!=null)
					{
					int NewsLettresStatut = NewsLettersDAO.add(
							"list", Client.getNewNewsLetters());
					res += NewsLettresStatut;
					}
				}
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
						return StatutUsername;
					}
					if (StatutMail == DBAccessControl.EmailUsed) {
						return StatutMail;
					}
				}
			}
			else{
				return DBAccessControl.Cool;
			}
		} else if (Param.equals("list")) {
			List<Clients> list = (List<Clients>) Param1;
			if (list == null) {
				return DBAccessControl.Error;
			}
			if (list.get(0).getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
			for (Clients client : list) {
				int res = add("this", client);
				if (res != DBAccessControl.Cool) {
					return res;
				}
			}
		}
		else{
			return DBAccessControl.WrongChoise;
		}
		return DBAccessControl.Cool;
	}

	public static int checkEmail(String Email) {
		Clients client = ((Clients) get("Email", "=", Email, "", "", "", "",
				"Unique"));
		if (client == null) {
			return DBAccessControl.Error;
		} else if (client.getID() != DBAccessControl.Empty) {
			return DBAccessControl.EmailUsed;
		} else {
			return DBAccessControl.Cool;
		}
	}

	public static int checkEmail(String Email, int ID) {
		Clients client = ((Clients) get("Email&ID", "=&<>", Email + "&" + ID,
				"AND", "", "", "", "Unique"));
		if (client == null) {
			return DBAccessControl.Error;
		} else if (client.getID() != DBAccessControl.Empty) {
			return DBAccessControl.EmailUsed;
		} else {
			return DBAccessControl.Cool;
		}
	}

	public static int checkUsername(String Param) {
		Clients client = ((Clients) get("Username", "=", Param, "", "", "", "",
				"Unique"));
		if (client == null) {
			return DBAccessControl.Error;
		} else if (client.getID() != DBAccessControl.Empty) {
			return DBAccessControl.UsernameUsed;
		} else {
			return DBAccessControl.Cool;
		}
	}

	public static int checkUsername(String Username, int ID) {
		Clients client = ((Clients) get("Username&ID", "=&<>", Username + "&"
				+ ID, "AND", "", "", "", "Unique"));
		if (client == null) {
			return DBAccessControl.Error;
		} else if (client.getID() != DBAccessControl.Empty) {
			return DBAccessControl.UsernameUsed;
		} else {
			return DBAccessControl.Cool;
		}
	}

	public static int delete(Clients client) {
		if (client.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (client == null) {
			return DBAccessControl.Error;
		}
		return delete("ID", "=", "" + client.getID(), "", "", "", "");
	}

	public static int delete(List<Clients> clients) {
		if (clients.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (clients == null) {
			return DBAccessControl.Error;
		}
		for (Clients client : clients) {
			if (delete(client) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int delete(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateQuery(
				Clients.getTableName(), "delete", Fields, Operators, Values,
				Links, SortBy, Order, Limt), "ClientsDAO.delete");
	}

	public static Object get(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt, String size) {
		ResultSet rs = null;
		Statement smt = null;
		if (DBAccessControl.connection == null)
			DBAccessControl.connect();
		List<Clients> list = new ArrayList<Clients>();
		Clients client = null;
		try {
			smt = (Statement) DBAccessControl.connection.createStatement();
			System.out.println(DBAccessControl.generateQuery(
					Clients.getTableName(), "select", Fields, Operators,
					Values, Links, SortBy, Order, Limt));
			rs = smt.executeQuery(DBAccessControl.generateQuery(
					Clients.getTableName(), "select", Fields, Operators,
					Values, Links, SortBy, Order, Limt));
			if (size.equals("Unique")) {
				while (rs.next()) {
					client = new Clients(rs.getInt(1), rs.getString(2),
							Encription.decrypt(rs.getString(3),
									Encription.getKey("password")),
							rs.getString(4), rs.getString(5), rs.getString(6),
							rs.getInt(7), rs.getString(8), rs.getInt(9),
							rs.getString(10), rs.getInt(11), rs.getDate(12),
							rs.getInt(13), rs.getInt(14),rs.getInt(15),rs.getInt(16),rs.getString(17));
					return client;
				}
				client = new Clients();
				client.setID(DBAccessControl.Empty);
				return client;
			} else if (size.equals("List")) {
				while (rs.next()) {
					client = (Clients) get("ID", "=", "" + rs.getInt(1), Links,
							SortBy, Order, Limt, "Unique");
					list.add(client);
				}
				if (list.size() != 0) {
					return list;
				} else {
					client = (Clients) get("ID", "=", "0", Links, SortBy,
							Order, Limt, "Unique");
					list.add(client);
					return list;
				}
			} else {
				System.out.println("Wrong parameters");
				return null;
			}
		} catch (Exception E) {
			System.out
					.println("Exception à ClientsDAO.get() " + E.getMessage());
			E.printStackTrace();
			return null;
		} finally {
			try {
				if (smt != null)
					smt.close();
				if (rs != null)
					rs.close();
			} catch (Exception E) {
				System.out.println("Exception à ClientsDAO.get() finally "
						+ E.getMessage());
			}
		}
	}

	public static Clients login(String ID, String pass) {
		if(ID==null||pass==null||ID.equals("")||pass.equals(""))
		{
			return (Clients) get(
					"ID",
					"=","0", "", "",
					"", "", "Unique");
		}
		Clients client = (Clients) get(
				"Username&Password",
				"=&=",
						ID
						+ "&"
						+ Encription.encrypt(pass,
								Encription.getKey("password")), "AND", "",
				"", "", "Unique");
		return client;
	}

	public static int save(Clients New) {
		Clients old = (Clients) get("ID", "=", "" + New.getID(), "", "", "",
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
			if (old.getVille() != New.getVille()) {
				res += update(old, "Ville", ""+New.getVille());
			}
			if (!old.getAdresse().equals(New.getAdresse())) {
				res += update(old, "Adresse", New.getAdresse());
			}
			if (!(old.getTypeCard() == New.getTypeCard())) {
				res += update(old, "TypeCard", "" + New.getTypeCard());
			}
			if (!(old.getNumeroCard() == New.getNumeroCard())) {
				res += update(old, "NumeroCard", "" + New.getNumeroCard());
			}
			if (!(old.getNewsLettres() == New.getNewsLettres())) {
				res += update(old, "NewsLettres", "" + New.getNewsLettres());
			}
			if (!NewsLettersDAO.same(New)) {
				if(New.getNewNewsLetters()!=null)
				{
				if (New.getNewNewsLetters().get(0).getID() == DBAccessControl.Empty)
					update(New, "NewsLettres", "0");
				if (New.getOldNewsLetters().get(0).getID() == DBAccessControl.Empty)
					update(New, "NewsLettres", "1");
				res += NewsLettersDAO.delete(New.getOldNewsLetters());
				res += NewsLettersDAO.add("list",
						New.getNewNewsLetters());
				}
			}
			if (!old.getDateInscription().equals(New.getDateInscription())) {
				res += update(old, "DateInscription",
						"" + New.getDateInscription());
			}
			if (!(old.getAllowedToSubmit() == New.getAllowedToSubmit())) {
				res += update(old, "AllowedToSubmit",
						"" + New.getAllowedToSubmit());
			}
			if (!(old.getActif() == New.getActif())) {
				res += update(old, "Actif", "" + New.getActif());
			}
			if(old.getCountry() != New.getCountry())
			{
				res += update(old, "Country", ""+New.getCountry());
			}
			if(old.getAge()!=New.getAge())
			{
				res += update(old, "Age", "" + New.getAge());
			}
			if(!(old.getGender().equals(New.getGender())))
			{
				res += update(old, "Gender", New.getGender());
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

	public static int save(List<Clients> New) {
		if (New.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (New == null) {
			return DBAccessControl.Error;
		}
		for (Clients client : New) {
			if (client.save() == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int update(Clients client, String Fields2, String Values2) {
		if (client.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (client == null) {
			return DBAccessControl.Error;
		}
		if (update("ID", "=", "" + client.getID(), Fields2, Values2, "", "",
				"", "") == DBAccessControl.Error) {
			return DBAccessControl.Error;
		}
		return DBAccessControl.Cool;
	}

	public static int update(List<Clients> clients, String Fields2,
			String Values2) {
		if (clients.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (clients == null) {
			return DBAccessControl.Error;
		}
		for (Clients client : clients) {
			if (update(client, Fields2, Values2) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int update(String Fields, String Operators, String Values,
			String Fields2, String Values2, String Links, String SortBy,
			String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateUpdate(
				Clients.getTableName(), Fields, Operators, Values, Fields2,
				Values2, Links, SortBy, Order, Limt), "ClientsDAO.update");
	}

	public ClientsDAO() {
	}
}
