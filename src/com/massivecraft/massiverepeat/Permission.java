package com.massivecraft.massiverepeat;

import org.bukkit.command.CommandSender;

import com.massivecraft.mcore3.util.Perm;

public enum Permission
{
	CREATE("create"),
	ADD("add"),
	DELETE("delete"),
	LIST("list"),
	SHOW("show"),
	START("start"),
	RESTART("restart"),
	PAUSE("pause"),
	STOP("stop"),
	REPEAT_STAR("repeat.*"),
	FIELD_CMDS_LIST("field.cmds.list"),
	FIELD_CMDS_ADD("field.cmds.add"),
	FIELD_CMDS_REMOVE("field.cmds.remove"),
	FIELD_CMDS_CLEAR("field.cmds.clear"),
	FIELD_PERMANENT("field.permanent"),
	FIELD_LENGTH("field.length"),
	FIELD_POS("field.pos"),
	FIELD_MINDELAY("field.mindelay"),
	FIELD_MAXDELAY("field.maxdelay"),
	FIELD_MININTERVAL("field.mininterval"),
	FIELD_MAXINTERVAL("field.maxinterval"),
	;
	
	public final String node;
	
	Permission(final String permissionNode)
	{
		this.node = "massiverepeat."+permissionNode;
    }
	
	public boolean has(CommandSender sender, boolean informSenderIfNot)
	{
		return Perm.has(sender, this.node, informSenderIfNot);
	}
	
	public boolean has(CommandSender sender)
	{
		return has(sender, false);
	}
}