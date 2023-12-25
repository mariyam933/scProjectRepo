import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFrameTest {
    @Test
    public void productFrameInitializationTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    try {
                        ArrayList<Product> products = new ArrayList<>();
                        // Assuming there is a constructor with parameters in the customer class
                        customer customer = new customer(1, "John Doe", null, null, null, null);

                        products.add(new Product(1, 1, 7400, "GEEPAS HEATER GFH9527", "EL", "GEEPAS HEATER GFH9527",
                                "C:\\Users\\MINAHIL SHAKOOR\\Desktop\\Grocery_Management_System_Java\\SMMS_Images\\Heater.png"));
                        products.add(new Product(2, 1, 12500, "ADLER KETTLE 1.2L AD 1280", "EL",
                                "ADLER KETTLE 1.2L AD 1280",
                                "C:\\Users\\MINAHIL SHAKOOR\\Desktop\\Grocery_Management_System_Java\\SMMS_Images\\kettle.png"));

                        ProductFrame frame = new ProductFrame(products, 1, customer);
                        frame.setVisible(true);

                        Assert.assertEquals("Product Details", frame.getTitle());
                        assertNotNull(frame.imageLabel.getIcon());
                        assertEquals("<html>GEEPAS HEATER GFH9527</html>", frame.nameLabel.getText());

                        assertTrue(frame.priceLabel.getText().contains("7400.0"));
                        ;
                        assertEquals("GEEPAS HEATER GFH9527", frame.descArea.getText());


                        frame.dispose();
                    } finally {
                        latch.countDown();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        latch.await(30, TimeUnit.SECONDS);
    }



        @Test
        public void testAddToCartBtnActionPerformed() {

            ArrayList<Product> products = new ArrayList<>();
            // Assuming there is a constructor with parameters in the customer class
            customer customer = new customer(1, "John Doe", null, null, null, null);

            products.add(new Product(1, 1, 7400, "GEEPAS HEATER GFH9527", "EL", "GEEPAS HEATER GFH9527",
                    "C:\\Users\\MINAHIL SHAKOOR\\Desktop\\Grocery_Management_System_Java\\SMMS_Images\\Heater.png"));
            products.add(new Product(2, 1, 12500, "ADLER KETTLE 1.2L AD 1280", "EL",
                    "ADLER KETTLE 1.2L AD 1280",
                    "C:\\Users\\MINAHIL SHAKOOR\\Desktop\\Grocery_Management_System_Java\\SMMS_Images\\kettle.png"));

            ProductFrame productFrame = new ProductFrame(products, 1, customer);
            try {

                // Use reflection to access the private method
                Method addToCartMethod = ProductFrame.class.getDeclaredMethod("addToCartBtnActionPerformed", java.awt.event.ActionEvent.class);
                addToCartMethod.setAccessible(true);

                // Simulate an ActionEvent
                java.awt.event.ActionEvent actionEvent = new java.awt.event.ActionEvent(productFrame, 0, "command");

                // Invoke the private method
                addToCartMethod.invoke(productFrame, actionEvent);

                // Retrieve the customer's orders and check if the order was added
                ArrayList<order> orders = productFrame.cs.getOrders();
                assertEquals(1, orders.size());

                // Optionally, you can further assert the properties of the added order
                order addedOrder = orders.get(0);
                assertEquals(productFrame.showpr.getID(), addedOrder.getID());
                assertEquals(productFrame.showpr.getName(), addedOrder.getName());
                assertEquals(1, addedOrder.getQuantity());
                assertEquals(productFrame.showpr.getPrice(), addedOrder.getTotalPrice());
                assertEquals("curr", addedOrder.getType());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    @Test
    public void testBackBtnActionPerformed() {

        ArrayList<Product> products = new ArrayList<>();
        // Assuming there is a constructor with parameters in the customer class
        customer customer = new customer(1, "John Doe", null, null, null, null);

        products.add(new Product(1, 1, 7400, "GEEPAS HEATER GFH9527", "EL", "GEEPAS HEATER GFH9527",
                "C:\\Users\\MINAHIL SHAKOOR\\Desktop\\Grocery_Management_System_Java\\SMMS_Images\\Heater.png"));
        products.add(new Product(2, 1, 12500, "ADLER KETTLE 1.2L AD 1280", "EL",
                "ADLER KETTLE 1.2L AD 1280",
                "C:\\Users\\MINAHIL SHAKOOR\\Desktop\\Grocery_Management_System_Java\\SMMS_Images\\kettle.png"));

        ProductFrame productFrame = new ProductFrame(products, 1, customer);
        try {
            // Use reflection to access the private method
            Method backBtnMethod = ProductFrame.class.getDeclaredMethod("backBtnActionPerformed", java.awt.event.ActionEvent.class);
            backBtnMethod.setAccessible(true);

            // Simulate an ActionEvent
            java.awt.event.ActionEvent actionEvent = new java.awt.event.ActionEvent(productFrame, 0, "command");

            // Invoke the private method
            backBtnMethod.invoke(productFrame, actionEvent);

            // Check whether the frame is not visible
            assertFalse(productFrame.isVisible());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    }



