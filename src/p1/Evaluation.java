package p1;

import monprojet.enums.CritereEvaluation;
import java.time.LocalDateTime;
import p0.Utilisateur;

public class Evaluation {
    private Utilisateur evaluateur;
    private Utilisateur utilisateurEvalue;
    private int noteGlobale;
    private CritereEvaluation critereEvaluation;
    private String commentaire;
    private LocalDateTime dateEvaluation;

    public Evaluation(Utilisateur evaluateur, Utilisateur utilisateurEvalue, int noteGlobale,
                      CritereEvaluation critereEvaluation, String commentaire) {
        if (noteGlobale < 1 || noteGlobale > 5) {
            throw new IllegalArgumentException("La note doit être entre 1 et 5");}
        this.evaluateur = evaluateur;
        this.utilisateurEvalue = utilisateurEvalue;
        this.noteGlobale = noteGlobale;
        this.critereEvaluation = critereEvaluation;
        this.commentaire = commentaire;
        this.dateEvaluation = LocalDateTime.now();
    }

    //getteur si besoin
    public Utilisateur getEvaluateur() {
        return evaluateur;
    }
    public Utilisateur getUtilisateurEvalue() {
        return utilisateurEvalue;
    }
    public int getNoteGlobale() {
        return noteGlobale;
    }
    public CritereEvaluation getCritereEvaluation() {
        return critereEvaluation;
    }
    public String getCommentaire() {
        return commentaire;
    }
    public LocalDateTime getDateEvaluation() {
        return dateEvaluation;
    }
}
