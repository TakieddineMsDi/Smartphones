package Isamm.ing.java.Smartphones.Models;

import Isamm.ing.java.Smartphones.doAll.Do;

public class Countries {
private int ID;

public Countries()
{
	
}
@Override
public String toString() {
	return "ID:"+this.ID
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
	return Do.save(Countries.getTableName(), "this", this);
}

public int delete()
{
	return Do.delete(Countries.getTableName(), "this", this, "", "", "", "", "", "", "");
}

public static String getTableName()
{return "countries";}
public static String getFields()
{
	return "ID,Libelle,Statut";
}
public Countries(int id,String libelle,int statut)
{
	this.ID = id;
	this.Libelle = libelle;
	this.Statut = statut;
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
public int getStatut() {
	return Statut;
}
public void setStatut(int statut) {
	Statut = statut;
}
private String Libelle;
private int Statut;
}
