/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hust.soict.dsai.blockchain.graph.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author 84913
 */
public class PanelRound extends JPanel{

    public int getRoundTopLeft() {
        return RoundTopLeft;
    }

    public int getRoundTopRight() {
        return RoundTopRight;
    }

    public void setRoundTopLeft(int RoundTopLeft) {
        this.RoundTopLeft = RoundTopLeft;
        repaint();
    }

    public void setRoundTopRight(int RoundTopRight) {
        this.RoundTopRight = RoundTopRight;
        repaint();
    }

    public void setRoundBottomLeft(int RoundBottomLeft) {
        this.RoundBottomLeft = RoundBottomLeft;
        repaint();
    }

    public void setRoundBottomRight(int RoundBottomRight) {
        this.RoundBottomRight = RoundBottomRight;
        repaint();
    }

    public int getRoundBottomLeft() {
        return RoundBottomLeft;
    }

    public int getRoundBottomRight() {
        return RoundBottomRight;
    }
    
    private int RoundTopLeft = 0;
    private int RoundTopRight = 0;
    private int RoundBottomLeft = 0;
    private int RoundBottomRight = 0;
    
    
    public PanelRound(){
        setOpaque(false);
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area area = new Area(createRoundTopLeft());
        if(RoundTopRight > 0){
            area.intersect(new Area(createRoundTopRight()));
        }
        if(RoundTopLeft > 0){
            area.intersect(new Area(createRoundTopLeft()));
        }
        if(RoundBottomRight > 0){
            area.intersect(new Area(createRoundBottomRight()));
        }
        if(RoundBottomLeft > 0){
            area.intersect(new Area(createRoundBottomLeft()));
        }
        g2.fill(area);
        g2.dispose();
        super.paintComponent(g);
        
    }
    
    private Shape createRoundTopRight(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, RoundTopRight);
        int roundY = Math.min(height, RoundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX/2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY/2, width, height - roundY/2)));
        return area;
    }
    
    private Shape createRoundTopLeft(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, RoundTopLeft);
        int roundY = Math.min(height, RoundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX/2, 0, width - roundX/2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY/2, width, height - roundY/2)));
        return area;
    }
    
    private Shape createRoundBottomRight(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, RoundBottomRight);
        int roundY = Math.min(height, RoundBottomRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX/2, 0, width - roundX/2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY/2)));
        return area;
    }
     
    private Shape createRoundBottomLeft(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, RoundBottomLeft);
        int roundY = Math.min(height, RoundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX/2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY/2)));
        return area;
    }
    //public static void main(String[] args){}
    
    
    
}
