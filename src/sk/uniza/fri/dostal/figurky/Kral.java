package sk.uniza.fri.dostal.figurky;

import sk.uniza.fri.dostal.hernaDoska.Sachovnica;
import sk.uniza.fri.dostal.vynimky.FigurkaNemozeUrobitTahException;

/**
 * Trieda Kral je potomok abstraktnej triedy Figurka, po ktorej dedi metody.
 * Je to jedna z figuriek v sachu.
 *
 * @author Juraj Dostál
 */

public class Kral extends Figurka {

    private boolean prvyPohyb;

    /**
     * Konstruktor na vytvorenie instancie potomkou
     *
     * @param kamen
     */
    public Kral(Kamen kamen) {
        super(kamen);
        this.prvyPohyb = true;
    }

    public boolean getPrvyPohyb() {
        return this.prvyPohyb;
    }

    public void spravilSaPrvyPohyb() {
        this.prvyPohyb = false;
    }

    /**
     * Metoda vracia nazov figurky
     *
     * @return String
     */
    @Override
    public String getNazov() {
        return "Kráľ";
    }

    /**
     * Metoda, ktora vracia cestu k obrazku danej figurke
     *
     * @return Cestu k obrazku
     */
    @Override
    public String getObrazok() {
        return "pics/" + super.getKamen().getText() + "/King.png";
    }

    /**
     * Metoda, ci dana Figurka moze vykonat dany pohyb
     *
     * @param sachovnica
     * @param mojaPozicia
     * @param cielenaPozicia
     * @return
     * @throws FigurkaNemozeUrobitTahException
     */
    @Override
    public boolean mozemUrobitPohyb(Sachovnica sachovnica, int[] mojaPozicia, int[] cielenaPozicia) throws FigurkaNemozeUrobitTahException {
        int mojaX = mojaPozicia[0];
        int mojaY = mojaPozicia[1];

        int cielX = cielenaPozicia[0];
        int cielY = cielenaPozicia[1];

        int rozdielX = mojaX - cielX;
        int rozdielY = mojaY - cielY;
        if ((-2 < rozdielX && rozdielX < 2) && (-2 < rozdielY && rozdielY < 2)) {
            if (sachovnica.volnePolicko(cielX, cielY)) {
                this.prvyPohyb = false;
                return true;
            } else if (sachovnica.obsadenePolicko(cielX, cielY) && sachovnica.farbaFigurky(cielX, cielY) != this.getKamen()) {
                this.prvyPohyb = false;
                return true;
            }
        }
        throw new FigurkaNemozeUrobitTahException("Kral nemoze vykonat tento tah!");
    }

    /**
     * Metoda, zistenie ci je kral ohrozeny
     *
     * @param sachovnica
     * @param pozicia
     * @return
     */
    @Override
    public boolean ohrozujeKrala(Sachovnica sachovnica, int[] pozicia) {
        var pozX = pozicia[0];
        var pozY = pozicia[1];

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (pozX + i > -1 && pozX + i < 8 && pozY + j > -1 && pozY + j < 8) {
                    if (sachovnica.obsadenePolicko(pozX + i, pozY + j)) {
                        var pomFigurka = sachovnica.getFigurka(pozX + i, pozY + j);
                        if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
