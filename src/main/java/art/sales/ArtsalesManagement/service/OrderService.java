package art.sales.ArtsalesManagement.service;

import art.sales.ArtsalesManagement.dao.request.CreateOrderRequest;
import art.sales.ArtsalesManagement.dao.request.FindAllOrderRequest;
import art.sales.ArtsalesManagement.dao.request.UpdateOrder;
import art.sales.ArtsalesManagement.dto.model.Order;
import org.springframework.data.domain.Page;

public interface OrderService {
    Order saveArtOrder(CreateOrderRequest createOrderRequest);

    long size();

    String deleteAllOrder();

    Order findById(Long id);

  Page<Order> findAllOrders(FindAllOrderRequest findAllOrder);

    String deleteOrderById(Long id);

    Order updateOrder(UpdateOrder updateOrder);
}
