package dev.danvega.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.Duration;
import java.time.LocalDateTime;

public record Run( //naainn das ist record Klasse wieso kann man nciht einfach normale klssen verwednen warm hat jeder ne behinderung ich chekc das nicht suhct euch hobbies
                  //chatgpt meint Ein record erzeugt automatisch:
                   //
                   //Konstruktor
                   //
                   //Getter für alle Felder
                   //
                   //equals(), hashCode() und toString()
                   //
                   //➡️ Du brauchst das alles nicht selbst zu schreiben.
        Integer id,  //lowkey warum hat run was in klmmer vllt weil kein konstruktor gerbaucht wird kp
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location
) {

    public Run { //ist das jetzt ein konstruktor?
        if (!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("Completed On must be after Started On");
        }
    }

    public Duration getDuration() {
        return Duration.between(startedOn,completedOn);
    } //und was machen die hier? oder sind die hier für die geschwindigkeit beim rennen aber wo gibt aman das an
    // ahhhh ich chekc das icst glaubn ich in diesen klammer ja ergibt doch sinn stabil ex kann damit leben easyyyyy

    public Integer getAvgPace() {
        return Math.toIntExact(getDuration().toMinutes() / miles);
    } //un dhier kriegt man davon die geschiwndigkeit

}
