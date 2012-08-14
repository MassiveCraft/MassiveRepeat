package com.massivecraft.massiverepeat.cmd;

import java.util.Map.Entry;

import com.massivecraft.massiverepeat.P;
import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.RepeaterManager;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;
import com.massivecraft.mcore4.util.IntervalUtil;
import com.massivecraft.mcore4.util.Txt;

public class CmdNew extends RepeatCommand
{
	protected boolean autostart;
	
	public CmdNew(String alias, Permission perm, boolean autostart)
	{
		this.addAliases(alias);
		this.addRequiredArg("id|temp");
		this.addRequiredArg("delay");
		this.addRequiredArg("interval");
		this.addRequiredArg("length");
		this.addOptionalArg("command", "*none*");
		this.setErrorOnToManyArgs(false);
		
		this.addRequirements(new ReqHasPerm(perm.node));
		this.autostart = autostart;
	}

	@Override
	public void perform()
	{
		// Read the args
		String idOrTemp = this.arg(0);
		boolean permanent = ! idOrTemp.toLowerCase().startsWith("temp");
		
		String delay = this.arg(1);
		Entry<Integer, Integer> delayInterval = IntervalUtil.parseIntegerInterval(delay, null, null);
		Integer mindelay = delayInterval.getKey();
		Integer maxdelay = delayInterval.getValue();
		if (mindelay == null || maxdelay == null)
		{
			msg("<b>Invalid delay.");
		}
		
		String interval = this.arg(2);
		Entry<Integer, Integer> intervalInterval = IntervalUtil.parseIntegerInterval(interval, null, null);
		Integer mininterval = intervalInterval.getKey();
		Integer maxinterval = intervalInterval.getValue();
		if (mininterval == null || maxinterval == null)
		{
			msg("<b>Invalid interval.");
		}
		
		Integer length = this.argAs(3, Integer.class);
		if (length == null) return;
		
		String command = null;
		if (this.argIsSet(4))
		{
			command = this.argConcatFrom(4);
			command = Txt.removeLeadingCommandDust(command);
			String commandName = Txt.divideOnFirstSpace(command).getKey();
			if ( ! P.p.canSenderRepeatCommand(sender, commandName))
			{
				msg("<b>You are not allowed to repeat the \"<h>"+commandName+"<b>\"-command.");
				return;
			}
		}
		
		Repeater repeater;
		if (permanent)
		{
			repeater = RepeaterManager.i.create(idOrTemp);
		}
		else
		{
			repeater = RepeaterManager.i.create();
		}
		
		repeater.setCreator(sender);
		
		if (command != null)
		{
			repeater.addCmd(command);
		}
		
		repeater.setPermanent(permanent);
		repeater.setLength(length);
		repeater.setLength(length);
		repeater.setMindelay(mindelay);
		repeater.setMaxdelay(maxdelay);
		repeater.setMininterval(mininterval);
		repeater.setMaxinterval(maxinterval);
		
		if (this.autostart)
		{
			repeater.start();
		}
		
		repeater.save();
		
		String textAndStarted = this.autostart ? "and autostarted " : "";
		String textPermanent = permanent ? "permanent" : "volatile";
		
		this.msg("<i>Created "+textAndStarted+"<h>"+textPermanent+"<i> repeater \"<h>"+repeater.getId()+"<i>\".");
	}
	
}