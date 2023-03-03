package tests;

import magentoPage.*;
import object.product.ProductCart;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCartTest extends BaseTest {
    @Test
    public void verifyAddCart() {
        LoginPage loginPage = new LoginPage();
        AccountPage accountPage = loginPage.openAccountPage(validUser);
        ProductsPage productsPage = accountPage.openProductsPage();
        ProductPage productPage = null;
        List<ProductCart> randomProducts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            productPage = productsPage.openProductPage();
            ProductCart randomProduct = productPage.getProduct();
            productPage.addedProductToCart();
            randomProducts.add(randomProduct);
            productPage.backProductsPage();
        }
        CartPage cartPage = productPage.openCartPage();
        List<ProductCart> cartProduct = cartPage.getAllProducts();
        assertTrue(CollectionUtils.isEqualCollection(randomProducts, cartProduct), "Products not added to cart");
    }
}
