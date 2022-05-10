package net.cnam.utils.direction;

import java.io.IOException;
import net.cnam.utils.console.RawConsoleInput;

public class DirectionUtils {

    public static Direction parseDirection(int input) throws DirectionNotFoundException {
        try {
            // Si un caractère d'échappemment est entré
            if (input == 27 && RawConsoleInput.read(true) == 91) {
                switch (RawConsoleInput.read(true)) {
                    // 65 = Flèche du haut
                    case 65 -> {
                        return Direction.TOP;
                    }
                    // 66 = Flèche du bas
                    case 66 -> {
                        return Direction.BOTTOM;
                    }
                    // 67 = Flèche de droite
                    case 67 -> {
                        return Direction.RIGHT;
                    }
                    // 68 = Flèche de gauche
                    case 68 -> {
                        return Direction.LEFT;
                    }
                }
            }
        } catch (IOException ex) {
        }

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
}
