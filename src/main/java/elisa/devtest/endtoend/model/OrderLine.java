package elisa.devtest.endtoend.model;


public class OrderLine {
    private long orderLineId;
    private Product product;
    private int quantity;

    public OrderLine(long orderLineId, Product product, int quantity) {
        this.orderLineId = orderLineId;
        this.product = product;
        this.quantity = quantity;
    }

    public long getOrderLineId() {
        return orderLineId;
    }

    public Product getProduct() {
        return product;
    }


    public int getQuantity() {
        return quantity;
    }
}
