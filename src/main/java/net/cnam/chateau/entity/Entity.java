package net.cnam.chateau.entity;

import net.cnam.chateau.App;
import net.cnam.chateau.DisplayableObject;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.event.entity.EntityApproachEvent;
import net.cnam.chateau.event.entity.EntityListener;
import net.cnam.chateau.game.EntityDeadException;
import net.cnam.chateau.gui.play.fight.Fight;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.wearable.Wearable;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.structure.block.Block;
import net.cnam.chateau.structure.block.Stair;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.weapon.Weapon;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe d'une entité
 */
public abstract class Entity implements DisplayableObject {
    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_RESISTANCE = 50;
    private static final int DEFAULT_STRENGTH = 15;
    private static final int DEFAULT_ACCURACY = 15;
    private static final int DEFAULT_SPEED = 2;

    private final App app;
    private Location location;
    private Stage stage;
    private String name;
    private Weapon weapon;
    private Item item;
    private int health = DEFAULT_HEALTH;
    private int maxHealth = DEFAULT_HEALTH;
    private int resistance = DEFAULT_RESISTANCE;
    private int maxResistance = DEFAULT_RESISTANCE;
    private int strength = DEFAULT_STRENGTH;
    private int maxStrength = DEFAULT_STRENGTH;
    private int accuracy = DEFAULT_ACCURACY;
    private int maxAccuracy = DEFAULT_ACCURACY;
    private int speed = DEFAULT_SPEED;
    private int maxSpeed = DEFAULT_SPEED;

    private int renderPriority = -1;

    /**
     * Constructeur
     *
     * @param app        L'application
     * @param stage      L'étage où elle se situe
     * @param location   Coordonnées où elle se situe
     * @param name       Le nom
     * @param health     La santé
     * @param resistance La résistance
     * @param strength   La force
     * @param accuracy   La précision
     * @param speed      La rapidité
     */
    public Entity(App app, Stage stage, Location location, String name, int health, int resistance, int strength, int accuracy,
                  int speed) {
        this(app, stage, location, name);

        this.health = health;
        this.maxHealth = health;
        this.resistance = resistance;
        this.maxResistance = resistance;
        this.strength = strength;
        this.maxStrength = strength;
        this.accuracy = accuracy;
        this.maxAccuracy = accuracy;
        this.speed = speed;
        this.maxSpeed = speed;
    }

    /**
     * Constructeur
     *
     * @param app      L'application
     * @param stage    L'étage où se situe l'entité
     * @param location Coordonnées de l'entité
     * @param name     Le nom de l'entité
     */
    public Entity(App app, Stage stage, Location location, String name) {
        this.app = app;
        this.stage = stage;
        this.location = location;
        this.name = name;
    }

    /**
     * Méthode permettant de téléporter l'entité à des coordonnées précises.
     *
     * @param location Les coordonnées de destination
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException Exception levée si l'entité veut être téléporté hors de l'étage
     * @throws net.cnam.chateau.entity.EntityAlreadyTeleportedException   Exception levée si l'entité a déjà été téléporté
     */
    public void teleport(Location location) throws CoordinatesOutOfBoundsException, EntityAlreadyTeleportedException {
        teleport(stage, location);
    }

