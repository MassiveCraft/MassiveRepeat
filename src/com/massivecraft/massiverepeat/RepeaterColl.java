package com.massivecraft.massiverepeat;

import com.massivecraft.mcore.store.Coll;
import com.massivecraft.mcore.store.MStore;

public class RepeaterColl extends Coll<Repeater>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //	
	
	public static RepeaterColl i = new RepeaterColl();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	private RepeaterColl()
	{
		super(Const.basenameRepeater, Repeater.class, MStore.getDb(ConfServer.dburi), P.p);
	}
	
	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
	public int startup()
	{
		int ret = 0;
		for (Repeater repeater : this.getAll())
		{
			if (repeater.isRunning())
			{
				repeater.scheduleIntervalTask();
				ret++;
			}
		}
		return ret;
	}
	
}
