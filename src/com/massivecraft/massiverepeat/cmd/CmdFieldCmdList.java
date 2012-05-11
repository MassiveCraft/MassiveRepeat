package com.massivecraft.massiverepeat.cmd;

import java.util.List;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore3.cmd.req.ReqHasPerm;
import com.massivecraft.mcore3.util.Txt;

public class CmdFieldCmdList extends RepeatCommand
{
	public CmdFieldCmdList()
	{
		this.addAliases("l","ls","list");
		this.addRequiredArg("id");
		this.addOptionalArg("page", "1");
		this.addRequirements(new ReqHasPerm(Permission.FIELD_CMDS_LIST.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.argAs(0, Repeater.class);
		if (repeater == null) return;
		
		Integer pageHumanBased = this.argAs(1, Integer.class, 1);
		if (pageHumanBased == null) return;
		
		List<String> lines = Txt.parseWrap(repeater.getFormatedCommandList());
		 
		this.sendMessage(Txt.getPage(lines, pageHumanBased, ""+repeater.getCmds().size()+" Commands"));
	}
}