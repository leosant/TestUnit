package site.softleo.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import site.softleo.utils.DataUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DiaSemanaMatcher extends TypeSafeMatcher<Date> {

    private Integer diaSemana;

    public DiaSemanaMatcher(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    @Override
    protected boolean matchesSafely(Date date) {
        return DataUtils.verificarDiaSemana(date, diaSemana);
    }

    @Override
    public void describeTo(Description description) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, diaSemana);
        String calendarExtenso = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR"));
        description.appendText(calendarExtenso);
    }
}
