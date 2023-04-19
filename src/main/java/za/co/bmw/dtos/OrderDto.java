package za.co.bmw.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import za.co.bmw.entities.Customer;
import za.co.bmw.entities.Product;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {

    private Long id;
    private Customer customerId;
    private List<Product> products;
    private double totalPrice;
    private Date orderDate;
}
