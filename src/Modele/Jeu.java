package Modele;

import java.util.List;

public class Jeu {
    static List<Brand>[] playercard=new List[2];//玩家手牌//建立了一个tableau 的list playercard[0]是玩家1的手卡，etc
    static List<Brand>[] pilescard=new List[6];//牌堆//建立了一个tableau 的list pilescard[0]是第一个牌堆，etc
    public int numberOfGames=0;//回合数
    public int numberOfRounds;//轮数
    int playerfirst=2 ;//哪个玩家先手，0代表玩家A。1代表玩家B
    List<Brand> test;

}
