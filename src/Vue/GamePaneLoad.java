package Vue;

import Controleur.IASimpleVue;
import Controleur.PlayCardsVue;
import Controleur.TakeCardVue;
import Modele.*;
//import global.ConfigurationSetting;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;

public class GamePaneLoad extends JPanel {
    InterfaceJeuLoad ifjgp;
    JFrame fgp;
    Histoire h;
    JFrame frame;
    Jeu j;
    JButton player0play, player1play, player0Suggest, player1Suggest;
    PlayCardsVue playcard;
    TakeCardVue takecard;
    Brand player1playercard;
    Brand player2playercard;
    private Map<Brand, Rectangle> mapCards;

    public GamePaneLoad(Jeu j, InterfaceJeuLoad ifj, JFrame f, Histoire h) {
        //点击鼠标，如果点击是卡的位置，则会将卡向上移，不是的话已经移动过的卡回�??原来的位置）

        this.j = j;
        this.ifjgp = ifj;
        this.fgp = f;
        this.h=h;

        playcard = new PlayCardsVue(j, ifjgp.h);
        takecard = new TakeCardVue(j, ifjgp.h);

        mapCards = new HashMap<>(j.playercard[0].size());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (ifjgp.selected != null) {
                    Rectangle bounds = mapCards.get(ifjgp.selected);
                    if (ifjgp.selected.getPlace() == 0)
                        mapCards.get(ifjgp.selected).y += 30;
                    else if (ifjgp.selected.getPlace() == 1)
                        mapCards.get(ifjgp.selected).y -= 30;
                    repaint();
                }
                ifjgp.selected = null;
                if (j.TurnProcess == 1) {
                    if (j.playerFirst == 0) {
                        for (Brand card : j.playercard[0]) {
                            //Rectangle bounds = mapCards.get(card);
                            if (mapCards.get(card).contains(e.getPoint())) {
                                ifjgp.selected = card;
                                mapCards.get(card).y -= 30;
                                //打印玩家手牌
                                //player0play.setText("PLAY");
                                System.out.println(card.toString());
                                repaint();
                                break;
                            }
                        }
                    } else {
                        for (Brand card : j.playercard[1]) {
                            Rectangle bounds = mapCards.get(card);
                            if (bounds.contains(e.getPoint())) {
                                ifjgp.selected = card;
                                bounds.y += 30;
                                //打印玩家手牌
                                //player1play.setText("PLAY");
                                System.out.println(card.toString());
                                //repaint();
                                break;
                            }

                        }
                    }
                } else if (j.TurnProcess == 2) {
                    for (Brand card : j.playercard[0]) {
                        if (playcard.limite(j, card)) {
                            Rectangle bounds = mapCards.get(card);
                            if (bounds.contains(e.getPoint())) {
                                ifjgp.selected = card;
                                bounds.y -= 30;
                                //打印玩家手牌
                                //player0play.setText("PLAY");
                                System.out.println(card.toString());
                                repaint();
                                break;
                            }
                        }
                    }
                    for (Brand card : j.playercard[1]) {
                        if (playcard.limite(j, card)) {
                            Rectangle bounds = mapCards.get(card);
                            if (bounds.contains(e.getPoint())) {
                                ifjgp.selected = card;
                                bounds.y += 30;
                                //打印玩家手牌
                                //player1play.setText("PLAY");
                                System.out.println(card.toString());
                                repaint();
                                break;
                            }
                        }
                    }
                } else if (j.TurnProcess >= 3 && j.numberOfRounds <= 15) {
                    for (int i = 0; i <= 5; i++) {
                        if (j.pilescard[i].size() > 0) {
                            Brand card = j.pilescard[i].get(0);
                            Rectangle bounds = mapCards.get(card);
                            if (bounds.contains((e.getPoint()))) {
                                if (j.TurnProcess == 3) takecard.playerWinTakeCard(j, card);
                                else takecard.playerLoseTakeCard(j, card);
                                drawCHANGFANGXING(j);
                                repaint();
                            }
                        }
                    }
                }

            }
        });

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1200, 800);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        mapCards.clear();
        this.removeAll();
        this.repaint();
        int height=getHeight();
        player0play = new JButton("JOUER");
        player0play.setBackground(Color.blue);
        player0play.setBounds(getWidth() /100*25, height / 20 * 15, getWidth() / 15, height / 20);
        this.add(player0play);
        player0Suggest = new JButton("Suggestion");
        player0Suggest.setBackground(Color.blue);
        player0Suggest.setBounds(getWidth() / 100 * 45, height / 20 * 15, getWidth() / 10, height / 20);
        this.add(player0Suggest);

        player1play = new JButton("JOUER");
        player1play.setBackground(Color.red);
        player1play.setBounds(getWidth() / 100*25, height / 20 * 4, getWidth() / 15, height / 20);
        this.add(player1play);
        player1Suggest = new JButton("Suggestion");
        player1Suggest.setBackground(Color.red);
        player1Suggest.setBounds(getWidth() / 100 * 45, height / 20 * 4, getWidth() / 10, height / 20);
        this.add(player1Suggest);

        player0play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ifjgp.selected != null) {
                    if (j.playerNow == 0) {
                        System.out.println("playcard");
                        player1playercard = ifjgp.selected;
                        if (j.playerNow == j.playerFirst) playcard.playerFirstPlayCard(j, ifjgp.selected);
                        else {
                            playcard.playerSecondePlayCard(j, ifjgp.selected);
                            estFINI(j, ifjgp.h);
                        }
                        drawCHANGFANGXING(j);
                        //TODO Can't make the button disappear
                        repaint();
                        ifjgp.selected = null;
                        estFINI(j,h);
                    }
                } else {
                    System.out.println("Please choose your card.");
                }
            }
        });

        player0Suggest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (j.playerNow == 0&&j.TurnProcess<3) {
                    Rectangle bounds;
                    if (ifjgp.selected != null) {
                        bounds = mapCards.get(ifjgp.selected);
                        bounds.y += 30;
                    }
                    System.out.println("playcard");
                    IASimpleVue ia = new IASimpleVue(j);
                    int index = ia.IASimplePlayerCard(0);
                    ifjgp.selected = j.playercard[0].get(index);
                    bounds = mapCards.get(ifjgp.selected);
                    bounds.y -= 30;
                    repaint();
                    //player0play.setText("PLAY");
                }
            }
        });

        player1play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ifjgp.selected != null) {
                    if (j.playerNow == 1) {
                        System.out.println("playcard");
                        player2playercard = ifjgp.selected;
                        if (j.playerNow == j.playerFirst) playcard.playerFirstPlayCard(j, ifjgp.selected);
                        else {
                            playcard.playerSecondePlayCard(j, ifjgp.selected);
                            estFINI(j, ifjgp.h);
                        }
                        drawCHANGFANGXING(j);
                        repaint();
                        ifjgp.selected = null;
                        estFINI(j,h);
                    }
                }
            }
        });

        player1Suggest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (j.playerNow == 1&&j.TurnProcess<3) {
                    Rectangle bounds;
                    if (ifjgp.selected != null) {
                        bounds = mapCards.get(ifjgp.selected);
                        bounds.y -= 30;
                    }
                    System.out.println("playcard");
                    IASimpleVue ia = new IASimpleVue(j);
                    int index = ia.IASimplePlayerCard(1);
                    ifjgp.selected = j.playercard[1].get(index);
                    bounds = mapCards.get(ifjgp.selected);
                    bounds.y += 30;
                    repaint();
                    //player1play.setText("PLAY");
                }
            }
        });

        //右边游戏信息
        drawCHANGFANGXING(j);
    }

    public void drawCHANGFANGXING(Jeu j) {
        //画长方形，并且将每个长方形和card绑定
        //确定每个卡片的高与宽
        int height = getHeight();
        int cardHeight = (height - 20) / 8;
        int cardWidth = (int) (cardHeight * 0.6);
        //每个卡片的偏移量
        int xDelta = cardWidth + 5;
        //第一个卡片的横坐标和纵坐�?
        int xPos = getWidth() / 10;
        int yPos = height / 100 * 90;
        //建立�?个hashmap，使每个长方形和每个卡片进行对应。显示下方玩家的手牌�?
        for (Brand card : j.playercard[0]) {
            Rectangle bounds = new Rectangle(xPos, yPos, cardWidth, cardHeight);
            mapCards.put(card, bounds);
            xPos += xDelta;
        }
        //显示上方的手�?
        xPos = getWidth() / 10;
        yPos = height / 40;
        for (Brand card : j.playercard[1]) {
            Rectangle bounds = new Rectangle(xPos, yPos, cardWidth, cardHeight);
            mapCards.put(card, bounds);
            xPos += xDelta;
        }
        //显示pile的手卡�??
        int xOri = 60;
        xPos = getWidth() / 100 * xOri;
        xDelta = cardWidth / 5;
        yPos = height / 2 - cardHeight / 2;
        for (int i = 0; i <= 5; i++) {
            for (int a = j.pilescard[i].size() - 1; a >= 0; a--) {
                Brand card = j.pilescard[i].get(a);
                Rectangle bounds = new Rectangle(xPos, yPos, cardWidth, cardHeight);
                mapCards.put(card, bounds);
                xPos -= xDelta;
            }
            xOri -= 10;
            xPos = getWidth() / 100 * xOri;
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        String back = System.getProperty("back");
        int backi = Integer.parseInt(back);
        backi++;
        //TODO setting background.
        //背景
        String background = System.getProperty("background");
        int backgroundi = Integer.parseInt(background);
        backgroundi++;
        java.net.URL imgURL = Vue.Main.class.getResource("/res/images/background (" + backgroundi + ").png");
        ImageIcon imageBackGround = new ImageIcon(imgURL);
        java.awt.Image image = imageBackGround.getImage();
        int middle = getWidth() / 5 * 4;
        int height = getHeight() + 1;
        g2d.drawImage(image, 0, 0, getWidth() / 5 * 4, height, null);


        //右边背景

        ImageIcon imageBackGroundRight;
        String rightback = System.getProperty("backright");
        int rightbacki = Integer.parseInt(rightback);
        rightbacki++;
        imgURL = Vue.Main.class.getResource("/res/images/backright (" + rightbacki + ").png");
        imageBackGroundRight = new ImageIcon(imgURL);
        image = imageBackGroundRight.getImage();
        g2d.drawImage(image, getWidth() / 5 * 4, 0, getWidth(), height, null);

        ImageIcon imageIcon;

        if (j.TurnProcess == 2 || j.TurnProcess == 3) {
            //鐢诲厛鎵嬫柟鍑虹殑鐗?
            if (j.TurnProcess == 2) {
                BufferedImage imageCard;
                File imgFilecard = null;
                if (j.playerFirst == 0) {
                    imgURL = Vue.Main.class.getResource("/res/images/card (" + j.FirstPlayerPlayCard.id + ").png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();
                } else {
                    imgURL = Vue.Main.class.getResource("/res/images/card (" + j.FirstPlayerPlayCard.id + ").png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();
                }
                if (j.playerNow == 1) {
                    ifjgp.a1 = 1;
                    int cardHeight = (height - 20) / 8;
                    int cardWidth = (int) (cardHeight * 0.6);
                    g2d.drawImage(image, getWidth() / 3, height / 20 * 12, cardWidth, cardHeight, null);
                } else {
                    ifjgp.a1 = 0;
                    int cardHeight = (height - 20) / 8;
                    int cardWidth = (int) (cardHeight * 0.6);
                    g2d.drawImage(image, getWidth() / 3, height / 20 * 5, cardWidth, cardHeight, null);
                }
            }
            //鎵撳嵃鍙屾柟鍑虹殑鐗?
            if (j.TurnProcess == 3) {
                //鎵撳嵃鍏堟墜鏂瑰嚭鐨勭墝
                player1playercard=j.lastgamep0playcard;
                imgURL = Vue.Main.class.getResource("/res/images/card (" + player1playercard.id + ").png");
                imageIcon = new ImageIcon(imgURL);
                image = imageIcon.getImage();
                int cardHeight = (height - 20) / 8;
                int cardWidth = (int) (cardHeight * 0.6);
                g2d.drawImage(image, getWidth() / 3, height / 20 * 12, cardWidth, cardHeight, null);

                //鍚庢墜鏂瑰嚭鐨勭墝
                player2playercard=j.lastgamep1playcard;
                imgURL = Vue.Main.class.getResource("/res/images/card (" + player2playercard.id + ").png");
                imageIcon = new ImageIcon(imgURL);
                image = imageIcon.getImage();
                g2d.drawImage(image, getWidth() / 3, height / 20 * 5, cardWidth, cardHeight, null);

            }
        }
        for (Brand card : j.playercard[1]) {
            Rectangle bounds = mapCards.get(card);
            //鏍规嵁闀挎柟褰㈢殑浣嶇疆锛屽～鍏呭浘鐗?
            if (bounds != null) {

                if (ifjgp.showcard || j.playerNow == 1) {
                    if ((j.playerFirst != j.playerNow) && !playcard.limite(j, card) && j.playerNow == 1 && j.TurnProcess == 2) {
                        imgURL = Vue.Main.class.getResource("/res/images/BBcard (" + card.id + ").png");
                        imageIcon = new ImageIcon(imgURL);
                        image = imageIcon.getImage();
                    } else {
                        imgURL = Vue.Main.class.getResource("/res/images/card (" + card.id + ").png");
                        imageIcon = new ImageIcon(imgURL);
                        image = imageIcon.getImage();
                    }
                    int cardHeight = (height - 20) / 8;
                    int cardWidth = (int) (cardHeight * 0.6);
                    //鐢诲浘鍍?
                    g2d.drawImage(image, bounds.x, bounds.y, cardWidth, cardHeight, null);
                    //鐢婚暱鏂瑰舰杈规
                    g2d.setColor(Color.red);
                    g2d.draw(bounds);
                } else {
                    imgURL = Vue.Main.class.getResource("/res/images/back (" + backi + ").png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();
                    int cardHeight = (height - 20) / 8;
                    int cardWidth = (int) (cardHeight * 0.6);
                    //鐢诲浘鍍?
                    g2d.drawImage(image, bounds.x, bounds.y, cardWidth, cardHeight, null);
                    //鐢婚暱鏂瑰舰杈规
                    g2d.setColor(Color.red);
                    g2d.draw(bounds);
                }

            }
        }

        switch (j.TurnProcess) {
            case 1:
                if (j.playerNow == 0) {
                    imgURL = Vue.Main.class.getResource("/res/images/j1j-modified.png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();
                } else {
                    imgURL = Vue.Main.class.getResource("/res/images/j2j-modified.png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();
                }
                break;
            case 2:
                if (j.playerNow == 0) {
                    imgURL = Vue.Main.class.getResource("/res/images/j1j-modified.png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();
                } else {
                    imgURL = Vue.Main.class.getResource("/res/images/j2j-modified.png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();
                }
                break;
            case 3:
                if (j.playerNow == 0) {
                    imgURL = Vue.Main.class.getResource("/res/images/j1p-modified.png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();
                } else {
                    imgURL = Vue.Main.class.getResource("/res/images/j2p-modified.png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();
                }
                break;
            case 4:
                if (j.playerNow == 0) {
                    imgURL = Vue.Main.class.getResource("/res/images/j1p-modified.png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();
                } else {
                    imgURL = Vue.Main.class.getResource("/res/images/j2p-modified.png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();
                }
                break;
        }

        g2d.drawImage(image, getWidth() / 100 * 70, height / 100 * 45, getWidth() / 100 * 10, getWidth() / 100 * 10, null);


        for (Brand card : j.playercard[0]) {
            Rectangle bounds = mapCards.get(card);

            //鏍规嵁闀挎柟褰㈢殑浣嶇疆锛屽～鍏呭浘鐗?
            if (bounds != null) {
                BufferedImage imageCard;
                if (ifjgp.showcard || j.playerNow == 0) {
                    if ((j.playerFirst != j.playerNow) && !playcard.limite(j, card) && j.playerNow == 0 && j.TurnProcess == 2) {
                        imgURL = Vue.Main.class.getResource("/res/images/BBcard (" + card.id + ").png");
                        imageIcon = new ImageIcon(imgURL);
                        image = imageIcon.getImage();

                    } else {
                        imgURL = Vue.Main.class.getResource("/res/images/card (" + card.id + ").png");
                        imageIcon = new ImageIcon(imgURL);
                        image = imageIcon.getImage();
                    }
                    int cardHeight = (height - 20) / 8;
                    int cardWidth = (int) (cardHeight * 0.6);
                    //鐢诲浘鍍?
                    g2d.drawImage(image, bounds.x, bounds.y, cardWidth, cardHeight, null);
                    //鐢婚暱鏂瑰舰杈规
                    g2d.setColor(Color.blue);
                    g2d.draw(bounds);
                } else {
                    imgURL = Vue.Main.class.getResource("/res/images/back (" + backi + ").png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();
                    int cardHeight = (height - 20) / 8;
                    int cardWidth = (int) (cardHeight * 0.6);
                    //鐢诲浘鍍?
                    g2d.drawImage(image, bounds.x, bounds.y, cardWidth, cardHeight, null);
                    //鐢婚暱鏂瑰舰杈规
                    g2d.setColor(Color.blue);
                    g2d.draw(bounds);
                }

            }
        }

        for (int i = 0; i <= 5; i++) {

            for (int a = j.pilescard[i].size() - 1; a >= 0; a--) {
                Brand card = j.pilescard[i].get(a);
                Rectangle bounds = mapCards.get(card);

                //鏍规嵁闀挎柟褰㈢殑浣嶇疆锛屽～鍏呭浘鐗?
                if (bounds != null) {
                    BufferedImage imageCard;
                    File imgFile;
                    if (a == 0) {
                        if (j.TurnProcess < 3) {
                            imgURL = Vue.Main.class.getResource("/res/images/Bcard (" + card.id + ").png");
                            imageIcon = new ImageIcon(imgURL);
                            image = imageIcon.getImage();
                        } else {
                            imgURL = Vue.Main.class.getResource("/res/images/card (" + card.id + ").png");
                            imageIcon = new ImageIcon(imgURL);
                            image = imageIcon.getImage();
                        }

                    } else {
                        imgURL = Vue.Main.class.getResource("/res/images/back (" + backi + ").png");
                        imageIcon = new ImageIcon(imgURL);
                        image = imageIcon.getImage();

                    }


                    int cardHeight = (height - 20) / 8;
                    int cardWidth = (int) (cardHeight * 0.6);
                    //鐢诲浘鍍?
                    g2d.drawImage(image, bounds.x, bounds.y, cardWidth, cardHeight, null);
                    //鐢婚暱鏂瑰舰杈规
                    g2d.setColor(Color.BLACK);
                    g2d.draw(bounds);
                }
            }
        }

        String[] dfonts;
        dfonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        //杈撳嚭鍙宠竟娓告垙淇℃伅銆?
        //Atout 鏂囧瓧
        g2d.setColor(Color.red);
        g2d.setFont(new Font(dfonts[1], Font.BOLD, 30));

        g2d.drawString("Atout", middle + middle / 15, height / 15);
        //BRAND 鍥剧墖
        if (j.avoiratout) {
            imgURL = Vue.Main.class.getResource("/res/images/Atout"+ j.atout.getInttype() + ".png");
            imageIcon = new ImageIcon(imgURL);
            image = imageIcon.getImage();



        } else {
            imgURL = Vue.Main.class.getResource("/res/images/card (" + backi + ").png");
            imageIcon = new ImageIcon(imgURL);
            image = imageIcon.getImage();

        }

        g2d.drawImage(image, middle + middle / 15, height / 100 * 8, getWidth() / 100 * 7, (getWidth() / 100 * 7) / 6 * 10, null);
        g2d.setColor(Color.ORANGE);
        g2d.setFont(new Font("Calibri", Font.ITALIC, 18));
        g2d.drawString("Cartes joué au tour précédent", middle + middle / 100, getHeight() / 100 * 35);
        if (j.lastgamep1playcard != null) {
            g2d.setFont(new Font(dfonts[5], Font.BOLD, 20));
            g2d.drawString("Joueur 1 ", middle + middle / 40, getHeight() / 100 * 38);


            imgURL = Vue.Main.class.getResource("/res/images/card (" + j.lastgamep0playcard.id + ").png");
            imageIcon = new ImageIcon(imgURL);
            image = imageIcon.getImage();


            g2d.drawImage(image, middle + middle / 40, getHeight() / 100 * 40, getWidth() / 100 * 7, (getWidth() / 100 * 7) / 6 * 10, null);
            g2d.drawString("Joueur 2 ", middle + middle / 8, getHeight() / 100 * 38);

            imgURL = Vue.Main.class.getResource("/res/images/card (" + j.lastgamep1playcard.id + ").png");
            imageIcon = new ImageIcon(imgURL);
            image = imageIcon.getImage();



            g2d.drawImage(image, middle + middle / 8, getHeight() / 100 * 40, getWidth() / 100 * 7, (getWidth() / 100 * 7) / 6 * 10, null);


        }
        //GameMode 娓告垙妯″紡杈撳嚭
        String GameModeStr = "GameMode est :";
        String Gamemode1 = null;
        String GameMode2 = null;
        switch (j.GameMode) {
            case 1:
                GameModeStr = GameModeStr.concat("BO1");
                break;
            case 2:
                GameModeStr = GameModeStr.concat("BO3");
                break;
            case 3:
                Gamemode1 = "Nombre fixe de tours";
                GameMode2 = "Le tours pour gagner est " + j.GameInformation;
                break;
            case 4:
                Gamemode1 = "Nombre fix de Score";
                GameMode2 = "Le score pour gagner est " + j.GameInformation;
                break;
        }
        g2d.setColor(Color.black);
        g2d.setFont(new Font(dfonts[2], Font.BOLD, 18));
        g2d.drawString(GameModeStr, middle + middle / 100, getHeight() / 100 * 65);
        if (GameMode2 != null) {
            g2d.drawString(Gamemode1, middle + middle / 100, getHeight() / 100 * 70);
            g2d.drawString(GameMode2, middle + middle / 100, getHeight() / 100 * 75);
        }

        //numbre de tour  鍥炲悎鏁?
        String nbtour = "Le nombre de tour est " + j.numberOfRounds;
        g2d.setColor(Color.blue);
        g2d.setFont(new Font("Monaco", Font.PLAIN, 20));
        g2d.drawString(nbtour, middle + middle / 100, getHeight() / 100 * 80);

        //鏄剧ず鐜╁1寰楀垎
        g2d.setColor(Color.blue);
        g2d.setFont(new Font("Calibri", Font.BOLD, 20));
        g2d.drawString("Score total du joueur 1: " + j.Player1totalScore, middle + middle / 100, getHeight() / 100 * 90);

        g2d.setColor(Color.red);
        g2d.setFont(new Font("Calibri", Font.BOLD, 20));
        g2d.drawString("Score total du joueur 2: " + j.Player2totalScore, middle + middle / 100, getHeight() / 100 * 95);

        //player1 score
        g2d.setColor(Color.blue);
        g2d.setFont(new Font("Calibri", Font.ITALIC, 20));
        g2d.drawString("Score du joueur 1 ", getWidth() / 100 * 70, height / 100 * 85);
        g2d.setFont(new Font("Calibri", Font.BOLD, 50));
        g2d.drawString(String.valueOf(j.Player1Score), getWidth() / 100 * 72, height / 100 * 92);
        //player2 score
        g2d.setColor(Color.red);
        g2d.setFont(new Font("Calibri", Font.ITALIC, 20));
        g2d.drawString("Score du joueur 2 ", getWidth() / 100 * 70, height / 100 * 5);
        g2d.setFont(new Font("Calibri", Font.BOLD, 50));
        g2d.drawString(String.valueOf(j.Player2Score), getWidth() / 100 * 72, height / 100 * 12);

        //双发拿的手卡
        if (j.numberOfRounds <= 16) {
            if (!(j.numberOfRounds == 1 && j.TurnProcess <= 3)) {

                if (j.TurnProcess == 4) {
                    BufferedImage imageCard;
                    File imgFilecard = null;
                    if (j.playerNow == 0) {
                        imgURL = Vue.Main.class.getResource("/res/images/card (" + j.player2takecard.id + ").png");
                        imageIcon = new ImageIcon(imgURL);
                        image = imageIcon.getImage();

                    } else {
                        imgURL = Vue.Main.class.getResource("/res/images/card (" + j.player1takecard.id + ").png");
                        imageIcon = new ImageIcon(imgURL);
                        image = imageIcon.getImage();


                    }
                    int cardHeight = (height - 20) / 8;
                    int cardWidth = (int) (cardHeight * 0.6);
                    g2d.setFont(new Font("Calibri", Font.BOLD, 20));
                    if (j.playerNow == 1) {
                        g2d.setColor(Color.BLUE);
                        g2d.drawString("Joueur 1 pioche", getWidth() / 100 * 5, height / 20 * 13);
                        g2d.drawImage(image, getWidth() / 100 * 5, height / 20 * 14, cardWidth, cardHeight, null);
                    } else {

                        g2d.setColor(Color.RED);
                        g2d.drawString("Joueur 2 pioche", getWidth() / 100 * 5, height / 100 * 38);
                        g2d.drawImage(image, getWidth() / 100 * 5, height / 100 * 20, cardWidth, cardHeight, null);
                    }


                } else if (j.TurnProcess == 3) {

                } else {


                    imgURL = Vue.Main.class.getResource("/res/images/card (" + j.player2takecard.id + ").png");
                    imageIcon = new ImageIcon(imgURL);
                    java.awt.Image image2 = imageIcon.getImage();

                    imgURL = Vue.Main.class.getResource("/res/images/card (" + j.player1takecard.id + ").png");
                    imageIcon = new ImageIcon(imgURL);
                    image = imageIcon.getImage();


                    int cardHeight = (height - 20) / 8;
                    int cardWidth = (int) (cardHeight * 0.6);
                    g2d.setFont(new Font("Calibri", Font.BOLD, 20));

                    g2d.setColor(Color.BLUE);
                    g2d.drawString("Joueur 1 pioche", getWidth() / 100 * 5, height / 20 * 13);
                    g2d.drawImage(image, getWidth() / 100 * 5, height / 20 * 14, cardWidth, cardHeight, null);

                    g2d.setColor(Color.RED);
                    g2d.drawString("Joueur 2 pioche", getWidth() / 100 * 5, height / 100 * 38);
                    g2d.drawImage(image2, getWidth() / 100 * 5, height / 100 * 20, cardWidth, cardHeight, null);
                }
            }
        }


        g2d.dispose();

    }

    public void changejue(Jeu j) {
        this.j = j;
        repaint();
    }

    public void estFINI(Jeu j, Histoire h) {
        switch (j.GameMode) {
            case 1:
                if (j.numberOfRounds == 27) {
                    if (j.Player1totalScore > j.Player2totalScore) {
                        wingamewindow(j, 2, 1);
                    } else {
                        wingamewindow(j, 2, 2);
                    }
                    h.cleanHistoire();
                }
                break;
            case 2:
                if (j.numberOfRounds == 27) {
                    if (j.Player1Score > j.Player2Score) {
                        wingamewindow(j, 1, 1);
                        j.Player1WinGame++;
                    } else {
                        wingamewindow(j, 1, 2);
                        j.Player2WinGame++;
                    }
                    if (j.Player1WinGame == 2) {
                        wingamewindow(j, 2, 1);
                    } else if (j.Player2WinGame == 2) {
                        wingamewindow(j, 2, 2);
                    }

                    gameStartencore(j, h);
                }
                break;
            case 3:
                if (j.numberOfRounds == 27) {
                    j.Game_ind++;
                    if (j.Game_ind > j.GameInformation) {
                        if (j.Player1totalScore > j.Player2totalScore) {
                            wingamewindow(j, 2, 1);
                        } else {
                            wingamewindow(j, 2, 2);
                        }
                        h.cleanHistoire();
                    } else {
                        if (j.Player1Score > j.Player2Score) {
                            wingamewindow(j, 1, 1);
                        } else {
                            wingamewindow(j, 1, 2);
                        }
                    }
                    gameStartencore(j, h);
                }
                break;
            case 4:
                if (j.numberOfRounds == 27) {
                    gameStartencore(j, h);
                }
                if (j.Player1totalScore >= j.GameInformation || j.Player2totalScore >= j.GameInformation) {
                    if (j.Player1totalScore > j.Player2totalScore) {
                        wingamewindow(j, 2, 1);
                    } else {
                        wingamewindow(j, 2, 2);
                    }
                    h.cleanHistoire();
                    exit(0);
                }
                break;
        }
        repaint();
    }

    public void gameStartencore(Jeu j, Histoire h) {
        j.reset();
        if (j.numberOfGames == 0) j.numberOfGames = 1;//�����Ϸ�տ�ʼ�Ļ�
        if (j.playerFirst == 2) {//������ָÿ�ʼ�Ļ����ж��ĸ�����ȿ�ʼ��Ϸ��
            j.playerFirst = (j.numberOfGames - 1) % 2;
            j.numberOfRounds = 1;
            j.playerNow = j.playerFirst;
            //���з����Լ��ƶѵ�ʵ��
            if (j.numberOfGames != 1) {
                StartHand startHand = new StartHand(j);
                startHand.stardHand();
            }
            drawCHANGFANGXING(j);
            Atout a = new Atout(j);
            a.determinerAtout();
            Jeu j0 = (Jeu) j.clone();
            h.ajouteListDeHistoire(j0);
            Jeu j100 = (Jeu) j.clone();
            h.ajouteListDeHistoire(j100);

        }
    }

    public void surrenderthisgame()  {
        if (j.playerNow == 0) {
            j.Player2Score +=(26 - j.numberOfRounds+1);
            j.Player2totalScore += (26 - j.numberOfRounds+1);
            if(j.TurnProcess>=3){
                j.Player2Score--;
                j.Player2totalScore--;
            }
        } else {
            j.Player1Score +=(26 - j.numberOfRounds+1);
            j.Player1totalScore += (26 - j.numberOfRounds+1);
            if(j.TurnProcess>=3){
                j.Player1Score--;
                j.Player1totalScore--;
            }
        }

        j.numberOfRounds = 27;
        estFINI(j, ifjgp.h);

    }

    public void wingamewindow(Jeu j, int i, int winner)  {
        if (i == 1) {
            String winmassage = "Joueur " + winner + " ganne ce tour, le jeu va continuer";
            JOptionPane.showMessageDialog(null, winmassage, "winer", JOptionPane.PLAIN_MESSAGE);
        } else {
            String winmassage = "Joueur " + winner + " ganne cette jeux, Vous volez jouer encore?";
            int res = JOptionPane.showConfirmDialog(null, winmassage, "win", JOptionPane.YES_NO_OPTION);
            if (res == 0) {
                //TODO how to close the window of game ��λص����˵�
                fgp.dispose();
                JavaClip m = new JavaClip();
                m.stop();
                Main window = new Main();
                window.mainframe.setVisible(true);
            } else {
                exit(0);
            }
        }

    }

}