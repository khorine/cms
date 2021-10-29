/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CmsPackage;

import java.awt.Image;
import java.awt.print.PrinterException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Horine
 */
public class MembersBySacramentalStatus extends javax.swing.JFrame {

    int insertedRows = 0;

    public MembersBySacramentalStatus() {
        initComponents();
        scaleIcon1();
        scaleIcon2();
        populateJTableFromDatabase();

    }

    Statement St1 = null;
    ResultSet Rs1 = null;
    Connection con = null;
    DefaultTableModel model;

    private void scaleIcon1() {
        ImageIcon icon1 = new ImageIcon("C:\\Users\\host\\CMS\\src\\images\\logo3.png");
        //Scale iamge to fit the jbutton
        Image img = icon1.getImage();
        Image imgScale = img.getScaledInstance(jLabel_ICON1.getWidth(), jLabel_ICON1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(imgScale);
        jLabel_ICON1.setIcon(scaledIcon1);
    }

    private void scaleIcon2() {
        ImageIcon icon2 = new ImageIcon("C:\\Users\\host\\CMS\\src\\images\\logo2.jpg");
        //Scale iamge to fit the jbutton
        Image img = icon2.getImage();
        Image imgScale = img.getScaledInstance(jLabel_ICON2.getWidth(), jLabel_ICON2.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(imgScale);
        jLabel_ICON2.setIcon(scaledIcon2);
    }

    private ArrayList<Members> membersList(String valueToSearch) {
        ArrayList<Members> membersList = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs = null;
        //Connection con;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "mysql");
            stmt = con.createStatement();
            //stmt = CmsConnection.getConnection().prepareStatement(selectAllSQLQuery);
            //stmt = con.createStatement();
            String searchQuery = "SELECT * FROM members WHERE SACRAMENTAL_STATUS LIKE '%" + valueToSearch + "%'";
            rs = stmt.executeQuery(searchQuery);

            //loop through the results
            while (rs.next()) {
                Members member = new Members();
                //populate User setters
                member.setID(rs.getInt("MEMBER_ID"));
                member.setFname(rs.getString("FIRST_NAME"));
                member.setMname(rs.getString("MIDDLE_NAME"));
                member.setSname(rs.getString("SURNAME"));
                member.setGender(rs.getString("GENDER"));
                member.setDob(rs.getString("DOB"));
                member.setVillage(rs.getString("VILLAGE"));
                member.setPhone(rs.getString("PHONE_NO"));
                member.setMarital(rs.getString("MARITAL_STATUS"));
                member.setOccupation(rs.getString("OCCUPATION"));
                member.setEmail(rs.getString("EMAIL"));
                member.setResidence(rs.getString("PLACE_OF_RESIDENCE"));
                member.setYrofAdm(rs.getString("ADMISSION_DATE"));
                member.setSacramental(rs.getString("SACRAMENTAL_STATUS"));
                member.setFather(rs.getString("FATHER_NAME"));
                member.setMother(rs.getString("MOTHER_NAME"));
                member.setMgroup(rs.getString("MEMBER_GROUP"));
                member.setMassign(rs.getString("MEMBER_ASSIGNMENT"));
                member.setChurch(rs.getString("CHURCH_NAME"));
                member.setPhoto(rs.getBytes("MEMBER_IMAGE"));
                membersList.add(member);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembersBySacramentalStatus.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //Close variables to prevent java.sql.SQLException: Java heap space error
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException sQLException) {
                sQLException.getMessage();
            }
        }
        return membersList;

    }

    //Populate JTable with data from database
    private void populateJTableFromDatabase() {
        ArrayList<Members> dataArray = membersList(jComboBox_GENDER.getSelectedItem().toString());
        model = (DefaultTableModel) jTable_MTable.getModel();
        //Clear JTable rows
        model.setRowCount(0);
        Object[] rows = new Object[19];

        //Loop through the arraylist to populate JTable
        for (int i = 0; i < dataArray.size(); i++) {
            rows[0] = dataArray.get(i).getID();
            rows[1] = dataArray.get(i).getFname();
            rows[2] = dataArray.get(i).getMname();
            rows[3] = dataArray.get(i).getSname();
            rows[4] = dataArray.get(i).getGender();
            rows[5] = dataArray.get(i).getDob();
            rows[6] = dataArray.get(i).getVillage();
            rows[7] = dataArray.get(i).getPhone();
            rows[8] = dataArray.get(i).getMarital();
            rows[9] = dataArray.get(i).getOccupation();
            rows[10] = dataArray.get(i).getEmail();
            rows[11] = dataArray.get(i).getResidence();
            rows[12] = dataArray.get(i).getYrofAdm();
            rows[13] = dataArray.get(i).getSacramental();
            rows[14] = dataArray.get(i).getFather();
            rows[15] = dataArray.get(i).getMother();
            rows[16] = dataArray.get(i).getMgroup();
            rows[17] = dataArray.get(i).getMassign();
            rows[18] = dataArray.get(i).getChurch();
            model.addRow(rows);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_ICON1 = new javax.swing.JLabel();
        jLabel_ICON2 = new javax.swing.JLabel();
        jComboBox_GENDER = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_MTable = new javax.swing.JTable();
        jButton_EXPORT = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(150, 40, 27));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MEMBERS DATA");

        jComboBox_GENDER.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_GENDER.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baptism", "Holy Communion", "Confirmation", "Matrimony", "Other" }));
        jComboBox_GENDER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_GENDERActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(118, 93, 105));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SELECT SACRAMENTAL STATUS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel_ICON1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_GENDER, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 357, Short.MAX_VALUE)
                .addComponent(jLabel_ICON2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_ICON1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_GENDER, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel_ICON2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTable_MTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MEMBER_ID", "FIRSTNAME", "MIDDLE_NAME", "SURNAME", "GENDER", "DOB", "VILLAGE", "PHONE_NO", "MARITAL_STATUS", "OCCUPATION", "EMAIL", "RESIDENCE", "ADMISSION_DATE", "SACRAMENTAL_STATUS", "FATHER_NAME", "MOTHER_NAME", "MEMBER_GROUP", "MEMBER_ASSIGNMENT", "CHURCH_NAME"
            }
        ));
        jScrollPane1.setViewportView(jTable_MTable);

        jButton_EXPORT.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jButton_EXPORT.setForeground(new java.awt.Color(0, 153, 51));
        jButton_EXPORT.setText("Export (Excel)");
        jButton_EXPORT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EXPORTActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton3.setText("Print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(372, 372, 372)
                .addComponent(jButton_EXPORT, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(242, 242, 242)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_EXPORT)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    DefaultTableModel ImportDataFromExcelModel;
    private void jComboBox_GENDERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_GENDERActionPerformed
        populateJTableFromDatabase();
    }//GEN-LAST:event_jComboBox_GENDERActionPerformed

    private void jButton_EXPORTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EXPORTActionPerformed
        // TODO add your handling code here:
        //Choose location for saving Excel file
        JFileChooser excelFileChooserExport = new JFileChooser("C:\\Users\\host\\Desktop");
        //Change Dialog box title
        excelFileChooserExport.setDialogTitle("Save As");
        //Only filter files with these extensions "xls","xlsx","xslm"
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xslm");
        excelFileChooserExport.setFileFilter(fnef);
        int excelChooser = excelFileChooserExport.showSaveDialog(null);

        //Check if save button is clicked
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            XSSFWorkbook excelJTableExporter = new XSSFWorkbook();
            XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");
            for (int i = 0; i < model.getRowCount(); i++) {
                XSSFRow excelRow = excelSheet.createRow(i);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    XSSFCell excelCell = excelRow.createCell(j);

                    excelCell.setCellValue(model.getValueAt(i, j).toString());
                }
            }
            FileOutputStream excelFOU;
            BufferedOutputStream excelBOU;
            try {
                excelFOU = new FileOutputStream(excelFileChooserExport.getSelectedFile() + ".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                excelBOU.close();
                excelJTableExporter.close();
                JOptionPane.showMessageDialog(null, "Data export successful.!!");
            } catch (IOException iOException) {
            }
        }
    }//GEN-LAST:event_jButton_EXPORTActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            boolean printJTable = jTable_MTable.print();
            if (!printJTable) {
                JOptionPane.showMessageDialog(null, "Unable to Print.!!");
            }
        } catch (PrinterException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MembersBySacramentalStatus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_EXPORT;
    private javax.swing.JComboBox jComboBox_GENDER;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_ICON1;
    private javax.swing.JLabel jLabel_ICON2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_MTable;
    // End of variables declaration//GEN-END:variables

    FileInputStream excelFIS = null;
    BufferedInputStream excelBIS = null;
    XSSFWorkbook excelImportWorkbook = null;

}
