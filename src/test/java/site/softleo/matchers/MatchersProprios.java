package site.softleo.matchers;

import java.util.Calendar;

public class MatchersProprios {

    public static DiaSemanaMatcher caiEm(Integer diaSemana) {
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiNumaTerca() {
        return new DiaSemanaMatcher(Calendar.TUESDAY);
    }
}
