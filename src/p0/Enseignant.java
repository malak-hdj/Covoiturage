package p0;
import p1.Profil;
public class Enseignant extends Utilisateur {
    private int anneeRecrutement;
    private String faculte;


    public Enseignant(String nom, String prenom, String matricule, Profil profil,
                      int anneeRecrutement, String faculte) {
        super(nom, prenom, matricule, profil);
        this.anneeRecrutement = anneeRecrutement;
        this.faculte = faculte; 
    }

    public void setFaculte(String faculte) {
        if (faculte != null && !faculte.isEmpty()) { this.faculte = faculte;
        System.out.println("Faculté changée avec succées ! ");}}
    
    public void setAnneeRecrutement(int anneeRecrutement) {
    	if (anneeRecrutement > 0) {this.anneeRecrutement = anneeRecrutement;
        System.out.println("Annéé de recrutement changée avec succées ! ");}}

    @Override
    public void afficherProfil() {
        super.afficherProfil();
        System.out.println("Enseignant - Année de recrutement : " + anneeRecrutement);
        System.out.println("Faculté : " + faculte);
    }
    
    public String getFaculte() {
    	return faculte;}
    
    public int getAnneeRecrutement() {
        return anneeRecrutement;}
    
}



