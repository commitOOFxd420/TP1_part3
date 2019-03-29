package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Client {

	public String nom;
	public static ArrayList<Client> clientList = new ArrayList<Client>();
	public ArrayList<Commande> commandes = new ArrayList<Commande>();
	public static String file = "src/commandes.txt";

	public Client(String nom) {
		this.nom = nom;
	}

	public Client() {

	}

	public static boolean getClients() throws FileNotFoundException, IOException {
		boolean valide = true;
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String ligneActuel;

			while (!(ligneActuel = br.readLine()).equals("Plats :")) {
				
				if(!ligneActuel.equals("Clients :")){
					valide = clientValide(ligneActuel);
					if (valide) {
						clientList.add(new Client(ligneActuel));
					} else {
						System.out.println("Format client invalide : " + ligneActuel);
						break;
					}
				}
				

			}
		}

		return valide;
	}

	public static boolean clientValide(String ligne) {

		boolean valide = false;

			if (ligne.matches("^[a-zA-Z]+$")) {
				valide = true;
			
		}

		return valide;
	}

	public static ArrayList<Client> getListeClient() {
		return clientList;
	}
}
