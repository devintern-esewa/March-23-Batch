package np.com.esewa.orderservice.documents;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import np.com.esewa.orderservice.enums.OrderStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/10/2023
 * @project orderservice
 * database entity instance for orders
 */
@Setter
@Getter
@Builder
@Document(collection = "orders")
public class Order {

//    public static final String SEQUENCE_NAME = "orders_sequence";

    @Id
    private String id;

    private Instant orderDate;

    private OrderStatus orderStatus;

    private String deliveryAddress;

    private float amount;

    @DBRef
    private List<OrderedProduct> orderedProduct;
}
