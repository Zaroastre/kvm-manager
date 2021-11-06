package io.nirahtech.virtualization.manager.domain.commands.kvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.nirahtech.virtualization.manager.domain.commands.CommandResult;

public final class ListVirtualMachinesCommand extends KvmCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListVirtualMachinesCommand.class);

    public ListVirtualMachinesCommand() {
        super("virsh", "list");
    }

    @Override
    public CommandResult execute() {
        LOGGER.info("[KVM] A KVM command will be executed: " + this.getCommandWithArguments());
        return super.execute();
    }
}
