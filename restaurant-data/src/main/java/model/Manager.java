package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by ch on 2020-05-19
 */
@Setter
@Getter
@Entity
public class Manager extends User{

    private String numberOfRestaurantRoom;

    private int salary;

    private String accountNumber;

    public Manager(){
        super();
    }
}
