package org.eclipse.model;

import java.util.ArrayList;
import java.util.Date;

public class Mediatheque {
	private String adresse;
	private String nom;
	private String directeur;
	private ArrayList<Oeuvre> listeDOeuvres;
	private ArrayList<Adherant> listeDAdherants;
	private int limiteLivresReserves;
	private int limiteDVDsReserves;
	private int nombreJoursReservation;

	public Mediatheque(String nom, String adresse, String directeur) {
		this(nom, adresse, directeur, new ArrayList<Oeuvre>(), new ArrayList<Adherant>(), 5, 2, 14);
	}

	public Mediatheque(String nom, String adresse, String directeur, ArrayList<Oeuvre> listeDOeuvres, ArrayList<Adherant> listeDAdherants) {
		this(nom, adresse, directeur, listeDOeuvres, listeDAdherants, 5, 2, 14);
	}
	
	public Mediatheque(String nom, String adresse, String directeur, ArrayList<Oeuvre> listeDOeuvres, ArrayList<Adherant> listeDAdherants, int limiteLivresReserves, int limiteDVDsReserves, int nombreJoursReservation) {
		this.adresse = adresse;
		this.nom = nom;
		this.directeur = directeur;
		this.listeDOeuvres = listeDOeuvres;
		this.listeDAdherants = listeDAdherants;
		this.limiteLivresReserves = limiteLivresReserves;
		this.limiteDVDsReserves = limiteDVDsReserves;
		this.nombreJoursReservation = nombreJoursReservation;
	}
	
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDirecteur() {
		return this.directeur;
	}

	public void setDirecteur(String directeur) {
		this.directeur = directeur;
	}

	public ArrayList<Oeuvre> getListeDOeuvres() {
		return this.listeDOeuvres;
	}

	public void setListeDOeuvres(ArrayList<Oeuvre> listeDOeuvres) {
		this.listeDOeuvres = listeDOeuvres;
	}

	public ArrayList<Adherant> getListeDAdherants() {
		return this.listeDAdherants;
	}

	public void setListeDAdherants(ArrayList<Adherant> listeDAdherants) {
		this.listeDAdherants = listeDAdherants;
	}
	
	public int getLimiteLivresReserves() {
		return limiteLivresReserves;
	}

	public void setLimiteLivresReserves(int limiteLivresReserves) {
		this.limiteLivresReserves = limiteLivresReserves;
	}

	public int getLimiteDVDsReserves() {
		return limiteDVDsReserves;
	}

	public void setLimiteDVDsReserves(int limiteDVDsReserves) {
		this.limiteDVDsReserves = limiteDVDsReserves;
	}

	public int getNombreJoursReservation() {
		return nombreJoursReservation;
	}

	public void setNombreJoursReservation(int nombreJoursReservation) {
		this.nombreJoursReservation = nombreJoursReservation;
	}

	public boolean ajouterLivre(Livre argLivre) {
		if (!listeDOeuvres.contains(argLivre)) {
			return listeDOeuvres.add(argLivre);
		}
		return false;
	}
	
	public boolean supprimerLivre(Livre argLivre) {
		if (listeDOeuvres.contains(argLivre)) {
			if (argLivre.getAdherant() != null) {
				if(argLivre.getAdherant().getOeuvresEmpruntes().contains(argLivre)) {
					argLivre.getAdherant().getOeuvresEmpruntes().remove(argLivre);
				}
			}
			return listeDOeuvres.remove(argLivre);
		}
		return false;
	}

	public boolean ajouterDVD(DVD argDVD) {
		if (!listeDOeuvres.contains(argDVD)) {
			return listeDOeuvres.add(argDVD);
		}
		return false;
	}
	
	public boolean supprimerDVD(DVD argDVD) {
		if (listeDOeuvres.contains(argDVD)) {
			if (argDVD.getAdherant() != null) {
				if(argDVD.getAdherant().getOeuvresEmpruntes().contains(argDVD)) {
					argDVD.getAdherant().getOeuvresEmpruntes().remove(argDVD);
				}
			}
			return listeDOeuvres.remove(argDVD);
		}
		return false;
	}
	
