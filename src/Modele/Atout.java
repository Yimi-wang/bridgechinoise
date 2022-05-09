package Modele;

public class Atout {

    static void determinierAtout(Jeu j){
        Brand maxtop= new Brand(Type.trefle, 1,3);
        j.avoiratout=false;
        for (int i = 0; i<=5;i++){
            if (j.pilescard[i].size()!=0){
                Brand c = j.pilescard[0].get(0);
                if(c.getNum()> maxtop.getNum()){
                    maxtop=c;
                }
                else if(c.getNum()==maxtop.getNum()){
                    if(c.getInttype()<maxtop.getInttype()){
                        maxtop=c;
                    }
                }
            }
        }
        if(maxtop.getNum()>9){
            j.atout =maxtop;
            j.avoiratout=true;
        }
        toStringAtout(j);
    }
    public static void toStringAtout(Jeu j) {
        if(j.avoiratout){
            String a = j.atout.toString();
            System.out.println( "Atout est "+a);
        }else {
            System.out.println( "Pas de atout");
        }

    }
}
