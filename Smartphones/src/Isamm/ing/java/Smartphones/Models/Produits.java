package Isamm.ing.java.Smartphones.Models;

import java.io.Serializable;
import java.sql.Date;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.DAO.ProduitsDAO;
import Isamm.ing.java.Smartphones.doAll.Do;

public class Produits implements Serializable {
	private static final long serialVersionUID = -3506828261934595812L;
	private int ID;
	private String Libelle;
	private Double PrixUnitaire;
	private int Quantite;
	private String URL;
	private int Marque;
	private String Description;
	private int Owner;
	private int IDOnwer;
	private int Statut;
	private Date DateSubmit;
	private int Downloads,Rating;
	
	@Override
	public String toString() {
		return "ID:"
				+ ",Libelle:"+this.Libelle
				+ ",PrixUnitaire:"+this.PrixUnitaire
				+ ",Quantite:"+this.Quantite
				+ ",URL:"+this.URL
				+ ",Marque:"+this.Marque
				+ ",Description:"+this.Description
				+ ",Owner:"+this.Owner
				+ ",IDOwner:"+this.IDOnwer
				+ ",Statut:"+this.Statut
				+ ",DateSubmit:"+this.DateSubmit
				+ ",Downloads:"+this.Downloads
				+ ",Rating:"+this.Rating;
	}
	
	public boolean equals(Produits produit)
	{
		if(this.toString().equals(produit.toString()))
		{return true;}
		else
		{return false;}
	}
	
	public boolean isActif()
	{
		return (this.Statut==DBAccessControl.Actif);
	}
	public boolean isBanned()
	{
		return this.Statut == DBAccessControl.Banned;
	}
	public boolean isInactif()
	{
		return this.Statut == DBAccessControl.Inactif;
	}
	public boolean isArchive()
	{
		return this.Statut == DBAccessControl.Archive;
	}
	public boolean isTrustWorthy()
	{
		return (this.Owner==1);
	}
	public static String getTableName()
	{return "produits";}
	
	public boolean isEmpty()
	{
		return this.ID == DBAccessControl.Empty;
	}
	public static String getFields()
	{
		return "ID,Libelle,PrixUnitaire,Quantite,URL,Marque,Description,Owner,IDOwner,Statut,DateSubmit,Downloads,Rating";
	}

	public Produits() {

	}
	
	public int save()
	{
		return ProduitsDAO.save(this);
	}
	
	public int deletecommandes()
	{
		return Do.delete(Commandes.getTableName(), "query", "", "IDProduit", "=", ""+ID, "", "", "", "");
	}
	
	public int delete()
	{
		String res = "";
		res += ""+deletecommandes();
		res += ProduitsDAO.delete(this);
		if(res.contains(""+DBAccessControl.Error))
		{
			return DBAccessControl.Error;
		}
		else{
			return DBAccessControl.Cool;
		}
	}

	public Produits(int id, String libelle, Double prixunitaire, int quantite,
			String url, int marque, String description, int owner, int idowner,
			int statut, Date datesubmit,int downloads,int rating) {
		this.ID = id;
		this.Libelle = libelle;
		this.PrixUnitaire = prixunitaire;
		this.Quantite = quantite;
		this.URL = url;
		this.Marque = marque;
		this.Description = description;
		this.Owner = owner;
		this.IDOnwer = idowner;
		this.Statut = statut;
		this.DateSubmit = datesubmit;
		this.Downloads = downloads;
		this.Rating = rating;
	}

	public Marques getRMarque()
	{
		return (Marques)Do.get(Marques.getTableName(), "ID", "=", ""+this.Marque, "", "", "", "", "Unique");
	}
	
	public Object getROwner()
	{
		if(this.Owner == 1)
		{
		   return (Administrateurs)Do.get(Administrateurs.getTableName(), "ID", "=", ""+this.IDOnwer, "", "", "", "", "Unique");
		}
		else if(this.Owner == 2)
		{
			return (Clients)Do.get(Clients.getTableName(), "ID", "=", ""+this.IDOnwer, "", "", "", "", "Unique");
		}
		else {
			return null;
		}
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getLibelle() {
		return Libelle;
	}

	public void setLibelle(String libelle) {
		Libelle = libelle;
	}

	public Double getPrixUnitaire() {
		return PrixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		PrixUnitaire = prixUnitaire;
	}

	public int getQuantite() {
		return Quantite;
	}

	public void setQuantite(int quantite) {
		Quantite = quantite;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public int getMarque() {
		return Marque;
	}

	public void setMarque(int marque) {
		Marque = marque;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getOwner() {
		return Owner;
	}

	public void setOwner(int owner) {
		Owner = owner;
	}

	public int getIDOnwer() {
		return IDOnwer;
	}

	public void setIDOnwer(int iDOnwer) {
		IDOnwer = iDOnwer;
	}

	public int getStatut() {
		return Statut;
	}

	public void setStatut(int statut) {
		Statut = statut;
	}

	public Date getDateSubmit() {
		return DateSubmit;
	}

	public void setDateSubmit(Date dateSubmit) {
		DateSubmit = dateSubmit;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}

	public int getDownloads() {
		return Downloads;
	}

	public void setDownloads(int downloads) {
		Downloads = downloads;
	}

}