	public boolean ajouterAdherant(Adherant argAdherant) {
		if (!listeDAdherants.contains(argAdherant)) {
			return listeDAdherants.add(argAdherant);
		}
		return false;
	}

	public boolean supprimerAdherant(Adherant argAdherant) {
		if(listeDAdherants.contains(argAdherant)) {
			for (Oeuvre oeuvre : argAdherant.getOeuvresEmpruntes()) {
			    if (listeDOeuvres.contains(oeuvre)) {
			    	oeuvre.setReserve(false);
			    	oeuvre.setAdherant(null);
			    	oeuvre.setDateDEmprunt(null);
			    	oeuvre.setDateDeRetour(null);
			    }
			}
			return listeDAdherants.remove(argAdherant);
		}
		return false;
	}

	public ArrayList<Livre> listeLivres() {
		ArrayList<Livre> livres = new ArrayList<Livre>();
		for (Oeuvre oeuvre : listeDOeuvres) {
			if (oeuvre instanceof Livre) {
				Livre livre = (Livre)oeuvre;
				livres.add(livre);
			}
		}
		return livres;
	}
	
	public ArrayList<DVD> listeDVDs() {
		ArrayList<DVD> dVDs = new ArrayList<DVD>();
		for (Oeuvre oeuvre : listeDOeuvres) {
			if (oeuvre instanceof DVD) {
				DVD dVD = (DVD)oeuvre;
				dVDs.add(dVD);
			}
		}
		return dVDs;
	}

	public boolean reserverLivre(Adherant argAdherant, Livre argLivre) {
		if (listeDAdherants.contains(argAdherant) && listeDOeuvres.contains(argLivre)) {
			if (!argLivre.getReserve() && argAdherant.nombreLivresReserves() <= limiteLivresReserves) {
				argLivre.setReserve(true);
				argLivre.setAdherant(argAdherant);
				argLivre.setDateDEmprunt(new Date(System.currentTimeMillis()));
				argLivre.setDateDeRetour(new Date(System.currentTimeMillis() + 86400000 * nombreJoursReservation));
				return argAdherant.reserverLivre(argLivre);
			}
		}
		return false;
	}
	
	public boolean restituerLivre(Adherant argAdherant, Livre argLivre) {
		if (listeDAdherants.contains(argAdherant) && listeDOeuvres.contains(argLivre)) {
			if (argAdherant.getOeuvresEmpruntes().contains(argLivre)) {
				argLivre.setReserve(false);
				argLivre.setAdherant(null);
				argLivre.setDateDEmprunt(null);
		    	argLivre.setDateDeRetour(null);
			}
		}
		return false;
	}
	
	public boolean reserverDVD(Adherant argAdherant, DVD argDVD) {
		if (listeDAdherants.contains(argAdherant) && listeDOeuvres.contains(argDVD)) {
			if (!argDVD.getReserve() && argAdherant.nombreDVDsReserves() <= limiteDVDsReserves) {
				argDVD.setReserve(true);
				argDVD.setAdherant(argAdherant);
				argDVD.setDateDEmprunt(new Date(System.currentTimeMillis()));
				argDVD.setDateDeRetour(new Date(System.currentTimeMillis() + 86400000 * nombreJoursReservation));
				return argAdherant.reserverDVD(argDVD);
			}
		}
		return false;
	}
	
	public boolean restituerDVD(Adherant argAdherant, DVD argDVD) {
		if (listeDAdherants.contains(argAdherant) && listeDOeuvres.contains(argDVD)) {
			if (argAdherant.getOeuvresEmpruntes().contains(argDVD)) {
				argDVD.setReserve(false);
				argDVD.setAdherant(null);
				argDVD.setDateDEmprunt(null);
		    	argDVD.setDateDeRetour(null);
			}
		}
		return false;
	}
	
	public ArrayList<Livre> listeLivresDisponibles() {
		ArrayList<Livre> livres = new ArrayList<Livre>();
		for (Oeuvre oeuvre : listeDOeuvres) {
			if (oeuvre instanceof Livre && !oeuvre.getReserve()) {
				livres.add((Livre)oeuvre);
			}
		}
		return livres;
	}
	
