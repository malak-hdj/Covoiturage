package p1;
import monprojet.enums.*;
import java.time.LocalDateTime;
public class Profil {
    private StatutUser statut;
    private TypeCourse typeCourse;
    private Itineraire itineraire;
    private Preferences preferences; 
    private Disponibilites disponibilites;
    
    public Profil() {}
    public Profil(StatutUser statut, TypeCourse typeCourse, Itineraire itineraire,
    		Preferences preferences, Disponibilites disponibilites) {
        this.statut = statut;
        this.typeCourse = typeCourse;
        this.itineraire = itineraire;
        this.preferences = preferences;
        this.disponibilites = disponibilites;}
    
    public void setStatut(StatutUser statut) {
        this.statut = statut;}

    public void setTypeCourse(TypeCourse typeCourse) {
        this.typeCourse = typeCourse;}

    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;}

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;}

    public void setDisponibilites(Disponibilites disponibilites) {
        this.disponibilites = disponibilites;}
    
    public void modifierDisponibilites(Disponibilites nouvellesDisponibilites) {
        this.disponibilites = nouvellesDisponibilites;}
    
    public boolean estDisponible(LocalDateTime dateHeure) {
        JourSemaine jour = JourSemaine.from(dateHeure.getDayOfWeek());
        return disponibilites.getDisponibilitesParJour().containsKey(jour);
    }
    
    public void afficherProfildynamique() {
        System.out.println("Profil {");
        System.out.println("  Statut: " + statut);
        System.out.println("  Type de course: " + typeCourse);
         //itineraire.afficherItineraire();
         if (itineraire != null) {
            System.out.print("  ");
            itineraire.afficherItineraire();
        } else {
            System.out.println("  Itinéraire: Non défini");
        }
         //preferences.afficherPreferences();
         if (preferences != null) {
            System.out.print("  ");
            preferences.afficherPreferences();
        } else {
            System.out.println("  Préférences: Non définies");
        }
        
        //disponibilites.afficherDisponibilites();
         if (disponibilites != null) {
            System.out.print("  ");
            disponibilites.afficherDisponibilites();
        } else {
            System.out.println("  disponibilitees: Non définies");
        }
        
        System.out.println("}");
    }
    
    public void modifierProfilDynamique(StatutUser statut, TypeCourse typeCourse, Itineraire itineraire,
    		Preferences preferences, Disponibilites disponibilites) {
              if (statut != null) {this.statut = statut;}
              if (typeCourse != null) {this.typeCourse = typeCourse;}
              if (itineraire != null) {this.itineraire = itineraire;}
              if (preferences != null) {this.preferences = preferences;}
              if (disponibilites != null) {this.disponibilites = disponibilites;}}


    // Getters (si besoin)
    public StatutUser getStatut() { return statut; }
    public TypeCourse getTypeCourse() { return typeCourse; }
    public Itineraire getItineraire() { return itineraire; }
    public Preferences getPreferences() {return preferences;}
    public Disponibilites getDisponibilites() { return disponibilites; }
}
