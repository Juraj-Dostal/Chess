package sk.uniza.fri.dostal.figurky;

/**
 * Enum Kamen, sluzi na odlisenie hraca podla farby figuriek
 *
 * @author Juraj Dostál
 */

public enum Kamen {

    BIELY("biely"),
    CIERNY("cierny");

    private String text;

    /**
     * Konstruktor na nastavenie atributu text
     *
     * @param text
     */
    Kamen(String text) {
        this.text = text;
    }

    /**
     * Getter metoda
     *
     * @return textovy format farby
     */
    public String getText() {
        return this.text;
    }
}