    /**
     * Méthode permettant de téléporter l'entité dans un étage à des coordonnées
     * précises.
     *
     * @param stage    L'étage de destination
     * @param location Les coordonnées de destination
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException Exception levée si l'entité veut être téléporté hors de l'étage
     * @throws net.cnam.chateau.entity.EntityAlreadyTeleportedException   Exception levée si l'entité a déjà été téléporté
     */
    public void teleport(Stage stage, Location location)
            throws CoordinatesOutOfBoundsException, EntityAlreadyTeleportedException {
        // On vérifie que les coordonnées de destination sont valides
        if (location.getX() < 0 || location.getY() < 0 || location.getX() >= stage.getLength()
                || location.getY() >= stage.getHeight()) {
            throw new CoordinatesOutOfBoundsException("L'entité ne peut pas sortir de l'étage!");
        }

        if (this.location == null) {
            this.location = new Location(0, 0);
        }

        if (this.stage == null) {
            this.stage = stage;
        }

        List<Entity> nearbyEntities = new LinkedList<>();
        for (Entity entity : stage.getEntities()) {
            if (entity.getLocation().equals(location)) {
                nearbyEntities.add(entity);
            }
        }

        // On notifie les entités aux alentours que l'entité a été déplacé
        EntityApproachEvent entityApproachEvent = new EntityApproachEvent(this);
        for (Entity entity : nearbyEntities) {
            if (entity instanceof EntityListener listener) {
                listener.onEntityApproachEvent(entityApproachEvent);

                if (entityApproachEvent.isCanceled()) {
                    return;
                }
            }
        }

        Stage saveEntityStage = this.stage;
        int saveEntityX = this.location.getX();
        int saveEntityY = this.location.getY();

        Block oldLocationBlock = this.stage.getBlock(this.location);
        Block newLocationBlock = stage.getBlock(location);
        // On notifie le nouveau block que l'entité rentre sur celui-ci
        // sauf si l'entité a changé d'étage et que le bloc est un escalier (sinon on
        // boucle)
        if (!(this.stage != stage && newLocationBlock instanceof Stair)) {
            if (newLocationBlock instanceof BlockListener blockListener) {
                EntityEnterBlockEvent entityEnterBlockEvent = new EntityEnterBlockEvent(this);
                blockListener.onEntityEnterBlock(entityEnterBlockEvent);
                // Si le block refuse que l'entité rentre sur son territoire, on ne déplace pas l'entité
                if (entityEnterBlockEvent.isCanceled()) {
                    return;
                }
            }
        }

        // On notifie l'ancien block où était l'entité que celle-ci est partie
        if (oldLocationBlock instanceof BlockListener blockListener) {
            blockListener.onEntityLeaveBlock(new EntityLeaveBlockEvent(this));
        }

        // Si le joueur a été téléporté entre temps on annule la suite
        if (saveEntityStage != this.stage || saveEntityX != this.location.getX()
                || saveEntityY != this.location.getY()) {
            throw new EntityAlreadyTeleportedException("L'entité à déjà été téléporté");
        }

        // Si l'entité est téléportée sur une autre étage
        if (this.stage != stage) {
            // On enlève l'entité de l'étage précédent
            this.stage.getEntities().remove(this);
            // On l'ajoute dans le nouvel étage
            this.stage = stage;
            if (renderPriority != -1 && renderPriority <= this.stage.getEntities().size()) {
                this.stage.getEntities().add(renderPriority, this);
            } else {
                this.stage.getEntities().add(this);
            }
        }

        // On déplace l'entité aux coordonnées désirée
        this.location.setX(location.getX());
        this.location.setY(location.getY());
    }

    /**
     * Méthode permettant d'infliger des points de dégâts à l'entité.
     *
     * @param damagePoints Points de dégâts
     * @throws EntityDeadException Exception levée si l'entité meurt
     */
    public void damage(int damagePoints) throws EntityDeadException {
        if (damagePoints < 0) {
            return;
        }

        this.health -= damagePoints;
        if (this.health <= 0) {
            this.kill();
            throw new EntityDeadException(this, name + " est mort");
        }
    }

    /**
     * Méthode permettant de soigner une entité.
     *
     * @param health entier, point de vie supplémentaire
     */
    public void heal(int health) {
        if (health < 0) {
            return;
        }

        if (this.health + health > this.getMaxHealth()) {
            this.health = this.getMaxHealth();
        } else {
            this.health += health;
        }
    }

    /**
     * Méthode permettant de tuer l'entité.
     */
    public void kill() {
        // On enlève cette entité de l'étage
        if (this.stage != null) {
            this.stage.getEntities().remove(this);
        }
    }

    /**
     * Permet de déclencher un combat avec un joueur
     *
     * @param player Le joueur
     * @return Le combat terminé
     */
    public Fight fight(Player player) {
        Fight fight = new Fight(app, player, this);
        SimpleAudioPlayer gamePlayer = app.getCurrentGame().getAudioPlayer();
        if (gamePlayer != null) {
            gamePlayer.stop();
        }
        app.getConsole().show(fight);
        if (!player.isDead() && gamePlayer != null) {
            try {
                gamePlayer.restart();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ignored) {
            }
        }
        return fight;
    }