	public ArrayList<DVD> listeDVDsDisponibles() {
		ArrayList<DVD> dVDs = new ArrayList<DVD>();
		for (Oeuvre oeuvre : listeDOeuvres) {
			if (oeuvre instanceof DVD && !oeuvre.getReserve()) {
				dVDs.add((DVD)oeuvre);
			}
		}
		return dVDs;
	}
	
	public ArrayList<Livre> listeLivresIndisponibles() {
		ArrayList<Livre> livres = new ArrayList<Livre>();
		for (Oeuvre oeuvre : listeDOeuvres) {
			if (oeuvre instanceof Livre && oeuvre.getReserve()) {
				livres.add((Livre)oeuvre);
			}
		}
		return livres;
	}
	
	public ArrayList<DVD> listeDVDsIndisponibles() {
		ArrayList<DVD> dVDs = new ArrayList<DVD>();
		for (Oeuvre oeuvre : listeDOeuvres) {
			if (oeuvre instanceof DVD && oeuvre.getReserve()) {
				dVDs.add((DVD)oeuvre);
			}
		}
		return dVDs;
	}

//	public ArrayList<Livre> listeLivresTriesParTitre() {
//	
//	}
//	
//	public ArrayList<Livre> listeLivresTriesParAuteur() {
//		
//	}
//	
//	public ArrayList<Livre> listeLivresTriesParCategories() {
//		
//	}
//	
//	public ArrayList<Livre> RechercheLivreParTitre() {
//		
//	}
//	
//	public ArrayList<Livre> RechercheLivreParAuteur() {
//		
//	}
//	
//	public ArrayList<Livre> RechercheLivreParCategories() {
//		
//	}
//	
//	public ArrayList<DVD> listeDVDsTriesParTitre() {
//		
//	}
//	
//	public ArrayList<DVD> listeDVDsTriesParAuteur() {
//		
//	}
//	
//	public ArrayList<DVD> listeDVDsTriesParCategories() {
//		
//	}
//	
//	public ArrayList<DVD> RechercheDVDParTitre() {
//		
//	}
//	
//	public ArrayList<DVD> RechercheDVDParAuteur() {
//		
//	}
//	
//	public ArrayList<DVD> RechercheDVDParCategories() {
//		
//	}
//	
//	public static ArrayList<Livre> triLivreParDisponibilité(ArrayList<Livre> listeLivre) {
//		
//	}
//	
//	public static ArrayList<Livre> triLivreParIndisponibilité(ArrayList<Livre> listeLivre) {
//		
//	}
//	
//	public static ArrayList<Livre> triLivreParTitre(ArrayList<Livre> listeLivre, String argTitre) {
//		
//	}
//	
//	public static ArrayList<Livre> triLivreParAuteur(ArrayList<Livre> listeLivre, String argAuteur) {
//		
//	}
//	
//	public static ArrayList<Livre> triLivreParCategories(ArrayList<Livre> listeLivre, ArrayList<Categorie> argCategories) {
//		
//	}
//	
//	public static ArrayList<DVD> triDVDParDisponibilité(ArrayList<DVD> listeDVD) {
//		
//	}
//	
//	public static ArrayList<DVD> triDVDParIndisponibilité(ArrayList<DVD> listeDVD) {
//		
//	}
//	
//	public static ArrayList<DVD> triDVDParTitre(ArrayList<DVD> listeDVD, String argTitre) {
//		
//	}
//	
//	public static ArrayList<DVD> triDVDParAuteur(ArrayList<DVD> listeDVD, String argAuteur) {
//		
//	}
//	
//	public static ArrayList<DVD> triDVDParCategories(ArrayList<DVD> listeDVD, ArrayList<Categorie> argCategories) {
//		
//	}
	
	public String toString() {
		return "Mediatheque [adresse=" + this.adresse + ", nom=" + this.nom + ", directeur=" + this.directeur + ", listeDOeuvres="
				+ this.listeDOeuvres + ", listeDAdherants=" + this.listeDAdherants + ", limiteLivresReserves=" + this.limiteLivresReserves + ", limiteDVDsReserves=" + this.limiteDVDsReserves + ", nombreJoursReservation=" + this.nombreJoursReservation +"]";
	}

}
