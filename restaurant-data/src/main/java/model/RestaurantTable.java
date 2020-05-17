package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ch on 2020-05-14
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "restaurant_table")
public class RestaurantTable extends BaseEntity{
    private String number;
    private String floor;
    private int maxNumberOfPeople;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "restaurantTable")
    private Set<Reservation> reservations = new HashSet<>();
}
