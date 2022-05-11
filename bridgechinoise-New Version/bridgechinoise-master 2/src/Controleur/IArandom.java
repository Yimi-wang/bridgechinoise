//package Controleur;
//
//import Modele.Jeu;
//
//import java.util.Objects;
//import java.util.Random;
//
//
//public class IArandom {
//    public static int IArandomPlayerCard(Jeu j){
//        Random r = new Random();
//        if (j.getPlayerNow()==1){
//            if(j.getPlayerfirst()==j.getPlayerNow()){
//                return r.nextInt(j.playercard[1].size());
//            }
//            else{
//                int index=r.nextInt(j.playercard[1].size());
//                System.out.println("le index est:" + index);
//                while(limite(j,j.getFirstPlayerPlayCard())&&(!Objects.equals(j.playercard[1].get(index).getInttype(), j.getFirstPlayerPlayCard().getInttype()))){
//                    index=r.nextInt(j.playercard[1].size());
//                }
//                return index;
//            }
//        }
//    return -1;
//    }
//
//    public static int IarandomGetCard(Jeu j){
//        Random r = new Random();
//        int index =r.nextInt(6);
//        if(j.getPlayerNow()==1){
//            while (j.pilescard[index].size()==0){
//                index =r.nextInt(6);
//            }
//        }
//        return index;
//    }
//
//}
