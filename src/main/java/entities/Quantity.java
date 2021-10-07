package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class Quantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quantity_id;

    private int quantity;

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Stock> stocks;
}
