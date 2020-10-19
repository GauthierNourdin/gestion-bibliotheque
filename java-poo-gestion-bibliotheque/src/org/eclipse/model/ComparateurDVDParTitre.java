package org.eclipse.model;

import java.util.Comparator;

public class ComparateurDVDParTitre implements Comparator<DVD> {
	public int compare(DVD dVD1, DVD dVD2){
		String titre1 = dVD1.getTitre();
		String titre2 = dVD2.getTitre();
		return titre1.compareToIgnoreCase(titre2);
	}
}
