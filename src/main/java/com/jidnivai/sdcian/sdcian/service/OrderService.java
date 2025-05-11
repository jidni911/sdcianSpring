package com.jidnivai.sdcian.sdcian.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.dto.ConfirmOrderRequestDto;
import com.jidnivai.sdcian.sdcian.dto.NewOrderDto;
import com.jidnivai.sdcian.sdcian.dto.OrderItemStatusDto;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.shop.CartItem;
import com.jidnivai.sdcian.sdcian.entity.shop.Order;
import com.jidnivai.sdcian.sdcian.entity.shop.OrderItem;
import com.jidnivai.sdcian.sdcian.entity.shop.Product;
import com.jidnivai.sdcian.sdcian.enums.OrderStatus;
import com.jidnivai.sdcian.sdcian.interfaces.OrderServiceInt;
import com.jidnivai.sdcian.sdcian.payload.response.OperationResult;
import com.jidnivai.sdcian.sdcian.repository.OrderItemRepository;
import com.jidnivai.sdcian.sdcian.repository.OrderRepository;
import com.jidnivai.sdcian.sdcian.repository.ProductRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;

import net.sf.jasperreports.engine.JRException;

@Service
public class OrderService implements OrderServiceInt {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartService cartService;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JasperService jasperService;

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<byte[]> createOrder(NewOrderDto newOrderDto, User user) throws JRException, IOException {
        Order order = new Order();
        BeanUtils.copyProperties(newOrderDto, order);
        order.setUser(user);
        List<CartItem> cartItems = cartService.getCartItems(newOrderDto.getOrderItemIds(), user);
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getDiscountPrice() == 0 ? cartItem.getProduct().getPrice()
                    : cartItem.getProduct().getDiscountPrice());
            orderItem.setOrder(order);
            orderItem.setSeller(cartItem.getProduct().getSeller());
            orderItem.setCustomer(order.getUser());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        order = orderRepository.save(order);
        return jasperService.memo(order, order.getOrderItems());
    }

    @Override
    public Order updateOrder(Long id, Order orderDetails) {
        // Order order = orderRepository.findById(id).orElse(null);
        // if (order != null) {
        // order.setOrderNumber(orderDetails.getOrderNumber());
        // order.setStatus(orderDetails.getStatus());
        // // update other fields as needed
        // return orderRepository.save(order);
        // }
        return null;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Page<OrderItem> getOrdersForSeller(int page, int size, OrderStatus status, User seller) {
        Page<OrderItem> orderItems = orderItemRepository.findBySellerAndStatus(seller, status,
                (PageRequest.of(page, size)));
        return orderItems;
    }

    @Override
    public Page<Order> getOrdersByUser(Long userId, int page, int size) {
        return orderRepository.findByUserId(userId, PageRequest.of(page, size));
    }

    @Override
    public OperationResult updateOrderItemStatus(OrderItemStatusDto orderItemStatusDto, User user) {

        OrderItem orderItem = orderItemRepository.findById(orderItemStatusDto.getId()).orElse(null);
        if (orderItem == null) {

            return new OperationResult(false, "Order item not found");
        }
        if (!orderItem.getSeller().getId().equals(user.getId()) && !orderItem.getCustomer().getId().equals(user.getId())) {
            return new OperationResult(false, "Not authorized");
        }
        if (orderItemStatusDto.getCurrentStatus() != orderItem.getStatus()) {
            return new OperationResult(false, "Status mismatch");
        }

        switch (orderItemStatusDto.getCurrentStatus()) {
        case PENDING:
            if (orderItemStatusDto.isContinuation()) {
                if (user.getId().equals(orderItem.getSeller().getId())) {
                    orderItem.setStatus(OrderStatus.PROCESSING);
                    orderItemRepository.save(orderItem);
                } else {
                    return new OperationResult(false, "Not Permitted");
                }
            } else {
                if (user.getId().equals(orderItem.getSeller().getId())) {
                    orderItem.setStatus(OrderStatus.REJECTED);
                    orderItemRepository.save(orderItem);
                } else {
                    orderItem.setStatus(OrderStatus.CANCELLED);
                    orderItemRepository.save(orderItem);
                }
            }
            break;
        case PROCESSING:
            if (user.getId().equals(orderItem.getSeller().getId())) {
                if (orderItemStatusDto.isContinuation()) {
                    Product product = orderItem.getProduct();
                    if(product.getQuantity() - orderItem.getQuantity() >= 0){
                        product.setQuantity(product.getQuantity() - orderItem.getQuantity());
                    } else {
                        return new OperationResult(false, "Out of stock");
                    }
                    productRepository.save(product);
                    orderItem.setStatus(OrderStatus.OUT_FOR_DELIVERY);
                } else {
                    orderItem.setStatus(OrderStatus.REJECTED);
                }
                orderItemRepository.save(orderItem);
            } else {
                return new OperationResult(false, "Not Permitted");
            }

            break;
        case OUT_FOR_DELIVERY:
            if (user.getId().equals(orderItem.getSeller().getId())) {
                if (orderItemStatusDto.isContinuation()) {
                    orderItem.setStatus(OrderStatus.COMPLETED);
                } else {
                    Product product = orderItem.getProduct();
                    product.setQuantity(product.getQuantity() + orderItem.getQuantity());
                    productRepository.save(product);
                    orderItem.setStatus(OrderStatus.REFUSED);
                }
                orderItemRepository.save(orderItem);
            } else {
                return new OperationResult(false, "Not Permitted");
            }

            break;
        case COMPLETED:
        case CANCELLED:
        case REJECTED:
        case REFUSED:
            return new OperationResult(false, "Order is already " + orderItem.getStatus());
        default:
            return new OperationResult(false, "Invalid status");
        }

        return new OperationResult(true, "Success");
    }

    @Override
    public List<OrderItem> getOrderItems(Long orderItemId, User seller) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow();
        Order order = orderItem.getOrder();
        List<OrderItem> orderItems = orderItemRepository.findByOrderAndSellerAndStatus(order, seller, OrderStatus.PROCESSING);
        return orderItems;
    }

    @Override
    public ResponseEntity<byte[]> confirmOrder(ConfirmOrderRequestDto confirmOrderRequestDto, User seller) throws JRException, IOException {
        Order order = null;
        Float subTotal = 0f;
        // System.out.println(confirmOrderRequestDto);
        List<OrderItem> orderItems = confirmOrderRequestDto.getOrderItems();
        List<OrderItem> foundOrderItems = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            OrderItem foundOrderItem = orderItemRepository.findById(orderItem.getId()).orElse(null);
            if (foundOrderItem == null) {
                return null;
            }
            if (!foundOrderItem.getSeller().getId().equals(seller.getId())) {
                return null;
            }
            if (foundOrderItem.getStatus() != OrderStatus.PROCESSING) {
                return null;
            }
            foundOrderItem.setStatus(OrderStatus.OUT_FOR_DELIVERY);
            foundOrderItem.setPrice(orderItem.getPrice());
            foundOrderItem.setQuantity(orderItem.getQuantity());
            foundOrderItems.add(foundOrderItem);
            subTotal += foundOrderItem.getPrice() * foundOrderItem.getQuantity();
        }
        // orderItemRepository.saveAll(foundOrderItems);//TODO handle new items

        order = foundOrderItems.get(0).getOrder();
        order.setServiceCharge(confirmOrderRequestDto.getServiceCharge());
        order.setDeliveryCharge(confirmOrderRequestDto.getDeliveryCharge());
        order.setDiscount(confirmOrderRequestDto.getDiscount());
        order.setSubTotal(subTotal);
        order.setTotal(subTotal + order.getServiceCharge() + order.getDeliveryCharge() - order.getDiscount());

        
        return jasperService.memo(order, foundOrderItems);

    }

    

}
