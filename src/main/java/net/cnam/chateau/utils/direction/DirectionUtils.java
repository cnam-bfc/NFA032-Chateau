package net.cnam.chateau.utils.direction;

public class DirectionUtils {

    /**
     * Méthode permettant de savoir la direction emprunté.
     * 
     * @param input // TODO victor
     * @return La constante lié à la direction
     * @throws DirectionNotFoundException Lève une exception si la direction est inconnue
     */
    public static Direction parseDirection(int input) throws DirectionNotFoundException {
        switch (input) {
            // 57416 = Flèche haut ; 122 = z ; 90 = Z
            case 57416 -> {
                return Direction.TOP;
            }
            // 57424 = Flèche bas ; 115 = s ; 83 = S
            case 57424 -> {
                return Direction.BOTTOM;
            }
            // 57419 = Flèche gauche ; 113 = q ; 81 = Q
            case 57419 -> {
                return Direction.LEFT;
            }
            // 57421 = Flèche droite ; 100 = d ; 68 = D
            case 57421 -> {
                return Direction.RIGHT;
            }
        }

        throw new DirectionNotFoundException("Le caractère n'est pas une direction valide !");
    }
}
