package Isamm.ing.java.Smartphones.Classes;

import java.sql.DriverManager;
import java.sql.ResultSet;

import Isamm.ing.java.Smartphones.Models.Actualites;
import Isamm.ing.java.Smartphones.Models.Administrateurs;
import Isamm.ing.java.Smartphones.Models.Clients;
import Isamm.ing.java.Smartphones.Models.Commandes;
import Isamm.ing.java.Smartphones.Models.Countries;
import Isamm.ing.java.Smartphones.Models.Marques;
import Isamm.ing.java.Smartphones.Models.NewsLetters;
import Isamm.ing.java.Smartphones.Models.Produits;
import Isamm.ing.java.Smartphones.Models.Typecard;
import Isamm.ing.java.Smartphones.Models.Ville;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBAccessControl {
	private static String ClassForName = "com.mysql.jdbc.Driver";
	public static Connection connection = null;
	private static String connectionURL = null;
	public static int Cool = 1;
	public static int EmailUsed = 4;
	public static int Empty = -2;
	private static String Entete = "jdbc:mysql://";
	public static int Error = -1;
	public static int NoChange = 0;
	public static int UsernameAndEmailused = 2;
	public static int UsernameUsed = 3;
	public static int WrongChoise = 5;
	public static int Actif = 1;
	public static int Inactif = 0;
	public static int InactifAdmin = -6;
	public static int Archive = 2;
	public static int ArchiveAdmin = -5;
	public static int Banned = 3;
	public static int BannedAdmin = -4;
	public static int QuatiteExceeded = -9;

	public static String getTablesNames() {
		
		return "actualites,administrateurs,clients,commandes,marques,newsletters,produits,typecard,ville,countries";
	}

	public static void connect() {
		try {
			connectionURL = Entete + "localhost:3306/projetj2ee";
			Class.forName(ClassForName).newInstance();
			connection = (Connection) DriverManager.getConnection(
					connectionURL, "root", "");
			if (connection.isClosed()) {
				connection.close();
				connection = null;
			}
		} catch (Exception E) {
			System.out.println("Exception à DBAccessControl.connect() : "
					+ E.getMessage());
			connection = null;
		}
	}

	public static void connect(String Entete, String Host, String Port,
			String DB, String User, String Pass) {
		try {
			connectionURL = Entete + Host + ":" + Port + "/" + DB + "";
			Class.forName(ClassForName).newInstance();
			connection = (Connection) DriverManager.getConnection(
					connectionURL, User, Pass);
			if (connection.isClosed()) {
				connection.close();
				connection = null;
			}
		} catch (Exception E) {
			System.out.println("Exception à DBAccessControl.connect(" + Entete
					+ "," + Host + "," + Port + "," + DB + "," + User + ","
					+ Pass + ") : " + E.getMessage());
			connection = null;
		}
	}

	@SuppressWarnings("unused")
	public static String createQuery(String For, String fields, Object Param1,
			String Links) {
		String SQL = (For.split(",")[1].toLowerCase().equals("select")) ? "SELECT * FROM"
				: (For.split(",")[1].toLowerCase().equals("delete")) ? "DELETE FROM"
						: null;
		String Fields = getFields(For.split(",")[0]);
		if (SQL == null || Fields == null) {
			return null;
		}
		if (Fields != null) {
			SQL += " " + For.split(",")[0];
		}
		if (fields == null || fields.equals("")) {
			if (!((String) Param1).equals("") || ((String) Param1) != null) {
				String Sort = ((String) Param1).split("&")[0];
				String limit = ((String) Param1).split("&")[1];
				if (!Sort.equals("-1")) {
					SQL += " ORDER BY " + Sort.split("£")[1] + " "
							+ Sort.split("£")[0];
				}
				if (!limit.equals("-1")) {
					SQL += " LIMIT " + limit;
				}
				return SQL;
			}
			return SQL;
		}
		int current = 0;
		for (int i = 0; i < fields.split("&").length; i++) {
			String CurrentField = fields.split("&")[i];
			if (Fields.indexOf(CurrentField) != -1) {
				String Currentvalue = ((String) Param1).split("&")[i];
				String Value = Currentvalue.split("£")[1];
				String Operateur = Currentvalue.split("£")[0];
				if (current == 0) {
					SQL += " WHERE (" + CurrentField + " " + Operateur + " '"
							+ Value + "')";
					current++;
				} else {
					if (!Links.equals("") && Links != null) {
						SQL += " " + Links.split("&")[i - 1] + " ("
								+ CurrentField + " " + Operateur + " '" + Value
								+ "')";
					} else {
						SQL += " AND (" + CurrentField + " " + Operateur + " '"
								+ Value + "')";
					}
				}
			} else {
				return "Unkown Column '" + CurrentField + "'";
			}
		}
		String Current = ((String) Param1).split("&")[fields.split("&").length];
		if (!Current.equals("-1")) {
			String field = Current.split("£")[1];
			String Order = Current.split("£")[0];
			if (Fields.indexOf(field) != -1) {
				SQL += " ORDER BY " + field + " " + Order;
			} else {
				return "Unknown Column '" + field + "' to Sort By";
			}
		}
		Current = ((String) Param1).split("&")[fields.split("&").length + 1];
		if (!Current.equals("-1")) {
			SQL += " LIMIT " + Current;
		}
		return SQL;
	}

	@SuppressWarnings("unused")
	public static String createQuery(String For, String fields, Object Param1,
			String fields2, Object Param2, String Links) {
		if (fields2 == null || fields2.equals("")) {
			System.out.println("Not an Update");
			return "Not an Update";
		}
		String SQL = "UPDATE";
		String Fields = getFields(For.split(",")[0]);
		if (SQL == null || Fields == null) {
			return null;
		}
		if (Fields != null) {
			SQL += " " + For;
		}
		int current2 = 0;
		for (int i = 0; i < fields2.split("&").length; i++) {
			String CurrentField = fields2.split("&")[i];
			if (Fields.indexOf(CurrentField) != -1) {
				String Currentvalue = ((String) Param2).split("&")[i];
				if (current2 == 0) {
					SQL += " SET " + CurrentField + " = '" + Currentvalue + "'";
					current2++;
				} else {
					SQL += "," + CurrentField + " = '" + Currentvalue + "'";
				}
			} else {
				return "Unkown Column '" + CurrentField + "' in Update";
			}
		}
		if (fields == null || fields.equals("")) {
			if (!((String) Param1).equals("") || ((String) Param1) != null) {
				String Sort = ((String) Param1).split("&")[0];
				String limit = ((String) Param1).split("&")[1];
				if (!Sort.equals("-1")) {
					SQL += " ORDER BY " + Sort.split("£")[1] + " "
							+ Sort.split("£")[0];
				}
				if (!limit.equals("-1")) {
					SQL += " LIMIT " + limit;
				}
				return SQL;
			}
			return SQL;
		}
		int current = 0;
		for (int i = 0; i < fields.split("&").length; i++) {
			String CurrentField = fields.split("&")[i];
			if (Fields.indexOf(CurrentField) != -1) {
				String Currentvalue = ((String) Param1).split("&")[i];
				String Value = Currentvalue.split("£")[1];
				String Operateur = Currentvalue.split("£")[0];
				if (current == 0) {
					SQL += " WHERE (" + CurrentField + " " + Operateur + " '"
							+ Value + "')";
					current++;
				} else {
					if (!Links.equals("") && Links != null) {
						SQL += " " + Links.split("&")[i - 1] + " ("
								+ CurrentField + " " + Operateur + " '" + Value
								+ "')";
					} else {
						SQL += " AND (" + CurrentField + " " + Operateur + " '"
								+ Value + "')";
					}
				}
			} else {
				return "Unkown Column '" + CurrentField + "'";
			}
		}
		String Current = ((String) Param1).split("&")[fields.split("&").length];
		if (!Current.equals("-1")) {
			String field = Current.split("£")[1];
			String Order = Current.split("£")[0];
			if (Fields.indexOf(field) != -1) {
				SQL += " ORDER BY " + field + " " + Order;
			} else {
				return "Unknown Column '" + field + "' to Sort By";
			}
		}
		Current = ((String) Param1).split("&")[fields.split("&").length + 1];
		if (!Current.equals("-1")) {
			SQL += " LIMIT " + Current;
		}
		return SQL;
	}

	public static int executeUpdate(String Query, String From) {
		Statement smt = null;
		if (connection == null)
			connect();
		try {
			smt = (Statement) connection.createStatement();
			smt.executeUpdate(Query);
			return Cool;
		} catch (Exception E) {
			System.out.println("Exception à DBAccessControl.executeUpdate("
					+ Query + "," + From + ") " + E.getMessage());
			return Error;
		} finally {
			try {
				if (smt != null)
					smt.close();
			} catch (Exception E) {
				System.out.println("Exception à DBAccessControl.executeUpdate("
						+ Query + "," + From + ") finally " + E.getMessage());
			}
		}
	}

	public static String generateQuery(String Table, String QueryType,
			String Fields, String Operators, String Values, String Links,
			String SortBy, String Order, String Limt) {
		String fields = "";
		String links = "";
		String Param1 = "";
		String For = Table + "," + QueryType;
		String originalfields = getFields(Table);
		int current = 0, currentl = 0;
		if (Fields.equals("") || Fields == null) {
			if (SortBy.equals("") || SortBy == null || Order.equals("")
					|| Order == null) {
				Param1 += "-1";
			} else {
				Param1 += "" + Order + "£" + SortBy;
			}
			if (Limt != null && !Limt.equals("")) {
				Param1 += "&" + Limt;
			} else {
				Param1 += "&-1";
			}
			return createQuery(For, "", Param1, "");
		}
		for (int i = 0; i < Fields.split("&").length; i++) {
			String CurrentField = Fields.split("&")[i];
			if (originalfields.indexOf(CurrentField) != -1) {
				String CurrentValue = Values.split("&")[i];
				String CurrentOperateur = Operators.split("&")[i];
				if (current == 0) {
					Param1 = CurrentOperateur + "£" + CurrentValue;
					fields = CurrentField;
					current++;
				} else {
					Param1 += "&" + CurrentOperateur + "£" + CurrentValue;
					fields += "&" + CurrentField;
					if (Links != null && !Links.equals("")) {
						if (currentl == 0) {
							links = Links.split("&")[i - 1];
							currentl++;
						} else {
							links += "&" + Links.split("&")[i - 1];
						}
					}
				}
			} else {
				return "Unkown Column '" + CurrentField + "'";
			}
		}
		if (SortBy.equals("") || SortBy == null || Order.equals("")
				|| Order == null) {
			Param1 += "&-1";
		} else {
			Param1 += "&" + Order + "£" + SortBy;
		}
		if (Limt != null && !Limt.equals("")) {
			Param1 += "&" + Limt;
		} else {
			Param1 += "&-1";
		}
		return createQuery(For, fields, Param1, links);
	}

	public static String generateUpdate(String Table, String Fields,
			String Operators, String Values, String Fields2, String Values2,
			String Links, String SortBy, String Order, String Limt) {
		String fields = "";
		String links = "";
		String Param1 = "";
		String For = Table;
		String originalfields = getFields(Table);
		int current = 0, currentl = 0;
		if (Fields.equals("") || Fields == null) {
			if (SortBy.equals("") || SortBy == null || Order.equals("")
					|| Order == null) {
				Param1 += "-1";
			} else {
				Param1 += "" + Order + "£" + SortBy;
			}
			if (Limt != null && !Limt.equals("")) {
				Param1 += "&" + Limt;
			} else {
				Param1 += "&-1";
			}
			return createQuery(For, "", Param1, Fields2, Values2, "");
		}
		for (int i = 0; i < Fields.split("&").length; i++) {
			String CurrentField = Fields.split("&")[i];
			if (originalfields.indexOf(CurrentField) != -1) {
				String CurrentValue = Values.split("&")[i];
				String CurrentOperateur = Operators.split("&")[i];
				if (current == 0) {
					Param1 = CurrentOperateur + "£" + CurrentValue;
					fields = CurrentField;
					current++;
				} else {
					Param1 += "&" + CurrentOperateur + "£" + CurrentValue;
					fields += "&" + CurrentField;
					if (Links != null && !Links.equals("")) {
						if (currentl == 0) {
							links = Links.split("&")[i - 1];
							currentl++;
						} else {
							links += "&" + Links.split("&")[i - 1];
						}
					}
				}
			} else {
				return "Unkown Column '" + CurrentField + "'";
			}
		}
		if (SortBy.equals("") || SortBy == null || Order.equals("")
				|| Order == null) {
			Param1 += "&-1";
		} else {
			Param1 += "&" + Order + "£" + SortBy;
		}
		if (Limt != null && !Limt.equals("")) {
			Param1 += "&" + Limt;
		} else {
			Param1 += "&-1";
		}
		return createQuery(For, fields, Param1, Fields2, Values2, links);
	}

	public static String getFields(String Param) {
		if (Param.equals(Actualites.getTableName())) {
			return Actualites.getFields();
		} else if (Param.equals(Administrateurs.getTableName())) {
			return Administrateurs.getFields();
		} else if (Param.equals(Clients.getTableName())) {
			return Clients.getFields();
		} else if (Param.equals(Commandes.getTableName())) {
			return Commandes.getFields();
		} else if (Param.equals(Marques.getTableName())) {
			return Marques.getFields();
		} else if (Param.equals(NewsLetters.getTableName())) {
			return NewsLetters.getFields();
		} else if (Param.equals(Produits.getTableName())) {
			return Produits.getFields();
		} else if (Param.equals(Typecard.getTableName())) {
			return Typecard.getFields();
		} else if (Param.equals(Countries.getTableName())) {
			return Countries.getFields();
		} else if (Param.equals(Ville.getTableName())) {
			return Ville.getFields();
		} else {
			return null;
		}
	}

	public static int getNewID(String Param) {
		ResultSet rs = null;
		Statement smt = null;
		if (connection == null)
			connect();
		String Sql = "SELECT MAX(ID) FROM ";
		if (Param.equals(Actualites.getTableName())) {
			Sql += Param;
		} else if (Param.equals(Administrateurs.getTableName())) {
			Sql += Param;
		} else if (Param.equals(Clients.getTableName())) {
			Sql += Param;
		} else if (Param.equals(Commandes.getTableName())) {
			Sql += Param;
		} else if (Param.equals(Marques.getTableName())) {
			Sql += Param;
		} else if (Param.equals(NewsLetters.getTableName())) {
			Sql += Param;
		} else if (Param.equals(Produits.getTableName())) {
			Sql += Param;
		} else if (Param.equals(Typecard.getTableName())) {
			Sql += Param;
		} else if (Param.equals(Countries.getTableName())) {
			Sql += Param;
		} else if (Param.equals(Ville.getTableName())) {
			Sql += Param;
		} else {
			return WrongChoise;
		}
		try {
			smt = (Statement) connection.createStatement();
			rs = smt.executeQuery(Sql);
			while (rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;
		} catch (Exception E) {
			System.out.println("Exception à DBAccessControl.getNewID(" + Param
					+ ") " + E.getMessage());
			return Error;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (smt != null)
					smt.close();
			} catch (Exception E) {
				System.out.println("Exception à DBAccessControl.getNewID("
						+ Param + ") finally " + E.getMessage());
			}
		}
	}

	public DBAccessControl() {
	}
}