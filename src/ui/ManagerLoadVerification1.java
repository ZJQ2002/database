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

public class ManagerLoadVerification1 implements ActionListener {
    JFrame jf = new JFrame();
    final int WIDTH = 300;
    final int HEIGHT = 500;
    String Mno = "";
    String Lamount = "";
    String Ldp = "";
    String Lano = "";

    JLabel jlb1 = new JLabel("������:  ");
    JLabel jlb2 = new JLabel("��������:  ");
    JButton accBtn = new JButton("ͬ��");
    JButton refuBtn = new JButton("�ܾ�");
    JButton returnBtn = new JButton("����");

    public ManagerLoadVerification1(String Mno,String Lano,String Lamount,String Ldp) throws IOException {
        this.Mno = Mno;
        this.Lamount = Lamount;
        this.Ldp = Ldp;
        this.Lano = Lano;
        jf.setTitle("����Ա" + Mno);
        jf.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-WIDTH)/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setIconImage(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\IconOfBank.jpg")));
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\BackImage.jpg")));
        jf.setResizable(false);

        Box vBox = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();

        jlb1.setText(jlb1.getText() + Lamount);
        jlb2.setText(jlb2.getText() + Ldp);
        box1.add(jlb1);
        box2.add(jlb2);
        accBtn.addActionListener(this::actionPerformed);
        box3.add(accBtn);
        box3.add(Box.createHorizontalStrut(40));
        refuBtn.addActionListener(this::actionPerformed);
        box3.add(refuBtn);
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
        if(e.getSource() == accBtn){
            GetSQL.Connect();
            if(GetSQL.updateInfo("�����",Lano,"ͬ��")){
                jf.dispose();
            }
        }
        if(e.getSource() == refuBtn){
            GetSQL.Connect();
            if(GetSQL.updateInfo("�����",Lano,"�ܾ�")){
                jf.dispose();
            }
        }
        if(e.getSource() == returnBtn){
            jf.dispose();
        }
        try {
            new ManagerLoadVerification(Mno);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
