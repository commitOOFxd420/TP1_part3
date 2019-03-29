package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Plat {

	public double prix;
	public String nom;
	public static String file = "src/commandes.txt";
	public static ArrayList<Plat> listePlats = new ArrayList<Plat>();

	public Plat(String nom, double prix) { 
		this.nom = nom;
		this.prix = prix;
	}

	public Plat() {

	}

	public static boolean getPlats() throws FileNotFoundException, IOException {
		boolean plats = false;
		boolean valide = true;
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while (!(line = br.readLine()).equals("Commandes :")) {

				if (line.equals("Plats :")) {
					plats = true;
				}

				if (plats && !(line.equals("Plats :"))) {
					
					valide = platValide(line);
					
					if(valide){
						String[] ligne = line.split(" ");
						listePlats.add(new Plat(ligne[0], Double.parseDouble(ligne[1])));
					} else {
						break;
					}
					

				}

			}
		}

		return valide;
	}

	public static boolean platValide(String ligne) {
		boolean valide = false;

		if (ligne.contains(" ")) {
			String[] ligneSplit = ligne.split(" ");
			if (ligneSplit.length == 2) {
				if (ligneSplit[0].matches("^[a-zA-Z0-9!@#$&()-`.+,/\"]*$")) {

					if (ligneSplit[1].matches("\\d+.\\d+")) {
						valide = true;
					} else {
						System.out.println("Prix invalide : " + ligne);
					}
				} else {
					System.out.println("Nom de plat invalide : " + ligne);
				}
			} else {
				if (ligneSplit.length == 1) {
					System.out.println("Information insuffisante : " + ligne);
				} else {
					System.out.println("Format de plat invalide : " + ligne);
				}
			}
		}

		return valide;

	}

	public static ArrayList<Plat> getListePlat() {
		return listePlats;
	}

}
