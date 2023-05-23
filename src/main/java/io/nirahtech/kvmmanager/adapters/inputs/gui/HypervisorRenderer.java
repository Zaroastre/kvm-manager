package io.nirahtech.kvmmanager.adapters.inputs.gui;

import java.awt.Component;
import java.awt.Image;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import io.nirahtech.kvmmanager.domain.Hypervisor;

public class HypervisorRenderer extends JLabel implements ListCellRenderer<Hypervisor> {

    private int paddingHeight = 5;
    private int paddingWidth = 5;

    public HypervisorRenderer() {
        super();
        this.setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Hypervisor> list, Hypervisor hypervisor, int index,
            boolean isSelected, boolean cellHasFocus) {
        ImageIcon icon = new ImageIcon(HypervisorRenderer.class.getResource("/icons/hypervisor/hypervisor.png"));
        Image image = icon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image); // transform it back
        this.setIcon(icon);
        this.setText(hypervisor.getName());
        this.setBorder(new EmptyBorder(this.paddingHeight, this.paddingWidth, this.paddingHeight, this.paddingWidth));

        if (isSelected) {
            this.setBackground(Color.decode("#b71c1c"));
            // this.setBackground(Color.decode("#000000"));
            this.setForeground(Color.decode("#ffffff"));
        } else {
            this.setBackground(Color.decode("#ffffff"));
            this.setForeground(Color.decode("#000000"));
        }

        return this;
    }

}
