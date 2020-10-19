package org.eclipse.model;

import java.util.Comparator;

public class ComparateurLivreParTitre implements Comparator<Livre> {
	public int compare(Livre livre1, Livre livre2){
		String titre1 = livre1.getTitre();
		String titre2 = livre2.getTitre();
		return titre1.compareToIgnoreCase(titre2);
	}
}
