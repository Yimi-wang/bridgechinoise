package Vue;

//import global.ConfigurationSetting;
//import sun.awt.image.InputStreamImageSource;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;


public class Main extends JFrame implements ActionListener {
    JFrame mainframe;

    Container mainContentPanel;

    JButton btnStartButton;
    JButton btnLoadButton;
    JButton btnSettingButton;
    JButton btnOnlineButton;
    //&#x6587;&#x672C;&#x8F93;&#x5165;&#x6309;&#x94AE;
    JTextField jta;
    //gameMode
    JButton GameModeQuestionButton; // ?&#x6309;&#x94AE;
    JButton GameModeLeftButton;//&#x5DE6;&#x7BAD;&#x5934;&#x6309;&#x94AE;
    JButton GameModeRightButton;//&#x53F3;&#x7BAD;&#x5934;&#x6309;&#x94AE;
    //JScrollPane GameModeInputJSP;//&#x6587;&#x672C;&#x8F93;&#x5165;&#x6846;&#xFF0C;&#x4F4D;&#x4E8E;&#x4E0B;&#x6765;&#x6846;&#x4E0B;
    JLabel lblGameMode;//gamemode&#x6807;&#x7B7E;
    JLabel lblGameModeTest;//&#x4E0B;&#x65B9;&#x7684;&#x63D0;&#x793A;&#x6846;&#x6587;&#x5B57;
    JComboBox GameModeComboBox;//&#x5355;&#x9009;&#x6846;

    JavaClip m;
    //AIMode
    JButton AIModeQuestionButton; // ?&#x6309;&#x94AE;
    JButton AIModeLeftButton;//&#x5DE6;&#x7BAD;&#x5934;&#x6309;&#x94AE;
    JButton AIModeRightButton;//&#x53F3;&#x7BAD;&#x5934;&#x6309;&#x94AE;
    JLabel lblAIMode;//gamemode&#x6807;&#x7B7E;
    JComboBox AIModeComboBox;//&#x5355;&#x9009;&#x6846;

    //&#x7528;&#x6237;&#x8BBE;&#x7F6E;
    JFrame settingJframe;

    Container settingContentPanel;

    JLabel settingJLabel;

    JLabel settingDesktoplbl;

    JLabel settingCardBack;

    JLabel settingShowCard;


    JRadioButton backradioBtn1;

    JRadioButton backradioBtn2;

    JRadioButton backradioBtn3;
    JRadioButton rightbackradioBtn1;

    JRadioButton rightbackradioBtn2;

    JRadioButton rightbackradioBtn3;

    JRadioButton cardRadioBtn1;

    JRadioButton cardRadioBtn2;

    JRadioButton cardRadioBtn3;

    JRadioButton cardShowBtn1;

    JRadioButton cardShowBtn2;

    JButton settingGetBackBtn;

    JButton settingSaveBackBtn;
    ButtonGroup btnBackGroup;
    ButtonGroup btnCardGroup;
    ButtonGroup btnrightBackGroup;

    /**
     * Create the application.
     */
    public Main()  {
        initialize();
        addEventListener();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Main window = new Main();
        window.mainframe.setVisible(true);
    }

    public static void backmenu() throws FileNotFoundException{
        Main window = new Main();
        window.mainframe.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {


        String[] dfonts;
        setValue();
        dfonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        // &#x521D;&#x59CB;&#x5316;&#x7A97;&#x4F53;
        mainframe = new JFrame("Bridge Chinoise");
        mainframe.setSize(720, 506);
        mainframe.setLocationRelativeTo(null);
        mainframe.getContentPane().setLayout(null);
        mainframe.setResizable(false);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m = new JavaClip();
        m.playmainmusic();
        JPanel impanel = (JPanel) mainframe.getContentPane();
        impanel.setOpaque(false);

        java.net.URL imgURL = Vue.Main.class.getResource("/res/images/Logo.png");
        ImageIcon logo = new ImageIcon(imgURL);
        JLabel logolabel = new JLabel(logo);
        logolabel.setBounds(30, 20, 300, 100);
        logo.setImage(logo.getImage().getScaledInstance(logolabel.getWidth(), logolabel.getHeight(), Image.SCALE_DEFAULT));
        mainframe.getLayeredPane().add(logolabel, Integer.valueOf(Integer.MIN_VALUE));


        //&#x663E;&#x793A;&#x80CC;&#x666F;
        imgURL = Vue.Main.class.getResource("/res/images/bg_menu.png");
        ImageIcon background = new ImageIcon(imgURL);
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, mainframe.getWidth(), mainframe.getHeight());
        background.setImage(background.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        mainframe.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));

//        // &#x6E38;&#x620F;&#x6587;&#x672C;&#x6807;&#x9898;
//        JLabel jLabel = new JLabel("Bridge Chinois");
//        jLabel.setBounds(30, 25, 300, 30);
//        Font f = new Font("&#x96B6;&#x4E66;",Font.PLAIN,30);
//        jLabel.setFont(f);
//        Color fg = new Color(255,255,255);
//        jLabel.setForeground(fg);
//        mainContentPanel.add(jLabel);


