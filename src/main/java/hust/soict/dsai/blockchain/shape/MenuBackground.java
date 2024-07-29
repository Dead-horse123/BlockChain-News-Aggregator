/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hust.soict.dsai.blockchain.shape;

/**
 *
 * @author 84913
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MenuBackground extends JPanel{

    private BufferedImage image;

    public MenuBackground() {
       try {                
          Path currentRelativePath = Paths.get("");
          String path = currentRelativePath.toAbsolutePath().toString();
          String realPath = path + "\\target\\classes\\images\\1.png";
          image = ImageIO.read(new File(realPath));
          
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }
    //public static void main(String[] args){}
}
