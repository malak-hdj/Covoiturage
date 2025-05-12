package p2;
import java.util.*;

import monprojet.enums.StatutUser;
import monprojet.enums.TypeCourse;
import p0.Utilisateur;
import p0.Etudiant;
import p0.ATS;
import p0.Enseignant;
import p1.Disponibilites;
import p1.Evaluation;
import p1.HistoriqueUtilisateur;
import p1.Profil;
import p1.Administration;
import p1.Course;
import p1.Itineraire;
import p1.Planning;
import p1.Preferences;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> admins = new HashMap<>();
        HashMap<String, String> etu = new HashMap<>();
        HashMap<String, String> ens = new HashMap<>();
        HashMap<String, String> ats = new HashMap<>();
        List<Utilisateur> utilisateurs = new ArrayList<>();
        admins.put("232331531413", "nihel123");
        admins.put("232331531414", "nazim123");
        admins.put("232331531415", "malak123");
        admins.put("232331531416", "mouna123");
        Profil profil1 = new Profil();
        Profil profil2 = new Profil();
        Profil profil3 = new Profil();
        Utilisateur e = new Etudiant("leclerc", "charles", "3849", profil1, 2023, "informatique", "acad");
        etu.put("3849", "azerty");
        utilisateurs.add(e);
        Utilisateur en = new Enseignant("hamilton", "lewis", "2343", profil2, 2020, "informatique");
        ens.put("2343", "azerty");
        utilisateurs.add(en);
        Utilisateur at = new ATS("Wolf", "toto", "1293", profil3, 2021, "chef departement");
        ats.put("1293", "azerty");
        utilisateurs.add(at);
        boolean continuer = true;

        while (continuer) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Connexion Utilisateur");
            System.out.println("2. Connexion Administrateur");
            System.out.println("3. Quitter");
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
            case 1: 
                System.out.println("1. Connexion");
                System.out.println("2. Inscription");
                System.out.print("Votre choix : ");
                int sousChoix = scanner.nextInt();
                System.out.println("Choisissez votre type :");
            	System.out.println("1. Ãtudiant");
            	System.out.println("2. Enseignant");
            	System.out.println("3. ATS");
            	int choixtype = scanner.nextInt();
                scanner.nextLine();
                if (sousChoix == 1) { // Connexion
                    System.out.print("Entrez votre matricule : ");
                    String matricule = scanner.nextLine();
                    if (etu.containsKey(matricule)) {
                        System.out.print("Entrez votre mot de passe : ");
                        String mdp = scanner.nextLine();
                        if (choixtype ==1) {
                        if (etu.get(matricule).equals(mdp)) {
                            System.out.println("Connexion rÃ©ussie !");
                            Utilisateur user = trouverUtilisateur(utilisateurs,matricule);
                            menuutilisateur(user);
                            
                        } else {
                            System.out.println("Mot de passe incorrect.");
                        }}
                        else if (choixtype == 2) {
                            if (ens.get(matricule).equals(mdp)) {
                                System.out.println("Connexion rÃ©ussie !");
                                Utilisateur user = trouverUtilisateur(utilisateurs,matricule);
                                menuutilisateur(user);
                            } else {
                                System.out.println("Mot de passe incorrect.");
                            }}
                       else if (choixtype == 3) {
                                if (ats.get(matricule).equals(mdp)) {
                                    System.out.println("Connexion rÃ©ussie !");
                                    Utilisateur user = trouverUtilisateur(utilisateurs,matricule);
                                    menuutilisateur(user);
                                } else {
                                    System.out.println("Mot de passe incorrect.");
                                }}
                    }
                     else { System.out.println("Matricule inconnu. Veuillez vous inscrire.");} 
                    }
                   else if (sousChoix == 2) { // Inscription
                    System.out.print("Entrez votre nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez votre prÃ©nom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Entrez votre matricule : ");
                    String matricule = scanner.nextLine();

                    if (etu.containsKey(matricule) && ens.containsKey(matricule) && ats.containsKey(matricule))  {
                        System.out.println("Ce matricule est dÃ©jÃ  utilisÃ©.");
                    } else {
                        System.out.print("Entrez votre mot de passe : ");
                        String mdp = scanner.nextLine();
                        if (choixtype ==1) {
                        	System.out.print("Entrez votre annee d'admission : ");
                            int anneeadmission = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Entrez votre faculte : ");
                            String faculte = scanner.nextLine();
                            System.out.print("Entrez votre specialite : ");
                            String specialite = scanner.nextLine();
                            Profil profil = new Profil(); 
                            Utilisateur nouveletudiant = new Etudiant(nom, prenom, matricule, profil,anneeadmission,faculte,specialite);
                            etu.put(matricule, mdp);
                            utilisateurs.add(nouveletudiant);
                        }
                        else if (choixtype == 2) {
                        	System.out.print("Entrez votre annee de recrutement : ");
                            int anneerecrutement = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Entrez votre faculte : ");
                            String faculte = scanner.nextLine();
                            Profil profil = new Profil(); 
                            Utilisateur nouvelEnseignant = new Enseignant(nom, prenom, matricule, profil,anneerecrutement,faculte);
                            ens.put(matricule, mdp);
                            utilisateurs.add(nouvelEnseignant);
                            
                        }
                        else if (choixtype == 3) {
                        	System.out.print("Entrez votre annee de recrutement : ");
                            int anneerecrutement = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Entrez votre servicerattachement : ");
                            String servicerattachement = scanner.nextLine();
                            Profil profil = new Profil(); 
                            Utilisateur nouvelATS = new ATS(nom, prenom, matricule, profil,anneerecrutement,servicerattachement);
                            ats.put(matricule, mdp);
                            utilisateurs.add(nouvelATS);
                        }
                    System.out.println("Inscription rÃ©ussie !");}
                } 
                  else {System.out.println("Choix invalide."); }
                break;
                case 2:
                    System.out.print("ID admin : ");
                    String idAdmin = scanner.nextLine();
                    
                    System.out.print("Mot de passe : ");
                    String mdpAdmin = scanner.nextLine();
                    if (admins.containsKey(idAdmin) && admins.get(idAdmin).equals(mdpAdmin)) {
                        System.out.println("Connexion administrateur rÃ©ussie.");
                        menuAdministrateur();
                    } else {
                        System.out.println("Identifiants administrateur incorrects.");
                    }
                    break;

                case 3:
                    continuer = false;
                    System.out.println("Fermeture de l'application.");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
            scanner.close();
    }
    
    public static Utilisateur trouverUtilisateur(List<Utilisateur> utilisateurs, String matricule) {
        
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getMatricule().equals(matricule)) {
                return utilisateur;}}
        return null;}
    public static void menuutilisateur(Utilisateur utilisateur) {
        
    }

    public static void menuAdministrateur() {
        System.out.println("Menu Administrateur - en dÃ©veloppement");
    }
}
