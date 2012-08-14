package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;

public class CmdStop extends RepeatCommand
{
	public CmdStop()
	{
		this.addAliases("stop");
		this.addRequiredArg("id");
		this.addRequirements(new ReqHasPerm(Permission.STOP.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.argAs(0, Repeater.class);
		if (repeater == null) return;
		
		boolean result = repeater.stop();
		
		if (result)
		{
			this.msg("<i>Repeater \"<h>"+repeater.getId()+"<i>\" stopped.");
		}
		else
		{
			this.msg("<i>Repeater \"<h>"+repeater.getId()+"<i>\" is already stopped.");
		}
	}
}