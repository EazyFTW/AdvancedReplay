package com.eazyftw.advancedreplay.commands.replay;


import com.eazyftw.advancedreplay.ReplaySystem;
import com.eazyftw.advancedreplay.commands.AbstractCommand;
import com.eazyftw.advancedreplay.commands.SubCommand;
import com.eazyftw.advancedreplay.filesystem.saving.ReplaySaver;
import com.eazyftw.advancedreplay.replaysystem.Replay;
import com.eazyftw.advancedreplay.replaysystem.replaying.ReplayHelper;
import com.eazyftw.advancedreplay.utils.fetcher.Consumer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ReplayPlayCommand extends SubCommand {

	public ReplayPlayCommand(AbstractCommand parent) {
		super(parent, "play", "Starts a recorded replay", "play <Name>", true);
	}

	@Override
	public boolean execute(CommandSender cs, Command cmd, String label, String[] args) {
		if (args.length != 2) return false;

		String name = args[1];

		final Player p = (Player) cs;

		if (ReplaySaver.exists(name) && !ReplayHelper.replaySessions.containsKey(p.getName())) {
			p.sendMessage(ReplaySystem.PREFIX + "Loading replay §e" + name + "§7...");
			try {
				ReplaySaver.load(args[1], new Consumer<Replay>() {

					@Override
					public void accept(Replay replay) {
						p.sendMessage(ReplaySystem.PREFIX + "Replay loaded. Duration §e" + (replay.getData().getDuration() / 20) + "§7 seconds.");
						replay.play(p);
					}
				});

			} catch (Exception e) {
				e.printStackTrace();

				p.sendMessage(ReplaySystem.PREFIX + "§cError while loading §o" + name + ".replay. §r§cCheck console for more details.");
			}
		} else {
			p.sendMessage(ReplaySystem.PREFIX + "§cReplay not found.");
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
