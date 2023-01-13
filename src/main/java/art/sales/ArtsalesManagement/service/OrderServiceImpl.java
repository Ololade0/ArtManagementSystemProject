package art.sales.ArtsalesManagement.service;

import art.sales.ArtsalesManagement.dao.model.Order;
import art.sales.ArtsalesManagement.dao.model.enumPackage.PaymentType;
import art.sales.ArtsalesManagement.dao.repository.OrderRepository;
import art.sales.ArtsalesManagement.dto.request.CreateOrderRequest;
import art.sales.ArtsalesManagement.dto.request.FindAllOrderRequest;
import art.sales.ArtsalesManagement.dto.request.UpdateOrder;
import art.sales.ArtsalesManagement.exception.OrderCannotBeFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order saveArtOrder(CreateOrderRequest createOrderRequest) {
        Order order = Order.builder()
                .ordered_at(LocalDateTime.now())
                .address(createOrderRequest.getAddress())
                .paymentDescription(createOrderRequest.getPaymentDescription())
                .paymentType(createOrderRequest.getPaymentType())
                .paymentTime(LocalDateTime.now())
                .build();
             return orderRepository.save(order);
    }

    @Override
    public long size() {
        return orderRepository.count();
    }

    @Override
    public String deleteAllOrder() {
        orderRepository.deleteAll();
        return "All orders successfully deleted";
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> foundOrder = orderRepository.findById(id);
        return foundOrder.orElse(null);
    }

    @Override
    public Page<Order> findAllOrders(FindAllOrderRequest findAllOrder) {
        Pageable pageable = PageRequest.of(findAllOrder.getPages() - 1, findAllOrder.getNumberOfPages());
        return orderRepository.findAll(pageable);
    }

//    @Override
//    public String deleteOrderById(Long id) {
//        Optional<Order> foundOrder = orderRepository.findById(id);
//        if (foundOrder.isPresent()) {
//            orderRepository.deleteById(id);
//            return "Order successfully deleted";
//        } else {
//            throw new OrderCannotBeFoundException(OrderCannotBeFoundException.OrderCannotBeFoundException(id));
//        }
//
//    }

    @Override
    public Order updateOrder(UpdateOrder updateOrder) {
        Optional<Order> foundOrder = orderRepository.findById(updateOrder.getId());
        if (foundOrder.isPresent()) {
            if (updateOrder.getAddress() != null) {
                foundOrder.get().setAddress(updateOrder.getAddress());
            }

            if (updateOrder.getPaymentDescription() != null) {
                foundOrder.get().setPaymentDescription(updateOrder.getPaymentDescription());
            }
            if (updateOrder.getPaymentType() != null) {
                foundOrder.get().setPaymentType(updateOrder.getPaymentType());
            }
            return orderRepository.save(foundOrder.get());
        } else {
            throw new OrderCannotBeFoundException(OrderCannotBeFoundException.OrderCannotBeFoundException(updateOrder.getId()));
        }
    }

}
