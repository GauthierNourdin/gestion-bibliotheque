package org.eclipse.model;

import java.util.ArrayList;
import java.util.Collections;
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
		return this.limiteLivresReserves;
	}

	public void setLimiteLivresReserves(int limiteLivresReserves) {
		this.limiteLivresReserves = limiteLivresReserves;
	}

	public int getLimiteDVDsReserves() {
		return this.limiteDVDsReserves;
	}

	public void setLimiteDVDsReserves(int limiteDVDsReserves) {
		this.limiteDVDsReserves = limiteDVDsReserves;
	}

	public int getNombreJoursReservation() {
		return this.nombreJoursReservation;
	}

	public void setNombreJoursReservation(int nombreJoursReservation) {
		this.nombreJoursReservation = nombreJoursReservation;
	}

	public boolean ajouterLivre(Livre argLivre) {
		if (!this.listeDOeuvres.contains(argLivre)) {
			return this.listeDOeuvres.add(argLivre);
		}
		return false;
	}
	
	public boolean supprimerLivre(Livre argLivre) {
		if (this.listeDOeuvres.contains(argLivre)) {
			if (argLivre.getAdherant() != null) {
				if(argLivre.getAdherant().getOeuvresEmpruntes().contains(argLivre)) {
					argLivre.getAdherant().getOeuvresEmpruntes().remove(argLivre);
				}
			}
			return this.listeDOeuvres.remove(argLivre);
		}
		return false;
	}

	public boolean ajouterDVD(DVD argDVD) {
		if (!this.listeDOeuvres.contains(argDVD)) {
			return this.listeDOeuvres.add(argDVD);
		}
		return false;
	}
	
	public boolean supprimerDVD(DVD argDVD) {
		if (this.listeDOeuvres.contains(argDVD)) {
			if (argDVD.getAdherant() != null) {
				if(argDVD.getAdherant().getOeuvresEmpruntes().contains(argDVD)) {
					argDVD.getAdherant().getOeuvresEmpruntes().remove(argDVD);
				}
			}
			return this.listeDOeuvres.remove(argDVD);
		}
		return false;
	}
	
	public boolean ajouterAdherant(Adherant argAdherant) {
		if (!this.listeDAdherants.contains(argAdherant)) {
			return this.listeDAdherants.add(argAdherant);
		}
		return false;
	}

	public boolean supprimerAdherant(Adherant argAdherant) {
		if(this.listeDAdherants.contains(argAdherant)) {
			for (Oeuvre oeuvre : argAdherant.getOeuvresEmpruntes()) {
			    if (this.listeDOeuvres.contains(oeuvre)) {
			    	oeuvre.setReserve(false);
			    	oeuvre.setAdherant(null);
			    	oeuvre.setDateDEmprunt(null);
			    	oeuvre.setDateDeRetour(null);
			    }
			}
			return this.listeDAdherants.remove(argAdherant);
		}
		return false;
	}

	public ArrayList<Livre> listeLivres() {
		ArrayList<Livre> livres = new ArrayList<Livre>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof Livre) {
				Livre livre = (Livre)oeuvre;
				livres.add(livre);
			}
		}
		return livres;
	}
	
	public ArrayList<DVD> listeDVDs() {
		ArrayList<DVD> dVDs = new ArrayList<DVD>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof DVD) {
				DVD dVD = (DVD)oeuvre;
				dVDs.add(dVD);
			}
		}
		return dVDs;
	}

	public boolean reserverLivre(Adherant argAdherant, Livre argLivre) {
		if (this.listeDAdherants.contains(argAdherant) && this.listeDOeuvres.contains(argLivre)) {
			if (!argLivre.getReserve() && argAdherant.nombreLivresReserves() <= this.limiteLivresReserves) {
				argLivre.setReserve(true);
				argLivre.setAdherant(argAdherant);
				argLivre.setDateDEmprunt(new Date(System.currentTimeMillis()));
				argLivre.setDateDeRetour(new Date(System.currentTimeMillis() + 86400000 * this.nombreJoursReservation));
				return argAdherant.reserverLivre(argLivre);
			}
		}
		return false;
	}
	
	public boolean restituerLivre(Adherant argAdherant, Livre argLivre) {
		if (this.listeDAdherants.contains(argAdherant) && this.listeDOeuvres.contains(argLivre)) {
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
		if (this.listeDAdherants.contains(argAdherant) && this.listeDOeuvres.contains(argDVD)) {
			if (!argDVD.getReserve() && argAdherant.nombreDVDsReserves() <= this.limiteDVDsReserves) {
				argDVD.setReserve(true);
				argDVD.setAdherant(argAdherant);
				argDVD.setDateDEmprunt(new Date(System.currentTimeMillis()));
				argDVD.setDateDeRetour(new Date(System.currentTimeMillis() + 86400000 * this.nombreJoursReservation));
				return argAdherant.reserverDVD(argDVD);
			}
		}
		return false;
	}
	
	public boolean restituerDVD(Adherant argAdherant, DVD argDVD) {
		if (this.listeDAdherants.contains(argAdherant) && this.listeDOeuvres.contains(argDVD)) {
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
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof Livre && !oeuvre.getReserve()) {
				livres.add((Livre)oeuvre);
			}
		}
		return livres;
	}
	
	public ArrayList<DVD> listeDVDsDisponibles() {
		ArrayList<DVD> dVDs = new ArrayList<DVD>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof DVD && !oeuvre.getReserve()) {
				dVDs.add((DVD)oeuvre);
			}
		}
		return dVDs;
	}
	
	public ArrayList<Livre> listeLivresIndisponibles() {
		ArrayList<Livre> livres = new ArrayList<Livre>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof Livre && oeuvre.getReserve()) {
				livres.add((Livre)oeuvre);
			}
		}
		return livres;
	}
	
	public ArrayList<DVD> listeDVDsIndisponibles() {
		ArrayList<DVD> dVDs = new ArrayList<DVD>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof DVD && oeuvre.getReserve()) {
				dVDs.add((DVD)oeuvre);
			}
		}
		return dVDs;
	}

	public ArrayList<Livre> listeLivresTriesParTitre() {
		ArrayList<Livre> listeLivres = new ArrayList<Livre>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof Livre) {
				listeLivres.add((Livre)oeuvre);
			}
		}
		Collections.sort(listeLivres, new ComparateurLivreParTitre());
		return listeLivres;
	}
	
	public ArrayList<Livre> listeLivresTriesParAuteur() {
		ArrayList<Livre> listeLivres = new ArrayList<Livre>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof Livre) {
				listeLivres.add((Livre)oeuvre);
			}
		}
		Collections.sort(listeLivres, new ComparateurLivreParAuteur());
		return listeLivres;
	}
	
	public ArrayList<Livre> listeLivresTriesParCategories() {
		ArrayList<Livre> listeLivres = new ArrayList<Livre>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof Livre) {
				listeLivres.add((Livre)oeuvre);
			}
		}
		Collections.sort(listeLivres, new ComparateurLivreParCategories());
		return listeLivres;
	}
	
	public ArrayList<DVD> listeDVDsTriesParTitre() {
		ArrayList<DVD> listeDVDs = new ArrayList<DVD>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof DVD) {
				listeDVDs.add((DVD)oeuvre);
			}
		}
		Collections.sort(listeDVDs, new ComparateurDVDParTitre());
		return listeDVDs;
	}
	
	public ArrayList<DVD> listeDVDsTriesParAuteur() {
		ArrayList<DVD> listeDVDs = new ArrayList<DVD>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof DVD) {
				listeDVDs.add((DVD)oeuvre);
			}
		}
		Collections.sort(listeDVDs, new ComparateurDVDParAuteur());
		return listeDVDs;
	}
	
	public ArrayList<DVD> listeDVDsTriesParCategories() {
		ArrayList<DVD> listeDVDs = new ArrayList<DVD>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof DVD) {
				listeDVDs.add((DVD)oeuvre);
			}
		}
		Collections.sort(listeDVDs, new ComparateurDVDParCategories());
		return listeDVDs;
	}
	
	public ArrayList<Livre> RechercheLivreParTitre(String argTitre) {
		ArrayList<Livre> listeLivres = new ArrayList<Livre>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof Livre && oeuvre.getTitre().equalsIgnoreCase(argTitre)) {
				listeLivres.add((Livre)oeuvre);
			}
		}
		return listeLivres;
    }

    public ArrayList<Livre> RechercheLivreParAuteur(String argAuteur) {
		ArrayList<Livre> listeLivres = new ArrayList<Livre>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof Livre && oeuvre.getAuteur().equalsIgnoreCase(argAuteur)) {
				listeLivres.add((Livre)oeuvre);
			}
		}
		return listeLivres;	
    }

    public ArrayList<Livre> RechercheLivreParCategories(ArrayList<Categorie> argCategories) {
		ArrayList<Livre> listeLivres = new ArrayList<Livre>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof Livre) {
				boolean flag = true;
				for (Categorie categorie : argCategories) {
					if (!oeuvre.getCategories().contains(categorie)) {
						flag = false;
					}
				}
				if (flag) {
					listeLivres.add((Livre)oeuvre);
				}
			}
		}
		return listeLivres;	
    }
	
	public ArrayList<DVD> RechercheDVDParTitre(String argTitre) {
		ArrayList<DVD> listeDVDs = new ArrayList<DVD>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof DVD && oeuvre.getTitre().equalsIgnoreCase(argTitre)) {
				listeDVDs.add((DVD)oeuvre);
			}
		}
		return listeDVDs;		
	}
	
	public ArrayList<DVD> RechercheDVDParAuteur(String argAuteur) {
		ArrayList<DVD> listeDVDs = new ArrayList<DVD>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof DVD && oeuvre.getAuteur().equalsIgnoreCase(argAuteur)) {
				listeDVDs.add((DVD)oeuvre);
			}
		}
		return listeDVDs;			
	}
	
	public ArrayList<DVD> RechercheDVDParCategories(ArrayList<Categorie> argCategories) {
		ArrayList<DVD> listeDVDs = new ArrayList<DVD>();
		for (Oeuvre oeuvre : this.listeDOeuvres) {
			if (oeuvre instanceof DVD) {
				boolean flag = true;
				for (Categorie categorie : argCategories) {
					if (!oeuvre.getCategories().contains(categorie)) {
						flag = false;
					}
				}
				if (flag) {
					listeDVDs.add((DVD)oeuvre);
				}
			}
		}
		return listeDVDs;			
	}
	
	public static ArrayList<Livre> selectionLivresParDisponibilité(ArrayList<Livre> argListeLivres) {
		ArrayList<Livre> listeLivres = new ArrayList<Livre>();
		for (Livre livre : argListeLivres) {
			if (!livre.getReserve()) {
				listeLivres.add(livre);
			}
		}
		return listeLivres;
	}
	
	public static ArrayList<Livre> selectionLivresParIndisponibilité(ArrayList<Livre> argListeLivres) {
		ArrayList<Livre> listeLivres = new ArrayList<Livre>();
		for (Livre livre : argListeLivres) {
			if (!livre.getReserve()) {
				listeLivres.add(livre);
			}
		}
		return listeLivres;
	}
	
	public static ArrayList<Livre> selectionLivresParTitre(ArrayList<Livre> argListeLivres, String argTitre) {
		ArrayList<Livre> listeLivres = new ArrayList<Livre>();
		for (Livre livre : argListeLivres) {
			if (livre.getTitre().equalsIgnoreCase(argTitre)) {
				listeLivres.add(livre);
			}
		}
		return listeLivres;
	}
	
	public static ArrayList<Livre> selectionLivresParAuteur(ArrayList<Livre> argListeLivres, String argAuteur) {
		ArrayList<Livre> listeLivres = new ArrayList<Livre>();
		for (Livre livre : argListeLivres) {
			if (livre.getAuteur().equalsIgnoreCase(argAuteur)) {
				listeLivres.add(livre);
			}
		}
		return listeLivres;
	}
	
	public static ArrayList<Livre> selectionLivresParCategories(ArrayList<Livre> argListeLivres, ArrayList<Categorie> argCategories) {
		ArrayList<Livre> listeLivres = new ArrayList<Livre>();
		for (Livre livre : argListeLivres) {
			boolean flag = true;
			for (Categorie categorie : argCategories) {
				if (!livre.getCategories().contains(categorie)) {
					flag = false;
				}
			}
			if (flag) {
				listeLivres.add(livre);
			}
		}
		return listeLivres;
	}
	
	public static ArrayList<DVD> selectionDVDsParDisponibilité(ArrayList<DVD> argListeDVDs) {
		ArrayList<DVD> listeDVDs = new ArrayList<DVD>();
		for (DVD dVD : argListeDVDs) {
			if (!dVD.getReserve()) {
				listeDVDs.add(dVD);
			}
		}
		return listeDVDs;
	}
	
	public static ArrayList<DVD> selectionDVDsParIndisponibilité(ArrayList<DVD> argListeDVDs) {
		ArrayList<DVD> listeDVDs = new ArrayList<DVD>();
		for (DVD dVD : argListeDVDs) {
			if (!dVD.getReserve()) {
				listeDVDs.add(dVD);
			}
		}
		return listeDVDs;
	}
	
	public static ArrayList<DVD> selectionDVDsParTitre(ArrayList<DVD> argListeDVDs, String argTitre) {
		ArrayList<DVD> listeDVDs = new ArrayList<DVD>();
		for (DVD dVD : argListeDVDs) {
			if (dVD.getTitre().equalsIgnoreCase(argTitre)) {
				listeDVDs.add(dVD);
			}
		}
		return listeDVDs;
	}
	
	public static ArrayList<DVD> selectionDVDsParAuteur(ArrayList<DVD> argListeDVDs, String argAuteur) {
		ArrayList<DVD> listeDVDs = new ArrayList<DVD>();
		for (DVD dVD : argListeDVDs) {
			if (dVD.getAuteur().equalsIgnoreCase(argAuteur)) {
				listeDVDs.add(dVD);
			}
		}
		return listeDVDs;
	}
	
	public static ArrayList<DVD> selectionDVDsParCategories(ArrayList<DVD> argListeDVDs, ArrayList<Categorie> argCategories) {
		ArrayList<DVD> listeDVDs = new ArrayList<DVD>();
		for (DVD dVD : argListeDVDs) {
			boolean flag = true;
			for (Categorie categorie : argCategories) {
				if (!dVD.getCategories().contains(categorie)) {
					flag = false;
				}
			}
			if (flag) {
				listeDVDs.add(dVD);
			}
		}
		return listeDVDs;
	}
	
//	public static ArrayList<Livre> triLivresParTitre(ArrayList<Livre> argListeLivres) {
//		
//	}
//	
//	public static ArrayList<Livre> triLivresParAuteur(ArrayList<Livre> argListeLivres) {
//		
//	}
//	
//	public static ArrayList<Livre> triLivresParCategories(ArrayList<Livre> argListeLivres) {
//		
//	}	
//	
//	public static ArrayList<Livre> triDVDsParTitre(ArrayList<Livre> argListeLivres) {
//		
//	}
//	
//	public static ArrayList<Livre> triDVDsParAuteur(ArrayList<Livre> argListeLivres) {
//		
//	}
//	
//	public static ArrayList<Livre> triDVDsParCategories(ArrayList<Livre> argListeLivres) {
//		
//	}	
	
	public String toString() {
		return "Mediatheque [adresse=" + this.adresse + ", nom=" + this.nom + ", directeur=" + this.directeur + ", listeDOeuvres="
				+ this.listeDOeuvres + ", listeDAdherants=" + this.listeDAdherants + ", limiteLivresReserves=" + this.limiteLivresReserves + ", limiteDVDsReserves=" + this.limiteDVDsReserves + ", nombreJoursReservation=" + this.nombreJoursReservation +"]";
	}

}
