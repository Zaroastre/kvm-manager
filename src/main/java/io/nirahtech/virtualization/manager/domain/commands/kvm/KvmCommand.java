package io.nirahtech.virtualization.manager.domain.commands.kvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.nirahtech.virtualization.manager.domain.commands.Command;
import io.nirahtech.virtualization.manager.domain.commands.CommandResult;

/**
 * Class that represents a KVM command.
 * 
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
public abstract class KvmCommand implements Command {

    private final String[] commandWithArguments;

    /**
     * Default constructor for a KVM command.
     * @param arguments Command with arguments.
     */
    protected KvmCommand(String... arguments) {
        this.commandWithArguments = arguments;
    }

    /**
     * Get command with parameters.
     * @return Command with arguments.
     */
    public String[] getCommandWithArguments() {
        return this.commandWithArguments;
    }

    @Override
    public CommandResult execute() {
        CommandResult result = null;
        final ProcessBuilder processBuilder = new ProcessBuilder(this.commandWithArguments);
        final StringBuilder output = new StringBuilder();
        final StringBuilder error = new StringBuilder();
        int exitcode = 1;
        try {
            final Process process = processBuilder.start();
            try {
                exitcode = process.waitFor();
            } catch (InterruptedException exception) {
                exitcode = -128;
                error.append(exception.getMessage());
            }

            final BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream())); 
            String outputLine;
            while ((outputLine = outputReader.readLine()) != null) { 
                output.append(outputLine);
            }

            final BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream())); 
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) { 
                error.append(errorLine);
            }
        } catch (IOException exception) {
            exitcode = -127;
            error.append(exception.getMessage());
        }

        final String outputString = output.toString().trim().isEmpty() ? null : output.toString();
        final String errorString = error.toString().trim().isEmpty() ? null : error.toString();
        
        return new CommandResult(exitcode, outputString, errorString);
    }
}
