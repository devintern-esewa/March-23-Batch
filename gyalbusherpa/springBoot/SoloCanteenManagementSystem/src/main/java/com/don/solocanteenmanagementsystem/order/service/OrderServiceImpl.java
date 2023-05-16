package com.don.solocanteenmanagementsystem.order.service;

import com.don.solocanteenmanagementsystem.customer.exception.CustomerDoNotExistException;
import com.don.solocanteenmanagementsystem.customer.model.Customer;
import com.don.solocanteenmanagementsystem.customer.repository.CustomerRepository;
import com.don.solocanteenmanagementsystem.fooditem.dto.FoodDtoForOrder;
import com.don.solocanteenmanagementsystem.fooditem.exception.FoodItemDoNotExistException;
import com.don.solocanteenmanagementsystem.fooditem.model.FoodItem;
import com.don.solocanteenmanagementsystem.fooditem.model.StoreFoodQuantity;
import com.don.solocanteenmanagementsystem.fooditem.repository.FoodItemRepository;
import com.don.solocanteenmanagementsystem.fooditem.repository.StoreFoodQuantityRepository;
import com.don.solocanteenmanagementsystem.order.dto.RequestOrderDto;
import com.don.solocanteenmanagementsystem.order.dto.ResponseOrderDto;
import com.don.solocanteenmanagementsystem.order.exception.OrderIdNotFoundException;
import com.don.solocanteenmanagementsystem.order.model.Order;
import com.don.solocanteenmanagementsystem.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final FoodItemRepository foodItemRepository;
    private final CustomerRepository customerRepository;
    private final StoreFoodQuantityRepository foodQuantityRepository;

    @Override
    public void placeOrder(RequestOrderDto requestOrderDto) {

        Order order = new Order();

        Optional<Customer> customer = customerRepository.findById(requestOrderDto.getCustomerId());
        if (customer.isPresent()) {
            order.setCustomer(customer.get());
        } else {
            throw new CustomerDoNotExistException("This customer doesn't exist");
        }

        List<FoodItem> foodItems = new ArrayList<>();

        List<FoodDtoForOrder> foodDto = requestOrderDto.getFoodItems();

        double totalPrice = 0;

        for (FoodDtoForOrder foods : foodDto) {

            Optional<FoodItem> foodItemsFromRepo = foodItemRepository.findFoodItemByName(foods.getFoodName());
            if (!foodItemsFromRepo.isPresent()) {
                throw new FoodItemDoNotExistException("Food item with name " + foods.getFoodName() + " do not exist");
            }

            totalPrice += foodItemsFromRepo.get().getPrice() * foods.getQuantity();

            StoreFoodQuantity foodQuantity = new StoreFoodQuantity();
            foodQuantity.setQuantity(foods.getQuantity());
            foodQuantity.setCustomerId(order.getCustomer().getCustomerId());
            foodQuantity.setFoodId(foodItemsFromRepo.get().getFoodId());
            foodQuantityRepository.save(foodQuantity);

            foodItems.add(foodItemsFromRepo.get());
        }

        order.setTotalPrice(totalPrice);
        order.setFoodItems(foodItems);
        order.setQuantity(foodItems.size());

        orderRepository.save(order);
    }

    @Override
    public List<ResponseOrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        List<ResponseOrderDto> responseOrderDtoArrayList = new ArrayList<>();

        for (Order order : orders) {

            ResponseOrderDto responseOrderDto = new ResponseOrderDto();

            responseOrderDto.setTotalPrice(order.getTotalPrice());
            responseOrderDto.setQuantity(order.getQuantity());
            responseOrderDto.setCustomerId(order.getCustomer().getCustomerId());

            List<FoodDtoForOrder> foodDtoForOrderList = new ArrayList<>();

            List<FoodItem> items = order.getFoodItems();

            for (FoodItem item : items) {
                FoodDtoForOrder foodDtoForOrder = new FoodDtoForOrder();
                foodDtoForOrder.setFoodName(item.getName());
                int quantity = foodQuantityRepository.findQuantityByTwoIds(item.getFoodId(),
                        order.getCustomer().getCustomerId());
                foodDtoForOrder.setQuantity(quantity);

                foodDtoForOrderList.add(foodDtoForOrder);
            }

            responseOrderDto.setFoodItems(foodDtoForOrderList);
            responseOrderDtoArrayList.add(responseOrderDto);
        }

        return responseOrderDtoArrayList;
    }

    @Override
    public ResponseOrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new OrderIdNotFoundException("This order id doesn't exist")
        );

        ResponseOrderDto dto = new ResponseOrderDto();

        dto.setTotalPrice(order.getTotalPrice());
        dto.setQuantity(order.getQuantity());
        dto.setCustomerId(order.getCustomer().getCustomerId());

        List<FoodItem> foodDtoForOrderList = order.getFoodItems();
        List<FoodDtoForOrder> orders = new ArrayList<>();
        for (FoodItem foodItem : foodDtoForOrderList) {
            FoodDtoForOrder foodDtoForOrder = new FoodDtoForOrder();
            foodDtoForOrder.setFoodName(foodItem.getName());

            int quantity = foodQuantityRepository.findQuantityByTwoIds(foodItem.getFoodId(),
                    order.getCustomer().getCustomerId());
            foodDtoForOrder.setQuantity(quantity);

            foodDtoForOrder.setQuantity(quantity);

            orders.add(foodDtoForOrder);
        }
        dto.setFoodItems(orders);

        return dto;
    }
}
