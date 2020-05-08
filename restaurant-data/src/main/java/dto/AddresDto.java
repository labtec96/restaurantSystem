package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;

/**
 * Created by ch on 2020-05-08
 */
@Getter
@Setter
@NoArgsConstructor
public class AddresDto {

    private Long id;
    private String street;
    private String numberOfHouseOrApartment;
    private String city;
    private String province;
    private String postcode;
    private String country;

    public boolean checkNull() {
        if(street != null && numberOfHouseOrApartment != null && city != null && province != null &&
                postcode != null && country != null)
            return false;
        return true;
    }
}
