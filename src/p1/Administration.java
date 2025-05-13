package p1;

import java.util.*;
import java.util.stream.Collectors;
import p0.*;
import monprojet.enums.Statut;

public class Administration {
	private List<Utilisateur> utilisateurs;
	private List<Etudiant> etudiants;
    private List<Enseignant> enseignants;
    private List<ATS> ats;
    private List<Utilisateur> administrateurs;
    private List<Course> courses;
    private Set<String> listeNoire;

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    public static boolean estAdmin(String identifiant, String motDePasse) {
        return ADMIN_USERNAME.equals(identifiant) && ADMIN_PASSWORD.equals(motDePasse);
    }

    public Administration() {
        this.etudiants = new ArrayList<>();
        this.enseignants = new ArrayList<>();
        this.ats = new ArrayList<>();
        this.administrateurs = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.utilisateurs = new ArrayList<>();
        this.listeNoire = new HashSet<>();
    }


    public boolean ajouterUtilisateur(Utilisateur u) {
        if (listeNoire.contains(u.getMatricule())) {
            System.out.println(" L'utilisateur avec le matricule " + u.getMatricule() + " est banni et ne peut pas être inscrit.");
            return false;
        }

        if (u instanceof Etudiant) etudiants.add((Etudiant) u);
        else if (u instanceof Enseignant) enseignants.add((Enseignant) u);
        else if (u instanceof ATS) ats.add((ATS) u);
        utilisateurs.add(u);
        return true;
    }
    
    public boolean banirUtilisateur(String matricule) {
        Utilisateur u = rechercherUtilisateurParMatricule(matricule);
        if (u != null) {
            supprimerUtilisateur(u);
            listeNoire.add(matricule);
            return true;
        } else {
            System.out.println("Aucun utilisateur trouvé avec le matricule: " + matricule);
            return false;
        }
    }
    public void afficherListeNoire() {
        System.out.println(" Liste noire des utilisateurs bannis :");
        if (listeNoire.isEmpty()) {
            System.out.println("Aucun utilisateur banni.");
            return;}
        for (String matricule : listeNoire) {
            Utilisateur u = rechercherUtilisateurParMatricule(matricule);
            if (u != null) {
                System.out.println("- " + u.getNom() + " (Matricule : " + matricule + ")");
            } else {
                System.out.println("- Utilisateur inconnu (Matricule : " + matricule + ")");}}}
    public void supprimerUtilisateur(Utilisateur u) {
        etudiants.remove(u);
        enseignants.remove(u);
        ats.remove(u);
        administrateurs.remove(u);
        utilisateurs.remove(u);
    }
    public List<Course> rechercherCourseParItineraire(String depart, String arrivee) {
        return courses.stream()
            .filter(c -> c.getStatut() == Statut.EN_COURS) // Seulement les courses disponibles
            .filter(c -> c.getItineraire().getPointDepart().equalsIgnoreCase(depart.trim()))
            .filter(c -> c.getItineraire().getPointArrivee().equalsIgnoreCase(arrivee.trim()))
            .collect(Collectors.toList());
    }

    public Utilisateur rechercherUtilisateurParNom(String nom) {
        List<Utilisateur> tous = getTousUtilisateurs();
        for (Utilisateur u : tous) {
            if (u.getNom().equalsIgnoreCase(nom)) {
                System.out.println("Type : " + u.getClass().getSimpleName());
                return u;}
        }
        return null;
    }
    
    
    public Utilisateur rechercherUtilisateurParMatricule(String matricule) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getMatricule().equals(matricule)) {
                return utilisateur;
            }
        }
        return null; 
    }


    public void afficherUtilisateurs() {
        getTousUtilisateurs().forEach(u ->
            System.out.println(u.getNom() + " - Type: " + u.getClass().getSimpleName() + " - Reputation: " + u.getReputation()));
    }

    public void filtrerUtilisateursParReputation(double minReputation) {
        getTousUtilisateurs().stream()
            .filter(u -> u.getReputation() >= minReputation)
            .forEach(u -> System.out.println(u.getNom() + " - RÃ©putation: " + u.getReputation()));
    }

    public void filtrerUtilisateursParStatut(Class<?> type) {
        getTousUtilisateurs().stream()
            .filter(u -> type.isInstance(u))
            .forEach(u -> System.out.println(u.getNom() + " - RÃ©putation: " + u.getReputation()));
    }

    private List<Utilisateur> getTousUtilisateurs() {
        List<Utilisateur> tous = new ArrayList<>();
        tous.addAll(etudiants);
        tous.addAll(enseignants);
        tous.addAll(ats);
        tous.addAll(administrateurs);
        return tous;
    }

    public void ajouterCourse(Course c) {
        courses.add(c);
    }

    public void afficherCoursesParStatut(Statut statut) {
        courses.stream()
            .filter(c -> c.getStatut() == statut)
            .forEach(Course::afficherDetails);
    }

    public void supprimerCourse(Course c) {
        courses.remove(c);
    }

    public void affecterPassager(Course c, Utilisateur passager) {
        c.ajouterPassager(passager);
    }


    public void afficherEvaluations(Utilisateur u) {
        u.getHistorique().getEvaluations().forEach(System.out::println);
    }

    public void supprimerEvaluation(Utilisateur u, Evaluation e) {
        u.getHistorique().getEvaluations().remove(e);
    }

    public void reinitialiserReputation(Utilisateur u) {
        u.setReputation(0);
    }

    // 4. Suivi des historiques
    public void consulterHistorique(Utilisateur u) {
        u.getHistorique().afficherHistorique();
    }

    public void restaurerProfil(Utilisateur u, Profil ancienProfil) {
        u.setProfil(ancienProfil);
    }

    // 5. Statistiques globales
    public void afficherStatistiquesGlobales() {
        System.out.println("--- Statistiques Globales ---");
        System.out.println("Nombre total d'utilisateurs : " + getTousUtilisateurs().size());
        System.out.println("Nombre d'Ã©tudiants : " + etudiants.size());
        System.out.println("Nombre d'enseignants : " + enseignants.size());
        System.out.println("Nombre d'ATS : " + ats.size());
        System.out.println("Nombre d'administrateurs : " + administrateurs.size());
        System.out.println("Nombre de courses crÃ©Ã©es : " + courses.size());
        System.out.println("Nombre de courses en cours : " + nombreCoursesParStatut(Statut.EN_COURS));
        System.out.println("Nombre de courses terminÃ©es : " + nombreCoursesParStatut(Statut.TERMINEE));

        List<Utilisateur> top10 = getTousUtilisateurs().stream()
            .sorted((a, b) -> Double.compare(b.getReputation(), a.getReputation()))
            .limit(10)
            .collect(Collectors.toList());

        List<Utilisateur> pire10 = getTousUtilisateurs().stream()
            .sorted(Comparator.comparingDouble(Utilisateur::getReputation))
            .limit(10)
            .collect(Collectors.toList());

        System.out.println("Top 10 utilisateurs :");
        top10.forEach(u -> System.out.println(u.getNom() + " - RÃ©putation: " + u.getReputation()));

        System.out.println("Pire 10 utilisateurs :");
        pire10.forEach(u -> System.out.println(u.getNom() + " - RÃ©putation: " + u.getReputation()));
    }

    private int nombreCoursesParStatut(Statut statut) {
        return (int) courses.stream().filter(c -> c.getStatut() == statut).count();
    }
    
    public Course trouverCourseParId(String id) {
        return courses.stream()
                      .filter(c -> c.getId().equals(id))
                      .findFirst()
                      .orElse(null);
    }
    
    public List<Course> getCourses() {
        return courses;
    }
}
