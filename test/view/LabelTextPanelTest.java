package view;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class LabelTextPanelTest {

    @Test
    public void testLabelTextPanel() {
        JLabel label = new JLabel("Test Label");
        JTextField textField = new JTextField("Test Text");
        LabelTextPanel panel = new LabelTextPanel(label, textField);

        assertEquals(2, panel.getComponentCount());
        assertEquals(label, panel.getComponent(0));
        assertEquals(textField, panel.getComponent(1));
        assertEquals("Test Label", ((JLabel) panel.getComponent(0)).getText());
        assertEquals("Test Text", ((JTextField) panel.getComponent(1)).getText());
    }
}
