package com.massivecraft.massiverepeat.cmdutil;

import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore.Predictate;

public class RepeaterCreatorNameEqPredictate implements Predictate<Repeater>
{
	public String creatorName;
	
	public RepeaterCreatorNameEqPredictate(String creatorName)
	{
		this.creatorName = creatorName;
	}
	
	@Override
	public boolean apply(Repeater repeater)
	{
		if (this.creatorName == null)
		{
			return repeater.getCreatorName() == null;
		}
		return this.creatorName.equalsIgnoreCase(repeater.getCreatorName());
	}
}
