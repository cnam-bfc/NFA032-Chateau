package net.cnam.chateau.gui.play;

import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.CPanel;
import net.cnam.chateau.gui.component.HorizontalAlignment;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.consumable.AttackPotion;
import net.cnam.chateau.item.consumable.HealPotion;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.utils.direction.Orientation;

public class ItemStats extends CPanel {
    private final Item item;

    public ItemStats(Item item) {
        super(HorizontalAlignment.CENTER, Orientation.VERTICAL, 1);

        this.item = item;

        update();
    }

    private void update() {
        this.getComponents().clear();

        // Nom
        CPanel namePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
        CLabel nameLabel = new CLabel("Nom");
        CLabel nameValue = new CLabel(item.getName());
        namePanel.getComponents().add(nameLabel);
        namePanel.getComponents().add(nameValue);
        namePanel.autoResize();
        this.getComponents().add(namePanel);

        // Description
        CPanel descriptionPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
        CLabel descriptionLabel = new CLabel("Description");
        CLabel descriptionValue = new CLabel(item.getDescription(), this.getLength());
        descriptionPanel.getComponents().add(descriptionLabel);
        descriptionPanel.getComponents().add(descriptionValue);
        descriptionPanel.autoResize();
        this.getComponents().add(descriptionPanel);

        if (item instanceof Weapon weapon) {
            // Force
            CPanel strengthPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel strengthLabel = new CLabel("Force");
            CLabel strengthValue = new CLabel(String.valueOf(weapon.getStrength()));
            strengthPanel.getComponents().add(strengthLabel);
            strengthPanel.getComponents().add(strengthValue);
            strengthPanel.autoResize();
            this.getComponents().add(strengthPanel);

            // Précision
            CPanel accuracyPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel accuracyLabel = new CLabel("Précision");
            CLabel accuracyValue = new CLabel(String.valueOf(weapon.getAccuracy()));
            accuracyPanel.getComponents().add(accuracyLabel);
            accuracyPanel.getComponents().add(accuracyValue);
            accuracyPanel.autoResize();
            this.getComponents().add(accuracyPanel);

            // Rapidité
            CPanel speedPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel speedLabel = new CLabel("Rapidité");
            CLabel speedValue = new CLabel(String.valueOf(weapon.getSpeed()));
            speedPanel.getComponents().add(speedLabel);
            speedPanel.getComponents().add(speedValue);
            speedPanel.autoResize();
            this.getComponents().add(speedPanel);
        } else if (item instanceof AttackPotion attackPotion) {
            // Dégâts
            CPanel damagePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel damageLabel = new CLabel("Dégâts");
            CLabel damageValue = new CLabel(String.valueOf(attackPotion.getDamage()));
            damagePanel.getComponents().add(damageLabel);
            damagePanel.getComponents().add(damageValue);
            damagePanel.autoResize();
            this.getComponents().add(damagePanel);
        } else if (item instanceof HealPotion healPotion) {
            // Soins
            CPanel healPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel healLabel = new CLabel("Soins");
            CLabel healValue = new CLabel(String.valueOf(healPotion.getHeal()));
            healPanel.getComponents().add(healLabel);
            healPanel.getComponents().add(healValue);
            healPanel.autoResize();
            this.getComponents().add(healPanel);
        }

        this.autoResize();
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);

        this.update();

        super.setLength(length);
    }
}
