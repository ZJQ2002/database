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

public class ClientWithdraw implements ActionListener {
    JFrame jf = new JFrame();
    final int WIDTH = 300;
    final int HEIGHT = 500;
    String Ano = "";

    JRadioButton jrb1 = new JRadioButton("活期");
    JRadioButton jrb2 = new JRadioButton("定期");
    JLabel amountLabel = new JLabel("取款金额:");
    JTextField amount = new JTextField(10);
    JButton wdBtn = new JButton("取出");
    JButton returnBtn = new JButton("返回");

    public ClientWithdraw(String Ano) throws IOException {
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

        box1.add(jrb1);
        box1.add(Box.createHorizontalStrut(40));
        box1.add(jrb2);

        box2.add(amountLabel);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(amount);

        wdBtn.addActionListener(this::actionPerformed);
        box3.add(wdBtn);
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
        if(e.getSource() == wdBtn){
            String Wsano = "";
            String Wtype = "";
            String Wdate = "";
            String Wamount = amount.getText();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Wdate = simpleDateFormat.format(date);
            if(jrb1.isSelected()){
                Wsano = Ano.concat("1");
                Wtype = "活期";
                GetSQL.Connect();
                if(GetSQL.withdraw(Wsano,Ano,Wtype,Wdate,Float.parseFloat(Wamount))){
                    JOptionPane.showMessageDialog(jf,"取款成功");
                };
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
