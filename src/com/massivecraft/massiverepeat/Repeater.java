package com.massivecraft.massiverepeat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.massivecraft.mcore4.util.IntervalUtil;
import com.massivecraft.mcore4.util.Txt;

public class Repeater extends com.massivecraft.mcore4.store.Entity<Repeater, String> implements Runnable
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	@Override protected Repeater getThis() { return this; }
	
	private final static transient Repeater defaultInstance = new Repeater();
	@Override public Repeater getDefaultInstance(){ return defaultInstance; }
	@Override protected Class<Repeater> getClazz() { return Repeater.class; }
	
	// -------------------------------------------- //
	// LOAD
	// -------------------------------------------- //
	
	@Override
	public Repeater load(Repeater that)
	{
		System.out.println("did creatorName" + creatorName);
		this.creatorName = that.creatorName;
		this.cmds = that.cmds;
		this.permanent = that.permanent;
		this.length = that.length;
		this.pos = that.pos;
		this.running = that.running;
		this.lastErrorCmd = that.lastErrorCmd;
		this.mindelay = that.mindelay;
		this.maxdelay = that.maxdelay;
		this.mininterval = that.mininterval;
		this.maxinterval = that.maxinterval;
		return this;
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// The bukkit taskId. This is non persistent and changes each execution.
	protected transient Integer taskId;
	public Integer getTaskId() { return this.taskId; }
	
	// Player that created this repeater 
	protected String creatorName;
	public String getCreatorName() { return this.creatorName; }
	public void setCreatorName(String val) { this.creatorName = val; }
	public boolean createdByConsole() { return this.creatorName == null; }
	public CommandSender getCreator()
	{
		if (this.creatorName == null) return Bukkit.getConsoleSender();
		return Bukkit.getPlayerExact(creatorName);
	}
	public void setCreator(CommandSender creator)
	{
		if (creator instanceof Player)
		{
			this.creatorName = ((Player)creator).getName();
		}
		else
		{
			this.creatorName = null;
		}
	}
	
	// The commands to execute on each run
	protected List<String> cmds;
	public List<String> getCmds() { return this.cmds; }
	public void addCmd(String cmd)
	{
		this.cmds.add(Txt.removeLeadingCommandDust(cmd));
	}
	
	// Is this repeater permanent or will it detach on finish?
	protected boolean permanent;
	public boolean isPermanent() { return this.permanent; }
	public void setPermanent(boolean val) { this.permanent = val; }

	// The maximum number of executions
	protected int length;
	public int getLength() { return this.length; }
	public void setLength(int val) { this.length = val; }
	
	// The current number of performed executions
	protected int pos;
	public int getPos() { return this.pos; }
	public void setPos(int val) { this.pos = val; }
	
	// Is it running right now?
	protected boolean running;
	public boolean isRunning() { return this.running; }
	
	// What was the last error?
	protected String lastErrorCmd;
	public String getLastErrorCmd() { return this.lastErrorCmd; }
	public void setLastErrorCmd(String val) { this.lastErrorCmd = val; }
	protected String lastErrorMsg;
	public String getLastErrorMsg() { return this.lastErrorMsg; }
	public void setLastErrorMsg(String val) { this.lastErrorMsg = val; }
	
	// The "delay" is the delay till the first execution
	protected int mindelay;
	public int getMindelay() { return this.mindelay; }
	public void setMindelay(int val) { this.mindelay = val; }
	protected int maxdelay;
	public int getMaxdelay() { return this.maxdelay; }
	public void setMaxdelay(int val) { this.maxdelay = val; }
	
	// The "interval" is the time between executions
	protected int mininterval;
	public int getMininterval() { return this.mininterval; }
	public void setMininterval(int val) { this.mininterval = val; }
	protected int maxinterval;
	public int getMaxinterval() { return this.maxinterval; }
	public void setMaxinterval(int val) { this.maxinterval = val; }
	
	// -------------------------------------------- //
	// CONSTRUCTORS
	// -------------------------------------------- //
	
	public Repeater(String creatorName, List<String> cmds, int length, int pos, boolean running, int delayMin, int delayMax, int intervalMin, int intervalMax)
	{
		this.taskId = null;
		this.creatorName = creatorName;
		this.cmds = cmds;
		this.length = length;
		this.pos = pos;
		this.running = running;
		this.lastErrorCmd = null;
		this.lastErrorMsg = null;
		this.mindelay = delayMin;
		this.maxdelay = delayMax;
		this.mininterval = intervalMin;
		this.maxinterval = intervalMax;
	}
	
	public Repeater()
	{
		this(null, new ArrayList<String>(), 1, 0, false, 0, 0, 20, 20);
	}
	
	// -------------------------------------------- //
	// MEDIAPLAYER-ISH METHODS
	// -------------------------------------------- //
	
	public boolean start()
	{
		if (this.isRunning()) return false;
		this.running = true;
		this.scheduleDelayTask();
		return true;
	}
	
	public boolean pause()
	{
		if ( ! this.isRunning()) return false;
		this.running = false;
		this.cancelTask();
		return true;
	}
	
	public boolean stop()
	{
		boolean pauseResult = this.pause();
		boolean wasAtBeginning = (this.pos == 0);
		boolean wasAlreadyStopped = (pauseResult && wasAtBeginning);
		this.pos = 0;
		
		if ( ! this.permanent)
		{
			this.detach();
		}
		
		return wasAlreadyStopped;
	}
	
	public void restart()
	{
		this.stop();
		this.start();
	}
	
	// -------------------------------------------- //
	// LOWLEVEL
	// -------------------------------------------- //
	
	@Override
	public void run()
	{
		// Move time forward or stop
		if ( this.length > 0 && this.pos >= this.length )
		{
			this.stop();
		}
		else
		{
			this.scheduleIntervalTask();
		}
		
		if (this.length > 0)
		{
			this.pos++;
		}
		
		// Execute the commands. If one of them fails we report it and pause stop the execution.
		for (String cmd : this.cmds)
		{
			if ( ! this.runCommand(cmd))
			{
				this.stop();
				return;
			}
		}
	}
	
	public boolean runCommand(String command)
	{
		String cmd = command;
		try
		{
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.setLastErrorCmd(cmd);
			this.setLastErrorMsg(e.getMessage());
			this.sendInfoMessage(Txt.parse("<b>Repeater \"<h>"+this.getId()+"<b>\" had an error and stoped."));
			this.sendInfoMessage(Txt.parse("<b>The server console may contain more info."));
			this.sendInfoMessage(Txt.parse("<k>Cmd: <v>"+cmd));
			this.sendInfoMessage(Txt.parse("<k>Error: <v>"+e.getMessage()));
			return false;
		}
	}
	
	public void cancelTask()
	{
		Bukkit.getScheduler().cancelTask(this.taskId);
	}
	
	public void scheduleTask(int delay)
	{
		this.taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(P.p, this, delay);
	}
	
	public void scheduleIntervalTask()
	{
		int delay = IntervalUtil.randomIntegerFromInterval(this.mininterval, this.maxinterval);
		this.scheduleTask(delay);
	}
	
	public void scheduleDelayTask()
	{
		int delay = IntervalUtil.randomIntegerFromInterval(this.mindelay, this.maxdelay);
		this.scheduleTask(delay);
	}
	
	// -------------------------------------------- //
	// MESSAGE FEATURES AND DESCRIPTORS
	// -------------------------------------------- //
	
	public List<CommandSender> getInfoRecipients()
	{
		List<CommandSender> ret = new ArrayList<CommandSender>();
		CommandSender creator = this.getCreator();
		if (creator != null)
		{
			ret.add(creator);
		}
		ret.add(Bukkit.getConsoleSender());
		return ret;
	}
	
	public void sendInfoMessage(String message)
	{
		for (CommandSender recipient : this.getInfoRecipients())
		{
			recipient.sendMessage(message);
		}
	}
	
	public static String descMinMax(int min, int max)
	{
		if (min == max)
		{
			return String.valueOf(min);
		}
		return ""+min+"to"+max;
	}
	
	public String descDelay()
	{
		return descMinMax(this.getMindelay(), this.getMaxdelay());
	}
	
	public String descInterval()
	{
		return descMinMax(this.getMininterval(), this.getMaxinterval());
	}
	
	public String descPosLength()
	{
		if (this.getLength() < 1)
		{
			return "endless";
		}
		return ""+this.getPos()+"/"+this.getLength();
	}
	
	public String getDescOneLine()
	{
		StringBuilder sb = new StringBuilder();
		
		if ( ! this.isPermanent())
		{
			sb.append("<silver>V ");
		}
		
		sb.append(this.isRunning() ? "<green>" : "<red>");
		sb.append(this.getId());
		sb.append(' ');
		
		sb.append(this.createdByConsole() ? "<gray>*CONSOLE*" : "<silver>"+this.getCreatorName());
		sb.append(' ');
		
		sb.append("<pink>");
		sb.append(this.descDelay());
		sb.append(' ');
		
		sb.append("<gold>");
		sb.append(this.descInterval());
		sb.append(' ');
		
		sb.append("<aqua>");
		sb.append(this.descPosLength());
		
		return sb.toString();
	}
	
	public static String formatCommand(String raw)
	{
		Entry<String, String> parts = Txt.divideOnFirstSpace(raw);
		String one = parts.getKey();
		String two = parts.getValue();
		
		String ret = "<c>/"+one;
		if (two != null)
		{
			ret += " <p>"+two;
		}
		
		return ret;
	}
	
	public List<String> getFormatedCommandList()
	{
		List<String> ret = new ArrayList<String>();
		int idx = 0;
		for (String cmd : this.cmds)
		{
			idx++;
			ret.add("<pink>"+idx+". "+formatCommand(cmd));
		}
		return ret;
	}
	
	public List<String> getDescMultiLine()
	{
		List<String> ret = new ArrayList<String>();
		
		ret.add("<k>Permanent: <v>"+this.isPermanent());
		ret.add("<k>Running: "+(this.isRunning() ? "<green>YES" : "<red>NO"));
		ret.add("<k>Creator: "+(this.createdByConsole() ? "<gray>*CONSOLE*" : "<silver>"+this.getCreatorName()));
		ret.add("<k>Delay: <pink>"+this.descDelay());
		ret.add("<k>Interval: <gold>"+this.descInterval());
		ret.add("<k>Pos/Length: <aqua>"+this.descPosLength());
		ret.add("<k>Commands:");
		ret.addAll(this.getFormatedCommandList());
		
		return ret;
	}
}
