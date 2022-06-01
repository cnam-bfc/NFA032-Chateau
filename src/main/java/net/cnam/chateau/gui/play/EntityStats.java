package net.cnam.chateau.gui.play;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Orientation;

public class EntityStats extends CPanel {
    private Orientation renderOrientation;
    private final Entity entity;
    private final CPanel head;
    private final CLabel name;
    private final CProgressBar hpBar;
    private CPanel weapon;
    private CLabel weaponName;
    private CPanel item;
    private CLabel itemName;

    public EntityStats(Entity entity, Orientation orientation) {
        super(0, 0, Orientation.VERTICAL, false);

        this.renderOrientation = orientation;
        this.entity = entity;

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

        // Head (nom + barre de vie)
        this.head = new CPanel(0, 2, Orientation.VERTICAL, false);
        head.getComponents().add(name);
        head.getComponents().add(hpBar);
        this.getComponents().add(head);

        int objectMaxLength = 0;

        if (!(entity instanceof Pet)) {
            // Arme
            CLabel weaponLabel = new CLabel(HorizontalAlignment.RIGHT, "Arme");
            weaponLabel.getColors().add(CColor.BOLD);
            if (orientation == Orientation.HORIZONTAL) {
                weaponLabel.setLength(5);
            }

            // Nom de l'arme
            if (entity.hasWeapon()) {
                this.weaponName = new CLabel(HorizontalAlignment.LEFT, entity.getWeapon().getName());
            } else {
                this.weaponName = new CLabel(HorizontalAlignment.LEFT, "Poings");
            }
            this.weaponName.getColors().add(CColor.YELLOW);

            if (weaponName.getLength() > objectMaxLength) {
                objectMaxLength = weaponName.getLength();
            }

            // Panel de l'arme
            if (orientation == Orientation.VERTICAL) {
                this.weapon = new CPanel(0, 2, Orientation.VERTICAL, false);
            } else {
                this.weapon = new CPanel(HorizontalAlignment.CENTER, 0, 1, Orientation.HORIZONTAL, 1);
            }
            weapon.getComponents().add(weaponLabel);
            weapon.getComponents().add(weaponName);
            this.getComponents().add(weapon);
        } else {
            emptySpace++;
        }

        if (entity instanceof Player) {
            // Objet
            CLabel itemLabel = new CLabel(HorizontalAlignment.RIGHT, "Objet");
            itemLabel.getColors().add(CColor.BOLD);
            if (orientation == Orientation.HORIZONTAL) {
                itemLabel.setLength(5);
            }

            // Nom de l'objet
            if (entity.hasItem()) {
                this.itemName = new CLabel(HorizontalAlignment.LEFT, entity.getItem().getName());
            } else {
                this.itemName = new CLabel(HorizontalAlignment.LEFT, "Aucun");
            }
            itemName.getColors().add(CColor.GREEN);

            if (itemName.getLength() > objectMaxLength) {
                objectMaxLength = itemName.getLength();
            }

            // Panel de l'objet
            if (orientation == Orientation.VERTICAL) {
                this.item = new CPanel(0, 2, Orientation.VERTICAL, false);
            } else {
                this.item = new CPanel(HorizontalAlignment.CENTER, 0, 1, Orientation.HORIZONTAL, 1);
            }
            item.getComponents().add(itemLabel);
            item.getComponents().add(itemName);
            this.getComponents().add(item);
        } else {
            emptySpace++;
        }

        if (orientation == Orientation.HORIZONTAL) {
            if (weaponName != null && weaponName.getLength() < objectMaxLength) {
                weaponName.setLength(objectMaxLength);
            }

            if (itemName != null && itemName.getLength() < objectMaxLength) {
                itemName.setLength(objectMaxLength);
            }
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

        int objectMaxLength = 0;

        if (weaponName != null) {
            if (entity.hasWeapon()) {
                weaponName.setText(entity.getWeapon().getName());
            } else {
                weaponName.setText("Poings");
            }
            weaponName.setLength(weaponName.getText().length());
            if (weaponName.getLength() > objectMaxLength) {
                objectMaxLength = weaponName.getLength();
            }
        }

        if (itemName != null) {
            if (entity.hasItem()) {
                itemName.setText(entity.getItem().getName());
            } else {
                itemName.setText("Aucun");
            }
            itemName.setLength(itemName.getText().length());
            if (itemName.getLength() > objectMaxLength) {
                objectMaxLength = itemName.getLength();
            }
        }

        if (weaponName != null && renderOrientation == Orientation.HORIZONTAL && weaponName.getLength() < objectMaxLength) {
            weaponName.setLength(objectMaxLength);
        }

        if (itemName != null && renderOrientation == Orientation.HORIZONTAL && itemName.getLength() < objectMaxLength) {
            itemName.setLength(objectMaxLength);
        }

        if (weapon != null) {
            weapon.autoResize();
        }

        if (item != null) {
            item.autoResize();
        }
    }

    @Override
    public String[] render() {
        update();

        return super.render();
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);

        head.setLength(length);
        hpBar.setLength(length - 2);
        if (weapon != null) {
            weapon.autoResize();
        }
        if (item != null) {
            item.autoResize();
        }
    }

    @Override
    public Orientation getRenderingOrientation() {
        return renderOrientation;
    }

    @Override
    public void setRenderingOrientation(Orientation renderOrientation) {
        this.renderOrientation = renderOrientation;
    }
}
