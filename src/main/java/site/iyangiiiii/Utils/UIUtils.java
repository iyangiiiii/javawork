package site.iyangiiiii.Utils;

import javax.swing.*;
import java.awt.*;

public class UIUtils {
    /**
     * 在某个部件旁边显示一个含有文字的泡
     * @param component 部件
     * @param text 需要显示的文字
     */
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
