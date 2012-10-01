package com.massivecraft.massiverepeat.cmd;

import java.lang.reflect.Field;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.cmdarg.ARRepeater;
import com.massivecraft.mcore4.cmd.arg.ArgReader;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;

public class CmdFieldX<T> extends RepeatCommand
{
	String fieldName;
	ArgReader<T> argReader;
	public CmdFieldX(String fieldName, ArgReader<T> argReader, Permission perm)
	{
		this.addAliases(fieldName);
		this.addRequiredArg("id");
		this.addOptionalArg("val", "*read*");
		this.addRequirements(new ReqHasPerm(perm.node));
		this.fieldName = fieldName;
		this.argReader = argReader;
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.arg(0, ARRepeater.get());
		if (repeater == null) return;
		
		if (this.argIsSet(1))
		{
			T val = this.arg(1, argReader);
			if (val == null) return;
			this.set(repeater, val);
		}
		
		this.repeatmsg(repeater, "has %s = %s.", fieldName, this.get(repeater).toString());
	}
	
	@SuppressWarnings("unchecked")
	public T get(Repeater repeater)
	{
		try
		{
			Field field = Repeater.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			return (T) field.get(repeater);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void set(Repeater repeater, Object val)
	{
		try
		{
			Field field = Repeater.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(repeater, val);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}