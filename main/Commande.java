import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Commande {
	
	public String nomClient, nomPlat;
	public double prix;
	static ArrayList<Commande> listeCommandes = new ArrayList<Commande>();

	public Commande(Client client, Plat plat){
		
		this.nomClient = client.nom;
		this.nomPlat = plat.nom;
		this.prix = plat.prix;
	}
	
	public static void getCommandes() throws FileNotFoundException, IOException{
		String file = "src/commandes.txt";
		Client client = new Client();
		Plat plat = new Plat();
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while(!(line = br.readLine()).equals("Fin")){
				
				if(line.contains(" ")){
					
					String[] ligne = line.split(" ");
					
					
					if(ligne.length == 3){
						
						if(ligne[2].matches("\\d+")){
							
							for(Client clientActu : Client.getListeClient()){
								
								if(clientActu.nom.equals(ligne[0])){
									client = clientActu;
								}
							}
							
							for(Plat platActu : Plat.getListePlat()){
								
								if(platActu.nom.equals(ligne[1])){
									plat = platActu;
								}
							}
							listeCommandes.add(new Commande(client, plat));
							
						}
					}
				}
			}
		}
	}
	
	
	
}
