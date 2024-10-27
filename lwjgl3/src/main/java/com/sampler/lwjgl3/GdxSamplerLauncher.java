package com.sampler.lwjgl3;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.utils.reflect.ClassReflection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GdxSamplerLauncher extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int CELL_WIDTH = 200;
    private static final int CANVAS_WIDTH = WIDTH - CELL_WIDTH;

    private LwjglAWTCanvas lwjglAWTCanvas;
    private JList sampleList;
    private JPanel controlPanel;

    public GdxSamplerLauncher() throws HeadlessException {
        init();
    }

    private void init() {
        createControlPanel();

        Container container = getContentPane();
        container.add(controlPanel, BorderLayout.WEST);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (lwjglAWTCanvas != null) {
                    lwjglAWTCanvas.stop();
                }
            }
        });

        setTitle(GdxSamplerLauncher.class.getSimpleName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // tell window (jframe) to resize and layout out components
        pack();
        setVisible(true);
    }

    private void createControlPanel() {
        controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 1;

        sampleList = new JList(
            new String[] {"com.sampler.InputPollingSample"}
        );
        sampleList.setFixedCellWidth(CELL_WIDTH);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sampleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if(e.getClickCount() == 2) {
                   launchSelectedSample();
               }
            }
        });

        JScrollPane scrollPane = new JScrollPane(sampleList);
        controlPanel.add(scrollPane, c);

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;

        JButton launchButton = new JButton("Launch Sample");
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchSelectedSample();
            }
        });

        controlPanel.add(launchButton, c);
    }

    private void launchSelectedSample() {
        String sampleName = sampleList.getSelectedValue().toString();

        if (sampleName == null || sampleName.isEmpty()) {
            System.out.println("Sample name is empty, cannot launch");
            return;
        }

        launchSample(sampleName);
    }

    private void launchSample(String name) {
        System.out.println("launching sample name = " + name);

        Container container = getContentPane();

        if (lwjglAWTCanvas != null) {
            lwjglAWTCanvas.stop();
            container.remove(lwjglAWTCanvas.getCanvas());
        }

        ApplicationListener sample;

        try {
            // get class object by name
            Class<ApplicationListener> clazz = ClassReflection.forName(name);

            // create new instance of our sample class
            sample = ClassReflection.newInstance(clazz);
        }
        catch (Exception e) {
            throw new RuntimeException("Cannot create sample with name = " + name, e);
        }

        lwjglAWTCanvas = new LwjglAWTCanvas(sample);
        lwjglAWTCanvas.getCanvas().setSize(CANVAS_WIDTH, HEIGHT);
        container.add(lwjglAWTCanvas.getCanvas(), BorderLayout.CENTER);

        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GdxSamplerLauncher();
            }
        });
    }
}
