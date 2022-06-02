package net.cnam.chateau.gui.play.escape.cheat;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.SoundEffect;
import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Ludo;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.InfoDialog;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ActivateCheatButton extends CButton {

    private App app;
    private CheatMenu menu;
    private Game game;

    public ActivateCheatButton(App app, CheatMenu menu, Game game) {
        super(app, "Activer le code de triche");

        this.app = app;
        this.menu = menu;
        this.game = game;
    }

    @Override
    public void execute() {
        String cheatCode = menu.getLeTexteQueLeMecEcritDansLeCadre().getText();
        Player player = game.getPlayer();

        switch (cheatCode) {
            case ("404") -> {
                app.stopAllPlayers();
                try {
                    SimpleAudioPlayer audioPlayer = app.createAudioPlayer(SoundEffect.CHEAT);
                    audioPlayer.setVolume(app.getSettings().getSoundEffectsVolume());
                    audioPlayer.play();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                         IllegalArgumentException ignored) {
                }
                app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO,"Vous avez activé une exception !"));
                throw new RuntimeException("Oups!");
            }
            case ("777") -> {
                player.setWeapon(new Weapon("Damoclès", "L'épée ultime pour tricheur !", 35,20,20));
                app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO,"Vous avez reçu l'épée de Damoclès !"));
                menu.stopDisplaying();
            }
            case ("42") -> {
                player.heal(player.getMaxHealth());
                app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO,"Vous avez activé le cheat de récupération de vie !"));
                menu.stopDisplaying();
            }
            case ("71") -> {
                if (player.hasPet()) {
                    Pet oldPet = player.getPet();
                    oldPet.setPlayer(null);
                    oldPet.getStage().getEntities().remove(oldPet);
                }
                Pet newPet = new Ludo(app);
                player.setPet(newPet);
                newPet.setPlayer(player);
                try {
                    newPet.teleport(player.getStage(), new Location(player.getLocation().getX(), player.getLocation().getY()));
                } catch (CoordinatesOutOfBoundsException | EntityAlreadyTeleportedException ignored) {
                }
                if (!player.getStage().getEntities().contains(newPet))
                    player.getStage().getEntities().add(newPet);
                app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO,"Vous avez reçu le familier Super Ludo !"));
                menu.stopDisplaying();
            }
        }
    }
}
