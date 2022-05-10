package Modele;

public class DonneeDeHistore {
    public Jeu j;
    private final Integer gameprocess;
    public DonneeDeHistore(Jeu j, int gameprocess){
        super();
        this.j=j;
        this.gameprocess=gameprocess;
    }

    public Jeu getJ() {
        return j;
    }

    public Integer getGameprocess() {
        return gameprocess;
    }
}
