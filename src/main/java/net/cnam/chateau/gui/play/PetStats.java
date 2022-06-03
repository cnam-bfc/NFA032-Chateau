package net.cnam.chateau.gui.play;

import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.CPanel;
import net.cnam.chateau.gui.component.CProgressBar;
import net.cnam.chateau.gui.component.HorizontalAlignment;
import net.cnam.chateau.utils.direction.Orientation;

public class PetStats extends CPanel {
    private final String title;
    private final Pet pet;

    public PetStats(Pet pet, int length) {
        this(pet, null, length);
    }

    public PetStats(Pet pet, String title, int length) {
        super(HorizontalAlignment.CENTER, length, 0, Orientation.VERTICAL, 1);

        this.title = title;
        this.pet = pet;

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
        CLabel nameValue = new CLabel(pet.getName());
        nameValue.getColors().add(CColor.GREEN);
        namePanel.getComponents().add(nameLabel);
        namePanel.getComponents().add(nameValue);
        namePanel.autoResize();
        if (namePanel.getLength() > panelMaxLength) {
            panelMaxLength = namePanel.getLength();
        }
        this.getComponents().add(namePanel);

        /*// Description
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
        this.getComponents().add(descriptionPanel);*/

        // Vie
        CPanel healthPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
        CLabel healthLabel = new CLabel("Santé");
        healthLabel.getColors().add(CColor.BOLD);
        CProgressBar healthValue = new CProgressBar(this.getLength() - 2, 1, pet.getHealth(), pet.getMaxHealth(), "%VALUE%/%MAX_VALUE% pv");
        healthValue.getProgressedColors().add(CColor.RED);
        healthValue.getUnProgressedColors().add(CColor.BRIGHT_BLACK);
        healthPanel.getComponents().add(healthLabel);
        healthPanel.getComponents().add(healthValue);
        healthPanel.autoResize();
        if (healthPanel.getLength() > panelMaxLength) {
            panelMaxLength = healthPanel.getLength();
        }
        this.getComponents().add(healthPanel);

        // Force
        CPanel strengthPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);
        CLabel strengthLabel = new CLabel("Force");
        strengthLabel.getColors().add(CColor.BOLD);
        CLabel strengthValue = new CLabel(String.valueOf(pet.getStrength()));
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
        CLabel accuracyValue = new CLabel(String.valueOf(pet.getAccuracy()));
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
        CLabel speedValue = new CLabel(String.valueOf(pet.getSpeed()));
        speedValue.getColors().add(CColor.GREEN);
        speedPanel.getComponents().add(speedLabel);
        speedPanel.getComponents().add(speedValue);
        speedPanel.autoResize();
        if (speedPanel.getLength() > panelMaxLength) {
            panelMaxLength = speedPanel.getLength();
        }
        this.getComponents().add(speedPanel);

        nameLabel.setLength(panelMaxLength);
        //descriptionPanel.setLength(panelMaxLength);
        healthLabel.setLength(panelMaxLength);
        strengthPanel.setLength(panelMaxLength);
        accuracyPanel.setLength(panelMaxLength);
        speedPanel.setLength(panelMaxLength);

        int length = this.getLength();
        this.autoResize();
        this.setLength(length);
    }
}
