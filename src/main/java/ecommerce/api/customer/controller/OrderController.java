package ecommerce.api.customer.controller;

import ecommerce.api.customer.entity.Order;
import ecommerce.api.customer.entity.Customer;
import ecommerce.api.customer.entity.Product;
import ecommerce.api.customer.repository.OrderRepository;
import ecommerce.api.customer.repository.CustomerRepository;
import ecommerce.api.customer.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Create new order
    @PostMapping
    public Order createOrder(@RequestBody OrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        double total = product.getPrice() * request.getQuantity();
        Order order = new Order(customer, product, request.getQuantity(), total, LocalDateTime.now());
        return orderRepository.save(order);
    }

    // Delete order
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }

    // Helper inner class for JSON request
    public static class OrderRequest {
        private Long customerId;
        private Long productId;
        private int quantity;

        public Long getCustomerId() { return customerId; }
        public void setCustomerId(Long customerId) { this.customerId = customerId; }

        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}
