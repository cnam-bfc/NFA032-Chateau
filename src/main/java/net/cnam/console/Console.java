package net.cnam.console;

import java.io.IOException;
import net.cnam.utils.StringUtils;
import net.cnam.utils.console.RawConsoleInput;

/**
 *
 */
public class Console {

    // ┌┐└┘├┤─│
    private static final int MIN_LENGTH = 80;
    private static final int MIN_HEIGHT = 25;

    // Dimensions par défaut
    private int length = MIN_LENGTH;
    private int height = MIN_HEIGHT;

    private Window content;

    public void adjustSize() {
        while (true) {
            clear();
            System.out.println('┌' + "─".repeat(length - 2) + '┐');
            String line = '│' + " ".repeat(length - 2) + '│';
            // Lignes de la console - lignes de texte au millieu (3) + la 1ère ligne (+1) + la dernière ligne (+1) = 5
            int paddingHeight = height - 5;
            for (int i = 0; i < paddingHeight / 2; i++) {
                System.out.println(line);
            }
            System.out.println('│' + StringUtils.centerString("Veuillez ajustez le cadre pour qu'il soit sur les bords de l'écran", ' ', length - 2) + '│');
            System.out.println('│' + StringUtils.centerString("Pour cela vous pouvez utiliser les flèches directionnelles ou zqsd", ' ', length - 2) + '│');
            System.out.println('│' + StringUtils.centerString("Appuyez sur \"Entrée\" pour valider", ' ', length - 2) + '│');
            for (int i = 0; i < paddingHeight / 2 + paddingHeight % 2; i++) {
                System.out.println(line);
            }
            System.out.println('└' + "─".repeat(length - 2) + '┘');

            try {
                int input = RawConsoleInput.read(true);
                // 13 = Entrée dans un terminal ; 10 = Entrée dans netbeans
                if (input == 13 || input == 10) {
                    return;
                }
                Direction direction = parseDirection(input);
                switch (direction) {
                    case LEFT -> {
                        if (length > MIN_LENGTH) {
                            length--;
                        }
                    }
                    case RIGHT -> {
                        length++;
                    }
                    case TOP -> {
                        if (height > MIN_HEIGHT) {
                            height--;
                        }
                    }
                    case BOTTOM -> {
                        height++;
                    }
                }
            } catch (IOException ex) {
                System.out.println("ERREUR");
                System.exit(1);
            } catch (DirectionNotFoundException ex) {

            }
        }
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

    // Méthode pour effacer la console
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void debugKeys() {
        while (true) {
            try {
                System.out.println(RawConsoleInput.read(true));
            } catch (IOException ex) {
            }
        }
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public Window getContent() {
        return content;
    }

    public void setContent(Window content) {
        this.content = content;
    }

    @Override
    public String toString() {
        String result = "";
        if (content != null) {
            String[] lines = StringUtils.convertStringToStringArray(content.toString());
            // Lignes de la console - lignes de texte au millieu
            int paddingHeight = height - lines.length;
            for (int i = 0; i < paddingHeight / 2; i++) {
                result += "\n";
            }
            for (String line : lines) {
                result += "\n" + StringUtils.centerString(line, ' ', length + 2).substring(1, length + 1);
            }
            for (int i = 0; i < paddingHeight / 2 + paddingHeight % 2; i++) {
                result += "\n";
            }
        }
        return result.replaceFirst("\n", "");
    }
}
