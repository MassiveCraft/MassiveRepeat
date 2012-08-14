package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;

public class CmdDelete extends RepeatCommand
{
	public CmdDelete()
	{
		this.addAliases("del","delete","rm","rem","remove");
		this.addRequiredArg("id");
		this.addRequirements(new ReqHasPerm(Permission.DELETE.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.argAs(0, Repeater.class);
		if (repeater == null) return;
		
		this.msg("<i>Deleted repeater \"<h>"+repeater.getId()+"<i>\".");
		
		repeater.stop();
		repeater.detach();
	}
	
}