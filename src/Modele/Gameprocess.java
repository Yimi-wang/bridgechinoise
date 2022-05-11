package Modele;
import Modele.Jeu;

import java.util.Scanner;

import static Modele.Histoire.ajoutelistdehistoire;
import static Modele.Histoire.cleanhistoire;
import static Modele.Playcards.*;
import static Modele.ResetJeu.reset;
import static Modele.Starthand.stardhand;
import static Modele.Atout.*;
import static Modele.Takecard.*;


public class Gameprocess {
    static int player2=0;
    static int player1=0;

    static int IA = 0;
    public static void creatJeu(){
        Jeu j = new Jeu();
        System.out.println("IA 0 : sans IA");
        System.out.println("IA 1 : random");
        System.out.println("IA 2 : IA simple");
        System.out.println("IA 3 : QAQ");
        System.out.println("Donner le 'IA' que vous voulez jouer");
        Scanner input = new Scanner(System.in);
        IA= input.nextInt();
        Gamemode(j);

    }

    public static void Gamestart(Jeu j) {
        if (j.numberOfGames == 0) j.numberOfGames = 1;//如果游戏刚开始的话
        if (j.playerfirst == 2) {//如果本轮该开始的话，判断哪个玩家先开始游戏。
            j.playerfirst = (j.numberOfGames - 1) % 2;
            j.numberOfRounds=1;
            //进行发牌以及牌堆的实现
            stardhand(j);
        }
    }

