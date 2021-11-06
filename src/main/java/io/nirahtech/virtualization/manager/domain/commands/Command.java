package io.nirahtech.virtualization.manager.domain.commands;

/**
 * Interface that represents a system command.
 * 
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
public interface Command {

    /**
     * Execute the command with the given configuration.
     * @return Result of the command.
     */
    CommandResult execute(); 
}
