/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtabbedpane;

import fr.esrf.Tango.DevFailed;
import fr.esrf.TangoApi.Database;
import fr.esrf.TangoApi.DeviceInfo;
import fr.esrf.TangoApi.DeviceProxy;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.Toolkit;

/**
 *
 * @author mxtura
 */
public class SystemPanel
        extends JPanel
        implements Runnable {

    int rowindex;
    int index;
    List<Configurations> conflist;
    ArrayList<DefaultTableModel> model;
    private Logger logger;

    public SystemPanel(List<Configurations> conflist, ArrayList<DefaultTableModel> model, Logger logger) {
        initComponents();

        this.conflist = conflist;
        this.model = model;
        this.logger = logger;

        model.add((DefaultTableModel) this.jTable1.getModel());

        this.index = this.index;

        this.jTable1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.jTable1.setShowVerticalLines(true);
        this.jTable1.setShowHorizontalLines(true);

        this.jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = SystemPanel.this.jTable1.rowAtPoint(evt.getPoint());
                int col = SystemPanel.this.jTable1.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    SystemPanel.this.rowindex = row;
                    SystemPanel.this.jButton2.setEnabled(true);
                    SystemPanel.this.jButton3.setEnabled(true);
                } else {
                    SystemPanel.this.jButton2.setEnabled(false);
                    SystemPanel.this.jButton3.setEnabled(false);
                }
            }
        });

        this.jTable1.getTableHeader().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int column = jTable1.columnAtPoint(e.getPoint());

                boolean anySelected = false;
                for (int row = 0; row < jTable1.getRowCount(); row++) {
                    if ((Boolean) jTable1.getValueAt(row, column)) {
                        anySelected = true;
                        break;
                    }
                }

                for (int row = 0; row < jTable1.getRowCount(); row++) {
                    if (anySelected) {
                        if ((boolean) jTable1.getValueAt(row, column) != false) {
                            jTable1.setValueAt(false, row, column);
                        }
                    } else {
                        jTable1.setValueAt(true, row, column);

                    }

                }
                jTable1.repaint();
            }

        });

        changeTable(this.jTable1, 1);
        this.jTable1.getModel().addTableModelListener(new CheckBoxModelListener());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(500, 400));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jButton1.setText("�������� ����� ����������");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("�������� ����������");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(462, 402));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "�����", "����", "State", "Status", "����", "����"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jTable1.setMinimumSize(new java.awt.Dimension(300, 200));
        jTable1.setPreferredSize(new java.awt.Dimension(920, 544));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(1).setMinWidth(40);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(40);
            jTable1.getColumnModel().getColumn(2).setMinWidth(100);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(3).setMinWidth(50);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(400);
            jTable1.getColumnModel().getColumn(4).setMinWidth(45);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(45);
            jTable1.getColumnModel().getColumn(5).setMinWidth(45);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(45);
        }

        jButton3.setText("������� ����������");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("�������� ����");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void run() {
        CalcOfCondition();
        for (int i = 0; i < this.jTable1.getModel().getRowCount(); i++) {
            this.jTable1.getModel().setValueAt(CheckingAdress(this.jTable1.getModel().getValueAt(i, 0).toString())[1],
                    i, 2);
            this.jTable1.getModel().setValueAt(CheckingAdress(this.jTable1.getModel().getValueAt(i, 0).toString())[0],
                    i, 3);
        }
    }

    public class CheckBoxModelListener
            implements TableModelListener {

        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column == 4) {
                TableModel tmodel = (TableModel) e.getSource();
                String columnName = tmodel.getColumnName(column);
                Boolean checked = (Boolean) tmodel.getValueAt(row, column);
                if (!checked.booleanValue()) {
                    String msg = "���� ���� ����������� ��� ���������� " + ((Configurations) conflist
                            .get(index - 1)).devices.get(row).get(0);
                    loggit(Level.INFO, (String) ((Configurations) conflist.get(index - 1)).devices.get(row).get(0), msg);
                    ((ArrayList<Boolean>) ((Configurations) SystemPanel.this.conflist
                            .get(SystemPanel.this.index - 1)).devices.get(row)).set(1, Boolean.valueOf(false));
                } else {
                    String msg = "���������� ���� ����������� ��� ���������� " + ((Configurations) conflist
                            .get(index - 1)).devices.get(row).get(0);
                    loggit(Level.INFO, (String) ((Configurations) conflist.get(index - 1)).devices.get(row).get(0), msg);
                    ((ArrayList<Boolean>) ((Configurations) SystemPanel.this.conflist
                            .get(SystemPanel.this.index - 1)).devices.get(row)).set(1, Boolean.valueOf(true));
                }
            }
            if (column == 5) {
                TableModel tmodel = (TableModel) e.getSource();
                String columnName = tmodel.getColumnName(column);
                Boolean checked = (Boolean) tmodel.getValueAt(row, column);
                if (!checked.booleanValue()) {
                    String msg = "� ���������� '" + ((Configurations) conflist.get(index - 1)).devices.get(row).get(0) + "' � ���������� '"
                            + ((Configurations) conflist.get(index - 1)).title + "' ������������ ���������";
                    loggit(Level.INFO, (String) ((Configurations) conflist.get(index - 1)).devices.get(row).get(0), msg);
                    ((ArrayList<Boolean>) ((Configurations) SystemPanel.this.conflist
                            .get(SystemPanel.this.index - 1)).devices.get(row)).set(2, Boolean.valueOf(false));
                } else {
                    ((ArrayList<Boolean>) ((Configurations) SystemPanel.this.conflist
                            .get(SystemPanel.this.index - 1)).devices.get(row)).set(2, Boolean.valueOf(true));
                    String msg = "� ���������� '" + ((Configurations) conflist.get(index - 1)).devices.get(row).get(0) + "' � ���������� '"
                            + ((Configurations) conflist.get(index - 1)).title + "' ������������ ��������";
                    loggit(Level.INFO, (String) ((Configurations) conflist.get(index - 1)).devices.get(row).get(0), msg);
                    
                }
            }
        }
    }

    private void jButton1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nm = JOptionPane.showInputDialog(this, "������� ����� ����������");
        if (nm == null || nm.length() <= 0) {
            return;
        }

        try {
            Database db = new Database();
            DeviceInfo deviceInfo = db.get_device_info(nm);
        } catch (DevFailed e) {
            Object[] options = {"��������", "��������", "������"};
            int n = JOptionPane.showOptionDialog(this, "����� ������ ��� � ����. �� ����� ������ ������ ���� �����?",
                    "������", 1, 3, null, options, options[2]);

            if (n == 1) {
                jButton1ActionPerformed(evt);
                return;
            }
            if (n == 2) {
                return;
            }
        }

        ((Configurations) this.conflist.get(this.index - 1)).Add(nm);
        CreatingAddresses(nm, true, true);
        String msg = "��������� ����� ���������� '" + nm + "' � ���������� '"
                + ((Configurations) this.conflist.get(this.index - 1)).title + "'";
        loggit(Level.INFO,nm , msg);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel dm = (DefaultTableModel) this.jTable1.getModel();
        String nm = JOptionPane.showInputDialog(this, "������� ����� ����������", dm.getValueAt(this.rowindex, 0));

        if (nm == null || nm.length() <= 0) {
            return;
        }
        String msg = "���������� '" + dm.getValueAt(this.rowindex, 0) + "' � ���������� '"
                + ((Configurations) this.conflist.get(this.index - 1)).title + "' ���� �������� �� '" + nm + "'";
        loggit(Level.INFO, (String) dm.getValueAt(this.rowindex, 0), msg);

        ChangingAdress(dm, nm);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String subsystemName = ((Configurations) this.conflist.get(this.index - 1)).title;

        LogView logView = new LogView();
        logView.loadLogs(subsystemName);
        logView.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(ActionEvent evt) {
        int res = JOptionPane.showConfirmDialog(this, "�� �������?");
        if (res == 0) {
            ((DefaultTableModel) this.jTable1.getModel()).removeRow(this.rowindex);
            String msg = "������� ���������� '"
                    + ((ArrayList<String>) ((Configurations) this.conflist.get(this.index - 1)).devices
                            .get(this.rowindex)).get(0)
                    + "' �� ���������� '" + ((Configurations) this.conflist.get(this.index - 1)).title + "'";
            loggit(Level.INFO, ((ArrayList<String>) ((Configurations) this.conflist.get(this.index - 1)).devices
                    .get(this.rowindex)).get(0), msg);
            ((Configurations) this.conflist.get(this.index - 1)).DeleteAd(this.rowindex);
        } else {
            return;
        }
    }

    private void changeTable(JTable table, int column_index) {
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String st_val = table.getValueAt(row, 2).toString();
                if (st_val == "ON") {
                    c.setBackground(Color.GREEN);
                } else {

                    c.setBackground(Color.RED);
                }
                return c;
            }
        });
    }

    private void loggit(Level lv, String nm, String msg) {
        if ((boolean) ((Configurations) this.conflist.get(this.index - 1)).findDevice(nm).get(2)) {
            this.logger.log(lv, msg);
        }
    }

    public void CreatingAddresses(String nm, boolean cr, boolean lg) {
        String status = CheckingAdress(nm)[0];
        String state = CheckingAdress(nm)[1];

        Object[] data = {nm, "", state, status, Boolean.valueOf(cr), Boolean.valueOf(lg)};

        ((DefaultTableModel) this.model.get(this.index - 1)).addRow(data);
    }

    public String[] CheckingAdress(String nm) {
        String status = "";
        String state = "";

        DeviceProxy dev = null;

        try {
            dev = new DeviceProxy(nm);
            status = dev.status();
            state = dev.state().toString();
        } catch (DevFailed e) {

            if (dev != null) {
                if (status != "��� ����� � �����������" && status != "") {
                    String msg = "� ���������� '" + nm
                            + "' ��������� ������ c '��� ����� � �����������' �� '" + status + "'";
                    loggit(Level.INFO, nm, msg);

                }
                status = "��� ����� � �����������";
            } else {
                if (status != "��� ���������� � ����� �������" && status != "") {
                    String msg = "� ���������� '" + nm
                            + "' ��������� ������ c '��� ���������� � ����� �������' �� '" + status + "'";
                    loggit(Level.INFO, nm, msg);
                }
                status = "��� ���������� � ����� �������";
            }

            state = "no conn.";
        }

        return new String[]{status, state};
    }

    private void ChangingAdress(DefaultTableModel dm, String nm) {
        DeviceProxy dev = null;

        try {
            dev = new DeviceProxy(nm);

            dm.setValueAt(dev.state(), this.rowindex, 2);
            dm.setValueAt(dev.status(), this.rowindex, 3);
        } catch (DevFailed e) {

            if (dev != null) {
                dm.setValueAt("no conn.", this.rowindex, 2);
                dm.setValueAt("��� ����� � �����������", this.rowindex, 3);
            } else {
                Object[] options = {"��������", "��������", "������"};
                int n = JOptionPane.showOptionDialog(this,
                        "����� ������ ��� � ����. �� ����� ������ ������ ���� �����?", "������", 1, 3, null, options,
                        options[2]);

                if (n == 0) {
                    dm.setValueAt("��� ���������� � ����� �������", this.rowindex, 3);
                    dm.setValueAt("no conn.", this.rowindex, 2);
                } else {
                    if (n == 1) {
                        String nm1 = JOptionPane.showInputDialog(this, "������� ����� ����������", nm);
                        ChangingAdress(dm, nm1);
                        return;
                    }
                    if (n == 2) {
                        return;
                    }
                }

            }
        }
        ((Configurations) this.conflist.get(this.index - 1)).ChangeNameAd(this.rowindex, nm);
        dm.setValueAt(nm, this.rowindex, 0);
    }

    public void OpenConfig() {
        for (ArrayList<Object> al : ((Configurations) this.conflist.get(this.index - 1)).devices) {
            String n1 = (String) al.get(0);
            boolean n2 = (boolean) al.get(1);
            boolean n3 = (boolean) al.get(2);
            CreatingAddresses(n1, n2, n3);
        }
    }

    public void CalcOfCondition() {
        int green_flag = 0;
        int red_flag = 0;
        int red_count = 0;

        for (int i = 0; i < this.jTable1.getModel().getRowCount(); i++) {
            if (CheckingAdress(this.jTable1.getValueAt(i, 0).toString())[1] == "ON") {
                if (((Boolean) this.jTable1.getValueAt(i, 4)).booleanValue() == true) {
                    green_flag++;
                }
            } else {
                red_count++;

                if (((Boolean) this.jTable1.getValueAt(i, 4)).booleanValue() == true) {
                    if (((Configurations) this.conflist.get(this.index - 1)).condition != Color.RED) {
                        Toolkit.getDefaultToolkit().beep();
                    }
                    ((Configurations) this.conflist.get(this.index - 1)).condition = Color.RED;
                    return;
                }
                red_flag++;
            }
        }

        if (green_flag == ((Configurations) this.conflist.get(this.index - 1)).devices.size()) {
            ((Configurations) this.conflist.get(this.index - 1)).condition = Color.GREEN;

            return;
        }
        if (red_count == red_flag) {
            ((Configurations) this.conflist.get(this.index - 1)).condition = Color.YELLOW;
            return;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
