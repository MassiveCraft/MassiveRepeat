package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.ConfServer;
import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.mcore.cmd.HelpCommand;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class RepeatBasecommand extends RepeatCommand
{
	public RepeatBasecommand()
	{
		super();
		this.addAliases(ConfServer.aliases);
		this.addSubCommand(HelpCommand.getInstance());
		this.addSubCommand(new CmdNew("create", InternalPermission.CREATE, false));
		this.addSubCommand(new CmdNew("add", InternalPermission.ADD, true));
		this.addSubCommand(new CmdDelete());
		this.addSubCommand(new CmdList());
		this.addSubCommand(new CmdShow());
		this.addSubCommand(new CmdStart());
		this.addSubCommand(new CmdRestart());
		this.addSubCommand(new CmdPause());
		this.addSubCommand(new CmdStop());
		this.addSubCommand(new CmdField());
		
		this.addRequirements(new ReqHasPerm(InternalPermission.ACCESS_BASE.node));
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}