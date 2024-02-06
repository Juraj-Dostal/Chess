package sk.uniza.fri.dostal.vynimky;
/**
 * Trieda ProtihracJeNaTahuException je vlastna kontrolovana vynimka
 *
 * @author Juraj Dostál
 */
public class ProtihracJeNaTahuException extends Exception {
    private final String text;

    /**
     * Konstruktor na vytvorenie vynimky.
     *
     * @param text
     */
    public ProtihracJeNaTahuException(String text) {
        this.text = text;
    }

    /**
     * Metoda na ziskanie popisu vynimky
     *
     * @return popisu vynimky
     */
    @Override
    public String getMessage() {
        return this.text;
    }
}
