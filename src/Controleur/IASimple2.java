package Controleur;

import Modele.Brand;
import Modele.Jeu;
import Modele.Type;

import java.util.Objects;

public class IASimple2 {
    public Jeu j;
    Type t;

    public IASimple2(Jeu j) {
        this.j = j;
    }

    public int IASimplePlayerCard() {
        System.out.println("IASimplePlayCard");
        boolean peutAtout = true;
        if (j.getPlayerfirst() == 0) {//先出的话，出数值最大的牌
            Brand max = j.playercard[0].get(0);
            for (int i = 1; i < j.playercard[0].size(); i++) {
                if (j.playercard[0].get(i).getNum() >= max.getNum()) {
                    max = j.playercard[0].get(i);
                }
            }
            return j.playercard[0].indexOf(max);
        } else {

            //如果有。出最小的大于它的牌
            for (int index = 0; index < j.playercard[0].size(); index++) {
                if (Objects.equals(j.playercard[0].get(index).getInttype(), j.getFirstPlayerPlayCard().getInttype())) {
                    peutAtout = false;
                    if (j.playercard[0].get(index).getNum() > j.getFirstPlayerPlayCard().getNum())
                        return index;
                }
            }
            //如果有，出最小的王牌。
            if (j.avoiratout && peutAtout) {
                for (int index = 0; index < j.playercard[0].size(); index++) {
                    if (Objects.equals(j.playercard[0].get(index).getInttype(), j.atout.getInttype())) return index;
                }
            }
            //如果有同花色但赢不了，出最小的牌
            Brand min = new Brand(Type.trefle, 10000, 1000000000,99);
            //如果都没有。出最小的牌
            if (!peutAtout) {
                for (int i = j.playercard[0].size() - 1; i >= 0; i--) {
                    if ((j.playercard[0].get(i).getNum() <= min.getNum()) && Objects.equals(j.playercard[0].get(i).getInttype(), j.getFirstPlayerPlayCard().getInttype())) {
                        min = j.playercard[0].get(i);
                    }
                }
                return j.playercard[0].indexOf(min);
            }
            for (int i = j.playercard[0].size() - 1; i >= 0; i--) {
                if (j.playercard[0].get(i).getNum() <= min.getNum()) {
                    min = j.playercard[0].get(i);
                }
            }
            return j.playercard[0].indexOf(min);

        }

    }

    public int IASimpleTakeCard() {
        System.out.println("IASimpleTakeCard");
        //如果游戏有王牌且牌堆有王牌的话
        Brand b = new Brand(Type.pique, 0, 0,99);
        int index = -10;
        if (j.avoiratout) {
            for (int i = 0; i <= 5; i++) {
                if (j.pilescard[i].size() > 0) {
                    if ((j.pilescard[i].get(0).getType() == j.atout.getType()) && j.pilescard[i].get(0).getNum() >= b.getNum()) {
                        index = i;
                        b = j.pilescard[i].get(0);
                    }
                }
            }
            if (index > -1) {
                return index;
            }

        }

        for (int i = 0; i <= 5; i++) {
            if (j.pilescard[i].size() != 0) {
                if (j.pilescard[i].get(0).getNum() > b.getNum()) {
                    b = j.pilescard[i].get(0);
                    index = i;
                } else if (Objects.equals(j.pilescard[i].get(0).getNum(), b.getNum())) {
                    if (j.pilescard[i].get(0).getInttype() > b.getInttype()) {
                        b = j.pilescard[i].get(0);
                        index = i;
                    }
                }
            }
        }
        return index;

    }
}
