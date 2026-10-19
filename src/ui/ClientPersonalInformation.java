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

public class ClientPersonalInformation implements ActionListener {
    JFrame jf = new JFrame();
    final int WIDTH = 300;
    final int HEIGHT = 500;
    String Ano = "";

    JLabel AbalanceLabel = new JLabel("账户余额:  ");
    JLabel AdateLabel = new JLabel("开户日期:  ");
    JLabel pLabel = new JLabel("新密码:  ");
    JTextField pField = new JTextField(15);
    JButton modifyBtn = new JButton("修改");
    JButton returnBtn = new JButton("返回");

    public ClientPersonalInformation(String Ano) throws IOException {
        this.Ano = Ano;
        jf.setTitle("账户" + Ano);
        jf.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-WIDTH)/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setIconImage(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\IconOfBank.jpg")));
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\BackImage.jpg")));
        jf.setResizable(false);

        Box vBox = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();

        GetSQL.Connect();
        AbalanceLabel.setText(AbalanceLabel.getText() + GetSQL.getInfo("账户表",Ano,4));
        AdateLabel.setText(AdateLabel.getText() + GetSQL.getInfo("账户表",Ano,5));
        box1.add(AbalanceLabel);
        box2.add(AdateLabel);
        box3.add(pLabel);
        box3.add(pField);
        modifyBtn.addActionListener(this::actionPerformed);
        box4.add(modifyBtn);
        box4.add(Box.createHorizontalStrut(40));
        returnBtn.addActionListener(this::actionPerformed);
        box4.add(returnBtn);

        vBox.add(Box.createVerticalStrut(80));
        vBox.add(box1);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(box2);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(box3);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(box4);

        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == modifyBtn){
            GetSQL.Connect();
            if(GetSQL.updateInfo("账户表",Ano,pField.getText())){
                JOptionPane.showMessageDialog(jf, "修改成功");
            }else JOptionPane.showMessageDialog(jf, "修改失败");
        }
        if(e.getSource() == returnBtn){
            jf.dispose();
            try {
                new ClientLogin(Ano);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
