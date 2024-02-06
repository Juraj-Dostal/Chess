package sk.uniza.fri.dostal.hernaDoska;

import sk.uniza.fri.dostal.hodiny.Casovac;
import sk.uniza.fri.dostal.figurky.Kamen;
import sk.uniza.fri.dostal.figurky.Dama;
import sk.uniza.fri.dostal.figurky.Kral;
import sk.uniza.fri.dostal.figurky.Strelec;
import sk.uniza.fri.dostal.figurky.Jazdec;
import sk.uniza.fri.dostal.figurky.Veza;
import sk.uniza.fri.dostal.figurky.Pesiak;
import sk.uniza.fri.dostal.figurky.Figurka;
import sk.uniza.fri.dostal.vynimky.FigurkaNemozeUrobitTahException;
import sk.uniza.fri.dostal.vynimky.OhrozenyKralException;
import sk.uniza.fri.dostal.vynimky.ProtihracJeNaTahuException;

import javax.swing.JOptionPane;


/**
 * Trieda Sachovnica, ktora vytvara sachovnicu a osluhuje ju.
 *
 * @author Juraj Dostál
 */

public class Sachovnica {
    private Policko[][] sachovnica;
    private Kamen naTahu;
    private Casovac casovac;
    private boolean ohrozujeKrala;

    /**
     * Konstruktor, ktory vytvori zaklad sachovnice
     *
     * @param casovac
     */
    public Sachovnica(Casovac casovac) {
        this.sachovnica = new Policko[8][8];
        this.naTahu = Kamen.BIELY;
        this.casovac = casovac;
        this.ohrozujeKrala = false;

        for (int i = 0; i < this.sachovnica.length; i++) {
            for (int j = 0; j < this.sachovnica[i].length; j++) {
                if ((i % 2) == 0) {
                    if ((j % 2) == 0) {
                        this.sachovnica[i][j] = new Policko(i, j, "orange");
                    } else {
                        this.sachovnica[i][j] = new Policko(i, j, "white");
                    }
                } else {
                    if ((j % 2) == 0) {
                        this.sachovnica[i][j] = new Policko(i, j, "white");
                    } else {
                        this.sachovnica[i][j] = new Policko(i, j, "orange");
                    }
                }
            }
        }

    }

    /**
     * Motoda na rozlozenie sachovnice
     */
    public void nastavRozlozenieFiguriek() {
        //biele figurky
        this.sachovnica[0][0].polozFigurku(new Veza(Kamen.BIELY));
        this.sachovnica[0][1].polozFigurku(new Jazdec(Kamen.BIELY));
        this.sachovnica[0][2].polozFigurku(new Strelec(Kamen.BIELY));
        this.sachovnica[0][3].polozFigurku(new Dama(Kamen.BIELY));
        this.sachovnica[0][4].polozFigurku(new Kral(Kamen.BIELY));
        this.sachovnica[0][5].polozFigurku(new Strelec(Kamen.BIELY));
        this.sachovnica[0][6].polozFigurku(new Jazdec(Kamen.BIELY));
        this.sachovnica[0][7].polozFigurku(new Veza(Kamen.BIELY));
        for (int i = 0; i < this.sachovnica.length; i++) {
            this.sachovnica[1][i].polozFigurku(new Pesiak(Kamen.BIELY));
        }

        //cierne figurky
        this.sachovnica[7][0].polozFigurku(new Veza(Kamen.CIERNY));
        this.sachovnica[7][1].polozFigurku(new Jazdec(Kamen.CIERNY));
        this.sachovnica[7][2].polozFigurku(new Strelec(Kamen.CIERNY));
        this.sachovnica[7][3].polozFigurku(new Dama(Kamen.CIERNY));
        this.sachovnica[7][4].polozFigurku(new Kral(Kamen.CIERNY));
        this.sachovnica[7][5].polozFigurku(new Strelec(Kamen.CIERNY));
        this.sachovnica[7][6].polozFigurku(new Jazdec(Kamen.CIERNY));
        this.sachovnica[7][7].polozFigurku(new Veza(Kamen.CIERNY));
        for (int i = 0; i < this.sachovnica.length; i++) {
            this.sachovnica[6][i].polozFigurku(new Pesiak(Kamen.CIERNY));
        }
    }

    /**
     * Metoda ktora vykonava presun Figurky
     * Kontroluje ci sa presun moze vykonat
     *
     * @param suradnicePrvehoPolicka
     * @param suradniceDruhehoPolicka
     * @throws Exception
     */

