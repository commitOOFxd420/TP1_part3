package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
	public static double totalFacture, sousTotalFacture, totalTPS, totalTVQ;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {

		boolean clientValide = false;
		boolean platValide = false;
		
 
		

		clientValide = Client.getClients();
		if (clientValide) {
			platValide = Plat.getPlats();
		}
		if (platValide) {
			
			Commande.getCommandes();
			sb.append("| Bienvenue chez Barette! |\n");
			sb.append("Facture:\n\n");
			
			sousTotalFacture = affichageClient();
			
			affichagePrix(sousTotalFacture);
			System.out.println(sb.toString());
			fichierFacture();
		}
	}
	
	public static void affichagePrix(double sousTotalFacture){
		
		totalTPS = ((sousTotalFacture / 100) * 5);
		totalTVQ = ((sousTotalFacture / 100) * 10);
		totalFacture = (sousTotalFacture + totalTPS + totalTVQ);

		sb.append("\n---------------------------------\n");
		sb.append("Sous-total : " + sousTotalFacture + "\n");
		sb.append("TPS : " + totalTPS + "\n");
		sb.append("TVQ : " + totalTVQ + "\n");
		sb.append("Total : " + totalFacture + "\n");
		
		
	}
	
	public static double affichageClient(){
		double total = 0;
		double sousTotal = 0;
		for (Client client : Client.clientList) {
			for (Commande commande : client.commandes) {
				total += commande.total;
				
			}
			totalClientValide(client , total);
			sousTotal += total;
			total = 0; 		
		}
		
		return sousTotal;
	}
	
	public static void totalClientValide(Client client ,double total){
		if (total != 0) {
			sb.append(client.nom + " " + total + "$\n");

		}
	}
	
	public static void fichierFacture() throws IOException{
		
		SimpleDateFormat date = new SimpleDateFormat( ("yyyy-MM-dd-HH-mm"));
		String nomFichier = "Facture-du-"+(date.format(new Date()) +".txt");
		
		
		
		try(FileOutputStream fos = new FileOutputStream(new File(nomFichier))){
			fos.write(sb.toString().getBytes());
		}
		
		
		
	}
	

}
