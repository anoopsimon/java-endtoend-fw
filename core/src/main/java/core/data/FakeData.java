package core.data;

import java.util.Locale;

public class FakeData {

    public String getAnswer(String keyword){
        if(keyword.isEmpty()) throw new RuntimeException("Keyword can't be empty.. ");

        switch (keyword.toUpperCase(Locale.ROOT)){
            case "RANDOM_FIRSTNAME":
                return RandomData.firstName();
            case "RANDOM_LASTNAME":
                return RandomData.lastName();
            case "RANDOM_ADDRESS_FULL":
                return RandomData.address().fullAddress();
            case "RANDOM_ADDRESS_STREETNAME":
                return RandomData.address().streetName();
            case "RANDOM_ADDRESS_STREETNUMBER":
                return RandomData.address().streetAddressNumber();
                case "RANDOM_ADDRESS_POSTCODE":
                return RandomData.address().zipCode();
            default:
                throw new RuntimeException("Keyword '" +keyword+ "' not supported");
        }
    }
}
