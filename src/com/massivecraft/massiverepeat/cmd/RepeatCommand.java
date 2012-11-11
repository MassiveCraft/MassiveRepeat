package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.P;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore5.cmd.MCommand;
import com.massivecraft.mcore5.util.Txt;

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
	
	public void repeatmsg(Repeater repeater, String msg, Object... args)
	{
		this.msg(Txt.parse("<i>Repeater \"<h>%s<i>\" ", repeater.getId())+msg, args);
	}
}
