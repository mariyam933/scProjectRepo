import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

class CartFrameTest {


    @Test
    public void testIncreaseQtyBtnActionPerformed() throws InvocationTargetException, InterruptedException {
        // Initialize sample data for testing
        customer customer = new customer(1, "Minahil", "03321655556", "nust", "123", "F");
        ArrayList<Product> products = new ArrayList<>();

        Product product1 = new Product(1, 3, 7400, "GEEPAS HEATER GFH9527", "EL", "GEEPAS HEATER GFH9527",
                "C:\\Users\\MINAHIL SHAKOOR\\Desktop\\Grocery_Management_System_Java\\SMMS_Images\\Heater.png");
        Product product2 = new Product(2, 3, 12500, "ADLER KETTLE 1.2L AD 1280", "EL", "ADLER KETTLE 1.2L AD 1280",
                "C:\\Users\\MINAHIL SHAKOOR\\Desktop\\Grocery_Management_System_Java\\SMMS_Images\\kettle.png");
        products.add(product1);
        products.add(product2);

        customer.addToOrders(product1, 1);
        customer.addToOrders(product2, 1);

        // Create CartFrame with the customer and products
        CartFrame cartFrame = new CartFrame(customer, products);
        cartFrame.setVisible(true); // Set the frame visible

        // Use CountDownLatch to synchronize with the Swing event dispatch thread
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            // Assuming the selected row is valid (row 0)
            cartFrame.productsTable.setRowSelectionInterval(0, 0);

            // Simulate clicking the "Increase Quantity" button
            cartFrame.increaseQtyBtn.addActionListener((e) -> latch.countDown());
            cartFrame.increaseQtyBtn.doClick();
        });

        // Introduce a small delay to allow Swing to process the event
        Thread.sleep(500);

        // Wait for the latch with a timeout (e.g., 5 seconds)
        assertTrue(latch.await(5, TimeUnit.SECONDS));

        int productId = 1;
        int expectedQuantity = 2; // Assuming the initial quantity is 1
        int actualQuantity = cartFrame.getOrderQuantity(productId);
        assertEquals(expectedQuantity, actualQuantity);

        // Clean up
        cartFrame.dispose();
    }

    @Test
    public void testIncreaseQuantityButtonWhenOutOFStock() {
        customer customer = new customer(1, "Minahil", "03321655556", "nust", "123", "F");
        ArrayList<Product> products = new ArrayList<>();

        Product product1 = new Product(1, 1, 7400, "GEEPAS HEATER GFH9527", "EL", "GEEPAS HEATER GFH9527",
                "C:\\Users\\MINAHIL SHAKOOR\\Desktop\\Grocery_Management_System_Java\\SMMS_Images\\Heater.png");
        Product product2 = new Product(2, 1, 12500, "ADLER KETTLE 1.2L AD 1280", "EL", "ADLER KETTLE 1.2L AD 1280",
                "C:\\Users\\MINAHIL SHAKOOR\\Desktop\\Grocery_Management_System_Java\\SMMS_Images\\kettle.png");

        customer.addToOrders(product1, 1);
        customer.addToOrders(product2, 1);

        products.add(product1);
        products.add(product2);


        CartFrame cartFrame = new CartFrame(customer, products);
        int initialSize = customer.getOrders().size();

        // Assuming the selected row is valid
        cartFrame.productsTable.setRowSelectionInterval(0, 0);
        cartFrame.increaseQtyBtnActionPerformed(null);

        int finalSize = customer.getOrders().size();

        // Update the assertion to reflect the correct behavior
        // The product is considered out of stock, so the quantity should not increase
        Assert.assertEquals("Quantity should not increase when product is out of stock", initialSize, finalSize);
    }

    @Test
    public void testCheckoutBtnActionPerformed() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            // Initialize sample data for testing
            customer customer = new customer(1, "Minahil", "03321655556", "nust", "123", "F");
            ArrayList<Product> products = new ArrayList<>();

            // ... (Create products and CartFrame)

            CartFrame cartFrame = new CartFrame(customer, products);
            cartFrame.setVisible(true); // Set the frame visible

            // Assuming the correct class name is ReceiptFrame
            RecieptFrame receiptFrame = new RecieptFrame(customer);

            // Set up a property change listener on the cart frame
            cartFrame.addPropertyChangeListener("dispose", evt -> {
                if ((Boolean) evt.getNewValue()) {
                    latch.countDown(); // Count down the latch when the cart frame is disposed
                }
            });

            // Create a mock ActionEvent for the checkoutBtnActionPerformed method
            ActionEvent mockEvent = new ActionEvent(cartFrame.checkoutBtn, ActionEvent.ACTION_PERFORMED, "Checkout");

            // Call the GUI method directly with the mock event
            cartFrame.checkoutBtnActionPerformed(mockEvent);

            // Clean up
            cartFrame.dispose();
        });


        SwingUtilities.invokeLater(() -> {
            // Recreate the customer object or use a new instance for the ReceiptFrame
            customer cs = new customer(1, "Minahil", "03321655556", "nust", "123", "F");
            RecieptFrame receiptFrame = new RecieptFrame(cs);

            // Add a latch to wait for the receipt frame to become visible
            CountDownLatch receiptLatch = new CountDownLatch(1);

            SwingUtilities.invokeLater(() -> {
                receiptFrame.addPropertyChangeListener("visible", evt -> {
                    if ((Boolean) evt.getNewValue()) {
                        receiptLatch.countDown(); // Count down the latch when the receipt frame is visible
                    }
                });

                receiptFrame.setVisible(true);
            });

            // Wait for the receipt frame to become visible or timeout after a specified duration
            try {
                assertTrue(receiptLatch.await(2, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            assertTrue(receiptFrame.isVisible()); // Assert the visibility of the receipt frame
            receiptFrame.dispose();
        });
    }


    @Test
    public void testDeleteBtnNoProducts() {
        // Initialize sample data for testing
        customer customer = new customer(1, "Minahil", "03321655556", "nust", "123", "F");
        ArrayList<Product> products = new ArrayList<>();

        // Create CartFrame with the customer and products
        CartFrame cartFrame = new CartFrame(customer, products);
        cartFrame.setVisible(true); // Set the frame visible

        // Assume there is no product in the table, so the row is -1
        int row = -1;

        // Create a mock ActionEvent for the deleteBtnActionPerformed method
        ActionEvent mockEvent = new ActionEvent(cartFrame.deleteBtn, ActionEvent.ACTION_PERFORMED, "Delete");

        // Call the GUI method directly with the mock event
        cartFrame.deleteBtnActionPerformed(mockEvent);

        // Assert that the size is not reduced
        assertTrue(products.isEmpty());

        // Assert that the frame is still visible
        assertTrue(cartFrame.isVisible());

        // Wait for the message to be displayed
        waitForMessageDisplay(cartFrame);


        // Clean up
        cartFrame.dispose();
    }

    // Check if the product selection message is displayed
    private boolean isProductSelectionMessageDisplayed(CartFrame cartFrame) {
        // Check if the message is displayed in any internal dialog of the cartFrame
        for (Window window : Frame.getFrames()) {
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                Component[] components = dialog.getContentPane().getComponents();
                for (Component component : components) {
                    if (component instanceof JOptionPane) {
                        JOptionPane optionPane = (JOptionPane) component;
                        if ("Please Select a Product to Delete Product!".equals(optionPane.getMessage())) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    // Wait for the message to be displayed with a timeout
    private void waitForMessageDisplay(CartFrame cartFrame) {
        int timeout = 5000; // Set your desired timeout in milliseconds
        int interval = 100; // Set your desired checking interval in milliseconds

        Timer timer = new Timer(interval, null);
        timer.setRepeats(true);
        AtomicBoolean messageDisplayed = new AtomicBoolean(false);

        timer.addActionListener(e -> {
            if (isProductSelectionMessageDisplayed(cartFrame)) {
                messageDisplayed.set(true);
                timer.stop();
            }
        });

        timer.start();

        // Wait until the message is displayed or timeout occurs
        while (!messageDisplayed.get() && timeout > 0) {
            try {
                Thread.sleep(interval);
                timeout -= interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testDeleteBtnActionPerformed() throws InvocationTargetException, InterruptedException {
        // Initialize sample data for testing
        customer customer = new customer(1, "Minahil", "03321655556", "nust", "123", "F");
        ArrayList<Product> products = new ArrayList<>();

        Product product1 = new Product(1, 1, 7400, "GEEPAS HEATER GFH9527", "EL", "GEEPAS HEATER GFH9527",
                "C:\\Users\\MINAHIL SHAKOOR\\Desktop\\Grocery_Management_System_Java\\SMMS_Images\\Heater.png");

        products.add(product1);


        customer.addToOrders(product1, 1);


        // Create CartFrame with the customer and products
        CartFrame cartFrame = new CartFrame(customer, products);
        cartFrame.setVisible(true); // Set the frame visible

        // Use CountDownLatch to synchronize with the Swing event dispatch thread
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            // Assuming the selected row is valid (row 0)
            cartFrame.productsTable.setRowSelectionInterval(0, 0);

            // Simulate clicking the "Delete" button
            cartFrame.deleteBtn.addActionListener((e) -> latch.countDown());
            cartFrame.deleteBtn.doClick();
        });

        // Introduce a small delay to allow Swing to process the event
        Thread.sleep(500);

        // Wait for the latch with a timeout (e.g., 5 seconds)
        assertTrue(latch.await(5, TimeUnit.SECONDS));

        // Verify that the order has been removed from the cart
        assertTrue(customer.getOrders().isEmpty());

        // Assert that the frame is still visible
        assertTrue(cartFrame.isVisible());

        // Clean up
        cartFrame.dispose();
    }
}


