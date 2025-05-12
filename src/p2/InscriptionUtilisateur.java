package p2;
import p0.Utilisateur;
import p0.Etudiant;
import p0.Enseignant;
import p0.ATS;
import p1.Profil;
import p1.Administration;
import java.util.Scanner;


public class InscriptionUtilisateur {
    public static Utilisateur inscrire(Administration admin, Scanner scanner) {
        System.out.println("\n=== Inscription d’un nouvel utilisateur ===");
        System.out.println("Type : 1. Étudiant | 2. Enseignant | 3. ATS");
        int type = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Matricule : ");
        String matricule = scanner.nextLine();

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        
        Utilisateur nouveau = null;
        Profil p = new Profil();

        switch (type) {
            case 1:
                System.out.print("Année d’admission : ");
                int anneeAdm = scanner.nextInt(); scanner.nextLine();
                System.out.print("Faculté : ");
                String fac = scanner.nextLine();
                System.out.print("Spécialité : ");
                String spe = scanner.nextLine();
                nouveau = new Etudiant( nom, prenom, matricule, p, anneeAdm, fac, spe);
                break;
            case 2:
                System.out.print("Année de recrutement : ");
                int anneeRecEns = scanner.nextInt(); scanner.nextLine();
                System.out.print("Faculté : ");
                String facEns = scanner.nextLine();
                nouveau = new Enseignant(nom, prenom, matricule, p,anneeRecEns, facEns);
                break;
            case 3:
                System.out.print("Année de recrutement : ");
                int anneeRecATS = scanner.nextInt(); scanner.nextLine();
                System.out.print("Service : ");
                String service = scanner.nextLine();
                nouveau = new ATS( nom, prenom, matricule, p, anneeRecATS, service);
                break;
            default:
                System.out.println("❌ Type inconnu.");
                nouveau = null;
        }

        if (admin.ajouterUtilisateur(nouveau)) {
            System.out.println(" Inscription réussie !");
        } else {
            System.out.println(" Échec de l’inscription (déjà inscrit ?)");
        }
        return nouveau;
    }
}