package net.cnam.chateau.entity.enemy.boss;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.structure.RoomBoss;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

import java.util.List;

public abstract class Boss extends Enemy {

    private List<String> dialogue;
    private Stage stage;
    private App app;

    /**
     * Constructeur
     *
     * @param app        L'application
     * @param stage      L'étage où il se situe
     * @param location   Coordonnées où il se situe
     * @param name       Le nom
     * @param health     La santé
     * @param resistance La résistance
     * @param strength   La force
     * @param accuracy   La précision
     * @param speed      La rapidité
     */
    public Boss(App app, Stage stage, Location location, String name, int health, int resistance, int strength,
                int accuracy, int speed) {
        super(app, stage, location, name, health, resistance, strength, accuracy, speed);

        this.stage = stage;
        this.app = app;
    }

    public List<String> getDialogue() {
        return dialogue;
    }

    public void setDialogue(List<String> dialogue) {
        this.dialogue = dialogue;
    }

    @Override
    public void kill() {
        super.kill();
        if (this.getStage().getRooms()[0] instanceof RoomBoss roomBoss) {
            roomBoss.openBossRoom(app);
        }
    }
}
