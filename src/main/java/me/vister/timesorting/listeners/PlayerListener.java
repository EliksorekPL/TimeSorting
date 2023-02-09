package me.vister.timesorting.listeners;

import me.vister.timesorting.TimeSorting;
import me.vister.timesorting.commands.SortPanelCmd;
import me.vister.timesorting.managers.PanelManager;
import me.vister.timesorting.utils.TimeSort;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener extends PanelManager implements Listener {

    private boolean addNumberActivated, removeNumberActivated;

    public PlayerListener(final TimeSorting plugin) {
        super(plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("§c§lTime§e§lSorting §b§lPanel") && e.getCurrentItem() != null) {
            e.setCancelled(true);
            if (e.getRawSlot() == 7) {
                if (e.isLeftClick())
                    addNumberActivated = true;
                else
                    removeNumberActivated = true;
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage("§7Enter number: ");
            } else if (numbersToSort.getNumbers().size() > 7) {
                switch (e.getRawSlot()) {
                    case 0 -> SortPanelCmd.sort.bubbleSort();
                    case 1 -> SortPanelCmd.sort.selectionSort();
                    case 2 -> SortPanelCmd.sort.insertionSort();
                    case 3 -> SortPanelCmd.sort.binarySort();
                } openPanel((Player) e.getWhoClicked());
            } else e.getWhoClicked().sendMessage("§cMinimum amount of numbers to sort is 8!");
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (addNumberActivated || removeNumberActivated) {
            e.setCancelled(true);
            int value;
            try {
                value = Integer.parseInt(e.getMessage());
            } catch (NumberFormatException ex) {
                e.getPlayer().sendMessage("§cArgument must be a number!");
                return;
            }
            if (addNumberActivated)
                e.getPlayer().sendMessage(numbersToSort.addNumber(value) ? "§aCorrectly added a new number!" : "§cThis number is already in the list!");
            else
                e.getPlayer().sendMessage(numbersToSort.removeNumber(value) ? "§aCorrectly removed a number!" : "§cThis number is not in the list!");
            sort = new TimeSort(numbersToSort);
            addNumberActivated = false;
            removeNumberActivated = false;
            Bukkit.getScheduler().runTaskLater(plugin, () -> openPanel(e.getPlayer()), 10); //1 second = 20 ticks
        }
    }
}
