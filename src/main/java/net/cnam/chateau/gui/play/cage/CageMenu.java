package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.gui.play.PetStats;
import net.cnam.chateau.structure.block.Cage;
import net.cnam.chateau.utils.direction.Orientation;

public class CageMenu extends CFrame implements DisplayableComponent {
    private final CPanel leftPanel;
    private final CPanel centerPanel;
    private final CPanel rightPanel;
    private final CChoices buttons;

    private boolean display = true;

    public CageMenu(App app, Player player, Cage cage) {
        super(0, 0, "Cage");

        // Contenu de la fenêtre
        this.getContentPane().setRenderingOrientation(Orientation.HORIZONTAL);
        this.getContentPane().setRenderingMainPadding(false);

        // Panneaux de gauche à droite
        this.leftPanel = new CPanel(0, 0);
        this.centerPanel = new CPanel(0, 0);
        this.rightPanel = new CPanel(0, 0);
        this.getContentPane().getComponents().add(this.leftPanel);
        this.getContentPane().getComponents().add(this.centerPanel);
        this.getContentPane().getComponents().add(this.rightPanel);

        // Panneau du centre
        // Boutons
        this.buttons = new CChoices(app, 1);

        // Bouton remplacer pet
        if (player.hasPet() && cage.hasPet()) {
            buttons.add(new ReplacePetButton(app, player, cage));
        }

        // Bouton ranger pet
        if (player.hasPet() && !cage.hasPet()) {
            buttons.add(new PutPetButton(app, this, player, cage));
        }

        // Bouton prendre pet
        if (!player.hasPet() && cage.hasPet()) {
            buttons.add(new TakePetButton(app, this, player, cage));
        }

        // Bouton quitter
        buttons.add(new LeaveCageButton(app, this));

        centerPanel.getComponents().add(buttons);
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    @Override
    public String[] render() {
        update();

        return super.render();
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);
        this.centerPanel.setLength(20);
        int statsLength = this.getContentPane().getLength() - this.centerPanel.getLength() - 2;
        this.leftPanel.setLength(statsLength / 2);
        this.rightPanel.setLength(statsLength / 2);
        for (CComponent component : this.leftPanel.getComponents()) {
            component.setLength(statsLength / 2);
            if (component instanceof PetStats petStats) {
                petStats.update();
            }
        }
        for (CComponent component : this.rightPanel.getComponents()) {
            component.setLength(statsLength / 2);
            if (component instanceof PetStats petStats) {
                petStats.update();
            }
        }
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        this.leftPanel.setHeight(this.getContentPane().getHeight());
        this.centerPanel.setHeight(this.getContentPane().getHeight());
        this.rightPanel.setHeight(this.getContentPane().getHeight());
    }

    @SuppressWarnings("ConstantConditions")
    private void update() {
        // On retire tous les composants des panneaux de gauche et de droite
        this.leftPanel.getComponents().clear();
        this.rightPanel.getComponents().clear();

        SelectableComponent selectableComponent = buttons.getSelectedComponent();
        // Si on a sélectionné un bouton de prise ou remplacement de pet
        if (!(selectableComponent instanceof PutPetButton
                || selectableComponent instanceof ReplacePetButton
                || selectableComponent instanceof TakePetButton)) {
            return;
        }

        // On ajoute les bonnes stats en fonction du bouton sélectionné
        CComponent selectedComponent = (CComponent) selectableComponent;
        // 24 = 20 (boutons) + 2 (Cadre frame) + 2 (Séparateur entre menu stats)
        int statsLength = this.getContentPane().getLength() - this.buttons.getLength() - 2;
        if (selectedComponent instanceof PutPetButton putPetButton) {
            Player player = putPetButton.getPlayer();
            PetStats playerPetStats = new PetStats(player.getPet(), player.getName() + " (Vous)", statsLength);
            this.leftPanel.getComponents().add(playerPetStats);
        } else if (selectedComponent instanceof ReplacePetButton replacePetButton) {
            Player player = replacePetButton.getPlayer();
            PetStats playerPetStats = new PetStats(player.getPet(), player.getName() + " (Vous)", statsLength);
            this.leftPanel.getComponents().add(playerPetStats);

            Cage cage = replacePetButton.getCage();
            PetStats cagePetStats = new PetStats(cage.getPet(), "Cage", statsLength);
            this.rightPanel.getComponents().add(cagePetStats);
        } else if (selectedComponent instanceof TakePetButton takePetButton) {
            Cage cage = takePetButton.getCage();
            PetStats catPetStats = new PetStats(cage.getPet(), "Cage", statsLength);
            this.rightPanel.getComponents().add(catPetStats);
        }
    }

    public void stopDisplay() {
        this.display = false;
    }

    public CChoices getButtons() {
        return buttons;
    }
}
