package Isamm.ing.java.Smartphones.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Models.Actualites;
import Isamm.ing.java.Smartphones.Models.Produits;

import com.mysql.jdbc.Statement;

public class ProduitsDAO {
	
	// addActualite("this",Actualites) add one actualite
	// addActualite("list",List<Actualites>) add list of actualite
	public static int add(String Param, Object Param1) {
		if (Param.equals("this")) {
			Produits produit = (Produits) Param1;
			if (produit == null) {
				return DBAccessControl.Error;
			}
			if (produit.getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
			return DBAccessControl
					.executeUpdate(
							"INSERT INTO "+Produits.getTableName()+"("+Produits.getFields()+") "
									+ "VALUES ("
									+ produit.getID()
									+ ",'"
									+ produit.getLibelle()
									+ "',"
									+ produit.getPrixUnitaire()
									+ ","
									+ produit.getQuantite()
									+ ",'"
									+ produit.getURL()
									+ "',"
									+ produit.getMarque()
									+ ",'"
									+ produit.getDescription()
									+ "',"
									+ produit.getOwner()
									+ ","
									+ produit.getIDOnwer()
									+ ","
									+ produit.getStatut()
									+ ",'"
									+ produit.getDateSubmit() + "'"
									+ ",'"+produit.getDownloads()+"'"
									+ ",'"+produit.getRating()+"'"
									+ ")",
							"ProduitsDAO.addProduit");
		} else if (Param.equals("list")) {
			List<Produits> list = (List<Produits>) Param1;
			if (list == null) {
				return DBAccessControl.Error;
			}
			if (list.get(0).getID() == DBAccessControl.Empty) {
				return DBAccessControl.Empty;
			}
				for (Produits produit : list) {
					if (add("this", produit) == DBAccessControl.Error) {
						return DBAccessControl.Error;
					}
				}
		} else {
			return DBAccessControl.WrongChoise;
		}
		return DBAccessControl.Cool;
	}

	public static int delete(Produits produit) {
		if (produit.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (produit == null) {
			return DBAccessControl.Error;
		}
		return delete("ID", "=", "" + produit.getID(), "", "", "", "");
	}

	public static int delete(List<Produits> produits) {
		if (produits.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (produits == null) {
			return DBAccessControl.Error;
		}
		for (Produits produit : produits) {
			if (delete("ID", "=", "" + produit.getID(), "", "", "", "") == -1) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int delete(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateQuery(
				Produits.getTableName(), "delete", Fields, Operators, Values, Links,
				SortBy, Order, Limt), "ProduitsDAO.delete");
	}

	public static Object get(String Fields, String Operators, String Values,
			String Links, String SortBy, String Order, String Limt, String size) {
		ResultSet rs = null;
		Statement smt = null;
		if (DBAccessControl.connection == null)
			DBAccessControl.connect();
		List<Produits> list = new ArrayList<Produits>();
		Produits produit = null;
		try {
			smt = (Statement) DBAccessControl.connection.createStatement();
			rs = smt.executeQuery(DBAccessControl.generateQuery(Produits.getTableName(),
					"select", Fields, Operators, Values, Links, SortBy, Order,
					Limt));
			if (size.equals("Unique")) {
				while (rs.next()) {
					produit = new Produits(rs.getInt(1), rs.getString(2),
							rs.getDouble(3), rs.getInt(4), rs.getString(5),
							rs.getInt(6), rs.getString(7), rs.getInt(8),
							rs.getInt(9), rs.getInt(10), rs.getDate(11),rs.getInt(12),rs.getInt(13));
					return produit;
				}
				produit = new Produits();
				produit.setID(DBAccessControl.Empty);
				return produit;
			} else if (size.equals("List")) {
				while (rs.next()) {
					produit = (Produits) get("ID", "=", "" + rs.getInt(1),
							Links, SortBy, Order, Limt, "Unique");
					list.add(produit);
				}
				if (list.size() != 0) {
					return list;
				} else {
					produit = (Produits) get("ID", "=", "0", Links, SortBy,
							Order, Limt, "Unique");
					list.add(produit);
					return list;
				}
			} else {
				System.out.println("Wrong parameters");
				return null;
			}
		} catch (Exception E) {
			System.out.println("Exception à ProduitsDAO.get() "
					+ E.getMessage());
			return null;
		} finally {
			try {
				if (smt != null)
					smt.close();
				if (rs != null)
					rs.close();
			} catch (Exception E) {
				System.out.println("Exception à ProduitsDAO.get() finally "
						+ E.getMessage());
			}
		}
	}

	public static int save(Produits New) {
		Produits old = (Produits) get("ID", "=", "" + New.getID(), "", "",
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
			if (!old.getLibelle().equals(New.getLibelle())) {
				res += update(old,"Libelle",New.getLibelle());
			}
			if (old.getPrixUnitaire() != New.getPrixUnitaire()) {
				res += update(old,"PrixUnitaire",""+New.getPrixUnitaire());
			}
			if (old.getQuantite() != New.getQuantite()) {
				res += update(old,"Quantite",""+New.getQuantite());
			}
			if (!old.getURL().equals(New.getURL())) {
				res += update(old,"URL",New.getURL());
			}
			if (old.getMarque() != New.getMarque()) {
				res += update(old,"Marque",""+New.getMarque());
			}
			if (!old.getDescription().equals(New.getDescription())) {
				res += update(old,"Description",New.getDescription());
			}
			if (old.getOwner() == New.getOwner()) {
				res += update(old,"Owner",""+New.getOwner());
			}
			if (old.getIDOnwer() != New.getIDOnwer() ) {
				res += update(old,"IDOwner",""+New.getIDOnwer());
			}
			if (old.getStatut() != New.getStatut() ) {
				res += update(old,"Statut",""+New.getStatut());
			}
			if (!old.getDateSubmit().equals(New.getDateSubmit())) {
				res += update(old,"DateSubmit",""+New.getDateSubmit());
			}
			if (old.getDownloads() != New.getDownloads()) {
				res += update(old,"Downloads",""+New.getDownloads());
			}
			if (old.getRating() != New.getRating()) {
				res += update(old,"Rating",""+New.getRating());
			}
			if (res.contains(""+DBAccessControl.Error)) {
				return DBAccessControl.Error;
			} else {
				return DBAccessControl.Cool;
			}
		}
	}

	public static int save(List<Produits> New) {
		if (New.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (New == null) {
			return DBAccessControl.Error;
		}
		for (Produits produit : New) {
			if (produit.save() == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}

	public static int update(List<Produits> produits, String Fields2,
			String Values2) {
		if (produits.get(0).getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (produits == null) {
			return DBAccessControl.Error;
		}
		for (Produits produit : produits) {
			if (update(produit,Fields2,Values2) == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		}
		return DBAccessControl.Cool;
	}
	
	public static int update(Produits produit, String Fields2,
			String Values2) {
		if (produit.getID() == DBAccessControl.Empty) {
			return DBAccessControl.Empty;
		}
		if (produit == null) {
			return DBAccessControl.Error;
		}
			if (update("ID", "=", "" + produit.getID(), Fields2, Values2, "",
					"", "", "") == DBAccessControl.Error) {
				return DBAccessControl.Error;
			}
		return DBAccessControl.Cool;
	}

	public static int update(String Fields, String Operators, String Values,
			String Fields2, String Values2, String Links, String SortBy,
			String Order, String Limt) {
		return DBAccessControl.executeUpdate(DBAccessControl.generateUpdate(
				Produits.getTableName(), Fields, Operators, Values, Fields2, Values2,
				Links, SortBy, Order, Limt), "ProduitsDAO.update");
	}
}
