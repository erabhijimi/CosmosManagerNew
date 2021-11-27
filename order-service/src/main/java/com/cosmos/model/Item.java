package com.cosmos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name="item001")
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	private int productId;
	private int quantityOfProduct;
	@ManyToOne
	@JoinColumn(name = "orderId")
	@JsonBackReference
	private Order order;

	public Long getItemId() {
		return itemId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantityOfProduct() {
		return quantityOfProduct;
	}

	public void setQuantityOfProduct(int quantityOfProduct) {
		this.quantityOfProduct = quantityOfProduct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (productId != other.productId)
			return false;
		return true;
	}

}
