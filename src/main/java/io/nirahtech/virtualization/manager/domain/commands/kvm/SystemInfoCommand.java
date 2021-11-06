package io.nirahtech.virtualization.manager.domain.commands.kvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.nirahtech.virtualization.manager.domain.commands.CommandResult;
import io.nirahtech.virtualization.manager.domain.entities.SystemInfo;

public final class SystemInfoCommand extends KvmCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemInfoCommand.class);

    public SystemInfoCommand() {
        super("virsh", "sysinfo");
    }

    @Override
    public CommandResult execute() {
        LOGGER.info("[KVM] A KVM command will be executed: " + this.getCommandWithArguments());
        final CommandResult commandResult = super.execute();
        final String outputAsXML = commandResult.getOutput().toString();
        final SystemInfo systemInfo = SystemInfo.parseXML(outputAsXML);
        return new CommandResult(commandResult.getExitCode(), systemInfo, commandResult.getError());
    }
}
