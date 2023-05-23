package io.nirahtech.kvmmanager.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Hypervisor implements Identifiable {
    private static int totalCreated = 0;
    private final HostIdentifier identifier;
    private final String name;
    private final List<VirtualMachine> virtualMachines = new ArrayList<>();

    public Hypervisor() {
        this.identifier = new HypervisorIdentifier(UUID.randomUUID().toString().split("-")[0]);
        this.name = String.format("HPVSR-%s", ++totalCreated);

        for (int counter = 0; counter < 10; counter++) {
            this.virtualMachines.add(new VirtualMachine());
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return this.identifier.getId();
    }

    public List<VirtualMachine> getVirtualMachines() {
        return this.virtualMachines;
    }

}
