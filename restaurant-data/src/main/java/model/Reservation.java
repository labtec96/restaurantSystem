package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by ch on 2020-05-16
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation extends BaseEntity{

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurantTable_id")
    private RestaurantTable restaurantTable;

}
