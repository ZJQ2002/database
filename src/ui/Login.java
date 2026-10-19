package ui;

import tool.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Login implements ActionListener{
    JFrame jf = new JFrame("银行储蓄系统登陆界面");
    final int WIDTH = 500;
    final int HEIGHT = 300;

    JRadioButton jrbManager = new JRadioButton("管理员");
    JRadioButton jrbTeller = new JRadioButton("柜员");
    JRadioButton jrbClient = new JRadioButton("客户");
    JButton loginBtn = new JButton("登陆");

    JTextField uField = new JTextField(20);
    JPasswordField pField = new JPasswordField(20);

    public Login() throws IOException {
        jf.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-WIDTH)/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setIconImage(ImageIO.read(new File("D:\\development tools\\Java\\Projects\\database\\images\\IconOfBank.jpg")));
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("D:\\development tools\\Java\\Projects\\database\\images\\BackImage.jpg")));
        jf.setResizable(false);

        Box vBox = Box.createVerticalBox();
        //组装账号
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("账号:");
        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        //组装密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密码:");
        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        //组装按钮
        Box jrbBox = Box.createHorizontalBox();
        jrbBox.add(jrbManager);
        jrbBox.add(Box.createHorizontalStrut(20));
        jrbBox.add(jrbTeller);
        jrbBox.add(Box.createHorizontalStrut(20));
        jrbBox.add(jrbClient);

        Box btnBox = Box.createHorizontalBox();
        loginBtn.addActionListener(this::actionPerformed);
        btnBox.add(loginBtn);

        vBox.add(Box.createVerticalStrut(40));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(jrbBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(btnBox);

        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginBtn){
            GetSQL.Connect();
            if(uField.getText().isEmpty() && pField.getText().isEmpty()){
                JOptionPane.showMessageDialog(jf,"请输入账号和密码");
            }else if(uField.getText().isEmpty()){
                JOptionPane.showMessageDialog(jf,"请输入账号");
            }else if(pField.getText().isEmpty()){
                JOptionPane.showMessageDialog(jf,"请输入密码");
            }else {
                String user = uField.getText();
                String pwd = pField.getText();
                if(jrbManager.isSelected()){
                    if(GetSQL.queryManager(user,pwd)) {
                        jf.dispose();
                        JOptionPane.showMessageDialog(jf, "欢迎您");
                        try {
                            new ManagerLogin(user);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(jf, "账号或密码错误");
                    }
                }else if(jrbTeller.isSelected()){
                    if(GetSQL.queryTeller(user,pwd)) {
                        jf.dispose();
                        JOptionPane.showMessageDialog(jf, "欢迎您");
                    }else{
                        JOptionPane.showMessageDialog(jf, "账号或密码错误");
                    }
                }else if(jrbClient.isSelected()){
                    if(GetSQL.queryClient(user,pwd) && (!GetSQL.getInfo("账户表",user,6).equals("冻结"))) {
                        jf.dispose();
                        JOptionPane.showMessageDialog(jf, "欢迎您");
                        try {
                            new ClientLogin(user);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(jf, "账号或密码错误");
                    }
                }
            }
        }
    }



}
