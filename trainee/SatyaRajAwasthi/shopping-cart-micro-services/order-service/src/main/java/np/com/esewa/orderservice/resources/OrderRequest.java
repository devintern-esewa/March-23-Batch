package np.com.esewa.orderservice.resources;

import lombok.*;
import np.com.esewa.orderservice.documents.OrderedProduct;
import np.com.esewa.orderservice.enums.PaymentMode;

import java.util.List;

/**
 * @author SatyaRajAwasth1
 * Written on: 5/10/2023
 * @project orderservice
 * a DTO class as a resource for getting as request entity for order
 */
@Data
@Builder
public class OrderRequest {
    private float totalAmount;
    private String deliveryAddress;
    private PaymentMode paymentMode;
    private List<OrderedProduct> cartItems;
}
