package com.eazyftw.advancedreplay.commands.replay;

import com.eazyftw.advancedreplay.ReplaySystem;
import com.eazyftw.advancedreplay.commands.AbstractCommand;
import com.eazyftw.advancedreplay.commands.MessageFormat;
import com.eazyftw.advancedreplay.commands.SubCommand;

public class ReplayCommand extends AbstractCommand {

    public ReplayCommand() {
        super("Replay", ReplaySystem.PREFIX + "AdvancedReplay §ev" + ReplaySystem.getInstance().getDescription().getVersion(), "replay.command");
    }

    @Override
    protected MessageFormat setupFormat() {
        return new MessageFormat()
                .overview("§6/{command} {args} §7 - {desc}")
                .syntax(ReplaySystem.PREFIX + "Usage: §6/{command} {args}")
                .permission(ReplaySystem.PREFIX + "§cInsufficient permissions")
                .notFound(ReplaySystem.PREFIX + "§7Command not found.");
    }

    @Override
    protected SubCommand[] setupCommands() {

        return new SubCommand[]{new ReplayStartCommand(this),
                new ReplayStopCommand(this).addAlias("save"),
                new ReplayPlayCommand(this),
                new ReplayDeleteCommand(this),
                new ReplayLeaveCommand(this),
                new ReplayInfoCommand(this),
                new ReplayListCommand(this),
                new ReplayReloadCommand(this),
                new ReplayReformatCommand(this),
                new ReplayMigrateCommand(this)};
    }

}
