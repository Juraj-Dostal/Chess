package sk.uniza.fri.dostal.hodiny;

import fri.shapesge.Stvorec;

/**
 * Trieda Displej vytvára displej na zobrazovanie minut a sekund, pomocou triedy SedemSegmentovyDisplej.
 * 
 * @author Juraj Dostál
 */
public class Displej {
    
    private SedemSegmentovyDisplej[] displejMinuty;
    private SedemSegmentovyDisplej[] displejSekundy;
    private int minuty;
    private int sekundy;
    
    /**
     * Konštruktor vytvára tri sedem segmentové displeje.
     */

    /**
     * Konstruktor vytvarajuci displej
     *
     * @param polohaX
     * @param polohaY
     */
    public Displej(int polohaX, int polohaY) {
        this.displejMinuty = new SedemSegmentovyDisplej[2];
        this.displejSekundy = new SedemSegmentovyDisplej[2];

        this.minuty = 0;
        this.sekundy = 0;

        var pozX = polohaX;

        for (int i = 0; i < this.displejMinuty.length; i++) {
            this.displejMinuty[i] = new SedemSegmentovyDisplej(50, pozX, polohaY, 5, 15, 0);
            pozX += 30;
        }
        pozX += 15;
        for (int j = 1; j < 3; j++) {
            var bodka = new Stvorec(pozX, polohaY + (15 * j));
            bodka.zmenStranu(7);
            bodka.zmenFarbu("red");
            bodka.zobraz();
        }
        pozX += 5;

        for (int i = 0; i < this.displejMinuty.length; i++) {
            this.displejSekundy[i] = new SedemSegmentovyDisplej(50, pozX, polohaY, 5, 15, 0);
            pozX += 30;
        }
    }

    /**
     * Metóda nastavi cas podla parametrov.
     * @param minuty
     * @param sekundy
     */
    public void nastavCas(int minuty, int sekundy) {
        int minutyCislo;
        int sekundyCislo;
        if (-1 < minuty && minuty < 100) {
            minutyCislo = minuty;
        } else if (minuty > 99) {
            minutyCislo = 99;
        } else {
            minutyCislo = 0;
        }

        if (-1 < sekundy && sekundy < 100) {
            sekundyCislo = sekundy;
        } else if (sekundy > 99) {
            sekundyCislo = 99;
        } else {
            sekundyCislo = 0;
        }
        
        for (int i = 0; i < this.displejMinuty.length; i++) {
            int delitel = 1;
            for (int index = 0; index < this.displejMinuty.length - i - 1; index++) {
                delitel *= 10;
            }
            this.displejMinuty[i].zobrazCislo(minutyCislo % (10 * delitel) / delitel);
        }
        for (int i = 0; i < this.displejSekundy.length; i++) {
            int delitel = 1;
            for (int index = 0; index < this.displejSekundy.length - i - 1; index++) {
                delitel *= 10;
            }
            this.displejSekundy[i].zobrazCislo(sekundyCislo % (10 * delitel) / delitel);
        }
    }

}
