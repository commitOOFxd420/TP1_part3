package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Commande {

	public Client client;
	public Plat plat;
	public double total;
	static Client clientActu = new Client();
	static Plat platActu = new Plat();
	public static String file = "src/commandes.txt";
	public Commande(Client client, Plat plat, int amount) {

		this.client = client;
		this.plat = plat;
		this.total = plat.prix * amount;
	}

	public static void getCommandes() throws FileNotFoundException, IOException {
	


		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			boolean commande = false;
			while (!(line = br.readLine()).equals("Fin")) {

					if (line.equals("Commandes :")) {
						commande = true;
					}
					if (commande && !(line.equals("Commandes :"))) {
						
						boolean valide = commandeValide(line);


						if(valide){
							String[] ligneSplit = line.split(" ");
							
							clientActu.commandes
							.add(new Commande(clientActu, platActu, Integer.parseInt(ligneSplit[2])));
						}
									

					

				}
			}
		}
	}
	
	public static boolean commandeValide(String ligne){
		boolean valide = false;
		boolean platValide = false, clientValide = false;
		
		if(ligne.contains(" ")){
			String[] ligneSplit = ligne.split(" ");
			if(ligneSplit.length == 3){
				if (ligneSplit[2].matches("\\d+")) {
					
					for (Client clientTemp : Client.getListeClient()) {
						if (clientTemp.nom.equals(ligneSplit[0])) {
							clientActu = clientTemp;
							clientValide = true;
						}
					}
					
					 
					if(!clientValide){
						System.out.println("Client invalide : " + ligne);
					}
					
					for (Plat platTemp : Plat.getListePlat()) {
 
						if (platTemp.nom.equals(ligneSplit[1])) {
							platActu = platTemp;
							platValide = true;
						}
					}
					
					if (!platValide) {
						System.out.println("Plat invalide : " + ligne);
					}
					
					if(platValide && clientValide){
						valide = true;
					}
					
					
				} else {
					System.out.println("Prix invalide");
				}
			} else {
				if(ligneSplit.length <3){
					System.out.println("Information manquante : " + ligne);
				} else {
					System.out.println("Format de commande invalide : " + ligne);
				}
			}
		}
		
		return valide;
		
	}


	

}
