package com.eazyftw.advancedreplay.commands.replay;

import com.eazyftw.advancedreplay.ReplaySystem;
import com.eazyftw.advancedreplay.commands.AbstractCommand;
import com.eazyftw.advancedreplay.commands.SubCommand;
import com.eazyftw.advancedreplay.filesystem.ConfigManager;
import com.eazyftw.advancedreplay.filesystem.saving.DatabaseReplaySaver;
import com.eazyftw.advancedreplay.filesystem.saving.DefaultReplaySaver;
import com.eazyftw.advancedreplay.filesystem.saving.IReplaySaver;
import com.eazyftw.advancedreplay.filesystem.saving.ReplaySaver;
import com.eazyftw.advancedreplay.replaysystem.Replay;
import com.eazyftw.advancedreplay.utils.LogUtils;
import com.eazyftw.advancedreplay.utils.fetcher.Consumer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReplayMigrateCommand extends SubCommand {

	private List<String> options = Arrays.asList("file", "database");

	public ReplayMigrateCommand(AbstractCommand parent) {
		super(parent, "migrate", "Migrate all replays", "migrate <File|Database>", false);

		this.setEnabled(false);
	}

	@Override
	public boolean execute(CommandSender cs, Command cmd, String label, String[] args) {
		if (args.length != 2) return false;

		String option = args[1].toLowerCase();
		if (options.contains(option)) {
			IReplaySaver migrationSaver = null;

			if (option.equalsIgnoreCase("file") && ReplaySaver.replaySaver instanceof DatabaseReplaySaver) {
				migrationSaver = new DefaultReplaySaver();

			} else if (option.equalsIgnoreCase("database") && ReplaySaver.replaySaver instanceof DefaultReplaySaver) {
				ConfigManager.USE_DATABASE = true;
				ConfigManager.loadData(false);

				migrationSaver = new DatabaseReplaySaver();
			} else {
				cs.sendMessage(ReplaySystem.PREFIX + "§cYou can't migrate to the same system.");
				return true;
			}

			cs.sendMessage(ReplaySystem.PREFIX + "§7Migrating replays to §e" + option);
			for (String replayName : ReplaySaver.getReplays()) {
				this.migrate(replayName, migrationSaver);
			}


		} else {
			cs.sendMessage(ReplaySystem.PREFIX + "§cInvalid argument. " + options.stream().collect(Collectors.joining("|", "<", ">")));
		}

		return true;
	}

	private void migrate(String replayName, IReplaySaver saver) {

		ReplaySaver.load(replayName, new Consumer<Replay>() {

			@Override
			public void accept(Replay replay) {
				try {
					if (!saver.replayExists(replayName)) {
						LogUtils.log("Migrating " + replayName + "...");

						saver.saveReplay(replay);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	@Override
	public List<String> onTab(CommandSender cs, Command cmd, String label, String[] args) {
		return options.stream()
				.filter(option -> StringUtil.startsWithIgnoreCase(option, args[1]))
				.collect(Collectors.toList());
	}

}
