package Modele;
import Modele.Jeu;

import java.util.Scanner;

import static Modele.Histoire.ajoutelistdehistoire;
import static Modele.Playcards.*;
import static Modele.Starthand.stardhand;
import static Modele.Atout.*;
import static Modele.Takecard.*;


public class Gameprocess {
    public static void creatJeu(){
        Jeu j = new Jeu();
        Gamestart(j);
    }

    public static void Gamestart(Jeu j) {
        if (j.numberOfGames == 0) j.numberOfGames = 1;//如果游戏刚开始的话
        if (j.playerfirst == 2) {//如果本轮该开始的话，判断哪个玩家先开始游戏。
            j.playerfirst = (j.numberOfGames - 1) % 2;
            j.numberOfRounds=1;
            //进行发牌以及牌堆的实现
            stardhand(j);
        }
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
    }

    public static void turnstrat(Jeu j){
        determinierAtout(j);//判断王牌花色
        //先手方出牌
        playerfirstplaycard(j);
        Jeu j1=(Jeu)j.clone();
        ajoutelistdehistoire(j1,1);
        //后手方出牌
        playersecondeplaycard(j);
        Jeu j2 =(Jeu)j.clone();
        ajoutelistdehistoire(j2,2);
        //比较双方牌的大小
        comparer(j);
        //根据赢家，进行拿牌操作。
        //if条件是当游戏在15轮内才进行拿牌操作。因为牌堆一共30张牌，第十六轮没有牌可以拿。
        if(j.numberOfRounds<=15) {
            //takecard(j);
            playerwintakecard(j);
            Jeu j3=(Jeu)j.clone();
            ajoutelistdehistoire(j3,3);
            playerlosetakecard(j);
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

}
