package art.sales.ArtsalesManagement.service;

import art.sales.ArtsalesManagement.dao.model.Order;
import art.sales.ArtsalesManagement.dto.request.CreateOrderRequest;
import art.sales.ArtsalesManagement.dto.request.FindAllOrderRequest;
import art.sales.ArtsalesManagement.dto.request.UpdateOrder;
import org.springframework.data.domain.Page;

public interface OrderService {
    Order saveArtOrder(CreateOrderRequest createOrderRequest);

    long size();

    String deleteAllOrder();

    Order findById(Long id);

  Page<Order> findAllOrders(FindAllOrderRequest findAllOrder);
    Order updateOrder(UpdateOrder updateOrder);
}
