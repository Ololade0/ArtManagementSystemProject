package art.sales.ArtsalesManagement.service;

import art.sales.ArtsalesManagement.dao.request.CreateOrderRequest;
import art.sales.ArtsalesManagement.dao.request.FindAllOrder;
import art.sales.ArtsalesManagement.dao.request.UpdateOrder;
import art.sales.ArtsalesManagement.dto.model.Art;
import art.sales.ArtsalesManagement.dto.model.Order;
import art.sales.ArtsalesManagement.dto.model.enumPackage.PaymentType;
import art.sales.ArtsalesManagement.dto.repository.OrderRepository;
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
//        Order order = Order.builder()
//                .email(createOrderRequest.getEmail())
//                .ordered_at(LocalDateTime.now())
//                .address(createOrderRequest.getAddress())
//                .paymentDescription(createOrderRequest.getPaymentDescription())
//                .paymentTime(LocalDateTime.now())
//                .paymentType(PaymentType.DEBIT_CARD)
//                .build();
//        return orderRepository.save(order);
        ModelMapper modelMapper = new ModelMapper();
        Order map =   modelMapper.map(createOrderRequest, Order.class);
        return orderRepository.save(map);


    }

    @Override
    public long size() {
        return orderRepository.count();
    }

    @Override
    public String deleteAllOrder() {
        orderRepository.deleteAll();;
        return "All orders successfully deleted";
    }

    @Override
    public Order findById(Long id) {
      Optional<Order> foundOrder = orderRepository.findById(id);
        return foundOrder.orElse(null);
    }

    @Override
    public Page<Order> findAllOrders(FindAllOrder findAllOrder) {
        Pageable pageable = PageRequest.of(findAllOrder.getPages()-1, findAllOrder.getNumberOfPages());
        return orderRepository.findAll(pageable);
    }

    @Override
    public String deleteOrderById(Long id) {
        Optional<Order> foundOrder = orderRepository.findById(id);
        if(foundOrder.isPresent()){
            orderRepository.deleteById(id);
            return "Order successfully deleted";
        }
        else{
            throw new OrderCannotBeFoundException(OrderCannotBeFoundException.OrderCannotBeFoundException(id));
        }

    }

    @Override
    public Order updateOrder(UpdateOrder updateOrder) {

        return null;
    }
}
