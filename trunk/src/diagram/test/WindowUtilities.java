package diagram.test;

import javax.swing.*;
import java.awt.*;

public class WindowUtilities {

  /** Tell system to use native look and feel, as in previous
   *  releases. Metal (Java) LAF is the default otherwise.
   */

  public static void setNativeLookAndFeel() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch(Exception e) {
      System.out.println("Error setting native LAF: " + e);
    }
  }

  /** A simplified way to see a JPanel or other Container.
   *  Pops up a JFrame with specified Container as the content pane.
   */

  public static JFrame openInJFrame(Container content,
                                    int width,
                                    int height,
                                    String title,
                                    Color bgColor) {
    JFrame frame = new JFrame(title);
    frame.setBackground(bgColor);
    content.setBackground(bgColor);
    frame.setSize(width, height);
    frame.setContentPane(content);
    frame.addWindowListener(new ExitListener());
    frame.setVisible(true);
    return(frame);
  }

  /** Uses Color.white as the background color. */

  public static JFrame openInJFrame(Container content,
                                    int width,
                                    int height,
                                    String title) {
    return(openInJFrame(content, width, height, title, Color.white));
  }

  /** Uses Color.white as the background color, and the
   *  name of the Container's class as the JFrame title.
   */

  public static JFrame openInJFrame(Container content,
                                    int width,
                                    int height) {
    return(openInJFrame(content, width, height,
                        content.getClass().getName(),
                        Color.white));
  }
}
