package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.cmdarg.ARRepeater;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;

public class CmdFieldCmdClear extends RepeatCommand
{
	public CmdFieldCmdClear()
	{
		this.addAliases("clear");
		this.addRequiredArg("id");
		this.addRequirements(new ReqHasPerm(InternalPermission.FIELD_CMDS_CLEAR.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.arg(0, ARRepeater.get());
		if (repeater == null) return;
		
		repeater.getCmds().clear();
		
		this.repeatmsg(repeater, "has no commands now.");
	}
}