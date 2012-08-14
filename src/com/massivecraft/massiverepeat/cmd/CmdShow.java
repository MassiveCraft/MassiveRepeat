package com.massivecraft.massiverepeat.cmd;

import java.util.List;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;
import com.massivecraft.mcore4.util.Txt;

public class CmdShow extends RepeatCommand
{
	public CmdShow()
	{
		this.addAliases("show","info","view");
		this.addRequiredArg("id");
		this.addOptionalArg("page", "1");
		this.addRequirements(new ReqHasPerm(Permission.SHOW.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.argAs(0, Repeater.class);
		if (repeater == null) return;
		
		Integer pageHumanBased = this.argAs(1, Integer.class, 1);
		if (pageHumanBased == null) return;
		
		List<String> lines = Txt.parseWrap(repeater.getDescMultiLine());
		this.sendMessage(Txt.getPage(lines, pageHumanBased, "Repeater "+repeater.getId()));
	}
}