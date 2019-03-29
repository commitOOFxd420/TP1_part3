import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Plat {

	public double prix;
	public String nom;
	
	public static ArrayList<Plat> listePlats = new ArrayList<Plat>();
	
	public Plat(String nom, double prix){
		this.nom = nom;
		this.prix = prix;
	}
	
	public Plat(){
		
	}
	
	public static void getPlats()throws FileNotFoundException, IOException{
		
		String file = "src/commandes.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while(!(line = br.readLine()).equals("Commandes :")){
				
				if(line.contains(" ")){
					
					String[] ligne = line.split(" ");
					if(ligne[1].matches("\\d+.\\d+")){
						listePlats.add(new Plat(ligne[0], Double.parseDouble(ligne[1])));
					}
				}
				
				
				
			}
		}
	}
	
	public static ArrayList<Plat> getListePlat(){
		return listePlats;
	}

}
