package com.massivecraft.massiverepeat.cmdarg;

import org.bukkit.command.CommandSender;

import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.RepeaterManager;
import com.massivecraft.mcore4.MPlugin;
import com.massivecraft.mcore4.cmd.arg.AHBase;

public class AHRepeater extends AHBase<Repeater>
{
	@Override
	public Repeater parse(String str, String style, CommandSender sender, MPlugin p)
	{	
		this.error.clear();
		Repeater ret = null;
		
		// Then we attempt to get by id.
		ret = RepeaterManager.i.get(str);
		
		if (ret == null)
		{
			this.error.add("<b>No repeater with id \"<p>"+str+"<b>\".");
		}
		
		return ret;
	}

	private AHRepeater() {}
	private static AHRepeater instance = new AHRepeater();
	public static AHRepeater getInstance() { return instance; } 
}
