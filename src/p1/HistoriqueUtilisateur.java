package p1;

import monprojet.enums.Statut;
import monprojet.enums.TypeCourse;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueUtilisateur {
    private List<Itineraire> anciensItineraires;
    private List<Statut> anciensStatuts;
    private List<TypeCourse> anciensTypesCourse;
    private List<Evaluation> evaluations;
    private List<Course> courses; 

    public HistoriqueUtilisateur() {
        this.anciensItineraires = new ArrayList<>();
        this.anciensStatuts = new ArrayList<>();
        this.anciensTypesCourse = new ArrayList<>();
        this.evaluations = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void ajouterCourse(Course course) {
        if (course != null) {
            courses.add(course);  // Ajoute la course à la liste
        }
    }

    public void ajouterItineraire(Itineraire itineraire) {
        if (itineraire != null)
            anciensItineraires.add(itineraire);
    }

    public void ajouterStatut(Statut statut) {
        if (statut != null)
            anciensStatuts.add(statut);
    }

    public void ajouterTypeCourse(TypeCourse type) {
        if (type != null)
            anciensTypesCourse.add(type);
    }

    public void ajouterEvaluation(Evaluation evaluation) {
        if (evaluation != null)
            evaluations.add(evaluation);
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void afficherHistorique() {
        System.out.println("â ItinÃ©raires prÃ©cÃ©dents â");
        for (Itineraire itin : anciensItineraires) {
            itin.afficherItineraire();
        }

        System.out.println("â Statuts prÃ©cÃ©dents â");
        for (Statut s : anciensStatuts) {
            System.out.println("- " + s);
        }

        System.out.println("â Types de course prÃ©cÃ©dents â");
        for (TypeCourse t : anciensTypesCourse) {
            System.out.println("- " + t);
        }

        System.out.println("â Ãvaluations reÃ§ues â");
        for (Evaluation e : evaluations) {
            System.out.println("- CritÃ¨re : " + e.getCritereEvaluation() +
                   " | Note : " + e.getNoteGlobale() +
                   " | Commentaire : " + e.getCommentaire() );

        }
        System.out.println("â Courses passées â");
        for (Course course : courses) {
            course.afficherDetails(); // Affiche les détails de chaque course dans l'historique
        }
    }
}