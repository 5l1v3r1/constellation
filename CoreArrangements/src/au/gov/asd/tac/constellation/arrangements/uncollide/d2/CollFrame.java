/*
 * Copyright 2010-2019 Australian Signals Directorate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package au.gov.asd.tac.constellation.arrangements.uncollide.d2;

import au.gov.asd.tac.constellation.arrangements.uncollide.d2.BoundingBox2D.Box2D;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author algol
 */
public class CollFrame extends javax.swing.JFrame {

    private static final String UNCOLLIDE_THREAD_NAME = "Uncollide";

    private static interface Callback {

        void call(List<Box2D> boxes, boolean isEnd);
    }

    public CollFrame() {
        initComponents();
    }

    private void repaintPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                drawPanel.repaint();
            }
        });
    }

    private void uncollide(final Orb2D[] orbs, final int iter, final Callback callback) {
        new Thread() {
            @Override
            public void run() {
                setName(UNCOLLIDE_THREAD_NAME);
                final long t0 = System.currentTimeMillis();

                float prevCollisions = -1;
                boolean isEnd = false;
                for (int i = 0; i < iter && !isEnd; i++) {
                    final Box2D bb = BoundingBox2D.getBox(orbs);
                    final QuadTree qt = new QuadTree(bb);
                    for (final Orb2D orb : orbs) {
                        qt.insert(orb);
                    }

                    // Vary the padding to see if we can make things use fewer steps.
                    //                    final float padding = 1;
//                    final float padding = 9-i%10;
//                    final float padding = prevCollisions<0 ? 1 : 1+2*prevCollisions/orbs.length;
                    final float padding = prevCollisions < 0 ? 1 : 1 + prevCollisions / orbs.length;
//                    final float padding = 0;

                    int totalCollided = 0;
                    for (final Orb2D orb : orbs) {
                        final int collided = qt.uncollide(orb, padding);
                        totalCollided += collided;
                    }

                    final long elapsed = System.currentTimeMillis() - t0;
                    final String msg = String.format("step %3d; pad %f; collisions %5d; %4.1fsec", i, padding, totalCollided, elapsed / 1000.0);
                    infoLabel.setText(msg);

                    prevCollisions = totalCollided;
                    isEnd = totalCollided == 0;

                    final List<Box2D> boxes = qt.getSubs();
//                    cp.setBoxes(boxes, isEnd);
//                    repaintPanel();
                }
            }
        }.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        uncollideButton = new javax.swing.JButton();
        drawButton = new javax.swing.JButton();
        uncollide1Button = new javax.swing.JButton();
        drawPanel = new CirclePanel();
        infoLabel = new javax.swing.JLabel();
        nSpinner = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        uncollideButton.setText("Uncollide");
        uncollideButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                uncollideButtonActionPerformed(evt);
            }
        });

        drawButton.setText("Draw");
        drawButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                drawButtonActionPerformed(evt);
            }
        });

        uncollide1Button.setText("Uncollide1");
        uncollide1Button.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                uncollide1ButtonActionPerformed(evt);
            }
        });

        drawPanel.setBackground(new java.awt.Color(255, 255, 255));
        drawPanel.setPreferredSize(new java.awt.Dimension(512, 512));
        drawPanel.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                drawPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        drawPanelLayout.setVerticalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
        );

        infoLabel.setFont(new java.awt.Font("Courier New", 0, 11)); // NOI18N

        nSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1000), Integer.valueOf(100), null, Integer.valueOf(100)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(drawButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uncollideButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uncollide1Button)
                .addGap(7, 7, 7))
            .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uncollide1Button, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(uncollideButton)
                        .addComponent(drawButton)
                        .addComponent(nSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void drawButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_drawButtonActionPerformed
    {//GEN-HEADEREND:event_drawButtonActionPerformed
        infoLabel.setText("");
        final int n = ((Integer) nSpinner.getModel().getValue()).intValue();
        ((CirclePanel) drawPanel).redo(n);
        drawPanel.repaint();
    }//GEN-LAST:event_drawButtonActionPerformed

    private void uncollideButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_uncollideButtonActionPerformed
    {//GEN-HEADEREND:event_uncollideButtonActionPerformed
        final CirclePanel cp = (CirclePanel) drawPanel;
        final Orb2D[] orbs = cp.getOrbs();

        uncollide(orbs, 10000, new Callback() {
            @Override
            public void call(final List<Box2D> boxes, final boolean isEnd) {
                cp.setBoxes(boxes, isEnd);
                repaintPanel();
            }
        });
    }//GEN-LAST:event_uncollideButtonActionPerformed

    private void drawPanelMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_drawPanelMouseClicked
    {//GEN-HEADEREND:event_drawPanelMouseClicked
//        final int x = evt.getX();
//        final int y = evt.getY();
//        System.out.printf("Click at %d,%d\n", x, y);
//        final CirclePanel cp = (CirclePanel)drawPanel;
//        final Orb2D[] orb = cp.getOrbs();
//        final ArrayList<Orb2D> clicked = new ArrayList<Orb2D>();
//        for(final Orb2D circle : orb)
//        {
//            if(circle.contains(x, y))
//            {
//                clicked.add(circle);
//                System.out.printf("contains %s\n", circle);
//            }
//        }

//        if(!clicked.isEmpty())
//        {
//            final Box2D bb = BoundingBox2D.getBB(orb);
//            final QuadTree qt = new QuadTree(bb);
//            for(final Orb2D circle : orb)
//            {
//                qt.insert(circle);
//            }
//            final int collided = qt.uncollide(clicked.get(0), 0);
//            System.out.printf("Collisions: %d\n", collided);
//            drawPanel.repaint();
//        }
    }//GEN-LAST:event_drawPanelMouseClicked

    private void uncollide1ButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_uncollide1ButtonActionPerformed
    {//GEN-HEADEREND:event_uncollide1ButtonActionPerformed
        final CirclePanel cp = (CirclePanel) drawPanel;
        final Orb2D[] orbs = cp.getOrbs();
        uncollide(orbs, 1, new Callback() {
            @Override
            public void call(final List<Box2D> boxes, final boolean isEnd) {
                cp.setBoxes(boxes, isEnd);
                repaintPanel();
            }
        });
    }//GEN-LAST:event_uncollide1ButtonActionPerformed

    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CollFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton drawButton;
    private javax.swing.JPanel drawPanel;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JSpinner nSpinner;
    private javax.swing.JButton uncollide1Button;
    private javax.swing.JButton uncollideButton;
    // End of variables declaration//GEN-END:variables
}
