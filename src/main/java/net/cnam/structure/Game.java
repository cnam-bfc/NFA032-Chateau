package net.cnam.structure;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.cnam.App;
import net.cnam.entity.Enemy;
import net.cnam.entity.Player;
import net.cnam.fight.Fight;
import net.cnam.generator.Generator;
import net.cnam.gui.Console;
import net.cnam.gui.component.CComponent;
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
    private boolean running = false;
    private App app;

    public Game(App app, Player player) {
        this(app, null, player);
    }

    public Game(App app, Castle castle, Player player) {
        this.app = app;
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

    public void start(App app) {
        if (running || !app.isRunning()) {
            return;
        }

        running = true;

        Console console = app.getConsole();

        this.setSize(console.getLength(), console.getHeight());
        List<CComponent> save = new LinkedList<>(console.getContent());
        console.getContent().clear();

        console.getContent().add(this);
        while (running && app.isRunning()) {
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
        console.getContent().clear();
        console.getContent().addAll(save);
        stop();
    }

    public void stop() {
        if (!running) {
            return;
        }

        running = false;
    }
    
    /**
     * Méthode pour ouvrir une fenêtre de combat et démarrer le combat.
     * 
     * @param enemy l'ennmie qu'affronte le joueur
     */
    public void fight(Enemy enemy){
        Fight fight = new Fight(this.player, enemy);
        fight.start(app);
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

    public boolean isRunning() {
        return running;
    }
}
