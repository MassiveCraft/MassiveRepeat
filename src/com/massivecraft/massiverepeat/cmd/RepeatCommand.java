package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.P;
import com.massivecraft.mcore3.cmd.MCommand;

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
	
	/*public GPlayer gme;
	@Override
	public void fixSenderVars()
	{
		this.gme = GPlayers.i.get(this.me);
	}*/
	
}
