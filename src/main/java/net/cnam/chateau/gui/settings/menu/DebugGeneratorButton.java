package net.cnam.chateau.gui.settings.menu;

import java.util.Random;
import net.cnam.chateau.generator.Generator;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.Castle;
import net.cnam.chateau.structure.Stage;

public class DebugGeneratorButton extends CButton {

    public DebugGeneratorButton() {
        super("Debug generator");
    }

    @Override
    public void execute() {
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
}