        mainContentPanel = mainframe.getContentPane();
        mainContentPanel.setLayout(null);
        // start&#x6309;&#x94AE;
        //&#x52A0;&#x8F7D;&#x80CC;&#x666F;&#x56FE;&#x7247;
        imgURL = Vue.Main.class.getResource("/res/images/START.png");
        ImageIcon startIcon1 = new ImageIcon(imgURL);
        Image startImg = startIcon1.getImage();
        Image startNewimg = startImg.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon startIcon2 = new ImageIcon(startNewimg);
        btnStartButton = new RoundRectButton(startIcon2);
        btnStartButton.setBounds(90, 140, 120, 40);
        btnStartButton.setBorder(new RoundBorder(Color.WHITE));
        btnStartButton.setBackground(Color.RED);
        mainContentPanel.add(btnStartButton);


//        // online
//        //&#x52A0;&#x8F7D;&#x80CC;&#x666F;&#x56FE;&#x7247;
//        String onlinePath = "res/images/ONLINE.png";
//        ImageIcon onlineIcon1 = new ImageIcon(onlinePath);
//        Image onlineImg = onlineIcon1.getImage();
//        Image onlineNewImg = onlineImg.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
//        ImageIcon onlineIcon2 = new ImageIcon(onlineNewImg);
//
//        btnOnlineButton = new RoundRectButton(onlineIcon2);
//        btnOnlineButton.setBounds(90, 220, 120, 40);
//        mainContentPanel.add(btnOnlineButton);


        // load
        //&#x52A0;&#x8F7D;&#x80CC;&#x666F;&#x56FE;&#x7247;
        imgURL = Vue.Main.class.getResource("/res/images/LOAD.png");
        ImageIcon loadIcon1 = new ImageIcon(imgURL);
        Image loadImg = loadIcon1.getImage();
        Image loadNewImg = loadImg.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon loadIcon2 = new ImageIcon(loadNewImg);

        btnLoadButton = new RoundRectButton(loadIcon2);
        btnLoadButton.setBounds(90, 240, 120, 40);
        mainContentPanel.add(btnLoadButton);


