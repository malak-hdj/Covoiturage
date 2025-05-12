package p0;
import p1.Profil;
public class Etudiant extends Utilisateur {
    private int anneeAdmission;
    private String faculte;
    private String specialite;
   
    public Etudiant(String nom, String prenom, String matricule, Profil profil,
                    int anneeAdmission, String faculte, String specialite) {
        super(nom, prenom, matricule, profil);
        this.anneeAdmission = anneeAdmission;
        this.faculte = faculte;
        this.specialite = specialite;}

    //pour pouvoir modifier les informations personnels personaliser a l'etudiant
    public void setAnneeAdmission(int anneeAdmission) {
        if (anneeAdmission > 0) {this.anneeAdmission = anneeAdmission;
        System.out.println("Année d'admission changer avec succes ! ");}}

    public void setFaculte(String faculte) {
        if (faculte != null && !faculte.isEmpty()) { this.faculte = faculte;
        System.out.println("Faculté changée avec succées ! ");}}

    public void setSpecialite(String specialite) {
        if (specialite != null && !specialite.isEmpty()) {this.specialite = specialite;
        System.out.println("Specialité changée avec succes ! ");}}
    
    //si il souhaite modifier plusieurs informations a la fois 
    public void modifierProfil(int nouvelleAnneeAdmission, String nouvelleFaculte, String nouvelleSpecialite) {
        setAnneeAdmission(nouvelleAnneeAdmission); 
        setFaculte(nouvelleFaculte);              
        setSpecialite(nouvelleSpecialite);}


    @Override
    public void afficherProfil() {
        super.afficherProfil();
        System.out.println("Étudiant - Année d'admission : " + anneeAdmission);
        System.out.println("Faculté : " + faculte);
        System.out.println("Spécialité : " + specialite);}
    
    public String getFaculte() {return faculte;}
    public String getSpecialite() {return specialite;}
    public int getanneeAdmission() {return anneeAdmission;}
    
}