    /**
     * Méthode permettant de savoir si l'entité est morte.
     *
     * @return Vrai si l'entité est morte, faux sinon
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Méthode permettant de récupérer la position de l'entité.
     *
     * @return La position
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Méthode permettant de récupérer l'étage où se situe l'entité.
     *
     * @return L'étage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Méthode permettant de récupérer le nom de l'entité.
     *
     * @return Le nom
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode permettant de définir le nom de l'entité.
     *
     * @param name Le nom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode pour vérifier que l'entité possède une arme
     *
     * @return si elle a une arme
     */
    public boolean hasWeapon() {
        return this.weapon != null;
    }

    /**
     * Méthode qui permet de récupérer l'arme de l'entité.
     * <p>
     * Retourne null si l'entité ne possède pas d'arme, null est équivalent aux
     * mains nues
     *
     * @return l'arme
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Méthode qui permet de définir l'arme possédée par l'entité.
     * <p>
     * null est équivalent aux mains nues
     *
     * @param weapon la nouvelle arme
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean hasItem() {
        return this.getItem() != null;
    }

    /**
     * Méthode permettant de récupérer l'item que possède l'entité.
     *
     * @return un Item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Méthode qui permet de définir l'item porté par l'entité
     *
     * @param item un Item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Méthode permettant de récupérer la santé de l'entité.
     *
     * @return la santé
     */
    public int getHealth() {
        return health;
    }

    /**
     * Méthode permettant de récupérer la résistance de l'entité.
     *
     * @return la résistance
     */
    public int getResistance() {
        return resistance;
    }

    /**
     * Méthode permettant de récupérer la force.
     * return 0 si le résultat est inférieur à 0
     *
     * @return la force
     */
    public int getStrength() {
        int totalStrength = this.strength;
        if (this.hasWeapon()) {
            totalStrength += this.getWeapon().getPower();
        }
        if (this.item instanceof Wearable wearable){
            totalStrength += wearable.getStrength();
        }
        if (totalStrength < 0){
            return 0;
        }
        return totalStrength;
    }

    /**
     * Méthode permettant de récupérer la précision.
     * return 0 si le résultat est inférieur à 0
     *
     * @return la précision
     */
    public int getAccuracy() {
        int totalAccuracy = this.accuracy;
        if (this.hasWeapon()) {
            totalAccuracy += this.getWeapon().getAccuracy();
        }
        if (this.item instanceof Wearable wearable){
            totalAccuracy += wearable.getAccuracy();
        }
        if (totalAccuracy < 0 ){
            return 0;
        }
        return totalAccuracy;
    }

    /**
     * Méthode permettant de récupérer la rapidité.
     * return 0 si le résultat est inférieur à 0
     *
     * @return la rapidité
     */
    public int getSpeed() {
        int totalSpeed = this.speed;
        if (this.hasWeapon()) {
            totalSpeed += this.getWeapon().getSpeed();
        }
        if (this.item instanceof Wearable wearable){
            totalSpeed += wearable.getSpeed();
        }
        if (totalSpeed < 0){
            return 0;
        }
        return totalSpeed;
    }

    /**
     * Méthode permettant de récupérer la vie maximum de l'entité.
     *
     * @return la vie maximale
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Méthode permettant de récupérer la résistance maximale de l'entité.
     *
     * @return la résistance maximale
     */
    public int getMaxResistance() {
        return maxResistance;
    }

    /**
     * Méthode permettant de récupérer la force maximale de l'entité.
     *
     * @return la force maximale
     */
    public int getMaxStrength() {
        return maxStrength;
    }

    /**
     * Méthode permettant de récupérer la précision maximale de l'entité.
     *
     * @return la précision maximale
     */
    public int getMaxAccuracy() {
        return maxAccuracy;
    }

    /**
     * Méthode permettant de récupérer la rapidité maximale de l'entité.
     *
     * @return la rapidité maximale
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Méthode permettant à l'entité d'être affiché en premier sur la map (par-dessus les autres entités).
     *
     * @param renderPriority priorité de rendu
     */
    protected void setRenderPriority(int renderPriority) {
        this.renderPriority = renderPriority;
    }
}
