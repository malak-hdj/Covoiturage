package p1;
import p0.Utilisateur;
import monprojet.enums.Statut;
import monprojet.enums.TypeCourse;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private static int compteur = 1;  // compteur global pour toutes les instances
	private String id; 
    private Utilisateur chauffeur;
    private List<Utilisateur> passagers;
    private Itineraire itineraire;
    private TypeCourse typeCourse;
    private Statut statut;
    protected int nombrePlacesDisponibles;

    public Course(Utilisateur chauffeur, Itineraire itineraire,TypeCourse typeCourse, int nombrePlacesDisponibles) {
    	this.id = "COURSE" + (compteur++); 
    	this.chauffeur = chauffeur;
        this.passagers = new ArrayList<>();
        this.itineraire = itineraire;
        this.statut = Statut.EN_COURS;
        this.typeCourse = typeCourse;
        this.nombrePlacesDisponibles = nombrePlacesDisponibles;}

    public boolean estDisponible() {
        return (statut == Statut.EN_COURS && passagers.size() < nombrePlacesDisponibles);}

    public boolean ajouterPassager(Utilisateur passager) {
        if (estDisponible()) {
            passagers.add(passager);
            System.out.println("Passager ajoute: " + passager.getNom());
            return true;
        } else {
            System.out.println("Impossible dâajouter le passager !");
            return false;
        }
    }

    public void retirerPassager(Utilisateur passager) {
        if (passagers.remove(passager)) {
            System.out.println("Passager retire : " + passager.getNom());
        } else {
            System.out.println("Ce passager nâest pas dans la course !");
        }
    }

    public void terminerCourse() {
        this.statut = Statut.TERMINEE;
        System.out.println("Course terminee.");
    }

    public int placesRestantes() {
        return nombrePlacesDisponibles - passagers.size();
    }

    public boolean correspond(Utilisateur passager) {
        Profil profilPassager = passager.getProfil();
        String arriveePassager = profilPassager.getItineraire().getPointArrivee();
        String arriveeChauffeur = itineraire.getPointArrivee();
        return arriveePassager.equals(arriveeChauffeur);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Statut getStatut() {
        return statut;}

    public List<Utilisateur> getPassagers() {
        return passagers;}

    public Utilisateur getChauffeur() {
        return chauffeur;}

    
    public Itineraire getItineraire(){
        return itineraire;}

    public void afficherDetails() {
        System.out.println("========== Details de la Course ==========");
        System.out.println("ID : " + id);
        System.out.println("Chauffeur : " + chauffeur.getNom());
        System.out.println("Type de course : " + typeCourse);
        itineraire.afficherItineraire();
        System.out.println("Passagers : ");
        for (Utilisateur p : passagers) {
            System.out.println(" - " + p.getNom());
        }
        System.out.println("Places restantes : " + placesRestantes());
        System.out.println("Statut : " + statut);
    }
}