package net.cnam.utils.direction;

public class DirectionUtils {

    public static Direction parseDirection(int input) throws DirectionNotFoundException {
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