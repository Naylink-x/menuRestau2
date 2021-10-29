package cours3.menuRestau;

import java.util.*;

public class Menu {

    static Scanner in = new Scanner(System.in);
    
    private List<String> listeEntrees = new ArrayList<>();
    private List<String> listePlats = new ArrayList<>();
    private List<String> listeDesserts = new ArrayList<>();

    private String commande;
    private char poursuivre;
    private String chaine = "";
    private FichierCommande F;
    private byte option;
    

    public void commande() {
        
        F = choixFichier();
        F.ouvrir("W");

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
                        System.out.println("Souhaitez-vous ajouter une entrée ? (o/n) ");
                        poursuivre = in.next().toLowerCase().charAt(0);
                    } while (poursuivre != 'n');
                    break;
                
                case 4 :
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

    public static FichierCommande choixFichier() {
        char poursuivre;
        String nomFichier;

        System.out.println("Dans quel fichier souhaitez-vous enregistrer votre commande ? (n'oubliez pas l'extension .txt) ");
        nomFichier = in.next();
        FichierCommande F = new FichierCommande(nomFichier);
        if (F.getFichierMenu().exists()) {
            System.out.println("Souhaitez-vous écraser le fichier ? (o/n)");
            poursuivre = in.next().toLowerCase().charAt(0);
            if (poursuivre == 'n') {
                System.out.println("Quel nom souhaitez-vous donner au nouveau fichier ? (n'oubliez pas l'extension .txt) ");
                F.setFichierMenu(in.next());
            }
        }
        return F;
    }
}
