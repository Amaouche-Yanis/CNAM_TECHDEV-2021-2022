import java.util.Scanner;
import java.util.regex.*;

public class CarnetDeContact {

	static Scanner input = new Scanner(System.in);

	public static void Ajouter(String tab[][]) {

		String nom;
		String prenom;
		String telephone;
		String email;

		boolean licorrect = false;
		int ligne;
		do {
			System.out.println("Entrer la ligne dans laquelle votre enregistrement sera stocké : \n");
			ligne = input.nextInt();         // Ceci est un choix de conception, primitif je l'admets, L'utilisateur choisira toujours sur quelle ligne il fera des opérations.
			ligne -= 1;
			if (ligne > tab.length || ligne < 0) {
				licorrect = false;
				System.out.println("La ligne n'est pas comprise dans le tableau");
			} else if (ligne < tab.length && ligne >= 0) {
				licorrect = true;
			}
		} while (licorrect == false);

		do {
			System.out.println("Entrer le nom de votre contact : \n");
			nom = input.next(); 					// Vérification des noms grâce à la regex de la fonction VerifNom.
			if (CarnetDeContact.VerifNomPrenom(nom) == false) {
				System.out.println("Le nom que vous avez rentré a un format invalide ! Essayez encore.");
			}
		} while (CarnetDeContact.VerifNomPrenom(nom) == false);

		do {
			System.out.println("Entrer le prénom de votre contact : \n");
			prenom = input.next(); 					// Vérification des prénoms grâces à la regex de la fonction VerifNom.
			if (CarnetDeContact.VerifNomPrenom(prenom) == false) {
				System.out.println("Le nom que vous avez rentré a un format invalide ! Essayez encore.");
			}
		} while (CarnetDeContact.VerifNomPrenom(prenom) == false);

		do {
			System.out.println("Entrer le numéro de téléphone de votre contact : \n");
			telephone = input.next(); 							// Vérification des numéros de téléphones grâce à la regex de la fonction VerifPhone.
										
			if (CarnetDeContact.VerifPhone(telephone) == false) {
				System.out.println("Le numéro de téléphone a un format invalide ! Essayez encore.");
			}
		} while (CarnetDeContact.VerifPhone(telephone) == false);

		do {
			System.out.println("Entrer l'adresse E-mail de votre contact : \n");
			email = input.next(); 								// Vérification des Em@il grâce à la regex de la fonction VerifEmail.
			if (CarnetDeContact.VerifMail(email) == false) {
				System.out.println("L'adresse email a un format invalide ! Essayez encore.");
			}
		} while (CarnetDeContact.VerifMail(email) == false);

		tab[ligne][0] = nom;
		tab[ligne][1] = prenom;
		tab[ligne][2] = telephone;
		tab[ligne][3] = email;

		CarnetDeContact.AfficherTout(tab);

		System.out.println("\n");

	}

