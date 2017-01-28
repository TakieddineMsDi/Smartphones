package Isamm.ing.java.Smartphones.doAll;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.util.List;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.DAO.ActualitesDAO;
import Isamm.ing.java.Smartphones.DAO.AdministrateursDAO;
import Isamm.ing.java.Smartphones.DAO.ClientsDAO;
import Isamm.ing.java.Smartphones.DAO.CommandesDAO;
import Isamm.ing.java.Smartphones.DAO.CountriesDAO;
import Isamm.ing.java.Smartphones.DAO.MarquesDAO;
import Isamm.ing.java.Smartphones.DAO.NewsLettersDAO;
import Isamm.ing.java.Smartphones.DAO.ProduitsDAO;
import Isamm.ing.java.Smartphones.DAO.TypecardDAO;
import Isamm.ing.java.Smartphones.DAO.VilleDAO;
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

import com.mysql.jdbc.Statement;

public class Do {

	public static int add(String Table, String Param, Object object) {
		if (DBAccessControl.getTablesNames().indexOf(Table) != -1) {
			if (Table.equals(DBAccessControl.getTablesNames().split(",")[0])) {
				if (Param.equals("this")) {
					return ActualitesDAO.add(Param, (Actualites) object);
				} else if (Param.equals("list")) {
					return ActualitesDAO.add(Param, (List<Actualites>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[1])) {
				if (Param.equals("this")) {
					return AdministrateursDAO.add(Param,
							(Administrateurs) object);
				} else if (Param.equals("list")) {
					return AdministrateursDAO.add(Param,
							(List<Administrateurs>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[2])) {
				System.out.println("Adding a client");
				if (Param.equals("this")) {
					return ClientsDAO.add(Param, (Clients) object);
				} else if (Param.equals("list")) {
					return ClientsDAO.add(Param, (List<Clients>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[3])) {
				if (Param.equals("this")) {
					return CommandesDAO.add(Param, (Commandes) object);
				} else if (Param.equals("list")) {
					return CommandesDAO.add(Param, (List<Commandes>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[4])) {
				if (Param.equals("this")) {
					return MarquesDAO.add(Param, (Marques) object);
				} else if (Param.equals("list")) {
					return MarquesDAO.add(Param, (List<Marques>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[5])) {
				if (Param.equals("this")) {
					return NewsLettersDAO.add(Param, (NewsLetters) object);
				} else if (Param.equals("list")) {
					return NewsLettersDAO
							.add(Param, (List<NewsLetters>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[6])) {
				if (Param.equals("this")) {
					return ProduitsDAO.add(Param, (Produits) object);
				} else if (Param.equals("list")) {
					return ProduitsDAO.add(Param, (List<Produits>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[7])) {
				if (Param.equals("this")) {
					return TypecardDAO.add(Param, (Typecard) object);
				} else if (Param.equals("list")) {
					return TypecardDAO.add(Param, (List<Typecard>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			}else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[8])) {
				if (Param.equals("this")) {
					return VilleDAO.add(Param, (Ville) object);
				} else if (Param.equals("list")) {
					return VilleDAO.add(Param, (List<Ville>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			}else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[9])) {
				if (Param.equals("this")) {
					return CountriesDAO.add(Param, (Countries) object);
				} else if (Param.equals("list")) {
					return CountriesDAO.add(Param, (List<Countries>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else {
				return DBAccessControl.WrongChoise;
			}
		} else {
			return DBAccessControl.Error;
		}
	}

	public static List<Marques> convertToMarques(List<NewsLetters> list) {
		return NewsLettersDAO.toMarques(list);
	}

	public static int Count(String Table, String Fields, String Operators,
			String Values, String Links, String SortBy, String Order,
			String Limt) {
		if (DBAccessControl.getTablesNames().indexOf(Table) != -1) {
			ResultSet rs = null;
			Statement smt = null;
			if (DBAccessControl.connection == null)
				DBAccessControl.connect();
			try {
				smt = (Statement) DBAccessControl.connection.createStatement();
				String SQL = DBAccessControl.generateQuery(Table, "select",
						Fields, Operators, Values, Links, SortBy, Order, Limt)
						.replace("*", "COUNT(ID)");
				System.out.println(SQL);
				rs = smt.executeQuery(SQL);
				while (rs.next()) {
					return rs.getInt(1);
				}
				return DBAccessControl.Empty;
			} catch (Exception E) {
				System.out.println("Exception à TypeCardDAO.get() "
						+ E.getMessage());
				return DBAccessControl.Error;
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
		return DBAccessControl.WrongChoise;
	}

	@SuppressWarnings("unchecked")
	public static int delete(String Table, String Param, Object object,
			String Fields, String Operators, String Values, String Links,
			String SortBy, String Order, String Limt) {
		if (DBAccessControl.getTablesNames().indexOf(Table) != -1) {
			if (Table.equals(DBAccessControl.getTablesNames().split(",")[0])) {
				if (Param.equals("this")) {
					return ActualitesDAO.delete((Actualites) object);
				} else if (Param.equals("list")) {
					return ActualitesDAO.delete((List<Actualites>) object);
				} else if (Param.equals("query")) {
					return ActualitesDAO.delete(Fields, Operators, Values,
							Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[1])) {
				if (Param.equals("this")) {
					return AdministrateursDAO.delete((Administrateurs) object);
				} else if (Param.equals("list")) {
					return AdministrateursDAO
							.delete((List<Administrateurs>) object);
				} else if (Param.equals("query")) {
					return AdministrateursDAO.delete(Fields, Operators, Values,
							Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[2])) {
				if (Param.equals("this")) {
					return ClientsDAO.delete((Clients) object);
				} else if (Param.equals("list")) {
					return ClientsDAO.delete((List<Clients>) object);
				} else if (Param.equals("query")) {
					return ClientsDAO.delete(Fields, Operators, Values, Links,
							SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[3])) {
				if (Param.equals("this")) {
					return CommandesDAO.delete((Commandes) object);
				} else if (Param.equals("list")) {
					return CommandesDAO.delete((List<Commandes>) object);
				} else if (Param.equals("query")) {
					return CommandesDAO.delete(Fields, Operators, Values,
							Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[4])) {
				if (Param.equals("this")) {
					return MarquesDAO.delete((Marques) object);
				} else if (Param.equals("list")) {
					return MarquesDAO.delete((List<Marques>) object);
				} else if (Param.equals("query")) {
					return MarquesDAO.delete(Fields, Operators, Values, Links,
							SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[5])) {
				if (Param.equals("this")) {
					return NewsLettersDAO.delete((NewsLetters) object);
				} else if (Param.equals("list")) {
					return NewsLettersDAO.delete((List<NewsLetters>) object);
				} else if (Param.equals("query")) {
					return NewsLettersDAO.delete(Fields, Operators, Values,
							Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[6])) {
				if (Param.equals("this")) {
					return ProduitsDAO.delete((Produits) object);
				} else if (Param.equals("list")) {
					return ProduitsDAO.delete((List<Produits>) object);
				} else if (Param.equals("query")) {
					return ProduitsDAO.delete(Fields, Operators, Values, Links,
							SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[7])) {
				if (Param.equals("this")) {
					return TypecardDAO.delete((Typecard) object);
				} else if (Param.equals("list")) {
					return TypecardDAO.delete((List<Typecard>) object);
				} else if (Param.equals("query")) {
					return TypecardDAO.delete(Fields, Operators, Values, Links,
							SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			}else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[8])) {
				if (Param.equals("this")) {
					return VilleDAO.delete((Ville) object);
				} else if (Param.equals("list")) {
					return VilleDAO.delete((List<Ville>) object);
				} else if (Param.equals("query")) {
					return VilleDAO.delete(Fields, Operators, Values, Links,
							SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			}else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[9])) {
				if (Param.equals("this")) {
					return CountriesDAO.delete((Countries) object);
				} else if (Param.equals("list")) {
					return CountriesDAO.delete((List<Countries>) object);
				} else if (Param.equals("query")) {
					return CountriesDAO.delete(Fields, Operators, Values, Links,
							SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else {
				return DBAccessControl.WrongChoise;
			}
		} else {
			return DBAccessControl.Error;
		}
	}
    public static boolean isSubscribed(int id,List<Marques> list)
    {
    	return NewsLettersDAO.subscribed(id, list);
    }
    
	public static Object get(String Table, String Fields, String Operators,
			String Values, String Links, String SortBy, String Order,
			String Limt, String size) {
		if (DBAccessControl.getTablesNames().indexOf(Table) != -1) {
			if (Table.equals(DBAccessControl.getTablesNames().split(",")[0])) {
				return ActualitesDAO.get(Fields, Operators, Values, Links,
						SortBy, Order, Limt, size);
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[1])) {
				return AdministrateursDAO.get(Fields, Operators, Values, Links,
						SortBy, Order, Limt, size);
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[2])) {
				return ClientsDAO.get(Fields, Operators, Values, Links, SortBy,
						Order, Limt, size);
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[3])) {
				return CommandesDAO.get(Fields, Operators, Values, Links,
						SortBy, Order, Limt, size);
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[4])) {
				return MarquesDAO.get(Fields, Operators, Values, Links, SortBy,
						Order, Limt, size);
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[5])) {
				return NewsLettersDAO.get(Fields, Operators, Values, Links,
						SortBy, Order, Limt, size);
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[6])) {
				return ProduitsDAO.get(Fields, Operators, Values, Links,
						SortBy, Order, Limt, size);
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[7])) {
				return TypecardDAO.get(Fields, Operators, Values, Links,
						SortBy, Order, Limt, size);
			}else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[8])) {
				return VilleDAO.get(Fields, Operators, Values, Links,
						SortBy, Order, Limt, size);
			}else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[9])) {
				return CountriesDAO.get(Fields, Operators, Values, Links,
						SortBy, Order, Limt, size);
			} else {
				return DBAccessControl.WrongChoise;
			}
		} else {
			return null;
		}
	}

	public static Object login(String Table, String ID, String Pass) {
		if (DBAccessControl.getTablesNames().indexOf(Table) != -1) {
			if (Table.equals(DBAccessControl.getTablesNames().split(",")[1])) {
				return AdministrateursDAO.login(ID, Pass);
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[2])) {
				return ClientsDAO.login(ID, Pass);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static boolean sameNewsLetters(Clients client) {
		return NewsLettersDAO.same(client);
	}

	public static int save(String Table, String Param, Object object) {
		if (DBAccessControl.getTablesNames().indexOf(Table) != -1) {
			if (Table.equals(DBAccessControl.getTablesNames().split(",")[0])) {
				if (Param.equals("this")) {
					return ActualitesDAO.save((Actualites) object);
				} else if (Param.equals("list")) {
					return ActualitesDAO.save((List<Actualites>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[1])) {
				if (Param.equals("this")) {
					return AdministrateursDAO.save((Administrateurs) object);
				} else if (Param.equals("list")) {
					return AdministrateursDAO
							.save((List<Administrateurs>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[2])) {
				if (Param.equals("this")) {
					return ClientsDAO.save((Clients) object);
				} else if (Param.equals("list")) {
					return ClientsDAO.save((List<Clients>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[3])) {
				if (Param.equals("this")) {
					return CommandesDAO.save((Commandes) object);
				} else if (Param.equals("list")) {
					return CommandesDAO.save((List<Commandes>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[4])) {
				if (Param.equals("this")) {
					return MarquesDAO.save((Marques) object);
				} else if (Param.equals("list")) {
					return MarquesDAO.save((List<Marques>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[6])) {
				if (Param.equals("this")) {
					return ProduitsDAO.save((Produits) object);
				} else if (Param.equals("list")) {
					return ProduitsDAO.save((List<Produits>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[7])) {
				if (Param.equals("this")) {
					return TypecardDAO.save((Typecard) object);
				} else if (Param.equals("list")) {
					return TypecardDAO.save((List<Typecard>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			}else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[8])) {
				if (Param.equals("this")) {
					return VilleDAO.save((Ville) object);
				} else if (Param.equals("list")) {
					return VilleDAO.save((List<Ville>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			}else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[9])) {
				if (Param.equals("this")) {
					return CountriesDAO.save((Countries) object);
				} else if (Param.equals("list")) {
					return CountriesDAO.save((List<Countries>) object);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else {
				return DBAccessControl.WrongChoise;
			}
		} else {
			return DBAccessControl.Error;
		}
	}

	public static int update(String Table, String Param, Object object,
			String Fields, String Operators, String Values, String Fields2,
			String Values2, String Links, String SortBy, String Order,
			String Limt) {
		if (DBAccessControl.getTablesNames().indexOf(Table) != -1) {
			if (Table.equals(DBAccessControl.getTablesNames().split(",")[0])) {
				if (Param.equals("this")) {
					return ActualitesDAO.update((Actualites) object, Fields2,
							Values2);
				} else if (Param.equals("list")) {
					return ActualitesDAO.update((List<Actualites>) object,
							Fields2, Values2);
				} else if (Param.equals("query")) {
					return ActualitesDAO.update(Fields, Operators, Values,
							Fields2, Values2, Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[1])) {
				if (Param.equals("this")) {
					return AdministrateursDAO.update((Administrateurs) object,
							Fields2, Values2);
				} else if (Param.equals("list")) {
					return AdministrateursDAO.update(
							(List<Administrateurs>) object, Fields2, Values2);
				} else if (Param.equals("query")) {
					return AdministrateursDAO.update(Fields, Operators, Values,
							Fields2, Values2, Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[2])) {
				if (Param.equals("this")) {
					return ClientsDAO
							.update((Clients) object, Fields2, Values2);
				} else if (Param.equals("list")) {
					return ClientsDAO.update((List<Clients>) object, Fields2,
							Values2);
				} else if (Param.equals("query")) {
					return ClientsDAO.update(Fields, Operators, Values,
							Fields2, Values2, Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[3])) {
				if (Param.equals("this")) {
					return CommandesDAO.update((Commandes) object, Fields2,
							Values2);
				} else if (Param.equals("list")) {
					return CommandesDAO.update((List<Commandes>) object,
							Fields2, Values2);
				} else if (Param.equals("query")) {
					return CommandesDAO.update(Fields, Operators, Values,
							Fields2, Values2, Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[4])) {
				if (Param.equals("this")) {
					return MarquesDAO
							.update((Marques) object, Fields2, Values2);
				} else if (Param.equals("list")) {
					return MarquesDAO.update((List<Marques>) object, Fields2,
							Values2);
				} else if (Param.equals("query")) {
					return MarquesDAO.update(Fields, Operators, Values,
							Fields2, Values2, Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[5])) {
				if (Param.equals("this")) {
					return NewsLettersDAO.update((NewsLetters) object, Fields2,
							Values2);
				} else if (Param.equals("list")) {
					return NewsLettersDAO.update((List<NewsLetters>) object,
							Fields2, Values2);
				} else if (Param.equals("query")) {
					return NewsLettersDAO.update(Fields, Operators, Values,
							Fields2, Values2, Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[6])) {
				if (Param.equals("this")) {
					return ProduitsDAO.update((Produits) object, Fields2,
							Values2);
				} else if (Param.equals("list")) {
					return ProduitsDAO.update((List<Produits>) object, Fields2,
							Values2);
				} else if (Param.equals("query")) {
					return ProduitsDAO.update(Fields, Operators, Values,
							Fields2, Values2, Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[7])) {
				if (Param.equals("this")) {
					return TypecardDAO.update((Typecard) object, Fields2,
							Values2);
				} else if (Param.equals("list")) {
					return TypecardDAO.update((List<Typecard>) object, Fields2,
							Values2);
				} else if (Param.equals("query")) {
					return TypecardDAO.update(Fields, Operators, Values,
							Fields2, Values2, Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			}else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[8])) {
				if (Param.equals("this")) {
					return VilleDAO.update((Ville) object, Fields2,
							Values2);
				} else if (Param.equals("list")) {
					return VilleDAO.update((List<Ville>) object, Fields2,
							Values2);
				} else if (Param.equals("query")) {
					return VilleDAO.update(Fields, Operators, Values,
							Fields2, Values2, Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			}else if (Table
					.equals(DBAccessControl.getTablesNames().split(",")[9])) {
				if (Param.equals("this")) {
					return CountriesDAO.update((Countries) object, Fields2,
							Values2);
				} else if (Param.equals("list")) {
					return CountriesDAO.update((List<Countries>) object, Fields2,
							Values2);
				} else if (Param.equals("query")) {
					return CountriesDAO.update(Fields, Operators, Values,
							Fields2, Values2, Links, SortBy, Order, Limt);
				} else {
					return DBAccessControl.WrongChoise;
				}
			} else {
				return DBAccessControl.WrongChoise;
			}
		} else {
			return DBAccessControl.Error;
		}
	}

}
