package com.massivecraft.massiverepeat;

import java.io.File;

import com.massivecraft.mcore4.persist.gson.GsonClassManager;

public class RepeaterManager extends GsonClassManager<Repeater>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	public static RepeaterManager i = new RepeaterManager();
	
	private RepeaterManager()
	{
		super(P.p.gson, new File(P.p.getDataFolder(), "repeater"), false, true);
		P.p.persist.setManager(Repeater.class, this);
		P.p.persist.setSaveInterval(Repeater.class, 1000*60*30);
		
		this.loadAll();
	}

	@Override
	public Class<Repeater> getManagedClass() { return Repeater.class; }

	@Override
	public String idFix(Object oid)
	{
		if (oid == null) return null;
		if (oid instanceof String) return (String) oid;
		return null;
	}

	@Override
	public boolean idCanFix(Class<?> clazz) { return clazz.equals(String.class); }
	
	// -------------------------------------------- //
	// SPECIAL METHODS
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
