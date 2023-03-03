package tests;

import magentoPage.AccountPage;
import magentoPage.LoginPage;
import magentoPage.ProductsPage;
import magentoPage.WishlistPage;
import object.product.ProductWishlist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddWishlistTest extends BaseTest {
    @Test
    public void verifyAddWishlist() {
        LoginPage loginPage = new LoginPage();
        AccountPage accountPage = loginPage.openAccountPage(validUser);
        ProductsPage productsPage = accountPage.openProductsPage();
        ProductWishlist randomProductWishlist = productsPage.getProductWishlist();
        WishlistPage wishlistPage = productsPage.openWishlistPage();
        ProductWishlist productWishlistFromWishList = wishlistPage.getProductFromWishList();
        assertEquals(productWishlistFromWishList, randomProductWishlist, "Product not added to Wishlist");
    }
}
