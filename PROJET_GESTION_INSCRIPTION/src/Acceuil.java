import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Acceuil extends JFrame {
    JButton allerInscription, allerScolarite;
    JLabel lblTitre;

    public Acceuil() {
        super.setTitle("BIENVENUE SUR MIAGE INSCRIPTION");
        super.setSize(800, 450);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);
        panel.setBackground(new Color(0, 255, 180));

        lblTitre = new JLabel("MIAGE GESTION INSCRIPTION");
        lblTitre.setBounds(200, 10, 800, 30);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 35));
        lblTitre.setForeground(new Color(255, 255, 255));
        lblTitre.setBackground(new java.awt.Color(30, 147, 116, 160));
        panel.add(lblTitre);

        allerInscription = new JButton("INSCRIPTION");
        allerInscription.setBounds(190, 145, 180, 50);
        allerInscription.setFont(new Font("Arial", Font.BOLD, 16));
        allerInscription.setForeground(new Color(255, 255, 255, 225));
        allerInscription.setBackground(new java.awt.Color(255, 0, 136, 255));
        allerInscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnregistrementEtudiants enregistrementEtudiants = new EnregistrementEtudiants();
                enregistrementEtudiants.setVisible(true);
            }
        });
        panel.add(allerInscription);

        // ajout image miage
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("E:/MIAGE/LICENCE 2/BASE DE DONNEES ET APPLICATIONS/TD/PROJET_GESTION_INSCRIPTION/miage.jpg");
        Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        imageLabel.setIcon(imageIcon);
        int x = (panel.getWidth() - imageLabel.getIcon().getIconWidth()) /200 ;
        int y = (panel.getHeight() - imageLabel.getIcon().getIconHeight()) / 150;
        imageLabel.setBounds(x, y, imageLabel.getIcon().getIconWidth(), imageLabel.getIcon().getIconHeight());
        panel.add(imageLabel);

        // bouton Scolarite
        allerScolarite = new JButton("SCOLARITE");
        allerScolarite.setBounds(400, 145, 180, 50);
        allerScolarite.setFont(new Font("Arial", Font.BOLD, 16));
        allerScolarite.setForeground(new Color(255, 0, 136, 255));
        allerScolarite.setBackground(new java.awt.Color(255, 255, 255, 255));
        allerScolarite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scolarite scolarite = new Scolarite();
                scolarite.setVisible(true);
            }
        });
        panel.add(allerScolarite);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        Acceuil acceuil = new Acceuil();
        acceuil.setVisible(true);
    }
}
