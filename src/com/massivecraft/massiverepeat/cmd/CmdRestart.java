package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.cmdarg.ARRepeater;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;

public class CmdRestart extends RepeatCommand
{
	public CmdRestart()
	{
		this.addAliases("restart","replay","run");
		this.addRequiredArg("id");
		this.addRequirements(new ReqHasPerm(InternalPermission.RESTART.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.arg(0, ARRepeater.get());
		if (repeater == null) return;
		
		repeater.restart();
		
		this.repeatmsg(repeater, "was restarted.");
	}
}