package sk.uniza.fri.dostal.figurky;

import sk.uniza.fri.dostal.hernaDoska.Sachovnica;
import sk.uniza.fri.dostal.vynimky.FigurkaNemozeUrobitTahException;
/**
 * Trieda Veza je potomok abstraktnej triedy Figurka, po ktorej dedi metody.
 * Je to jedna z figuriek v sachu.
 *
 * @author Juraj Dostál
 */
public class Veza extends Figurka {

    private boolean prvyPohyb;

    /**
     * Konstruktor na vytvorenie instancie potomkou
     *
     * @param kamen
     */
    public Veza(Kamen kamen) {
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
        return "Veža";
    }

    /**
     * Metoda, ktora vracia cestu k obrazku danej figurke
     *
     * @return Cestu k obrazku
     */
    @Override
    public String getObrazok() {
        return "pics/" + super.getKamen().getText() + "/Rook.png";
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

        if (mojaX == cielX) {
            var pomY = mojaY;
            while (pomY > 0) {
                pomY--;
                if (pomY == cielY) {
                    if (sachovnica.volnePolicko(cielX, cielY)) {
                        this.prvyPohyb = false;
                        return true;
                    } else if (sachovnica.obsadenePolicko(cielX, cielY) && sachovnica.farbaFigurky(cielX, cielY) != this.getKamen()) {
                        this.prvyPohyb = false;
                        return true;
                    }
                    throw new FigurkaNemozeUrobitTahException("Veza nemoze vykonat tento tah!");
                } else if (sachovnica.obsadenePolicko(mojaX, pomY)) {
                    break;
                }
            }

            pomY = mojaY;
            while (pomY < 7) {
                pomY++;
                if (pomY == cielY) {
                    if (sachovnica.volnePolicko(cielX, cielY)) {
                        this.prvyPohyb = false;
                        return true;
                    } else if (sachovnica.obsadenePolicko(cielX, cielY) && sachovnica.farbaFigurky(cielX, cielY) != this.getKamen()) {
                        this.prvyPohyb = false;
                        return true;
                    }
                    throw new FigurkaNemozeUrobitTahException("Veza nemoze vykonat tento tah!");
                } else if (sachovnica.obsadenePolicko(mojaX, pomY)) {
                    break;
                }
            }

        } else if (mojaY == cielY) {
            var pomX = mojaX;
            while (pomX > 0) {
                pomX--;
                if (pomX == cielX) {
                    if (sachovnica.volnePolicko(cielX, cielY)) {
                        this.prvyPohyb = false;
                        return true;
                    } else if (sachovnica.obsadenePolicko(cielX, cielY) && sachovnica.farbaFigurky(cielX, cielY) != this.getKamen()) {
                        this.prvyPohyb = false;
                        return true;
                    }
                    throw new FigurkaNemozeUrobitTahException("Veza nemoze vykonat tento tah!");
                } else if (sachovnica.obsadenePolicko(pomX, mojaY)) {
                    break;
                }
            }

            pomX = mojaX;
            while (pomX < 7) {
                pomX++;
                if (pomX == cielX) {
                    if (sachovnica.volnePolicko(cielX, cielY)) {
                        this.prvyPohyb = false;
                        return true;
                    } else if (sachovnica.obsadenePolicko(cielX, cielY) && sachovnica.farbaFigurky(cielX, cielY) != this.getKamen()) {
                        this.prvyPohyb = false;
                        return true;
                    }
                    throw new FigurkaNemozeUrobitTahException("Veza nemoze vykonat tento tah!");
                } else if (sachovnica.obsadenePolicko(pomX, mojaY)) {
                    break;
                }
            }
        }
        throw new FigurkaNemozeUrobitTahException("Veza nemoze vykonat tento tah!");
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

        for (int i = pozX + 1; i < 8; i++) {
            if (sachovnica.obsadenePolicko(i, pozY)) {
                var pomFigurka = sachovnica.getFigurka(i, pozY);
                if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = pozX - 1; i >= 0; i--) {
            if (sachovnica.obsadenePolicko(i, pozY)) {
                var pomFigurka = sachovnica.getFigurka(i, pozY);
                if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = pozY + 1; i < 8; i++) {
            if (sachovnica.obsadenePolicko(pozX, i)) {
                var pomFigurka = sachovnica.getFigurka(pozX, i);
                if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                    return true;
                } else {
                    break;
                }
            }
        }
        for (int i = pozY - 1; i >= 0; i--) {
            if (sachovnica.obsadenePolicko(pozX, i)) {
                var pomFigurka = sachovnica.getFigurka(pozX, i);
                if (pomFigurka instanceof Kral && pomFigurka.getKamen() != this.getKamen()) {
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }
}
