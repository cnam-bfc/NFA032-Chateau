package net.cnam.fight;

import net.cnam.entity.enemy.Enemy;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import net.cnam.App;
import net.cnam.entity.*;
import net.cnam.gui.Console;
import net.cnam.gui.component.CComponent;
import net.cnam.gui.component.CFrame;
import net.cnam.structure.CoordinatesOutOfBoundsException;
import net.cnam.utils.console.RawConsoleInput;
import net.cnam.utils.direction.Direction;
import net.cnam.utils.direction.DirectionNotFoundException;
import net.cnam.utils.direction.DirectionUtils;

public class Fight extends CFrame {

    private Player player;
    private Enemy enemy;
    private boolean running = false;

    public Fight(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void start(App app) {
        if (running || !app.isRunning() || !app.getCurrentGame().isRunning()) {
            return;
        }

        running = true;

        Console console = app.getConsole();

        this.setSize(console.getLength(), console.getHeight());
        List<CComponent> save = new LinkedList<>(console.getContent());
        console.getContent().clear();

        console.getContent().add(this);
        while (running && app.isRunning() && app.getCurrentGame().isRunning()) {
            console.print();
            try {
                int input = RawConsoleInput.read(true);
                // 13 = Entrée dans un terminal ; 10 = Entrée dans netbeans
                if (input == 13 || input == 10) {
                    break;
                }
            } catch (IOException ex) {
                System.out.println("ERREUR");
                System.exit(1);
            }
        }
        console.getContent().clear();
        console.getContent().addAll(save);
        stop();
    }

    public void stop() {
        if (!running) {
            return;
        }

        running = false;
    }

    public void chooseAction() {
        //texte Attaquer
        //utiliser un objet
        //fuire
    }

    //le joueur choisie attaque ou fuite ou utiliser un objet
    // si le joueur attaque, le plus rapide attaque en 1er
    //une attaque peut échouer (précision)
}
