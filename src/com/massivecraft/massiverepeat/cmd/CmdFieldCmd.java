package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.mcore3.cmd.HelpCommand;

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
		
		/*this.addSubCommand(new CmdGateTargetHere());
		this.addSubCommand(new CmdGateTargetGate());
		this.addSubCommand(new CmdGateTargetGoto());
		this.addSubCommand(new CmdGateTargetRemove());*/
		this.setDesc("manage repeater commads");
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}