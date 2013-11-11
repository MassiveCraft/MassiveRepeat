package com.massivecraft.massiverepeat;

import com.massivecraft.massiverepeat.cmd.RepeatBasecommand;
import com.massivecraft.mcore.MPlugin;

public class P extends MPlugin
{
	// Our single plugin instance
	public static P p;
	
	// Command
	public RepeatBasecommand basecommand;
	
	public P()
	{
		P.p = this;
	}
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Load Conf from disk
		ConfServer.i.load();
		
		RepeaterColl.i.init();
		
		// Load and startup repeaters
		int startedCount = RepeaterColl.i.startup();
		int totalCount = RepeaterColl.i.getAll().size();
		
		log(""+startedCount+" repeaters loaded.");
		log(""+totalCount+" repeaters started.");
		
		// Add Base Commands
		this.basecommand = new RepeatBasecommand();
		this.basecommand.register();
		
		postEnable();
	}
}
