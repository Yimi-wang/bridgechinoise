package Modele;

import java.util.Scanner;

import static Modele.Starthand.showcard;

public class Takecard {
    static public void takecard(Jeu j){
        //赢家拿牌
        System.out.println("Maintenant c'est le tour de Jouer"+(j.Playerwin+1));
        System.out.println(("Donned le index que vous voulez prendre"));
        //输入
        Scanner input=new Scanner(System.in);
        int index = input.nextInt();
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
        //输家拿牌
        int a=j.Playerwin+1;
        if (a==2) a=0;
        System.out.println("Maintenant c'est le tour de Jouer"+(a+1));
        System.out.println(("Donned le index que vous voulez prendre"));
        //输入
         input=new Scanner(System.in);
         index = input.nextInt();
        //如果输入有误，重新输入
        while(j.pilescard[index].size()==0){
            System.out.println(("L'erreur, reessayer"));
            input=new Scanner(System.in);
            index = input.nextInt();
        }
        //把输家的牌放到手牌里头
        card = j.pilescard[index].get(index);
        j.pilescard[index].remove(index);
        j.playercard[a].add(card);
        showcard(j);
    }
}
