package jtabbedpane;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import java.nio.charset.StandardCharsets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author mxtur
 */
public final class MyFrame extends javax.swing.JFrame {

    Component comp;
    DefaultTableModel md;
    List<Configurations> conflist = new ArrayList<>();
    ArrayList<DefaultTableModel> model = new ArrayList<>();
    int rowindex;
    private static int deviceCheckInterval = 4;

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(21);
    private static final Logger logger = Logger.getLogger(MyFrame.class.getName());
    private static Handler fileHandler;

    private final List<List> beepsLists = new ArrayList<>();

    private FileService fileService;
    private LogView logView;

    public static Timer timer;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT [%4$-7s] %3$s - %5$s %n");
    }

    public MyFrame() throws IOException {
        initComponents();

        timer = new Timer(2000, e -> {
            if (jCheckBoxMenuItem1.getState()) {
                Toolkit.getDefaultToolkit().beep();
            }
        });

        fileService = new FileService(this);
        logView = new LogView();

        fileHandler = new FileHandler("logs.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);

        logger.setLevel(Level.ALL);
        logger.info("Start");
        this.md = (DefaultTableModel) this.jTable2.getModel();
        this.jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getSource() == jTable2) {
                    int row = MyFrame.this.jTable2.rowAtPoint(evt.getPoint());
                    if (row >= 0) {
                        MyFrame.this.rowindex = row;
                        MyFrame.this.jButton2.setEnabled(true);
                        MyFrame.this.jButton3.setEnabled(true);
                    }
                } else if (evt.getSource() != jTable2) {
                    jTable2.clearSelection();
                }

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    MyFrame.this.jTabbedPane1.setSelectedIndex(row + 1);
                }
            }
        });

        changeTable(this.jTable2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Мониторинг подсистем TANGO");
        setMinimumSize(new java.awt.Dimension(750, 600));
        setName("Frame1"); // NOI18N

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setName(""); // NOI18N

        jButton1.setText("Новая подсистема");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Подсистемы", "Сост"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setShowHorizontalLines(true);
        jTable2.setShowVerticalLines(true);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(1).setMinWidth(40);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTable2.getColumnModel().getColumn(1).setMaxWidth(40);
        }

        jButton2.setText("Удалить подсистему");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Переименовать подсистему");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 0, 0));
        jButton4.setText("Сбросить оповещение");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 529, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(190, 190, 190))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addGap(43, 43, 43))
        );

        jTabbedPane1.addTab("Сводка", jPanel1);

        jMenu1.setText("Файл");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Открыть конфигурацию");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Сохранить конфигурацию");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Сохранить конфигурацию как");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem4.setText("Выход");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Параметры");

        jMenuItem5.setText("Интервал обновления");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Звуковое оповещение");
        jMenu2.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void processWindowEvent(WindowEvent we) {
        if (we.getID() == WindowEvent.WINDOW_CLOSING) {
            if ((!(fileService.calculateHash(conflist)).equals(fileService.originalConfigHash) && fileService.originalConfigHash != null)) {
                int option = JOptionPane.showConfirmDialog(this, "Файл не был сохранен. Вы хотите сохранить файл?",
                        "Подтверждение сохранения", 1);
                if (option == 0) {
                    fileService.saveFile(conflist);
                } else if (option == 2) {
                    return;
                }
            }
            scheduler.shutdown();
            super.processWindowEvent(we);

        }
    }

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        int interval;
        String input = JOptionPane.showInputDialog(this, "Введите интервал времени в секундах:", deviceCheckInterval);

        if (input == null) {
            return;
        }

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Пустой ввод не допускается. Пожалуйста, введите интервал времени.");

            return;
        }

        try {
            interval = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Неверный формат ввода. Пожалуйста, введите целое число.");
            jMenuItem5ActionPerformed(evt);

            return;
        }
        if (interval <= 0) {
            JOptionPane.showMessageDialog(this, "Интервал времени должен быть положительным числом.");
            jMenuItem5ActionPerformed(evt);

            return;
        }
        deviceCheckInterval = interval;
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if ((!(fileService.calculateHash(conflist)).equals(fileService.originalConfigHash) && fileService.originalConfigHash != null)) {
            int option = JOptionPane.showConfirmDialog(this, "Файл не был сохранен. Вы хотите сохранить файл?",
                    "Подтверждение сохранения", 1);
            if (option == 0) {
                fileService.saveFile(conflist);
            } else if (option == 2) {
                return;
            }
        }
        scheduler.shutdown();
        System.exit(0);


    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        fileService.saveFileAs(conflist);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        fileService.saveFile(conflist);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        conflist = fileService.openFile(conflist);
        DefaultTableModel dtm = (DefaultTableModel) this.jTable2.getModel();
        dtm.setRowCount(0);
        OpenWasClicked();
        updateTable();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nm = JOptionPane.showInputDialog(this, "Введите название подсистемы");

        if (nm == null || nm.length() <= 0) {
            return;
        }

        for (int i = 0; i < jTable2.getRowCount(); i++) {
            String existingName = (String) jTable2.getValueAt(i, 0);
            if (nm.equals(existingName)) {
                Object[] options = {"Ввести другое", "Отмена"};
                int n = JOptionPane.showOptionDialog(this, "Это устройство уже есть в подсистеме",
                        "Ошибка", 1, 3, null, options, options[1]);

                if (n == 0) {
                    jButton1ActionPerformed(evt);
                    return;
                }
                if (n == 1) {
                    return;
                }
            }
        }

        Configurations conf = new Configurations(nm);
        this.conflist.add(conf);

        this.md.addRow(new Object[]{nm});

        SystemPanel sp = new SystemPanel(this.conflist, this.model, logger);
        this.jTabbedPane1.addTab(nm, sp);
        beepsLists.add(sp.beepList);
        this.jTabbedPane1.setSelectedIndex(this.jTabbedPane1.getTabCount() - 1);
        sp.index = this.jTabbedPane1.getSelectedIndex();
        scheduler.scheduleAtFixedRate(sp, 0L, deviceCheckInterval, TimeUnit.SECONDS);
        updateTable();
        logger.log(Level.INFO, "Была создана новая подсистема " + nm);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int res = JOptionPane.showConfirmDialog(this, "Вы уверены?");
        if (res == 0) {
            this.jTabbedPane1.removeTabAt(this.rowindex + 1);
            logger.log(Level.INFO,
                    "Была удалена подсистема " + ((Configurations) this.conflist.get(this.rowindex)).title);
            this.conflist.remove(this.rowindex);
            this.md.removeRow(this.rowindex);
            this.jButton2.setEnabled(false);
            this.jButton3.setEnabled(false);
            updateTable();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String nm = JOptionPane.showInputDialog(this, "Введите название подсистемы");

        if (nm == null || nm.length() <= 0) {
            return;
        }

        for (int i = 0; i < jTable2.getRowCount(); i++) {
            String existingName = (String) jTable2.getValueAt(i, 0);
            if (nm.equals(existingName)) {
                Object[] options = {"Ввести другое", "Отмена"};
                int n = JOptionPane.showOptionDialog(this, "Это устройство уже есть в подсистеме",
                        "Ошибка", 1, 3, null, options, options[1]);

                if (n == 0) {
                    jButton1ActionPerformed(evt);
                    return;
                }
                if (n == 1) {
                    return;
                }
            }
        }
        
        logger.addHandler(logView.editLogs((FileHandler)fileHandler,((Configurations) this.conflist.get(this.rowindex)).title, nm));

        logger.log(Level.INFO, "Подсистема была переименована с '"
                + ((Configurations) this.conflist.get(this.rowindex)).title + "' на '" + nm + "'");
        ((Configurations) this.conflist.get(this.rowindex)).title = nm;
        this.jTabbedPane1.setTitleAt(this.rowindex + 1, nm);
        this.jTable2.setValueAt(nm, this.rowindex, 0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String devs = "";
        for (List beep : beepsLists) {
            for (Object str : beep) {
                devs += str + "\n";
            }
        }
        setTimer(false);

        Object[] options = {"Ok", "Cancel"};

        JOptionPane.showOptionDialog(
                this,
                devs,
                "Устройства отключены",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        jButton4.setEnabled(false);

    }//GEN-LAST:event_jButton4ActionPerformed

    public void changeTable(JTable table) {
        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus,
                    int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if ("Подсистемы".equals(table.getColumnName(column))) {
                    table.setValueAt(((Configurations) MyFrame.this.conflist.get(row)).title, row, column);
                } else {
                    c.setBackground(((Configurations) MyFrame.this.conflist.get(row)).condition);
                }
                return c;
            }
        });
    }

    private static void updateTable() {
        jPanel1.repaint();
        jPanel1.revalidate();
    }

    public void OpenWasClicked() {
        this.model = new ArrayList<>();
        this.comp = this.jTabbedPane1.getComponentAt(0);
        this.jTabbedPane1.removeAll();
        this.jTabbedPane1.add(this.comp, "Сводка");
        beepsLists.clear();
        for (Configurations con : this.conflist) {
            this.md.addRow(new Object[]{con.title});
            SystemPanel sp = new SystemPanel(this.conflist, this.model, logger);
            this.jTabbedPane1.addTab(con.title, sp);
            beepsLists.add(sp.beepList);
            sp.index = this.jTabbedPane1.getTabCount() - 1;

            sp.OpenConfig();
            scheduler.scheduleAtFixedRate(sp, 0L, deviceCheckInterval, TimeUnit.SECONDS);
        }
    }

    public static JButton getButton() {
        return jButton4;
    }

    public static void setTimer(boolean bl) {
        if (bl) {
            timer.start();
        } else {
            timer.stop();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        // </editor-fold>

        // </editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    (new MyFrame()).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
            }
        });

        scheduler.scheduleAtFixedRate(() -> {
            SwingUtilities.invokeLater(() -> {
                updateTable();
            });
        }, 0L, 1L, TimeUnit.SECONDS);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private static javax.swing.JButton jButton4;
    private static javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private static javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
