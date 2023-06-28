// YEO FRANCOIS DE SALES GNENEMAN
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Scanner;

public class Scolarite extends JFrame {
    Scanner saisie = new Scanner(System.in);
    Connexion connexion1 = new Connexion();

    // Declaration des differentes variables

    Statement statement;
    ResultSet resultSet;
    JLabel lblTitre,lblId_scolarite, lblDate_inscription ,lbLMatricule , lblPaye, lblRestant, lblVers;
    JTextField textId_scolarite, textDate_inscription, textMatricule, textPaye, textRestant;
    JComboBox comboVersement;
    JButton btnEnregistrer, btnRechercher;
    JTable table, table1;
    JScrollPane scroll, scroll1;

    // methodes pour communiquer avec la table
    public void init(){
        table1 = new JTable();
        scroll1 = new JScrollPane();
        scroll1.setBounds(10,350,900,130);
        scroll1.setViewportView(table1);
    }

    public Scolarite(){
        super.setTitle("LOGICIEL GESTION DES INSCRIPTIONS ");
        super.setSize(900,600);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);
        panel.setBackground(new Color(0, 255, 180));

        // AJOUT DU TITRE
        lblTitre = new JLabel("SCOLARITE");

        lblTitre.setBounds(250,10,800,30);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitre.setForeground(new Color(255, 255, 255));
        panel.add(lblTitre);

        // AJOUT DES LABEL ET ZONE DE SAISIE

