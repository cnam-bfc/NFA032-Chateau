package net.cnam;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.cnam.gui.Console;
import net.cnam.generator.Generator;
import net.cnam.gui.menu.MainMenu;
import net.cnam.object.Location;
import net.cnam.structure.Castle;
import net.cnam.structure.Room;
import net.cnam.structure.Stage;
import net.cnam.utils.console.RawConsoleInput;

public class App {

    private final Console console = new Console();
    private final MainMenu mainMenu = new MainMenu();

    public void start() {
        console.adjustSize();
        mainMenu.show(this);
        debugGenerator();
    }

    public void startGame() {
        Generator gene = new Generator(new Random().nextLong());
        Castle castle = gene.generateCastle();
        Stage[] stages = castle.getStages();
        for (int i = 0; i < stages.length; i++) {
            try {
                Stage stage = stages[i];
                Room room = stage.getRooms()[0];
                console.getContent().add(stage);
                console.print();
                RawConsoleInput.read(true);
                console.getContent().remove(stage);
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

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
}
