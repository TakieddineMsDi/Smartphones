package Isamm.ing.java.Smartphones.Models;

import Isamm.ing.java.Smartphones.doAll.Do;

public class Ville {
private int ID;

public Ville()
{
	
}

@Override
public String toString() {
	return "ID:"+this.ID
			+ ",IDCountry:"+this.IDCountry
			+ ",Libelle:"+this.Libelle
			+ ",Statut:"+this.Statut;
}

public boolean equals(Clients client)
{
	if(this.toString().equals(client.toString()))
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
	return Do.save(Ville.getTableName(), "this", this);
}

public int delete()
{
	return Do.delete(Ville.getTableName(), "this", this, "", "", "", "", "", "", "");
}

public static String getTableName()
{return "ville";}
public static String getFields()
{
	return "ID,IDCountry,Libelle,Statut";
}
public Ville(int id,int idcountry,String libelle,int statut)
{
	this.ID = id;
	this.IDCountry = idcountry;
	this.Libelle = libelle;
	this.Statut = statut;
}
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public int getIDCountry() {
	return IDCountry;
}
public void setIDCountry(int iDCountry) {
	IDCountry = iDCountry;
}
public String getLibelle() {
	return Libelle;
}
public void setLibelle(String libelle) {
	Libelle = libelle;
}
public int getStatut() {
	return Statut;
}
public void setStatut(int statut) {
	Statut = statut;
}
private int IDCountry;
private String Libelle;
private int Statut;
}
