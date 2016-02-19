package com.gridworld;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tom on 12/4/2015.
 */
public class JavaGraphics extends Canvas {
//    Some basics on simple graphics in AWT
    public static Canvas c = null;
    public static void main(String[] args) {
//        create window
        JFrame frame = new JFrame();
//        Important !!
//        Default JFrame behaviour is HIDE_ON_CLOSE which will not free memory
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Canvas canvas = new JavaGraphics();
        c = canvas;    // awful hack for test of drawing image
        canvas.setSize(600, 600);
        canvas.setBackground(Color.orange);
        frame.getContentPane().add(canvas);

//        show the frame
        frame.pack();
        frame.setVisible(true);
    }

//    Canvas calls .paint() automatically upon draw
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(200, 200, 50, 50);
        Rectangle box = new Rectangle(100, 100, 200, 200);
//        System.out.println(g);
//        mickey(g, box);

        ArrayList<Rectangle> head = new ArrayList<>();
        head.add(box);
//        mickeyMoose(g, head);

        mickeyMooseV2(g, box);
    }

    public void boxOval(Graphics g, Rectangle bb) {
        g.fillOval(bb.x, bb.y, bb.width, bb.height);
    }

//    Draw Mickey Mouse head with 3 ovals
    public void mickey(Graphics g, Rectangle bb) {
        boxOval(g, bb);

        int dx = bb.width/2;
        int dy = bb.height/2;
        Rectangle half = new Rectangle(bb.x, bb.y, dx, dy);

        half.translate(-dx/2, -dy/2);
        System.out.println(half);
        boxOval(g, half);

        half.translate(dx*2, 0);
        System.out.println(half);
        boxOval(g, half);

    }

//    Draw Mickey Mouse where each ear (leaf) sprouts another pair of ears
//    Until drawn oval is greater than 3
    public void mickeyMoose(Graphics g, ArrayList<Rectangle> heads) {
        ArrayList<Rectangle> leaves = new ArrayList<>();
        for (Rectangle head: heads) {
            if (head.width <= 3 || head.height <= 3) {
                continue;
            }
            boxOval(g, head);

            int dx = head.width / 2;
            int dy = head.height / 2;
            Rectangle leaf1 = new Rectangle(head.x, head.y, dx, dy);
            Rectangle leaf2 = new Rectangle(head.x, head.y, dx, dy);

            leaf1.translate(-dx / 2, -dy / 2);
            leaf2.translate((3 * dx) / 2, -dy / 2);

            boxOval(g, leaf1);
            boxOval(g, leaf2);

            leaves.add(leaf1);
            leaves.add(leaf2);
        }
        if (leaves.isEmpty()) {
            return;
        }
        System.out.println("[leaves] " + leaves.toString());
        mickeyMoose(g, leaves);

    }

//    Much nicer and easier version
    public void mickeyMooseV2(Graphics g, Rectangle bb) {
        if (bb.width <= 3) {
            return;
        }
        boxOval(g, bb);

        int dx = bb.width/2;
        int dy = bb.height/2;
        Rectangle half = new Rectangle(bb.x, bb.y, dx, dy);

        half.translate(-dx/2, -dy/2);
        mickeyMooseV2(g, half);
//        System.out.println(half);
        boxOval(g, half);

        half.translate(dx*2, 0);
        mickeyMooseV2(g, half);
//        System.out.println(half);
        boxOval(g, half);

    }

    public static void drawImage(Graphics g, String file) {
        ImageIcon icon = new ImageIcon(file);
        icon.paintIcon(c, g, 0, 0);
    }


}