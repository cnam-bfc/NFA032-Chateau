package net.cnam.chateau.game;

import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import net.cnam.chateau.AppSettings;
import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
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
import net.cnam.chateau.gui.event.key.KeyEvent;
import net.cnam.chateau.gui.event.key.KeyListener;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

public class Game extends CFrame implements DisplayableComponent, KeyListener {

    private final Castle castle;
    private final Map map;
    private final Player player;
    private SimpleAudioPlayer audioPlayer;
    private boolean display = true;

    public Game(AppSettings settings) {
        this(settings, new Random().nextLong());
    }

    public Game(AppSettings settings, long seed) {
        super(0, 0);

        Generator generator = new Generator(seed);
        this.castle = generator.generateCastle();
        Stage firstStage = this.castle.getStages()[0];
        this.player = new Player(this, firstStage, new Location(castle.getPlayerStartLocation().getX(), castle.getPlayerStartLocation().getY()), "Joueur");
        firstStage.getEntities().add(0, player);
        if (player.hasPet()) {
            firstStage.getEntities().add(1, player.getPet());
        }
        this.map = new Map(player);

        this.getContentPane().getComponents().add(map);

        this.setTitle(new CLabel("Jeu\n(seed: " + this.castle.getSeed() + ")"));

        try {
            this.audioPlayer = new SimpleAudioPlayer("/songs/Stranger Things 3 - The Game Soundtrack - Russian Farm Base.wav");
            audioPlayer.setVolume(settings.getMusicVolume());
            audioPlayer.setLoop(true);
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | IllegalArgumentException ex) {
        }
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        // On transmet la touche appuyé aux composants dans cette fenêtre
        super.onKeyPressed(event);

        // On déplace le joueur vers la direction souhaité
        try {
            Direction direction = DirectionUtils.parseDirection(event.getKey());
            int x = player.getLocation().getX();
            int y = player.getLocation().getY();
            switch (direction) {
                case TOP -> {
                    y--;
                }
                case RIGHT -> {
                    x++;
                }
                case BOTTOM -> {
                    y++;
                }
                case LEFT -> {
                    x--;
                }
            }
            player.teleport(new Location(x, y));
        } catch (DirectionNotFoundException | CoordinatesOutOfBoundsException | EntityAlreadyTeleportedException ex) {
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
        if (audioPlayer != null) {
            audioPlayer.stop();
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
