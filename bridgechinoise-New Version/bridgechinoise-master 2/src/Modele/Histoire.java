package Modele;

import java.util.LinkedList;


public class Histoire {
    LinkedList<Jeu> listDeHistoire;

    public Jeu j;

    public Histoire(Jeu j) {
        this.j = j;
        listDeHistoire = new LinkedList<>();

    }

    public void ajouteListDeHistoire(Jeu j) {
        listDeHistoire.add(j);

    }

    public void returnHistoire() {
        if (j.numberOfRounds == 0) {
            //无法回退
            System.out.println("首回合");
        }
        Jeu newJeu = listDeHistoire.get(listDeHistoire.size() - 2);
        j = newJeu;
        listDeHistoire.remove(listDeHistoire.size()-1);
    }

    public void cleanHistoire() {
        listDeHistoire.clear();
    }

}
