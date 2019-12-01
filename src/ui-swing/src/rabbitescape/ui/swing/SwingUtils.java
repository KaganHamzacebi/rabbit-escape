package rabbitescape.ui.swing;
import javax.swing.*;
import java.awt.*;

public class SwingUtils {
	 public static void setFontSize(JComponent component, Dimension buttonSizeInPixels) {
	        component.setFont( new Font( "monospaced", Font.PLAIN, (int) (buttonSizeInPixels.height * 0.5)) );
	    }
}
