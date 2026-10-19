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

public class ManagerLoadVerification implements ActionListener {
    JFrame jf = new JFrame();
    final int WIDTH = 300;
    final int HEIGHT = 500;
    String Mno = "";

    JLabel AnoLabel = new JLabel("待审核账户号:  ");
    JTextField Ano = new JTextField(10);
    JButton queryBtn = new JButton("查询");
    JButton returnBtn = new JButton("返回");

    public ManagerLoadVerification(String Mno) throws IOException {
        this.Mno = Mno;
        jf.setTitle("管理员" + Mno);
        jf.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-WIDTH)/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setIconImage(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\IconOfBank.jpg")));
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\BackImage.jpg")));
        jf.setResizable(false);

        Box vBox = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();

        box1.add(AnoLabel);
        box1.add(Ano);

        queryBtn.addActionListener(this::actionPerformed);
        box2.add(queryBtn);
        box2.add(Box.createHorizontalStrut(40));
        returnBtn.addActionListener(this::actionPerformed);
        box2.add(returnBtn);

        vBox.add(Box.createVerticalStrut(80));
        vBox.add(box1);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(box2);

        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == queryBtn){
            GetSQL.Connect();
            String Lano = this.Ano.getText();
            String Lamount = GetSQL.getLoanInfo(Lano,4);
            String Ldp = GetSQL.getLoanInfo(Lano,6);
            if(Lamount == null){
                JOptionPane.showMessageDialog(jf,"该账户不存在需要审批的贷款申请");
            }else{
                jf.dispose();
                try {
                    new ManagerLoadVerification1(Mno,Lano,Lamount,Ldp);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
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
