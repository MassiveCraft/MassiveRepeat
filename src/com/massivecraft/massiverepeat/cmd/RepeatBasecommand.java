package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.Conf;
import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.mcore4.cmd.HelpCommand;

public class RepeatBasecommand extends RepeatCommand
{
	public RepeatBasecommand()
	{
		super();
		this.addAliases(Conf.aliases);
		this.addSubCommand(HelpCommand.getInstance());
		this.addSubCommand(new CmdNew("create", Permission.CREATE, false));
		this.addSubCommand(new CmdNew("add", Permission.ADD, true));
		this.addSubCommand(new CmdDelete());
		this.addSubCommand(new CmdList());
		this.addSubCommand(new CmdShow());
		this.addSubCommand(new CmdStart());
		this.addSubCommand(new CmdRestart());
		this.addSubCommand(new CmdPause());
		this.addSubCommand(new CmdStop());
		this.addSubCommand(new CmdField());
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}