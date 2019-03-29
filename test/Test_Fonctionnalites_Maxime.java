package test;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.After;
import org.junit.Test;
import main.Client;
import main.Plat;
import main.Commande;
import main.Main;

public class Test_Fonctionnalites_Maxime {

	private Commande commande;
	private String ligne = "";
	private BufferedReader br;
	private boolean valide = false;

	@After
	public void finTest() {
		Plat.listePlats.clear();
		Client.clientList.clear();
		ligne = null;
		valide = false;
	}

	// Test servant a vérifier si le format de nom testé est valide.

	@Test
	public void testFormatClient() throws IOException {

		ligne = "Antoine";

		assertTrue(Client.clientValide(ligne));
	}

	// Test servant à vérifier si le format de plat testé est valide.
	@Test
	public void testFormatPlat() throws IOException {

		ligne = "Steak 22.75";

		assertTrue(Plat.platValide(ligne));

	}

	// Test servant à vérifier si le format de commande testé est valide.

	@Test
	public void testCommandeValide() throws IOException {

		Client clientTest = new Client("Jacques");
		Client.clientList.add(clientTest);
		Plat platTest = new Plat("Spag", 10);
		Plat.listePlats.add(platTest);

		ligne = "Jacques Spag 1";

		assertTrue(Commande.commandeValide(ligne));
	}

	// Test servant à vérifier si le format de nom testé est invalide.
	@Test
	public void testFormatClientInvalide() throws IOException {

		System.out.println("\nTEST NOM CLIENT INVALIDE");
		System.out.println("____________________________________________\n");

		ligne = "Jean-Guy Dupré";

		assertFalse(Client.clientValide(ligne));
	}

	// Test servant à vérifier le format de plat testé est invalide.
	@Test
	public void testFormatPlatInvalide() throws IOException {

		System.out.println("\nTEST PLAT INVALIDE");
		System.out.println("____________________________________________\n");

		ligne = "Crevette 13.75 1";

		assertFalse(Plat.platValide(ligne));
	}

	// Test servant à vérifier le format de commande testé est invalide.
	@Test
	public void testFormatCommandeInvalide() throws IOException {

		System.out.println("\nTEST FORMAT COMMANDE INVALIDE");
		System.out.println("____________________________________________\n");

		Client clientTest = new Client("Paul");
		Client.clientList.add(clientTest);
		Plat platTest = new Plat("Jambon", 15);
		Plat.listePlats.add(platTest);

		ligne = "Jacques Jambon 1 extra sauce";

		assertFalse(Commande.commandeValide(ligne));
	}

	// Test servant à vérfiier un fichier ayant un format valide.
	@Test
	public void testFormatFichierValide() throws IOException {

		System.out.println("\nTEST AVEC FICHIER VALIDE");
		System.out.println("____________________________________________\n");

		Plat.file = "src/test/testValide.txt";
		Client.file = "src/test/testValide.txt";
		Commande.file = "src/test/testValide.txt";
		String[] args = null;

		Main.main(args);
		assertNotNull(Main.totalFacture);
	}

	// Test servant à vérifier si une commande ne correspondant à aucun client
	// est invalide.
	@Test
	public void testPlatAucunClient() throws IOException {

		System.out.println("\nTEST AUCUN CLIENT CORRESPOND À LA COMMANDE");
		System.out.println("____________________________________________\n");

		Plat.file = "src/test/clientInvalide.txt";
		Client.file = "src/test/clientInvalide.txt";
		Commande.file = "src/test/clientInvalide.txt";
		String[] args = null;

		Main.main(args);

	} 

	// Test servant à vérifier si aucun plat correspond à la commande.
	@Test
	public void testPlatAucunPlat() throws IOException {

		System.out.println("\nTEST AUCUN PLAT CORRESPOND À LA COMMANDE");
		System.out.println("____________________________________________\n");

		Plat.file = "src/test/platInvalide.txt";
		Client.file = "src/test/platInvalide.txt";
		Commande.file = "src/test/platInvalide.txt";
		String[] args = null;

		Main.main(args); 

	}

	//Test servant à verifier si le format du prix d'un plat est valide.
	@Test
	public void testPrixPlat() throws IOException{
		
		System.out.println("\nTEST PRIX PLAT INVALIDE");
		System.out.println("____________________________________________\n");
		
		ligne = "Cuisse_Poulet quarante-neuf";
		
		assertFalse(Plat.platValide(ligne));
	}
	
	//Test servant à verifier si le format d'un plat est trop court.
		@Test
		public void testInfosPlat() throws IOException{
			
			System.out.println("\nTEST INFO PLAT INVALIDE ( court )");
			System.out.println("____________________________________________\n");
			
			ligne = "Cuisse_Poulet "; 
			
			assertFalse(Plat.platValide(ligne));
		}
		
		//Test servant à verifier si le format d'un plat est trop court.
		@Test
		public void testNomPlatInvalide() throws IOException{
			
			System.out.println("\nTEST NOM PLAT INVALIDE");
			System.out.println("____________________________________________\n");
			
			ligne = "Cuisse_Poulet@¦¬ 12.75 "; 
			
			assertFalse(Plat.platValide(ligne));
		}
		
}
