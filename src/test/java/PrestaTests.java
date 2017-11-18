public class PrestaTests {
    PrestaDriver pd = null;

    PrestaTests() {
        this.pd = new PrestaDriver();
    }

    public void testA() {
        pd.logIn();
        pd.logOut();
    }

    public void testB() {
        pd.logIn();
        pd.checkMenu();
        pd.closeBrowser();
    }
}
