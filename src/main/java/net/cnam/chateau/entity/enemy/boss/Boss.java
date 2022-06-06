package net.cnam.chateau.entity.enemy.boss;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.dialog.DialogType;
import net.cnam.chateau.gui.dialog.InfoDialog;
import net.cnam.chateau.gui.play.fight.Fight;
import net.cnam.chateau.structure.RoomBoss;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

import java.util.List;

public abstract class Boss extends Enemy {
    private final App app;
    private final String character;
    private List<String> dialogue;
    private boolean dialogPassed = false;

    /**
     * Constructeur
     *
     * @param app        L'application
     * @param stage      L'étage où il se situe
     * @param location   Coordonnées où il se situe
     * @param name       Le nom
     * @param health     La santé
     * @param strength   La force
     * @param accuracy   La précision
     * @param speed      La rapidité
     * @param character  Le caractère d'affichage
     */
    public Boss(App app, Stage stage, Location location, String name, int health, int strength,
                int accuracy, int speed, String character) {
        super(app, stage, location, name, health, strength, accuracy, speed, "");

        this.app = app;
        this.character = character;
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

    /**
     * Redéfinition de la méthode permettant d'afficher l'entité sur la carte.
     *
     * @return Le caractère représentant l'entité
     */
    @Override
    public String getCharacter() {
        return CColor.RED + this.character + CColor.RED.getForegroundReset();
    }

    @Override
    public Fight fight(Player player, boolean runAway) {
        if (!this.dialogPassed){
            this.app.getConsole().show(new InfoDialog(DialogType.HISTORY,
                    "Donc c'est toi l'intrus qui a pénétré dans mon château ?\n" +
                    "Prépare toi à gouter à la colère de " + this.getName() + " !\n" +
                    "Mon arme \" " + this.getWeapon().getName() + " \" sera la dernière que tu verras !"));
        }
        this.dialogPassed = true;
        return super.fight(player, runAway);
    }
}
