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
public class Cook extends User{

    private int salary;

    private String accountNumber;

    private String numberOfKitchen;

    public Cook(){
        super();
    }
}
