package sk.uniza.fri.dostal.figurky;

import sk.uniza.fri.dostal.hernaDoska.Sachovnica;
import sk.uniza.fri.dostal.vynimky.FigurkaNemozeUrobitTahException;

/**
 * Trieda Pesiak je potomok abstraktnej triedy Figurka, po ktorej dedi metody.
 * Je to jedna z figuriek v sachu.
 *
 * @author Juraj Dostál
 */

public class Pesiak extends Figurka {

    private boolean prvyPohyb;

    /**
     * Konstruktor na vytvorenie instancie potomkou
     *
     * @param kamen
     */
    public Pesiak(Kamen kamen) {
        super(kamen);
        this.prvyPohyb = true;
    }

    /**
     * Metoda vracia nazov figurky
     *
     * @return String
     */
    @Override
    public String getNazov() {
        return "Pešiak";
    }

    /**
     * Metoda, ktora vracia cestu k obrazku danej figurke
     *
     * @return Cestu k obrazku
     */
    @Override
    public String getObrazok() {
        return "pics/" + super.getKamen().getText() + "/Pawn.png";
    }

    /**
     * Metoda, ci dana Figurka moze vykonat dany pohyb
     *
     * @param sachovnica
     * @param mojaPozicia
     * @param cielenaPozicia
     * @return
     * @throws Exception
     */
    @Override
    public boolean mozemUrobitPohyb(Sachovnica sachovnica, int[] mojaPozicia, int[] cielenaPozicia) throws FigurkaNemozeUrobitTahException {
        int mojaX = mojaPozicia[0];
        int mojaY = mojaPozicia[1];

        int cielX = cielenaPozicia[0];
        int cielY = cielenaPozicia[1];

        if (this.getKamen() == Kamen.BIELY) {
            if (mojaY == cielY) {
                if (this.prvyPohyb) {
                    if (mojaX + 2 == cielX && sachovnica.volnePolicko(cielX, cielY) && sachovnica.volnePolicko(cielX - 1, cielY)) {
                        this.prvyPohyb = false;
                        return true;
                    }
                }
                if (mojaX + 1 == cielX && sachovnica.volnePolicko(cielX, cielY) ) {
                    this.prvyPohyb = false;
                    return true;
                }
            } else if (mojaY + 1 == cielY || mojaY - 1 == cielY) {
                if (mojaX + 1 == cielX) {

                    if (sachovnica.obsadenePolicko(cielX, cielY) && (sachovnica.farbaFigurky(cielX, cielY) != this.getKamen())) {
                        this.prvyPohyb = false;
                        return true;
                    }
                }

            }

        } else {
            if (mojaY == cielY) {
                if (this.prvyPohyb) {
                    if (mojaX - 2 == cielX && sachovnica.volnePolicko(cielX, cielY) && sachovnica.volnePolicko(cielX + 1, cielY)) {
                        this.prvyPohyb = false;
                        return true;
                    }
                }
                if (mojaX - 1 == cielX && sachovnica.volnePolicko(cielX, cielY) ) {
                    this.prvyPohyb = false;
                    return true;
                }
            } else if (mojaY + 1 == cielY || mojaY - 1 == cielY) {
                if (mojaX - 1 == cielX) {
                    if (sachovnica.obsadenePolicko(cielX, cielY) && (sachovnica.farbaFigurky(cielX, cielY) != this.getKamen())) {
                        this.prvyPohyb = false;
                        return true;
                    }
                }
            }
        }

        throw new FigurkaNemozeUrobitTahException("Tento tah pesiak nemoze urobit");
    }

    /**
     * Metoda, zistenie ci je kral ohrozeny
     *
     * @param sachovnica
     * @param pozicia
     * @return boolean
     */
    @Override
    public boolean ohrozujeKrala(Sachovnica sachovnica, int[] pozicia) {
        var pozX = pozicia[0];
        var pozY = pozicia[1];
        if (this.getKamen() == Kamen.BIELY) {
            if (pozX + 1 < 8) {
                if (pozY + 1 < 8) {
                    if (sachovnica.obsadenePolicko(pozX + 1, pozY + 1)) {
                        var pomFigurka = sachovnica.getFigurka(pozX + 1, pozY + 1);
                        if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                            return true;
                        }
                    }
                }
                if (pozY - 1 > -1) {
                    if (sachovnica.obsadenePolicko(pozX + 1, pozY - 1)) {
                        var pomFigurka = sachovnica.getFigurka(pozX + 1, pozY - 1);
                        if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                            return true;
                        }
                    }
                }
            }
        } else {
            if (pozX - 1 > -1) {
                if (pozY + 1 < 8) {
                    if (sachovnica.obsadenePolicko(pozX - 1, pozY + 1)) {
                        var pomFigurka = sachovnica.getFigurka(pozX - 1, pozY + 1);
                        if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                            return true;
                        }
                    }
                }
                if (pozY - 1 > -1) {
                    if (sachovnica.obsadenePolicko(pozX - 1, pozY - 1)) {
                        var pomFigurka = sachovnica.getFigurka(pozX - 1, pozY - 1);
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
