package site.iyangiiiii.Utils;

import javax.swing.*;
import java.awt.*;

public class UIUtils {
    public static void showTooltip(JComponent component, String text) {
        ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
        toolTipManager.setInitialDelay(0);

        JToolTip toolTip = new JToolTip();
        toolTip.setTipText(text);

        Point location = component.getLocationOnScreen();
        toolTip.setLocation(location.x + component.getWidth(), location.y);

        PopupFactory factory = PopupFactory.getSharedInstance();
        Popup popup = factory.getPopup(component, toolTip, location.x + component.getWidth(), location.y);
        popup.show();
    }
}
