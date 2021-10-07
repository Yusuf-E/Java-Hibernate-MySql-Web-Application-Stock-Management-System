package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class BoxAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int box_bid;

    private String box_rid;

    @OneToOne
    private Customer customer;
    @OneToOne
    private Quantity quantity_id;



}
