package Isamm.ing.java.Smartphones.Models;

import java.io.Serializable;
import java.util.List;

import Isamm.ing.java.Smartphones.DAO.MarquesDAO;
import Isamm.ing.java.Smartphones.doAll.Do;

public class Marques implements Serializable {
	private static final long serialVersionUID = 3210704801933259800L;
	private int ID;
	private String Marque,Description,Logo;
	private int Statut;

	@Override
	public String toString() {
		return "ID:"+this.ID
				+",Marque:"+this.Marque
				+",Description:"+this.Description
				+",Logo:"+this.Logo
				+",Statut:"+this.Statut;
	}
	
	public boolean imISubscribed(List<Marques> list)
	{
		return Do.isSubscribed(this.ID, list);
	}
	
	public boolean equals(Marques marque)
	{
		if(this.toString().equals(marque.toString()))
			{return true;}
		else
		    {return false;}
	}
	
	public boolean isActive()
	{
		if(this.Statut!=0)
		{return true;}
		else
		{return false;}
	}
	
	public int delete()
	{
		return MarquesDAO.delete(this);
	}
	
	public int save()
	{
		return MarquesDAO.save(this);
	}
	
	public static String getTableName()
	{return "marques";}
	public static String getFields()
	{
		return "ID,Marque,Description,Logo,Statut";
	}
	public Marques() {

	}

	public Marques(int id, String marque,String description,String logo,int statut) {
		this.setID(id);
		this.setMarque(marque);
		this.Description = description;
		this.Logo = logo;
		this.setStatut(statut);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMarque() {
		return Marque;
	}

	public void setMarque(String marque) {
		Marque = marque;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getLogo() {
		return Logo;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public int getStatut() {
		return Statut;
	}

	public void setStatut(int statut) {
		Statut = statut;
	}
}
