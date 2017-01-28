package Isamm.ing.java.Smartphones.Models;

import java.io.Serializable;
import java.sql.Date;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.DAO.AdministrateursDAO;
import Isamm.ing.java.Smartphones.doAll.Do;

public class Administrateurs implements Serializable {
	private static final long serialVersionUID = 299497149809300348L;
	private int ID;
	private String Username, Password, Email, Nom, Prenom, Adresse;
	private Date DateInscription;
	private int Level;

	public Administrateurs() {

	}

	public int add(String Table,String Param,Object object)
	{
		return Do.add(Table, Param, object);
	}
	public int delete(String Table,String Param,Object object,String Fields,String Operators,String Values,String Links,String SortBy,String Order,String Limt)
	{
		return Do.delete(Table, Param, object, Fields, Operators, Values, Links, SortBy, Order, Limt);
	}
	public Object get(String Table,String Fields,String Operators,String Values,String Links,String SortBy,String Order,String Limt,String Size)
	{
		return Do.get(Table, Fields, Operators, Values, Links, SortBy, Order, Limt,Size);
	}
	public int update(String Table,String Param,Object object,String Fields,String Operators,String Values,String Fields2,String Values2,String Links,String SortBy,String Order,String Limt)
	{
		return Do.update(Table, Param, object, Fields, Operators, Values, Fields2, Values2, Links, SortBy, Order, Limt);
	}
	public int save(String Table,String Param,Object object)
	{
		return Do.save(Table, Param, object);
	}
	public int Count(String Table,String Fields,String Operators,String Values,String Links,String SortBy,String Order,String Limt)
	{
		return Do.Count(Table, Fields, Operators, Values, Links, SortBy, Order, Limt);
	}
	@Override
	public String toString() {
		return "ID:"+this.ID
				+",Username:"+this.Username
				+",Password:"+this.Password
				+",Email:"+this.Email
				+",Nom:"+this.Nom
				+",Prenom:"+this.Prenom
				+",Adresse:"+this.Adresse
				+",DateInscription:"+this.DateInscription
				+",Level:"+this.Level;
	}
	
	public boolean equals(Administrateurs administrateur)
	{
		if(this.toString().equals(administrateur.toString()))
			{return true;}
		else
		    {return false;}
	}
	
	public boolean isSuper()
	{
		return Level == 1;
	}
	public boolean isBanned()
	{
		return Level==DBAccessControl.BannedAdmin;
	}
	public boolean isArchive()
	{
		return Level == DBAccessControl.ArchiveAdmin;
	}
	public boolean isActive()
	{
		return Level>1;
	}
	public boolean isEmpty()
	{
		return ID==DBAccessControl.Empty;
	}
	public boolean isInactive()
	{
		return Level==DBAccessControl.InactifAdmin;
	}
	
	public int save()
	{
		return AdministrateursDAO.save(this);
	}
	
	
	public int delete()
	{
		return AdministrateursDAO.delete(this);
	}
	public static String getTableName()
	{return "administrateurs";}
	
	public static String getFields()
	{
		return "ID,Username,Password,Email,Nom,Prenom,Adresse,DateInscription,Level";
	}
	
	public Administrateurs(int id, String username, String password,
			String email, String nom, String prenom, String adresse,
			Date dateinscription, int level) {
		this.ID = id;
		this.Username = username;
		this.Password = password;
		this.Email = email;
		this.Nom = nom;
		this.Prenom = prenom;
		this.Adresse = adresse;
		this.DateInscription = dateinscription;
		this.Level = level;

	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public Date getDateInscription() {
		return DateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		DateInscription = dateInscription;
	}

}
