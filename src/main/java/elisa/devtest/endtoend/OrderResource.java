package elisa.devtest.endtoend;

import elisa.devtest.endtoend.dao.OrderDao;
import elisa.devtest.endtoend.model.Order;
import elisa.devtest.endtoend.service.OrderService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/orders")
public class OrderResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Order> getOrders() {
        return new OrderDao().findOrders();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProduct(Order order) {

        try {
            new OrderService().insertOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
