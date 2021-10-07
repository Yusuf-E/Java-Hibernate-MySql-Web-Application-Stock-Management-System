package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class CheckOut {
    @Id
    @GeneratedValue()
    private int id ;

    private int checkout_amount;

}
