package com.eazyftw.advancedreplay.api;

import com.eazyftw.advancedreplay.replaysystem.data.ActionData;
import com.eazyftw.advancedreplay.replaysystem.data.types.PacketData;
import com.eazyftw.advancedreplay.replaysystem.replaying.Replayer;

import java.util.List;

public interface IReplayHook {

	List<PacketData> onRecord(String playerName);

	void onPlay(ActionData data, Replayer replayer);
}
