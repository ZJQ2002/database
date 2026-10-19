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
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientTransfer implements ActionListener {
    JFrame jf = new JFrame();
    final int WIDTH = 300;
    final int HEIGHT = 500;
    String Ano = "";

    JLabel TRamount = new JLabel("转账金额:  ");
    JLabel TRobacc = new JLabel("目的账户:  ");
    JTextField amount = new JTextField(10);
    JTextField obacc = new JTextField(10);
    JButton confirmBtn = new JButton("确认");
    JButton returnBtn = new JButton("返回");

    public ClientTransfer(String Ano) throws IOException {
        this.Ano = Ano;
        jf.setTitle("账户" + Ano);
        jf.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-WIDTH)/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setIconImage(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\IconOfBank.jpg")));
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\BackImage.jpg")));
        jf.setResizable(false);

        Box vbox = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();

        box1.add(TRamount);
        box1.add(amount);

        box2.add(TRobacc);
        box2.add(obacc);

        confirmBtn.addActionListener(this::actionPerformed);
        box3.add(confirmBtn);
        box3.add(Box.createHorizontalStrut(40));
        returnBtn.addActionListener(this::actionPerformed);
        box3.add(returnBtn);

        vbox.add(Box.createVerticalStrut(80));
        vbox.add(box1);
        vbox.add(Box.createVerticalStrut(40));
        vbox.add(box2);
        vbox.add(Box.createVerticalStrut(40));
        vbox.add(box3);

        bgPanel.add(vbox);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmBtn){
            String TRsano = Ano.concat("1");
            String TRamount = amount.getText();
            String TRobacc = obacc.getText();
            String TRdate = "";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            TRdate = simpleDateFormat.format(date);
            GetSQL.Connect();
            if(GetSQL.transfer(TRsano,Ano,Float.parseFloat(TRamount),TRdate,TRobacc)){
                JOptionPane.showMessageDialog(jf,"转账成功");
            }
        }
        if(e.getSource() == returnBtn){
            jf.dispose();
            try {
                new ClientDealWithMoney(Ano);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
