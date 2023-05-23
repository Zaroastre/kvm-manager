package io.nirahtech.kvmmanager.domain;

import java.util.UUID;

public final class VirtualMachine implements Identifiable {
    private static int totalCreated = 0;
    private final HostIdentifier identifier;
    private final String name;
    private State state;

    public VirtualMachine() {
        this.identifier = new VirtualMachineIdentifier(UUID.randomUUID().toString().split("-")[0]);
        this.name = String.format("VM-%s", ++totalCreated);
        this.state = State.OFF;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return this.identifier.getId();
    }

    public State getState() {
        return state;
    }

}
