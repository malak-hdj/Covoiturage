package p2;

import java.util.Scanner;
import p0.Utilisateur;
import p1.Administration;
import monprojet.enums.Statut;
import p1.Course;
import p1.Itineraire;
import monprojet.enums.TypeCourse;

public class MenuAdministrateur {

    public static void lancerMenu(Administration admin) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
        	System.out.println("=====Menu Administrateur====");
            System.out.println("1. Ajouter un utilisateur");
            System.out.println("2. bannir un utilisateur");
            System.out.println("3. Lister les utilisateurs");
            System.out.println("4. Rechercher un utilisateur (par nom ou matricule)");
            System.out.println("5. Filtrer les utilisateurs (par type et réputation)");
            System.out.println("6. Voir les évaluations d’un utilisateur");
            System.out.println("7. Voir toutes les courses");
            System.out.println("8. Ajouter une course");
            System.out.println("9. Supprimer une course");
            System.out.println("10. Afficher l’historique");
            System.out.println("11. Afficher les statistiques");
            System.out.println("12. Modifier un utilisateur");
            System.out.println("13. Afficher la liste noire");
            System.out.println("0. Se déconnecter");
            System.out.print("Choix : ");

            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un nombre.");
                scanner.nextLine();
            }
            choix = scanner.nextInt();
            scanner.nextLine(); 


            switch (choix) {
            case 1:
            	Utilisateur u = InscriptionUtilisateur.inscrire(admin, scanner);
            if (u != null) {
                System.out.println("Utilisateur ajouté avec succès.");
            } else {
                System.out.println("Échec de l'ajout de l'utilisateur.");
            }
            break;
                case 2:
                	System.out.print("Matricule de l'utilisateur à bannir : ");
                	String mat1 = scanner.nextLine();
                	Utilisateur uASupprimer = admin.rechercherUtilisateurParMatricule(mat1);
                	if (uASupprimer != null) {
                	    admin.banirUtilisateur(mat1); 
                	    System.out.println(" Utilisateur " + uASupprimer.getNom() + " (matricule: " + mat1 + ") a été banni avec succès.");
                	} else {
                	    System.out.println(" Aucun utilisateur trouvé avec le matricule : " + mat1);
                	}
            
                    break;

                case 3:
                    admin.afficherUtilisateurs();
                    break;

                case 4:
                    System.out.print("Nom ou matricule : ");
                    String id = scanner.nextLine();
                    Utilisateur u1 = admin.rechercherUtilisateurParNom(id);
                    if (u1 == null) u1 = admin.rechercherUtilisateurParMatricule(id);
                    System.out.println(u1 != null ? "Utilisateur trouvé : " + u1.getNom() : "Aucun utilisateur trouvé.");
                    break;

                case 5:
                    System.out.print("Réputation min : ");
                    double rep = scanner.nextDouble();
                    scanner.nextLine();
                    admin.filtrerUtilisateursParReputation(rep);
                    break;

                case 6:
                    System.out.print("Nom utilisateur : ");
                    String nomEval = scanner.nextLine();
                    Utilisateur uEval = admin.rechercherUtilisateurParNom(nomEval);
                    if (uEval != null) admin.afficherEvaluations(uEval);
                    else System.out.println("Utilisateur non trouvé.");
                    break;

                case 7:
                    admin.afficherCoursesParStatut(Statut.EN_COURS);
                    admin.afficherCoursesParStatut(Statut.TERMINEE);
                    break;

                case 8:
                    System.out.println("=== Ajouter une Course ===");
                    
                    System.out.print("ID de la course : ");
                    String idCourse = scanner.nextLine();

                    System.out.print("Matricule du chauffeur : ");
                    String matriculeChauffeur = scanner.nextLine();
                    Utilisateur chauffeur = admin.rechercherUtilisateurParMatricule(matriculeChauffeur);

                    if (chauffeur == null) {
                        System.out.println("❌ Chauffeur introuvable.");
                        break;
                    }

                    System.out.print("Point de départ : ");
                    String depart = scanner.nextLine();

                    System.out.print("Point d'arrivée : ");
                    String arrivee = scanner.nextLine();

                    System.out.print("Type de course (ALLER / RETOUR / ALLER_RETOUR) : ");
                    String typeStr = scanner.nextLine().toUpperCase();

                    TypeCourse typecourse = TypeCourse.valueOf(typeStr.toUpperCase());

                    System.out.print("Nombre de places disponibles : ");
                    int places = scanner.nextInt();
                    scanner.nextLine();

                    Itineraire itineraire = new Itineraire(depart, arrivee);
                    Course nouvelleCourse = new Course(chauffeur, itineraire, typecourse, places);
                    
                    admin.ajouterCourse(nouvelleCourse);
                    System.out.println("✅ Course ajoutée avec succès !");
                    break;

                case 9:
                    System.out.println("=== Supprimer une Course ===");
                    System.out.print("ID de la course à supprimer : ");
                    String idASupprimer = scanner.nextLine();

                    Course courseTrouvee = admin.getCourses().stream()
                        .filter(c -> c.getId().equalsIgnoreCase(idASupprimer))
                        .findFirst()
                        .orElse(null);

                    if (courseTrouvee != null) {
                        admin.supprimerCourse(courseTrouvee);
                        System.out.println("✅ Course supprimée avec succès.");
                    } else {
                        System.out.println("❌ Aucune course trouvée avec cet ID.");
                    }
                    break;
                case 10:
                    System.out.print("Nom utilisateur : ");
                    String nomHist = scanner.nextLine();
                    Utilisateur uHist = admin.rechercherUtilisateurParNom(nomHist);
                    if (uHist != null) admin.consulterHistorique(uHist);
                    else System.out.println("Utilisateur non trouvé.");
                    break;

                case 11:
                    admin.afficherStatistiquesGlobales();
                    break;

                case 12:
                    System.out.print("Matricule utilisateur à modifier : ");
                    String mat = scanner.nextLine();
                    Utilisateur uModif = admin.rechercherUtilisateurParMatricule(mat);
                    if (uModif != null) {
                        System.out.println("1. Nom\n2. Prénom\n3. Réputation\n4. Retour");
                        System.out.print("Choix : ");
                        int cm = scanner.nextInt();
                        scanner.nextLine();
                        switch (cm) {
                            case 1:
                                System.out.print("Nouveau nom : ");
                                uModif.setNom(scanner.nextLine());
                                break;
                            case 2:
                                System.out.print("Nouveau prénom : ");
                                uModif.setPrenom(scanner.nextLine());
                                break;
                            case 3:
                                System.out.print("Nouvelle réputation : ");
                                uModif.setReputation(scanner.nextDouble());
                                scanner.nextLine();
                                break;
                            default:
                                System.out.println("Retour.");
                        }
                    } else {
                        System.out.println("Utilisateur non trouvé.");
                    }
                    break;
                case 13:
                	admin.afficherListeNoire();
                	break;

                case 0:
                    System.out.println("Déconnexion...");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }

        } while (choix != 0);
    }
}
