package Modele;

import java.util.List;
import java.util.ArrayList;

public class Jeu {
    static List<Brand>[] playercard;//=new List[2];//玩家手牌//建立了一个tableau 的list playercard[0]是玩家1的手卡，etc
    static List<Brand>[] pilescard;//=new List[6];//牌堆//建立了一个tableau 的list pilescard[0]是第一个牌堆，etc
    public int numberOfGames=0;//回合数
    public int numberOfRounds;//轮数
    int playerfirst=2 ;//哪个玩家先手，0代表玩家A。1代表玩家B
    List<Brand> playcard0=new ArrayList<>();
    List<Brand> playcard1=new ArrayList<>();
    List<Brand> pilescard1=new ArrayList<>();
    List<Brand> pilescard2=new ArrayList<>();
    List<Brand> pilescard3=new ArrayList<>();
    List<Brand> pilescard4=new ArrayList<>();
    List<Brand> pilescard5=new ArrayList<>();
    List<Brand> pilescard6=new ArrayList<>();
}
