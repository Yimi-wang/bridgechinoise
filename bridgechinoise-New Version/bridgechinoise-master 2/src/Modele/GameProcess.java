package Modele;

import Modele.Jeu;

import java.util.Scanner;


public class GameProcess {
    int player2;
    int player1;
    int IA;
    Jeu j;
    Atout a;
    Histoire h;
    PlayCards playCards;
    TakeCard takeCard;

    public GameProcess() {
    }

    public void creatJeu() {
        player1 = 0;
        player2 = 0;
        IA = 0;
        j = new Jeu();
        a = new Atout(j);
        h = new Histoire(j);
        playCards = new PlayCards(j, h);
        takeCard = new TakeCard(j, h);
        j.playerFirst = 2;
        System.out.println("IA 0 : sans IA");
        System.out.println("IA 1 : random");
        System.out.println("IA 2 : IA simple");
        System.out.println("IA 3 : QAQ");
        System.out.println("Donner le 'IA' que vous voulez jouer");
        Scanner input = new Scanner(System.in);
        IA = input.nextInt();
        gameMode();

    }

    public void gameStart() {
        reset();
        if (j.numberOfGames == 0) j.numberOfGames = 1;//如果游戏刚开始的话
        if (j.playerFirst == 2) {//如果本轮该开始的话，判断哪个玩家先开始游戏。
            j.playerFirst = (j.numberOfGames - 1) % 2;
            j.numberOfRounds = 1;
            //进行发牌以及牌堆的实现
            StartHand startHand = new StartHand(j);
            startHand.stardHand();
            a.determinerAtout();
            Jeu j0 = (Jeu) j.clone();
            h.ajouteListDeHistoire(j0);

        }
    }

    public void gameMode() {
        System.out.println("Gamemode 1 : BO1");
        System.out.println("Gamemode 2 : BO3");
        System.out.println("Gamemode 3 : Number de Game Fixe");
        System.out.println("Gamemode 4 : Score Fixe");
        System.out.println("Donner le 'gamemode' que vous voulez jouer");
        Scanner input = new Scanner(System.in);
        int gamemode = input.nextInt();
        switch (gamemode) {
            case 1:
                gameStart();
                //进行26轮游戏（因为一共52张牌）
                while (j.numberOfRounds != 26) {
                    if (j.TurnProcess == 5)
                        j.TurnProcess = 1;
                    turnstart();
                }
                //游戏结束，判断胜负手
                if (j.Player1Score > j.Player2Score) {
                    System.out.println("Player 1 win!");
                } else {
                    System.out.println("Player 2 win!");
                }
                break;
            case 2:
                for (int i = 0; i < 3; i++) {
                    gameStart();
                    while (j.numberOfRounds != 26) {
                        if (j.TurnProcess == 5)
                            j.TurnProcess = 1;
                        turnstart();
                    }
                    //turnstrat2();}
                    //游戏结束，判断胜负手
                    if (j.Player1Score > j.Player2Score) {
                        j.Player1WinGame++;
                    } else if (j.Player1Score < j.Player2Score) {
                        j.Player2WinGame++;
                    }
                    if (j.Player1WinGame == 2) {
                        System.out.println("Player 1 win!");
                        break;
                    } else if (j.Player2WinGame == 2) {
                        System.out.println("Player 2 win!");
                        break;
                    }
                }
                break;
            case 3:
                System.out.println("Donner le numbre de game vous voulez jouer");
                input = new Scanner(System.in);
                int nGame = input.nextInt();
                for (int i = 0; i < nGame; i++) {
                    gameStart();
                    while (j.numberOfRounds != 26) {
                        if (j.TurnProcess == 5)
                            j.TurnProcess = 1;
                        turnstart();
                    }
                    h.cleanHistoire();
                }
                if (j.Player1totalScore > j.Player2totalScore) {
                    System.out.println("Player 1 win!");
                } else {
                    System.out.println("Player 2 win!");
                }
                break;
            case 4:
                System.out.println("Donner le score vous voulez jouer");
                input = new Scanner(System.in);
                int ScoreWin = input.nextInt();
                while (j.Player1totalScore < ScoreWin && j.Player2totalScore < ScoreWin) {
                    gameStart();
                    while (j.numberOfRounds != 26) {
                        if (j.TurnProcess == 5)
                            j.TurnProcess = 1;
                        turnstart();
                        if (j.Player1totalScore >= ScoreWin || j.Player2totalScore >= ScoreWin) {
                            if (j.Player1totalScore > j.Player2totalScore) {
                                System.out.println("Player 1 win!");
                            } else {
                                System.out.println("Player 2 win!");
                            }
                            break;
                        }
                    }
                    h.cleanHistoire();
                }
                break;


        }

    }

