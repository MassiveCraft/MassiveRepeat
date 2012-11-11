package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.cmdarg.ARRepeater;
import com.massivecraft.mcore5.cmd.arg.ARInteger;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;

public class CmdFieldCmdRemove extends RepeatCommand
{
	public CmdFieldCmdRemove()
	{
		this.addAliases("rm","rem","remove","del","delete");
		this.addRequiredArg("id");
		this.addRequiredArg("index");
		this.addRequirements(new ReqHasPerm(InternalPermission.FIELD_CMDS_REMOVE.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.arg(0, ARRepeater.get());
		if (repeater == null) return;
		
		Integer index = this.arg(1, ARInteger.get());
		if (index == null) return;
		
		if (index > repeater.getCmds().size())
		{
			msg("<b>Index to large.");
			return;
		}
		
		index--;
		repeater.getCmds().remove(index.intValue());
		
		this.repeatmsg(repeater, "now has one less command.");
	}
}