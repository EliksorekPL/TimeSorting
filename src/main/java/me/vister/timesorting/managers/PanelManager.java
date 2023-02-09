package me.vister.timesorting.managers;

import me.vister.timesorting.TimeSorting;
import me.vister.timesorting.utils.NumbersToSort;
import me.vister.timesorting.utils.SortType;
import me.vister.timesorting.utils.TimeSort;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PanelManager {
    protected TimeSorting plugin;
    protected static TimeSort sort;
    protected NumbersToSort numbersToSort;

    public PanelManager(final TimeSorting plugin) {
        this.plugin = plugin;
        numbersToSort = new NumbersToSort();
        if (sort == null) sort = new TimeSort(numbersToSort);
    }

    public void openPanel(Player player) {
        Inventory sortPanel = Bukkit.createInventory(player, 9,  "§c§lTime§e§lSorting §b§lPanel");

        sortPanel.setItem(0, createItem(Material.OAK_WOOD, "§eBubble sort", new ArrayList<>(Arrays.asList("§bSort time: §7" + sort.getSortTime(SortType.BUBBLE_SORT), "", "§aClick LMB to enter"))));
        sortPanel.setItem(1, createItem(Material.SPRUCE_WOOD, "§eSelection sort", new ArrayList<>(Arrays.asList("§bSort time: §7" + sort.getSortTime(SortType.SELECTION_SORT), "", "§aClick LMB to enter"))));
        sortPanel.setItem(2, createItem(Material.BIRCH_WOOD, "§eInsertion sort", new ArrayList<>(Arrays.asList("§bSort time: §7" + sort.getSortTime(SortType.INSERTION_SORT), "", "§aClick LMB to enter"))));
        sortPanel.setItem(3, createItem(Material.JUNGLE_WOOD, "§eBinary sort", new ArrayList<>(Arrays.asList("§bSort time: §7" + sort.getSortTime(SortType.BINARY_SORT), "", "§aClick LMB to enter"))));
        sortPanel.setItem(7, createItem(Material.SUNFLOWER, "§aNumbers for sort", new ArrayList<>(Arrays.asList(numbersToSort.getNumbers().toString(), "", "§aClick LMB to add a new number", "§cClick RMB to remove a number"))));
        sortPanel.setItem(8, createItem(Material.NETHER_STAR, "§aBest sort type", new ArrayList<>(Arrays.asList("§bType: §7" + sort.getLowestSortTimeMethod(), "§bTime: §7" + sort.getLowestSortTime()))));

        player.openInventory(sortPanel);
    }

    private ItemStack createItem(Material material, String displayName, List<String> lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}