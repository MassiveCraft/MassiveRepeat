package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.P;
import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore3.cmd.req.ReqHasPerm;
import com.massivecraft.mcore3.util.Txt;

public class CmdFieldCmdAdd extends RepeatCommand
{
	public CmdFieldCmdAdd()
	{
		this.addAliases("add");
		this.addRequiredArg("id");
		this.addRequiredArg("cmd");
		this.setErrorOnToManyArgs(false);
		this.addRequirements(new ReqHasPerm(Permission.FIELD_CMDS_ADD.node));
	}

	@Override
	public void perform()
	{		
		Repeater repeater = this.argAs(0, Repeater.class);
		if (repeater == null) return;
		
		String command = this.argConcatFrom(1);
		
		command = Txt.removeLeadingCommandDust(command);
		String commandName = Txt.divideOnFirstSpace(command).getKey();
		if ( ! P.p.canSenderRepeatCommand(sender, commandName))
		{
			msg("<b>You are not allowed to repeat the \"<h>"+commandName+"<b>\"-command.");
			return;
		}
		
		
		repeater.addCmd(command);
		
		this.msg("<i>Added command to repeater \"<h>"+repeater.getId()+"<i>\".");
	}
}