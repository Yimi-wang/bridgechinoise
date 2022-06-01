package Vue;


import Controleur.SaveLoadVue;
import Modele.*;
import global.Configuration;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import static java.lang.System.exit;

public class InterfaceIA implements Runnable {

    public Brand selected;
    Jeu j;
    int player2;
    int player1;
    int IA;
    Atout a;
    Histoire h;
    JLabel Atout;
    int a1 = 0;
    JavaClip m;

    boolean showcard;

    //构�?�函�?
    public InterfaceIA() {
    }

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        SwingUtilities.invokeLater(new InterfaceIA());
    }

    public void run() {
        //MusicTest m = new MusicTest();
        // m.play();
        //不知道用�?
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        //ȷ��jue��ͷ���ֿ�
        j = creatJeu();
        gameStart(j, h);
        //gameprocessvue.gameMode(j);（启动游戏）

        //界面
        String aimode = null;
        switch (j.AI) {
            case 1:
                aimode = "AI simple";
                break;
            case 2:
                aimode = "AI moyenne";
                break;
            case 3:
                aimode = "AI difficile";
                break;
        }
        JFrame frame = new JFrame("Bridge Chinoise "+ aimode);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //end
        frame.setPreferredSize(new Dimension(1200,800));

        GamePaneIA cardinterface = new GamePaneIA(j, this, frame,h);

        cardinterface.setLayout(null);

        frame.setMinimumSize(new Dimension(1200, 800));
        m=new JavaClip();
        m.playgamemusic();
        /**
         * ��Ϸ�˵���ʵ��
         */

        //���˵�
        JMenuBar menuBar = new JMenuBar();
        JMenu saveMenu = new JMenu("Sauvegarder");
        JMenu backMenu = new JMenu("Retour");
        JMenu surrenderMenu = new JMenu("Abondonner");
        JMenu showcardMenu = new JMenu("Montrer la carte");
        JMenu music = new JMenu("Musique");
        JMenu helpMenu = new JMenu("Aide");
        JMenu quitterMenu = new JMenu("Quit");
        menuBar.add(saveMenu);
        menuBar.add(backMenu);
        menuBar.add(surrenderMenu);
        menuBar.add(showcardMenu);
        menuBar.add(music);
        menuBar.add(helpMenu);
        menuBar.add(quitterMenu);
        /**
         * Save���˵��ͼ����Լ����ڷ���
         */
        //Save�˵��Ӳ˵�
        JMenuItem saveMenuItem01 = new JMenuItem("Sauvegarder et continuer");
        JMenuItem saveMenuItem02 = new JMenuItem("Sauvegarder et quitter");
        saveMenu.add(saveMenuItem01);
        saveMenu.add(saveMenuItem02);
        saveMenuItem01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                SaveLoadVue slv = new SaveLoadVue();

                JFrame saveframe = new JFrame("Sauvegarder  !");
                saveframe.setSize(280, 120);
                /*
                 * ImageIcon savelogo = new ImageIcon("res/images/Logo.png"); JLabel
                 * savelogolabel = new JLabel(savelogo); savelogolabel.setBounds(30, 20, 300,
                 * 100); savelogo.setImage(savelogo.getImage().getScaledInstance(savelogolabel.
                 * getWidth(), savelogolabel.getHeight(), Image.SCALE_DEFAULT));
                 * saveframe.add(savelogolabel, BorderLayout.NORTH);
                 */


                JButton saveconfirm = new JButton("Sauvegarder ");

                JButton savecancel = new JButton("Quitter");

                JLabel saveinfoLabel = new JLabel("Saissisez le nom du fichier �� sauvegarder ");

                JTextField savenameField = new JTextField("defautSaveFile");

                Box savebuttonBox = Box.createHorizontalBox();
                savebuttonBox.add(saveconfirm);
                savebuttonBox.add(savecancel);

                Box savecontentBox = Box.createVerticalBox();
                savecontentBox.add(saveinfoLabel);
                savecontentBox.add(savenameField);

                saveconfirm.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        slv.Save(h, savenameField.getText());
                        saveframe.dispose();
                    }
                });
                savecancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saveframe.dispose();
                    }
                });


                saveframe.setLayout(new BorderLayout(20, 20));
                saveframe.setLocationRelativeTo(null);

                saveframe.add(savecontentBox, BorderLayout.CENTER);
                saveframe.add(savebuttonBox, BorderLayout.SOUTH);

                saveframe.setVisible(true);

            }
        });
        saveMenuItem02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                SaveLoadVue slv = new SaveLoadVue();

                JFrame saveframe = new JFrame("Sauvegarder !");
                saveframe.setSize(200, 120);
                /*
                 * ImageIcon savelogo = new ImageIcon("res/images/Logo.png"); JLabel
                 * savelogolabel = new JLabel(savelogo); savelogolabel.setBounds(30, 20, 300,
                 * 100); savelogo.setImage(savelogo.getImage().getScaledInstance(savelogolabel.
                 * getWidth(), savelogolabel.getHeight(), Image.SCALE_DEFAULT));
                 * saveframe.add(savelogolabel, BorderLayout.NORTH);
                 */


                JButton saveconfirm = new JButton("Sauvegarder");

                JButton savecancel = new JButton("Quitter");

                JLabel saveinfoLabel = new JLabel("Saissisez le nom du fichier �� sauvegarder");

                JTextField savenameField = new JTextField("defautSaveFile");

                Box savebuttonBox = Box.createHorizontalBox();
                savebuttonBox.add(saveconfirm);
                savebuttonBox.add(savecancel);

                Box savecontentBox = Box.createVerticalBox();
                savecontentBox.add(saveinfoLabel);
                savecontentBox.add(savenameField);

                saveconfirm.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        slv.Save(h, savenameField.getText());
                        saveframe.dispose();
                        m.stop();
                        frame.dispose();
                        try {
                            Main.backmenu();
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                savecancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saveframe.dispose();
                    }
                });


                saveframe.setLayout(new BorderLayout(20, 20));
                saveframe.setLocationRelativeTo(null);

                saveframe.add(savecontentBox, BorderLayout.CENTER);
                saveframe.add(savebuttonBox, BorderLayout.SOUTH);

                saveframe.setVisible(true);

            }
        });

        /**
         * Back���˵��ͼ����Ͷ�Ӧ����
         */
        //Back�˵��Ӳ˵�
        JMenuItem backMenuItem01 = new JMenuItem("Retourner à ton tour");
        JMenuItem backMenuItem02 = new JMenuItem("Redémarrer");
        backMenu.add(backMenuItem01);
        backMenu.add(backMenuItem02);
        //�����ͷ���
        backMenuItem01.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int pn = j.playerNow;
                j=h.returnHistoire();
                while(j.playerNow!=pn)
                    j=h.returnHistoire();
                m.stop();
                frame.dispose();
                InterfaceIALoad ijl = new InterfaceIALoad(h);
                ijl.run();
            }

        });
        backMenuItem02.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                j=h.returnHistoire();
                while(j.numberOfRounds!=1||j.TurnProcess!=1)
                    j=h.returnHistoire();
                m.stop();
                frame.dispose();
                InterfaceIALoad ijl = new InterfaceIALoad(h);
                ijl.run();

            }
        });
        /**
         * Surrender ���˵��ͼ����Ͷ�Ӧ����
         */
        //surrender�˵��Ӳ˵�
        JMenuItem surrenderMenuItem01 = new JMenuItem("Abondonner ce jeu");
        JMenuItem surrenderMenuItem02 = new JMenuItem("Abondonner tous les jeux");
        surrenderMenu.add(surrenderMenuItem01);
        surrenderMenu.add(surrenderMenuItem02);
        //����
        surrenderMenuItem01.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                cardinterface.surrenderthisgame();
            }
        });
        surrenderMenuItem02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardinterface.wingamewindow(j, 2, j.playerNow);
            }
        });
        /**
         * showcard���˵��ͼ����Ͷ�Ӧ����
         */
        //shoucard�˵��Ӳ˵�
        final JRadioButtonMenuItem showcardTrue = new JRadioButtonMenuItem("oui");
        final JRadioButtonMenuItem showcardFalse = new JRadioButtonMenuItem("non");
        showcardMenu.add(showcardTrue);
        showcardMenu.add(showcardFalse);
        // �������� ��ѡ��ť�Ӳ˵���Ҫʵ�ֵ�ѡ��ť��Ч������Ҫ�����Ƿŵ�һ����ť����
        ButtonGroup showcardgroup = new ButtonGroup();
        showcardgroup.add(showcardTrue);
        showcardgroup.add(showcardFalse);

        // Ĭ�ϵ�һ����ѡ��ť�Ӳ˵�ѡ��
        showcardFalse.setSelected(true);

        showcardTrue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showcard = true;
                cardinterface.repaint();
            }
        });
        showcardFalse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showcard = false;
                cardinterface.repaint();
            }
        });


        final JRadioButtonMenuItem mTrue = new JRadioButtonMenuItem("oui");
        final JRadioButtonMenuItem mFalse = new JRadioButtonMenuItem("non");
        music.add(mTrue);
        music.add(mFalse);
        // �������� ��ѡ��ť�Ӳ˵���Ҫʵ�ֵ�ѡ��ť��Ч������Ҫ�����Ƿŵ�һ����ť����
        ButtonGroup mgroup = new ButtonGroup();
        mgroup.add(mTrue);
        mgroup.add(mFalse);

        // Ĭ�ϵ�һ����ѡ��ť�Ӳ˵�ѡ��
        mTrue.setSelected(true);

        mTrue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.stop();
                m.playgamemusic();
            }
        });
        mFalse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.stop();
            }
        });
        /**
         * help���˵��ͼ����Ͷ�Ӧ������
         */
        //help �˵��Ӳ˵�
        JMenuItem helpMenuItem01 = new JMenuItem("Aide");
        JMenuItem helpMenuItem02 = new JMenuItem("Règles");
        helpMenu.add(helpMenuItem01);
        helpMenu.add(helpMenuItem02);
        //����
        helpMenuItem01.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Comment jouer:\n" +
                        "        Jouer la carte : Cliquez sur la carte puis cliquez sur jouer pour jouer la carte.\n" +
                        "        Piocher une carte : Cliquez sur le paquet pour prendre une carte\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Fonction barre de menus\n" +
                        "        Sauvegarder et continuer : Entrez le nom à sauvegarder, le jeu continue\n" +
                        "        Sauvegarder et quitter : Saisissez le nom à sauvegarder, le jeu est terminée\n" +
                        "        Retour à votre tour : revenez au tour précédent (si c'était votre tour de jeu, revenez à votre dernier tour de pioche)\n" +
                        "\n" +
                        "        Redémarrer : redémarrer le jeu\n" +
                        "\n" +
                        "        Abandonnez ce jeu : Abandonnez ce jeu et le joueur adverse obtiendra des points pour les tours restants.\n" +
                        "        Abandonnez tout le jeu : Abandonnez ce jeu. L'adversaire gagne\n" +
                        "\n" +
                        "        Montrer le carte : Montrez la carte de l'adversaire", "Aide", JOptionPane.QUESTION_MESSAGE);
            }
        });
        helpMenuItem02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    File pdfFile = new File("regle.pdf");
                    if (pdfFile.exists()) {

                        if (Desktop.isDesktopSupported()) {
                            Desktop.getDesktop().open(pdfFile);
                        } else {
                            System.out.println("Awt Desktop is not supported!");
                        }

                    } else {
                        System.out.println("File is not exists!");
                    }

                    System.out.println("Done");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        JMenuItem quitMenuItem01 = new JMenuItem("Quitter le Jeu");
        JMenuItem quitMenuItem02 = new JMenuItem("Return to Menu");
        quitterMenu.add(quitMenuItem01);
        quitterMenu.add(quitMenuItem02);
        //�����ͷ���
        quitMenuItem01.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit(0);
            }

        });
        quitMenuItem02.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m.stop();
                frame.dispose();
                try {
                    Main.backmenu();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        //��ӵ�frame
        frame.setJMenuBar(menuBar);
        frame.add(cardinterface);

        //��С����
        frame.pack();
        //λ�þ���
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public Jeu creatJeu() {
        player1 = 0;
        player2 = 0;
        j = new Jeu();
        a = new Atout(j);
        h = new Histoire(j);
        String GameMode = Configuration.instance().lis("GameMode");
        int GameModei = Integer.parseInt(GameMode);
        if (GameModei > 2) {
            String GameInformation = Configuration.instance().lis("GameInformation");
            int GameInformationi = Integer.parseInt(GameInformation);
            j.GameInformation = GameInformationi;
        }

        String AI = Configuration.instance().lis("AI");
        int AIi = Integer.parseInt(AI);
        j.AI = AIi;
        j.GameMode = GameModei;

        j.playerFirst = 2;
        IA = j.AI;
        StartHand startHand = new StartHand(j);
        startHand.stardHand();
        //gameMode(j);
        return j;
    }

    public void gameStart(Jeu j, Histoire h) {
        j.reset();
        if (j.numberOfGames == 0) j.numberOfGames = 1;//如果游戏刚开始的�?
        if (j.playerFirst == 2) {//如果本轮该开始的话，判断哪个玩家先开始游戏�??
            j.playerFirst = (j.numberOfGames - 1) % 2;
            j.numberOfRounds = 1;
            //进行发牌以及牌堆的实�?
            if (j.numberOfGames != 1) {
                StartHand startHand = new StartHand(j);
                startHand.stardHand();
            }
            a.determinerAtout();
            Jeu j0 = (Jeu) j.clone();
            h.ajouteListDeHistoire(j0);
            Jeu j100 = (Jeu) j.clone();
            h.ajouteListDeHistoire(j100);

        }
    }

}