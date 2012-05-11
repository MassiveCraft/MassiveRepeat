package com.massivecraft.massiverepeat.cmdutil;

import java.util.Comparator;

import com.massivecraft.massiverepeat.Repeater;

public class ListCommandComparator implements Comparator<Repeater>
{	
	@Override
	public int compare(Repeater one, Repeater two)
	{
		if (one.isPermanent() != two.isPermanent())
		{
			return one.isPermanent() ? 1 : -1;
		}
		
		return 0;
	}
	
	public static Comparator<Repeater> instance = new ListCommandComparator();
}