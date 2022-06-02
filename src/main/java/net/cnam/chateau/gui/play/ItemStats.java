package net.cnam.chateau.gui.play;

import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.CPanel;
import net.cnam.chateau.gui.component.HorizontalAlignment;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.consumable.AttackPotion;
import net.cnam.chateau.item.consumable.HealPotion;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.item.wearable.Wearable;
import net.cnam.chateau.utils.direction.Orientation;

public class ItemStats extends CPanel {
    private final String title;
    private final Item item;

    public ItemStats(Item item, int length) {
        this(item, null, length);
    }

    public ItemStats(Item item, String title, int length) {
        super(HorizontalAlignment.CENTER, length, 0, Orientation.VERTICAL, 1);

        this.title = title;
        this.item = item;

        update();
    }

    public void update() {
        this.getComponents().clear();

        int panelMaxLength = 0;

        // Titre
        if (title != null) {
            CLabel titleLabel = new CLabel(title);
            if (title.contains("(Vous)")) {
                titleLabel.getColors().add(CColor.BRIGHT_BLUE);
            }
            titleLabel.getColors().add(CColor.BOLD);
            this.getComponents().add(titleLabel);
            panelMaxLength = titleLabel.getLength();
        }

        // Nom
        CPanel namePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
        CLabel nameLabel = new CLabel("Nom");
        nameLabel.getColors().add(CColor.BOLD);
        CLabel nameValue = new CLabel(item.getName());
        nameValue.getColors().add(CColor.GREEN);
        namePanel.getComponents().add(nameLabel);
        namePanel.getComponents().add(nameValue);
        namePanel.autoResize();
        if (namePanel.getLength() > panelMaxLength) {
            panelMaxLength = namePanel.getLength();
        }
        this.getComponents().add(namePanel);

        // Description
        CPanel descriptionPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
        CLabel descriptionLabel = new CLabel("Description");
        descriptionLabel.getColors().add(CColor.BOLD);
        CLabel descriptionValue = new CLabel(item.getDescription(), this.getLength());
        descriptionValue.getColors().add(CColor.GREEN);
        descriptionPanel.getComponents().add(descriptionLabel);
        descriptionPanel.getComponents().add(descriptionValue);
        descriptionPanel.autoResize();
        if (descriptionPanel.getLength() > panelMaxLength) {
            panelMaxLength = descriptionPanel.getLength();
        }
        this.getComponents().add(descriptionPanel);

        if (item instanceof Weapon weapon) {
            // Force
            CPanel strengthPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel strengthLabel = new CLabel("Force");
            strengthLabel.getColors().add(CColor.BOLD);
            CLabel strengthValue = new CLabel(String.valueOf(weapon.getStrength()));
            strengthValue.getColors().add(CColor.GREEN);
            strengthPanel.getComponents().add(strengthLabel);
            strengthPanel.getComponents().add(strengthValue);
            strengthPanel.autoResize();
            if (strengthPanel.getLength() > panelMaxLength) {
                panelMaxLength = strengthPanel.getLength();
            }
            this.getComponents().add(strengthPanel);

            // Précision
            CPanel accuracyPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel accuracyLabel = new CLabel("Précision");
            accuracyLabel.getColors().add(CColor.BOLD);
            CLabel accuracyValue = new CLabel(String.valueOf(weapon.getAccuracy()));
            accuracyValue.getColors().add(CColor.GREEN);
            accuracyPanel.getComponents().add(accuracyLabel);
            accuracyPanel.getComponents().add(accuracyValue);
            accuracyPanel.autoResize();
            if (accuracyPanel.getLength() > panelMaxLength) {
                panelMaxLength = accuracyPanel.getLength();
            }
            this.getComponents().add(accuracyPanel);

            // Rapidité
            CPanel speedPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel speedLabel = new CLabel("Rapidité");
            speedLabel.getColors().add(CColor.BOLD);
            CLabel speedValue = new CLabel(String.valueOf(weapon.getSpeed()));
            speedValue.getColors().add(CColor.GREEN);
            speedPanel.getComponents().add(speedLabel);
            speedPanel.getComponents().add(speedValue);
            speedPanel.autoResize();
            if (speedPanel.getLength() > panelMaxLength) {
                panelMaxLength = speedPanel.getLength();
            }
            this.getComponents().add(speedPanel);

            strengthPanel.setLength(panelMaxLength);
            accuracyPanel.setLength(panelMaxLength);
            speedPanel.setLength(panelMaxLength);
        } else if (item instanceof Wearable wearable) {
            // Force
            CPanel strengthPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel strengthLabel = new CLabel("Force");
            strengthLabel.getColors().add(CColor.BOLD);
            CLabel strengthValue = new CLabel(String.valueOf(wearable.getStrength()));
            strengthValue.getColors().add(CColor.GREEN);
            strengthPanel.getComponents().add(strengthLabel);
            strengthPanel.getComponents().add(strengthValue);
            strengthPanel.autoResize();
            if (strengthPanel.getLength() > panelMaxLength) {
                panelMaxLength = strengthPanel.getLength();
            }
            this.getComponents().add(strengthPanel);

            // Précision
            CPanel accuracyPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel accuracyLabel = new CLabel("Précision");
            accuracyLabel.getColors().add(CColor.BOLD);
            CLabel accuracyValue = new CLabel(String.valueOf(wearable.getAccuracy()));
            accuracyValue.getColors().add(CColor.GREEN);
            accuracyPanel.getComponents().add(accuracyLabel);
            accuracyPanel.getComponents().add(accuracyValue);
            accuracyPanel.autoResize();
            if (accuracyPanel.getLength() > panelMaxLength) {
                panelMaxLength = accuracyPanel.getLength();
            }
            this.getComponents().add(accuracyPanel);

            // Rapidité
            CPanel speedPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel speedLabel = new CLabel("Rapidité");
            speedLabel.getColors().add(CColor.BOLD);
            CLabel speedValue = new CLabel(String.valueOf(wearable.getSpeed()));
            speedValue.getColors().add(CColor.GREEN);
            speedPanel.getComponents().add(speedLabel);
            speedPanel.getComponents().add(speedValue);
            speedPanel.autoResize();
            if (speedPanel.getLength() > panelMaxLength) {
                panelMaxLength = speedPanel.getLength();
            }
            this.getComponents().add(speedPanel);

            strengthPanel.setLength(panelMaxLength);
            accuracyPanel.setLength(panelMaxLength);
            speedPanel.setLength(panelMaxLength);
        } else if (item instanceof AttackPotion attackPotion) {
            // Dégâts
            CPanel damagePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel damageLabel = new CLabel("Dégâts");
            damageLabel.getColors().add(CColor.BOLD);
            CLabel damageValue = new CLabel(String.valueOf(attackPotion.getDamage()));
            damageValue.getColors().add(CColor.GREEN);
            damagePanel.getComponents().add(damageLabel);
            damagePanel.getComponents().add(damageValue);
            damagePanel.autoResize();
            if (damagePanel.getLength() > panelMaxLength) {
                panelMaxLength = damagePanel.getLength();
            }
            this.getComponents().add(damagePanel);

            damagePanel.setLength(panelMaxLength);
        } else if (item instanceof HealPotion healPotion) {
            // Soins
            CPanel healPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
            CLabel healLabel = new CLabel("Soins");
            healLabel.getColors().add(CColor.BOLD);
            CLabel healValue = new CLabel(String.valueOf(healPotion.getHeal()));
            healValue.getColors().add(CColor.GREEN);
            healPanel.getComponents().add(healLabel);
            healPanel.getComponents().add(healValue);
            healPanel.autoResize();
            if (healPanel.getLength() > panelMaxLength) {
                panelMaxLength = healPanel.getLength();
            }
            this.getComponents().add(healPanel);

            healPanel.setLength(panelMaxLength);
        }

        nameLabel.setLength(panelMaxLength);
        descriptionLabel.setLength(panelMaxLength);

        int length = this.getLength();
        this.autoResize();
        this.setLength(length);
    }
}
