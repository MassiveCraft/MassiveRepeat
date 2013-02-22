package com.massivecraft.massiverepeat.cmd;

import java.util.List;

import com.massivecraft.massiverepeat.InternalPermission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.massiverepeat.cmdarg.ARRepeater;
import com.massivecraft.mcore.cmd.arg.ARInteger;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.Txt;

public class CmdShow extends RepeatCommand
{
	public CmdShow()
	{
		this.addAliases("show","info","view");
		this.addRequiredArg("id");
		this.addOptionalArg("page", "1");
		this.addRequirements(new ReqHasPerm(InternalPermission.SHOW.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.arg(0, ARRepeater.get());
		if (repeater == null) return;
		
		Integer pageHumanBased = this.arg(1, ARInteger.get(), 1);
		if (pageHumanBased == null) return;
		
		List<String> lines = Txt.parseWrap(repeater.getDescMultiLine());
		this.sendMessage(Txt.getPage(lines, pageHumanBased, "Repeater "+repeater.getId(), sender));
	}
}