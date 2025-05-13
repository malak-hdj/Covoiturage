package p2;
import java.util.*;
import p0.Utilisateur;
import p0.Etudiant;
import p0.ATS;
import p0.Enseignant;
import p1.Profil;
import p1.Administration;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Administration admin = new Administration();
        // HashMap pour gérer les identifiants utilisateur : pseudo -> mot de passe
        Map<String, String> comptesUtilisateurs = new HashMap<>();

        // Ajout d’utilisateurs de test
        Profil profil = new Profil();
        Utilisateur e1 = new Etudiant("leclerc", "charles", "3849", profil, 2023, "informatique", "acad");
        Utilisateur e2 = new Enseignant("hamilton", "lewis", "2343", profil, 2020, "informatique");
        Utilisateur e3 = new ATS("Wolf", "toto", "1293", profil, 2021, "chef departement");

        admin.ajouterUtilisateur(e1);
        admin.ajouterUtilisateur(e2);
        admin.ajouterUtilisateur(e3);

        //on a utilisee matricule comme pseudo et on leur associe des mots de passe)
        comptesUtilisateurs.put("3849", "leclerc12");
        comptesUtilisateurs.put("2343", "hamilton12");
        comptesUtilisateurs.put("1293", "wolf12");

        boolean continuer = true;
        while (continuer) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Utilisateur");
            System.out.println("2. Administrateur");
            System.out.println("3. Quitter");
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1: // Utilisateur
                    System.out.println("1. Connexion");
                    System.out.println("2. Inscription");
                    System.out.print("Votre choix : ");
                    int sousChoix = scanner.nextInt();
                    scanner.nextLine();

                    if (sousChoix == 1) { // Connexion utilisateur
                        System.out.print("Matricule : ");
                        String identifiant = scanner.nextLine();
                        System.out.print("Mot de passe : ");
                        String motDePasse = scanner.nextLine();

                        if (comptesUtilisateurs.containsKey(identifiant)) {
                            if (comptesUtilisateurs.get(identifiant).equals(motDePasse)) {
                                Utilisateur u = admin.rechercherUtilisateurParMatricule(identifiant);
                                if (u != null) {
                                    System.out.println(" Connexion réussie Bienvenue !!" + u.getNom() + " " + u.getPrenom());
                                    MenuUtilisateur.menuUtilisateur(u, admin, scanner);
                                }
                            } else {
                                System.out.println(" Mot de passe incorrect.");
                            }
                        } else {
                            System.out.println(" Identifiant non reconnu.");
                        }
                    } else if (sousChoix == 2) { // Inscription
                        Utilisateur nouvelUtilisateur = InscriptionUtilisateur.inscrire(admin, scanner);
                        System.out.print("Choisissez un mot de passe : ");
                        String motDePasse = scanner.nextLine();
                        comptesUtilisateurs.put(nouvelUtilisateur.getMatricule(), motDePasse);
                        System.out.println(" Inscription réussie !");
                    } else {
                        System.out.println("Choix invalide.");
                    }
                    break;

                case 2: // Connexion admin
                    if(ConnexionAdmin.demarrerConnexion())
                    MenuAdministrateur.lancerMenu(admin);
                    break;

                case 3: // Quitter
                    continuer = false;
                    System.out.println("Fermeture de l'application.");
                    break;
                default:
                    System.out.println("Choix invalide.");}}
        scanner.close();
    }
}

