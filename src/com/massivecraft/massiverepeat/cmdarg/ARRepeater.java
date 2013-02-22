package com.massivecraft.massiverepeat.cmdarg;

import java.util.Collection;

import org.bukkit.command.CommandSender;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.RepeaterColl;
import com.massivecraft.mcore5.cmd.arg.ARAbstractSelect;

public class ARRepeater extends ARAbstractSelect<Repeater>
{
	@Override
	public String typename()
	{
		return "repeater";
	}

	@Override
	public Repeater select(String str, CommandSender sender)
	{
		return RepeaterColl.i.get(str);
	}
	
	@Override
	public boolean canList(CommandSender sender)
	{
		return InternalPermission.LIST.has(sender, false);
	}

	@Override
	public Collection<String> altNames(CommandSender sender)
	{
		return RepeaterColl.i.getIds();
	}
	
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static ARRepeater i = new ARRepeater();
	public static ARRepeater get() { return i; }
	
}
