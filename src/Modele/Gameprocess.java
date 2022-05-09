package Modele;
import Modele.Jeu;

import java.util.Scanner;

import static Modele.Playcards.comparer;
import static Modele.Playcards.playcards;
import static Modele.Starthand.stardhand;
import static Modele.Atout.*;
import static Modele.Takecard.takecard;


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
        j.playerNow=j.playerfirst;
        //输入先手方想出的牌
        System.out.println("Maintenant c'est le tour de Jouer"+(j.playerNow+1));
        System.out.println(("Donned le index que vous voulez jouer"));
        Scanner input=new Scanner(System.in);
        int index = input.nextInt();
        //进行出牌操作
        playcards(j,index);
        //打印先手方出的牌
        System.out.println((j.playerNow+1)+"jouer"+j.FirstPlayerPlayCard.toString());
        //后手方出牌
        j.playerNow=j.playerNow+1;
        if(j.playerNow ==2) j.playerNow=0;
        //输入后手方想出的牌
        System.out.println("Maintenant c'est le tour de Jouer"+(j.playerNow+1));
        System.out.println(("Donned le index que vous voulez jouer"));
        input = new Scanner(System.in);
        index = input.nextInt();
        //进行出牌操作
        playcards(j,index);
        //打印后手方出的牌
        System.out.println((j.playerNow+1)+"jouer"+j.SecondPlayerPlayerCard.toString());
        //比较双方牌的大小
        comparer(j);
        //根据赢家，进行拿牌操作。
        //if条件是当游戏在15轮内才进行拿牌操作。因为牌堆一共30张牌，第十六轮没有牌可以拿。
        if(j.numberOfGames<=15) takecard(j);
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
