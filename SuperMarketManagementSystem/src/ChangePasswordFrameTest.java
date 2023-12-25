import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

public class ChangePasswordFrameTest {

    @Test
    public void changePasswordWithCorrectOldPassword() throws Exception {
        // Create a customer with a known password
        customer customer = new customer(1, "Minahil", "03321655556", "nust", "123", "F");

        // Use an AtomicBoolean to check if Swing operations are completed
        AtomicBoolean swingOperationsCompleted = new AtomicBoolean(false);

        SwingUtilities.invokeAndWait(() -> {
            // Create ChangePasswordFrame
            ChangePasswordFrame frame = new ChangePasswordFrame(customer);

            // Set the old and new passwords
            frame.oldPassField.setText("123");
            frame.newPassField.setText("1234");

            // Add a callback to set the AtomicBoolean to true when Swing operations are completed
            SwingUtilities.invokeLater(() -> swingOperationsCompleted.set(true));

            // Simulate button click
            frame.changeBtn.doClick();

            // Dispose of the frame
            frame.dispose();
        });

        // Wait until Swing operations are completed
        while (!swingOperationsCompleted.get()) {
            Thread.sleep(50); // Adjust the sleep duration based on your needs
        }

        // Verify that the password is changed only if the old password is correct
        assertEquals("1234", customer.getPassword());
    }
    @Test
    public void changePasswordWithIncorrectOldPassword() throws Exception {
        // Create a customer with a known password
        customer customer = new customer(1, "Minahil", "03321655556", "nust", "123", "F");

        // Use an AtomicBoolean to check if Swing operations are completed
        AtomicBoolean swingOperationsCompleted = new AtomicBoolean(false);

        SwingUtilities.invokeAndWait(() -> {
            // Create ChangePasswordFrame
            ChangePasswordFrame frame = new ChangePasswordFrame(customer);

            // Set the old and new passwords
            frame.oldPassField.setText("12345");
            frame.newPassField.setText("1234");

            // Add a callback to set the AtomicBoolean to true when Swing operations are completed
            SwingUtilities.invokeLater(() -> swingOperationsCompleted.set(true));

            // Simulate button click
            frame.changeBtn.doClick();

            // Dispose of the frame
            frame.dispose();
        });

        // Wait until Swing operations are completed
        while (!swingOperationsCompleted.get()) {
            Thread.sleep(50); // Adjust the sleep duration based on your needs
        }

        // Verify that the password is changed only if the old password is correct
        assertEquals("123", customer.getPassword());
    }

    @Test
    public void changePasswordWithEmptyOldPassword() throws Exception {
        // Create a customer with a known password
        customer customer = new customer(1, "Minahil", "03321655556", "nust", "123", "F");

        // Use an AtomicBoolean to check if Swing operations are completed
        AtomicBoolean swingOperationsCompleted = new AtomicBoolean(false);

        SwingUtilities.invokeAndWait(() -> {
            // Create ChangePasswordFrame
            ChangePasswordFrame frame = new ChangePasswordFrame(customer);

            // Set the old and new passwords
            frame.oldPassField.setText("");
            frame.newPassField.setText("1234");

            // Add a callback to set the AtomicBoolean to true when Swing operations are completed
            SwingUtilities.invokeLater(() -> swingOperationsCompleted.set(true));

            // Simulate button click
            frame.changeBtn.doClick();

            // Dispose of the frame
            frame.dispose();
        });

        // Wait until Swing operations are completed
        while (!swingOperationsCompleted.get()) {
            Thread.sleep(50); // Adjust the sleep duration based on your needs
        }

        // Verify that the password is changed only if the old password is correct
        assertEquals("123", customer.getPassword());
    }





}
