package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.cmdarg.ARRepeater;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;

public class CmdPause extends RepeatCommand
{
	public CmdPause()
	{
		this.addAliases("pause","hold");
		this.addRequiredArg("id");
		this.addRequirements(new ReqHasPerm(Permission.PAUSE.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.arg(0, ARRepeater.get());
		if (repeater == null) return;
		
		boolean result = repeater.pause();
		
		if (result)
		{
			this.repeatmsg(repeater, "paused.");
		}
		else
		{
			this.repeatmsg(repeater, "is already paused.");
		}
	}
}