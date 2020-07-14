package com.eazyftw.advancedreplay.utils;

import com.eazyftw.advancedreplay.ReplaySystem;
import com.eazyftw.advancedreplay.commands.replay.ReplayCommand;
import com.eazyftw.advancedreplay.listener.ReplayListener;
import com.eazyftw.advancedreplay.replaysystem.Replay;

import java.util.HashMap;

public class ReplayManager {

	public static HashMap<String, Replay> activeReplays = new HashMap<String, Replay>();

	public static void register() {
		registerEvents();
		registerCommands();
	}

	private static void registerEvents() {
		new ReplayListener().register();
	}

	private static void registerCommands() {
		ReplaySystem.getInstance().getCommand("replay").setExecutor(new ReplayCommand());
	}

}
