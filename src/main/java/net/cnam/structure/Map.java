package net.cnam.structure;

import net.cnam.entity.LivingEntity;
import net.cnam.gui.component.CComponent;
import net.cnam.object.Location;
import net.cnam.structure.block.Block;
import net.cnam.utils.StringUtils;

public class Map extends CComponent {

    private final Location playerLocation;
    private Stage stage;

    public Map(Stage stage, Location playerLocation) {
        super(0, 0);

        this.stage = stage;
        this.playerLocation = playerLocation;
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int linePointer = 0;

        for (int y = 0; y < stage.getHeight(); y++) {
            String line = "";
            for (int x = 0; x < stage.getLength(); x++) {
                try {
                    Block block = stage.getBlock(x, y);
                    LivingEntity entity = stage.getEntity(x, y);
                    line += ' ';
                    if (entity != null) {
                        line += entity.getCharacter();
                    } else if (block != null) {
                        line += block.getCharacter();
                    } else {
                        line += ' ';
                    }
                } catch (CoordinatesOutOfBoundsException ex) {
                }
            }
            line = line.replaceFirst(" ", "");
            if (line.length() > this.getLength()) {
                line = line.substring(0, this.getLength());
            } else {
                line = StringUtils.centerString(line, ' ', this.getLength());
            }
            linePointer = this.renderAddLine(result, linePointer, line);
        }

        return result;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
