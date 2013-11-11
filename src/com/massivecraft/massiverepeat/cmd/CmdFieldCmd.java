package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.mcore.cmd.HelpCommand;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdFieldCmd extends RepeatCommand
{
	public CmdFieldCmd()
	{
		this.addAliases("cmd");
		
		this.addSubCommand(new CmdFieldCmdList());
		this.addSubCommand(new CmdFieldCmdAdd());
		this.addSubCommand(new CmdFieldCmdRemove());
		this.addSubCommand(new CmdFieldCmdClear());
		
		this.addRequirements(new ReqHasPerm(InternalPermission.ACCESS_BASE_FIELD_CMD.node));
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.get().execute(this.sender, this.args, this.commandChain);
	}
}