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
@NoArgsConstructor
@Entity
public class Cook extends Worker{

    private String numberOfKitchen;
}
