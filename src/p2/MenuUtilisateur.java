package p2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import monprojet.enums.*;
import p0.Utilisateur;
import p1.*;

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
                    utilisateur.modifierProfilPersonnel(nom, prenom, null);
                
                    System.out.print("Souhaitez-vous modifier votre profil dynamique ? (1-Oui / 2-Non) : ");
                    String reponse = scanner.nextLine();
                
                    if (reponse.equals("1")) {
                        // Choix du statut
                        System.out.println("Choisissez un statut :");
                        System.out.println("1. CHAUFFEUR");
                        System.out.println("2. PASSAGER");
                        System.out.print("Votre choix (laisser vide pour ignorer) : ");
                        String statutChoix = scanner.nextLine();
                        StatutUser statut = null;
                        if (statutChoix.equals("1")) statut = StatutUser.chauffer;
                        else if (statutChoix.equals("2")) statut = StatutUser.passager;
                
                        // Choix du type de course
                        System.out.println("Choisissez un type de course :");
                        System.out.println("1. ALLER");
                        System.out.println("2. RETOUR");
                        System.out.println("3. ALLER_RETOUR");
                        System.out.print("Votre choix (laisser vide pour ignorer) : ");
                        String typeChoix = scanner.nextLine();
                        TypeCourse typeCourse = null;
                        if (typeChoix.equals("1")) typeCourse = TypeCourse.ALLER_SIMPLE;
                        else if (typeChoix.equals("2")) typeCourse = TypeCourse.RETOUR_SIMPLE;
                        else if (typeChoix.equals("3")) typeCourse = TypeCourse.ALLER_RETOUR;
                
                        // Départ et arrivée
                        System.out.print("Départ (laisser vide pour ignorer) : ");
                        String depart = scanner.nextLine();
                        System.out.print("Arrivée (laisser vide pour ignorer) : ");
                        String arrivee = scanner.nextLine();
                        Itineraire itineraire = null;
                        if (!depart.isEmpty() || !arrivee.isEmpty()) {
                            itineraire = new Itineraire(depart, arrivee);
                        }
                
                        // Préférences
                        System.out.print("Souhaitez-vous définir vos préférences ? (1-Oui / 2-Non) : ");
                        String repPref = scanner.nextLine();
                        Preferences preferences = null;
                
                        if (repPref.equals("1")) {
                            preferences = new Preferences();
                            for (PreferenceType type : PreferenceType.values()) {
                                System.out.print("Acceptez-vous la préférence " + type + " ? (1-Oui / 2-Non) : ");
                                String rep = scanner.nextLine();
                                preferences.ajouterPreference(type, rep);
                            }
                        }
                
                        // Suppression de la disponibilité
                
                        utilisateur.getProfil().modifierProfilDynamique(statut, typeCourse, itineraire, preferences, null);
                        System.out.println("Profil dynamique mis à jour.");
                    }
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
                    scanner.nextLine();                
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
                        admin.ajouterCourse(course); // Ajouter cette ligne
                        System.out.println("Course créée avec succès !");
                    }
                
                break;
                case 5:
                System.out.print(" Entrez le point de départ : ");
                String pointDepart = scanner.nextLine().trim();
            
                System.out.print(" Entrez le point d'arrivée : ");
                String pointArrivee = scanner.nextLine().trim();
            
                if (pointDepart.isEmpty() || pointArrivee.isEmpty()) {
                    System.out.println(" Les champs de départ et d'arrivée ne doivent pas être vides.");
                    break;
                }
            
                List<Course> resultats = new ArrayList<>();
            
                for (Course c : admin.getCourses()) {
                    if (
                        c.getStatut() == Statut.EN_COURS &&
                        c.getItineraire().getPointDepart().equalsIgnoreCase(pointDepart) &&
                        c.getItineraire().getPointArrivee().equalsIgnoreCase(pointArrivee) &&
                        c.placesRestantes() > 0
                    ) {
                        resultats.add(c);
                    }
                }
            
                if (resultats.isEmpty()) {
                    System.out.println(" Aucune course disponible pour ces critères.");
                } else {
                    System.out.println("\n --- Courses disponibles ---");
                    for (Course c : resultats) {
                        System.out.println(" ID : " + c.getId());
                        System.out.println(" Chauffeur : " + c.getChauffeur().getNom());
                        System.out.println(" Type : " + c.getTypeCourse());
                        System.out.println(" Départ : " + c.getItineraire().getPointDepart());
                        System.out.println(" Arrivée : " + c.getItineraire().getPointArrivee());
                        System.out.println(" Places restantes : " + c.placesRestantes());
                        System.out.println("----------------------------");
                    }
                }
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
