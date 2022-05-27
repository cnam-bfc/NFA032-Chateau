package net.cnam.chateau.entity;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.dialog.InfoDialog;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Classe d'un joueur
 */
public class Player extends Entity {
    private final App app;

    private Pet pet;

    /**
     * Constructeur
     *
     * @param app      L'application
     * @param stage    L'étage où se situe l'entité
     * @param location Coordonnées de l'entité
     * @param name     Le nom de l'entité
     */
    public Player(App app, Stage stage, Location location, String name) {
        super(app, stage, location, name, 100, 50, 10, 10, 10);

        this.app = app;

        this.setRenderPriority(0);
    }

    @Override
    public void teleport(Stage stage, Location location)
            throws CoordinatesOutOfBoundsException, EntityAlreadyTeleportedException {
        Stage oldPlayerStage = this.getStage();
        Location oldPlayerLocation = new Location(this.getLocation().getX(), this.getLocation().getY());

        super.teleport(stage, location);

        // Si le joueur à changé d'étage
        if (oldPlayerStage != stage) {
            // On rend la pièce de destination visible
            Room destinationRoom = stage.getRoom(location);
            destinationRoom.setVisible(true);
            return;
        }

        // Si le joueur à un pet, on le téléporte aussi
        if (this.hasPet() && this.getPet().isFollowingPlayer()) {
            this.getPet().teleport(stage, oldPlayerLocation);
        }
    }

    @Override
    public void kill() {
        super.kill();

        app.getCurrentGame().stop();

        SimpleAudioPlayer audioPlayer = null;
        try {
            audioPlayer = app.createAudioPlayer(Music.DEATH);
            audioPlayer.setVolume(app.getSettings().getMusicVolume());
            audioPlayer.setLoop(true);
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                 IllegalArgumentException ignored) {
        }
        app.getConsole().show(new InfoDialog(InfoDialog.Type.DEAD, "GAME OVER"));
        if (audioPlayer != null) {
            audioPlayer.stop();
        }
    }

    /**
     * Retourne le caractère à afficher pour un joueur
     *
     * @return Le caractère
     */
    @Override
    public String getCharacter() {
        return CColor.CYAN + "P" + CColor.CYAN.getForegroundReset();
    }

    /**
     * Permet de savoir si le joueur possède un pet
     *
     * @return si le joueur possède un pet
     */
    public boolean hasPet() {
        return this.pet != null;
    }

    /**
     * Permet de récupérer le pet du joueur
     *
     * @return Le pet
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Permet de définir un pet au joueur
     *
     * @param pet Un pet
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
