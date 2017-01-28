package Isamm.ing.java.Smartphones.Models;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Classes.Encription;
import Isamm.ing.java.Smartphones.doAll.Do;

public class Clients  implements Serializable {
	private static final long serialVersionUID = -7775622499994022083L;
	private int ID;
	private String Username, Password, Email, Nom, Prenom, Adresse;
	private int Ville;
	private int TypeCard;
	private String NumeroCard;
	private int NewsLettres;
	private Date DateInscription;
	private int AllowedToSubmit, Actif;
	private String Gender;
	private int Country;
	private int Age;
	private List<NewsLetters> NewNewsLetters;
	
	public List<NewsLetters> getOldNewsLetters()
	{
		List<NewsLetters> list = ((List<NewsLetters>)(Do.get(NewsLetters.getTableName(), "IDClient", "=", ""+ID, "", "", "", "", "List")));
		if(list==null){return null;}
		return list;
	}

	@Override
	public String toString() {
		return "ID:"+this.ID
				+ ",Username:"+this.Username
				+ ",Password:"+this.Password
				+ ",Email:"+this.Email
				+ ",Nom:"+this.Nom
				+ ",Prenom:"+this.Prenom
				+ ",Ville:"+this.Ville
				+ ",Adresse:"+this.Adresse
				+ ",TypeCard:"+this.TypeCard
				+ ",NumeroCard:"+this.NumeroCard
				+ ",NewsLettres:"+this.NewsLettres
				+ ",DateInscription:"+this.DateInscription
				+ ",AllowedToSubmit:"+this.AllowedToSubmit
				+ ",Actif:"+this.Actif
				+ ",Country:"+this.Country
				+ ",Age"+this.Age
				+ ",Gender:"+this.Gender;
	}
	
	public boolean isActif()
	{
		return Actif == DBAccessControl.Actif;
	}
	public boolean isInactif()
	{
		return Actif == DBAccessControl.Inactif;
	}
	public boolean isArchive()
	{
		return Actif == DBAccessControl.Archive;
	}
	public boolean isAllowedToSubmit()
	{
		return AllowedToSubmit == 1;
	}
	
	public int deletecommandes()
	{
		return Do.delete(Commandes.getTableName(), "query", "", "IDClient", "=", ""+ID, "", "", "", "");
	}
	
	public int deletenewsletters()
	{
		return Do.delete(NewsLetters.getTableName(), "query", "", "IDClient", "=", ""+ID, "", "", "", "");
	}
	
	public int deleteproduits()
	{
		return Do.delete(Produits.getTableName(), "query", "", "IDOwner&Owner", "=&=", "2&"+ID, "", "", "", "");
	}
	public Countries getRCountry()
	{
		return (Countries) Do.get(Countries.getTableName(), "ID", "=", ""+Country, "", "", "", "", "Unique");
	}
	public boolean isBanned()
	{
		return Actif == DBAccessControl.Banned;
	}
	public boolean equals(Clients client)
	{
		if(this.toString().equals(client.toString())&&Do.sameNewsLetters(client))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int save()
	{
		return Do.save(Clients.getTableName(), "this", this);
	}
	public boolean isEmpty()
	{
		return ID == DBAccessControl.Empty;
	}
	public int delete()
	{
		String res = "";
        res+=deletecommandes();
        res+=deletenewsletters();
        res+=deleteproduits();
        res+=Do.delete(Clients.getTableName(), "this", this, "", "", "", "", "", "", "");
        if(res.contains(""+DBAccessControl.Error))
        {
        	return DBAccessControl.Error;
        }
        else{
        	return DBAccessControl.Cool;
        }
	}
	
	public static String getTableName()
	{return "clients";}
	public static String getFields()
	{
		return "ID,Username,Password,Email,Nom,Prenom,Ville,Adresse,TypeCard,NumeroCard,NewsLettres,DateInscription,AllowedToSubmit,Actif,Country,Age,Gender";
	}
	
	public Clients() {

	}

	public Clients(int id, String username, String password, String email,
			String nom, String prenom, int ville, String adresse,
			int typecard, String numerocard, int newslettres,
			Date dateinscription, int allowedtosubmit, int actif,int country,int age,String gender) {
		this.ID = id;
		this.Username = username;
		this.Password = password;
		this.Email = email;
		this.Nom = nom;
		this.Prenom = prenom;
		this.Ville = ville;
		this.Adresse = adresse;
		this.TypeCard = typecard;
		this.NumeroCard = numerocard;
		this.NewsLettres = newslettres;
		this.DateInscription = dateinscription;
		this.AllowedToSubmit = allowedtosubmit;
		this.Actif = actif;
		this.Country = country;
		this.Gender = gender;
		this.Age = age;

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
	
	public String getEncriptedPass()
	{
		return Encription.encrypt(Password, Encription.getKey("password"));
	}
	
	public String getDecriptedPass()
	{
		return Encription.decrypt(Password, Encription.getKey("password"));
	}
	
	public boolean hasProducts()
	{
		int count = Do.Count(Produits.getTableName(), "Owner&IDOwner", "=&=", "2&"+ID, "AND", "", "", "");
		return ((count != DBAccessControl.Empty)&&(count!=0));
	}
	
	public List<Produits> getProduits()
	{
		return (List<Produits>)Do.get(Produits.getTableName(), "Owner&IDOwner&Statut", "=&=&<>", "2&"+ID+"&"+DBAccessControl.Archive, "", "", "", "", "List");
	}
	
	public int CountProducts()
	{
		if(hasProducts())
		{
			return Do.Count(Produits.getTableName(), "Owner&IDOwner&Statut", "=&=&<>", "2&"+ID+"&"+DBAccessControl.Archive, "AND&AND", "", "", "");
		}
		else{
			return DBAccessControl.Empty;
		}
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

	public int getVille() {
		return Ville;
	}

	public void setVille(int ville) {
		Ville = ville;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public int getTypeCard() {
		return TypeCard;
	}

	public void setTypeCard(int typeCard) {
		TypeCard = typeCard;
	}

	public String getNumeroCard() {
		return NumeroCard;
	}

	public void setNumeroCard(String numeroCard) {
		NumeroCard = numeroCard;
	}

	public int getActif() {
		return Actif;
	}

	public void setActif(int actif) {
		Actif = actif;
	}

	public int getNewsLettres() {
		return NewsLettres;
	}

	public void setNewsLettres(int newsLettres) {
		NewsLettres = newsLettres;
	}

	public Date getDateInscription() {
		return DateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		DateInscription = dateInscription;
	}

	public int getAllowedToSubmit() {
		return AllowedToSubmit;
	}

	public void setAllowedToSubmit(int allowedToSubmit) {
		AllowedToSubmit = allowedToSubmit;
	}

	public List<NewsLetters> getNewNewsLetters() {
		return NewNewsLetters;
	}

	public void setNewNewsLetters(List<NewsLetters> newNewsLetters) {
		NewNewsLetters = newNewsLetters;
	}

	public int getCountry() {
		return Country;
	}

	public void setCountry(int country) {
		Country = country;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

}
