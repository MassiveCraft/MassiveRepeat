package com.massivecraft.massiverepeat;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.massivecraft.massiverepeat.cmd.RepeatBasecommand;
import com.massivecraft.mcore4.MPlugin;

public class P extends MPlugin
{
	// Our single plugin instance
	public static P p;
	
	// Command
	public RepeatBasecommand basecommand;
	
	public static net.milkbowl.vault.permission.Permission vaultPerm = null;
	
	public P()
	{
		P.p = this;
	}
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Load Conf from disk
		Conf.load();
		
		// Vault integration
		if ( ! this.setupPermissions())
		{
			log("Vault missing!");
			this.suicide();
		}
		
		// Load and startup repeaters
		int startedCount = RepeaterManager.i.startup();
		int totalCount = RepeaterManager.i.getAll().size();
		
		log(""+startedCount+" repeaters loaded.");
		log(""+totalCount+" repeaters started.");
		
		// Add Base Commands
		this.basecommand = new RepeatBasecommand();
		this.basecommand.register();
		
		postEnable();
	}
	
	protected Boolean setupPermissions()
    {
        RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null)
        {
        	vaultPerm = permissionProvider.getProvider();
        }
        return (vaultPerm != null);
    } 
	
	public boolean canSenderRepeatCommand(CommandSender sender, String command)
	{
		if (sender.hasPermission(Permission.REPEAT_STAR.node)) return true;
		if ( ! (sender instanceof Player)) return false;
		Player player = (Player)sender;
		String permission = "massiverepeat.repeat."+command;
		return vaultPerm.playerHas(player, permission);
	}
}
