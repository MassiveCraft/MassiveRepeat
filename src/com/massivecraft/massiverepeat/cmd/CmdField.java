package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.mcore4.cmd.HelpCommand;
import com.massivecraft.mcore4.cmd.arg.ARBoolean;
import com.massivecraft.mcore4.cmd.arg.ARInteger;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;

public class CmdField extends RepeatCommand
{
	public CmdField()
	{
		super();
		this.addAliases("field");
		this.addSubCommand(new CmdFieldCmd());
		this.addSubCommand(new CmdFieldX<Boolean>("permanent", ARBoolean.get(), InternalPermission.FIELD_PERMANENT));
		this.addSubCommand(new CmdFieldX<Integer>("length", ARInteger.get(), InternalPermission.FIELD_LENGTH));
		this.addSubCommand(new CmdFieldX<Integer>("pos", ARInteger.get(), InternalPermission.FIELD_POS));
		this.addSubCommand(new CmdFieldX<Integer>("mindelay", ARInteger.get(), InternalPermission.FIELD_MINDELAY));
		this.addSubCommand(new CmdFieldX<Integer>("maxdelay", ARInteger.get(), InternalPermission.FIELD_MAXDELAY));
		this.addSubCommand(new CmdFieldX<Integer>("mininterval", ARInteger.get(), InternalPermission.FIELD_MININTERVAL));
		this.addSubCommand(new CmdFieldX<Integer>("maxinterval", ARInteger.get(), InternalPermission.FIELD_MAXINTERVAL));
		
		this.addRequirements(new ReqHasPerm(InternalPermission.ACCESS_BASE_FIELD.node));
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}