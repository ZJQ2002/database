package ui;

import tool.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ClientLogin implements ActionListener {
    JFrame jf = new JFrame();
    final int WIDTH = 300;
    final int HEIGHT = 500;
    String Ano = "";

    JButton jb1 = new JButton("个人信息助理");
    JButton jb2 = new JButton("查询业务");
    JButton jb3 = new JButton("资金业务");
    JButton jb4 = new JButton("退出");

    public ClientLogin(String Ano) throws IOException {
        this.Ano = Ano;
        jf.setTitle("账户" + Ano);
        jf.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-WIDTH)/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setIconImage(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\IconOfBank.jpg")));
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\BackImage.jpg")));
        jf.setResizable(false);

        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalStrut(80));
        jb1.addActionListener(this::actionPerformed);
        vBox.add(jb1);
        vBox.add(Box.createVerticalStrut(40));
        jb2.addActionListener(this::actionPerformed);
        vBox.add(jb2);
        vBox.add(Box.createVerticalStrut(40));
        jb3.addActionListener(this::actionPerformed);
        vBox.add(jb3);
        vBox.add(Box.createVerticalStrut(40));
        jb4.addActionListener(this::actionPerformed);
        vBox.add(jb4);

        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb1){
            jf.dispose();
            try {
                new ClientPersonalInformation(Ano);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == jb2){}
        if(e.getSource() == jb3){
            jf.dispose();
            try {
                new ClientDealWithMoney(Ano);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == jb4){
            jf.dispose();
        }
    }
}
