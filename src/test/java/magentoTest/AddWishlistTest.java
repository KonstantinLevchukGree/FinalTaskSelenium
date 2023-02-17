package magentoTest;

import magentoPage.AccountPage;
import magentoPage.LoginPage;
import magentoPage.WishlistPage;
import magentoPage.ProductsPage;
import org.junit.jupiter.api.Test;
import object.product.ProductWishlist;
import utils.singleton.SingletonInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddWishlistTest extends BaseTest {
    @Test
    public void verifyAddWishlist() {
        LoginPage loginPage = new LoginPage(SingletonInstance.getInstance().getDriver());
        AccountPage accountPage = loginPage.openAccountPage(validUser);
        ProductsPage productsPage = accountPage.openProductsPage();
        ProductWishlist randomProductWishlist = productsPage.getProductWishlist();
        WishlistPage wishlistPage = productsPage.openWishlistPage();
        ProductWishlist productWishlistFromWishList = wishlistPage.getProductFromWishList();
        assertEquals(productWishlistFromWishList, randomProductWishlist, "Product not added to Wishlist");
    }
}
