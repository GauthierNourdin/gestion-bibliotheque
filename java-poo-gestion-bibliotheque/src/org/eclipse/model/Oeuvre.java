package org.eclipse.model;

import java.util.ArrayList;
import java.util.Date;

public abstract class Oeuvre {

	private int isbn;
	private String titre;
	private String auteur;
	private ArrayList<Categorie> categories;
	private Boolean reservee;
	private Date dateDeRetour;
	private Date dateDEmprunt;
	private Adherant adherant;
	
	public Oeuvre(int isbn, String titre, String auteur, ArrayList<Categorie> categories) {
		super();
		this.isbn = isbn;
		this.titre = titre;
		this.auteur = auteur;
		this.categories = categories;
		this.reservee = false;
		this.dateDeRetour = null;
		this.dateDEmprunt = null;
		this.adherant = null;
	}

	public int getIsbn() {
		return this.isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return this.auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public ArrayList<Categorie> getCategories() {
		return this.categories;
	}

	public void setCategories(ArrayList<Categorie> categories) {
		this.categories = categories;
	}

	public Boolean getReserve() {
		return this.reservee;
	}

	public void setReserve(Boolean reserve) {
		this.reservee = reserve;
	}

	public Date getDateDeRetour() {
		return this.dateDeRetour;
	}

	public void setDateDeRetour(Date dateDeRetour) {
		this.dateDeRetour = dateDeRetour;
	}

	public Date getDateDEmprunt() {
		return this.dateDEmprunt;
	}

	public void setDateDEmprunt(Date dateDEmprunt) {
		this.dateDEmprunt = dateDEmprunt;
	}

	public Adherant getAdherant() {
		return this.adherant;
	}

	public void setAdherant(Adherant adherant) {
		this.adherant = adherant;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Oeuvre other = (Oeuvre) obj;
		if (auteur == null) {
			if (other.auteur != null)
				return false;
		} else if (!auteur.equals(other.auteur))
			return false;
		if (this.categories == null) {
			if (other.categories != null)
				return false;
		} else if (!this.categories.equals(other.categories))
			return false;
		if (this.isbn != other.isbn)
			return false;
		if (this.titre == null) {
			if (other.titre != null)
				return false;
		} else if (!this.titre.equalsIgnoreCase(other.titre))
			return false;
		return true;
	}
	
	public String toString() {
		return "Oeuvre [isbn=" + this.isbn + ", titre=" + this.titre + ", auteur=" + this.auteur + ", categories=" + this.categories
				+ ", reserve=" + this.reservee + ", dateDeRetour=" + this.dateDeRetour + ", dateDEmprunt=" + this.dateDEmprunt
				+ ", adherant.nom=" + this.adherant.getNom() + "]";
	}
	
}
