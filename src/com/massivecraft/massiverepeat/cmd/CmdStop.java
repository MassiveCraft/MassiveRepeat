package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.cmdarg.ARRepeater;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;

public class CmdStop extends RepeatCommand
{
	public CmdStop()
	{
		this.addAliases("stop");
		this.addRequiredArg("id");
		this.addRequirements(new ReqHasPerm(InternalPermission.STOP.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.arg(0, ARRepeater.get());
		if (repeater == null) return;
		
		boolean result = repeater.stop();
		
		if (result)
		{
			this.repeatmsg(repeater, "stopped.");
		}
		else
		{
			this.repeatmsg(repeater, "is already stopped.");
		}
	}
}