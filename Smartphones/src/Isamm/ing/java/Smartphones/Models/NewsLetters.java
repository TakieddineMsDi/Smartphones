package Isamm.ing.java.Smartphones.Models;

import java.io.Serializable;

import Isamm.ing.java.Smartphones.DAO.NewsLettersDAO;

public class NewsLetters implements Serializable {
	private static final long serialVersionUID = -5129681192774980698L;
	private int ID, IDClient, IDMarque;

	@Override
	public String toString() {
		return "ID:"+this.ID
				+",IDClient:"+this.IDClient
				+",IDMarque:"+this.IDMarque;
	}
	public static String getTableName()
	{return "newsletters";}
	public boolean equals(NewsLetters newslettres)
	{
		if(this.toString().equals(newslettres.toString()))
			{return true;}
		else
		    {return false;}
	}
	
	public static String getFields()
	{
		return "ID,IDClient,IDMarque";
	}
	
	public int delete()
	{
		return NewsLettersDAO.delete(this);
	}
	public NewsLetters() {

	}

	public NewsLetters(int id, int idclient, int idmarque) {
		this.ID = id;
		this.IDClient = idclient;
		this.IDMarque = idmarque;
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

	public int getIDMarque() {
		return IDMarque;
	}

	public void setIDMarque(int iDMarque) {
		IDMarque = iDMarque;
	}
}
