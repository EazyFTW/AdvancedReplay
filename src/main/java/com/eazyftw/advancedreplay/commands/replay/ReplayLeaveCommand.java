package com.eazyftw.advancedreplay.commands.replay;


import com.eazyftw.advancedreplay.ReplaySystem;
import com.eazyftw.advancedreplay.commands.AbstractCommand;
import com.eazyftw.advancedreplay.commands.SubCommand;
import com.eazyftw.advancedreplay.replaysystem.replaying.ReplayHelper;
import com.eazyftw.advancedreplay.replaysystem.replaying.Replayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplayLeaveCommand extends SubCommand {

	public ReplayLeaveCommand(AbstractCommand parent) {
		super(parent, "leave", "Leave your Replay", "leave", true);
	}

	@Override
	public boolean execute(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = (Player) cs;

		if (ReplayHelper.replaySessions.containsKey(p.getName())) {
			Replayer replayer = ReplayHelper.replaySessions.get(p.getName());

			replayer.stop();

		} else {
			p.sendMessage(ReplaySystem.PREFIX + "§cYou need to play a Replay first");
		}

		return true;
	}


}
