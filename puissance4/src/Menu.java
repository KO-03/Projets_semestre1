import java.util.Random;
import java.util.Scanner;

/*
 * Cette classe contient le menu du jeu qui permet de lancer des parties contre des joueurs, et les autres modes à venir
 */

public class Menu {
	static final String TEXT_MENU = 
			"\nVeuillez choisir avec le numéro correspondant : \n" + "\n" + 
			"1. régles du jeu\n" + "\n" + 
			"2. Jouer contre une personne\n" + "\n" + 
			"3. Jouer contre l'ordinateur(facile)\n"+"\n"+
			"4. Jouer contre l'ordinateur(intermediaire)\n"+"\n"+
			"5. Pour quitter"; 
	
	static final String TEXT_REGLE = 
					"\nChaque joueur place un jeton chacun son tour dans une colonne, le jeton tombe en bas de la colonne\n"
					+"Pour gagner une partie de puissance 4, il suffit d’être le premier à aligner 4 jetons de sa couleur horizontalement, verticalement et diagonalement.\r\n" + 
					"\r\n" + "Si lors d’une partie, tous les jetons sont joués sans qu’il y est d’alignement de jetons, la partie est déclaré nulle."; 
	
	public static void menuJeu() {	
		Scanner saisie = new Scanner(System.in);
		int choix;
		int tourJoueur;
		String pseudoJ1, pseudoJ2;
		
		System.out.println("Bienvenue dans ce jeu du puissance 4 en mode console ! \n Pour commencer veuillez enter vos pseudos :\n");
		
		//Créer le plateau
		int plateau[][] = new int[7][6];
		// plateau[axe x colonnes][axe y lignes]
		
		//Saisie peusdo avant pour garder en mémoire
		
		
		System.out.println("\nVeuillez entrer le pseudo du joueur 1 :");
		pseudoJ1 = saisie.nextLine();
		System.out.println("\nVeuillez entrer le pseudo du joueur 2 :");
		pseudoJ2 = saisie.nextLine();
		
			
		
		do {
			System.out.println(TEXT_MENU);
			choix = Integer.parseInt(saisie.nextLine());
			
			switch (choix) {
		        case 1://régles du jeu
		        	System.out.println(TEXT_REGLE);
		    		System.out.println("Le plateau s'acutalisera à chaque action, "
		    				+ "0 correspond a une case vide,\n Le 1 au jeton du joueur 1 : \n Le 2 au jeton du joueur 2 : \n");

		        	break;
		        case 2://Contre une personne
		    			    		
		    		System.out.println("Le plateau s'acutalisera à chaque action, "
		    				+ "0 correspond a une case vide,\n Le 1 au jeton du joueur 1 : " + pseudoJ1 +  " \n Le 2 au jeton du joueur 2 : " + pseudoJ2 +"\n");
		    		//Initialiser le plateau 
		    		Methodes.plateauAZero(plateau);

		    		
		    		System.out.println("Le joueur qui commence est choisi aléatoirement");
		    		// Choix aléatoire du premier joueur
		    		tourJoueur = new Random().nextInt(2)+1;//+1 pour avoir tourJoueur = Jeton = Joueur
		    				
		    		Methodes.jeuMainVSJoueur(plateau, tourJoueur, pseudoJ1, pseudoJ2); // PRINCIPAL
		    		
		    		Methodes.afficherPlateau(plateau);
		    		
		    		System.out.println("\nFin de la partie ! Voulez vous rejouer ?");
		    		
		        	break;
		        case 3:// IA simple (//nul horizontalement//)
		        	System.out.println("\nVeuillez entrer votre pseudo :");
		    		pseudoJ1 = saisie.nextLine();
		    		
		   
		    		pseudoJ2 = "L'Ordinateur";
		    		System.out.println("Le plateau s'acutalisera à chaque action, "
		    				+ "0 correspond a une case vide,\n Le 1 au jeton du joueur 1 : " + pseudoJ1 +  " \n Le 2 au jeton du joueur 2 : " + pseudoJ2 +"\n");
		    		//TO DO Initialiser le plateau 
		    		Methodes.plateauAZero(plateau);

		    		
		    		System.out.println("Le joueur qui commence est choisi aléatoirement");
		    		// Choix aléatoire du premier joueur
		    		tourJoueur = new Random().nextInt(2)+1;//+1 pour avoir tourJoueur = Jeton = Joueur
		    				
		    		Methodes.jeuMainVSIA(plateau, tourJoueur, pseudoJ1, pseudoJ2); // PRINCIPAL
		    		
		    		Methodes.afficherPlateau(plateau);
		    		
		    		System.out.println("\nFin de la partie ! Voulez vous rejouer ?");
		    		
		        	

		    	break;
		        case 4:// IA intermédiaire

		    	break;
		    	
		        case 5: break;
		        
		        default: System.out.println("mauvaise valeur !\n");
				break;
			}
			
		}while(choix!=5);
		
		saisie.close();
	}
}
