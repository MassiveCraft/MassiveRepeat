package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.cmdarg.ARRepeater;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;

public class CmdStart extends RepeatCommand
{
	public CmdStart()
	{
		this.addAliases("start","play");
		this.addRequiredArg("id");
		this.addRequirements(new ReqHasPerm(InternalPermission.START.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.arg(0, ARRepeater.get());
		if (repeater == null) return;
		
		boolean result = repeater.start();
		
		if (result)
		{
			this.repeatmsg(repeater, "started.");
		}
		else
		{
			this.repeatmsg(repeater, "is already running.");
		}
	}
}