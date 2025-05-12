package p2;

import p1.Administration;
import java.util.Scanner;

public class ConnexionAdmin {

    public static boolean demarrerConnexion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Connexion Administrateur ===");
        System.out.print("Identifiant : ");
        String identifiant = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        boolean estConnecte = Administration.estAdmin(identifiant, motDePasse);
        if (estConnecte) {
            System.out.println(" Connexion réussie en tant qu’administrateur !");
        } else {
            System.out.println(" Accès refusé. Identifiants incorrects.");
        }

        return estConnecte;
    }
}