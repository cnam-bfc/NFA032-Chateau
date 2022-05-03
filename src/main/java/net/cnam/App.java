package net.cnam;

import java.util.Random;
import net.cnam.gui.Console;
import net.cnam.generator.Generator;
import net.cnam.gui.menu.MainMenu;
import net.cnam.object.Location;
import net.cnam.structure.Castle;
import net.cnam.structure.Room;
import net.cnam.structure.Stage;

public class App {

    private final Console console = new Console();
    private final MainMenu mainMenu = new MainMenu();

    public void start() {
        console.adjustSize();
        mainMenu.show(console);
        debugGenerator();
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
            Room[] rooms = stage.getRooms();
            for (int y = 1; y <= rooms.length; y++) {
                Room room = rooms[y - 1];
                Location location = room.getLocation();
                System.out.println("pièce " + y + " (x=" + location.getX() + " y=" + location.getY() + " length=" + room.getLength() + " width=" + room.getWidth() + ")");
            }
        }
        Room[] test= stages[0].getRooms();
        Room test2 = test[0];
        System.out.println(test2);
    }
}
