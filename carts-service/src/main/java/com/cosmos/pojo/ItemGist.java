package com.cosmos.pojo;

import com.cosmos.model.Item;
import com.cosmos.model.UserCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemGist {
	private Item item;
	private ProductGist product;

}
