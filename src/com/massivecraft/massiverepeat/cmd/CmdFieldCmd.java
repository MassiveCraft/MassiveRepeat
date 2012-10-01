package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.mcore4.cmd.HelpCommand;

public class CmdFieldCmd extends RepeatCommand
{
	public CmdFieldCmd()
	{
		super();
		this.addAliases("cmd");
		
		this.addSubCommand(new CmdFieldCmdList());
		this.addSubCommand(new CmdFieldCmdAdd());
		this.addSubCommand(new CmdFieldCmdRemove());
		this.addSubCommand(new CmdFieldCmdClear());
		
		this.setDesc("manage repeater commads");
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}