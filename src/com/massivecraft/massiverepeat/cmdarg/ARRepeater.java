package com.massivecraft.massiverepeat.cmdarg;

import java.util.Collection;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.RepeaterManager;
import com.massivecraft.mcore4.cmd.MCommand;
import com.massivecraft.mcore4.cmd.arg.ARAbstractSelect;

public class ARRepeater extends ARAbstractSelect<Repeater>
{
	@Override
	public String typename()
	{
		return "repeater";
	}

	@Override
	public Repeater select(String str, MCommand mcommand)
	{
		return RepeaterManager.i.get(str);
	}
	
	@Override
	public boolean canList(MCommand mcommand)
	{
		return Permission.LIST.has(mcommand.sender, false);
	}

	@Override
	public Collection<String> altNames(MCommand mcommand)
	{
		return RepeaterManager.i.getIds();
	}
	
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static ARRepeater i = new ARRepeater();
	public static ARRepeater get() { return i; }
	
}
