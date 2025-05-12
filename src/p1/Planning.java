package p1;
import java.util.ArrayList;
import java.util.List;
import monprojet.enums.*;
import p0.Utilisateur;


public class Planning {
    private List<Course> coursesEnCours;
    private List<Course> coursesAvenir;
    private List<Course> historiqueCourses;

    public Planning() {
        this.coursesEnCours = new ArrayList<>();
        this.coursesAvenir = new ArrayList<>();
        this.historiqueCourses = new ArrayList<>();}

    public void ajouterCourseAvenir(Course course) {
        coursesAvenir.add(course);
        System.out.println("Course ajoutee au planning à venir");}

    public void terminerCourse(Course course) {
        course.terminerCourse();
        coursesEnCours.remove(course);
        historiqueCourses.add(course);
        System.out.println("Course terminee et ajoutee à l’historique");}

    public void afficherPlanningJournalier(JourSemaine jour) {
        System.out.println("Courses prévues pour le jour : " + jour);
        for (Course c : coursesAvenir) {
            Utilisateur chauffeur = c.getChauffeur();
            if (chauffeur.getProfil().getDisponibilites().getJoursDisponibles().contains(jour)) {
                c.afficherDetails();}}}



    public void afficherPlanningHebdomadaire(String semaine) {
        System.out.println("Planning hebdomadaire : " + semaine);
        for (Course c : coursesAvenir) {
            c.afficherDetails();
        }
        if (historiqueCourses.isEmpty()) {
            System.out.println("Aucune course enregistree dans l’historique.");
        } else {
            for (Course course : historiqueCourses) {
                course.afficherDetails();
            }}
    }

    public void rechercherCourseParCritere(String pointDepart, String pointArrivee) {
                boolean courseTrouvee = false;
                for (Course course : coursesAvenir) {
                    if (course.getItineraire().getPointDepart().equals(pointDepart) && 
                        course.getItineraire().getPointArrivee().equals(pointArrivee) && 
                        course.estDisponible()) {
                        course.afficherDetails();
                        courseTrouvee = true;
                    }
                }
        
                if (!courseTrouvee) {
                    System.out.println("Aucune course à venir disponible pour ces critères.");
                }
    }



    public List<Course> getCoursesEnCours() {
        return coursesEnCours;
    }

    public List<Course> getCoursesAvenir() {
        return coursesAvenir;
    }

    public List<Course> getHistoriqueCourses() {
        return historiqueCourses;
    }
}
