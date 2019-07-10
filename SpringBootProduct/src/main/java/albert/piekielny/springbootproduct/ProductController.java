package albert.piekielny.springbootproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    private void setProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/Product",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    Iterable<Product> getProducts(@RequestParam ArrayList<Integer> creditsId) {
//        ArrayList<Product> productsInfo = new ArrayList<>();
//         productsInfo.add(productRepository.findAllById(creditsId));
        creditsId.forEach(creditId -> {System.out.println(creditId);});
         return productRepository.findAllById(creditsId);
    }


    @PostMapping(path = "/Product")
    public @ResponseBody Product createProduct( Product product) {
        return productRepository.save(product);
    }
}
