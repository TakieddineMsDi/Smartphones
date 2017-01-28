package Isamm.ing.java.Smartphones.Models;

import java.io.Serializable;
import java.util.List;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.DAO.ActualitesDAO;
import Isamm.ing.java.Smartphones.doAll.Do;

public class Actualites implements Serializable {
	private static final long serialVersionUID = -7056524908633869293L;
	private int ID;
	private String Titre, Description, URL;
	private java.sql.Date Date;
	private int IDAdmin,Statut;

	public static String getTableName()
	{return "actualites";}
	public static String getFields()
	{
		return "ID,Titre,Description,URL,Date,IDAdmin,Statut";
	}
	public Actualites() {

	}
	
	public static List<Actualites> getLatest(int limit)
	{
		return ((List<Actualites>) ActualitesDAO.get("", "", "", "", "Date", "DESC", ""+limit, "List"));
	}
	@Override
	public String toString() {
		return "ID:"+this.ID
				+",Titre:"+this.Titre
				+",Description:"+this.Description
				+",URL:"+this.URL
				+",Date:"+this.Date
				+",IDAdmin:"+this.IDAdmin
				+",Statut:"+this.Statut;
	}
	
	public boolean equals(Actualites actualite)
	{
		if(this.toString().equals(actualite.toString()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isActive()
	{
		if(this.Statut==1)
		{return true;}
		else
		{return false;}
	}
	public Administrateurs getOwner()
	{
		return (Administrateurs)Do.get(Administrateurs.getTableName(), "ID", "=", ""+IDAdmin, "", "", "", "", "Unique");
	}
	public boolean isEmpty()
	{
		return this.ID == DBAccessControl.Empty;
	}
	public boolean isInactive()
	{
		return this.Statut == DBAccessControl.Inactif;
	}
	public boolean isArchive()
	{
		return this.Statut == DBAccessControl.Archive;
	}
	public boolean isBanned()
	{
		return this.Statut == DBAccessControl.Banned;
	}
	public int save()
	{
		return ActualitesDAO.save(this);
	}
	public int delete()
	{
		return ActualitesDAO.delete(this);
	}
	
	public Actualites(int id, String titre, String description, String url,
			java.sql.Date date, int idadmin,int statut) {
		this.ID = id;
		this.Titre = titre;
		this.Description = description;
		this.URL = url;
		this.Date = date;
		this.IDAdmin = idadmin;
		this.Statut = statut;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitre() {
		return Titre;
	}

	public void setTitre(String titre) {
		Titre = titre;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public java.sql.Date getDate() {
		return Date;
	}

	public void setDate(java.sql.Date date) {
		Date = date;
	}

	public int getIDAdmin() {
		return IDAdmin;
	}

	public void setIDAdmin(int iDAdmin) {
		IDAdmin = iDAdmin;
	}

	public int getStatut() {
		return Statut;
	}

	public void setStatut(int statut) {
		Statut = statut;
	}
}
