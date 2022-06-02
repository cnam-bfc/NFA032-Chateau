package net.cnam.chateau.event.player;

import net.cnam.chateau.entity.Player;

public class PlayerInteractEvent {
    private final Player player;

    public PlayerInteractEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
