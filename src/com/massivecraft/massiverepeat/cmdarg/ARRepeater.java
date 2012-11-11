package com.massivecraft.massiverepeat.cmdarg;

import java.util.Collection;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.RepeaterColl;
import com.massivecraft.mcore5.cmd.MCommand;
import com.massivecraft.mcore5.cmd.arg.ARAbstractSelect;

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
		return RepeaterColl.i.get(str);
	}
	
	@Override
	public boolean canList(MCommand mcommand)
	{
		return InternalPermission.LIST.has(mcommand.sender, false);
	}

	@Override
	public Collection<String> altNames(MCommand mcommand)
	{
		return RepeaterColl.i.getIds();
	}
	
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static ARRepeater i = new ARRepeater();
	public static ARRepeater get() { return i; }
	
}
