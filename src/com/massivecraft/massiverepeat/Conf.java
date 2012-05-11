package com.massivecraft.massiverepeat;

import java.util.ArrayList;
import java.util.List;

public class Conf
{
	public static List<String> aliases = new ArrayList<String>();
	
	static
	{
		aliases.add("rep");
		aliases.add("repeat");
		aliases.add("repeater");
	}
	
	// -------------------------------------------- //
	// Persistance
	// -------------------------------------------- //
	private static transient Conf i = new Conf();
	public static void load()
	{
		P.p.one.loadOrSaveDefault(i, Conf.class);
	}
	public static void save()
	{
		P.p.one.save(i);
	}
}