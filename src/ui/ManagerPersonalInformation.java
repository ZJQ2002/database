package ui;

import tool.BackGroundPanel;
import tool.GetSQL;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ManagerPersonalInformation implements ActionListener {
    JFrame jf = new JFrame();
    final int WIDTH = 300;
    final int HEIGHT = 500;
    String Mno = "";

    JLabel MnoLabel = new JLabel("管理员编号:  ");
    JLabel MnameLabel = new JLabel("管理员姓名:  ");
    JLabel MphoneLabel = new JLabel("管理员电话:  ");
    JLabel MsexLabel = new JLabel("管理员性别:  ");
    JLabel pLabel = new JLabel("新密码:  ");
    JTextField pField = new JTextField(15);
    JButton modifyBtn = new JButton("修改");
    JButton returnBtn = new JButton("返回");

    public ManagerPersonalInformation(String Mno) throws IOException {
        this.Mno = Mno;
        jf.setTitle("管理员" + Mno);
        jf.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-WIDTH)/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setIconImage(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\IconOfBank.jpg")));
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\BackImage.jpg")));
        jf.setResizable(false);

        Box vBox = Box.createVerticalBox();
        Box infoBox = Box.createVerticalBox();
        Box modificationBox = Box.createHorizontalBox();
        Box btnBox = Box.createHorizontalBox();

        GetSQL.Connect();
        MnoLabel.setText(MnoLabel.getText() + GetSQL.getInfo("银行管理员表",Mno,1));
        MnameLabel.setText(MnameLabel.getText() + GetSQL.getInfo("银行管理员表",Mno,3));
        MphoneLabel.setText(MphoneLabel.getText() + GetSQL.getInfo("银行管理员表",Mno,4));
        MsexLabel.setText(MsexLabel.getText() + GetSQL.getInfo("银行管理员表",Mno,5));
        infoBox.add(this.MnoLabel);
        infoBox.add(Box.createVerticalStrut(15));
        infoBox.add(this.MnameLabel);
        infoBox.add(Box.createVerticalStrut(15));
        infoBox.add(this.MphoneLabel);
        infoBox.add(Box.createVerticalStrut(15));
        infoBox.add(this.MsexLabel);
        Box infoBox_1 = Box.createHorizontalBox();
        infoBox_1.add(infoBox);

        modificationBox.add(pLabel);
        modificationBox.add(pField);

        modifyBtn.addActionListener(this::actionPerformed);
        btnBox.add(modifyBtn);
        btnBox.add(Box.createHorizontalStrut(40));
        returnBtn.addActionListener(this::actionPerformed);
        btnBox.add(returnBtn);

        vBox.add(Box.createVerticalStrut(60));
        vBox.add(infoBox_1);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(modificationBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(btnBox);

        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == modifyBtn){
            GetSQL.Connect();
            if(GetSQL.updateInfo("银行管理员表",Mno,pField.getText())){
                JOptionPane.showMessageDialog(jf, "修改成功");
            }else JOptionPane.showMessageDialog(jf, "修改失败");
        }
        if(e.getSource() == returnBtn){
            jf.dispose();
            try {
                new ManagerLogin(Mno);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
