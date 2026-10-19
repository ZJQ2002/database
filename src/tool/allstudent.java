package tool;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import java.sql.*;
import javax.swing.table.*;

class allstudent extends JFrame implements ActionListener {
    JMenu jm = new JMenu("信息");
    JMenuItem j1 = new JMenuItem("显示信息");
    JMenuItem j2 = new JMenuItem("退出");
    JMenuBar JBar = new JMenuBar();

    allstudent() {
        super("学生信息记录表");
        setSize(540, 400);
        setLocation(320, 240);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JBar.add(jm);
        jm.add(j1);
        jm.add(j2);
        setJMenuBar(JBar);
        j1.addActionListener(this);
        j2.addActionListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == j2)
            this.dispose();
        else if (e.getSource() == j1) {
            int i, j, row;
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ce) {
            }
            try {
                Connection con = DriverManager
                        .getConnection("jdbc:sqlserver://localhost:1433;databaseName=银行储蓄系统", "sa", "20020223@ZjQ");
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("select * from 银行管理员表");
                displayResultSet(rs);
                rs.close();
                stm.close();
                //con.close();
            } catch (SQLException se) {
            }
        }
    }

    public void displayResultSet(ResultSet rs) throws SQLException {
        boolean moreRecords = rs.next();
        if (!moreRecords) {
            JOptionPane.showMessageDialog(null, "结果无记录", "无记录", JOptionPane.INFORMATION_MESSAGE);
            //return;
        }
        Vector rows = new Vector();
        Vector columnHeads = new Vector();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                columnHeads.addElement(rsmd.getColumnName(i));
            do {
                rows.addElement(getNextRow(rs, rsmd));
            } while (rs.next());
            JTable table = new JTable(rows, columnHeads);
            table.setSize(new Dimension(400, 400));
            JScrollPane scroller = new JScrollPane(table);
            Container c = getContentPane();
            c.add(scroller, BorderLayout.CENTER);
            c.validate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
        Vector currentRow = new Vector();
        for (int i = 1; i <= rsmd.getColumnCount(); i++)
            currentRow.addElement(rs.getString(i));
        return currentRow;
    }
}
