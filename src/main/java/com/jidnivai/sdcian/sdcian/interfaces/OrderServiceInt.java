package com.jidnivai.sdcian.sdcian.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.jidnivai.sdcian.sdcian.dto.ConfirmOrderRequestDto;
import com.jidnivai.sdcian.sdcian.dto.NewOrderDto;
import com.jidnivai.sdcian.sdcian.dto.OrderItemStatusDto;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.shop.Order;
import com.jidnivai.sdcian.sdcian.entity.shop.OrderItem;
import com.jidnivai.sdcian.sdcian.enums.OrderStatus;
import com.jidnivai.sdcian.sdcian.payload.response.OperationResult;

import net.sf.jasperreports.engine.JRException;

public interface OrderServiceInt {

    Order getOrder(Long id);

    Page<OrderItem> getOrdersForSeller(int page, int size,  OrderStatus status, User user);

    ResponseEntity<byte[]> createOrder(NewOrderDto newOrderDto, User user) throws JRException, IOException;

    Order updateOrder(Long id, Order orderEntity);

    void deleteOrder(Long id);

    Page<Order> getOrdersByUser(Long userId, int page, int size);

    OperationResult updateOrderItemStatus(OrderItemStatusDto orderItemStatusDto, User user);

    List<OrderItem> getOrderItems(Long orderItemId, User user);

    ResponseEntity<byte[]> confirmOrder(ConfirmOrderRequestDto confirmOrderRequestDto, User user) throws JRException, IOException ;

}
