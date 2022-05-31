package net.cnam.chateau.utils.direction;

public class DirectionUtils {

    /**
     * Méthode permettant de savoir la direction emprunté.
     *
     * @param input touche appuyée par l'utilisateur
     * @param zqsd  Prendre en compte les touches ZQSD
     * @return La direction
     * @throws DirectionNotFoundException Lève une exception si la direction est inconnue
     */
    public static Direction parseDirection(int input, boolean zqsd) throws DirectionNotFoundException {
        if (zqsd) {
            switch (input) {
                // 57416 = Flèche haut ; 122 = z ; 90 = Z
                case 122, 90 -> input = 57416;
                // 57424 = Flèche bas ; 115 = s ; 83 = S
                case 115, 83 -> input = 57424;
                // 57419 = Flèche gauche ; 113 = q ; 81 = Q
                case 113, 81 -> input = 57419;
                // 57421 = Flèche droite ; 100 = d ; 68 = D
                case 100, 68 -> input = 57421;
            }
        }

        switch (input) {
            // 57416 = Flèche haut
            case 57416 -> {
                return Direction.TOP;
            }
            // 57424 = Flèche bas
            case 57424 -> {
                return Direction.BOTTOM;
            }
            // 57419 = Flèche gauche
            case 57419 -> {
                return Direction.LEFT;
            }
            // 57421 = Flèche droite
            case 57421 -> {
                return Direction.RIGHT;
            }
        }

        throw new DirectionNotFoundException("Le caractère n'est pas une direction valide !");
    }
}
