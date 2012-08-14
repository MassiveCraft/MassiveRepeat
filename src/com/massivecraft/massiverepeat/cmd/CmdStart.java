package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;

public class CmdStart extends RepeatCommand
{
	public CmdStart()
	{
		this.addAliases("start","play");
		this.addRequiredArg("id");
		this.addRequirements(new ReqHasPerm(Permission.START.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.argAs(0, Repeater.class);
		if (repeater == null) return;
		
		boolean result = repeater.start();
		
		if (result)
		{
			this.msg("<i>Repeater \"<h>"+repeater.getId()+"<i>\" started.");
		}
		else
		{
			this.msg("<i>Repeater \"<h>"+repeater.getId()+"<i>\" is already running.");
		}
	}
}