package art.sales.ArtsalesManagement.service;

import art.sales.ArtsalesManagement.dao.request.CreateOrderRequest;
import art.sales.ArtsalesManagement.dao.request.FindAllOrderRequest;
import art.sales.ArtsalesManagement.dao.request.UpdateOrder;
import art.sales.ArtsalesManagement.dto.model.Order;
import art.sales.ArtsalesManagement.dto.model.enumPackage.PaymentType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

class OrderServiceImplTest {
    Order savedOrder;
    @Autowired
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .ordered_at(LocalDateTime.now())
                .paymentTime(LocalDateTime.now())
                .address("Nigeria")
                .paymentDescription("Orders Description")
                .paymentType(PaymentType.DEBIT_CARD)
                .email("adesuyiololade@gmail.com")
                .build();
         savedOrder =  orderService.saveArtOrder(createOrderRequest);
    }

    @AfterEach
    void tearDown() {
        orderService.deleteAllOrder();
    }
    @Test
    void orderCanBeSaved(){
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .ordered_at(LocalDateTime.now())
                .paymentTime(LocalDateTime.now())
                .address("Nigeria")
                .paymentDescription("Orders Description")
                .paymentType(PaymentType.DEBIT_CARD)
                .email("adesuyiololade@gmail.com")
                .build();
      Order savedOrder =  orderService.saveArtOrder(createOrderRequest);
        assertEquals("Orders Description",savedOrder.getPaymentDescription());
        assertEquals("adesuyiololade@gmail.com",savedOrder.getEmail());
        assertThat(savedOrder.getId()).isNotNull();
        assertEquals(2L, orderService.size());
    }
    @Test
    void orderCanBefindById(){
      Order foundOrder =  orderService.findById(savedOrder.getId());
        assertThat(foundOrder.getId()).isEqualTo(savedOrder.getId());
    }

    @Test
    void findAllOrders(){
        FindAllOrderRequest findAllOrder = FindAllOrderRequest.builder()
                .orderId(savedOrder.getId())
                .numberOfPages(2)
                .pages(2)
                .build();
       Page<Order>foundOrder =  orderService.findAllOrders(findAllOrder);
        assertThat(foundOrder.getTotalElements()).isNotNull();
        assertEquals(1L, foundOrder.getTotalElements());
    }

    @Test
    void deleteAllOrders(){
        orderService.deleteAllOrder();
        assertEquals(0, orderService.size());
    }

//    @Test
//    void deleteOrderById(){
//        orderService.deleteOrderById(savedOrder.getId());
//        assertEquals(0, orderService.size());
//    }

    @Test
    void OrderCanBeUpdated(){
        UpdateOrder updateOrder = UpdateOrder.builder()
                .id(savedOrder.getId())
                .address("Ghana")
                .paymentDescription("Art Description")
                .paymentType(PaymentType.MOBILE_PAYMENT)
                .email("ololade@gmail.com")
                .updatedAt(LocalDateTime.now())
                .build();
      Order updatedOrder =  orderService.updateOrder(updateOrder);
      assertEquals("Ghana", updatedOrder.getAddress());
        assertEquals("Art Description", updatedOrder.getPaymentDescription());
        assertEquals("ololade@gmail.com", updatedOrder.getEmail());
        assertEquals(PaymentType.MOBILE_PAYMENT, updatedOrder.getPaymentType());
        System.out.println("updated order is" + updatedOrder);



    }






}