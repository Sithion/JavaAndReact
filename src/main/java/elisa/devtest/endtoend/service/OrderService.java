package elisa.devtest.endtoend.service;

import elisa.devtest.endtoend.dao.OrderDao;
import elisa.devtest.endtoend.model.Order;
import elisa.devtest.endtoend.model.OrderLine;

public class OrderService {

    public int insertOrder(Order order) throws Exception {
        try {
            OrderDao orderDao = new OrderDao();
            int orderId = orderDao.insertOrder(order);
            for (OrderLine orderLine : order.getOrderLines()) {
                orderDao.insertOrderLine(orderId, orderLine);
            }
            return orderId;
        } catch (Exception error) {
            throw new Exception("error to include the order");
        }
    }
}
