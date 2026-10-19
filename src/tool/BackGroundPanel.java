package tool;

import javax.swing.*;
import java.awt.*;

public class BackGroundPanel extends JPanel {
    private Image backImage;
    public BackGroundPanel(Image backImage){
        this.backImage = backImage;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backImage,0,0,this.getWidth(),this.getHeight(),null);
    }
}
