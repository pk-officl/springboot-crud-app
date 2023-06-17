package in.pk.springcrudapp.models;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "products")
public class Product {

    @Id
    private String _id;

	private String prod_name;
	
	private String prod_brand;

    private String prod_description;
    
    private String prod_category;

    private double prod_price;
    
    private double prod_actual_price;
    
    private long prod_count;
    
    private String prod_image;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getProd_brand() {
		return prod_brand;
	}

	public void setProd_brand(String prod_brand) {
		this.prod_brand = prod_brand;
	}

	public String getProd_description() {
		return prod_description;
	}

	public void setProd_description(String prod_description) {
		this.prod_description = prod_description;
	}
	
	public String getProd_category() {
		return prod_category;
	}

	public void setProd_category(String prod_category) {
		this.prod_category = prod_category;
	}

	public double getProd_price() {
		return prod_price;
	}

	public void setProd_price(double prod_price) {
		this.prod_price = prod_price;
	}

	public double getProd_actual_price() {
		return prod_actual_price;
	}

	public void setProd_actual_price(double prod_actual_price) {
		this.prod_actual_price = prod_actual_price;
	}

	public long getProd_count() {
		return prod_count;
	}

	public void setProd_count(long prod_count) {
		this.prod_count = prod_count;
	}

	public String getProd_image() {
		return prod_image;
	}

	public void setProd_image(String prod_image) {
		this.prod_image = prod_image;
	}
    
}
