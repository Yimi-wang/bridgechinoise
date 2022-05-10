package Modele;

import java.util.LinkedList;

public class Jeu implements Cloneable{
    LinkedList<Brand>[] playercard;//=new List[2];//玩家手牌//建立了一个tableau 的list playercard[0]是玩家1的手卡，etc
    LinkedList<Brand>[] pilescard;//=new List[6];//牌堆//建立了一个tableau 的list pilescard[0]是第一个牌堆，etc
    public int numberOfGames=0;//回合数
    public int numberOfRounds;//轮数
    int playerfirst=2 ;//哪个玩家先手，0代表玩家A。1代表玩家B
    int playerNow;
    Brand atout;
    Boolean avoiratout;

    Brand FirstPlayerPlayCard;
    Brand SecondPlayerPlayerCard;
    public int Playerwin;
    public int Player1Score =0;
    public int Player2Score =0;
    public Object clone() {
        Jeu a = new Jeu();
       try{
           a = (Jeu)super.clone();
       }catch(CloneNotSupportedException e) {
           e.printStackTrace();
      }
        a.playercard=new LinkedList[2];//手牌
        a.pilescard=new LinkedList[6];//
        for (int i =0; i<2; i++){
            a.playercard[i]=new LinkedList<>();
            for (int z =0; z<this.playercard[i].size();z++ ){
                Brand temp = new Brand( this.playercard[i].get(z).getType(),this.playercard[i].get(z).getNum(),this.playercard[i].get(z).getInttype());
                a.playercard[i].add(temp);
            }
        }
        for (int i =0; i<6; i++){
            a.pilescard[i]=new LinkedList<>();
            for (int z =0; z<this.pilescard[i].size();z++ ){
                Brand temp = new Brand( this.pilescard[i].get(z).getType(),this.pilescard[i].get(z).getNum(),this.pilescard[i].get(z).getInttype());
                a.pilescard[i].add(temp);
            }
        }
        return a;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public int getPlayer1Score() {
        return Player1Score;
    }

    public int getPlayer2Score() {
        return Player2Score;
    }
}
