package net.cnam.chateau.gui.play.fight.loot;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.gui.play.ItemStats;
import net.cnam.chateau.item.consumable.Consumable;
import net.cnam.chateau.utils.direction.Orientation;

import java.util.LinkedList;
import java.util.List;

public class LootMenu extends CFrame implements DisplayableComponent {
    private final Player player;
    private final Entity entity;
    private final CPanel leftPanel;
    private final CPanel centerPanel;
    private final CPanel rightPanel;
    private final CChoices buttons;
    private final LeaveButton leaveButton;

    private boolean display = true;

    public LootMenu(App app, Player player, Entity enemy) {
        super(0, 0, "Butin de " + enemy.getName());

        this.player = player;
        this.entity = enemy;

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
        // Bouton weapon
        if (enemy.hasWeapon()) {
            if (player.hasWeapon()) {
                buttons.add(new ReplaceWeaponButton(app, player, enemy));
            } else {
                buttons.add(new TakeWeaponButton(app, player, enemy, this));
            }
        }
        // Bouton item
        if (enemy.hasItem()) {
            if (player.hasItem()) {
                buttons.add(new ReplaceItemButton(app, this, player, enemy));
            } else {
                buttons.add(new TakeItemButton(app, player, enemy, this));
            }
        }
        // Bouton utiliser item
        if (player.hasItem() && player.getItem() instanceof Consumable consumable) {
            List<Entity> entities = new LinkedList<>();
            entities.add(player);
            if (player.hasPet()) {
                entities.add(player.getPet());
            }
            buttons.add(new UseItemButton(app, this, consumable, entities));
        }
        // Bouton quitter
        this.leaveButton = new LeaveButton(app, this);
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
        if (!(selectableComponent instanceof ReplaceWeaponButton
                || selectableComponent instanceof TakeWeaponButton
                || selectableComponent instanceof ReplaceItemButton
                || selectableComponent instanceof TakeItemButton)) {
            return;
        }

        // On ajoute les bonnes stats en fonction du bouton sélectionné
        CComponent selectedComponent = (CComponent) selectableComponent;
        // 24 = 20 (boutons) + 2 (Cadre frame) + 2 (Séparateur entre menu stats)
        int statsLength = this.getContentPane().getLength() - this.buttons.getLength() - 2;
        if (selectedComponent instanceof ReplaceWeaponButton replaceWeaponButton) {
            Player player = replaceWeaponButton.getPlayer();
            ItemStats playerWeaponStats = new ItemStats(player.getWeapon(), player.getName() + " (Vous)", statsLength / 2);
            this.leftPanel.getComponents().add(playerWeaponStats);

            Entity enemy = replaceWeaponButton.getEnemy();
            ItemStats enemyWeaponStats = new ItemStats(enemy.getWeapon(), enemy.getName(), statsLength / 2);
            this.rightPanel.getComponents().add(enemyWeaponStats);
        } else if (selectedComponent instanceof TakeWeaponButton takeWeaponButton) {
            Entity enemy = takeWeaponButton.getEnemy();
            ItemStats enemyWeaponStats = new ItemStats(enemy.getWeapon(), enemy.getName(), statsLength / 2);
            this.rightPanel.getComponents().add(enemyWeaponStats);
        } else if (selectedComponent instanceof ReplaceItemButton replaceItemButton) {
            Player player = replaceItemButton.getPlayer();
            ItemStats playerItemStats = new ItemStats(player.getItem(), player.getName() + " (Vous)", statsLength / 2);
            this.leftPanel.getComponents().add(playerItemStats);

            Entity enemy = replaceItemButton.getEnemy();
            ItemStats enemyItemStats = new ItemStats(enemy.getItem(), enemy.getName(), statsLength / 2);
            this.rightPanel.getComponents().add(enemyItemStats);
        } else if (selectedComponent instanceof TakeItemButton takeItemButton) {
            Entity enemy = takeItemButton.getEnemy();
            ItemStats enemyItemStats = new ItemStats(enemy.getItem(), enemy.getName(), statsLength / 2);
            this.rightPanel.getComponents().add(enemyItemStats);
        }
    }

    public void stopDisplaying() {
        display = false;
    }

    public CChoices getButtons() {
        return buttons;
    }

    public LeaveButton getLeaveButton() {
        return leaveButton;
    }

    public Player getPlayer() {
        return player;
    }

    public Entity getEntity() {
        return entity;
    }
}
