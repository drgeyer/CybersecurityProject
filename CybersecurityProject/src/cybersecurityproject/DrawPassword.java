/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurityproject;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author 7051665
 */
public class DrawPassword extends JPanel{
    Password LoadedPassword = new Password();
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        if(LoadedPassword.events.isEmpty())
            return;
        else
            DrawLoadedPassword(g2d);
    }

    private void DrawLoadedPassword(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        int width = this.getWidth();
        int height = this.getHeight();
        Font small = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
        Font large = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        
        double pixels_per_ms = (double)(width - 80) / (double)LoadedPassword.events.get(LoadedPassword.events.size()-1).TimeSinceFirst;
        
        int left_pos = 20;
        int bottom_pos = height / 2;
        g2d.drawString("Password: " + LoadedPassword.final_password, 20, 20);
        for(PasswordEvent e1 : LoadedPassword.events)
        {
            left_pos = DrawEvent(g2d, e1, left_pos, bottom_pos, pixels_per_ms, small, large);
        }
    }

    private int DrawEvent(Graphics2D g2d, PasswordEvent e1, int left_pos, int bottom_pos, double ppms, Font small, Font large) {
        
        String code = Integer.toString(e1.KeyCode);
        //draw the events Pressed/Released Arrow below the code
        if(e1.KeyPressed == 401)
            code += " ↓";
        else
            code += " ↑";
        
        //draw the time to next event line
        g2d.drawLine(left_pos, bottom_pos, left_pos + (int)(ppms * e1.TimeToNext), bottom_pos);
        int line_pos = bottom_pos;
        
        if(e1.EventPosition % 2 == 1)
        {
            //print above the line
            g2d.drawLine(left_pos, line_pos, left_pos, line_pos - 60);
            bottom_pos = line_pos - 40;
        }
        else
        {
            //print below the line
            g2d.drawLine(left_pos, line_pos, left_pos, line_pos + 60);
            bottom_pos = line_pos + 30;
        }
        int text_pos = left_pos + 3;
        //draw the events KeyCode
        g2d.setFont(large);
        g2d.drawString(code, text_pos, bottom_pos);
        
        g2d.setFont(small);
        bottom_pos += 10;
        
        if(e1.KeyLocation == 1)
            g2d.drawString("STD", text_pos, bottom_pos);
        else if(e1.KeyLocation == 2)
            g2d.drawString("LEFT", text_pos, bottom_pos);
        else if(e1.KeyLocation == 3)
            g2d.drawString("RIGHT", text_pos, bottom_pos);
        else if (e1.KeyLocation == 4)
            g2d.drawString("NUM PAD", text_pos, bottom_pos);
        bottom_pos += 10;
        g2d.drawString("Next: " + Integer.toString(e1.TimeToNext), text_pos, bottom_pos);
        
        return (int) (left_pos + (e1.TimeToNext * ppms));
    }
}
