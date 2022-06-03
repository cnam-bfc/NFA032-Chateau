package net.cnam.chateau.gui.play.escape.cheat;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Ludo;
import net.cnam.chateau.entity.Pet;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.ErrorDialog;
import net.cnam.chateau.gui.dialog.InfoDialog;
import net.cnam.chateau.gui.play.escape.EscapeMenu;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.structure.block.Block;
import net.cnam.chateau.structure.block.UpStair;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ActivateCheatButton extends CButton {
    private final App app;
    private final CheatMenu menu;
    private final Game game;
    private final EscapeMenu escapeMenu;

    public ActivateCheatButton(App app, CheatMenu menu, Game game, EscapeMenu escapeMenu) {
        super(app, "Activer le code de triche");

        this.app = app;
        this.menu = menu;
        this.game = game;
        this.escapeMenu = escapeMenu;
    }

    @Override
    public void execute() {
        String cheatCode = menu.getCheatCodeField().getText();
        Player player = game.getPlayer();

        switch (cheatCode) {
            case ("404") -> {
                app.stopAllPlayers();
                try {
                    SimpleAudioPlayer audioPlayer = app.createAudioPlayer(Music.CHEAT);
                    audioPlayer.setVolume(app.getSettings().getMusicVolume());
                    audioPlayer.play();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                         IllegalArgumentException ignored) {
                }
                app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO, "Vous avez activé une exception !"));
                throw new RuntimeException("Oups!");
            }
            case ("777") -> {
                player.setWeapon(new Weapon("Damoclès", "L'épée ultime pour tricheur !", 35, 20, 20));
                app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO, "Vous avez reçu l'épée de Damoclès !"));
            }
            case ("42") -> {
                player.heal(player.getMaxHealth());
                app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO, "Vous avez activé le cheat de récupération de vie !"));
            }
            case ("LUDO!") -> {
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
                app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO, "Vous avez reçu le familier Super Ludo !"));
            }
            case ("666") -> {
                player.kill();
                app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO, "Il y a parfois des courageux!\n \nMais il faut croire que vous n'en n'êtes pas un."));
                escapeMenu.stopLoopingMode();
            }
            case ("BOSS") -> {
                Stage stage = game.getCastle().getStages()[game.getCastle().getStages().length - 1];
                firstLoop:
                for (Room room : stage.getRooms()) {
                    for (Block[] blocks : room.getBlocks()) {
                        for (Block block : blocks) {
                            if (block instanceof UpStair upstair) {
                                try {
                                    player.teleport(upstair.getOtherStair().getStage(), upstair.getOtherStair().getLocation());
                                } catch (CoordinatesOutOfBoundsException | EntityAlreadyTeleportedException ignored) {
                                }
                                break firstLoop;
                            }
                        }
                    }
                }
                app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO, "Vous avez été téléporté dans la salle du boss !"));
                escapeMenu.stopLoopingMode();
            }
            default -> {
                app.getConsole().show(new ErrorDialog(ErrorDialog.Type.WARNING, "Code de triche invalide !"));
                return;
            }
        }
        game.getStatistic().activeCheat();
        menu.stopLoopingMode();
    }
}
