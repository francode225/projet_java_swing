import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Main extends JFrame {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        Acceuil acceuil = new Acceuil();
        acceuil.setVisible(true);
    }
}