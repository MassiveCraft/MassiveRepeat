package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.cmdarg.ARRepeater;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;

public class CmdDelete extends RepeatCommand
{
	public CmdDelete()
	{
		this.addAliases("del","delete","rm","rem","remove");
		this.addRequiredArg("id");
		this.addRequirements(new ReqHasPerm(InternalPermission.DELETE.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.arg(0, ARRepeater.get());
		if (repeater == null) return;
		
		this.repeatmsg(repeater, "was deleted.");
		
		repeater.stop();
		repeater.detach();
	}
	
}