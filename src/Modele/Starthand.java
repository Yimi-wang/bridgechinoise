package Modele;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//实现分发手牌
public class Starthand {
    static List<Integer> listcard=new ArrayList<>();
    static void createPlayingCard(List<Integer> listcard) {
        for(int i=1;i<=52;i++){
            listcard.add(i);
        }
        Collections.shuffle(listcard); //随机打乱list数据

    }
    static void stardhand(Jeu j){
        Brand card = null;
        createPlayingCard(listcard);//
        //j.playercard=new List[2];//手牌
       // j.pilescard=new List[6];//牌堆
        j.test = new ArrayList<>();
        for(int i =0; i <52; i++){
            int coleur=(listcard.get(i))/13;
            switch (coleur){
                case 0:
                    card = new Brand(Type.pique,new Integer((listcard.get(i))%13));
                    break;
                case 1:
                    card = new Brand(Type.coeur,new Integer((listcard.get(i))%13));
                    break;
                case 2:
                    card = new Brand(Type.carreau,new Integer((listcard.get(i))%13));
                    break;
                case 3:
                    card = new Brand(Type.trefle,new Integer((listcard.get(i))%13));
                    break;
            }
        Brand c=card;
        if(i<11){
            j.test.add(c);
          //  (j.playercard[0]).add(c);
        }
        else if(i>10 && i<21){
            j.playercard[1].add(card);
        }
        else{
            j.pilescard[(i-21)%6].add(card);
        }
        }
        showcard(j);
    }

    private static void showcard(Jeu j) {
        for(int i=0;i<11;i++){
            System.out.println("player 1 card");
            //Brand b =  j.playercard[0].get(i);
            Brand b = j.test.get(i);
            String s = b.toString();
            System.out.println(s);
        }
        for(int i=0;i<11;i++){
            System.out.println("player 2 card");
            Brand b =  j.playercard[1].get(i);
            String s = b.toString();
            System.out.println(s);
        }
        for(int i=0;i<5;i++){
            System.out.println("pile card");
            Brand b =  j.pilescard[0].get(i);
            String s = b.toString();
            System.out.println(s);
        }
        for(int i=0;i<5;i++){
            System.out.println("pile card");
            Brand b =  j.pilescard[1].get(i);
            String s = b.toString();
            System.out.println(s);
        }
        for(int i=0;i<5;i++){
            System.out.println("pile card");
            Brand b =  j.pilescard[2].get(i);
            String s = b.toString();
            System.out.println(s);
        }
        for(int i=0;i<5;i++){
            System.out.println("pile card");
            Brand b =  j.pilescard[3].get(i);
            String s = b.toString();
            System.out.println(s);
        }
        for(int i=0;i<5;i++){
            System.out.println("pile card");
            Brand b =  j.pilescard[4].get(i);
            String s = b.toString();
            System.out.println(s);
        }
        for(int i=0;i<5;i++){
            System.out.println("pile card");
            Brand b =  j.pilescard[5].get(i);
            String s = b.toString();
            System.out.println(s);
        }
    }


}
