package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.CComponent;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.CPanel;
import net.cnam.chateau.gui.component.CProgressBar;
import net.cnam.chateau.utils.direction.Orientation;

public class EntityStats extends CPanel {
    private final Entity entity;
    private final boolean autoUpdate;
    private final CPanel head;
    private final CLabel name;
    private final CProgressBar hpBar;
    private CPanel weapon;
    private CLabel weaponName;
    private CPanel item;
    private CLabel itemName;

    public EntityStats(Entity entity, Orientation orientation) {
        this(entity, orientation, true);
    }

    public EntityStats(Entity entity, Orientation orientation, boolean autoUpdate) {
        super(0, 0, Orientation.VERTICAL, false);

        this.entity = entity;
        this.autoUpdate = autoUpdate;

        int emptySpace = 0;

        // Nom de l'entité
        if (entity instanceof Player) {
            this.name = new CLabel(entity.getName() + " (Vous)");
            this.name.getColors().add(CColor.BRIGHT_BLUE);
        } else {
            this.name = new CLabel(entity.getName());
        }
        if (entity instanceof Pet) {
            this.name.getColors().add(CColor.CYAN);
        }
        this.name.getColors().add(CColor.BOLD);

        // Barre de vie
        this.hpBar = new CProgressBar(0, 1, entity.getHealth(), entity.getMaxHealth(), "%VALUE%/%MAX_VALUE% pv");
        hpBar.getProgressedColors().add(CColor.RED);
        hpBar.getUnprogressedColors().add(CColor.BRIGHT_BLACK);

        // Head
        this.head = new CPanel(0, 2, Orientation.VERTICAL, false);
        head.getComponents().add(name);
        head.getComponents().add(hpBar);
        this.getComponents().add(head);

        if (!(entity instanceof Pet)) {
            // Arme
            CLabel weaponLabel = new CLabel("Arme");
            weaponLabel.getColors().add(CColor.BOLD);

            // Nom de l'arme
            if (entity.hasWeapon()) {
                this.weaponName = new CLabel(entity.getWeapon().getName());
            } else {
                this.weaponName = new CLabel("Poings");
            }
            this.weaponName.getColors().add(CColor.YELLOW);

            // Panel de l'arme
            if (orientation == Orientation.VERTICAL) {
                this.weapon = new CPanel(0, 2, Orientation.VERTICAL, false);
            } else {
                this.weapon = new CPanel(0, 1, Orientation.HORIZONTAL, true);
            }
            weapon.getComponents().add(weaponLabel);
            weapon.getComponents().add(weaponName);
            this.getComponents().add(weapon);
        } else {
            emptySpace++;
        }

        if (entity instanceof Player) {
            // Objet
            CLabel itemLabel = new CLabel("Objet");
            itemLabel.getColors().add(CColor.BOLD);

            // Nom de l'objet
            if (entity.hasItem()) {
                this.itemName = new CLabel(entity.getItem().getName());
            } else {
                this.itemName = new CLabel("Aucun");
            }
            itemName.getColors().add(CColor.GREEN);

            // Panel de l'objet
            if (orientation == Orientation.VERTICAL) {
                this.item = new CPanel(0, 2, Orientation.VERTICAL, false);
            } else {
                this.item = new CPanel(0, 1, Orientation.HORIZONTAL, true);
            }
            item.getComponents().add(itemLabel);
            item.getComponents().add(itemName);
            this.getComponents().add(item);
        } else {
            emptySpace++;
        }

        for (int i = 0; i < emptySpace; i++) {
            if (orientation == Orientation.VERTICAL) {
                this.getComponents().add(new CLabel(" \n "));
            }
        }

        int height = -1;
        for (CComponent component : this.getComponents()) {
            height += component.getHeight();
            height++;
        }
        this.setHeight(height);
    }

    public void update() {
        if (!(entity instanceof Player)) {
            name.setText(entity.getName());
            name.setLength(name.getText().length());
        }

        hpBar.setValue(entity.getHealth());
        hpBar.setMaxValue(entity.getMaxHealth());

        if (weaponName != null) {
            if (entity.hasWeapon()) {
                weaponName.setText(entity.getWeapon().getName());
            } else {
                weaponName.setText("Poings");
            }
            weaponName.setLength(weaponName.getText().length());
        }

        if (itemName != null) {
            if (entity.hasItem()) {
                itemName.setText(entity.getItem().getName());
            } else {
                itemName.setText("Aucun");
            }
            itemName.setLength(itemName.getText().length());
        }
    }

    @Override
    public String[] render() {
        if (autoUpdate) {
            update();
        }

        return super.render();
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);

        head.setLength(length);
        hpBar.setLength(length - 2);
        if (weapon != null) {
            weapon.setLength(length);
        }
        if (item != null) {
            item.setLength(length);
        }
    }

    public CProgressBar getHpBar() {
        return hpBar;
    }
}
