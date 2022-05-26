package net.cnam.chateau.game;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.generator.Generator;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.CPanel;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.play.EntityStats;
import net.cnam.chateau.structure.Castle;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;
import net.cnam.chateau.utils.direction.Orientation;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Game extends CFrame implements DisplayableComponent {
    private final Castle castle;
    private final Map map;
    private final Player player;
    private SimpleAudioPlayer audioPlayer;
    private boolean display = true;

    public Game(App app, long seed) {
        super(0, 0);

        Generator generator = new Generator(app, seed);
        this.castle = generator.generateCastle();
        Stage firstStage = this.castle.getStages()[0];
        this.player = new Player(this, firstStage, new Location(castle.getPlayerStartLocation().getX(), castle.getPlayerStartLocation().getY()), "Joueur");
        firstStage.getEntities().add(0, player);
        if (player.hasPet()) {
            firstStage.getEntities().add(1, player.getPet());
        }
        this.map = new Map(player);

        this.getContentPane().getComponents().add(map);

        CLabel title = new CLabel("Jeu\n(seed: " + this.castle.getSeed() + ")");
        CPanel header = new CPanel(0, title.getHeight());
        header.getComponents().add(title);
        this.setHeader(header);

        CPanel footer = new CPanel(0, 2, Orientation.HORIZONTAL, false);

        EntityStats playerStats = new EntityStats(player, 50);
        footer.getComponents().add(playerStats);

        this.setFooter(footer);

        try {
            this.audioPlayer = new SimpleAudioPlayer(Music.GAME.getFilePath());
            audioPlayer.setVolume(app.getSettings().getMusicVolume());
            audioPlayer.setLoop(true);
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                 IllegalArgumentException ignored) {
        }
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        // On transmet la touche appuyée aux composants dans cette fenêtre
        super.onKeyPressed(event);

        // On déplace le joueur vers la direction souhaitée
        try {
            Direction direction = DirectionUtils.parseDirection(event.getKey());
            int x = player.getLocation().getX();
            int y = player.getLocation().getY();
            switch (direction) {
                case TOP -> y--;
                case RIGHT -> x++;
                case BOTTOM -> y++;
                case LEFT -> x--;
            }
            player.teleport(new Location(x, y));
        } catch (DirectionNotFoundException | CoordinatesOutOfBoundsException |
                 EntityAlreadyTeleportedException ignored) {
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

        this.map.setHeight(this.getContentPane().getHeight());
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);

        this.map.setLength(this.getContentPane().getLength());
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

    public SimpleAudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}
