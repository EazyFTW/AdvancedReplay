package com.eazyftw.advancedreplay.listener;


import com.eazyftw.advancedreplay.ReplaySystem;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class AbstractListener implements Listener {

	protected ReplaySystem plugin;

	public AbstractListener() {
		this.plugin = ReplaySystem.instance;
	}

	public void register() {
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
	}

	public void unregister() {
		HandlerList.unregisterAll(this);
	}


}
