package com.eazyftw.advancedreplay.api;

import com.eazyftw.advancedreplay.filesystem.saving.IReplaySaver;
import com.eazyftw.advancedreplay.filesystem.saving.ReplaySaver;
import com.eazyftw.advancedreplay.replaysystem.Replay;
import com.eazyftw.advancedreplay.replaysystem.replaying.ReplayHelper;
import com.eazyftw.advancedreplay.utils.ReplayManager;
import com.eazyftw.advancedreplay.utils.fetcher.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReplayAPI {

	private static ReplayAPI instance;

	private HookManager hookManager;

	private ReplayAPI() {
		this.hookManager = new HookManager();
	}

	public static ReplayAPI getInstance() {
		if (instance == null) instance = new ReplayAPI();

		return instance;
	}

	public void registerHook(IReplayHook hook) {
		this.hookManager.registerHook(hook);
	}

	public void unregisterHook(IReplayHook hook) {
		this.hookManager.unregisterHook(hook);
	}

	public Replay recordReplay(String name, CommandSender sender, Player... players) {
		List<Player> toRecord = new ArrayList<Player>();

		if (players != null && players.length > 0) {
			toRecord = Arrays.asList(players);
		} else {
			for (Player all : Bukkit.getOnlinePlayers()) {
				toRecord.add(all);
			}
		}

		Replay replay = new Replay();
		if (name != null) replay.setId(name);
		replay.recordAll(toRecord, sender);

		return replay;
	}

	public void stopReplay(String name, boolean save) {
		if (ReplayManager.activeReplays.containsKey(name)) {
			Replay replay = ReplayManager.activeReplays.get(name);
			if (replay.isRecording()) replay.getRecorder().stop(save);
		}
	}

	public void playReplay(String name, Player watcher) {
		if (ReplaySaver.exists(name) && !ReplayHelper.replaySessions.containsKey(watcher.getName())) {
			ReplaySaver.load(name, new Consumer<Replay>() {

				@Override
				public void accept(Replay replay) {
					replay.play(watcher);

				}
			});
		}
	}

	public void registerReplaySaver(IReplaySaver replaySaver) {
		ReplaySaver.register(replaySaver);
	}

	public HookManager getHookManager() {
		return hookManager;
	}
}
