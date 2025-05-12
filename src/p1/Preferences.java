package p1;

import monprojet.enums.PreferenceType;
import java.util.HashMap;
import java.util.Map;

public class Preferences {
    private Map<PreferenceType, String> preferences;

    public Preferences() {
        this.preferences = new HashMap<>();
    }

    public String getPreference(PreferenceType cle) {
        return preferences.get(cle);
    }

    public void ajouterPreference(PreferenceType cle, String valeur) {
        preferences.put(cle, valeur);
    }

    public void modifierPreference(PreferenceType cle, String nouvelleValeur) {
        if (preferences.containsKey(cle)) {
            preferences.put(cle, nouvelleValeur);
            System.out.println("Préférence modifiée : " + cle + " -> " + nouvelleValeur);
        } else {
            System.out.println("La préférence " + cle + " n'existe pas. Utilisez ajouterPreference() si besoin.");
        }
    }

    public void supprimerPreference(PreferenceType cle) {
        if (preferences.containsKey(cle)) {
            preferences.remove(cle);
            System.out.println("Préférence supprimée : " + cle);
        } else {
            System.out.println("Impossible de supprimer : la préférence " + cle + " n'existe pas.");
        }
    }

    public void afficherPreferences() {
        if (preferences.isEmpty()) {
            System.out.println("Aucune préférence définie.");
        } else {
            System.out.println("Préférences :");
            for (Map.Entry<PreferenceType, String> entry : preferences.entrySet()) {
                System.out.println("- " + entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    // Getter si jamais tu veux manipuler la map directement (optionnel)
    public Map<PreferenceType, String> getPreferencesMap() {
        return preferences;
    }
}
