package com.eazyftw.advancedreplay.commands.replay;


import com.eazyftw.advancedreplay.ReplaySystem;
import com.eazyftw.advancedreplay.commands.AbstractCommand;
import com.eazyftw.advancedreplay.commands.SubCommand;
import com.eazyftw.advancedreplay.filesystem.saving.ReplaySaver;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ReplayDeleteCommand extends SubCommand {

	public ReplayDeleteCommand(AbstractCommand parent) {
		super(parent, "delete", "Deletes a replay", "delete <Name>", false);
	}

	@Override
	public boolean execute(CommandSender cs, Command cmd, String label, String[] args) {
		if (args.length != 2) return false;

		String name = args[1];

		if (ReplaySaver.exists(name)) {
			ReplaySaver.delete(name);
			cs.sendMessage(ReplaySystem.PREFIX + "§aSuccessfully deleted replay.");
		} else {
			cs.sendMessage(ReplaySystem.PREFIX + "§cReplay not found.");
		}

		return true;
	}

	@Override
	public List<String> onTab(CommandSender cs, Command cmd, String label, String[] args) {
		return ReplaySaver.getReplays().stream()
				.filter(name -> StringUtil.startsWithIgnoreCase(name, args.length > 1 ? args[1] : null))
				.collect(Collectors.toList());
	}


}
