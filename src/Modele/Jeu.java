package Modele;

import java.util.List;

public class Jeu {
    static List<Brand>[] playercard;//玩家手牌//建立了一个tableau 的list playercard[0]是玩家1的手卡，etc
    static List<Brand>[] pilescard;//牌堆//建立了一个tableau 的list pilescard[0]是第一个牌堆，etc
    private static int numberOfGames = 0;//回合数
    private static int numberOfRounds = 0;//轮数
    private static int playerfirst = 2;//哪个玩家先手，0代表玩家A。1代表玩家B

    public static int getNumberOfGames() {
        return numberOfGames;
    }
}
