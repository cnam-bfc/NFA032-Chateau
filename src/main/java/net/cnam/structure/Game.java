package net.cnam.structure;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.cnam.entity.Personage;
import net.cnam.entity.Player;
import net.cnam.generator.Generator;
import net.cnam.gui.Console;
import net.cnam.gui.component.CButton;
import net.cnam.gui.component.CFrame;
import net.cnam.gui.component.CLabel;
import net.cnam.utils.console.RawConsoleInput;
import net.cnam.utils.direction.Direction;
import net.cnam.utils.direction.DirectionNotFoundException;
import net.cnam.utils.direction.DirectionUtils;

public class Game extends CFrame {

    private final Castle castle;
    private final Map map;
    private final Player player;

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

        this.setTitle(new CLabel("Jeu (seed: " + this.castle.getSeed() + ")"));
    }

    public void start(Console console) {
        this.setSize(console.getLength(), console.getHeight());
        console.getContent().add(this);
        while (true) {
            console.print();
            try {
                int input = RawConsoleInput.read(true);
                // 13 = Entrée dans un terminal ; 10 = Entrée dans netbeans
                if (input == 13 || input == 10) {
                    break;
                }

                Direction direction = DirectionUtils.parseDirection(input);
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
            } catch (IOException ex) {
                System.out.println("ERREUR");
                System.exit(1);
            } catch (DirectionNotFoundException | CoordinatesOutOfBoundsException ex) {

            }
        }
        console.getContent().remove(this);
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

    public Personage getPlayer() {
        return player;
    }
}
