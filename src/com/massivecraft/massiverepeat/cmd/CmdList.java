package com.massivecraft.massiverepeat.cmd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.RepeaterColl;
import com.massivecraft.massiverepeat.cmdutil.ListCommandComparator;
import com.massivecraft.massiverepeat.cmdutil.RepeaterCreatorNameEqPredictate;
import com.massivecraft.mcore5.cmd.arg.ARInteger;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;
import com.massivecraft.mcore5.util.Txt;

public class CmdList extends RepeatCommand
{
	public CmdList()
	{
		this.addAliases("l","ls","list");
		this.addOptionalArg("page", "1");
		this.addOptionalArg("creator", "*anyone*");
		this.addRequirements(new ReqHasPerm(InternalPermission.LIST.node));
	}

	@Override
	public void perform()
	{
		Integer pageHumanBased = this.arg(0, ARInteger.get(), 1);
		if (pageHumanBased == null) return;
		
		String creatorName = this.arg(1);
		Collection<Repeater> repeaters;
		if (creatorName != null)
		{
			repeaters = RepeaterColl.i.getAll(new RepeaterCreatorNameEqPredictate(creatorName), ListCommandComparator.instance);
		}
		else
		{
			repeaters = RepeaterColl.i.getAll(null, ListCommandComparator.instance);
		}
		
		List<String> lines = new ArrayList<String>();
		lines.add("<silver>volatile <green>i<red>d <silver>creator <pink>delay <gold>interval <aqua>pos/length");
		
		for(Repeater repeater: repeaters)
		{
			
			lines.add(repeater.getDescOneLine());
		}
		
		lines = Txt.parseWrap(lines);
		this.sendMessage(Txt.getPage(lines, pageHumanBased, ""+repeaters.size()+" Repeaters", sender));
	}
}