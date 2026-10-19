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
import java.util.Calendar;

public class ClientDeposit implements ActionListener {
    JFrame jf = new JFrame();
    final int WIDTH = 300;
    final int HEIGHT = 500;
    String Ano = "";

    JRadioButton jrb1 = new JRadioButton("活期");
    JRadioButton jrb2 = new JRadioButton("定期");
    JLabel jLabel1 = new JLabel("存款金额:");
    JLabel jLabel2 = new JLabel("定期存款存期(单位:年):");
    JTextField amount = new JTextField(10);
    JTextField time = new JTextField(10);
    JButton depBtn = new JButton("存入");
    JButton returnBtn = new JButton("返回");

    public ClientDeposit(String Ano) throws IOException {
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
        Box box4 = Box.createHorizontalBox();

        box1.add(jrb1);
        box1.add(Box.createHorizontalStrut(40));
        box1.add(jrb2);

        box2.add(jLabel1);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(amount);

        box3.add(jLabel2);
        box3.add(Box.createHorizontalStrut(20));
        box3.add(time);

        depBtn.addActionListener(this::actionPerformed);
        box4.add(depBtn);
        box4.add(Box.createHorizontalStrut(40));
        returnBtn.addActionListener(this::actionPerformed);
        box4.add(returnBtn);

        vbox.add(Box.createVerticalStrut(80));
        vbox.add(box1);
        vbox.add(Box.createVerticalStrut(40));
        vbox.add(box2);
        vbox.add(Box.createVerticalStrut(40));
        vbox.add(box3);
        vbox.add(Box.createVerticalStrut(40));
        vbox.add(box4);

        bgPanel.add(vbox);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == depBtn){
            String DEsano;
            String DEtype;
            String DEamount = this.amount.getText();
            String time = this.time.getText();
            String datestr = "";
            String timestr = "";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            datestr = simpleDateFormat.format(date);
            if(jrb1.isSelected()){
                DEsano = Ano.concat("1");
                DEtype = "活期";
                GetSQL.Connect();
                if(GetSQL.deposit(Ano,DEsano,DEtype,Float.parseFloat(DEamount),datestr,null)){
                    JOptionPane.showMessageDialog(jf,"存款成功");
                }
            }
            else if(jrb2.isSelected()) {
                DEsano = Ano.concat("2");
                DEtype = "定期";
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.YEAR,Integer.parseInt(time));
                date = calendar.getTime();
                timestr = simpleDateFormat.format(date);
                GetSQL.Connect();
                if(GetSQL.deposit(Ano,DEsano,DEtype,Float.parseFloat(DEamount),datestr,timestr)){
                    JOptionPane.showMessageDialog(jf,"存款成功");
                }
            }
            else{
                JOptionPane.showMessageDialog(jf,"请选择存款类型");
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
