package Isamm.ing.java.Smartphones.Models;

import java.io.Serializable;

import Isamm.ing.java.Smartphones.DAO.TypecardDAO;

public class Typecard implements Serializable {
	private static final long serialVersionUID = 5585183787177403141L;
	public static String getFields() {
		return "ID,Libelle,Description,Statut";
	}
	public static String getTableName() {
		return "typecard";
	}
	private String Description;
	private int ID;

	private String Libelle;

	private int Statut;

	public Typecard() {

	}

	public Typecard(int id, String libelle, String description, int statut) {
		this.setID(id);
		this.setLibelle(libelle);
		this.setDescription(description);
		this.setStatut(statut);
	}

	public int delete() {
		return TypecardDAO.delete(this);
	}

	public boolean equals(Typecard typecard) {
		if (this.toString().equals(typecard.toString())) {
			return true;
		} else {
			return false;
		}
	}

	public String getDescription() {
		return Description;
	}

	public int getID() {
		return ID;
	}

	public String getLibelle() {
		return Libelle;
	}

	public int getStatut() {
		return Statut;
	}

	public boolean isActive() {
		if (this.Statut != 0) {
			return true;
		} else {
			return false;
		}
	}

	public int save() {
		return TypecardDAO.save(this);
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setLibelle(String libelle) {
		Libelle = libelle;
	}

	public void setStatut(int statut) {
		Statut = statut;
	}

	@Override
	public String toString() {
		return "ID:" + this.ID + ",Libelle:" + this.Libelle + ",Description:"
				+ this.Description + ",Statut:" + this.Statut;
	}

}
