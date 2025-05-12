package monprojet.enums;

import java.time.DayOfWeek;

public enum JourSemaine {
    LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE;

    public static JourSemaine from(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return LUNDI;
            case TUESDAY: return MARDI;
            case WEDNESDAY: return MERCREDI;
            case THURSDAY: return JEUDI;
            case FRIDAY: return VENDREDI;
            case SATURDAY: return SAMEDI;
            case SUNDAY: return DIMANCHE;
            default: throw new IllegalArgumentException("Jour inconnu: " + dayOfWeek);
        }
    }
}
