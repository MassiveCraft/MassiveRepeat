package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.mcore4.cmd.HelpCommand;
import com.massivecraft.mcore4.cmd.arg.ARBoolean;
import com.massivecraft.mcore4.cmd.arg.ARInteger;

public class CmdField extends RepeatCommand
{
	public CmdField()
	{
		super();
		this.addAliases("field");
		this.addSubCommand(new CmdFieldCmd());
		this.addSubCommand(new CmdFieldX<Boolean>("permanent", ARBoolean.get(), Permission.FIELD_PERMANENT));
		this.addSubCommand(new CmdFieldX<Integer>("length", ARInteger.get(), Permission.FIELD_LENGTH));
		this.addSubCommand(new CmdFieldX<Integer>("pos", ARInteger.get(), Permission.FIELD_POS));
		this.addSubCommand(new CmdFieldX<Integer>("mindelay", ARInteger.get(), Permission.FIELD_MINDELAY));
		this.addSubCommand(new CmdFieldX<Integer>("maxdelay", ARInteger.get(), Permission.FIELD_MAXDELAY));
		this.addSubCommand(new CmdFieldX<Integer>("mininterval", ARInteger.get(), Permission.FIELD_MININTERVAL));
		this.addSubCommand(new CmdFieldX<Integer>("maxinterval", ARInteger.get(), Permission.FIELD_MAXINTERVAL));
		this.setDesc("manage repeater fields");
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}