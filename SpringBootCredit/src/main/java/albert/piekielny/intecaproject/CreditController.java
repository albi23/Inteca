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
    public void setCreditRepository(CreditRepository creditRepository){this.creditRepository = creditRepository;}


    @GetMapping(path = "/Credit")
    public @ResponseBody int createCredit(@RequestParam String nameOfCredit,
                                             @RequestParam String clientName,
                                             @RequestParam String clientSurname,
                                             @RequestParam String clientPesel,
                                             @RequestParam String productName,
                                             @RequestParam int productValue){

        Credit credit = new Credit(nameOfCredit);
        int currentAmountsOFCredits = (int)creditRepository.count()+1;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Product> request = new HttpEntity<>(new Product(currentAmountsOFCredits,productValue,productName));


         restTemplate.exchange("http://localhost:8081/Product",
                HttpMethod.POST,
                request,
                Product.class);

        HttpEntity<Customer> customerHttpEntity = new HttpEntity<>(new Customer(currentAmountsOFCredits,clientName,clientSurname,clientPesel));
        restTemplate.exchange("http://localhost:8082/Customer",
                HttpMethod.POST,
                customerHttpEntity,
                Customer.class);


        creditRepository.save(credit);
        return currentAmountsOFCredits; // numer nadanego credytu
    }

    @GetMapping(path="/Credits")
    public @ResponseBody Iterable<Credit> getAllCredits() {


        Iterable<Credit> creditsInfo = creditRepository.findAll();
        ArrayList<Integer> creditsId = new ArrayList<>();
        creditsInfo.forEach(credit -> creditsId.add(credit.getId()));

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<ArrayList<Integer>> request = new HttpEntity<>(creditsId);
        ResponseEntity<Product[]> productInfo = restTemplate.exchange("http://localhost:8081/Product",
                HttpMethod.GET,
                request,
                Product[].class);

        Product[] products = productInfo.getBody();
        assert products != null;
        for (Product p: products) {
            System.out.println(p);
        }
        return creditsInfo;

    }

}
