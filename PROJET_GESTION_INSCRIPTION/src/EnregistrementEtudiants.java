// YEO FRANCOIS DE SALES GNENEMAN
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Scanner;

public class EnregistrementEtudiants extends JFrame {
    Scanner saisie = new Scanner(System.in);
    Connexion connexion1 = new Connexion();

    // Declaration des differentes variables
    String path = null;
    byte[] ImageEtudiant = null;
    Statement statement;
    ResultSet resultSet;
    JLabel lblTitre, lblmatricule, lblNom, lblPrenoms, lblEmail, lblTel, lblSexe, lblimg, image, lblDate_naissance;
    JTextField textMatricule, textNom, textPrenoms, textEmail,textTel, textDate_Naissance;
    JComboBox comboSexe, comboNiveau;
    JButton btnEnregistrer, btnSupprimer, btnRechercher;
    JTable table, table1;
    JScrollPane scroll, scroll1;

    // methodes pour communiquer avec la table
    public void init(){
        table1 = new JTable();
        scroll1 = new JScrollPane();
        scroll1.setBounds(60,480,800,130);
        scroll1.setViewportView(table1);
    }

    public EnregistrementEtudiants(){
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
        lblTitre = new JLabel("Section des Etudiants");

        // -----setBounds permet le positionnement et la taille x et y sont les marges
        lblTitre.setBounds(250,10,800,30);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitre.setForeground(new Color(255, 255, 255));
        panel.add(lblTitre);

        // AJOUT DES LABEL ET ZONE DE SAISIE

        // matricule etudiant
        lblmatricule = new JLabel("MATRICULE");
        lblmatricule.setBounds(60,60,1000,50);
        lblmatricule.setFont(new Font("Arial", Font.BOLD, 20));
        lblmatricule.setForeground(new Color(255, 255, 255));
        panel.add(lblmatricule);

        textMatricule = new JTextField();
        textMatricule.setBounds(220,60,200,40);
        textMatricule.setText("Ex:CI0122423590");
        textMatricule.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textMatricule);

        // nom etudiant
        lblNom = new JLabel("NOM");
        lblNom.setBounds(60,110,1000,50);
        lblNom.setFont(new Font("Arial", Font.BOLD, 20));
        lblNom.setForeground(new Color(255, 255, 255));
        panel.add(lblNom);

