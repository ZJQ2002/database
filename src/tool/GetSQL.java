package tool;

import java.sql.*;

public class GetSQL {
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;
    static String URL = "jdbc:mysql:///bank_management_system?useSSL=false";
    static String userName = "sa";
    static String userPwd = "20020223@ZjQ";

    public static void Connect(){
        try {
            con = DriverManager.getConnection(URL, userName, userPwd);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }

    public static boolean loan(String Lsano, String Lano, float Lamount, String Ldate, String Ldp){
        String Lno = "";
        try {
            ps = con.prepareStatement("select 贷款表.Lno from 贷款表");
            rs = ps.executeQuery();
            while(rs.next()){
                Lno = rs.getString(1);
                Lno = String.valueOf(Integer.parseInt(Lno) + 1);
            }
            ps = con.prepareStatement("insert into 贷款表 values('" + Lno + "','" + Lsano + "','" + Lano
                    + "','" + Lamount + "','" + Ldate + "','" + Ldp + "','" +  "未审批" + "')");
            int i = ps.executeUpdate();
            return i > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean transfer(String TRsano, String TRano, float TRamount, String TRdate, String TRobacc){
        String TRno = "";
        try {
            ps = con.prepareStatement("select 转账表.TRno from 转账表");
            rs = ps.executeQuery();
            while(rs.next()){
                TRno = rs.getString(1);
                TRno = String.valueOf(Integer.parseInt(TRno) + 1);
            }
            ps = con.prepareStatement("insert into 转账表 values('" + TRno + "','" + TRsano + "','" + TRano
                    + "','" + TRamount + "','" + TRdate + "','" + TRobacc + "')");
            int i = ps.executeUpdate();
            return i > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean withdraw(String Wsano, String Wano, String Wtype, String Wdate, float Wamount){
        String Wno = "";
        try {
            ps = con.prepareStatement("select 取款表.Wno from 取款表");
            rs = ps.executeQuery();
            while(rs.next()){
                Wno = rs.getString(1);
                Wno = String.valueOf(Integer.parseInt(Wno) + 1);
            }
            if(Wtype.equals("活期")){
                ps = con.prepareStatement("insert into 取款表 values('" + Wno + "','" + Wsano + "','" + Wano
                        + "','" + Wtype + "','" + Wdate + "'," + Wamount + ")");
            }
            int i = ps.executeUpdate();
            return i > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deposit(String DEano, String DEsano, String DEtype, float DEamount, String DEdate, String DEtime){
        String DEno = "";
        try {
            ps = con.prepareStatement("select 存款表.DEno from 存款表");
            rs = ps.executeQuery();
            while (rs.next()) {
                DEno = rs.getString(1);
                DEno = String.valueOf(Integer.parseInt(DEno) + 1);
            }
            if(DEtype.equals("活期")) {
                ps = con.prepareStatement("insert into 存款表 values('" + DEno + "','" + DEsano + "','" + DEano
                        + "','" + DEtype + "','" + DEamount + "','" + DEdate + "'," + null + ")");
            }else if(DEtype.equals("定期")){
                ps = con.prepareStatement("insert into 存款表 values('" + DEno + "','" + DEsano + "','" + DEano
                        + "','" + DEtype + "','" + DEamount + "','" + DEdate + "','" + DEtime + "')");
            }
            int i = ps.executeUpdate();
            return i > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static String getLoanInfo(String Ano, int columnIndex){
        try{
            ps = con.prepareStatement("select * from 贷款表 where Lano=?" + " and Lacc='未审批'");
            ps.setString(1,Ano);
            rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString(columnIndex);
            }else return null;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getInfo(String tableName, String ID, int columnIndex){
        try{
            ps = con.prepareStatement("select * from " + tableName);
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equals(ID)){
                    return rs.getString(columnIndex);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateInfo(String tableName, String ID, String newValue){
        try{
            if(tableName.equals("银行管理员表")) {
                ps = con.prepareStatement("update 银行管理员表 set Mpwd=? where Mno=?");
                ps.setString(1,newValue);
                ps.setString(2,ID);
            }else if(tableName.equals("贷款表")){
                ps = con.prepareStatement("update 贷款表 set Lacc=? where Lano=? and Lacc='未审批'");
                ps.setString(1,newValue);
                ps.setString(2,ID);
            }
            int i = ps.executeUpdate();
            return i > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean queryManager(String userName, String password){
        try{
            ps = con.prepareStatement("select * from 银行管理员表 where Mno=? and Mpwd=?");
            ps.setString(1,userName);
            ps.setString(2,password);
            rs = ps.executeQuery();
            return rs.next();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean queryTeller(String userName, String password){
        try{
            ps = con.prepareStatement("select * from 银行柜员表 where Tno=? and Tpwd=?");
            ps.setString(1,userName);
            ps.setString(2,password);
            rs = ps.executeQuery();
            return rs.next();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean queryClient(String userName, String password){
        try{
            ps = con.prepareStatement("select * from 账户表 where Ano=? and Apwd=?");
            ps.setString(1,userName);
            ps.setString(2,password);
            rs = ps.executeQuery();
            return rs.next();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
