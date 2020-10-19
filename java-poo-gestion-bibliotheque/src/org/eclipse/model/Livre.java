package org.eclipse.model;

import java.util.ArrayList;

public class Livre extends Oeuvre {

	public Livre(int isbn, String titre, String auteur, ArrayList<Categorie> categories) {
		super(isbn, titre, auteur, categories);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		return true;
	}

	public String toString() {
		return "Livre [isbn=" + this.getIsbn() + ", titre=" + this.getTitre() + ", auteur=" + this.getAuteur()
				+ ", categories=" + this.getCategories() + ", reserve=" + this.getReserve() + ", dateDeRetour="
				+ this.getDateDeRetour() + ", dateDEmprunt=" + this.getDateDEmprunt() + ", idEmprunteur="
				+ this.getAdherant() + "]";
	}
	
}