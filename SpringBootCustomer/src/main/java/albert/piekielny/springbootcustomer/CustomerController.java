package albert.piekielny.springbootcustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class CustomerController {

    private CustomerRepository customerRepository;

    @Autowired
    private void setCustomerRepository(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/Customer",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    Iterable<Customer> getCustomers(int[] creditsId){
        return customerRepository.findAll();
    }

    @PostMapping(path = "/Customer")
    public @ResponseBody Customer createCustomer(@RequestBody Customer customer) {
    return customerRepository.save(customer);
    }

}
