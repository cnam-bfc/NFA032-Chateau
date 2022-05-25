package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.gui.Console;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

import java.util.Random;

public class EnemyUtils {

    private static final int LUCK_SPECIAL_ENNEMY = 80; // entier à dépasser sur un random entre 1 et 100

    public static Enemy getAEnemy(Console console, Stage stage, Location location) {
        if (new Random().nextInt(1, 101) > LUCK_SPECIAL_ENNEMY || Enemy.specialEnemys.isEmpty()) {
            return getSpecialEnemy(console, stage, location, new Random());
        } else {
            return getRandomEnemy(console, stage, location, new Random());
        }
    }

    public static Enemy getAEnemy(Console console, Stage stage, Location location, Random random) {
        if (random.nextInt(1, 101) > LUCK_SPECIAL_ENNEMY || Enemy.specialEnemys.isEmpty()) {
            return getSpecialEnemy(console, stage, location, random);
        } else {
            return getRandomEnemy(console, stage, location, random);
        }
    }

    public static Enemy getRandomEnemy(Console console, Stage stage, Location location) {
        return getRandomEnemy(console, stage, location, new Random());
    }

    public static Enemy getRandomEnemy(Console console, Stage stage, Location location, Random random) {
        Enemy entity = null;
        switch (random.nextInt(0, 8)) {
            case 0 -> entity = new Demogorgon(console, stage, location, random);
            case 1 -> entity = new Harpy(console, stage, location, random);
            case 2 -> entity = new HeadlessKnight(console, stage, location, random);
            case 3 -> entity = new Morbol(console, stage, location, random);
            case 4 -> entity = new Spider(console, stage, location, random);
            case 5 -> entity = new Werewolf(console, stage, location, random);
            case 7 -> entity = new Zombie(console, stage, location, random);
        }
        return entity;
    }

    public static Enemy getSpecialEnemy(Console console, Stage stage, Location location) {
        return getSpecialEnemy(console, stage, location, new Random());
    }

    public static Enemy getSpecialEnemy(Console console, Stage stage, Location location, Random random) {
        if (Enemy.specialEnemys != null) {
            return Enemy.specialEnemys.remove(random.nextInt(0, Enemy.specialEnemys.size()));
        }
        return null;
    }
}