    public void turnstart() {
        switch (j.TurnProcess) {
            //先手方出牌
            case 1:
                j.playerNow = j.playerFirst;
                if (IA > 0 && j.getPlayerNow() == 1) {
                    playCards.IAplaycard(j,IA);
                } else {
                    playCards.playerFirstPlayCard();
                }
                Jeu j1 = (Jeu) j.clone();
                h.ajouteListDeHistoire(j1);
                break;
            //后手方出牌
            case 2:
                j.playerNow = j.playerNow + 1;
                if (j.playerNow == 2) j.playerNow = 0;
                if (IA > 0 && j.getPlayerNow() == 1) {
                    playCards.IAplaycard(j,IA);
                } else {
                    playCards.playerSecondePlayCard();
                }
                Jeu j2 = (Jeu) j.clone();
                h.ajouteListDeHistoire(j2);
                //比较双方牌的大小
                playCards.comparer();
                break;
            //根据赢家，进行拿牌操作。
            //if条件是当游戏在15轮内才进行拿牌操作。因为牌堆一共30张牌，第十六轮没有牌可以拿。
            case 3:
                if (j.numberOfRounds <= 15) {
                    j.playerNow = j.Playerwin;
                    if (IA > 0 && j.playerNow == 1) {
                        takeCard.IAtakecard(j,IA);
                    } else {
                        takeCard.playerWinTakeCard();
                    }
                    Jeu j3 = (Jeu) j.clone();
                    h.ajouteListDeHistoire(j3);
                }
                break;
            case 4:
                if (j.numberOfRounds <= 15) {
                    j.playerNow = j.Playerwin + 1;
                    if (j.playerNow == 2) j.playerNow = 0;
                    if (IA > 0 && j.playerNow == 1) {
                        takeCard.IAtakecard(j,IA);
                    } else {
                        takeCard.playerLoseTakeCard();
                    }
                    Jeu j4 = (Jeu) j.clone();
                    h.ajouteListDeHistoire(j4);
                }
                //轮数增加
                j.numberOfRounds++;
                //先后手改变
                j.playerFirst = j.Playerwin;
                //得分增加
                if (j.Playerwin == 0) {
                    j.Player1Score++;
                } else {
                    j.Player2Score++;
                }
                System.out.println(("Player 1 score est " + j.Player1Score + ". Player 2 Score est " + j.Player2Score));
                break;
        }
    }
    public void reset() {
        j.playerFirst=2;
        j.numberOfRounds=0;
        j.Playerwin=2;
        j.Player1Score=0;
        j.Player2Score=0;
        j.TurnProcess=1;

    }
}

//    public void turnstart2(Jeu j){
//        determinierAtout(j);//判断王牌花色
//        //先手方出牌
//        j.playerNow=j.playerfirst;
//        if(IA>0&&j.getPlayerNow()==1){
//            IAplaycard(j,IA);
//        }else{
//            IAplaycard(j,3);}
//        //后手方出牌
//        j.playerNow=j.playerNow+1;
//        if(j.playerNow ==2) j.playerNow=0;
//        if(IA>0&&j.getPlayerNow()==1){
//            IAplaycard(j,IA);
//        }else{
//            IAplaycard(j,3);}
//        //比较双方牌的大小
//        comparer(j);
//        //根据赢家，进行拿牌操作。
//        //if条件是当游戏在15轮内才进行拿牌操作。因为牌堆一共30张牌，第十六轮没有牌可以拿。
//        if(j.numberOfRounds<=15) {
//            j.playerNow =j.Playerwin;
//            if(IA>0 && j.playerNow==1){
//                IAtakecard(j,IA);
//            }else{
//                IAtakecard(j,3);}
//            Jeu j3=(Jeu)j.clone();
//            ajoutelistdehistoire(j3,3);
//            j.playerNow =j.Playerwin+1;
//            if(j.playerNow==2)j.playerNow=0;
//            if(IA>0 && j.playerNow==1){
//                IAtakecard(j,IA);
//            }else{
//                IAtakecard(j,3);}
//        }
//        //轮数增加
//        j.numberOfRounds++;
//        //先后手改变
//        j.playerfirst=j.Playerwin;
//        //得分增加
//        if(j.Playerwin==0){
//            j.Player1Score++;
//        }else {
//            j.Player2Score++;
//        }
//        System.out.println(("Player 1 score est " + j.Player1Score +". Player 2 Score est "+j.Player2Score));
//    }

