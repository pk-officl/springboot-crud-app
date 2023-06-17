package in.pk.springcrudapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import in.pk.springcrudapp.controllers.helper.ProductControllerHelper;
import in.pk.springcrudapp.services.ProductService;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/{product_type}/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getProductsByType(HttpServletRequest request, @PathVariable("product_type") String productType) {
		return new ProductControllerHelper(productService).getProductsByType(request, productType);
	}
	
	@GetMapping(value="/suggestions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getProductsByFilter(HttpServletRequest request) {
		return new ProductControllerHelper(productService).getProductsSuggestionBySearch(request);
	}
	
	@PostMapping(value="/add")
	public ResponseEntity<String> addProducts(HttpServletRequest request, @RequestBody String jsonString) {
		return new ProductControllerHelper(productService).addProducts(request, jsonString);
	}
}
