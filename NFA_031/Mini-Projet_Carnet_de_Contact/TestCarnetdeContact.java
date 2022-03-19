import java.util.Scanner;

public class TestCarnetdeContact {
	
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String [][] Carnet = new String[10][4];
		String [][] Amis = new String [5][4];
		String [][] Famille = new String [5][4];  // je crée les tableaux qui stockeront les carnet et les différents groupes de contacts.
		String [][] Collegues = new String [5][4];
		String [][] Clients = new String [5][4];
		
		CarnetDeContact.Init(Carnet);
		CarnetDeContact.Init(Amis);
		CarnetDeContact.Init(Famille);	// J'initialise tous les tableaux avec la chaine de caractère "vide" pour plus de confort dans les traitements suivants. 
		CarnetDeContact.Init(Collegues);
		CarnetDeContact.Init(Clients);
		
		CarnetDeContact.Menu(Carnet, Amis, Famille, Collegues, Clients); // La fonction menu est la fonction qui contient tous les appels vers les autres fonctions.
		
	}
}
