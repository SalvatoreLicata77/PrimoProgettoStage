package it.enaip.corso.model;

public class Articles {
	protected int id;
	protected String name;
	protected String description;
	protected int quantity;
	protected String location;
	
	public Articles() {
		
	}
	
	public Articles(int id) {
		this.id = id;
	}
	
	public Articles(int id, String name, String description, int quantity, String location) {
		this(name, description, quantity, location);
		this.id = id;
	}
	public Articles(String name, String description, int quantity, String location) {
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
		
}
	

