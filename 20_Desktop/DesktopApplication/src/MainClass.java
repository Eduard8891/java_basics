import javax.swing.*;
import java.awt.*;

public class MainClass {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(600, 400);

        jFrame.add(new MainForm().getMainPanel());

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
