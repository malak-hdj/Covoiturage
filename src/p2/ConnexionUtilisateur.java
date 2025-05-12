package p2;
import p0.Utilisateur;

import java.util.List;
import java.util.Scanner;

public class ConnexionUtilisateur {

    public static Utilisateur seConnecter(List<Utilisateur> utilisateurs, Scanner scanner) {
        System.out.println("=== Connexion Utilisateur ===");
        System.out.print("Matricule : ");
        String matricule = scanner.nextLine();

        for (Utilisateur u : utilisateurs) {
            if (u.getMatricule().equals(matricule)) {
                System.out.println(" Bienvenue " + u.getNom() + " " + u.getPrenom());
                return u;
            }
        }

        System.out.println(" Utilisateur non trouvé.");
        return null;
    }
}