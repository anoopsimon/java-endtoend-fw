package core.data;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;

import java.util.Locale;

public class RandomData
{
    public static String firstName(){
        return new Faker().name().firstName();
    }
    public static String lastName(){
        return new Faker().name().firstName();
    }

    public static String fullName(){
        return new Faker().name().fullName();
    }

    public static Address address(){
        Faker ukFaker = new Faker(new Locale("en-AU"));

        return ukFaker.address();
    }

    public static PhoneNumber mobile(){
        Faker ukFaker = new Faker(new Locale("en-AU"));

        return ukFaker.phoneNumber();
    }

    public static void main(String[] args) {
        System.out.println(address().fullAddress());
    }
}