        // setting
        //&#x52A0;&#x8F7D;&#x80CC;&#x666F;&#x56FE;&#x7247;
        imgURL = Vue.Main.class.getResource("/res/images/SETTING.png");
        ImageIcon settingIcon1 = new ImageIcon(imgURL);
        Image settingImg = settingIcon1.getImage();
        Image settingNewImg = settingImg.getScaledInstance(120, 40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon settingIcon2 = new ImageIcon(settingNewImg);

        btnSettingButton = new RoundRectButton(settingIcon2);
        btnSettingButton.setBounds(90, 340, 120, 40);
        mainContentPanel.add(btnSettingButton);

//        //&#x6DFB;&#x52A0;gameMode
//        JLabel gameModeText = new JLabel("GameMode");
//        gameModePanel.add(gameModeText);
//        mainContentPanel.add(gameModePanel);
        /**
         * start gamemode &#x7A97;&#x53E3;
         */
        //&#x6807;&#x9898;
        lblGameMode = new JLabel("GameMode");
        lblGameMode.setBounds(200, 150, 300, 30);
        Font f1 = new Font(dfonts[0], Font.PLAIN, 30);
        lblGameMode.setFont(f1);
        Color fg1 = new Color(255, 255, 255);
        lblGameMode.setForeground(fg1);
        mainContentPanel.add(lblGameMode);

        GameModeComboBox = new JComboBox();
        // &#x7ED1;&#x5B9A;&#x4E0B;&#x62C9;&#x6846;&#x9009;&#x9879;
        String[] strArray = {"BO1", "BO3", "Nombre de Jeu Fixe", "Score Fixe"};
        for (String item : strArray) {
            GameModeComboBox.addItem(item);
        }
        GameModeComboBox.setFont(new Font(dfonts[0], Font.PLAIN, 14));
        GameModeComboBox.setBounds(150, 200, 180, 30);
        mainContentPanel.add(GameModeComboBox);

        //&#x6DFB;&#x52A0;&#x95EE;&#x53F7;&#x6309;&#x94AE;
        GameModeQuestionButton = new JButton("?");
        GameModeQuestionButton.setBounds(340, 200, 50, 30);
        mainContentPanel.add(GameModeQuestionButton);

        //&#x4E0B;&#x65B9;&#x7684;&#x6587;&#x5B57;&#x63D0;&#x793A;
        lblGameModeTest = new JLabel("<html><body>Donner le nombre de Jue<br>(Si vous choisi Numbre  Fixe)<br>ou le nombre de Score<br>(Si vous choisi Score Fixe</body></html>");
        lblGameModeTest.setBounds(150, 150, 300, 300);
        Font f22 = new Font(dfonts[0], Font.PLAIN, 15);
        lblGameModeTest.setFont(f22);
        Color fg22 = new Color(205, 27, 74);
        lblGameModeTest.setForeground(fg22);
        mainContentPanel.add(lblGameModeTest);
        //&#x6DFB;&#x52A0;&#x6587;&#x672C;&#x6846;
        jta = new JTextField();
        jta.setForeground(Color.BLACK);    //&#x8BBE;&#x7F6E;&#x7EC4;&#x4EF6;&#x7684;&#x80CC;&#x666F;&#x8272;
        jta.setFont(new Font(dfonts[15], Font.ROMAN_BASELINE, 10));    //&#x4FEE;&#x6539;&#x5B57;&#x4F53;&#x6837;&#x5F0F;
        jta.setBounds(150, 350, 200, 30);
        lblGameModeTest.setVisible(false);
        jta.setVisible(false);
        mainContentPanel.add(jta);
        GameModeComboBox.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent event)
            {
                switch (event.getStateChange())
                {
                    case ItemEvent.SELECTED:
                        if(event.getItem()=="Nombre de Jeu Fixe"||event.getItem()=="Score Fixe"){
                            lblGameModeTest.setVisible(true);
                            jta.setVisible(true);
                        }else{
                            lblGameModeTest.setVisible(false);
                            jta.setVisible(false);
                        }
                        break;
                }
            }
        });
        //&#x6DFB;&#x52A0;&#x5DE6;&#x53F3;&#x6309;&#x94AE;
        GameModeLeftButton = new JButton("←");
        GameModeLeftButton.setBounds(150, 400, 50, 30);
        mainContentPanel.add(GameModeLeftButton);
        GameModeRightButton = new JButton("→");
        GameModeRightButton.setBounds(340, 400, 50, 30);
        mainContentPanel.add(GameModeRightButton);

        /**
         * AImode
         */
        // &#x6DFB;&#x52A0;AIMode**********************
        lblAIMode = new JLabel("AIMode");
        lblAIMode.setBounds(200, 150, 300, 30);
        Font f2 = new Font(dfonts[0], Font.PLAIN, 30);
        lblAIMode.setFont(f2);
        Color fg2 = new Color(255, 255, 255);
        lblAIMode.setForeground(fg2);
        mainContentPanel.add(lblAIMode);

        AIModeComboBox = new JComboBox();
        // &#x7ED1;&#x5B9A;&#x4E0B;&#x62C9;&#x6846;&#x9009;&#x9879;
        String[] AIStrArray = {"Sans AI", "AI simple", "AI moyenne", "AI difficile"};
        for (String item : AIStrArray) {
            AIModeComboBox.addItem(item);
        }
        AIModeComboBox.setFont(new Font(dfonts[0], Font.PLAIN, 14));
        AIModeComboBox.setBounds(150, 200, 180, 30);
        mainContentPanel.add(AIModeComboBox);

        //&#x6DFB;&#x52A0;&#x95EE;&#x53F7;&#x6309;&#x94AE;
        //AIModeQuestionButton = new JButton("?");
        AIModeQuestionButton = new JButton("?");
        AIModeQuestionButton.setBounds(340, 200, 50, 30);
        mainContentPanel.add(AIModeQuestionButton);


        //&#x6DFB;&#x52A0;&#x5DE6;&#x53F3;&#x6309;&#x94AE;
        AIModeLeftButton = new JButton("←");
        AIModeLeftButton.setBounds(150, 400, 50, 30);
        mainContentPanel.add(AIModeLeftButton);
        AIModeRightButton = new JButton("→");
        AIModeRightButton.setBounds(340, 400, 50, 30);
        mainContentPanel.add(AIModeRightButton);

        gameModeDisappear();
        AIModeDisappear();


    }

    //gameMode&#x6D88;&#x5931;
    public void gameModeDisappear() {
        lblGameMode.setVisible(false);
        GameModeComboBox.setVisible(false);
        GameModeQuestionButton.setVisible(false);
        jta.setVisible(false);
        lblGameModeTest.setVisible(false);
        GameModeLeftButton.setVisible(false);
        GameModeRightButton.setVisible(false);
    }

    //gameMode&#x51FA;&#x73B0;
    public void gameModeAppear() {
        lblGameMode.setVisible(true);
        GameModeComboBox.setVisible(true);
        GameModeQuestionButton.setVisible(true);
        if(GameModeComboBox.getSelectedIndex()<2){
            jta.setVisible(false);
            lblGameModeTest.setVisible(false);}
        else{
            jta.setVisible(true);
            lblGameModeTest.setVisible(true);
        }
        GameModeLeftButton.setVisible(true);
        GameModeRightButton.setVisible(true);
    }


    public void AIModeDisappear() {
        lblAIMode.setVisible(false);
        AIModeComboBox.setVisible(false);
        AIModeQuestionButton.setVisible(false);
        AIModeLeftButton.setVisible(false);
        AIModeRightButton.setVisible(false);
    }

    public void AIModeAppear() {
        lblAIMode.setVisible(true);
        AIModeComboBox.setVisible(true);
        AIModeQuestionButton.setVisible(true);
        AIModeLeftButton.setVisible(true);
        AIModeRightButton.setVisible(true);
    }


    /**
     * &#x4E3B;&#x754C;&#x9762;&#x6309;&#x94AE;&#x6D88;&#x5931;
     */
    public void mainFrameButtonDisappear() {
        btnStartButton.setVisible(false);
        btnLoadButton.setVisible(false);
        btnSettingButton.setVisible(false);
        // btnOnlineButton.setVisible(false);
    }


    /**
     * &#x4E3B;&#x754C;&#x9762;&#x6309;&#x94AE;&#x51FA;&#x73B0;
     */
    public void mainFrameButtonAppear() {
        btnStartButton.setVisible(true);
        btnLoadButton.setVisible(true);
        btnSettingButton.setVisible(true);
        // btnOnlineButton.setVisible(true);

    }

    public void addEventListener() {

        //&#x4E3B;&#x754C;&#x9762;&#x6309;&#x94AE;
        btnStartButton.addActionListener(this);
        btnLoadButton.addActionListener(this);
        //     btnOnlineButton.addActionListener(this);
        btnSettingButton.addActionListener(this);

        //gameMode&#x6309;&#x94AE;
        GameModeQuestionButton.addActionListener(this);
        GameModeLeftButton.addActionListener(this);
        GameModeRightButton.addActionListener(this);

        //AIMode&#x6309;&#x94AE;
        AIModeLeftButton.addActionListener(this);
        AIModeRightButton.addActionListener(this);
        AIModeQuestionButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStartButton) {
            mainFrameButtonDisappear();
            gameModeAppear();
        }
        if (e.getSource() == GameModeLeftButton) {
            mainFrameButtonAppear();
            gameModeDisappear();
        }
        //&#x5C06;GameMode&#x4FDD;&#x5B58;
        if (e.getSource() == GameModeRightButton) {
            AIModeAppear();
            gameModeDisappear();
            int GameMode;

            // Human vs Human
            GameMode = GameModeComboBox.getSelectedIndex() + 1;
            System.setProperty("GameMode", String.valueOf(GameMode));
            String NB = jta.getText();
            if (GameMode > 2) {
                System.setProperty("GameInformation", String.valueOf(GameMode));
            }
        }
        if (e.getSource() == GameModeQuestionButton) {
            JOptionPane.showMessageDialog(null, "BO1: Un jeu (toutes les cartes jouées), celui qui a le meilleur score gagne.\n" +
                    "BO3: Trois jeux(toutes les cartes jouées dans chaque jeu)Celui qui gagne deux matchs gagne.\n" +
                    "Nombre des Jeux Fixe:  On joue x fois jeu.(x est donnée par utilisateur)le joueur ayant le score le plus élevé à la fin de tous les jeux est  gagné.\n" +
                    "Score Fixe: Le joueur qui atteint le score requis en premier gagne la partie.\n", "Gamemode", JOptionPane.QUESTION_MESSAGE);
        }
        if (e.getSource() == AIModeLeftButton) {
            AIModeDisappear();
            gameModeAppear();
        }
        if (e.getSource() == AIModeRightButton) {
            int IA=0;
            IA = AIModeComboBox.getSelectedIndex();
            System.setProperty("AI", String.valueOf(IA));
            mainframe.dispose();
            m.stop();
            if (IA==0) InterfaceJeu.start();
            else InterfaceIA.start();
        }

        if (e.getSource() == AIModeQuestionButton) {
            JOptionPane.showMessageDialog(null, "Vous pouvez choisir le mode AI ici", "AImode", JOptionPane.QUESTION_MESSAGE);
        }
        if (e.getSource() == btnLoadButton) {

            LoadInterface li = new LoadInterface();
            li.run();
            mainframe.dispose();
            m.stop();

        }

        if(e.getSource()==btnSettingButton){

            initSettingJFrame();
            addSettingActionListener();
        }

        if(e.getSource()==settingGetBackBtn){
            settingJframe.dispose();
//            Main window = new Main();
//            window.mainframe.setVisible(true);

        }
        if(e.getSource()==settingSaveBackBtn){
            FileWriter fw;
            int i=0;
            Enumeration element =btnBackGroup.getElements();
            while (element.hasMoreElements()){
                AbstractButton button = (AbstractButton)element.nextElement();
                if (button.isSelected()) {

                    System.setProperty("background", String.valueOf(i));

                    break;
                }
                i++;
            }
            i=0;
            element=btnCardGroup.getElements();
            while (element.hasMoreElements()){
                AbstractButton button = (AbstractButton)element.nextElement();
                if (button.isSelected()) {
                    System.setProperty("back", String.valueOf(i));
                    break;
                }
                i++;
            }
            i=0;
            element=btnrightBackGroup.getElements();
            while (element.hasMoreElements()){
                AbstractButton button = (AbstractButton)element.nextElement();
                if (button.isSelected()) {
                    System.setProperty("backright", String.valueOf(i));
                    break;
                }
                i++;
            }
            settingJframe.dispose();
//                Main window = new Main();
//                window.mainframe.setVisible(true);
        }
    }

    //setting Gui
    public void initSettingJFrame() {

        settingJframe = new JFrame("Configuration");
        settingJframe.setSize(600, 700);
        settingJframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingJframe.setResizable(false);
        settingJframe.setLocationRelativeTo(getOwner()); // &#x5C4F;&#x5E55;&#x5C45;&#x4E2D;
        settingContentPanel = settingJframe.getContentPane();


        settingGetBackBtn = new JButton("rentrer");
        settingGetBackBtn.setBounds(70, 600, 160, 30);
        settingContentPanel.add(settingGetBackBtn);


        settingSaveBackBtn = new JButton("conserver");
        settingSaveBackBtn.setBounds(370, 600, 160, 30);
        settingContentPanel.add(settingSaveBackBtn);

        //&#x521D;&#x59CB;&#x5316;&#x7EC4;&#x4EF6;
        settingJLabel = new JLabel("Configuration");
        settingJLabel.setBounds(220, 20, 200, 30);
        Font f1 = new Font("Calibri", Font.PLAIN, 18);
        settingJLabel.setFont(f1);
        settingContentPanel.add(settingJLabel);

        JLabel seprate0 = new JLabel("");
        seprate0.setBounds(0, 55, 10000, 1);
        seprate0.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        settingContentPanel.add(seprate0);

        settingDesktoplbl = new JLabel("Set desktop background:");
        settingDesktoplbl.setBounds(30, 60, 230, 30);
        settingDesktoplbl.setFont(f1);
        settingContentPanel.add(settingDesktoplbl);

        String background=System.getProperty("background");
        int backgroundi = Integer.parseInt(background);


        if (backgroundi == 0)
            backradioBtn1 = new JRadioButton("", true);
        else backradioBtn1 = new JRadioButton("", false);
        backradioBtn1.setBounds(60, 100, 30, 30);
        settingContentPanel.add(backradioBtn1);
        java.net.URL imgURL = Vue.Main.class.getResource("/res/images/background (1).png");
        JLabel back1 = new JLabel(SwingUtil.createAutoAdjustIcon(imgURL, true));
        back1.setBounds(90, 100, 100, 60);
        settingContentPanel.add(back1);

        if (backgroundi == 1)
            backradioBtn2 = new JRadioButton("", true);
        else backradioBtn2 = new JRadioButton("", false);
        backradioBtn2.setBounds(230, 100, 30, 30);
        settingContentPanel.add(backradioBtn2);
        imgURL = Vue.Main.class.getResource("/res/images/background (2).png");
        JLabel back2 = new JLabel(SwingUtil.createAutoAdjustIcon(imgURL, true));
        back2.setBounds(260, 100, 100, 60);
        settingContentPanel.add(back2);

        if (backgroundi == 2)
            backradioBtn3 = new JRadioButton("", true);
        else backradioBtn3 = new JRadioButton("", false);
        backradioBtn3.setBounds(390, 100, 30, 30);
        settingContentPanel.add(backradioBtn3);
        imgURL = Vue.Main.class.getResource("/res/images/background (3).png");
        JLabel back3 = new JLabel(SwingUtil.createAutoAdjustIcon(imgURL, true));
        back3.setBounds(420, 100, 100, 60);
        settingContentPanel.add(back3);


        btnBackGroup = new ButtonGroup();
        btnBackGroup.add(backradioBtn1);
        btnBackGroup.add(backradioBtn2);
        btnBackGroup.add(backradioBtn3);

        //&#x5206;&#x5272;&#x7EBF;
        JLabel seprate1 = new JLabel("");
        seprate1.setBounds(0, 180, 10000, 1);
        seprate1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        settingContentPanel.add(seprate1);


        settingCardBack = new JLabel("Set card background:");
        settingCardBack.setBounds(30, 200, 230, 30);
        settingCardBack.setFont(f1);
        settingContentPanel.add(settingCardBack);

        String back = System.getProperty("back");
        int backi = Integer.parseInt(back);

        if (backi == 0)
            cardRadioBtn1 = new JRadioButton("", true);
        else cardRadioBtn1 = new JRadioButton("", false);
        cardRadioBtn1.setBounds(60, 250, 30, 30);
        settingContentPanel.add(cardRadioBtn1);
        imgURL = Vue.Main.class.getResource("/res/images/back (1).png");
        JLabel cardBack1 = new JLabel(SwingUtil.createAutoAdjustIcon(imgURL, true));
        cardBack1.setBounds(90, 250, 60, 90);
        settingContentPanel.add(cardBack1);

        if (backi == 1)
            cardRadioBtn2 = new JRadioButton("", true);
        else cardRadioBtn2 = new JRadioButton("", false);
        cardRadioBtn2.setBounds(230, 250, 30, 30);
        settingContentPanel.add(cardRadioBtn2);
        imgURL = Vue.Main.class.getResource("/res/images/back (2).png");
        JLabel cardBack2 = new JLabel(SwingUtil.createAutoAdjustIcon(imgURL, true));
        cardBack2.setBounds(260, 250, 60, 90);
        settingContentPanel.add(cardBack2);

        if (backi == 2)
            cardRadioBtn3 = new JRadioButton("", true);
        else cardRadioBtn3 = new JRadioButton("", false);
        cardRadioBtn3.setBounds(390, 250, 30, 30);
        settingContentPanel.add(cardRadioBtn3);
        imgURL = Vue.Main.class.getResource("/res/images/back (3).png");
        JLabel cardBack3 = new JLabel(SwingUtil.createAutoAdjustIcon(imgURL, true));
        cardBack3.setBounds(420, 250, 60, 90);
        settingContentPanel.add(cardBack3);



        btnCardGroup = new ButtonGroup();
        btnCardGroup.add(cardRadioBtn1);
        btnCardGroup.add(cardRadioBtn2);
        btnCardGroup.add(cardRadioBtn3);


        //&#x5206;&#x5272;&#x7EBF;2 &#x8BBE;&#x7F6E;&#x53F3;&#x8FB9;&#x80CC;&#x666F;
        JLabel seprate2 = new JLabel("");
        seprate2.setBounds(0, 390, 10000, 1);
        seprate2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        settingContentPanel.add(seprate2);


        settingShowCard = new JLabel(" set right background");
        settingShowCard.setBounds(30, 400, 230, 30);
        settingShowCard.setFont(f1);
        settingContentPanel.add(settingShowCard);


        String rightback= System.getProperty("backright");
        int rightbacki =Integer.parseInt(rightback);

        if (rightbacki == 0)
            rightbackradioBtn1 = new JRadioButton("", true);
        else rightbackradioBtn1 = new JRadioButton("", false);
        rightbackradioBtn1.setBounds(60, 450, 30, 30);
        settingContentPanel.add(rightbackradioBtn1);
        imgURL = Vue.Main.class.getResource("/res/images/backright (1).png");
        JLabel rightback1 = new JLabel(SwingUtil.createAutoAdjustIcon(imgURL, true));
        rightback1.setBounds(90, 450, 60, 90);
        settingContentPanel.add(rightback1);

        if (rightbacki == 1)
            rightbackradioBtn2 = new JRadioButton("", true);
        else rightbackradioBtn2 = new JRadioButton("", false);
        rightbackradioBtn2.setBounds(230, 450, 30, 30);
        settingContentPanel.add(rightbackradioBtn2);
        imgURL = Vue.Main.class.getResource("/res/images/backright (2).png");
        JLabel rightback2 = new JLabel(SwingUtil.createAutoAdjustIcon(imgURL, true));
        rightback2.setBounds(260, 450, 60, 90);
        settingContentPanel.add(rightback2);

        if (rightbacki == 2)
            rightbackradioBtn3 = new JRadioButton("", true);
        else rightbackradioBtn3 = new JRadioButton("", false);
        rightbackradioBtn3.setBounds(390, 450, 30, 30);
        settingContentPanel.add(rightbackradioBtn3);
        imgURL = Vue.Main.class.getResource("/res/images/backright (3).png");
        JLabel rightback3 = new JLabel(SwingUtil.createAutoAdjustIcon(imgURL, true));
        rightback3.setBounds(420, 450, 60, 90);
        settingContentPanel.add(rightback3);

        btnrightBackGroup = new ButtonGroup();
        btnrightBackGroup.add(rightbackradioBtn1);
        btnrightBackGroup.add(rightbackradioBtn2);
        btnrightBackGroup.add(rightbackradioBtn3);

        settingContentPanel.setLayout(null);

        settingJframe.setVisible(true);
    }

    //&#x6DFB;&#x52A0;setting&#x754C;&#x9762;&#x76D1;&#x542C;
    public void addSettingActionListener() {
//        cardRadioBtn1.addActionListener(this);
//        cardRadioBtn2.addActionListener(this);
//        cardRadioBtn3.addActionListener(this);
//        backradioBtn1.addActionListener(this);
//        backradioBtn2.addActionListener(this);
//        backradioBtn3.addActionListener(this);
//        cardShowBtn1.addActionListener(this);
//        cardShowBtn2.addActionListener(this);
        settingGetBackBtn.addActionListener(this);
        settingSaveBackBtn.addActionListener(this);

    }

    public static void setValue() {
        System.setProperty("background", "0");
        System.setProperty("back", "0");
        System.setProperty("backright", "0");
    }
}

