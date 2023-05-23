package io.nirahtech.kvmmanager;

import io.nirahtech.kvmmanager.adapters.inputs.gui.KvmManagerWindow;
import io.nirahtech.kvmmanager.adapters.inputs.gui.UserInterface;

public class KvmManagerApplication {
    public static void main(String[] args) {
        UserInterface gui = new KvmManagerWindow();
        gui.run();
    }
}
