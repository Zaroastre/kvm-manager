package io.nirahtech.kvmmanager.domain;

public abstract class HostIdentifier implements Identifiable {
    protected final String id;

    protected HostIdentifier(final String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
