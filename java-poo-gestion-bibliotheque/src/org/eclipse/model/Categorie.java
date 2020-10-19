package org.eclipse.model;

public class Categorie {

	private String categorie;

	public Categorie(String categorie) {
		this.categorie = categorie;
	}

	public String getCategorie() {
		return this.categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	public boolean equals(Categorie categorie) {
		return (this.categorie.equalsIgnoreCase(categorie.getCategorie()));
	}

	public String toString() {
		return "Categorie [categorie=" + this.categorie + "]";
	}
	
}
