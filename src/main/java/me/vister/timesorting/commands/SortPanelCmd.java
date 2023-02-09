package me.vister.timesorting.commands;

import me.vister.timesorting.TimeSorting;
import me.vister.timesorting.managers.PanelManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SortPanelCmd extends PanelManager implements CommandExecutor {

    public SortPanelCmd(final TimeSorting plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            openPanel(player);
            return true;
        } return false;
    }
}