package net.cnam.chateau.gui.play;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Orientation;

public class EntityStats extends CPanel {
    private final Entity entity;
    private final CLabel name;
    private final CProgressBar hpBar;
    private final CPanel weapon;
    private final CLabel weaponName;
    private CPanel pet;
    private CLabel petName;

    public EntityStats(Entity entity) {
        super(HorizontalAlignment.LEFT, 0, 0, Orientation.VERTICAL, false);

        this.entity = entity;

        this.name = new CLabel(HorizontalAlignment.LEFT, entity.getName());
        this.getComponents().add(name);

        this.hpBar = new CProgressBar(0, 1, entity.getHealth(), entity.getMaxHealth(), "%VALUE%/%MAX_VALUE% hp");
        hpBar.getColors().add(CColor.RED);
        this.getComponents().add(hpBar);

        CLabel weaponLabel = new CLabel(HorizontalAlignment.LEFT, "Arme: ");
        if (entity.hasWeapon()) {
            this.weaponName = new CLabel(HorizontalAlignment.LEFT, entity.getWeapon().getName());
        } else {
            this.weaponName = new CLabel(HorizontalAlignment.LEFT, "Poings");
        }
        this.weaponName.getColors().add(CColor.YELLOW);
        this.weapon = new CPanel(HorizontalAlignment.LEFT, 0, 1, Orientation.HORIZONTAL, true);
        this.weapon.getComponents().add(weaponLabel);
        this.weapon.getComponents().add(weaponName);
        this.getComponents().add(weapon);

        int fill = 0;

        if (entity instanceof Player player) {
            CLabel petLabel = new CLabel(HorizontalAlignment.LEFT, "Familier: ");
            if (player.hasPet()) {
                this.petName = new CLabel(HorizontalAlignment.LEFT, player.getPet().getName());
            } else {
                this.petName = new CLabel(HorizontalAlignment.LEFT, "Aucun");
            }
            this.petName.getColors().add(CColor.GREEN);
            this.pet = new CPanel(HorizontalAlignment.LEFT, 0, 1, Orientation.HORIZONTAL, true);
            this.pet.getComponents().add(petLabel);
            this.pet.getComponents().add(petName);
            this.getComponents().add(pet);
        } else {
            fill++;
        }

        for (int i = 0; i < fill; i++) {
            this.getComponents().add(new CLabel(HorizontalAlignment.LEFT, " "));
        }

        int height = -1;
        for (CComponent component : this.getComponents()) {
            height += component.getHeight();
            height++;
        }
        this.setHeight(height);
    }

    @Override
    public String[] render() {
        name.setText(entity.getName());
        name.setLength(entity.getName().length());

        hpBar.setValue(entity.getHealth());
        hpBar.setMaxValue(entity.getMaxHealth());

        if (entity.hasWeapon()) {
            weaponName.setText(entity.getWeapon().getName());
            weaponName.setLength(entity.getWeapon().getName().length());
        }

        if (petName != null && entity instanceof Player player && player.hasPet()) {
            petName.setText(player.getPet().getName());
            petName.setLength(player.getPet().getName().length());
        }

        return super.render();
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);

        name.setLength(length);
        hpBar.setLength(length);
        weapon.setLength(length);
        if (pet != null) {
            pet.setLength(length);
        }
    }
}
