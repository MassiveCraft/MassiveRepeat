package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.cmdarg.ARRepeater;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;

public class CmdFieldCmdClear extends RepeatCommand
{
	public CmdFieldCmdClear()
	{
		this.addAliases("clear");
		this.addRequiredArg("id");
		this.addRequirements(new ReqHasPerm(Permission.FIELD_CMDS_CLEAR.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.arg(0, ARRepeater.get());
		if (repeater == null) return;
		
		repeater.getCmds().clear();
		
		this.msg("<i>Removed all commands from repeater \"<h>"+repeater.getId()+"<i>\".");
	}
}