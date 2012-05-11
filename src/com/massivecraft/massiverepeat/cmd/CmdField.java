package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.mcore3.cmd.HelpCommand;

public class CmdField extends RepeatCommand
{
	public CmdField()
	{
		super();
		this.addAliases("field");
		this.addSubCommand(new CmdFieldCmd());
		this.addSubCommand(new CmdFieldX<Boolean>("permanent", Boolean.class, Permission.FIELD_PERMANENT));
		this.addSubCommand(new CmdFieldX<Integer>("length", Integer.class, Permission.FIELD_LENGTH));
		this.addSubCommand(new CmdFieldX<Integer>("pos", Integer.class, Permission.FIELD_POS));
		this.addSubCommand(new CmdFieldX<Integer>("mindelay", Integer.class, Permission.FIELD_MINDELAY));
		this.addSubCommand(new CmdFieldX<Integer>("maxdelay", Integer.class, Permission.FIELD_MAXDELAY));
		this.addSubCommand(new CmdFieldX<Integer>("mininterval", Integer.class, Permission.FIELD_MININTERVAL));
		this.addSubCommand(new CmdFieldX<Integer>("maxinterval", Integer.class, Permission.FIELD_MAXINTERVAL));
		this.setDesc("manage repeater fields");
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}