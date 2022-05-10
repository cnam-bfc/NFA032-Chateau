package net.cnam.game;

import java.util.Random;
import net.cnam.entity.Player;
import net.cnam.generator.Generator;
import net.cnam.gui.DisplayableComponent;
import net.cnam.gui.component.CFrame;
import net.cnam.gui.component.CLabel;
import net.cnam.structure.Castle;
import net.cnam.structure.CoordinatesOutOfBoundsException;
import net.cnam.utils.direction.Direction;
import net.cnam.utils.direction.DirectionNotFoundException;
import net.cnam.utils.direction.DirectionUtils;

public class Game extends CFrame implements DisplayableComponent {

    private final Castle castle;
    private final Map map;
    private final Player player;
    private boolean display = true;

    public Game(Player player) {
        this(null, player);
    }

    public Game(Castle castle, Player player) {
        if (castle != null) {
            this.castle = castle;
        } else {
            Generator generator = new Generator(new Random().nextLong());
            this.castle = generator.generateCastle();
        }
        this.player = player;
        this.castle.getStages()[0].getEntities().add(player);
        this.map = new Map(this.castle.getStages()[0], player.getLocation());

        this.getContent().getContent().add(map);

        this.setTitle(new CLabel("Jeu\n(seed: " + this.castle.getSeed() + ")"));
    }

    @Override
    public void onKeyPressed(int key) {
        // TODO Enlever ça, temporaire
        if (key == 13 || key == 10) {
            stopDisplaying();
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
    public boolean isDisplayable() {
        return display;
    }

    public void stopDisplaying() {
        display = false;
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
