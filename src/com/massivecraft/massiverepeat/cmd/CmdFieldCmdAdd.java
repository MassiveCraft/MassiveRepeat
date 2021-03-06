package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.massiverepeat.RepeatPerm;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.cmdarg.ARRepeater;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.Txt;

public class CmdFieldCmdAdd extends RepeatCommand
{
	public CmdFieldCmdAdd()
	{
		this.addAliases("add");
		this.addRequiredArg("id");
		this.addRequiredArg("cmd");
		this.setErrorOnToManyArgs(false);
		this.addRequirements(new ReqHasPerm(InternalPermission.FIELD_CMDS_ADD.node));
	}

	@Override
	public void perform()
	{		
		Repeater repeater = this.arg(0, ARRepeater.get());
		if (repeater == null) return;
		
		String command = this.argConcatFrom(1);
		
		command = Txt.removeLeadingCommandDust(command);
		String commandName = Txt.divideOnFirstSpace(command).getKey();
		
		if (! RepeatPerm.has(sender, commandName, true)) return;
		/*if ( ! P.p.canSenderRepeatCommand(sender, commandName))
		{
			msg("<b>You are not allowed to repeat the \"<h>%s<b>\"-command.", commandName);
			return;
		}*/
		
		repeater.addCmd(command);
		
		this.repeatmsg(repeater, "got a new command.");
	}
}