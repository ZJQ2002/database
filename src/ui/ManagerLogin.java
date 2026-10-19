package ui;

import tool.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ManagerLogin implements ActionListener {
    JFrame jf = new JFrame();
    final int WIDTH = 300;
    final int HEIGHT = 500;
    String Mno = "";

    JButton jb1 = new JButton("��������");
    JButton jb2 = new JButton("���й�Ա��Ϣ����");
    JButton jb3 = new JButton("�ͻ�������������");
    JButton jb4 = new JButton("����Ա��Ϣ¼��");
    JButton jb5 = new JButton("�ʽ�����ͳ��");
    JButton jb6 = new JButton("�˳�");

    public ManagerLogin(String Mno) throws IOException {
        this.Mno = Mno;
        jf.setTitle("����Ա" + Mno);
        jf.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-WIDTH)/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setIconImage(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\IconOfBank.jpg")));
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("D:\\Java\\IdeaProjects\\database\\images\\BackImage.jpg")));
        jf.setResizable(false);

        //��װ��ť
        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalStrut(50));
        jb1.addActionListener(this::actionPerformed);
        vBox.add(jb1);
        vBox.add(Box.createVerticalStrut(30));
        jb2.addActionListener(this::actionPerformed);
        vBox.add(jb2);
        vBox.add(Box.createVerticalStrut(30));
        jb3.addActionListener(this::actionPerformed);
        vBox.add(jb3);
        vBox.add(Box.createVerticalStrut(30));
        jb4.addActionListener(this::actionPerformed);
        vBox.add(jb4);
        vBox.add(Box.createVerticalStrut(30));
        jb5.addActionListener(this::actionPerformed);
        vBox.add(jb5);
        vBox.add(Box.createVerticalStrut(30));
        jb6.addActionListener(this::actionPerformed);
        vBox.add(jb6);

        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb1){
            jf.dispose();
            try {
                new ManagerPersonalInformation(this.Mno);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == jb2){}
        if(e.getSource() == jb3){
            jf.dispose();
            try {
                new ManagerLoadVerification(Mno);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == jb4){}
        if(e.getSource() == jb5){}
        if(e.getSource() == jb6){
            jf.dispose();
        }
    }
}
