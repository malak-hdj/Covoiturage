package p0;
import p1.Profil;

public class ATS extends Utilisateur {
    private int anneeRecrutement;
    private String serviceRattachement;

    public ATS(String nom, String prenom, String matricule, Profil profil,
               int anneeRecrutement, String serviceRattachement) {
        super(nom, prenom, matricule, profil);
        this.anneeRecrutement = anneeRecrutement;
        this.serviceRattachement = serviceRattachement;
    }

    public void setAnneeRecrutement(int anneeRecrutement) {
    	if (anneeRecrutement > 0) {this.anneeRecrutement = anneeRecrutement;
        System.out.println("Annéé de recrutement changée avec succées ! ");}}
    
    public void setServiceRattachement(String serviceRattachement) {
    	if (serviceRattachement != null) {this.serviceRattachement = serviceRattachement;
        System.out.println("Service de Rattachement changée avec succées ! ");}}

    @Override
    public void afficherProfil() {
        super.afficherProfil();
        System.out.println("ATS - Année de recrutement : " + anneeRecrutement);
        System.out.println("Service : " + serviceRattachement);
    }
   
    public int getAnneeRecrutement() {
        return anneeRecrutement;}
    public String getServiceRattachement() {
        return serviceRattachement;}
}