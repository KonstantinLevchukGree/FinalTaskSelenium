package magentoTest;

import magentoPage.*;
import object.product.ProductCart;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import utils.singleton.SingletonInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCartTest extends BaseTest {
    @Test
    public void verifyAddCart() {
        LoginPage loginPage = new LoginPage(SingletonInstance.getInstance().getDriver());
        AccountPage accountPage = loginPage.openAccountPage(validUser);

        ProductsPage productsPage1 = accountPage.openProductsPage();
        ProductPage productPage = productsPage1.openProductPage();
        ProductCart randomProduct1 = productPage.getProduct();
        productPage.addedProductToCart();
        ProductsPage productsPage2 = productPage.backProductsPage();

        ProductPage productPage2 = productsPage2.openProductPage();
        ProductCart randomProduct2 = productPage2.getProduct();
        productPage2.addedProductToCart();
        ProductsPage productsPage3 = productPage.backProductsPage();

        ProductPage productPage3 = productsPage3.openProductPage();
        ProductCart randomProduct3 = productPage3.getProduct();
        productPage3.addedProductToCart();

        List<ProductCart> randomProducts = new ArrayList<>();
        randomProducts.add(randomProduct1);
        randomProducts.add(randomProduct2);
        randomProducts.add(randomProduct3);

        CartPage cartPage = productPage3.openCartPage();
        List<ProductCart> cartProduct = cartPage.getAllProducts();
        assertTrue(CollectionUtils.isEqualCollection(randomProducts, cartProduct), "Products not added to cart");
    }
}
