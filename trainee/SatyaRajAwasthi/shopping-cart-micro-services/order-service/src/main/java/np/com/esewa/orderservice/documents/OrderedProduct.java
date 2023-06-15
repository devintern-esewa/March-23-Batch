package np.com.esewa.orderservice.documents;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author SatyaRajAwasth1
 * Written on: 6/14/2023
 * @project shopping-cart-micro-services
 * @description @
 */

@Getter
@Setter
@Builder
@Document
public class OrderedProduct {
    @Id
    private int id;
    private long productId;
    private int quantity;
}
