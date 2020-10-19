package org.eclipse.model;

import java.util.Comparator;

public class ComparateurDVDParAuteur implements Comparator<DVD> {
	public int compare(DVD dVD1, DVD dVD2){
		String auteur1 = dVD1.getAuteur();
		String auteur2 = dVD2.getAuteur();
		return auteur1.compareToIgnoreCase(auteur2);
	}
}
