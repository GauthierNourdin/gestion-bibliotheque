package org.eclipse.model;

import java.util.ArrayList;
import java.util.Comparator;

public class ComparateurDVDParCategories implements Comparator<DVD> {
	public int compare(DVD dVD1, DVD dVD2){
		ArrayList<Categorie> categories1 = dVD1.getCategories();
		ArrayList<Categorie> categories2 = dVD2.getCategories();
		int taille1 = categories1.size();
		int taille2 = categories2.size();
		if (taille1 > 3) taille1 = 3;
		if (taille2 > 3) taille2 = 3;
		if (taille1 == 0 && taille2 != 0) {
			return "".compareToIgnoreCase(categories2.get(0).getCategorie());
		}
		if (taille1 != 0 && taille2 == 0) {
			return categories1.get(0).getCategorie().compareToIgnoreCase("");
		}
		if (taille1 == 0 && taille2 == 0) {
			return 0;
		}
		if (taille1 == taille2) {
			int comparaison = 0;
			for (int i = 0 ; i < taille1 ; i++) {
			comparaison += categories1.get(i).getCategorie().compareToIgnoreCase(categories2.get(i).getCategorie()) * Puissance(256, taille1 - i - 1);
			}
			return comparaison;
		}
		if (taille1 < taille2) {
			int comparaison = 0;
			for (int i = 0 ; i < taille1 ; i++) {
			comparaison += categories1.get(i).getCategorie().compareToIgnoreCase(categories2.get(i).getCategorie()) * Puissance(256, taille1 - i);
			}
			comparaison += "".compareToIgnoreCase(categories2.get(taille2 - 1).getCategorie());
			return comparaison;
		}
		if (taille1 > taille2) {
			int comparaison = 0;
			for (int i = 0 ; i < taille1 ; i++) {
			comparaison += categories1.get(i).getCategorie().compareToIgnoreCase(categories2.get(i).getCategorie()) * Puissance(256, taille1 - i);
			}
			comparaison += categories1.get(taille1 - 1).getCategorie().compareToIgnoreCase("");
			return comparaison;
		}
		return 0;
	}
	
	private int Puissance(int a, int b) {
		int res = 1;
		for (int i = 0 ; i < b ; i++) {
			res *= a;
		}
		return res;
	}
	
}
