package p1;

import java.util.*;

        public class Itineraire {
        private String pointDepart;
        private String pointArrivee;
        private List<String> pointsIntermediaires;

   public Itineraire(String pointDepart, String pointArrivee) {
   this.pointDepart = pointDepart;
   this.pointArrivee = pointArrivee;
   this.pointsIntermediaires = new ArrayList<>();}

   public void ajouterPointArret(String point) {
   pointsIntermediaires.add(point);}

   public void supprimerPointArret(String point) {
   pointsIntermediaires.remove(point);}

   public void afficherItineraire() {
   System.out.println("Départ: " + pointDepart);
   for (String point : pointsIntermediaires) {
       System.out.println("Arrêt: " + point);}
   System.out.println("Arrivée: " + pointArrivee);}

   public int nombreTotalEtapes() {
   return 2 + pointsIntermediaires.size();}

      public String getPointDepart() { return pointDepart; }
      public void setPointDepart(String pointDepart) { this.pointDepart = pointDepart; }
      public String getPointArrivee() { return pointArrivee; }
      public void setPointArrivee(String pointArrivee) { this.pointArrivee = pointArrivee; }
      public List<String> getPointsIntermediaires() { return pointsIntermediaires; }
 }


