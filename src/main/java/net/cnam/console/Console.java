package net.cnam.console;

import java.io.IOException;
import net.cnam.utils.StringUtils;
import net.cnam.utils.console.RawConsoleInput;

/**
 *
 */
public class Console {
    // ┌┐└┘├┤─│

    // Dimensions par défaut
    private int length = 80;
    private int height = 25;

    public void adjustSize() {
        while (true) {
            clear();
            System.out.println("┌" + "─".repeat(length - 2) + "┐");
            String line = "│" + " ".repeat(length - 2) + "│";
            for (int i = 0; i < height / 2; i++) {
                System.out.println(line);
            }
            System.out.println("│" + StringUtils.centerText("Veuillez ajustez le cadre pour qu'il soit sur les bords de l'écran", ' ', length - 2) + "│");
            System.out.println("│" + StringUtils.centerText("Pour cela vous pouvez utiliser les flèches directionnelles ou zqsd", ' ', length - 2) + "│");
            System.out.println("│" + StringUtils.centerText("Appuyez sur \"Entrée\" pour valider", ' ', length - 2) + "│");
            for (int i = 0; i < height / 2 + height % 2; i++) {
                System.out.println(line);
            }
            System.out.println("└" + "─".repeat(length - 2) + "┘");

            try {
                int input = RawConsoleInput.read(true);
                // 13 = Entrée dans un terminal ; 10 = Entrée dans netbeans
                if (input == 13 || input == 10) {
                    return;
                }
                Direction direction = parseDirection(input);
                switch (direction) {
                    case BOTTOM -> {
                        if (height > 25) {
                            height--;
                        }
                    }
                    case TOP -> {
                        height++;
                    }
                    case LEFT -> {
                        if (length > 80) {
                            length--;
                        }
                    }
                    case RIGHT -> {
                        length++;
                    }
                }
            } catch (IOException ex) {
                System.out.println("ERREUR");
                System.exit(1);
            } catch (DirectionNotFoundException ex) {

            }
        }
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    // Méthode pour effacer la console
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public String toString() {
        return "";
        // return StringUtils.convertStringArrayToString(render());
    }

    public Direction parseDirection(int input) throws DirectionNotFoundException {
        switch (input) {
            // 57416 = Flèche haut ; 122 = z ; 90 = Z
            case 57416, 122, 90 -> {
                return Direction.TOP;
            }
            // 57424 = Flèche bas ; 115 = s ; 83 = S
            case 57424, 115, 83 -> {
                return Direction.BOTTOM;
            }
            // 57419 = Flèche gauche ; 113 = q ; 81 = Q
            case 57419, 113, 81 -> {
                return Direction.LEFT;
            }
            // 57421 = Flèche droite ; 100 = d ; 68 = D
            case 57421, 100, 68 -> {
                return Direction.RIGHT;
            }
        }

        throw new DirectionNotFoundException("Le caractère n'est pas une direction valide !");
    }

    public void debugKeys() {
        while (true) {
            try {
                System.out.println(RawConsoleInput.read(true));
            } catch (IOException ex) {
            }
        }
    }
}
