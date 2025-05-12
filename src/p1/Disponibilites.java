package p1;
import java.util.*;
import monprojet.enums.JourSemaine;
import monprojet.enums.DisponibiliteType;


public class Disponibilites {
    private Map<JourSemaine, List<String>> disponibilitesParJour;
    private DisponibiliteType type;

    public Disponibilites(DisponibiliteType type) {
        this.disponibilitesParJour = new HashMap<>();
        this.type = type;
    }

    public void ajouterMomentDisponibilite(JourSemaine jour, String moment) {
        List<String> moments = disponibilitesParJour.get(jour);
        if (moments == null) {
            moments = new ArrayList<>(); 
            disponibilitesParJour.put(jour, moments);}
        moments.add(moment);}

    public void supprimerMomentDisponibilite(JourSemaine jour, String moment) {
        List<String> moments = disponibilitesParJour.get(jour);
        if (moments != null) {
            moments.remove(moment); 
            if (moments.isEmpty()) {
                disponibilitesParJour.remove(jour); 
            }}}

    public void afficherDisponibilites() {
        if (disponibilitesParJour.isEmpty()) {
            System.out.println("Aucune disponibilitÃ© dÃ©finie.");
            return;}
        System.out.println("DisponibilitÃ©s :");
        // Parcours des jours de la semaine et affichage des disponibilitÃ©s
        for (Map.Entry<JourSemaine, List<String>> entry : disponibilitesParJour.entrySet()) {
            System.out.println("Jour : " + entry.getKey()); // Affiche le jour
            List<String> moments = entry.getValue();
            if (moments.isEmpty()) {
                System.out.println("  Aucun moment disponible.");
            } else {
                System.out.println("  Moments disponibles :");
                for (String moment : moments) {
                    System.out.println("    - " + moment); // Affiche chaque moment pour le jour
                    }}}
    }

    public Map<JourSemaine, List<String>> getDisponibilitesParJour() {
        return disponibilitesParJour;
    }

    public void setDisponibilitesParJour(Map<JourSemaine, List<String>> disponibilitesParJour) {
        this.disponibilitesParJour = disponibilitesParJour;
    }

    public DisponibiliteType getType() {
        return type;
    }

    public void setType(DisponibiliteType type) {
        this.type = type;
    }
    
    public List<JourSemaine> getJoursDisponibles() {
        List<JourSemaine> joursDisponibles = new ArrayList<>();
        for (Map.Entry<JourSemaine, List<String>> entry : disponibilitesParJour.entrySet()) {
            joursDisponibles.add(entry.getKey());}
        return joursDisponibles;}
}

