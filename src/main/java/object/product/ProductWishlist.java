package object.product;

import java.util.Objects;

public class ProductWishlist {
    private String name;
    private String price;

    public ProductWishlist() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductWishlist productWishlist = (ProductWishlist) o;

        if (!Objects.equals(name, productWishlist.name)) return false;
        return Objects.equals(price, productWishlist.price);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
