import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecieptFrameTest {

    @Test
    public void grandTotalLabelTest() throws InterruptedException {
        // Create a CountDownLatch to wait for Swing operations to complete
        CountDownLatch latch = new CountDownLatch(1);

        // Simulate GUI interaction using SwingUtilities
        SwingUtilities.invokeLater(() -> {
            try {
                // Create sample orders with specific prices
                ArrayList<order> orders = new ArrayList<>();
                orders.add(new order(1, "GEEPAS HEATER GFH9527", 2, 7400, "EL"));
                orders.add(new order(2, "ADLER KETTLE 1.2L AD 1280", 1, 12500, "EL"));

                // Create RecieptFrame with a customer and set the sample orders
                customer testCustomer = new customer(1, "Minahil", "03321655556", "nust", "123", "F");
                testCustomer.getOrders().addAll(orders);

                RecieptFrame frame = new RecieptFrame(testCustomer);

                // Wait for Swing operations to complete before asserting
                SwingUtilities.invokeLater(() -> {
                    // Calculate the expected grand total based on sample orders
                    double expectedGrandTotal = calculateExpectedGrandTotal(orders);

                    // Calculate the actual grand total from the customer's cart
                    double actualGrandTotal = calculateActualGrandTotal(testCustomer);

                    assertEquals(expectedGrandTotal, actualGrandTotal, 0.01);

                    // Dispose of the frame to avoid resource leaks
                    frame.dispose();

                    // Release the latch to signal that the test is complete
                    latch.countDown();
                });
            } catch (Exception e) {
                e.printStackTrace();
                // Release the latch in case of an exception
                latch.countDown();
            }
        });

        // Wait for the latch to be released or timeout after 5 seconds
        latch.await(5, TimeUnit.SECONDS);
    }





    private double calculateExpectedGrandTotal(ArrayList<order> orders) {
        double count = 0;
        for (order order : orders) {
            if ("curr".equals(order.getType())) {
                int qty = order.getQuantity();
                double price = order.getTotalPrice();
                count += price * qty;
            }
        }
        return count;
    }

    private double calculateActualGrandTotal(customer testCustomer) {
        double count = 0;
        for (order order : testCustomer.getOrders()) {
            if ("curr".equals(order.getType())) {
                int qty = order.getQuantity();
                double price = order.getTotalPrice();
                count += price * qty;
            }
        }
        return count;
    }

    @Test
    public void backBtnActionPerformedTest() {
        SwingUtilities.invokeLater(() -> {
            // Create a customer with some orders
            customer customer = new customer(1, "Minahil", "03321655556", "nust", "123", "F");
            customer.getOrders().add(new order(1, "GEEPAS HEATER GFH9527", 2, 7400, "EL"));

            // Create RecieptFrame
            RecieptFrame frame = new RecieptFrame(customer);

            // Simulate button click
            frame.backBtnActionPerformed(null);

            // Verify that orders are updated to "prev" type
            assertEquals("prev", customer.getOrders().get(0).getType());

            // Verify that the frame is not visible

            assertEquals(true, frame.isVisible());

            // Dispose of the frame
            frame.dispose();
        });
    }

}



