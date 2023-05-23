package io.nirahtech.kvmmanager.adapters.inputs.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.function.Supplier;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import io.nirahtech.kvmmanager.domain.Hypervisor;
import io.nirahtech.kvmmanager.domain.VirtualMachine;

public class KvmManagerWindow extends JFrame implements UserInterface {

    private final DefaultListModel<Hypervisor> kvmManagerModel = new DefaultListModel<>();
    private final String[] columnsHeader = {
            "Name", "Status", "CPU Usage", "Memory Usage", "Uptime"
    };
    // private String[][] data = { { "TEST", "DOWN", "0.0", "0", "NC" } };
    private Hypervisor selectedHypervisor = null;
    private final DefaultTableModel virtualMachinesModel = new DefaultTableModel(null, this.columnsHeader);

    public KvmManagerWindow() {

    }

    private JPanel buildKvmManagersPanel() {

        Supplier<Void> connectToHypervisorSupplier = () -> {
            this.selectedHypervisor = new Hypervisor();
            this.kvmManagerModel.addElement(this.selectedHypervisor);

            while (this.virtualMachinesModel.getRowCount() > 0) {
                this.virtualMachinesModel.removeRow(0);
            }
            for (VirtualMachine vm : this.selectedHypervisor.getVirtualMachines()) {
                String[] row = {
                        vm.getId(),
                        vm.getName(),
                        "0",
                        "0",
                        "ND"
                };
                this.virtualMachinesModel.addRow(row);
            }

            return null;
        };

        final JPanel master = new JPanel();
        final JLabel title = new JLabel("KVM Managers");
        final JPanel header = new JPanel();
        final JPanel footer = new JPanel();
        final JList<Hypervisor> list = new JList<>(this.kvmManagerModel);
        list.setCellRenderer(new HypervisorRenderer());
        final JScrollPane body = new JScrollPane(list);
        final JButton addButton = new JButton("+ Add");
        final JButton deleteButton = new JButton("- Delete");
        addButton.setPreferredSize(new Dimension(100, 30));
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(event -> {
            KvmConnectorWindow connection = new KvmConnectorWindow();
            connection.addEventListenerOnConnect(connectToHypervisorSupplier);
            connection.run();
        });

        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        master.setLayout(new BorderLayout());
        master.setAlignmentY(0.0f);
        master.setMinimumSize(new Dimension(220, 100));
        header.setBackground(Color.decode("#000000"));
        title.setForeground(Color.decode("#ffffff"));
        title.setFont(title.getFont().deriveFont(title.getFont().getStyle() | Font.BOLD));
        header.add(title);
        footer.add(addButton, BorderLayout.WEST);
        footer.add(deleteButton, BorderLayout.EAST);
        master.add(header, BorderLayout.NORTH);
        master.add(body, BorderLayout.CENTER);
        master.add(footer, BorderLayout.SOUTH);
        return master;
    }

    private JPanel buildActiondPanel() {
        final JPanel master = new JPanel();
        final JLabel title = new JLabel("Actions");
        final JPanel header = new JPanel();
        final JScrollPane body = new JScrollPane();
        master.setLayout(new BorderLayout());
        master.setAlignmentY(0.0f);
        master.setMinimumSize(new Dimension(100, 100));
        header.setBackground(Color.decode("#000000"));
        title.setForeground(Color.decode("#ffffff"));
        title.setFont(title.getFont().deriveFont(title.getFont().getStyle() | Font.BOLD));
        header.add(title);
        master.add(header, BorderLayout.NORTH);
        master.add(body, BorderLayout.CENTER);
        return master;
    }

    private JPanel buildChekPointsPanel() {
        final JPanel master = new JPanel();
        final JLabel title = new JLabel("Check Points");
        final JPanel header = new JPanel();
        final JScrollPane body = new JScrollPane();
        master.setLayout(new BorderLayout());
        master.setAlignmentY(0.0f);
        master.setMinimumSize(new Dimension(100, 100));
        header.setBackground(Color.decode("#000000"));
        title.setForeground(Color.decode("#ffffff"));
        title.setFont(title.getFont().deriveFont(title.getFont().getStyle() | Font.BOLD));
        header.add(title);
        master.add(header, BorderLayout.NORTH);
        master.add(body, BorderLayout.CENTER);
        return master;
    }

    private JPanel buildVirtualMachineDetailsPanel() {
        final JPanel master = new JPanel();
        final JLabel title = new JLabel("Details");
        final JPanel header = new JPanel();
        final JScrollPane body = new JScrollPane();
        master.setLayout(new BorderLayout());
        master.setAlignmentY(0.0f);
        master.setMinimumSize(new Dimension(100, 100));
        header.setBackground(Color.decode("#000000"));
        title.setForeground(Color.decode("#ffffff"));
        title.setFont(title.getFont().deriveFont(title.getFont().getStyle() | Font.BOLD));
        header.add(title);
        master.add(header, BorderLayout.NORTH);
        master.add(body, BorderLayout.CENTER);
        return master;
    }

    private JPanel buildVirtualMachinesPanel() {
        final JPanel master = new JPanel();
        final JLabel title = new JLabel("Virtual Machines");
        final JPanel header = new JPanel();
        final JTable table = new JTable(this.virtualMachinesModel);
        final JScrollPane body = new JScrollPane(table);
        table.setDefaultEditor(Object.class, null);
        master.setLayout(new BorderLayout());
        master.setAlignmentY(0.0f);
        master.setMinimumSize(new Dimension(100, 100));
        header.setBackground(Color.decode("#000000"));
        title.setForeground(Color.decode("#ffffff"));
        title.setFont(title.getFont().deriveFont(title.getFont().getStyle() | Font.BOLD));
        header.add(title);
        master.add(header, BorderLayout.NORTH);
        master.add(body, BorderLayout.CENTER);
        return master;
    }

    private JPanel buildStatusBarPanel() {
        final JPanel master = new JPanel();
        final JLabel status = new JLabel("KVM Manager is running...");
        status.setBackground(Color.RED);
        master.add(status, BorderLayout.WEST);
        return master;
    }

    private void build() {

        JSplitPane checkPointsSplitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                this.buildChekPointsPanel(),
                this.buildVirtualMachineDetailsPanel());

        JSplitPane virutalMachinesSplitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                this.buildVirtualMachinesPanel(),
                checkPointsSplitPane);

        JSplitPane actionsSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                virutalMachinesSplitPane,
                this.buildActiondPanel());

        JSplitPane kvmManagersSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                this.buildKvmManagersPanel(),
                actionsSplitPane);

        this.add(kvmManagersSplitPane, BorderLayout.CENTER);
        this.add(this.buildStatusBarPanel(), BorderLayout.SOUTH);

    }

    private void generateFakeData() {

        this.kvmManagerModel.addElement(new Hypervisor());
        this.kvmManagerModel.addElement(new Hypervisor());
        this.kvmManagerModel.addElement(new Hypervisor());
        this.kvmManagerModel.addElement(new Hypervisor());
        this.kvmManagerModel.addElement(new Hypervisor());
        this.kvmManagerModel.addElement(new Hypervisor());
        this.kvmManagerModel.addElement(new Hypervisor());

    }

    @Override
    public void run() {

        try {
            // On change le look and feel en cours
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            // On applique ce nouveau look à la fenêtre intégral
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        this.setTitle("Kernel-base Virtual Machine Manager");
        this.setSize(1920 / 2, 1080 / 2);
        this.build();
        this.generateFakeData();
        this.setMinimumSize(new Dimension(800, 600));
        this.setVisible(true);
    }

}