        textNom = new JTextField();
        textNom.setBounds(220,110,250,40);
        textNom.setText("Ex:Yeo");
        textNom.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textNom);

        // prenoms etudiant
        lblPrenoms = new JLabel("PRENOMS");
        lblPrenoms.setBounds(60,160,1000,50);
        lblPrenoms.setFont(new Font("Arial", Font.BOLD, 20));
        lblPrenoms.setForeground(new Color(255, 255, 255));
        panel.add(lblPrenoms);

        textPrenoms = new JTextField();
        textPrenoms.setBounds(220,160,250,40);
        textPrenoms.setText("Ex:François De Sales Gneneman");
        textPrenoms.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textPrenoms);

        // email etudiant
        lblEmail = new JLabel("EMAIL");
        lblEmail.setBounds(60,210,1000,50);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 20));
        lblEmail.setForeground(new Color(255, 255, 255));
        panel.add(lblEmail);

        textEmail = new JTextField();
        textEmail.setBounds(220,210,250,40);
        textEmail.setText("EX:yeofrancois@gmail.com");
        textEmail.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textEmail);

        // telephone etudiant
        lblTel = new JLabel("CONTACT");
        lblTel.setBounds(60,260,1000,50);
        lblTel.setFont(new Font("Arial", Font.BOLD, 20));
        lblTel.setForeground(new Color(255, 255, 255));
        panel.add(lblTel);

        textTel = new JTextField();
        textTel.setBounds(220,260,250,40);
        textTel.setText("0556894212");
        textTel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textTel);

        // date de naissance etudiant
        lblDate_naissance = new JLabel("NEE LE");
        lblDate_naissance.setBounds(60,310,1000,50);
        lblDate_naissance.setFont(new Font("Arial", Font.BOLD, 20));
        lblDate_naissance.setForeground(new Color(255, 255, 255));
        panel.add(lblDate_naissance);

        textDate_Naissance = new JTextField();
        textDate_Naissance.setBounds(220,310,250,40);
        textDate_Naissance.setText("annee-mois-jour");
        textDate_Naissance.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textDate_Naissance);

        // Sexe etudiant
        lblSexe = new JLabel("SEXE");
        lblSexe.setBounds(60,360,850,30);
        lblSexe.setFont(new Font("Arial", Font.BOLD, 16));
        lblSexe.setForeground(new Color(255, 255, 255, 255));
        panel.add(lblSexe);

        comboSexe = new  JComboBox();
        comboSexe.setBounds(220,360,100,30);
        comboSexe.setFont(new Font("Arial", Font.PLAIN,14));
        comboSexe.setBackground(new Color(255, 0, 136, 255));
        comboSexe.addItem("");
        comboSexe.addItem("Masculin");
        comboSexe.addItem("Feminin");
        panel.add(comboSexe);


        // Image de l'etudiant
        lblimg = new JLabel("PHOTO D'IDENTITE");
        lblimg.setBounds(650,70,170,20);
        lblimg.setFont(new Font("Arial", Font.BOLD, 16));
        lblimg.setForeground(new Color(255, 255, 255,225));
        panel.add(lblimg);

        image = new JLabel();
        image.setBounds(650,100,200,200);
        image.setFont(new Font("Arial", Font.BOLD, 16));
        image.setBackground(new java.awt.Color(255, 0, 136, 255));
        image.setHorizontalAlignment(SwingConstants.CENTER);
        image.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        image.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageMouseClicked(evt);
            }
            private void imageMouseClicked(MouseEvent evt) {
                JFileChooser pic  = new JFileChooser();
                pic.showOpenDialog(null);
                File picture = pic.getSelectedFile();

                path = picture.getAbsolutePath();

                BufferedImage img;

                try {

                    img = ImageIO.read(picture);
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
                    image.setIcon(imageIcon);
                    File images = new File(path);

                    FileInputStream fis = new FileInputStream(images);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    byte[] buff = new  byte[1024];

                    for (int i; (i= fis.read(buff)) != -1;){
                        bos.write(buff,0,i);
                    }

                    ImageEtudiant = bos.toByteArray();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        panel.add(image);


        // Ajout du bouton pour Enregistrer
        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setBounds(230,430,150,40);
        btnEnregistrer.setFont(new Font("Arial", Font.BOLD, 16));
        btnEnregistrer.setForeground(new Color(255, 255, 255,225));
        btnEnregistrer.setBackground(new java.awt.Color(255, 0, 136, 255));

        // ajout d'un ecouteur d'action
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mat, nom,prenoms,email,tel, sexe, date;

                mat = textMatricule.getText();
                nom = textNom.getText();
                prenoms = textPrenoms.getText();
                email = textEmail.getText();
                tel = textTel.getText();
                // on doit convertir les valeurs prisent des JComboBox en String
                sexe = comboSexe.getSelectedItem().toString();
                date = textDate_Naissance.getText();


                String requete = "insert into etudiant (matricule, nom,prenoms, email,tel,sexe, image, date_naissance) values (?,?,?,?,?,?,?,?) ";
                try{
                    PreparedStatement statement1 = connexion1.maConnexion().prepareStatement(requete);
                    statement1.setString(1,mat);
                    statement1.setString(2,nom);
                    statement1.setString(3,prenoms);
                    statement1.setString(4,email);
                    statement1.setString(5,tel);
                    statement1.setString(6,sexe);

                    statement1.setBytes(7,ImageEtudiant);
                    statement1.setString(8, date);
                    // condition des differents attribut

                    if ("".equals(mat)){
                        JOptionPane.showMessageDialog(null,"LE MATRICULE ne doit pas être vide", null,JOptionPane.INFORMATION_MESSAGE);
                    } else if (mat.length()>12) {
                        JOptionPane.showMessageDialog(null,"Le Matricule ne peut pas être superieur à 6 caracteres", null,JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if ("".equals(nom)) {
                        JOptionPane.showMessageDialog(null,"Le nom  ne doit pas être vide", null,JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if ("".equals(prenoms)) {
                        JOptionPane.showMessageDialog(null,"prenoms ne doit pas être vide", null,JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if ("".equals(email) && "".equals(tel)) {
                        JOptionPane.showMessageDialog(null,"email et le numero de telephone ne doit pas être vide", null,JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if ("".equals(date)) {
                        JOptionPane.showMessageDialog(null,"La date de naissance ne doit pas être vide", null,JOptionPane.INFORMATION_MESSAGE);
                    }

                    else if ("".equals(sexe)) {
                        JOptionPane.showMessageDialog(null,"Le sexe ne doit pas être vide", null,JOptionPane.INFORMATION_MESSAGE);
                    }

                    else if (!"".equals(mat) && mat.length()<=12 && !"".equals(nom) && !"".equals(sexe) && !("".equals(prenoms)) && !("".equals(email) && "".equals(tel)) && !("".equals(date)) ){
                        statement1.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Etudiants enregistré. Merci", null,JOptionPane.INFORMATION_MESSAGE);
                        connexion1.maConnexion().close();
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Erreur d'enregistrement", ex.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
                // disposer la premiere fenetre ensuite faire appelle des elements (rafraichir et actualiser les informations)
                dispose();
                EnregistrementEtudiants Etudiant1 = new EnregistrementEtudiants();
                Etudiant1.setVisible(true);

            }
        });
        panel.add(btnEnregistrer);


        // Ajout du bouton pour Supprimer

        btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setBounds(430,430,150,40);
        btnSupprimer.setFont(new Font("Arial", Font.BOLD, 16));
        btnSupprimer.setForeground(new Color(255, 255, 255,225));
        btnSupprimer.setBackground(new java.awt.Color(255, 0, 136, 255));

        // ajout d'un ecouteur d'action
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mat;
                // comme le v-model c'est pour avoir les differents elements entré dans les inputs
                mat = textMatricule.getText();
                String requete = "delete from etudiant where Matricule = '"+ mat + "'  ";
                try{
                    statement = connexion1.maConnexion().createStatement();
                    // les conditions pour valider les inputs
                    if ("".equals(mat)){
                        JOptionPane.showMessageDialog(null,"LE MATRICULE ne doit pas être vide", null,JOptionPane.INFORMATION_MESSAGE);
                    } else if (mat.length()>12) {
                        JOptionPane.showMessageDialog(null,"Le Matricule ne peut pas être superieur à 12 caracteres", null,JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        statement.executeUpdate(requete);
                        JOptionPane.showMessageDialog(null,"Etudiants Supprimé. Merci", null,JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Erreur de Supprimer", ex.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
                dispose();
                EnregistrementEtudiants Etudiant1 = new EnregistrementEtudiants();
                Etudiant1.setVisible(true);

            }
        });
        panel.add(btnSupprimer);


        // tableau des informations des etudiants

        DefaultTableModel model = new DefaultTableModel();
        init();
        panel.add(scroll1);
        model.addColumn("Matricule");
        model.addColumn("Nom");
        model.addColumn("Prenoms");
        model.addColumn("email");
        model.addColumn("Contact");
        model.addColumn("Sexe");
        model.addColumn("date_naissace");

        // setModel est utilisé pour afficher
        table1.setModel(model);

        String requete = "select * from etudiant  order by nom ";
        try {
            statement = connexion1.maConnexion().createStatement();
            resultSet = statement.executeQuery(requete);
            while (resultSet.next()){
                model.addRow(new Object[]{
                        resultSet.getString("matricule"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenoms"),
                        resultSet.getString("email"),
                        resultSet.getString("tel"),
                        resultSet.getString("sexe"),
                        resultSet.getString("date_naissance")
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
                textMatricule.setText(model.getValueAt(selectionner,0).toString());
                textNom.setText(model.getValueAt(selectionner,1).toString());
                comboSexe.setSelectedItem(model.getValueAt(selectionner,2).toString());
                comboNiveau.setSelectedItem(model.getValueAt(selectionner,3).toString());
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
                    String requete = "select * from etudiant where Matricule =? ";
                    PreparedStatement preparedStatement = connexion1.maConnexion().prepareStatement(requete);
                    preparedStatement.setString(1,mat);
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()== false){
                        JOptionPane.showMessageDialog(null, "Ce matricule n'existe pas pour un etudiant", null, JOptionPane.ERROR_MESSAGE);
                        textMatricule.setText("");
                    } else {

                        textNom.setText(resultSet.getString("nom").trim());
                        textPrenoms.setText(resultSet.getString("prenoms").trim());
                        textEmail.setText(resultSet.getString("email").trim());
                        textTel.setText(resultSet.getString("tel").trim());
                        comboSexe.setSelectedItem(resultSet.getString("sexe").trim());
                        textDate_Naissance.setText(resultSet.getString("date_naissance").trim());

                        try {
                            Blob blob1 = resultSet.getBlob("image");
                            byte[] imagebyte = blob1.getBytes(1, (int) blob1.length() );
                            ImageIcon imag = new ImageIcon(new ImageIcon(imagebyte).getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
                            image.setIcon(imag);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null,"Erreur" + e.getMessage(), null,JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }

        });

    }
}
