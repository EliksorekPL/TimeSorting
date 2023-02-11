package me.vister.timesorting;

import me.vister.timesorting.commands.SortPanelCmd;
import me.vister.timesorting.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TimeSorting extends JavaPlugin {

    @Override
    public void onEnable() {
        TimeSorting plugin = this;

        getCommand("sortpanel").setExecutor(new SortPanelCmd(plugin));
        getServer().getPluginManager().registerEvents(new PlayerListener(plugin), plugin);
    }
}