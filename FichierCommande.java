package cours3.menuRestau;

import java.io.*;

public class FichierCommande {
    private BufferedWriter fW;
    private BufferedReader fR;
    private String nomFichier;
    private File fichierMenu;
    private char mode;

    public FichierCommande(String nomFichier) {
        this.nomFichier = nomFichier;

        try {
            fichierMenu = new File(nomFichier);
        }
        catch (NullPointerException e) {
            System.out.println(fichierMenu + " : Erreur lors de la création ");
        }
    }

    public void ouvrir(String s) {
        try {
            mode = s.toUpperCase().charAt(0);
            if (mode == 'R' || mode =='L') 
                fR = new BufferedReader(new FileReader(fichierMenu = new File(nomFichier)));
            else if (mode == 'W' || mode == 'E')
                fW = new BufferedWriter(new FileWriter(fichierMenu = new File(nomFichier)));
        }
        catch (IOException e) {
            System.out.println(fichierMenu + " : Erreur lors de l'ouverture ");
        }
    }

    public void ecrire(String cmd) {
        try {
            if (cmd != null) {
                fW.write(cmd, 0, cmd.length());
                fW.newLine();
            }
        }
        catch (IOException e) {
            System.out.println(fichierMenu + " : Erreur lors de l'écriture ");
        }
    }

    public String lire() {
        try {
            String cmd = fR.readLine();
            return cmd;
        }
        catch (IOException e) {
            System.out.println(fichierMenu + " : Erreur de lecture ");
        }
        return null;
    }

    public void fermer() {
        try {
            if (mode == 'R' || mode == 'L') fR.close();
            else if (mode == 'W' || mode == 'E') fW.close();
        }
        catch (IOException e) {
            System.out.println(fichierMenu + " : Erreur à la fermeture ");
        }
    }

    public void setFichierMenu(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public File getFichierMenu() {
        return fichierMenu;
    }
}