package in.pk.springcrudapp.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.pk.springcrudapp.models.Product;

@Service
public class ProductService {
	
	private final MongoTemplate mongoTemplate;

    @Autowired
    public ProductService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
	
	public JSONObject addProducts(JSONObject productJson) {
		JSONObject outputJson = new JSONObject();
		try {
			if (!mongoTemplate.collectionExists(Product.class)) {
				mongoTemplate.createCollection(Product.class);
			}
			List<Product> products = new ObjectMapper().readValue(productJson.get("products").toString(), new TypeReference<List<Product>>(){});
			Collection<Product> newProducts = mongoTemplate.insert(products, Product.class);
			outputJson.put("message", "Product Added Successfully!");
			outputJson.put("products", newProducts);
			return outputJson;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputJson;
	}

	public List<JSONObject> getProductsByType(String productType, String searchTerm, int pageNumber, int pageSize) {
		List<JSONObject> productList = new ArrayList<>();
		JSONObject outputJson = new JSONObject();
		try {
			List<Product> result = new ArrayList<>();
			if (StringUtils.isNotBlank(productType)) {
				Query query = new Query();
				if (!productType.equalsIgnoreCase("all")) {
					query.addCriteria(Criteria.where("prod_category").is(productType));
				}
				if (StringUtils.isNotBlank(searchTerm)) {
					Criteria prdCategoryQuery = Criteria.where("prod_category").regex(searchTerm, "i");
					Criteria prdCategoryName = Criteria.where("prod_name").regex(searchTerm, "i");
					query.addCriteria(new Criteria().orOperator(prdCategoryQuery,prdCategoryName));
				}
				query.with(PageRequest.of(pageNumber - 1, pageSize));
				result = mongoTemplate.find(query, Product.class);
			}
			if (!result.isEmpty()) {
				for (Product prd : result) {
					JSONObject product = new JSONObject();
					product.put("prod_id", prd.get_id());
					product.put("prod_name", prd.getProd_name());
					product.put("prod_description", prd.getProd_description());
					product.put("prod_category", prd.getProd_category());
					product.put("prod_price", prd.getProd_price());
					product.put("prod_actual_price", prd.getProd_actual_price());
					product.put("prod_count", prd.getProd_count());
					product.put("prod_image", prd.getProd_image());
					productList.add(product);
				}
			}
			outputJson.put("products", productList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	public List<String> getProductsSuggestionBySearch(String searchTerm, int pageNumber, int pageSize) {
		List<String> suggestionList = new ArrayList<>();
		try {
			Query query = new Query();
			if(StringUtils.isNotBlank(searchTerm)) {
				query.addCriteria(Criteria.where("prod_name").regex(searchTerm, "i"));
			}
			query.fields().include("prod_brand");
			query.fields().include("prod_name");
			query.with(PageRequest.of(pageNumber - 1, pageSize));
			List<Product> result = mongoTemplate.find(query, Product.class);
			if (!result.isEmpty()) {
				Set<String> productBrands = result.stream().map(Product::getProd_brand).filter(brand -> brand.toLowerCase().startsWith(searchTerm.toLowerCase())).distinct().collect(Collectors.toSet());
				Set<String> productNames = result.stream().map(Product::getProd_name).collect(Collectors.toSet());
				if (!productBrands.isEmpty()) {
					suggestionList.addAll(productBrands);
				}
				if (!productNames.isEmpty()) {
					suggestionList.addAll(productNames);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return suggestionList;
	}
	
}
