package p0;
import p1.Profil;
import p1.HistoriqueUtilisateur;
import p1.Planning;
import p1.Evaluation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects; // pour controller les null 
import monprojet.enums.CritereEvaluation;
import p1.Disponibilites;
import monprojet.enums.DisponibiliteType;




public abstract class Utilisateur {
	private String nom;
    private String prenom;
    private final String matricule;
    private double reputation;
    private List<String> commentaires;
    private Profil profil;
    private HistoriqueUtilisateur historique;
    private Disponibilites disponibilites;
    private Planning planning;

    
  public Utilisateur(String nom, String prenom, String matricule, Profil profil) {
    this.nom = Objects.requireNonNull(nom, "Le nom ne peut pas être null");
    this.prenom = Objects.requireNonNull(prenom, "Le prénom ne peut pas être null");
    this.matricule = Objects.requireNonNull(matricule, "Le matricule ne peut pas être null");
    this.profil = Objects.requireNonNull(profil, "Le profil ne peut pas être null");  // Validation sans copie
    this.reputation = 5.0;
    this.commentaires = new ArrayList<>();
    this.historique = new HistoriqueUtilisateur();
    this.disponibilites = new Disponibilites(DisponibiliteType.QUOTIDIEN);
    this.planning = new Planning();

}
  
    public void modifierProfilPersonnel(String nom,String prenom,Profil profil) {
    	    if (nom != null && !nom.isEmpty()) {this.nom = nom;}
    	    if (prenom != null && !prenom.isEmpty()) {this.prenom = prenom;}
    	    if (profil != null) {this.profil = profil;}}
    
    public void ajouterEvaluation(Evaluation evaluation) {
      if (evaluation != null && evaluation.getUtilisateurEvalue().equals(this)) {
        this.historique.ajouterEvaluation(evaluation);
        if (evaluation.getCommentaire() != null && !evaluation.getCommentaire().isEmpty()) {
            this.commentaires.add(evaluation.getCommentaire());}
         mettreAJourReputation();}}

    public void evaluerUtilisateur(Utilisateur utilisateur, CritereEvaluation critere, int note, String commentaire) {
        Evaluation evaluation = new Evaluation(this, utilisateur, note, critere, commentaire);
        utilisateur.ajouterEvaluation(evaluation);
    }
    
    private void mettreAJourReputation() {
      List<Evaluation> evaluations = historique.getEvaluations();
      if (evaluations == null || evaluations.isEmpty()) {
        this.reputation = 5.0;  // Note par défaut pour les nouveaux
        return;}
      int somme = 0;
      for (Evaluation e : evaluations) {
        somme += e.getNoteGlobale();}
  
      this.reputation = (double) somme / evaluations.size();
    }

    public void afficherProfil() {
        System.out.println("========== Affichage profil ==========");
        System.out.println("Nom: " + nom);
        System.out.println("Prénom: " + prenom);
        System.out.println("Matricule: " + matricule);
        System.out.println("Réputation: " + reputation);
        profil.afficherProfildynamique();
    }

    public void afficherHistorique() {
        System.out.println("========== Affichage Historique ==========");
        System.out.println("Historique de " + nom + " " + prenom + ":");
        historique.afficherHistorique();
    }
    
    public void ajouterCommentaire(String commentaire) {
        if (commentaire != null && !commentaire.isEmpty()) {
            commentaires.add(commentaire);
        }
    }

    //getteur if needed
    public List<String> getCommentaires() {
        return Collections.unmodifiableList(commentaires);
    }
    public Planning getPlanning(){
        return planning;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    
    public Profil getProfil() {
        return profil;}
    
    public String getMatricule() {
        return matricule;}
    
    public double getReputation() {
    	return reputation;}
    
    public HistoriqueUtilisateur getHistorique() {
    	return historique;}
    
    public void setPrenom(String prenom) {
        if (prenom != null && !prenom.isEmpty()) {this.prenom = prenom; System.out.println("Prenom changer avec succes");
         } else {System.out.println("Prénom invalide.");}}
    
    public void setNom(String nom) {
        if (nom != null && !nom.isEmpty()) {this.nom = nom; System.out.println("Nom changer avec succes");
         } else {System.out.println("Nom invalide.");}}
    
    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public void setReputation(double reputation) {
        this.reputation = reputation;
    }

    public Disponibilites getDisponibilites() {
        return disponibilites;
    }
    
    public void setDisponibilites(Disponibilites disponibilites) {
        this.disponibilites = disponibilites;
    }
    
}
