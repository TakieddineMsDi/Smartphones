package Isamm.ing.java.Smartphones.Models;

import java.io.Serializable;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.DAO.CommandesDAO;
import Isamm.ing.java.Smartphones.doAll.Do;

public class Commandes implements Serializable {
	private static final long serialVersionUID = -3963857968496657770L;
	private int ID, IDClient, IDProduit, Quantite;
	private java.sql.Date Date;
	private int ValidationClient, ValidationAdmin,Statut;

	
	@Override
	public String toString() {
		return "ID:"+this.ID
				+ ",IDClient:"+this.IDClient
				+ ",IDProduit:"+this.IDProduit
				+ ",Quantite:"+this.Quantite
				+ ",Date:"+this.Date
				+ ",ValidationClient:"+this.ValidationClient
				+ ",ValidationAdmin:"+this.ValidationAdmin
				+ ",Statut:"+this.Statut;
	}
	
	public boolean equals(Commandes commande)
	{
		if(this.toString().equals(commande.toString()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean isArchive()
	{
		return Statut == DBAccessControl.Archive;
	}
	
	public boolean isBanned()
	{
		return Statut == DBAccessControl.Banned;
	}
	
	public double getPrice()
	{
		return this.Quantite * this.getProduit().getPrixUnitaire();
	}
	
	public Administrateurs getValidator()
	{
		if(ValidationAdmin!=0)
		{
			return (Administrateurs)Do.get(Administrateurs.getTableName(), "ID", "=", ""+ValidationAdmin, "", "", "", "", "Unique");
		}
		else{
			return null;
		}
	}
	public boolean isActive()
	{
		if(this.Statut==DBAccessControl.Actif)
		{return true;}
		else
		{return false;}
	}
	
	public boolean isInactive()
	{
		if(this.Statut==DBAccessControl.Inactif)
		{return true;}
		else
		{return false;}
	}
	
	public boolean isValidated()
	{
		if(this.ValidationAdmin!=0)
		{return true;}
		else
		{return false;}
	}
	
	public boolean isStillActive()
	{
		if(this.ValidationClient==DBAccessControl.Actif)
		{return true;}
		else
		{return false;}
	}
	
	public boolean isEmpty()
	{
		return ID==DBAccessControl.Empty;
	}
	
	public int save()
	{
		return CommandesDAO.save(this);
	}
	
	public int delete()
	{
		Produits temp = this.getProduit();
		temp.setQuantite(temp.getQuantite()+this.Quantite);
		temp.save();
		return CommandesDAO.delete(this);
	}
	
	public static String getTableName()
	{return "commandes";}
	public static String getFields()
	{
		return "ID,IDClient,IDProduit,Quantite,Date,ValidationClient,ValidationAdmin,Statut";
	}
	public Commandes() {

	}

	public Commandes(int id, int idclient, int idproduit, int quantite,
			java.sql.Date date, int vc, int va,int statut) {
		this.ID = id;
		this.IDClient = idclient;
		this.IDProduit = idproduit;
		this.Quantite = quantite;
		this.Date = date;
		this.ValidationClient = vc;
		this.ValidationAdmin = va;
		this.Statut = statut;
	}

	public Produits getProduit()
	{
		return (Produits)Do.get(Produits.getTableName(), "ID", "=", ""+IDProduit, "", "", "", "", "Unique");
	}
	
	public Clients getOwner()
	{
		return (Clients)Do.get(Clients.getTableName(), "ID", "=", ""+IDClient, "", "", "", "", "Unique");
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getIDClient() {
		return IDClient;
	}

	public void setIDClient(int iDClient) {
		IDClient = iDClient;
	}

	public int getIDProduit() {
		return IDProduit;
	}

	public void setIDProduit(int iDProduit) {
		IDProduit = iDProduit;
	}

	public int getQuantite() {
		return Quantite;
	}

	public void setQuantite(int quantite) {
		Quantite = quantite;
	}

	public int getValidationClient() {
		return ValidationClient;
	}

	public void setValidationClient(int validationClient) {
		ValidationClient = validationClient;
	}

	public int getValidationAdmin() {
		return ValidationAdmin;
	}

	public void setValidationAdmin(int validationAdmin) {
		ValidationAdmin = validationAdmin;
	}

	public java.sql.Date getDate() {
		return Date;
	}

	public void setDate(java.sql.Date date) {
		Date = date;
	}

	public int getStatut() {
		return Statut;
	}

	public void setStatut(int statut) {
		Statut = statut;
	}
}
