package Modele;

import java.util.LinkedList;


public class Histoire {
    LinkedList<Jeu> listDeHistoire;

    public Jeu j;
    //public Integer gameProcess;

    public Histoire(Jeu j) {
        this.j = j;
        //this.gameProcess = gameProcess;
        listDeHistoire = new LinkedList<>();
    }

    public void ajouteListDeHistoire() {
        listDeHistoire.add(j);

    }

    public void returnHistoire() {
        if (j.numberOfRounds == 0) {
            //无法回退
            System.out.println("首回合");
        }
        Jeu newJeu = listDeHistoire.get(listDeHistoire.size() - 2);
        j = newJeu;

//        listDeHistoire.remove(listDeHistoire.size() - 1);
//        DonneeDeHistore H2 = listDeHistoire.get(listDeHistoire.size()-1);
//        Jeu oldJeu = H2.getJ();
//        showCard(oldJeu);
//        switch (H.getGameprocess()) {
//            case 1:
//                playerfirstplaycard(oldJeu);
//                ajoutelistDeHistoire(oldJeu, 1);
//            case 2:
//                playersecondeplaycard(oldJeu);
//                ajoutelistDeHistoire(oldJeu, 2);
//                comparer(oldJeu);
//            case 3:
//                if (oldJeu.numberOfRounds <= 15) {
//                    playerwintakecard(oldJeu);
//                    ajoutelistDeHistoire(oldJeu, 3);
//                }
//            case 4:
//                if (oldJeu.numberOfRounds <= 15) {
//                    playerlosetakecard(oldJeu);
//                    ajoutelistDeHistoire(oldJeu, 4);
//                }
//                oldJeu.numberOfRounds++;
//                //先后手改变
//                oldJeu.playerfirst = oldJeu.Playerwin;
//                //得分增加
//                if (oldJeu.Playerwin == 0) {
//                    oldJeu.Player1Score++;
//                } else {
//                    oldJeu.Player2Score++;
//                }
//                System.out.println(("Player 1 score est " + oldJeu.Player1Score + ". Player 2 Score est " + oldJeu.Player2Score));
//                turnstrat(oldJeu);
    }

    public void cleanHistoire() {
        listDeHistoire.clear();
    }

}
