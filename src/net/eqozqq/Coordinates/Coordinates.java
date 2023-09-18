package net.eqozqq.Coordinates;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.Player;

public class Coordinates extends PluginBase {

    @Override
    public void onEnable() {
        getLogger().info("Coordinates enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Coordinates disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("xyz") || command.getName().equalsIgnoreCase("coords") || command.getName().equalsIgnoreCase("coordinates")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    double x = player.getX();
                    double y = player.getY();
                    double z = player.getZ();

                    int roundedX = (int) Math.round(x);
                    int roundedY = (int) Math.round(y);
                    int roundedZ = (int) Math.round(z);

                    player.sendMessage("Your current coordinates: X: " + roundedX + ", Y: " + roundedY + ", Z: " + roundedZ);
                    return true;
                } else if (args.length == 1) {
                    if (sender.hasPermission("coordinates.admin")) {
                        Player target = getServer().getPlayer(args[0]);
                        if (target != null) {
                            double x = target.getX();
                            double y = target.getY();
                            double z = target.getZ();

                            int roundedX = (int) Math.round(x);
                            int roundedY = (int) Math.round(y);
                            int roundedZ = (int) Math.round(z);

                            player.sendMessage("Player named " + target.getName() + " coordinates" + ": X: " + roundedX + ", Y: " + roundedY + ", Z: " + roundedZ);
                            return true;
                        } else {
                            sender.sendMessage("Player with name " + args[0] + " not found.");
                            return true;
                        }
                    } else {
                        sender.sendMessage("You do not have permission to view other players' coordinates.");
                        return true;
                    }
                }
            } else {
                sender.sendMessage("This command is only available in game.");
                return true;
            }
        }
        return false;
    }
}
