import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class Background extends Entity {

    // Properties
    final int lineWidth = 5;
    final int linesPerScreen = 6;

    // Rendering the background
    public void prerender(Graphics g, Vector2 cameraPos)
    {
        // Getting the local position
        int lx = (int) (pos.x-cameraPos.x);
        int ly = (int) (pos.y-cameraPos.y);

        // spacing
        double xspacing = (double) Engine.engine.screenWidth / ( linesPerScreen - 1 );
        // double yspacing = (double) Engine.engine.screenHeight / ( linesPerScreen - 1 );

        int dim = 1;

        // Filling the background
        g.setColor(new Color(100/dim, 100/dim, 125/dim));
        g.fillRect(0, 0, Engine.engine.screenWidth, Engine.engine.screenHeight);

        // Setting the line color
        g.setColor(new Color(75/dim, 75/dim, 100/dim));

        for (int i = 0; i < linesPerScreen; i++)
        {
            // Vertical Lines
            g.fillRect(
                (int)(xspacing*i-lineWidth/2 + lx%xspacing), 
                0, 
                lineWidth, 
                Engine.engine.screenHeight
                );

            // Horizontal Lines
            g.fillRect(
                0, 
                (int)(xspacing*i-lineWidth/2  + ly%xspacing), 
                Engine.engine.screenWidth, 
                lineWidth
                );
        }

        // g.fillRect(lx, ly, lx, ly);
    }
}