    public static void Gamemode(Jeu j){
        System.out.println("Gamemode 1 : BO1");
        System.out.println("Gamemode 2 : BO3");
        System.out.println("Gamemode 3 : Number de Game Fixe");
        System.out.println("Gamemode 4 : Score Fixe");
        System.out.println("Donner le 'gamemode' que vous voulez jouer");
        boolean win= false;
        Scanner input = new Scanner(System.in);
        int gamemode = input.nextInt();
        switch (gamemode){
            case 1 :
                Gamestart(j);
                //进行26轮游戏（因为一共52张牌）
                while(j.numberOfRounds!=27){
                    turnstrat(j);}
                //游戏结束，判断胜负手
                if(j.Player1Score>j.Player2Score){
                    System.out.println("Player 1 win!");
                }
                else{
                    System.out.println("Player 2 win!");
                }
                break;
            case 2 :
                for(int i =0; i<3;i++){
                    Gamestart(j);
                    while(j.numberOfRounds!=27){
                        turnstrat(j);}
                        //turnstrat2(j);}
                    //游戏结束，判断胜负手
                    if(j.Player1Score>j.Player2Score){
                        j.Player1WinGame++;
                    } else if (j.Player1Score<j.Player2Score) {
                        j.Player2WinGame++;
                    }
                    if(j.Player1WinGame==2){
                        System.out.println("Player 1 win!");
                        break;
                    } else if (j.Player2WinGame==2) {
                        System.out.println("Player 2 win!");
                        break;
                    }
                }
                break;
            case 3 :
                System.out.println("Donner le numbre de game vous voulez jouer");
                input = new Scanner(System.in);
                int nGame = input.nextInt();
                for(int i =0; i<nGame;i++){
                    Gamestart(j);
                    while(j.numberOfRounds!=27){
                        turnstrat(j);}
                    reset(j);
                    cleanhistoire();
                }
                if(j.Player1totalScore>j.Player2totalScore){
                    System.out.println("Player 1 win!");
                }
                else{
                    System.out.println("Player 2 win!");
                }
                break;
            case 4:
                System.out.println("Donner le score vous voulez jouer");
                input = new Scanner(System.in);
                int ScoreWin = input.nextInt();
                while(j.Player1totalScore<ScoreWin&&j.Player2totalScore<ScoreWin){
                    Gamestart(j);
                    while(j.numberOfRounds!=27){
                        turnstrat(j);
                        if(j.Player1totalScore>=ScoreWin||j.Player2totalScore>=ScoreWin){
                            if(j.Player1totalScore>j.Player2totalScore){
                                System.out.println("Player 1 win!");
                            }
                            else{
                                System.out.println("Player 2 win!");
                            }
                            break;
                        }
                    }
                    reset(j);
                    cleanhistoire();
                }
                break;



        }

    }
    public static void turnstrat(Jeu j){
        determinierAtout(j);//判断王牌花色
        //先手方出牌
        j.playerNow=j.playerfirst;
        if(IA>0&&j.getPlayerNow()==1){
            IAplaycard(j,IA);
        }else{
            playerfirstplaycard(j);}
        Jeu j1=(Jeu)j.clone();
        ajoutelistdehistoire(j1,1);
        //后手方出牌
        j.playerNow=j.playerNow+1;
        if(j.playerNow ==2) j.playerNow=0;
        if(IA>0&&j.getPlayerNow()==1){
            IAplaycard(j,IA);
        }else{
            playersecondeplaycard(j);}
        Jeu j2 =(Jeu)j.clone();
        ajoutelistdehistoire(j2,2);
        //比较双方牌的大小
        comparer(j);
        //根据赢家，进行拿牌操作。
        //if条件是当游戏在15轮内才进行拿牌操作。因为牌堆一共30张牌，第十六轮没有牌可以拿。
        if(j.numberOfRounds<=15) {
            j.playerNow =j.Playerwin;
            if(IA>0 && j.playerNow==1){
                IAtakecard(j,IA);
            }else{
                playerwintakecard(j);}
            Jeu j3=(Jeu)j.clone();
            ajoutelistdehistoire(j3,3);
            j.playerNow =j.Playerwin+1;
            if(j.playerNow==2)j.playerNow=0;
            if(IA>0 && j.playerNow==1){
                IAtakecard(j,IA);
            }else{
                playerlosetakecard(j);}
            Jeu j4 =(Jeu)j.clone();
            ajoutelistdehistoire(j4,4);
        }
        //轮数增加
        j.numberOfRounds++;
        //先后手改变
        j.playerfirst=j.Playerwin;
        //得分增加
        if(j.Playerwin==0){
            j.Player1Score++;
        }else {
            j.Player2Score++;
        }
        System.out.println(("Player 1 score est " + j.Player1Score +". Player 2 Score est "+j.Player2Score));
    }
    public static void turnstrat2(Jeu j){
        determinierAtout(j);//判断王牌花色
        //先手方出牌
        j.playerNow=j.playerfirst;
        if(IA>0&&j.getPlayerNow()==1){
            IAplaycard(j,IA);
        }else{
            IAplaycard(j,3);}
        //后手方出牌
        j.playerNow=j.playerNow+1;
        if(j.playerNow ==2) j.playerNow=0;
        if(IA>0&&j.getPlayerNow()==1){
            IAplaycard(j,IA);
        }else{
            IAplaycard(j,3);}
        //比较双方牌的大小
        comparer(j);
        //根据赢家，进行拿牌操作。
        //if条件是当游戏在15轮内才进行拿牌操作。因为牌堆一共30张牌，第十六轮没有牌可以拿。
        if(j.numberOfRounds<=15) {
            j.playerNow =j.Playerwin;
            if(IA>0 && j.playerNow==1){
                IAtakecard(j,IA);
            }else{
                IAtakecard(j,3);}
            Jeu j3=(Jeu)j.clone();
            ajoutelistdehistoire(j3,3);
            j.playerNow =j.Playerwin+1;
            if(j.playerNow==2)j.playerNow=0;
            if(IA>0 && j.playerNow==1){
                IAtakecard(j,IA);
            }else{
                IAtakecard(j,3);}
        }
        //轮数增加
        j.numberOfRounds++;
        //先后手改变
        j.playerfirst=j.Playerwin;
        //得分增加
        if(j.Playerwin==0){
            j.Player1Score++;
        }else {
            j.Player2Score++;
        }
        System.out.println(("Player 1 score est " + j.Player1Score +". Player 2 Score est "+j.Player2Score));
    }
}