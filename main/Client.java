import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Client {

	public String nom;
	public static ArrayList<Client> clientList = new ArrayList<Client>();
	
	public Client(String nom){
		this.nom = nom;
	}
	
	public Client(){
		
	}
	
	public static void getClients() throws FileNotFoundException, IOException{
		String file = "src/commandes.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while(!(line = br.readLine()).equals("Plats :")){
				if(!line.equals("Clients :")){
					clientList.add(new Client(line));
				}
				
			}
		}
	}
	
	public static ArrayList<Client> getListeClient(){
		return clientList;
	}
}
