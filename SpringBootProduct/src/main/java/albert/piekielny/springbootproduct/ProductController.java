package albert.piekielny.springbootproduct;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
            method = RequestMethod.POST,
            path = "/Products",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    List<Product> getProducts(HttpServletRequest request) {

        List<Product> productsInfo = new ArrayList<>();
        try {
            List<String> creditsIdObject = IOUtils.readLines(request.getInputStream(), "UTF-8");
            String result = creditsIdObject.get(0).substring(1, creditsIdObject.get(0).length() - 1);
            String[] idCredits = result.split(",");
            for (String id: idCredits) {
               this.productRepository.findById((Integer.parseInt(id))).ifPresent(productsInfo::add);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return productsInfo;
    }

    @PostMapping(path = "/Product")
    public @ResponseBody
    Product createProduct(HttpServletRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        try {
            product = mapper.readValue(request.getInputStream(), Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productRepository.save(product);
    }
}
