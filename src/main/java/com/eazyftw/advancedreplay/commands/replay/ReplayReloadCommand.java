package com.eazyftw.advancedreplay.commands.replay;


import com.eazyftw.advancedreplay.ReplaySystem;
import com.eazyftw.advancedreplay.commands.AbstractCommand;
import com.eazyftw.advancedreplay.commands.SubCommand;
import com.eazyftw.advancedreplay.filesystem.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ReplayReloadCommand extends SubCommand {

	public ReplayReloadCommand(AbstractCommand parent) {
		super(parent, "reload", "Reloads the config", "reload", false);
	}

	@Override
	public boolean execute(CommandSender cs, Command cmd, String label, String[] args) {
		ConfigManager.reloadConfig();
		cs.sendMessage(ReplaySystem.PREFIX + "§aSuccessfully reloaded the configuration.");
		return true;
	}


}
