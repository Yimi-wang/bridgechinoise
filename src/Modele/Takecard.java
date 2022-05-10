package Modele;

import Controleur.IASimple;
import Controleur.IArandom;

import java.util.Scanner;

import static Modele.Histoire.returnhistoire;
import static Modele.Starthand.showcard;

public class Takecard {
    static public void playerwintakecard(Jeu j){
        //赢家拿牌
        System.out.println("Maintenant c'est le tour de Jouer"+(j.Playerwin+1));
        System.out.println(("Donned le index que vous voulez prendre"));
        //输入
        Scanner input=new Scanner(System.in);
        int index = input.nextInt();
        //回退历史记录
        if(index==-1){
            returnhistoire();
        }
        //如果输入有误，重新输入
        while(j.pilescard[index].size()==0){
            System.out.println(("L'erreur, reessayer"));
            input=new Scanner(System.in);
            index = input.nextInt();
        }
        //把赢家的牌放到手牌里头
        Brand card = j.pilescard[index].get(index);
        j.pilescard[index].remove(index);
        j.playercard[j.Playerwin].add(card);
        showcard(j);
    }
    static public void playerlosetakecard(Jeu j){
        int a=j.Playerwin+1;
        if (a==2) a=0;
        System.out.println("Maintenant c'est le tour de Jouer"+(a+1));
        System.out.println(("Donned le index que vous voulez prendre"));
        //输入
         Scanner input=new Scanner(System.in);
        int index = input.nextInt();
        //回退历史记录
        if(index==-1){
            returnhistoire();
        }
        //如果输入有误，重新输入
        while(j.pilescard[index].size()==0){
            System.out.println(("L'erreur, reessayer"));
            input=new Scanner(System.in);
            index = input.nextInt();
        }
        //把输家的牌放到手牌里头
        Brand card = j.pilescard[index].get(index);
        j.pilescard[index].remove(index);
        j.playercard[a].add(card);
        showcard(j);
    }

    static public void IAtakecard(Jeu j, int IA){
        int index=-1;
        switch (IA){
            case 1:
                index = IArandom.IarandomGetCard(j);
                break;
            case 2:
                index = IASimple.IASimpleTakeCard(j);
        }
        Brand card = j.pilescard[index].get(0);
        System.out.println("IA prendre"+card.toString() + index);
        j.pilescard[index].remove(index);
        j.playercard[1].add(card);
        showcard(j);
    }

}
