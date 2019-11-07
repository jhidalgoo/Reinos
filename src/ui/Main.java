package ui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.FontUIResource;

public class Main {

	/**
	 * Launch the application.
	 */
    public static void main(String args[]) {
        try {
            // javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        	OSUtil.OS_TYPE os = OSUtil.getOSType();
            if (os == OSUtil.OS_TYPE.WINDOWS) {
                // Custom Windows L&F and font settings
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

                // This font looks good but on Windows 7 it misses some chars like the stars...
                //FontUIResource font = new FontUIResource("Lucida Sans Unicode", Font.PLAIN, 11);
               // UIManager.put("Table.font", font);
            } else {
                for (LookAndFeelInfo ui : UIManager.getInstalledLookAndFeels()) {
                    System.out.println("Available look and feel: " + ui.getName() + " " + ui.getClassName());
                    if (ui.getName().equals("Nimbus")) {
                        UIManager.setLookAndFeel(ui.getClassName());
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	FrmMain elMain = new FrmMain();
                elMain.setVisible(true);
            }
        });
    }
}
