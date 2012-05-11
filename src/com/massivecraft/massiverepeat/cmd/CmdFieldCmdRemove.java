package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore3.cmd.req.ReqHasPerm;

public class CmdFieldCmdRemove extends RepeatCommand
{
	public CmdFieldCmdRemove()
	{
		this.addAliases("rm","rem","remove","del","delete");
		this.addRequiredArg("id");
		this.addRequiredArg("index");
		this.addRequirements(new ReqHasPerm(Permission.FIELD_CMDS_REMOVE.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.argAs(0, Repeater.class);
		if (repeater == null) return;
		
		Integer index = this.argAs(1, Integer.class);
		if (index == null) return;
		
		if (index > repeater.getCmds().size())
		{
			msg("<b>Index to large.");
			return;
		}
		
		index--;
		repeater.getCmds().remove(index.intValue());
		
		this.msg("<i>Command removed from repeater \"<h>"+repeater.getId()+"<i>\".");
	}
}