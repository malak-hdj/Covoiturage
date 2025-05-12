package p2;

import java.util.Scanner;

import monprojet.enums.DisponibiliteType;
import monprojet.enums.JourSemaine;
import monprojet.enums.TypeCourse;
import p0.Utilisateur;
import p1.Administration;
import p1.Course;
import p1.Itineraire;

public class MenuUtilisateur {


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
                    scanner.nextLine();
                
                    System.out.println("Choisir le type de course :");
                    System.out.println("1. ALLER_SIMPLE");
                    System.out.println("2. RETOUR_SIMPLE");
                    System.out.println("3. ALLER_RETOUR");
                    System.out.print("Choix : ");
                    int typeChoix = scanner.nextInt();
                    scanner.nextLine(); // vider le buffer
                
                    TypeCourse type = null;
                    switch (typeChoix) {
                        case 1: type = TypeCourse.ALLER_SIMPLE; break;
                        case 2: type = TypeCourse.RETOUR_SIMPLE; break;
                        case 3: type = TypeCourse.ALLER_RETOUR; break;
                        default:
                            System.out.println("Choix invalide.");
                            break;
                    }
                
                    if (type != null) {
                        Itineraire itineraire = new Itineraire(depart, arrivee);
                        Course course = new Course(utilisateur, itineraire, type, places);
                        utilisateur.getHistorique().ajouterCourse(course);
                        System.out.println("Course créée avec succès !");
                    }
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
        System.out.println("4. Modifier le type de disponibilité");
        System.out.println("5. Retour");
        System.out.print("Choix : ");
        int choixDispo = scanner.nextInt();
        scanner.nextLine(); // vider la ligne

        switch (choixDispo) {
            case 1:
                utilisateur.getDisponibilites().afficherDisponibilites();
                break;

            case 2:
                System.out.print("Entrez le jour (ex: LUNDI, MARDI...) : ");
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
                System.out.print("Entrez le jour (ex: LUNDI, MARDI...) : ");
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
                System.out.println("Type actuel : " + utilisateur.getDisponibilites().getType());
                System.out.println("Types disponibles :");
                for (DisponibiliteType t : DisponibiliteType.values()) {
                    System.out.println("- " + t);
                }
                System.out.print("Entrez le nouveau type : ");
                String typeStr = scanner.nextLine().toUpperCase();
                try {
                    DisponibiliteType nouveauType = DisponibiliteType.valueOf(typeStr);
                    utilisateur.getDisponibilites().setType(nouveauType);
                    System.out.println("Type de disponibilité mis à jour !");
                } catch (IllegalArgumentException e) {
                    System.out.println("Type invalide.");
                }
                break;

            case 5:
                continuerGestion = false;
                break;

            default:
                System.out.println("Choix invalide.");
        }
    }
    break;

                case 7:
                    System.out.println("Deconnexion reussie.");
                    System.out.println("A tres bientot !.");
                    
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }}}
}