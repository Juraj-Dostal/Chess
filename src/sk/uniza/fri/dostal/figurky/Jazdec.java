package sk.uniza.fri.dostal.figurky;

import sk.uniza.fri.dostal.hernaDoska.Sachovnica;
import sk.uniza.fri.dostal.vynimky.FigurkaNemozeUrobitTahException;

/**
 * Trieda Jazdec je potomok abstraktnej triedy Figurka, po ktorej dedi metody.
 * Je to jedna z figuriek v sachu.
 *
 * @author Juraj Dostál
 */

public class Jazdec extends Figurka {

    /**
     * Konstruktor na vytvorenie instancie potomkou
     *
     * @param kamen figurky
     */
    public Jazdec(Kamen kamen) {
        super(kamen);
    }

    /**
     * Metoda vracia nazov figurky
     *
     * @return String
     */
    @Override
    public String getNazov() {
        return "Jazdec";
    }

    /**
     * Metoda, ktora vracia cestu k obrazku danej figurke
     *
     * @return Cestu k obrazku
     */
    @Override
    public String getObrazok() {
        return "pics/" + super.getKamen().getText() + "/Knight.png";
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

        if (mojaX + 2 == cielX || mojaX - 2 == cielX) {
            if (mojaY + 1 == cielY) {
                if (sachovnica.volnePolicko(cielX, cielY)) {
                    return true;
                } else if (sachovnica.obsadenePolicko(cielX, cielY) && sachovnica.farbaFigurky(cielX, cielY) != this.getKamen()) {
                    return true;
                }
            } else if (mojaY - 1 == cielY) {
                if (sachovnica.volnePolicko(cielX, cielY)) {
                    return true;
                } else if (sachovnica.obsadenePolicko(cielX, cielY) && sachovnica.farbaFigurky(cielX, cielY) != this.getKamen()) {
                    return true;
                }
            }
        } else if (mojaY + 2 == cielY || mojaY - 2 == cielY) {
            if (mojaX + 1 == cielX) {
                if (sachovnica.volnePolicko(cielX, cielY)) {
                    return true;
                }  else if (sachovnica.obsadenePolicko(cielX, cielY) && sachovnica.farbaFigurky(cielX, cielY) != this.getKamen()) {
                    return true;
                }
            } else if (mojaX - 1 == cielX) {
                if (sachovnica.volnePolicko(cielX, cielY)) {
                    return true;
                }  else if (sachovnica.obsadenePolicko(cielX, cielY) && sachovnica.farbaFigurky(cielX, cielY) != this.getKamen()) {
                    return true;
                }
            }
        }

        throw new FigurkaNemozeUrobitTahException("Jazdec nemoze urobit tento tah");

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

        if (pozX + 2 < 8) {
            if (pozY + 1 < 8) {
                if (sachovnica.obsadenePolicko(pozX + 2, pozY + 1)) {
                    var pomFigurka = sachovnica.getFigurka(pozX + 2, pozY + 1);
                    if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                        return true;
                    }
                }
            }
            if (pozY - 1 >= 0) {
                if (sachovnica.obsadenePolicko(pozX + 2, pozY - 1)) {
                    var pomFigurka = sachovnica.getFigurka(pozX + 2, pozY - 1);
                    if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                        return true;
                    }
                }
            }
        }

        if (pozX - 2 >= 0) {
            if (pozY + 1 < 8) {
                if (sachovnica.obsadenePolicko(pozX - 2, pozY + 1)) {
                    var pomFigurka = sachovnica.getFigurka(pozX - 2, pozY + 1);
                    if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                        return true;
                    }
                }
            }
            if (pozY - 1 >= 0) {
                if (sachovnica.obsadenePolicko(pozX - 2, pozY - 1)) {
                    var pomFigurka = sachovnica.getFigurka(pozX - 2, pozY - 1);
                    if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                        return true;
                    }
                }
            }
        }

        if (pozY + 2 < 8) {
            if (pozX + 1 < 8) {
                if (sachovnica.obsadenePolicko(pozX + 1, pozY + 2)) {
                    var pomFigurka = sachovnica.getFigurka(pozX + 1, pozY + 2);
                    if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                        return true;
                    }
                }
            }
            if (pozX - 1 >= 0) {
                if (sachovnica.obsadenePolicko(pozX - 1, pozY + 2)) {
                    var pomFigurka = sachovnica.getFigurka(pozX - 1, pozY + 2);
                    if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                        return true;
                    }
                }
            }
        }

        if (pozY - 2 >= 0) {
            if (pozX + 1 < 8) {
                if (sachovnica.obsadenePolicko(pozX + 1, pozY - 2)) {
                    var pomFigurka = sachovnica.getFigurka(pozX + 1, pozY - 2);
                    if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                        return true;
                    }
                }
            }
            if (pozX - 1 >= 0) {
                if (sachovnica.obsadenePolicko(pozX - 1 , pozY - 2)) {
                    var pomFigurka = sachovnica.getFigurka(pozX - 1, pozY - 2);
                    if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
