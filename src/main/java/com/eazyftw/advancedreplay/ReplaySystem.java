package com.eazyftw.advancedreplay;


import com.eazyftw.advancedreplay.filesystem.ConfigManager;
import com.eazyftw.advancedreplay.filesystem.saving.DatabaseReplaySaver;
import com.eazyftw.advancedreplay.filesystem.saving.DefaultReplaySaver;
import com.eazyftw.advancedreplay.filesystem.saving.ReplaySaver;
import com.eazyftw.advancedreplay.replaysystem.Replay;
import com.eazyftw.advancedreplay.replaysystem.utils.ReplayCleanup;
import com.eazyftw.advancedreplay.utils.LogUtils;
import com.eazyftw.advancedreplay.utils.ReplayManager;
import com.eazyftw.advancedreplay.utils.Updater;
import org.bukkit.plugin.java.JavaPlugin;

public class ReplaySystem extends JavaPlugin {

	public final static String PREFIX = "§8[§3Replay§8] §r§7";
	public static ReplaySystem instance;
	public static Updater updater;

	public static ReplaySystem getInstance() {
		return instance;
	}

	@Override
	public void onDisable() {
		ReplayManager.activeReplays.values().stream().filter(Replay::isRecording).forEach(replay -> replay.getRecorder().stop(ConfigManager.SAVE_STOP));
	}

	@Override
	public void onEnable() {
		instance = this;
		long start = System.currentTimeMillis();

		LogUtils.log("Loading Replay v" + getDescription().getVersion() + " by " + getDescription().getAuthors().get(0));

		ReplayManager.register();
		ConfigManager.loadConfigs();

		ReplaySaver.register(ConfigManager.USE_DATABASE ? new DatabaseReplaySaver() : new DefaultReplaySaver());

		updater = new Updater();

		if (ConfigManager.CLEANUP_REPLAYS > 0) ReplayCleanup.cleanupReplays();

		LogUtils.log("Finished (" + (System.currentTimeMillis() - start) + "ms)");
	}
}
