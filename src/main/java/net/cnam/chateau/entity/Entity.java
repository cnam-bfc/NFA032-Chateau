package net.cnam.chateau.entity;

import net.cnam.chateau.DisplayableObject;
import net.cnam.chateau.gui.event.block.BlockListener;
import net.cnam.chateau.gui.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.structure.block.Block;
import net.cnam.chateau.structure.block.Stair;

/**
 * Classe d'une entité
 */
public abstract class Entity implements DisplayableObject {

    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_RESISTANCE = 50;
    private static final int DEFAULT_STRENGTH = 15;
    private static final int DEFAULT_ACCURACY = 15;
    private static final int DEFAULT_SPEED = 15;

    private final Location location;
    private Stage stage;
    private String name;
    private Weapon weapon;
    private int health = DEFAULT_HEALTH;
    private int resistance = DEFAULT_RESISTANCE;
    private int strength = DEFAULT_STRENGTH;
    private int accuracy = DEFAULT_ACCURACY;
    private int speed = DEFAULT_SPEED;

    private int renderPriority = -1;

    /**
     * Constructeur
     *
     * @param stage L'étage où elle se situe
     * @param location Coordonnées où elle se situe
     * @param name Le nom
     * @param health La santé
     * @param resistance La résistance
     * @param strength La force
     * @param accuracy La précision
     * @param speed La rapidité
     */
    public Entity(Stage stage, Location location, String name, int health, int resistance, int strength, int accuracy, int speed) {
        this(stage, location, name);

        this.health = health;
        this.resistance = resistance;
        this.strength = strength;
        this.accuracy = accuracy;
        this.speed = speed;
    }

    /**
     * Constructeur
     *
     * @param stage L'étage où se situe l'entité
     * @param location Coordonnées de l'entité
     * @param name Le nom de l'entité
     */
    public Entity(Stage stage, Location location, String name) {
        this.stage = stage;
        this.location = location;
        this.name = name;
    }

    /**
     * Méthode permettant de téléporter l'entité à des coordonées précises.
     *
     * @param location Les coordonnées de destination
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception levé si l'entité veut être téléporté hors de l'étage
     * @throws net.cnam.chateau.entity.EntityAlreadyTeleportedException
     * Exception levé si l'entité à déjà été téléporté
     */
    public void teleport(Location location) throws CoordinatesOutOfBoundsException, EntityAlreadyTeleportedException {
        teleport(stage, location);
    }

    /**
     * Méthode permettant de téléporter l'entité dans un étage à des coordonées
     * précises.
     *
     * @param stage L'étage de destination
     * @param location Les coordonnées de destination
     * @throws net.cnam.chateau.structure.CoordinatesOutOfBoundsException
     * Exception levé si l'entité veut être téléporté hors de l'étage
     * @throws net.cnam.chateau.entity.EntityAlreadyTeleportedException
     * Exception levé si l'entité à déjà été téléporté
     */
    public void teleport(Stage stage, Location location) throws CoordinatesOutOfBoundsException, EntityAlreadyTeleportedException {
        // On vérifie que les coordonnées de destination sont valides
        if (location.getX() < 0 || location.getY() < 0 || location.getX() >= stage.getLength() || location.getY() >= stage.getHeight()) {
            throw new CoordinatesOutOfBoundsException("L'entité ne peut pas sortir de l'étage!");
        }

        Stage saveEntityStage = this.stage;
        int saveEntityX = this.location.getX();
        int saveEntityY = this.location.getY();

        Block oldLocationBlock = this.stage.getBlock(this.location);
        Block newLocationBlock = stage.getBlock(location);
        // On notifie le nouveau block que l'entité rentre sur celui-ci
        // sauf si l'entité à changé d'étage et que le bloc est un escalier (sinon on boucle)
        if (!(this.stage != stage && newLocationBlock instanceof Stair)) {
            if (newLocationBlock != null && newLocationBlock instanceof BlockListener blockListener) {
                EntityEnterBlockEvent event = new EntityEnterBlockEvent(this);
                blockListener.onEntityEnterBlock(event);
                // Si le block refuse que l'entité rentre sur son territoire on ne déplace pas l'entité
                if (event.isCanceled()) {
                    return;
                }
            }
        }

        // On notifie l'ancien block où était l'entité que celle-ci est partie
        if (oldLocationBlock != null && oldLocationBlock instanceof BlockListener blockListener) {
            blockListener.onEntityLeaveBlock(new EntityLeaveBlockEvent(this));
        }

        // Si le joueur à été téléporté entre temps on annulé la suite
        if (saveEntityStage != this.stage || saveEntityX != this.location.getX() || saveEntityY != this.location.getY()) {
            throw new EntityAlreadyTeleportedException("L'entité à déjà été téléporté");
        }

        // Si l'entité est téléporté sur une autre étage
        if (this.stage != stage) {
            // On enlève l'enité de l'étage précédent
            this.stage.getEntities().remove(this);
            // On l'ajoute dans le nouvel étage
            this.stage = stage;
            if (renderPriority != -1 && renderPriority <= this.stage.getEntities().size()) {
                this.stage.getEntities().add(renderPriority, this);
            } else {
                this.stage.getEntities().add(this);
            }
        }

        // On déplace l'entité aux coordonnées désirés
        this.location.setX(location.getX());
        this.location.setY(location.getY());
    }

    /**
     * Méthode permettant d'infliger des points de dégâts à l'entité.
     *
     * @param damagePoints Points de dégâts
     */
    public void damage(int damagePoints) {
        if (damagePoints < 0) {
            return;
        }

        this.health -= damagePoints;
        if (this.health <= 0) {
            this.kill();
        }
    }

    /**
     * Méthode permettant de tuer l'entité.
     */
    public void kill() {
        // On enlève cette entité de l'étage
        this.stage.getEntities().remove(this);
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
        return (this.weapon != null);
    }

    /**
     * Méthode qui permet de récupérer l'arme de l'entité.
     *
     * Retourne null si l'entité ne possède pas d'arme, null est équivalent aux
     * mains nues
     *
     * @return l'arme
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Méthode qui permet de définir l'arme possédé par l'entité.
     *
     * null est équivalent aux mains nues
     *
     * @param weapon la nouvelle arme
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
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
     *
     * @return la force
     */
    public int getStrength() {
        int totalStrength = this.strength;
        if (this.hasWeapon()) {
            totalStrength += this.getWeapon().getPower();
        }
        return totalStrength;
    }

    /**
     * Méthode permettant de récupérer la précision.
     *
     * @return la précision
     */
    public int getAccuracy() {
        int totalAccuracy = this.accuracy;
        if (this.hasWeapon()) {
            totalAccuracy += this.getWeapon().getAccuracy();
        }
        return totalAccuracy;
    }

    /**
     * Méthode permettant de récupérer la rapidité.
     *
     * @return la rapidité
     */
    public int getSpeed() {
        int totalSpeed = this.speed;
        if (this.hasWeapon()) {
            totalSpeed += this.getWeapon().getSpeed();
        }
        return totalSpeed;
    }

    /**
     * Méthode permettant à l'entité d'être affiché en premier sur la map (par
     * dessus les autres entités).
     *
     * @param renderPriority priorité de rendu
     */
    protected void setRenderPriority(int renderPriority) {
        this.renderPriority = renderPriority;
    }
}
