package org.eclipse.model;

import java.util.Comparator;

public class ComparateurLivreParAuteur implements Comparator<Livre> {
	public int compare(Livre livre1, Livre livre2){
		String auteur1 = livre1.getAuteur();
		String auteur2 = livre2.getAuteur();
		return auteur1.compareToIgnoreCase(auteur2);
	}
}
