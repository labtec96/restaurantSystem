package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ch on 2020-05-08
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "address")
public class Address extends BaseEntity{

    private String street;
    private String numberOfHouseOrApartment;
    private String city;
    private String province;
    private String postcode;
    private String country;

}
