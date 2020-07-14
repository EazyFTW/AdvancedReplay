package com.eazyftw.advancedreplay.commands.replay;


import com.eazyftw.advancedreplay.ReplaySystem;
import com.eazyftw.advancedreplay.commands.AbstractCommand;
import com.eazyftw.advancedreplay.commands.SubCommand;
import com.eazyftw.advancedreplay.filesystem.saving.DefaultReplaySaver;
import com.eazyftw.advancedreplay.filesystem.saving.ReplaySaver;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ReplayReformatCommand extends SubCommand {

	public ReplayReformatCommand(AbstractCommand parent) {
		super(parent, "reformat", "Reformat the replays", "reformat", false);

		this.setEnabled(false);
	}

	@Override
	public boolean execute(CommandSender cs, Command cmd, String label, String[] args) {
		cs.sendMessage(ReplaySystem.PREFIX + "Reformatting Replay files...");
		((DefaultReplaySaver) ReplaySaver.replaySaver).reformatAll();
		cs.sendMessage("§aFinished. Check console for details.");

		return true;
	}


}
