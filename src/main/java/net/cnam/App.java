package net.cnam;

import java.io.IOException;
import java.util.Random;
import net.cnam.gui.Console;
import net.cnam.generator.Generator;
import net.cnam.gui.menu.mainmenu.MainMenu;
import net.cnam.structure.Castle;
import net.cnam.structure.Game;
import net.cnam.structure.Stage;
import net.cnam.utils.console.RawConsoleInput;

public class App {

    private final Console console;
    private final MainMenu mainMenu;
    private Game currentGame;
    private boolean running;

    public App() {
        this.running = false;
        this.console = new Console();
        this.mainMenu = new MainMenu(this);
    }

    public void start() {
        if (running) {
            return;
        }

        running = true;
        console.adjustSize(this);

        mainMenu.setSize(console.getLength(), console.getHeight());
        console.getContent().add(mainMenu);

        while (running) {
            console.print();
            try {
                int input = RawConsoleInput.read(true);
                console.keyPressed(input);
            } catch (IOException ex) {
                System.out.println("ERREUR");
                System.exit(1);
            }
        }

        //TODO REMOVE THIS
        debugGenerator();
    }

    public void stop() {
        if (!running) {
            return;
        }

        running = false;
    }

    // TODO REMOVE THIS
    public void debugGenerator() {
        Generator gene = new Generator(new Random().nextLong()); //new Random().nextLong() //2027015466020144793L
        System.out.println("Seed: " + gene.getSeed());
        Castle castle = gene.generateCastle();
        Stage[] stages = castle.getStages();
        for (int i = 1; i <= stages.length; i++) {
            Stage stage = stages[i - 1];
            System.out.println("-------------------------");
            System.out.println("étage " + i + " (" + stage.getRooms().length + " pièces)");
            System.out.println(stage);
//            Room[] rooms = stage.getRooms();
//            for (int y = 1; y <= rooms.length; y++) {
//                Room room = rooms[y - 1];
//                Location location = room.getLocation();
//                System.out.println("pièce " + y + " (x=" + location.getX() + " y=" + location.getY() + " length=" + room.getLength() + " height=" + room.getHeight() + ")");
//                System.out.println(room);
//            }
        }
    }

    public Console getConsole() {
        return console;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public boolean isRunning() {
        return running;
    }
}
