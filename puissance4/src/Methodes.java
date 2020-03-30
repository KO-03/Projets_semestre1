import java.util.Random;
import java.util.Scanner;
/*
 * Regroupe les méthodes du programme du puissance 4
 */
public class Methodes {

	private static Scanner saisie;
	
	/*
	 * Parcours le plateau et l'affiche
	 */
	public static void afficherPlateau(int[][] tab) {
		
			for(int ligne = 0; ligne<tab[0].length; ligne++) {
				for(int colonne = 0; colonne<tab.length; colonne++) {
					System.out.print("|" + tab[colonne][ligne]+ "| ");
			}
			System.out.println("");
		}
	}
	
	/*
	 * Parcours le plateau et met les valeurs à zéro
	 */
	public static void plateauAZero(int[][] tab) {
		for(int ligne = 0; ligne<tab[0].length; ligne++) {
			for(int colonne = 0; colonne<tab.length; colonne++) {
				tab[colonne][ligne]=0;
			}
		}
	}
	
	/*
	 * Méthode contenant la boucle principale du jeu VS joueur
	 */
	public static void jeuMainVSJoueur(int[][] tab, int tourJoueur, String pseudoJ1, String pseudoJ2) {
		saisie = new Scanner(System.in);
		int choixColonne;
		int tour=1;
		boolean jouer=true;

		while(jouer && tour<=42) {
			
			
			
			int ligne=(tab[0].length)-1;
			int jeton;			
			System.out.println("______________________________________\n \n");
			afficherPlateau(tab);
			
			afficheTourJoueur(pseudoJ1, pseudoJ2, tourJoueur);
			
			System.out.println("Tour n° : " + tour);
			
			
			do {
				System.out.println("\nPour placer votre jeton entrez un nombre entre 1 et 7.\n");
				choixColonne = saisie.nextInt()-1;

				while(choixColonne<0 ||choixColonne>6){
					System.out.println("\nIncorrecte ! \nPour placer votre jeton entrez un nombre entre 1 et 7.\n");
					choixColonne = saisie.nextInt()-1;
				}

				if (verifColonne(choixColonne, tab)) {
					System.out.println("\nCette colonne "+ choixColonne+1 +" est remplie !\n");
				}

			}while(verifColonne(choixColonne, tab));	// Verifier si la colonne est remplie
				
			System.out.println("______________________________________\n \n");
			
			if (tourJoueur==1)
				jeton=1;
			else
				jeton=2;
			
			// Trouver la ligne qui n'est pas prise dans la colonne
			for(int row =(tab[0].length)-1; row>0; row--) {
				if(tab[choixColonne][row]!=0) {
					ligne--;
				}
			}
				
			tab[choixColonne][ligne]=jeton;	
			
			//Verifier puissance 4
			if (tour>3) {
				jouer=verifJeton(tab, tourJoueur, ligne, choixColonne, pseudoJ1, pseudoJ2);
			}
			
			//Initialisation et Changement de joueur pour le prochain tour 
			tour++;
						
			if(tourJoueur==1)
				tourJoueur=2;
			else
				tourJoueur=1;
		}
		
		
	}
	
	/*
	 * Vérifie si 4 jeton du même joueur sont alignés à chaque fois qu'un jeton est posé
	 */
	public static boolean verifJeton(int[][] tab, int tourJoueur, int rang, int colonne, String pseudoJ1, String pseudoJ2) {
		boolean verif= true;
		int max = 0;
		int x; int y;
		int somme;
		
		//-->  diagonale HG-BD
		x = colonne; y = rang; somme=-1;
		while(y >= 0 && x >= 0 && tab[x][y] == tourJoueur){ 
			y--; 
			x--; 
			somme++;
		}
		
		x = colonne; y = rang;
		while(y < 6 && x < 7 && tab[x][y] == tourJoueur){ 
			y++; 
			x++; 
			somme++;
		}
		
		if(somme > max) max= somme;
		
		//-->  diagonale HD-BG
		x = colonne;
		y = rang; 
		somme=-1;
		while(y >= 0 && x < 7 && tab[x][y] == tourJoueur){
			y--; 
			x++; 
			somme++;
		}
		
		x = colonne; y = rang;
		while(y < 6 && x >= 0 && tab[x][y] == tourJoueur){ 
			y++;
			x--; 
			somme++;
		}
		if(somme > max) max= somme;
		
		//-->  verticale:
		x = colonne; y = rang; somme=-1;
		while(y >= 0 && tab[x][y] == tourJoueur){ 
			y--;
			somme++;
			}
		
		y = rang;
		while(y < 6 && tab[x][y] == tourJoueur){ 
			y++;
			somme++;
		}
		if(somme > max) max= somme;
		
		//-->  horizontale:
		x = colonne; y = rang; somme=-1;
		while(x >= 0 && tab[x][y] == tourJoueur){ x--;
		somme++;
		}
		
		x = colonne;
		while(x < 7 && tab[x][y] == tourJoueur){ x++;
		somme++;
		}
		if(somme > max) max= somme;
		
		
		if(max >= 4){
			if (tourJoueur==1) {
				System.out.println("\n" +pseudoJ1 + " a gagné !\n");
				verif= false;
			}else if (tourJoueur==2) {
				System.out.println("\n" +pseudoJ2 + " a gagné !\n");
				verif= false;
			}	
		}
		
		return verif;	
	}
	
	/*
	 * Méthodes pour metttre fin à la partie
	 */
	public static boolean endGame(int gagnant, String pseudoJ1, String pseudoJ2) {
		
		if (gagnant==1) {
			System.out.println("\n" +pseudoJ1 + " a gagné !\n");
			return false;
		}else if (gagnant==2) {
			System.out.println("\n" +pseudoJ2 + " a gagné !\n");
			return false;
		}else
			return true;
	}
	
