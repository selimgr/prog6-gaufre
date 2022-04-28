package Vue;

import javax.swing.*;

public class InterfaceGraphique implements Runnable {
    JFrame frame;

    public InterfaceGraphique() {
    }

    public static void demarre() {
        SwingUtilities.invokeLater(new InterfaceGraphique());
    }

    @Override
    public void run() {
        this.frame = new JFrame("Gaufre empoisonnée (rip)");
        this.frame.setSize(400, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        
        Box box = Box.createVerticalBox();
        box.add(new JLabel("appétissant.."));
        box.add(new JLabel(new ImageIcon(ClassLoader.getSystemResource("gaufre.jpg"))));

        this.frame.add(box);
    }
}
