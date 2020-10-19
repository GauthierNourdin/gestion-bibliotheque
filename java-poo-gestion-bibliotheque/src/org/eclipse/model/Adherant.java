package org.eclipse.model;

import java.util.ArrayList;

public class Adherant {
	private int id;
	private String nom;
	private String adresse;
	private Mediatheque mediatheque;
	private ArrayList<Oeuvre> oeuvresReservees = new ArrayList<Oeuvre>();

	public Adherant(int id, String nom, String adresseMail, Mediatheque mediatheque) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresseMail;
		this.mediatheque = mediatheque;
		this.oeuvresReservees = new ArrayList<Oeuvre>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresseMail() {
		return this.adresse;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresse = adresseMail;
	}

	public Mediatheque getMediatheque() {
		return this.mediatheque;
	}

	public void setMediatheque(Mediatheque mediatheque) {
		this.mediatheque = mediatheque;
	}

	public ArrayList<Oeuvre> getOeuvresEmpruntes() {
		return this.oeuvresReservees;
	}

	public void setOeuvresEmpruntes(ArrayList<Oeuvre> oeuvresReservees) {
		this.oeuvresReservees = oeuvresReservees;
	}

	public boolean reserverLivre(Livre argLivre) {
		if (!this.oeuvresReservees.contains(argLivre)) {
			return this.oeuvresReservees.add(argLivre);
		}
		return false;
	}

	public boolean restituerLivre(Livre argLivre) {
		if (this.oeuvresReservees.contains(argLivre)) {
			return this.oeuvresReservees.remove(argLivre);
		}
		return false;
	}

	public boolean reserverDVD (DVD argDVD) {
		if (!this.oeuvresReservees.contains(argDVD)) {
			return this.oeuvresReservees.add(argDVD);
		}
		return false;
	}
	
	public boolean restituerDVD(DVD argDVD) {
		if (this.oeuvresReservees.contains(argDVD)) {
			return this.oeuvresReservees.remove(argDVD);
		}
		return false;
	}
	
	public ArrayList<Livre> listeLivresReserves() {
		ArrayList<Livre> liste = new ArrayList<Livre>();
		for (Oeuvre oeuvre : this.oeuvresReservees) {
			if (oeuvre instanceof Livre) {
				liste.add((Livre)oeuvre);
			}
		}
		return liste;
	}
	
	public ArrayList<DVD> listeDVDsReserves() {
		ArrayList<DVD> liste = new ArrayList<DVD>();
		for (Oeuvre oeuvre : this.oeuvresReservees) {
			if (oeuvre instanceof DVD) {
				liste.add((DVD)oeuvre);
			}			
		}
		return liste;
	}
	
	public int nombreLivresReserves() {
		int nombre = 0;
		for (Oeuvre oeuvre : this.oeuvresReservees) {
			if (oeuvre instanceof Livre) {
				nombre++;
			}
		}
		return nombre;
	}
	
	public int nombreDVDsReserves() {
		int nombre = 0;
		for (Oeuvre oeuvre : this.oeuvresReservees) {
			if (oeuvre instanceof DVD) {
				nombre++;
			}
		}
		return nombre;
	}

	public String toString() {
		int i;
		String listeOeuvresReservees = "[";
		for (i = 0 ; i < this.oeuvresReservees.size() - 1 ; i++) {
			listeOeuvresReservees += this.oeuvresReservees.get(i).getTitre() + ", ";
		}
		listeOeuvresReservees += " ]";
		return "Adherant [id=" + this.id + ", nom=" + this.nom + ", adresseMail=" + this.adresse + ", mediatheque=" + this.mediatheque
				+ ", oeuvresEmpruntes.titre=" + listeOeuvresReservees + "]";
	}

}