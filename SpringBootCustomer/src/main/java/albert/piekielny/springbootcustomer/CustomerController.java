package albert.piekielny.springbootcustomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            method = RequestMethod.POST,
            path = "/Customers",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    List<Customer> getCustomers(HttpServletRequest request){

        List<Customer> customerInfo = new ArrayList<>();
        try {
            List<String> creditsIdObject = IOUtils.readLines(request.getInputStream(), "UTF-8");
            String result = creditsIdObject.get(0).substring(1, creditsIdObject.get(0).length() - 1);
            String[] idCredits = result.split(",");
            for (String id: idCredits) {
                this.customerRepository.findById((Integer.parseInt(id))).ifPresent(customerInfo::add);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customerInfo;
    }


    @PostMapping(path = "/Customer")
    public @ResponseBody Customer createCustomer(HttpServletRequest request) {

        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = null;
        try {
            customer = objectMapper.readValue(request.getInputStream(), Customer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerRepository.save(customer);
    }

}
