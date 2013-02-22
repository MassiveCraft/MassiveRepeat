package com.massivecraft.massiverepeat;

import com.massivecraft.mcore.store.Coll;
import com.massivecraft.mcore.store.MStore;

public class RepeaterColl extends Coll<Repeater, String>
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
		super(MStore.getDb(ConfServer.dburi), P.p, "ai", Const.basenameRepeater, Repeater.class, String.class, false);
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
