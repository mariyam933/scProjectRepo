import static org.junit.jupiter.api.Assertions.*;

class LoginFrameTest {

    @org.junit.jupiter.api.Test
    // Note: Use the fully qualified name of the annotation above
    public void testLoginWithValidCredentials() {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.IDField.setText("1");
        loginFrame.pssField.setText("123");

        loginFrame.loginBtn.doClick();
    }

    @org.junit.jupiter.api.Test
    public void testLoginWithInvalidCredentials() {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.IDField.setText("1");
        loginFrame.pssField.setText("1234");

        loginFrame.loginBtn.doClick();

    }

    @org.junit.jupiter.api.Test
    public void testLoginWithEmptyFields() {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.IDField.setText("");
        loginFrame.pssField.setText("");

        loginFrame.loginBtn.doClick();

    }


}
