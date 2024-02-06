package sk.uniza.fri.dostal.vynimky;
/**
 * Trieda OhrozenyKralException je vlastna kontrolovana vynimka
 *
 * @author Juraj Dostál
 */
public class OhrozenyKralException extends Exception {
    private final String text;

    /**
     * Konstruktor na vytvorenie vynimky.
     *
     * @param text
     */
    public OhrozenyKralException(String text) {
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
