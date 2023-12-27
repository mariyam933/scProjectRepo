import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SignUpFrameTest {

    @Test
    public void testSignUpBtnActionPerformed() throws InvocationTargetException, InterruptedException {
        // Create SignUpFrame
        SignUpFrame signUpFrame = new SignUpFrame();
        signUpFrame.setVisible(true);

        // Use CountDownLatch to synchronize with the Swing event dispatch thread
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            // Set some sample data for testing
            signUpFrame.nameField.setText("John Doe");
            signUpFrame.pssField.setText("password");
            signUpFrame.phoneField.setText("1234567890");
            signUpFrame.addField.setText("123 Main St");
            signUpFrame.Male.setSelected(true); // Assume Male is selected

            // Simulate clicking the "Sign Up" button
            signUpFrame.signUpBtn.addActionListener((e) -> latch.countDown());
            signUpFrame.signUpBtn.doClick();
        });

        // Introduce a small delay to allow Swing to process the event
        Thread.sleep(1000);

        // Wait for the latch with a timeout (e.g., 5 seconds)
        assertTrue(latch.await(5, TimeUnit.SECONDS));

        // Add your assertions based on the expected behavior of the SignUpBtnActionPerformed method
        // For example, you can check if a new user is registered successfully

        // Clean up
        signUpFrame.dispose();
    }

    @Test
    public void testSignUpBtnWithEmptyFields() throws InvocationTargetException, InterruptedException {
        // Create SignUpFrame
        SignUpFrame signUpFrame = new SignUpFrame();
        signUpFrame.setVisible(true);

        // Use CountDownLatch to synchronize with the Swing event dispatch thread
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            // Leave some fields empty
            signUpFrame.nameField.setText("John Doe");
            signUpFrame.pssField.setText("password");

            // Simulate clicking the "Sign Up" button
            signUpFrame.signUpBtn.addActionListener((e) -> latch.countDown());
            signUpFrame.signUpBtn.doClick();
        });

        // Introduce a small delay to allow Swing to process the event
        Thread.sleep(500);

        // Wait for the latch with a timeout (e.g., 5 seconds)
        assertTrue(latch.await(5, TimeUnit.SECONDS));

        // Add assertions based on the expected behavior of the SignUpBtnActionPerformed method
        // For example, you can check if an error message is displayed for empty fields

        // Clean up
        signUpFrame.dispose();
    }

    // Add more test methods as needed for other functionality in SignUpFrame
}
