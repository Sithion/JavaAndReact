package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.model.Customer;
import elisa.devtest.endtoend.model.Order;
import elisa.devtest.endtoend.model.OrderLine;

import elisa.devtest.endtoend.model.Product;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class OrderDao {

    public List<Order> findOrders() {
        try {
            return createJdbcTemplate().query("select * from orders",
                    (resultSet, rowNumber) -> new Order(resultSet.getLong("order_id"), findCustomer(resultSet.getLong("customer_id")), findOrderLines(resultSet.getLong("order_id"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public int insertOrder(Order order) {
        String INSERT_SQL = "INSERT INTO orders (customer_ID) VALUES (?);";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getDataSource());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps =
                    connection.prepareStatement(INSERT_SQL, new String[]{"id"});
            ps.setLong(1, order.getCustomer().getCustomerId());
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public int insertOrderLine(int orderId, OrderLine orderLine) {
        String INSERT_SQL = "INSERT INTO order_line (order_id ,product_id ,product_name,quantity ) VALUES (?);";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getDataSource());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps =
                    connection.prepareStatement(INSERT_SQL, new String[]{"id"});
            ps.setInt(1, orderId);
            ps.setString(2, orderLine.getProduct().getId());
            ps.setString(3, orderLine.getProduct().getName());
            ps.setInt(4, orderLine.getQuantity());
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().intValue();
    }

    private List<OrderLine> findOrderLines(long orderId) {
        try {
            return createJdbcTemplate().query("select * from order_line where order_id = ?",
                    new Object[]{orderId},
                    (resultSet, rowNumber) -> new OrderLine(
                            resultSet.getLong("order_line_id"),
                            new Product(
                                    resultSet.getString("product_id"),
                                    resultSet.getString("product_name")
                            ),
                            resultSet.getInt("quantity")
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public Customer findCustomer(final long customerId) {
        try {
            return createJdbcTemplate().queryForObject("select * from customer where customer_id = ?", new Object[]{customerId}, (resultSet, rowNumber) -> new Customer(resultSet.getLong("customer_id"), resultSet.getString("company_name"), resultSet.getString("street"), resultSet.getString("postal_code"), resultSet.getString("city"), resultSet.getString("country")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Customer();
    }

    private JdbcTemplate createJdbcTemplate() {
        return new JdbcTemplate(DBConnection.getDataSource());
    }

}
