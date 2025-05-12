package p2;
import java.util.*;

import monprojet.enums.*;
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

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> admins = new HashMap<>();
        HashMap<String, String> etu = new HashMap<>();
        HashMap<String, String> ens = new HashMap<>();
        HashMap<String, String> ats = new HashMap<>();
        
        admins.put("232331531413", "nihel123");
        admins.put("232331531414", "nazim123");
        admins.put("232331531415", "malak123");
        admins.put("232331531416", "mouna123");
        
        Profil profil1 = new Profil();
        Profil profil2 = new Profil();
        Profil profil3 = new Profil();
        Utilisateur e = new Etudiant("leclerc", "charles", "3849", profil1, 2023, "informatique", "acad");
        etu.put("3849", "azerty");
        Utilisateur en = new Enseignant("hamilton", "lewis", "2343", profil2, 2020, "informatique");
        ens.put("2343", "azerty");
        Utilisateur a = new ATS("Wolf", "toto", "1293", profil3, 2021, "chef departement");
        ats.put("1293", "azerty");
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
                            menuUtilisateur(e, null, scanner);
                        } else {
                            System.out.println("Mot de passe incorrect.");
                        }}
                        else if (choixtype == 2) {
                            if (ens.get(matricule).equals(mdp)) {
                                System.out.println("Connexion rÃ©ussie !");
                                menuUtilisateur(en, null, scanner);
                            } else {
                                System.out.println("Mot de passe incorrect.");
                            }}
                       else if (choixtype == 3) {
                                if (ats.get(matricule).equals(mdp)) {
                                    System.out.println("Connexion rÃ©ussie !");
                                    menuUtilisateur(a, null, scanner);
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
                        }
                        else if (choixtype == 3) {
                        	System.out.print("Entrez votre annee de recrutement : ");
                            int anneerecrutement = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Entrez votre servicerattachement : ");
                            String servicerattachement = scanner.nextLine();
                            Profil profil = new Profil(); 
                            Utilisateur nouvelEnseignant = new Enseignant(nom, prenom, matricule, profil,anneerecrutement,servicerattachement);
                            ats.put(matricule, mdp);
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
                        //menuAdministrateur();
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
    public static void menuUtilisateur(Utilisateur utilisateur, Administration admin, Scanner scanner) {
        boolean continuer = true;

        while (continuer) {

            System.out.println("\n--- Menu de " + utilisateur.getPrenom() + " " + utilisateur.getNom() + " ---");
            System.out.println("1. Afficher mes informations");
            System.out.println("2. Modifier mon profil");
            System.out.println("3. Consulter mon historique");
            System.out.println("4. Créer une course (en tant que chauffeur)");
            System.out.println("5. Rechercher une course (en tant que passager)");
            System.out.println("6. Gérer mes disponibilités");
            System.out.println("7. Se déconnecter");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    utilisateur.afficherProfil();;
                    break;

                case 2:
                System.out.println("Modifier mon profil");
                System.out.print("Modifier le nom (laisser vide pour ignorer) : ");
                String nom = scanner.nextLine();
                System.out.print("Modifier le prénom (laisser vide pour ignorer) : ");
                String prenom = scanner.nextLine();
                // Modification du profil avec les nouveaux paramètres
                utilisateur.modifierProfilPersonnel(nom, prenom, null);
                break;
                

                case 3:
                utilisateur.afficherHistorique(); 
                    break;
                case 4:
                System.out.print("Lieu de départ : ");
                String depart = scanner.nextLine();

                System.out.print("Lieu d'arrivée : ");
                String arrivee = scanner.nextLine();

                System.out.print("Nombre de places disponibles : ");
                int places = scanner.nextInt();
                scanner.nextLine(); // Clear newline

                System.out.println("Choisissez le type de course :");
                System.out.println("1. ALLER_SIMPLE");
                System.out.println("2. RETOUR_SIMPLE");
                System.out.println("3. ALLER_RETOUR");

                int choixType = scanner.nextInt();
                scanner.nextLine(); // Clear newline

                TypeCourse type;
                switch (choixType) {
                case 1 -> type = TypeCourse.ALLER_SIMPLE;
                case 2 -> type = TypeCourse.RETOUR_SIMPLE;
                case 3 -> type = TypeCourse.ALLER_RETOUR;
                 default -> {
                System.out.println("Choix invalide. Type par défaut : ALLER_SIMPLE.");
                 type = TypeCourse.ALLER_SIMPLE;
                 }
                }

               Itineraire itineraire = new Itineraire(depart, arrivee);
               Course course = new Course(utilisateur, itineraire, type, places);
               utilisateur.getHistorique().ajouterCourse(course);
               System.out.println("Course créée avec succès !");
    
                break;

                case 5:
                // Rechercher une course en tant que passager
                 System.out.println("Entrez le point de départ de la recherche :");
                scanner.nextLine(); // Pour consommer la nouvelle ligne laissée par nextInt
                String pointDepart = scanner.nextLine();

                System.out.println("Entrez le point d'arrivée de la recherche :");
                String pointArrivee = scanner.nextLine();

                // Recherche de la course dans le planning
                utilisateur.getPlanning().rechercherCourseParCritere(pointDepart, pointArrivee);  // Recherche dans le planning de l'utilisateur
                break;

                case 6:
                    boolean continuerGestion = true;
                    while (continuerGestion) {
                        System.out.println("\n--- Gérer mes disponibilités ---");
                        System.out.println("1. Afficher mes disponibilités");
                        System.out.println("2. Ajouter une disponibilité");
                        System.out.println("3. Supprimer une disponibilité");
                        System.out.println("4. Retour");
                        System.out.print("Choix : ");
                        int choixDispo = scanner.nextInt();
                        scanner.nextLine(); // vider la ligne
                
                        switch (choixDispo) {
                            case 1:
                                utilisateur.getDisponibilites().afficherDisponibilites();
                                break;
                
                            case 2:
                                System.out.println("Entrez le jour (ex: LUNDI, MARDI...) : ");
                                String jourAjoutStr = scanner.nextLine().toUpperCase();
                                JourSemaine jourAjout;
                                try {
                                    jourAjout = JourSemaine.valueOf(jourAjoutStr);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Jour invalide.");
                                    break;
                                }
                                System.out.print("Entrez le moment (ex: matin, après-midi, soir) : ");
                                String momentAjout = scanner.nextLine();
                                utilisateur.getDisponibilites().ajouterMomentDisponibilite(jourAjout, momentAjout);
                                System.out.println("Disponibilité ajoutée !");
                                break;
                
                            case 3:
                                System.out.println("Entrez le jour (ex: LUNDI, MARDI...) : ");
                                String jourSupprStr = scanner.nextLine().toUpperCase();
                                JourSemaine jourSuppr;
                                try {
                                    jourSuppr = JourSemaine.valueOf(jourSupprStr);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Jour invalide.");
                                    break;
                                }
                                System.out.print("Entrez le moment à supprimer : ");
                                String momentSuppr = scanner.nextLine();
                                utilisateur.getDisponibilites().supprimerMomentDisponibilite(jourSuppr, momentSuppr);
                                System.out.println("Disponibilité supprimée (si elle existait).");
                                break;
                
                            case 4:
                                continuerGestion = false;
                                break;
                
                            default:
                                System.out.println("Choix invalide.");
                        }
                    }
                    break;

                case 7:
                    System.out.println("DÃ©connexion rÃ©ussie.");
                    continuer = false;
                    break;

                

                default:
                    System.out.println("Choix invalide.");
            }}}


    public static void menuAdministrateur(Scanner scanner, Administration admin) {
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n=== Menu Administrateur ===");
            System.out.println("1. Lister tous les utilisateurs");
            System.out.println("2. Filtrer utilisateurs par type");
            System.out.println("3. Supprimer un utilisateur");
            System.out.println("4. Voir Ã©valuations dâun utilisateur");
            System.out.println("5. RÃ©initialiser rÃ©putation");
            System.out.println("6. Afficher les statistiques");
            System.out.println("7. Voir toutes les courses");
            System.out.println("8. Se dÃ©connecter");
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    admin.afficherUtilisateurs();
                    break;

                case 2:
                    System.out.println("1. Ãtudiant  2. Enseignant  3. ATS");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    switch (type) {
                        case 1: admin.filtrerUtilisateursParStatut(Etudiant.class); break;
                        case 2: admin.filtrerUtilisateursParStatut(Enseignant.class); break;
                        case 3: admin.filtrerUtilisateursParStatut(ATS.class); break;
                        default: System.out.println("Type invalide."); break;
                    }
                    break;

                case 3:
                    System.out.print("Matricule de l'utilisateur Ã  supprimer : ");
                    String matriculeSup = scanner.nextLine();
                    Utilisateur u = admin.rechercherUtilisateurParMatricule(matriculeSup);
                    if (u != null) {
                        admin.supprimerUtilisateur(u);
                        System.out.println("Utilisateur supprimÃ©.");
                    } else {
                        System.out.println("Utilisateur introuvable.");
                    }
                    break;

                case 4:
                    System.out.print("Matricule de l'utilisateur : ");
                    String matEval = scanner.nextLine();
                    Utilisateur uEval = admin.rechercherUtilisateurParMatricule(matEval);
                    if (uEval != null) {
                        admin.afficherEvaluations(uEval);
                    } else {
                        System.out.println("Utilisateur introuvable.");
                    }
                    break;

                case 5:
                    System.out.print("Matricule de l'utilisateur : ");
                    String matRep = scanner.nextLine();
                    Utilisateur uRep = admin.rechercherUtilisateurParMatricule(matRep);
                    if (uRep != null) {
                        admin.reinitialiserReputation(uRep);
                        System.out.println("RÃ©putation rÃ©initialisÃ©e.");
                    } else {
                        System.out.println("Utilisateur introuvable.");
                    }
                    break;

                case 6:
                    admin.afficherStatistiquesGlobales();
                    break;

                case 7:
                    admin.afficherCoursesParStatut(Statut.EN_COURS);
                    admin.afficherCoursesParStatut(Statut.TERMINEE);
                    break;

                case 8:
                    continuer = false;
                    System.out.println("DÃ©connexion...");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

}
