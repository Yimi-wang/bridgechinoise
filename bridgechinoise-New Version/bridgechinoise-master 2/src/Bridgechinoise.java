
import Modele.GameProcess;

import java.io.IOException;

import Vue.GameFrame;

public class Bridgechinoise {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GameProcess gameProcess = new GameProcess();
        //gameProcess.creatJeu();
        GameFrame gameFrame = new GameFrame(gameProcess.getJ());
        gameFrame.run();



    }


}
