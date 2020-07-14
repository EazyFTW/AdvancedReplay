package com.eazyftw.advancedreplay.filesystem.saving;

import com.eazyftw.advancedreplay.replaysystem.Replay;
import com.eazyftw.advancedreplay.utils.fetcher.Consumer;

import java.util.List;

public interface IReplaySaver {

	void saveReplay(Replay replay);

	void loadReplay(String replayName, Consumer<Replay> consumer);

	boolean replayExists(String replayName);

	void deleteReplay(String replayName);

	List<String> getReplays();
}
