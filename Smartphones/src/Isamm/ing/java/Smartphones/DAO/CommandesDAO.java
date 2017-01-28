package Isamm.ing.java.Smartphones.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Models.Commandes;
import Isamm.ing.java.Smartphones.Models.Produits;

import com.mysql.jdbc.Statement;

public class CommandesDAO {

	public static int add(String Param, Object Param1) {
		if (Param.equals("this")) {
			Commandes Commande = (Commandes) Param1;
			if (Commande == null) {
				return DBAccessControl.Error;
			}
			if (Commande.getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
			int res = 0;
			boolean pass = false;
				Produits temp = Commande.getProduit();
				if(temp.getQuantite()>=Commande.getQuantite())
				{
				temp.setQuantite(temp.getQuantite()-Commande.getQuantite());
				res = temp.save();
				   pass = res==DBAccessControl.Cool;
				}
				else{
					pass = false;
				}
				if(pass)
				{
			res = DBAccessControl.executeUpdate(
					"INSERT INTO " + Commandes.getTableName() + "("
							+ Commandes.getFields() + ")" + "VALUES("
							+ Commande.getID() + "," + Commande.getIDClient()
							+ "," + Commande.getIDProduit() + ","
							+ Commande.getQuantite() + ",'"
							+ Commande.getDate() + "',"
							+ Commande.getValidationClient() + ","
							+ Commande.getValidationAdmin() + ","
							+ Commande.getStatut() + ")",
					"CommandesDAO.addCommande()");
				}
			else{
				return res;
			}
		} else if (Param.equals("list")) {
			List<Commandes> list = (List<Commandes>) Param1;
			if (list == null) {
				return DBAccessControl.Error;
			}
			if (list.get(0).getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
			for (Commandes commande : list) {
				if (add("this", commande) == DBAccessControl.Error) {
					return DBAccessControl.Error;
				}
			}
		} else {
			return DBAccessControl.WrongChoise;
		}
		return DBAccessControl.Cool;
	}

	public static int delete(Commandes commande) {
		if (commande.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (commande == null) {
			return DBAccessControl.Error;
		}
		return delete("ID", "=", "" + commande.getID(), "", "", "", "");
	}

	public static int delete(List<Commandes> commandes) {
		if (commandes.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (commandes == null) {
			return DBAccessControl.Error;
		}
		for (Commandes commande : commandes) {
			if (delete("ID", "=", "" + commande.getID(), "", "", "", "") == -1) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int delete(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateQuery(
				Commandes.getTableName(), "delete", Fields, Operators, Values,
				Links, SortBy, Order, Limt), "CommandesDAO.delete");
	}

	public static Object get(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt, String size) {
		ResultSet rs = null;
		Statement smt = null;
		if (DBAccessControl.connection == null)
			DBAccessControl.connect();
		List<Commandes> list = new ArrayList<Commandes>();
		Commandes commande = null;
		try {
			smt = (Statement) DBAccessControl.connection.createStatement();
			rs = smt.executeQuery(DBAccessControl.generateQuery(
					Commandes.getTableName(), "select", Fields, Operators,
					Values, Links, SortBy, Order, Limt));
			if (size.equals("Unique")) {
				while (rs.next()) {
					commande = new Commandes(rs.getInt(1), rs.getInt(2),
							rs.getInt(3), rs.getInt(4), rs.getDate(5),
							rs.getInt(6), rs.getInt(7), rs.getInt(8));
					return commande;
				}
				commande = new Commandes();
				commande.setID(DBAccessControl.Empty);
				return commande;
			} else if (size.equals("List")) {
				while (rs.next()) {
					commande = (Commandes) get("ID", "=", "" + rs.getInt(1),
							Links, SortBy, Order, Limt, "Unique");
					list.add(commande);
				}
				if (list.size() != 0) {
					return list;
				} else {
					commande = (Commandes) get("ID", "=", "0", Links, SortBy,
							Order, Limt, "Unique");
					list.add(commande);
					return list;
				}
			} else {
				System.out.println("Wrong parameters");
				return null;
			}
		} catch (Exception E) {
			System.out.println("Exception à CommandesDAO.get() "
					+ E.getMessage());
			return null;
		} finally {
			try {
				if (smt != null)
					smt.close();
				if (rs != null)
					rs.close();
			} catch (Exception E) {
				System.out.println("Exception à CommandesDAO.get() finally "
						+ E.getMessage());
			}
		}
	}

	public static int save(Commandes New) {
		Commandes old = (Commandes) get("ID", "=", "" + New.getID(), "", "",
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
			if (New.getIDClient() != old.getIDClient()) {
				res += update(old, "IDClient", "" + New.getIDClient());
			}
			if (New.getIDProduit() != old.getIDClient()) {
				res += update(old, "IDProduit", "" + New.getIDProduit());
			}
			if (New.getQuantite() != old.getQuantite()) {
				boolean pass = false;
				Produits temp = New.getProduit();
				if(New.getQuantite()>old.getQuantite())
				{
					if(New.getQuantite()<=temp.getQuantite())
					{
						temp.setQuantite(New.getQuantite());
						pass = temp.save()==DBAccessControl.Cool?true:false;
					}
				}
				else{
					if(New.getQuantite()<=temp.getQuantite())
					{
						temp.setQuantite(New.getQuantite());
						pass = temp.save()==DBAccessControl.Cool?true:false;
					}
				}
				if(pass)
				{
				res += update(old, "Quantite", "" + New.getQuantite());
				}
				else{
					res += DBAccessControl.QuatiteExceeded;
				}
			}
			if (old.getStatut() != New.getStatut()) {
				res += update(old, "Statut", "" + New.getStatut());
			}
			if (!old.getDate().equals(New.getDate())) {
				res += update(old, "Date", "" + New.getDate());
			}
			if (old.getValidationAdmin() != New.getValidationAdmin()) {
				res += update(old, "ValidationAdmin",
						"" + New.getValidationAdmin());
			}
			if (old.getValidationClient() != New.getValidationClient()) {
				res += update(old, "ValidationClient",
						"" + New.getValidationClient());
			}
			if (res.contains("" + DBAccessControl.Error)) {
				return DBAccessControl.Error;
			} else {
				return DBAccessControl.Cool;
			}
		}
	}

	public static int save(List<Commandes> New) {
		if (New.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (New == null) {
			return DBAccessControl.Error;
		}
		for (Commandes commande : New) {
			if (commande.save() == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int update(Commandes commande, String Fields2, String Values2) {
		if (commande.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (commande == null) {
			return DBAccessControl.Error;
		}
		if (update("ID", "=", "" + commande.getID(), Fields2, Values2, "", "",
				"", "") == DBAccessControl.Error) {
			return DBAccessControl.Error;
		}
		return DBAccessControl.Cool;
	}

	public static int update(List<Commandes> commandes, String Fields2,
			String Values2) {
		if (commandes.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (commandes == null) {
			return DBAccessControl.Error;
		}
		for (Commandes commande : commandes) {
			if (update(commande, Fields2, Values2) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int update(String Fields, String Operators, String Values,
			String Fields2, String Values2, String Links, String SortBy,
			String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateUpdate(
				Commandes.getTableName(), Fields, Operators, Values, Fields2,
				Values2, Links, SortBy, Order, Limt), "CommandesDAO.update");
	}

}
