package net.cnam.structure;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.cnam.entity.Personage;
import net.cnam.generator.Generator;
import net.cnam.gui.Console;
import net.cnam.gui.component.CFrame;
import net.cnam.gui.component.CLabel;
import net.cnam.utils.console.RawConsoleInput;

public class Game extends CFrame {

    private final Castle castle;
    private final Map map;
    private final Personage player;

    public Game(Personage player) {
        this(null, player);
    }

    public Game(Castle castle, Personage player) {
        if (castle != null) {
            this.castle = castle;
        } else {
            Generator generator = new Generator(new Random().nextLong());
            this.castle = generator.generateCastle();
        }
        this.player = player;
        this.map = new Map(this.castle.getStages()[0], player.getLocation());

        this.getContent().getContent().add(map);

        this.setTitle(new CLabel("Jeu (seed: " + this.castle.getSeed() + ")"));
    }

    public void start(Console console) {
        this.setSize(console.getLength(), console.getHeight());
        console.getContent().add(this);
        console.print();
        try {
            RawConsoleInput.read(true);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
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