    /**
     * Metoda ktora vykonava presun Figurky
     * Kontroluje ci sa presun moze vykonat
     *
     * @param suradnicePrvehoPolicka
     * @param suradniceDruhehoPolicka
     * @throws ProtihracJeNaTahuException
     * @throws OhrozenyKralException
     * @throws FigurkaNemozeUrobitTahException
     */
    public void presunFigurku(int[] suradnicePrvehoPolicka, int[] suradniceDruhehoPolicka) throws ProtihracJeNaTahuException, OhrozenyKralException, FigurkaNemozeUrobitTahException {
        var figurka = this.sachovnica[suradnicePrvehoPolicka[0]][suradnicePrvehoPolicka[1]].getFigurka();


        if (figurka == null) {
            throw new IllegalArgumentException("Nenachadza sa figurka na policku");
        }

        var pozX = suradniceDruhehoPolicka[0];
        var pozY = suradniceDruhehoPolicka[1];
        if (this.naTahu != figurka.getKamen()) {
            throw new ProtihracJeNaTahuException("Na tahu je " + this.naTahu.getText());
        }

        if (this.ohrozujeKrala) {
            if (!(figurka instanceof Kral)) {
                throw new OhrozenyKralException("Musis tahat kralom"); // nevyhadzuje vznimku
            }
        }
        this.ohrozujeKrala = false;

        //algoritmus na vytvorenie rosady
        if (this.sachovnica[suradniceDruhehoPolicka[0]][suradniceDruhehoPolicka[1]].getFigurka() != null) {
            var pomFigurka = this.sachovnica[suradniceDruhehoPolicka[0]][suradniceDruhehoPolicka[1]].getFigurka();
            if (pomFigurka.getKamen() == figurka.getKamen() && pomFigurka instanceof Veza veza && figurka instanceof Kral kral) {
                boolean mozeUrobitRoradu = true;

                var kralSuradnicaX = suradnicePrvehoPolicka[0];
                var kralSuradnicaY = suradnicePrvehoPolicka[1];
                var vezaSuradnicaX = suradniceDruhehoPolicka[0];
                var vezaSuradnicaY = suradniceDruhehoPolicka[1];

                if (veza.getPrvyPohyb() && kral.getPrvyPohyb()) {
                    if (vezaSuradnicaY > kralSuradnicaY) {
                        for (int i = kralSuradnicaY + 1; i < vezaSuradnicaY; i++) {
                            if (this.obsadenePolicko(kralSuradnicaX, i)) {
                                mozeUrobitRoradu = false;
                            }
                        }
                        if (mozeUrobitRoradu) {
                            this.sachovnica[kralSuradnicaX][kralSuradnicaY + 2].polozFigurku(this.sachovnica[kralSuradnicaX][kralSuradnicaY].zoberFigurku());
                            this.sachovnica[vezaSuradnicaX][vezaSuradnicaY - 2].polozFigurku(this.sachovnica[vezaSuradnicaX][vezaSuradnicaY].zoberFigurku());
                            kral.spravilSaPrvyPohyb();
                            veza.spravilSaPrvyPohyb();

                            this.zmenNaTahu();
                            return;
                        }
                    } else {
                        for (int i = vezaSuradnicaY + 1; i < kralSuradnicaY; i++) {
                            if (this.obsadenePolicko(vezaSuradnicaX, i)) {
                                mozeUrobitRoradu = false;
                            }
                        }
                        if (mozeUrobitRoradu) {
                            this.sachovnica[kralSuradnicaX][kralSuradnicaY - 2].polozFigurku(this.sachovnica[kralSuradnicaX][kralSuradnicaY].zoberFigurku());
                            this.sachovnica[vezaSuradnicaX][vezaSuradnicaY + 3].polozFigurku(this.sachovnica[vezaSuradnicaX][vezaSuradnicaY].zoberFigurku());
                            kral.spravilSaPrvyPohyb();
                            veza.spravilSaPrvyPohyb();

                            this.zmenNaTahu();
                            return;
                        }
                    }
                }
            }
        }


        if (figurka.mozemUrobitPohyb(this, suradnicePrvehoPolicka, suradniceDruhehoPolicka)) {
            //vymena pesiaka za inu figurku
            if (figurka instanceof Pesiak && (pozX == 0 || pozX == 7)) {
                this.sachovnica[suradnicePrvehoPolicka[0]][suradnicePrvehoPolicka[1]].zoberFigurku();
                this.sachovnica[pozX][pozY].polozFigurku(this.vyberFigurku());
            } else {
                this.sachovnica[pozX][pozY].polozFigurku(this.sachovnica[suradnicePrvehoPolicka[0]][suradnicePrvehoPolicka[1]].zoberFigurku());
            }
            this.zmenNaTahu();
            this.ohrozujeKrala = figurka.ohrozujeKrala(this, suradniceDruhehoPolicka);
        }
    }

    /**
     * Metoda zistuje ci dane policko je volne
     *
     * @param pozX
     * @param pozY
     * @return
     */
    public boolean volnePolicko(int pozX, int pozY) {
        return !(this.sachovnica[pozX][pozY].obsahujeFigurku());
    }

    /**
     * Metoda zistuje ci dane policko je obsadene
     *
     * @param pozX
     * @param pozY
     * @return boolean
     */
    public boolean obsadenePolicko(int pozX, int pozY) {
        return this.sachovnica[pozX][pozY].obsahujeFigurku();
    }

    /**
     * Metoda na zistenie figurky
     *
     * @param pozX
     * @param pozY
     * @return Figurka
     */
    public Figurka getFigurka(int pozX, int pozY) {
        return this.sachovnica[pozX][pozY].getFigurka();
    }

    /**
     * Metoda na zistenie farby danej Figurky
     *
     * @param pozX
     * @param pozY
     * @return Kamen
     * @throws NullPointerException
     */
    public Kamen farbaFigurky(int pozX, int pozY) throws NullPointerException {
        return this.sachovnica[pozX][pozY].farbaFigurky();
    }

    private void zmenNaTahu() {
        switch (this.naTahu) {
            case BIELY:
                this.naTahu = Kamen.CIERNY;
                break;
            case CIERNY:
                this.naTahu = Kamen.BIELY;
                break;
        }
        this.casovac.zmenHraca(this.naTahu);
    }

    private Figurka vyberFigurku() {
        String[] moznosti = {"Dama", "Veza", "Strelec", "Jazdec"};
        int nFig = JOptionPane.showOptionDialog(null, "Vyber si ktoru figurku chces.", "Vymen pesiaka", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, moznosti, moznosti[0]);
        switch (nFig) {
            case 0 -> {
                return new Dama(this.naTahu);
            }
            case 1 -> {
                return new Veza(this.naTahu);
            }
            case 2 -> {
                return new Strelec(this.naTahu);
            }
            case 3 -> {
                return new Jazdec(this.naTahu);
            }
        }
        return new Dama(this.naTahu);
    }
}
