import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

public class UserInfoFrameTest {
    @Test
    public void testUserInformationDisplay() {
        // Create a customer with known information
        customer customer = new customer(1, "Minahil", "03321655556", "nust", "123", "F");

        // Instantiate UserInfoFrame
        UserInfoFrame userInfoFrame = new UserInfoFrame(customer);

        // Verify that the information displayed in the frame matches the customer's
        // information
        assertEquals("Minahil", userInfoFrame.nameField.getText());
        assertEquals("03321655556", userInfoFrame.phoneField.getText());
        assertEquals("nust", userInfoFrame.addField.getText());
        assertEquals("F", userInfoFrame.genderField.getText());
    }

    @Test
    public void testBackButtonFunctionality() {
        // Instantiate UserInfoFrame with a dummy customer object
        UserInfoFrame userInfoFrame = new UserInfoFrame(new customer(1, "Minahil", "03321655556", "nust", "123", "F"));

        // Simulate a button click on the "Back" button
        ActionListener[] listeners = userInfoFrame.backBtn.getActionListeners();
        for (ActionListener listener : listeners) {
            listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }

        // Verify that the frame is not visible after the button click
        assertFalse(userInfoFrame.isVisible());
    }
}
