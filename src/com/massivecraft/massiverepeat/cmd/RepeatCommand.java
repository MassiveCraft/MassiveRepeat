package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.P;
import com.massivecraft.mcore4.cmd.MCommand;

public abstract class RepeatCommand extends MCommand
{
	public P p;
	public RepeatCommand()
	{
		super();
		this.p = P.p;
	}
	
	@Override
	public P p()
	{
		return P.p;
	}
}
