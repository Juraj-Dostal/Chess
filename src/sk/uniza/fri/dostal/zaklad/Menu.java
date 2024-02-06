package sk.uniza.fri.dostal.zaklad;

import sk.uniza.fri.dostal.hernaDoska.Sachovnica;
import sk.uniza.fri.dostal.hodiny.Casovac;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * Trieda menu, ktora tvori menu celej hry a spusta ju.
 *
 * @author Juraj Dostál
 */
public class Menu {
    private JFrame okno;
    private JPanel menu;
    private JComboBox zoznamCasov;
    private JPanel stred;
    private JLabel casText;
    private JButton hratBT;
    private JButton zrusBT;
    private JPanel spodok;
    private JPanel cas;
    private JPanel buttonPanel;

    public Menu() {

        this.okno = new JFrame("Šachy (Chess)");
        this.okno.setContentPane(this.menu);
        this.okno.pack();
        this.okno.setVisible(true);

        String[] moznostiCasov = {"1", "3", "5", "10", "15", "20", "30"};

        this.zoznamCasov.addItem("10");
        this.zoznamCasov.addItem("1");
        this.zoznamCasov.addItem("3");
        this.zoznamCasov.addItem("5");
        this.zoznamCasov.addItem("15");
        this.zoznamCasov.addItem("20");
        this.zoznamCasov.addItem("30");
        this.zoznamCasov.setEditable(false);

        this.hratBT.addActionListener(e -> {
            var hodiny = new Casovac(Integer.parseInt(String.valueOf(Menu.this.zoznamCasov.getSelectedItem())));
            hodiny.zacalaHra();

            var sachovnica = new Sachovnica(hodiny);
            var ovladanie = new Ovladanie(sachovnica);
            sachovnica.nastavRozlozenieFiguriek();
            Menu.this.okno.setVisible(false);
        });

        this.zrusBT.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(Menu.this.okno, "Chces naozaj skoncit", "Ukoncenie", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else {
                Menu.this.okno.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            }
        });

        this.okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
