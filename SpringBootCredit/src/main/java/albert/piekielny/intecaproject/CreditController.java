package albert.piekielny.intecaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/")
public class CreditController {

    private CreditRepository creditRepository;

    @Autowired
    public void setCreditRepository(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }


    @GetMapping(path = "/Credit")
    public @ResponseBody
    int createCredit(@RequestParam String nameOfCredit,
                     @RequestParam String clientName,
                     @RequestParam String clientSurname,
                     @RequestParam String clientPesel,
                     @RequestParam String productName,
                     @RequestParam int productValue) {

        Credit credit = new Credit(nameOfCredit);
        int currentAmountsOFCredits = (int) creditRepository.count() + 1;

        createProduct(currentAmountsOFCredits, productValue, productName);
        createCustomer(currentAmountsOFCredits, clientName, clientSurname, clientPesel);

        creditRepository.save(credit);
        return currentAmountsOFCredits; // numer nadanego credytu
    }

    @GetMapping(path = "/Credits")
    public @ResponseBody
    Iterable<Credit> getAllCredits() {


        Iterable<Credit> creditsInfo = creditRepository.findAll();
        ArrayList<Integer> creditsId = new ArrayList<>();
        creditsInfo.forEach(credit -> creditsId.add(credit.getId()));

        Product[] productInfo = getProducts(creditsId);
        Customer[] customerInfo = getCustomers(creditsId);

        for (Customer c: customerInfo) {
            System.out.println(c);
        }

        return creditsInfo;
    }

    private Customer[] getCustomers(ArrayList<Integer> creditsId){

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ArrayList<Integer>> request = new HttpEntity<>(creditsId);

        ResponseEntity<Customer[]> customers = restTemplate.exchange("http://localhost:8082/Customers",
                HttpMethod.POST,
                request,
                Customer[].class);

        return customers.getBody();
    }

    private Product[] getProducts(ArrayList<Integer> creditsId){

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ArrayList<Integer>> request = new HttpEntity<>(creditsId);

        ResponseEntity<Product[]> productInfo = restTemplate.exchange("http://localhost:8081/Products",
                HttpMethod.POST,
                request,
                Product[].class);

        return productInfo.getBody();
    }

    private void createProduct(int currentAmountsOFCredits, int productValue, String productName) {
        HttpEntity<Product> request = new HttpEntity<>(new Product(currentAmountsOFCredits, productValue, productName));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange("http://localhost:8081/Product",
                HttpMethod.POST,
                request,
                Product.class);
    }

    private void createCustomer(int currentAmountsOFCredits, String clientName, String clientSurname, String clientPesel) {

        HttpEntity<Customer> customerHttpEntity = new HttpEntity<>(new Customer(currentAmountsOFCredits, clientName, clientSurname, clientPesel));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange("http://localhost:8082/Customer",
                HttpMethod.POST,
                customerHttpEntity,
                Customer.class);
    }

}
