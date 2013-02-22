package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore.cmd.MCommand;
import com.massivecraft.mcore.util.Txt;

public abstract class RepeatCommand extends MCommand
{
	public void repeatmsg(Repeater repeater, String msg, Object... args)
	{
		this.msg(Txt.parse("<i>Repeater \"<h>%s<i>\" ", repeater.getId())+msg, args);
	}
}