	/*
	 * 	Affiche le pseudo du  joueur qui doit jouer
	 */
	public static void afficheTourJoueur(String joueur1, String joueur2, int tourJoueur) {
		if (tourJoueur==1) {
			System.out.println("\nTour du joueur : " + joueur1);
		}else
			System.out.println("\nTour du joueur : " + joueur2);
	}
	
	/*
	 * Reçoit la colonne choisie et retourne vrai si elle est remplie
	 */
	public static boolean verifColonne(int choixColonne, int[][] tab) {
		if (tab[choixColonne][0]!=0)
			return true;
		else 
			return false;
	}
	
	///////////////////////////////////////////////////////////////    Mode IA SIMPLE    //////////////////////////////////////////
	
	public static void jeuMainVSIA(int[][] tab, int tourJoueur, String pseudoJ1, String pseudoJ2) {
		saisie = new Scanner(System.in);
		int choixColonne=-1;
		int tour=1;
		boolean jouer=true;

		while(jouer && tour<=42) {
			
			
			
			int ligne=(tab[0].length)-1;
			int jeton;			
			System.out.println("______________________________________\n \n");
			afficherPlateau(tab);
			
			afficheTourJoueur(pseudoJ1, pseudoJ2, tourJoueur);
			
			System.out.println("Tour n° : " + tour);
	
			if (tourJoueur==1) {
				jeton=1;
				do {
					System.out.println("\nPour placer votre jeton entrez un nombre entre 1 et 7.\n");
					choixColonne = saisie.nextInt()-1;
					
					while(choixColonne<0 ||choixColonne>6){
						System.out.println("\nIncorrecte ! \nPour placer votre jeton entrez un nombre entre 1 et 7.\n");
						choixColonne = saisie.nextInt()-1;
					}
					
					if (verifColonne(choixColonne, tab)) {
							System.out.println("\nCette colonne "+ choixColonne+1 +" est remplie !\n");
					}

					}while(verifColonne(choixColonne, tab));	// Verifier si la colonne est remplie
			}
			else {
				jeton=2;
				
				if (tour==1) {
					choixColonne = new Random().nextInt(7);
				}else {
					do {					
						do{
							choixColonne = choixIA(tab, 1, ligne, choixColonne);
						}while(choixColonne<0 ||choixColonne>6);
						
						if (verifColonne(choixColonne, tab)) {
								System.out.println("\nCette colonne "+ choixColonne+1 +" est remplie !\n");
						}

						}while(verifColonne(choixColonne, tab));	// Verifier si la colonne est remplie
				}
				
			}
			
			System.out.println("______________________________________\n \n");
			// debug System.out.println(choixColonne);

			for(int row =(tab[0].length)-1; row>0; row--) {
				if(tab[choixColonne][row]!=0) {
					ligne--;
				}
			}
			
			//  debug System.out.println(ligne+""+choixColonne+""+jeton);
			
			tab[choixColonne][ligne]=jeton;	
			
			//Verifier puissance 4
			if (tour>3) {
				jouer=verifJeton(tab, tourJoueur, ligne, choixColonne, pseudoJ1, pseudoJ2);
			}
			
			
			//Initialisation et Changement de joueur pour le prochain tour 
			tour++;
						
			if(tourJoueur==1)
				tourJoueur=2;
			else
				tourJoueur=1;
			
		}
	}
	
	
	/*
	 * Vérifie si 4 jeton du même joueur sont alignés à chaque fois qu'un jeton est posé
	 */
	public static int choixIA(int[][] tab, int tourJoueur, int rang, int colonne) {
		int max = 0;
		int x; int y;
		int somme;
		
		int colonneIA = -1; 
		
		//-->  diagonale HG-BD
		x = colonne; y = rang; somme=-1;
		while(y >= 0 && x >= 0 && tab[x][y] == tourJoueur){ 
			y--; 
			x--; 
			somme++;
		}
		
		x = colonne; y = rang;
		while(y < 6 && x < 7 && tab[x][y] == tourJoueur){ 
			y++; 
			x++; 
			somme++;
		}
		if(somme > max) max= somme;
		
		colonneIA = colonne(max, colonne);
		
		//-->  diagonale HD-BG
		x = colonne;
		y = rang; 
		somme=-1;
		while(y >= 0 && x < 7 && tab[x][y] == tourJoueur){
			y--; 
			x++; 
			somme++;
		}
		
		x = colonne; y = rang;
		while(y < 6 && x >= 0 && tab[x][y] == tourJoueur){ 
			y++;
			x--;
			somme++;
		}
		if(somme > max) max= somme;
		
		colonneIA = colonne(max, colonne);
		
		//-->  verticale:
		x = colonne; y = rang; somme=-1;
		while(y >= 0 && tab[x][y] == tourJoueur){ 
			y--;
			somme++;
			}
		
		y = rang;
		while(y < 6 && tab[x][y] == tourJoueur){ 
			y++;
			somme++;
		}
		if(somme > max) max= somme;
		
		colonneIA = colonne(max, colonne);
		
		//-->  horizontale:
		x = colonne; y = rang; somme=-1;
		while(x >= 0 && tab[x][y] == tourJoueur){ x--;
		somme++;
		}
		
		x = colonne;
		while(x < 7 && tab[x][y] == tourJoueur){ x++;
		somme++;
		}
		if(somme > max) max= somme;
		
		colonneIA = colonne(max, colonne);
				
		return colonneIA;
		
	}
	/*
	 * Retourner la colonne pour l'IA SIMPLE
	 */
	public static int colonne(int max, int colonne) {

			if(max >= 2){
				return colonne;
			}else {
				return colonne = new Random().nextInt(7);
			}		

		}
	}

