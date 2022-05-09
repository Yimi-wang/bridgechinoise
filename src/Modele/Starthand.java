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
        //j.pilescard=new List[6];//牌堆
        for(int i =0; i <52; i++){
            int coleur=(listcard.get(i))/13;
            switch (coleur){
                case 0:
                    card = new Brand(Type.pique, (listcard.get(i)) % 13);
                    break;
                case 1:
                    card = new Brand(Type.coeur, (listcard.get(i)) % 13);
                    break;
                case 2:
                    card = new Brand(Type.carreau, (listcard.get(i)) % 13);
                    break;
                case 3:
                    card = new Brand(Type.trefle, (listcard.get(i)) % 13);
                    break;
            }
        Brand c=card;
        if(i<11){
            j.playcard0.add(c);
          //  (j.playercard[0]).add(c);
        }
        else if(i>10 && i<22){
            //j.playercard[1].add(card);
            j.playcard1.add(c);
        }
        else{
            //j.pilescard[(i-21)%6].add(card);
            switch ((i-21)%6){
                case 0:
                    j.pilescard1.add(c);
                    break;
                case 1:
                    j.pilescard2.add(c);
                    break;
                case 2:
                    j.pilescard3.add(c);
                    break;
                case 3:
                    j.pilescard4.add(c);
                    break;
                case 4:
                    j.pilescard5.add(c);
                    break;
                case 5:
                    j.pilescard6.add(c);
                    break;

            }

        }
        }
        showcard(j);
    }

    private static void showcard(Jeu j) {
        System.out.println("player 1 card");
        for(int i=0;i<11;i++){
            //Brand b =  j.playercard[0].get(i);
            Brand b = j.playcard0.get(i);
            String s = b.toString();
            System.out.println(s);
        }
        System.out.println("player 2 card");
        for(int i=0;i<11;i++){
            Brand b =  j.playcard1.get(i);
            String s = b.toString();
            System.out.println(s);
        }
        System.out.println("pile 1 card");
        for(int i=0;i<5;i++){
            Brand b =  j.pilescard1.get(i);
            String s = b.toString();
            System.out.println(s);
        }
        System.out.println("pile 2 card");
        for(int i=0;i<5;i++){
            Brand b =  j.pilescard2.get(i);
            String s = b.toString();
            System.out.println(s);
        }
        System.out.println("pile 3 card");
        for(int i=0;i<5;i++){
            Brand b =  j.pilescard3.get(i);
            String s = b.toString();
            System.out.println(s);
        }
        System.out.println("pile 4 card");
        for(int i=0;i<5;i++){
            Brand b =  j.pilescard4.get(i);
            String s = b.toString();
            System.out.println(s);
        }
        System.out.println("pile 5 card");
        for(int i=0;i<5;i++){
            Brand b =  j.pilescard5.get(i);
            String s = b.toString();
            System.out.println(s);
        }
        System.out.println("pile 6 card");
        for(int i=0;i<5;i++){
            Brand b =  j.pilescard6.get(i);
            String s = b.toString();
            System.out.println(s);
        }
    }


}
