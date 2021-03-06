package com.massivecraft.massiverepeat;

import java.util.List;

import com.massivecraft.mcore.SimpleConfig;
import com.massivecraft.mcore.util.MUtil;

public class ConfServer extends SimpleConfig
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public static List<String> aliases = MUtil.list("rep", "repeat", "repeater");
	
	// -------------------------------------------- //
	// Persistence
	// -------------------------------------------- //
	public static transient ConfServer i = new ConfServer();
	public ConfServer() { super(P.p); }
}