        // identifaint Scolarite
        lblId_scolarite= new JLabel("Id Scolarite");
        lblId_scolarite.setBounds(60,60,1000,50);
        lblId_scolarite.setFont(new Font("Arial", Font.BOLD, 20));
        lblId_scolarite.setForeground(new Color(255, 255, 255));
        panel.add(lblId_scolarite);
        int nb1 = 500;
        textId_scolarite = new JTextField();
        textId_scolarite.setBounds(220,60,200,40);
        textId_scolarite.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textId_scolarite);



        // id Inscription
        lbLMatricule = new JLabel("Id Inscription");
        lbLMatricule.setBounds(60,110,1000,50);
        lbLMatricule.setFont(new Font("Arial", Font.BOLD, 20));
        lbLMatricule.setForeground(new Color(255, 255, 255));
        panel.add(lbLMatricule);

        textMatricule= new JTextField();
        textMatricule.setBounds(220,110,250,40);
        textMatricule.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textMatricule);


        lblVers = new JLabel("VERSEMENT");
        lblVers.setBounds(60,160,850,30);
        lblVers.setFont(new Font("Arial", Font.BOLD, 16));
        lblVers.setForeground(new Color(255, 255, 255, 255));
        panel.add(lblVers);

        comboVersement = new  JComboBox();
        comboVersement.setBounds(160,160,150,30);
        comboVersement.setFont(new Font("Arial", Font.PLAIN,14));
        comboVersement.setBackground(new Color(255, 0, 136, 255));
        comboVersement.addItem("");
        comboVersement.addItem("TOUT LES VERSEMEMENT");
        comboVersement.addItem("VERSEMENT 1");
        comboVersement.addItem("VERSEMENT 2");
        comboVersement.addItem("VERSEMENT 3");
        panel.add(comboVersement);

        lblPaye = new JLabel("Montant Payé");
        lblPaye.setBounds(60,210,1000,50);
        lblPaye.setFont(new Font("Arial", Font.BOLD, 20));

        lblPaye.setForeground(new Color(255, 255, 255));
        panel.add(lblPaye);

        textPaye= new JTextField();
        textPaye.setBounds(220,210,250,40);
        textPaye.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textPaye);

        lblRestant = new JLabel("Montant Restant");
        lblRestant.setBounds(60,260,1000,50);

        lblRestant.setFont(new Font("Arial", Font.BOLD, 20));
        lblRestant.setForeground(new Color(255, 255, 255));
        panel.add(lblRestant);

        textRestant= new JTextField();
        textRestant.setBounds(220,260,250,40);
        textRestant.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textRestant);



        // Ajout du bouton pour Enregistrer
        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setBounds(250,300,150,40);
        btnEnregistrer.setFont(new Font("Arial", Font.BOLD, 16));
        btnEnregistrer.setForeground(new Color(255, 255, 255,225));
        btnEnregistrer.setBackground(new java.awt.Color(255, 0, 136, 255));

        // ajout d'un ecouteur d'action
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String  id_scolarite,montant_paye,montant_restant,matricule,versement ;


                id_scolarite = textId_scolarite.getText();
                montant_paye = textPaye.getText();
                montant_restant = textRestant.getText();
                versement = comboVersement.getSelectedItem().toString();
                matricule = textMatricule.getText();


                String requete = "insert into scolarite (id_scolarite, montant_paye, montant_restant, versement, id_inscription) values (?,?,?,?,?) ";
                try {
                    PreparedStatement statement1 = connexion1.maConnexion().prepareStatement(requete);
                    statement1.setString(1, id_scolarite);
                    statement1.setString(2, montant_paye);
                    statement1.setString(3, montant_restant);
                    statement1.setString(4, versement);
                    statement1.setString(5, matricule);

                    if ("".equals(id_scolarite)) {
                        JOptionPane.showMessageDialog(null, "L'identifiant ne doit pas être vide", null, JOptionPane.INFORMATION_MESSAGE);
                    } else if (id_scolarite.length() > 12) {
                        JOptionPane.showMessageDialog(null, "L'identifiant ne peut pas dépasser 12 caractères", null, JOptionPane.INFORMATION_MESSAGE);
                    } else if ("".equals(versement)) {
                        JOptionPane.showMessageDialog(null, "Veuillez sélectionner un versement", null, JOptionPane.INFORMATION_MESSAGE);
                    } else if ("".equals(matricule)) {
                        JOptionPane.showMessageDialog(null, "L'identifiant d'inscription ne doit pas être vide", null, JOptionPane.INFORMATION_MESSAGE);
                    } else if ("".equals(montant_paye)) {
                        JOptionPane.showMessageDialog(null, "Veuillez entrer le montant payé", null, JOptionPane.INFORMATION_MESSAGE);
                    } else if (!"".equals(id_scolarite) && id_scolarite.length() <= 12 && !"".equals(matricule) && !"".equals(versement) && !"".equals(montant_paye)) {
                        statement1.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Etudiant enregistré. Merci", null, JOptionPane.INFORMATION_MESSAGE);
                        connexion1.maConnexion().close();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur d'enregistrement", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                }

                // disposer la premiere fenetre ensuite faire appelle des elements (rafraichir et actualiser les informations)
                dispose();
                Scolarite scolarite = new Scolarite();
                scolarite.setVisible(true);

            }
        });
        panel.add(btnEnregistrer);



        // tableau des informations des etudiants

        DefaultTableModel model = new DefaultTableModel();
        init();
        panel.add(scroll1);
        model.addColumn("Id Scolarité");
        model.addColumn("Montant Payé");
        model.addColumn("montant_restant");
        model.addColumn("Versement");
        model.addColumn("id inscription");

        table1.setModel(model);

        String requete = "select * from scolarite  order by id_scolarite ";
        try {
            statement = connexion1.maConnexion().createStatement();
            resultSet = statement.executeQuery(requete);
            while (resultSet.next()){
                model.addRow(new Object[]{
                        resultSet.getString("id_scolarite"),
                        resultSet.getString("montant_paye"),
                        resultSet.getString("montant_restant"),
                        resultSet.getString("versement"),
                        resultSet.getString("id_inscription"),
                });
            }
        } catch ( Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur", null, JOptionPane.ERROR_MESSAGE);
        }

        table1.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Table1mouseReleased(evt);
            }
            private void Table1mouseReleased(MouseEvent evt){
                int selectionner = table1.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                textId_scolarite.setText(model.getValueAt(selectionner,0).toString());
                textMatricule.setText(model.getValueAt(selectionner,1).toString());
                comboVersement.setSelectedItem(model.getValueAt(selectionner,2).toString());
            }
        });


        // Ajout d'un boutton de recherche
        btnRechercher = new JButton("Recherche");
        btnRechercher.setBounds(400,60,150,40);
        btnRechercher.setFont(new Font("ARIAL", Font.BOLD, 14));
        btnRechercher.setForeground(new Color(0,0,0));
        btnRechercher.setBackground(new Color(173,216,230));
        panel.add(btnRechercher);

        btnRechercher.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnTelechargeractionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            private void btnTelechargeractionPerformed(ActionEvent evt) throws SQLException {
                String mat;
                mat = textMatricule.getText();

                try {
                    String requete = "select * from scolarite where id_scolarite =? ";
                    PreparedStatement preparedStatement = connexion1.maConnexion().prepareStatement(requete);
                    preparedStatement.setString(1,mat);
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()== false){
                        JOptionPane.showMessageDialog(null, "Ce identifiant n'existe pas pour un etudiant", null, JOptionPane.ERROR_MESSAGE);
                        textMatricule.setText("");
                    } else {

                        textId_scolarite.setText(resultSet.getString("id_scolarite").trim());
                        textId_scolarite.setText(resultSet.getString("montant_paye").trim());
                        textId_scolarite.setText(resultSet.getString("montant_restant").trim());
                        comboVersement.setSelectedItem(resultSet.getString("versement").trim());
                        textDate_inscription.setText(resultSet.getString("id_inscription").trim());

                    }
                }catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }

        });

    }
}
