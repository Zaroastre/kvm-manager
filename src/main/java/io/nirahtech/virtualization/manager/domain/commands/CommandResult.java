package io.nirahtech.virtualization.manager.domain.commands;

import java.io.Serializable;

/**
 * Class that represents a command result.
 * 
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
public final class CommandResult implements Serializable {
    private final int exitCode;
    private final Object output;
    private final String error;

    /**
     * Default constructor of the command result.
     * @param exitCode Exit code of the command.
     * @param output Result (standard output) of the command.
     */
    public CommandResult(final int exitCode, final Object output, final String error) {
        this.exitCode = exitCode;
        this.output = output;
        this.error = error;
    }

    /**
     * Get the result (standard output) of the command.
     * @return The result of the command.
     */
    public final Object getOutput() {
        return this.output;
    }

    /**
     * Get the exit code of the command.
     * @return The exit code of the command.
     */
    public final int getExitCode() {
        return this.exitCode;
    }

    /**
     * Get the error of the command.
     * @return The error of the command.
     */
    public final String getError() {
        return this.error;
    }
}
