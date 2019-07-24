package albert.piekielny.intecaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class CreditController {

    private CreditRepository creditRepository;

    @Autowired
    public void setCreditRepository(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }


    @CrossOrigin()
    @GetMapping(path = "/Credit")
    public @ResponseBody
    int createCredit(@RequestParam String nameOfCredit,
                     @RequestParam String clientName,
                     @RequestParam String clientSurname,
                     @RequestParam String clientPesel,
                     @RequestParam String productName,
                     @RequestParam int productValue) {

        Credit credit = new Credit(nameOfCredit);
        creditRepository.save(credit);

        createProduct(credit.getId(), productValue, productName);
        createCustomer(credit.getId(), clientName, clientSurname, clientPesel);

        return credit.getId();
    }

    @CrossOrigin()
    @GetMapping(path = "/Credits")
    public @ResponseBody
    List<CreditResult> getAllCredits( ) {

        Iterable<Credit> creditsInfo = creditRepository.findAll();
        ArrayList<Integer> creditsId = new ArrayList<>();
        creditsInfo.forEach(credit -> creditsId.add(credit.getId()));

        Product[] productInfo = getProducts(creditsId);
        Customer[] customerInfo = getCustomers(creditsId);

        return prepareResult(creditsInfo,productInfo,customerInfo);
    }


    /***
     * Numbers of rows credits, products and customers are the same,
     * and data taken from DB are sorted, so we can easily construct response
     * by iterate by all collections use only 1 loop
     * @param credits
     * @param products
     * @param customers
     */
    private List<CreditResult> prepareResult(Iterable<Credit> credits, Product[] products, Customer[] customers) {

        List<CreditResult> creditResults = new ArrayList<>();

        for (Credit credit: credits) {
            creditResults.add(new CreditResult(
                    customers[credit.getId()-1].getFirstName(),customers[credit.getId()-1].getSurname(),customers[credit.getId()-1].getPesel(),
                    products[credit.getId()-1].getProductName(), products[credit.getId()-1].getValue(),credit.getCreditName()
            ));
        }
        return creditResults;
    }


    private Customer[] getCustomers(ArrayList<Integer> creditsId) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ArrayList<Integer>> request = new HttpEntity<>(creditsId);

        ResponseEntity<Customer[]> customers = restTemplate.exchange("http://localhost:8082/Customers",
                HttpMethod.POST,
                request,
                Customer[].class);

        return customers.getBody();
    }

    private Product[] getProducts(ArrayList<Integer> creditsId) {

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
