package net.cnam.chateau.entity;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.dialog.InfoDialog;
import net.cnam.chateau.item.Key;
import net.cnam.chateau.item.consumable.HealPotion;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe d'un joueur
 */
public class Player extends Entity {
    private final App app;

    private Pet pet;
    private List<Key> keys = new ArrayList<>( );

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
        this.setItem(new HealPotion(new Random()));

        this.setRenderPriority(0);
    }

    /**
     * Redéfinition de la méthode pour téléporter une entité.
     * Permet de téléporter le joueur dans un étage à des coordonnées précise.
     * 
     * @param stage Etage de la téléportation
     * @param location Coordonnées de la téléportation
     * @throws CoordinatesOutOfBoundsException Exception levée si l'entité veut être téléporté hors de l'étage
     * @throws EntityAlreadyTeleportedException Exception levée si l'entité a déjà été téléporté
     */
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

    /**
     * Redéfinition de la méthode permettant de tuer une entité.
     * Ici on gère la mort du joueur afin de mettre fin à la partie
     */
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

    /**
     * Getter permettant de récupérer la clé que possède le joueur.
     * 
     * @return la Clé que le joueur possède
     */
    public List<Key> getlistKey() {
        return keys;
    }

    /**
     * Setter permettant de définir la clé que possède le joueur.
     * 
     * @param key Clé
     */
    public void addKey(Key key) {
        this.keys.add(key);
    }

    public List<Key> getKeys() {
        return keys;
    }

    /**
     * Méthode permettant de vérifier si le joueur à une clé.
     * 
     * @return true si le joueur possède une clé, sinon false
     */
    public boolean hasKey(Key key){
        return this.keys.contains(key);
    }
}
