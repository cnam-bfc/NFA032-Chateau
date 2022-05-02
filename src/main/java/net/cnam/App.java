package net.cnam;

import java.util.Random;
import net.cnam.console.Console;
import net.cnam.generator.Generator;
import net.cnam.object.Location;
import net.cnam.structure.Castle;
import net.cnam.structure.Room;
import net.cnam.structure.Stage;

public class App {

    public static void main(String[] args) {
        // Console console = new Console();
        // console.adjustSize();
        // console.debugKeys();
        debugGenerator();
    }

    private static void debugGenerator() {
        Generator gene = new Generator(new Random().nextLong());
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
    }

    // Méthode pour effacer la console
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
