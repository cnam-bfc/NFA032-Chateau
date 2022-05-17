package net.cnam.chateau.game;

import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.generator.Generator;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.structure.Castle;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;
import net.cnam.chateau.gui.DisplayableComponent;

public class Game extends CFrame implements DisplayableComponent {

    private final Castle castle;
    private final Map map;
    private final Player player;
    private SimpleAudioPlayer audioPlayer;
    private boolean display = true;

    public Game(AppSettings settings, Player player) {
        this(settings, null, player);
    }

    public Game(AppSettings settings, Castle castle, Player player) {
        super(0, 0);

        if (castle != null) {
            this.castle = castle;
        } else {
            Generator generator = new Generator(new Random().nextLong());
            this.castle = generator.generateCastle();
        }
        this.player = player;
        this.castle.getStages()[0].getEntities().add(player);
        this.castle.getStages()[0].getEntities().add(player.getPet()); //à voir pour faire mieux
        this.map = new Map(this.castle.getStages()[0], player.getLocation());

        this.getContent().getContent().add(map);

        this.setTitle(new CLabel("Jeu\n(seed: " + this.castle.getSeed() + ")"));

        try {
            this.audioPlayer = new SimpleAudioPlayer("/songs/Stranger Things 3 - The game soundtrack - Russian Farm Base.wav");
            audioPlayer.setVolume(settings.getMusicVolume());
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
        }
    }

    @Override
    public void onKeyPressed(int key) {
        // TODO Enlever ça, temporaire
        if (key == 13 || key == 10) {
            stop();
            return;
        }

        // On transmet la touche appuyé aux composants dans cette fenêtre
        super.onKeyPressed(key);

        // On déplace le joueur vers la direction souhaité
        try {
            Direction direction = DirectionUtils.parseDirection(key);
            switch (direction) {
                case TOP -> {
                    this.getCastle().getStages()[0].move(player, 0, -1);
                }
                case RIGHT -> {
                    this.getCastle().getStages()[0].move(player, 1, 0);
                }
                case BOTTOM -> {
                    this.getCastle().getStages()[0].move(player, 0, 1);
                }
                case LEFT -> {
                    this.getCastle().getStages()[0].move(player, -1, 0);
                }
            }
        } catch (DirectionNotFoundException | CoordinatesOutOfBoundsException ex) {
        }
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    public void stop() {
        display = false;
        try {
            audioPlayer.stop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException ex) {
        }
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);

        int mapHeight = height - 2;
        if (this.getTitle() != null) {
            mapHeight -= this.getTitle().getHeight();
            mapHeight--;
        }
        this.map.setHeight(mapHeight);
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);

        this.map.setLength(length - 2);
    }

    public Castle getCastle() {
        return castle;
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }
}
