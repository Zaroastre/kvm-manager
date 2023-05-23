package io.nirahtech.kvmmanager.adapters.inputs.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class KvmConnectorWindow extends JFrame implements UserInterface {

    private int paddingHeight = 5;
    private int paddingWidth = 5;

    private Supplier<Void> onClickHandler = null;

    public void addEventListenerOnConnect(Supplier<Void> callback) {
        this.onClickHandler = callback;
    }

    public KvmConnectorWindow() {
        super();
    }

    private JPanel buildConnectionPanel() {
        final JPanel master = new JPanel();
        final JLabel title = new JLabel("Select a computer");
        final JPanel header = new JPanel();
        final JPanel body = new JPanel();
        final JLabel hostLabel = new JLabel("Host");
        final JTextField host = new JTextField("127.0.0.1");
        final JLabel portLabel = new JLabel("Port");
        SpinnerNumberModel portModel = new SpinnerNumberModel(22.0, 1.0, 65535.0, 1.0);
        final JSpinner port = new JSpinner(portModel);
        final JLabel usernameLabel = new JLabel("Username");
        final JTextField username = new JTextField("root");
        final JLabel keyLabel = new JLabel("Public Key");
        final JTextField publicKey = new JTextField();
        final JButton connect = new JButton("Connect");
        master.setLayout(new BorderLayout());
        master.setAlignmentY(0.0f);
        body.setLayout(new GridLayout(5, 2));
        body.setAlignmentY(0.0f);
        master.setMinimumSize(new Dimension(100, 100));
        header.setBackground(Color.decode("#000000"));
        title.setForeground(Color.decode("#ffffff"));
        title.setFont(title.getFont().deriveFont(title.getFont().getStyle() | Font.BOLD));
        body.setBorder(
                new EmptyBorder(this.paddingHeight, this.paddingWidth, this.paddingHeight, this.paddingWidth));
        hostLabel.setBorder(
                new EmptyBorder(this.paddingHeight, this.paddingWidth, this.paddingHeight, this.paddingWidth));
        portLabel.setBorder(
                new EmptyBorder(this.paddingHeight, this.paddingWidth, this.paddingHeight, this.paddingWidth));
        usernameLabel.setBorder(
                new EmptyBorder(this.paddingHeight, this.paddingWidth, this.paddingHeight, this.paddingWidth));
        keyLabel.setBorder(
                new EmptyBorder(this.paddingHeight, this.paddingWidth, this.paddingHeight, this.paddingWidth));
        connect.addActionListener((event) -> {
            if (this.onClickHandler != null) {
                this.onClickHandler.get();
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
        });
        header.add(title);

        body.add(hostLabel);
        body.add(host);
        body.add(portLabel);
        body.add(port);
        body.add(usernameLabel);
        body.add(username);
        body.add(keyLabel);
        body.add(publicKey);
        body.add(connect);
        master.add(header, BorderLayout.NORTH);
        master.add(body, BorderLayout.CENTER);
        return master;

    }

    private final void build() {
        this.add(this.buildConnectionPanel());
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

        this.setTitle("KVMM Connector");
        this.setSize(300, 200);
        this.build();
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setVisible(true);
    }
}