	public static void ChercheretAfficher(String tab[][]) {

		System.out.println("Quelle est la valeur que vous chercher ? \n");
		String valeur = input.next();
		int ligne = 0;              
														// ici on cherche une valeur, peu importe que ce soit un nom, un prénom, une adresse email ou un numéro de téléphone.
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				String extrait = tab[i][j];
				extrait.toLowerCase();
				if (extrait.equalsIgnoreCase(valeur)) {
					System.out.println(
							"La valeur se trouve à la ligne " + (i + 1) + " et à la colonne " + (j + 1) + "\n");
					ligne = i;																						// Le programme balaye le tableau à la recherche d'une valeur, affiche le contenu de chaque ligne qui contient une occurence de la valeur.
					System.out.print("Nom : " + tab[ligne][0] + "\nPrénom : " + tab[ligne][1] + "\nTéléphone : "
							+ tab[ligne][2] + "\nE-m@il : " + tab[ligne][3] + "\n");
				}
			}
		}

		System.out.println("\n");
	}

	public static void AfficherTout(String tab[][]) {

		try {
			for (int i = 0; i < tab.length; i++) {
				System.out.println("\n");
				for (int j = 0; j < tab[i].length; j++) {
					System.out.print(tab[i][j] + " ");
				}
			}							// Une simple boucle avec un affichage à chaque incrémentation de la boucle.

		} catch (Exception e) {
			System.out.println("Erreur dans le parcours de la boucle");
		}

		System.out.println("\n");

	}

	public static void Modifier(String tab[][]) {

		boolean licorrect = false;
		int ligne;
		String verif;

		do {
			System.out.println("Entrer la ligne que vous voulez modifier\n");
			ligne = input.nextInt();
			ligne -= 1;
			if (ligne > tab.length || ligne < 0) {
				licorrect = false;
				System.out.println("La ligne n'est pas comprise dans le tableau");		// Simple vérification si la ligne entrée correspond à une ligne dans le tableau
			} else if (ligne < tab.length && ligne >= 0) {
				licorrect = true;
			}
		} while (licorrect == false);

		System.out.print("Nom : " + tab[ligne][0] + "\nPrénom : " + tab[ligne][1] + "\nTéléphone : " + tab[ligne][2]
				+ "\nE-m@il : " + tab[ligne][3] + "\n");

		System.out.println("Voulez vous vraiment la modifier ? \n Oui/Non\n");
		verif = input.next();										// Demande de confirmation si l'utilisateur veut modifier une ligne.
		if (verif.equalsIgnoreCase("oui")) {

			do {
				System.out.println("Changez le nom\n");
				tab[ligne][0] = input.next();
				if (CarnetDeContact.VerifNomPrenom(tab[ligne][0]) == false) {			// Vérification des numéros de téléphones grâce à la regex de la fonction VerifNomPrenom
					System.out.println("Le nom a un format invalide ! Essayez encore.");
				}
			} while (CarnetDeContact.VerifNomPrenom(tab[ligne][0]) == false);

			do {
				System.out.println("Changer le prénom\n");
				tab[ligne][1] = input.next();
				if (CarnetDeContact.VerifNomPrenom(tab[ligne][1]) == false) {
					System.out.println("Le prénom a un format invalide ! Essayez encore.");		// Vérification des numéros de téléphones grâce à la regex de la fonction VerifNomPrenom
				}
			} while (CarnetDeContact.VerifNomPrenom(tab[ligne][1]) == false);

			do {
				System.out.println("Changer le numéro de téléphone\n");
				tab[ligne][2] = input.next();
				if (CarnetDeContact.VerifPhone(tab[ligne][2]) == false) {
					System.out.println("Le numéro de téléphone a un format invalide ! Essayez encore.");	// Vérification des numéros de téléphones grâce à la regex de la fonction verifPhone
				}
			} while (CarnetDeContact.VerifPhone(tab[ligne][2]) == false);

			do {
				System.out.println("Changer l'em@il\n");
				tab[ligne][3] = input.next();
				if (CarnetDeContact.VerifMail(tab[ligne][3]) == false) {
					System.out.println("L'adresse email a un format invalide ! Essayez encore.");  // Vérification des numéros de téléphones grâce à la regex de la fonction verifEmail
				}
			} while (CarnetDeContact.VerifMail(tab[ligne][3]) == false);

		}

		System.out.println("\n");

	}

	public static void Supprimer(String tab[][]) {

		System.out.println("Quelle ligne voulez vous supprimer ?\n");
		int ligne = input.nextInt();
		ligne -= 1;

		for (int i = ligne; i <= ligne; i++) {
			for (int j = 0; j < 4; j++) {
				tab[i][j] = "vide";				// Simple remplacement de la chaine de caractère représentant le contact pas la chaine de caractère "vide" qui est une convention qui représente le vide. 
			}
		}

		System.out.println("\n");
	}

	public static boolean VerifPhone(String chaine) {

		String telephone = chaine;
		boolean condition = Pattern.matches("^((06)|(07))[0-9]{8}|^((0033)|\\+33)[0-9]{9}", telephone); // La variable condition prend la valeur renvoyée par la méthode matches qui compare la regex à la chaine qui entre en input.
																										// regex écrite par mes soins et testée sur www.regex101.com
		return condition;		
	}

	public static boolean VerifMail(String chaine) {

		String email = chaine;
		boolean condition = Pattern.matches("^[^0-9]([a-zA-Z0-9-_\\.]|){1,20}@([a-zA-Z0-9]){1,20}\\.[a-zA-Z]{2,6}",
				email); // La variable condition prend la valeur renvoyée par la méthode matches qui compare la regex à la chaine qui entre en input.
						// regex écrite par mes soins et testée sur www.regex101.com
		return condition;
	}

	public static boolean VerifNomPrenom(String chaine) {

		String valeur = chaine;
		boolean condition = Pattern.matches("^[a-zA-Z]{1}[a-zA-Z_\\- ]+", valeur); // La variable condition prend la valeur renvoyée par la méthode matches qui compare la regex à la chaine qui entre en input.
																					// regex écrite par mes soins et testée sur www.regex101.com
		return condition;
	}

	public static String[][] AjouterLigne(String tab[][]) {

		int rajout;
		System.out.println("Combien de lignes voulez vous rajoutez ?");
		rajout = input.nextInt();   //fonction rendue obligatoire par mon choix de conception et en raison de la nature de la structure de données statique.
		int lignes = tab.length;
		String[][] tab2 = new String[lignes + rajout][4];		// L'utilisateur choisit le nombre de lignes qu'il veut rajouter à la structure.
																

		for (int k = 0; k < tab2.length; k++) {
			for (int l = 0; l < tab2[k].length; l++) {
				tab2[k][l] = "vide";							// un nouvelle structure est crée, de taille ( x + lignes en +) ; affectée avec la chaine "vide" conventionnelle.
			}
		}

		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {		// copie du contenu de l'ancienne structure de données dans la nouvelle
				tab2[i][j] = tab[i][j];
			}
		}

		for (int i = 0; i < tab2.length; i++) {
			System.out.println("\r");						// affichage de la structure
			for (int j = 0; j < tab2[i].length; j++) {
				System.out.print(tab2[i][j] + "\t");
			}
		}

		System.out.println("\n");

		return tab2;
	}

	public static void Contact2Groupe(String tab[][], String tab1[][]) {
		
		boolean lignetab = false;
		int ligneCarnet;
		
		do {
		System.out.println("Quelle ligne de votre carnet voulez vous ajouter au groupe ?");
		ligneCarnet = input.nextInt();
		ligneCarnet -= 1;
		if ( ligneCarnet > tab.length || ligneCarnet < 0) { // Vérification si la ligne est comprise dans le carnet de contact.
			lignetab = false;
			System.out.println("La ligne n'est pas comprise dans le tableau");
		} else if ( ligneCarnet < tab.length && ligneCarnet >= 0 ) {
			lignetab = true;
		}
		} while ( lignetab == false );
		
		boolean lignetab1 = false;
		int ligneGroupe;
		
		do {
		System.out.println("Dans quelle ligne de votre groupe de contact voulez vous copier le contact ?");
		ligneGroupe = input.nextInt();
		ligneGroupe -= 1;
		if ( ligneGroupe > tab1.length || ligneGroupe < 0 ) {	// Vérification si la ligne est comprise dans le tab2 qui peut être n'importe quel groupe de contact
			lignetab1 = false;
			System.out.println("La ligne n'est pas comprise dans le tableau");
		} else if ( ligneGroupe < tab.length && ligneGroupe >= 0 ) {
			lignetab1 = true ;
		}
		} while ( lignetab1 == false );
		
		
		tab1[ligneGroupe][0] = tab[ligneCarnet][0];
		tab1[ligneGroupe][1] = tab[ligneCarnet][1];
		tab1[ligneGroupe][2] = tab[ligneCarnet][2];
		tab1[ligneGroupe][3] = tab[ligneCarnet][3];

	}

	public static void Menu(String tab[][], String tab2[][], String tab3[][], String tab4[][], String tab5[][]) {

		int choix;
		boolean choixcorrect = false;

		do {

			System.out.print("Menu  Carnet de contact :\n" + " ( 1 ) * Ajouter un contact\n"
					+ " ( 2 ) * Chercher et afficher\n" + " ( 3 ) * Modifier\n" + " ( 4 ) * Supprimer un contact\n"
					+ " ( 5 ) * Afficher tout le contenu du carnet\n" + " ( 6 ) * Ajouter des lignes\n"
					+ " ( 7 ) * Ajouter un contact à un groupe\n" + " ( 8 ) * Afficher le groupe de contact\n"
					+ " ( 0 ) * Quitter\n" + "------------------------------------------------------------\n"
					+ " Votre choix : ");

			choix = input.nextInt(); // Faiblesse du programme, si l'utilisateur rentre une mauvaise valeur le programme s'arrête

			switch (choix) {
			case 1:
				CarnetDeContact.Ajouter(tab); // on ne peut ajouter de contact que dans le carnet principal
				break;
			case 2:
				CarnetDeContact.ChercheretAfficher(tab); // on ne peut chercher de contact que dans le carnet principal
				break;
			case 3:
				CarnetDeContact.Modifier(tab); // On ne peut modifier de contact que dans le carnet principal
				break;
			case 4:
				CarnetDeContact.Supprimer(tab); // On ne peut supprimer de contact que dans le carnet principal
				break;
			case 5:
				CarnetDeContact.AfficherTout(tab);
				break;
			case 6:
				System.out.println("Dans quel carnet voulez vous ajouter une/plusieurs lignes ?");
				System.out.println("1 : Carnet principal | 2 : Amis | 3 : Famille | 4 : Collègues | 5 : Clients");
				int choix6 = input.nextInt();
				switch (choix6) {
				case 1:
					tab = CarnetDeContact.AjouterLigne(tab);
					break;
				case 2:
					tab2 = CarnetDeContact.AjouterLigne(tab2);
					break;
				case 3:
					tab3 = CarnetDeContact.AjouterLigne(tab3);
					break;
				case 4:
					tab4 = CarnetDeContact.AjouterLigne(tab4);
					break;
				case 5:
					tab5 = CarnetDeContact.AjouterLigne(tab5);
					break;
				}
				break;
			case 7:
				System.out.println("Dans quel groupe de contact voulez vous ajouter votre contact ?");
				System.out.println("1 : Amis | 2 : Famille | 3 : Collègues | 4 : Clients\n");
				int choix7 = input.nextInt();
				switch (choix7) {
				case 1:
					CarnetDeContact.Contact2Groupe(tab, tab2);
					break;
				case 2:
					CarnetDeContact.Contact2Groupe(tab, tab3);
					break;
				case 3:
					CarnetDeContact.Contact2Groupe(tab, tab4);
					break;
				case 4:
					CarnetDeContact.Contact2Groupe(tab, tab5);
					break;
				}
				break;
			case 8:
				System.out.println("Quel groupe de contact voulez-vous afficher ?");
				System.out.println("1 : Amis | 2 : Famille | 3: Collègues | 4 : Clients\n");
				int choix8 = input.nextInt();
				switch (choix8) {
				case 1:
					CarnetDeContact.AfficherTout(tab2);
					break;
				case 2:
					CarnetDeContact.AfficherTout(tab3);
					break;
				case 3:
					CarnetDeContact.AfficherTout(tab4);
					break;
				case 4:
					CarnetDeContact.AfficherTout(tab5);
					break;
				}

			}

		} while (choix != 0);
		System.out.println("Merci d'avoir utilisé mon programme ^^ ");

	}

	public static String[][] Init(String tab[][]) {

		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) { // petite fonction pour remédier au problème des pointeurs sur des objets String "null" qui complique les comparaisons entre objets.
				tab[i][j] = "vide";
			}
		}

		return tab;

	}
}
