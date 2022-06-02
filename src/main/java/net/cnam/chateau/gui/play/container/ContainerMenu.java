package net.cnam.chateau.gui.play.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.gui.play.ItemStats;
import net.cnam.chateau.item.Key;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.structure.block.container.Container;
import net.cnam.chateau.utils.direction.Orientation;

public class ContainerMenu extends CFrame implements DisplayableComponent {
    private final CPanel leftPanel;
    private final CPanel centerPanel;
    private final CPanel rightPanel;
    private final CChoices buttons;
    private final LeaveContainerButton leaveButton;

    private boolean display = true;

    public ContainerMenu(App app, Player player, Container block) {
        super(0, 0, "Contenu de " + block.getName());

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

        // Bouton échanger objet
        if (player.hasItem() && block.hasItem() && !(block.getHiddenItem() instanceof Weapon || block.getHiddenItem() instanceof Key)) {
            buttons.add(new ReplaceItemButton(app, player, block));
        }

        // Bouton échanger arme
        if (player.hasWeapon() && block.hasItem() && (block.getHiddenItem() instanceof Weapon)) {
            buttons.add(new ReplaceWeaponButton(app, player, block));
        }

        // Bouton ranger objet
        if (player.hasItem() && !block.hasItem()) {
            buttons.add(new PutItemButton(app, this, player, block));
        }

        // Bouton ranger arme
        if (player.hasWeapon() && !block.hasItem()) {
            buttons.add(new PutWeaponButton(app, this, player, block));
        }

        // Bouton prendre objet
        if (!player.hasItem() && block.hasItem() && !(block.getHiddenItem() instanceof Weapon || block.getHiddenItem() instanceof Key)) {
            buttons.add(new TakeItemButton(app, this, player, block));
        }

        // Bouton prendre arme
        if (!player.hasWeapon() && block.hasItem() && (block.getHiddenItem() instanceof Weapon)) {
            buttons.add(new TakeWeaponButton(app, this, player, block));
        }

        // Bouton prendre clé
        if (block.getHiddenItem() instanceof Key) {
            buttons.add(new TakeKeyButton(app, this, player, block));
        }

        // Bouton quitter
        this.leaveButton = new LeaveContainerButton(app, this);
        buttons.add(leaveButton);

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
            if (component instanceof ItemStats itemStats) {
                itemStats.update();
            }
        }
        for (CComponent component : this.rightPanel.getComponents()) {
            component.setLength(statsLength / 2);
            if (component instanceof ItemStats itemStats) {
                itemStats.update();
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
        // Si on a sélectionné un bouton de prise ou remplacement d'item
        if (!(selectableComponent instanceof PutItemButton
                || selectableComponent instanceof PutWeaponButton
                || selectableComponent instanceof ReplaceItemButton
                || selectableComponent instanceof ReplaceWeaponButton
                || selectableComponent instanceof TakeItemButton
                || selectableComponent instanceof TakeWeaponButton
                || selectableComponent instanceof TakeKeyButton)) {
            return;
        }

        // On ajoute les bonnes stats en fonction du bouton sélectionné
        CComponent selectedComponent = (CComponent) selectableComponent;
        // 24 = 20 (boutons) + 2 (Cadre frame) + 2 (Séparateur entre menu stats)
        int statsLength = this.getContentPane().getLength() - this.buttons.getLength() - 2;
        if (selectedComponent instanceof PutItemButton putItemButton) {
            Player player = putItemButton.getPlayer();
            ItemStats playerItemStats = new ItemStats(player.getItem(), player.getName() + " (Vous)", statsLength / 2);
            this.leftPanel.getComponents().add(playerItemStats);
        } else if (selectedComponent instanceof PutWeaponButton putWeaponButton) {
            Player player = putWeaponButton.getPlayer();
            ItemStats playerWeaponStats = new ItemStats(player.getWeapon(), player.getName() + " (Vous)", statsLength / 2);
            this.leftPanel.getComponents().add(playerWeaponStats);
        } else if (selectedComponent instanceof ReplaceItemButton replaceItemButton) {
            Player player = replaceItemButton.getPlayer();
            ItemStats playerItemStats = new ItemStats(player.getItem(), player.getName() + " (Vous)", statsLength / 2);
            this.leftPanel.getComponents().add(playerItemStats);

            Container container = replaceItemButton.getBlock();
            ItemStats containerItemStats = new ItemStats(container.getHiddenItem(), container.getName(), statsLength / 2);
            this.rightPanel.getComponents().add(containerItemStats);
        } else if (selectedComponent instanceof ReplaceWeaponButton replaceWeaponButton) {
            Player player = replaceWeaponButton.getPlayer();
            ItemStats playerWeaponStats = new ItemStats(player.getWeapon(), player.getName() + " (Vous)", statsLength / 2);
            this.leftPanel.getComponents().add(playerWeaponStats);

            Container container = replaceWeaponButton.getBlock();
            ItemStats containerWeaponStats = new ItemStats(container.getHiddenItem(), container.getName(), statsLength / 2);
            this.rightPanel.getComponents().add(containerWeaponStats);
        } else if (selectedComponent instanceof TakeItemButton takeItemButton) {
            Container container = takeItemButton.getBlock();
            ItemStats containerItemStats = new ItemStats(container.getHiddenItem(), container.getName(), statsLength / 2);
            this.rightPanel.getComponents().add(containerItemStats);
        } else if (selectedComponent instanceof TakeWeaponButton takeWeaponButton) {
            Container container = takeWeaponButton.getBlock();
            ItemStats containerWeaponStats = new ItemStats(container.getHiddenItem(), container.getName(), statsLength / 2);
            this.rightPanel.getComponents().add(containerWeaponStats);
        } else if (selectedComponent instanceof TakeKeyButton takeKeyButton) {
            Container container = takeKeyButton.getBlock();
            ItemStats containerKeyStats = new ItemStats(container.getHiddenItem(), container.getName(), statsLength / 2);
            this.rightPanel.getComponents().add(containerKeyStats);
        }
    }

    public void stopDisplaying() {
        this.display = false;
    }

    public CChoices getButtons() {
        return buttons;
    }

    public LeaveContainerButton getLeaveButton() {
        return leaveButton;
    }
}
