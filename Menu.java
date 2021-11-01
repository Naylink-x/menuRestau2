package cours3.menuRestau;

import java.util.*;

public class Menu {

    static Scanner in = new Scanner(System.in);
    
    private ArrayList<String> listeEntrees = new ArrayList<>();
    private ArrayList<String> listePlats = new ArrayList<>();
    private ArrayList<String> listeDesserts = new ArrayList<>();

    private String commande;
    private char poursuivre;
    private String chaine = "";
    private Fichier F;
    private byte option;
    

    public void commande() {

        do {
            option = menuPrincipal();
            switch (option) {
                case 1 :
                    do {
                        System.out.println("Quelle entrée souhaitez-vous commander ? ");
                        in.nextLine();
                        commande = in.nextLine();
                        listeEntrees.add(commande);
                        System.out.println("Souhaitez-vous une autre entrée ? (o/n) ");
                        poursuivre = in.next().toLowerCase().charAt(0);
                    } while (poursuivre != 'n');
                    break;
                
                case 2 :
                    do {
                        System.out.println("Quel plat souhaitez-vous commander ? ");
                        in.nextLine();
                        commande = in.nextLine();
                        listePlats.add(commande);
                        System.out.println("Souhaitez-vous un autre plat ? (o/n) ");
                        poursuivre = in.next().toLowerCase().charAt(0);
                    } while (poursuivre != 'n');
                    break;

                case 3 :
                    do {

                        System.out.println("Quel dessert souhaitez-vous commander ? ");
                        in.nextLine();
                        commande = in.nextLine();
                        listeDesserts.add(commande);
                        System.out.println("Souhaitez-vous ajouter un dessert ? (o/n) ");
                        poursuivre = in.next().toLowerCase().charAt(0);
                    } while (poursuivre != 'n');
                    break;
                
                case 4 :
                    F = choixFichier();
                    F.ouvrir("W");
                    miseEnForme(listeEntrees, "Entrées");
                    ecrireMenu(listeEntrees);
                    miseEnForme(listePlats, "Plats");
                    ecrireMenu(listePlats);
                    miseEnForme(listeDesserts, "Desserts");
                    ecrireMenu(listeDesserts);
                    F.fermer();
                    System.out.println("Nous avons bien enregistré votre commande, voici un récapitulatif : ");
                    F.ouvrir("R");
                    do {
                        chaine = F.lire();
                        if (chaine != null) System.out.println(chaine);
                    } while (chaine != null);
                    F.fermer();
                    sortir();
                    break;

                default :
					System.out.println("Cette option n'existe pas ");
            }
        } while (option != 4);
    }

    public void miseEnForme(ArrayList listeMenu, String titre) {
        String deco = "##########################################";

        if (listeMenu.size() != 0) {
            for (int i=0; i<3; i++) {
                if (i == 0 || i == 2) {
                    F.ecrire(deco);
                }
                else {
                    F.ecrire("  " + titre);
                }
            }
        }
    }

    public void ecrireMenu(ArrayList listeMenu) {
        String commande;

        if (listeMenu != null) {
            for (int i=0; i<listeMenu.size(); i++) {
                commande = (String)listeMenu.get(i);
                F.ecrire("    - " + commande);
            }
        }
    }

    public byte menuPrincipal() {
		byte tmp;
		
		System.out.println("1. Commander une entrée");
		System.out.println("2. Commander un plat");
		System.out.println("3. Commander un dessert");
		System.out.println("4. Sortir");
		System.out.println();
		System.out.println("Votre choix : ");
		tmp = in.nextByte();
		
		return tmp;
	}

    public static void sortir() {
		System.exit(0);
	}

    public static Fichier choixFichier() {
        char poursuivre;
        String nomFichier;

        System.out.println("Dans quel fichier souhaitez-vous enregistrer votre commande ? (n'oubliez pas l'extension .txt) ");
        nomFichier = in.next();
        Fichier F = new Fichier(nomFichier);
        if (F.getFichierMenu().exists()) {
            System.out.println("Souhaitez-vous écraser le fichier ? (o/n)");
            poursuivre = in.next().toLowerCase().charAt(0);
            if (poursuivre == 'n') {
                do {
                    System.out.println("Quel nom souhaitez-vous donner au nouveau fichier ? (n'oubliez pas l'extension .txt) ");
                    nomFichier = in.next();
                } while (F.getNomFichier() == nomFichier);
                F.setNomFichier(nomFichier);
            }
        }
        return F;
    }
}
