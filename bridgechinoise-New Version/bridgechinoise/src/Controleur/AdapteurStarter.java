package Controleur;

import Modele.GameProcessVue;
import Modele.Jeu;
import Vue.InterfaceInitialise;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdapteurStarter implements ActionListener {
    boolean isAIGame;
    InterfaceInitialise interIni;
    Jeu j = new Jeu();
    GameProcessVue gameProcessVue = new GameProcessVue();

    public AdapteurStarter(boolean isAIGame, InterfaceInitialise interIni) {
        this.isAIGame = isAIGame;
        this.interIni = interIni;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        try {
            initiailiseParameters();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //loadAndStartMainInterface();
        this.interIni.frame.setVisible(false);
    }

    private void initiailiseParameters() throws IOException, ClassNotFoundException {
        Jeu j = new Jeu();
        // Human vs Human
        for (int i1 = 0; i1 < this.interIni.aiSelections.size(); i1++) {
            if (this.interIni.obSelection.get(i1).isSelected()) {
                j.GameMode = i1 + 1;
                break;
            }

        }
        String NB = this.interIni.NBText.getText();
        if(j.GameMode>2) {j.GameInformation=Integer.parseInt(NB);
        System.out.println("gamemode est " + j.GameMode);}

        if (this.isAIGame) {
            int aiType = 0;
            for (int i = 0; i < this.interIni.aiSelections.size(); i++) {
                if (this.interIni.aiSelections.get(i).isSelected()) {
                    aiType = i + 1;
                    break;
                }
            }
            System.out.println("ai type est " + aiType);
            j.AI = aiType + 1;
        }
        interIni.frame.setVisible(false);
        gameProcessVue.creatJeu(j);
    }

}
