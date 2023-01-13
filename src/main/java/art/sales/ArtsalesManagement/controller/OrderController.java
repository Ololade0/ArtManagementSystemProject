package art.sales.ArtsalesManagement.controller;

import art.sales.ArtsalesManagement.dao.model.Order;
import art.sales.ArtsalesManagement.dto.request.CreateOrderRequest;
import art.sales.ArtsalesManagement.dto.request.FindAllOrderRequest;
import art.sales.ArtsalesManagement.dto.request.UpdateOrder;
import art.sales.ArtsalesManagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> PlaceOrders(@RequestBody CreateOrderRequest createOrderRequest){
      Order createOrderResponse = orderService.saveArtOrder(createOrderRequest);
        return new ResponseEntity<>(createOrderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> findOrders(@PathVariable Long orderId){
        Order foundOrder = orderService.findById(orderId);
        return new ResponseEntity<>(foundOrder, HttpStatus.CREATED);
    }

    @GetMapping("/findOrders")
    public ResponseEntity<?> findAllOrders(FindAllOrderRequest findAllOrderRequest){
        Page<Order> foundOrder = orderService.findAllOrders(findAllOrderRequest);
        return new ResponseEntity<>(foundOrder, HttpStatus.CREATED);
    }

    @PutMapping("/updateOrders")
    public ResponseEntity<?> updateOrders(UpdateOrder updateOrder){
      Order updatedOrder  = orderService.updateOrder(updateOrder);
        return new ResponseEntity<>(updatedOrder, HttpStatus.CREATED);
    }
}
