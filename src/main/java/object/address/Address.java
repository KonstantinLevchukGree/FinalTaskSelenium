package object.address;

import java.util.Objects;

public class Address {
    private String phone;
    private String street;
    private String city;
    private String zipCode;
    private String country;

    public Address(String phone, String street, String city, String zipCode, String country) {
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        if (!Objects.equals(phone, address.phone)) return false;
        if (!Objects.equals(street, address.street)) return false;
        if (!Objects.equals(city, address.city)) return false;
        if (!Objects.equals(zipCode, address.zipCode)) return false;
        return Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        int result = phone != null ? phone.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
