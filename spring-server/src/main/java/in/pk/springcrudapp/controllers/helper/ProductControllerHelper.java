package in.pk.springcrudapp.controllers.helper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.ResponseEntity;

import in.pk.springcrudapp.services.ProductService;

public class ProductControllerHelper {

	private ProductService productService;

	public ProductControllerHelper(ProductService productService) {
		this.productService = productService;
	}

	public ResponseEntity<String> getProductsByType(HttpServletRequest request, String productType) {
		JSONObject resJson = new JSONObject();
		try {
			if (StringUtils.isNotBlank(productType)) {
				int pageNumber = request.getParameter("pageNumber") != null ? Integer.parseInt(request.getParameter("pageNumber")) : 1;
				int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize")) : 6;
				String searchTerm = request.getParameter("searchTerm") != null ? request.getParameter("searchTerm"):"";
				List<JSONObject> products = productService.getProductsByType(productType, searchTerm, pageNumber, pageSize);
				resJson.put("productType", productType);
				resJson.put("products", products);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(resJson.toString());
	}

	public ResponseEntity<String> addProducts(HttpServletRequest request, String jsonString) {
		JSONObject resJson = new JSONObject();
		try {
			if (StringUtils.isNotBlank(jsonString)) {
				JSONObject productJson = new JSONObject(jsonString);
				resJson = productService.addProducts(productJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(resJson.toString());
	}

	public ResponseEntity<String> getProductsSuggestionBySearch(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		try {
			String searchTerm = request.getParameter("searchTerm");
			if(StringUtils.isNotBlank(searchTerm)) {
				int pageNumber = request.getParameter("pageNumber") != null ? Integer.parseInt(request.getParameter("pageNumber")) : 1;
				int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize")) : 6;
				jsonObject.put("suggestions", productService.getProductsSuggestionBySearch(searchTerm, pageNumber, pageSize));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(jsonObject.toString());
	}

}
