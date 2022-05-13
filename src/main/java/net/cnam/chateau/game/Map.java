package net.cnam.chateau.game;

import net.cnam.chateau.entity.LivingEntity;
import net.cnam.chateau.gui.component.CComponent;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.structure.block.Block;
import net.cnam.chateau.structure.block.Wall;

public class Map extends CComponent {

    private final Location playerLocation;
    private Stage stage;
    private Location origin;

    public Map(Stage stage, Location playerLocation) {
        super(0, 0);

        this.stage = stage;
        this.playerLocation = playerLocation;
        this.origin = new Location(0, 0);
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int linePointer = 0;
        String emptyLine = " ".repeat(this.getLength());

        // Déplacer la map en fonction de la position du joueur
        // Si le joueur se rapproche du bord haut
        while (origin.getY() > 0 && playerLocation.getY() - 4 < origin.getY()) {
            int newY = origin.getY() - 4;
            if (newY > 0) {
                origin.setY(newY);
            } else {
                origin.setY(0);
            }
        }
        // Si le joueur se rapproche du bord bas
        while (playerLocation.getY() + 5 > origin.getY() + this.getHeight()) {
            // Et que l'on peut abaisser la map
            if (origin.getY() + this.getHeight() + 4 <= stage.getHeight()) {
                origin.setY(origin.getY() + 4);
            } else {
                int newY = stage.getHeight() - this.getHeight();
                if (newY >= 0) {
                    origin.setY(newY);
                }
                break;
            }
        }
        // Si le joueur se rapproche du bord gauche
        while (origin.getX() > 0 && playerLocation.getX() - 4 < origin.getX()) {
            int newX = origin.getX() - 4;
            if (newX > 0) {
                origin.setX(newX);
            } else {
                origin.setX(0);
            }
        }
        // Si le joueur se rapproche du bord droit
        while (playerLocation.getX() + 5 > origin.getX() + this.getLength() / 2) {
            // Et que l'on peut bouger la map
            if (origin.getX() + this.getLength() / 2 + 4 <= stage.getLength()) {
                origin.setX(origin.getX() + 4);
            } else {
                int newX = stage.getLength() - this.getLength() / 2;
                if (newX >= 0) {
                    origin.setX(newX);
                }
                break;
            }
        }

        // Lignes de la console - lignes de texte au millieu
        int paddingHeight = this.getHeight() - stage.getHeight();
        for (int i = 0; i < paddingHeight / 2; i++) {
            result[linePointer++] = emptyLine;
        }

        // Colonnes de la console - colonnes de texte au milieu
        int paddingLength = this.getLength() - stage.getLength() * 2 - 1;

        for (int y = origin.getY(); y < stage.getHeight(); y++) {
            String line = "";
            int lineLength = 0;
            for (; lineLength < paddingLength / 2; lineLength++) {
                line += ' ';
            }
            for (int x = origin.getX(); x < stage.getLength(); x++) {
                if (lineLength + 2 > this.getLength()) {
                    break;
                }
                lineLength++;
                if (x != 0) {
                    lineLength++;
                }

                try {
                    Block block = stage.getBlock(x, y);
                    LivingEntity entity = stage.getEntity(x, y);
                    if (x != 0) {
                        if (block instanceof Wall wall) {
                            line += wall.getCharacter();
                        } else if (stage.getBlock(x - 1, y) instanceof Wall wall) {
                            line += wall.getCharacter();
                        } else {
                            line += ' ';
                        }
                    }
                    if (entity != null) {
                        line += entity.getCharacter();
                    } else if (block != null) {
                        line += block.getCharacter();
                    } else {
                        line += ' ';
                    }
                } catch (CoordinatesOutOfBoundsException ex) {
                    break;
                }
            }
            for (; lineLength < this.getLength(); lineLength++) {
                line += ' ';
            }
            if (linePointer < result.length) {
                result[linePointer++] = line;
            } else {
                break;
            }
        }

        // Bourage à la fin
        for (; linePointer < result.length; linePointer++) {
            result[linePointer] = emptyLine;
        }

        return result;
    }

    @Override
    public void onKeyPressed(int key) {
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        this.origin = new Location(0, 0);
    }
}
