package com.thiagobaptista.cartcalculator.model;

import java.io.Serializable;

/**
 * 
 * Cart Calculator - simple, generic shopping cart total due calculator
 * Copyright (c) 2014 Thiago Gonçalves Baptista
 * contato@thiagobaptista.com
 * 
 * This program is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */
public class CartItem implements Serializable
{
	private static final long serialVersionUID = 994760458297599495L;

	private int quantity;
	
	private Product product;
	
	public CartItem(Product product)
	{
		this(product, 1);
	}
	
	public CartItem(Product product, int quantity)
	{
		this.product = product;
		
		if (quantity < 1)
		{
			quantity = 1;
		}
		this.quantity = quantity;
	}
	
	public void increaseQuantity()
	{
		quantity++;
	}
	
	public void decreaseQuantity()
	{
		quantity--;
		
		if (quantity < 0)
		{
			quantity = 0;
		}
	}

	public void setProduct(Product product)
	{
		this.product = product;	
	}
	
	public String getName()
	{
		return product.getName();
	}

	public int getQuantity()
	{
		return quantity;
	}
	
	public double getUnitPrice()
	{
		return product.getPrice();
	}

	public double getTotalPrice()
	{
		return product.getPrice() * quantity;
	}
	
	@Override
	public String toString()
	{
		return product.toString() + " (" + quantity + ")";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		
		result = prime * result + quantity;
		
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		
		if (obj == null)
		{
			return false;
		}
		
		if (!(obj instanceof CartItem))
		{
			return false;
		}
		
		CartItem other = (CartItem) obj;
		if (product == null)
		{
			if (other.product != null)
			{
				return false;
			}
		}
		else if (!product.equals(other.product))
		{
			return false;
		}
		
		if (quantity != other.quantity) {
			return false;
		}
		
		return true;
	}
}
