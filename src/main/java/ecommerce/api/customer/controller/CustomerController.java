package ecommerce.api.customer.controller;

import ecommerce.api.customer.entity.Customer;
import ecommerce.api.customer.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    // 1. Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    // 2. Add new customer
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    //  3. Update existing customer by ID
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Optional<Customer> existingCustomerOpt = repository.findById(id);

        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            return repository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer with ID " + id + " not found");
        }
    }

    // 4. Delete customer by ID
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Customer with ID " + id + " deleted successfully!";
        } else {
            return "Customer with ID " + id + " not found!";
        }
    }
}
