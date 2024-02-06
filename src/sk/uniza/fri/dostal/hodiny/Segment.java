package sk.uniza.fri.dostal.hodiny;

import fri.shapesge.Obdlznik;
/**
 *
 * Segment využíva obdĺžnik pomocou, ktorého sa vytvára jeden segment zo sedem segmentového displeja.
 * 
 * @author Juraj Dostál
 * !!! robené na cvičení ale upravovane
 */
public class Segment {
    
    private Obdlznik segment;
    private int x;
    private int y;
    private String farba;
    private static final String FARBA_POZADIA = "lightgray";
    
    /**
     * Konštruktor nastavuje jednotlivé atribúti.
     */
    public Segment() {
        this.farba = null;
        this.segment = new Obdlznik();
        this.x = 0;
        this.y = 0;
        this.segment.posunVodorovne(-60);
        this.segment.posunZvisle(-50);
    }

    /**
     * Metóda nastavuje pozíciu segmentu.
     *
     * @param polohaX
     * @param polohaY
     */
    public void nastavPoziciu(int polohaX, int polohaY) {
        this.segment.posunVodorovne(-this.x);
        this.segment.posunZvisle(-this.y);
        this.x = polohaX;
        this.y = polohaY;
        this.segment.posunVodorovne(this.x);
        this.segment.posunZvisle(this.y);
    }

    /**
     * Metóda nastavuje veľkosť segmentu.
     *
     * @param dlzka
     * @param sirka
     */
    public void nastavVelkost(int dlzka, int sirka) {
        this.segment.zmenStrany(dlzka, sirka);
    }
    /**
     * Metóda nastavuje farbu segmentu.
     *
     * @param farba
     */
    public void nastavFarbu(String farba) {
        this.farba = farba;
        this.segment.zmenFarbu(this.farba);
    }
    
    /**
     * Metóda zobrazí segment.
     */
    public void zapni() {
        this.segment.zmenFarbu(this.farba);
        this.segment.zobraz();
    }
    
    /**
     * Metóda skryje segment. Je to na farbu pozadia, z dôvodu, že ShapesGE vymaže objekt a nejde ho znova zobraziť.
     */
    public void vypni() {
        this.segment.zmenFarbu(this.FARBA_POZADIA);
    }
    
    /**
     * Metóda vymaže segment.
     */
    public void vymaz() {
        this.segment.skry();
    }
}
