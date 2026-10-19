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

public class ClientLoan implements ActionListener {
    JFrame jf = new JFrame();
    final int WIDTH = 300;
    final int HEIGHT = 500;
    String Ano = "";

    JLabel jlb1 = new JLabel("贷款金额:  ");
    JLabel jlb2 = new JLabel("贷款描述:  ");
    JTextField amount = new JTextField(10);
    JTextField description = new JTextField(10);
    JButton applicationBtn = new JButton("申请");
    JButton returnBtn = new JButton("返回");

    public ClientLoan(String Ano) throws IOException {
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

        box1.add(jlb1);
        box1.add(amount);

        box2.add(jlb2);
        box2.add(description);

        applicationBtn.addActionListener(this::actionPerformed);
        box3.add(applicationBtn);
        box3.add(Box.createHorizontalStrut(40));
        returnBtn.addActionListener(this::actionPerformed);
        box3.add(returnBtn);

        vBox.add(Box.createVerticalStrut(80));
        vBox.add(box1);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(box2);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(box3);


        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == applicationBtn){
            GetSQL.Connect();
            if(GetSQL.getLoanInfo(Ano,1) != null){
                JOptionPane.showMessageDialog(jf,"当前账户存在一项贷款申请");
            }else{
                String Lsano = Ano.concat("1");
                String Lano = Ano;
                String Lamount = amount.getText();
                String Ldp = description.getText();
                String Ldate;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                Ldate = simpleDateFormat.format(date);
                if(GetSQL.loan(Lsano,Lano,Float.parseFloat(Lamount),Ldate,Ldp)){
                    JOptionPane.showMessageDialog(jf,"申请提交成功");
                }
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
