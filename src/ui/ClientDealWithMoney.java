package ui;

import tool.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ClientDealWithMoney implements ActionListener {
    JFrame jf = new JFrame();
    final int WIDTH = 300;
    final int HEIGHT = 500;
    String Ano = "";

    JButton jb1 = new JButton("存款");
    JButton jb2 = new JButton("取款");
    JButton jb3 = new JButton("转账");
    JButton jb4 = new JButton("贷款");
    JButton jb5 = new JButton("返回");

    public ClientDealWithMoney(String Ano) throws IOException {
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
        vBox.add(Box.createVerticalStrut(40));
        jb5.addActionListener(this::actionPerformed);
        vBox.add(jb5);

        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb1){
            jf.dispose();
            try {
                new ClientDeposit(Ano);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == jb2){
            jf.dispose();
            try {
                new ClientWithdraw(Ano);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == jb3){
            jf.dispose();
            try {
                new ClientTransfer(Ano);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == jb4){
            jf.dispose();
            try {
                new ClientLoan(Ano);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == jb5){
            jf.dispose();
            try {
                new ClientLogin(Ano);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
