/*
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
 */

package com.thiagobaptista.cartcalculator.test.model;

import com.thiagobaptista.cartcalculator.model.Product;

import junit.framework.TestCase;

public class ProductTest extends TestCase
{
	public ProductTest()
	{
		super();
	}
	
	public void test_should_not_the_price_be_lower_than_zero()
	{
		double [] prices = {0.0, -1.0, -10.0, -4.2, -0.0000082};
		
		Product product = new Product();
		
		for (double price : prices)
		{
			product.setPrice(price);
			
			assertEquals(0.0, product.getPrice());
		}
	}
	
	public void test_should_two_different_products_be_not_equal()
	{
		Product [] products = {
				new Product("Xuxu", 0.1), 
				new Product("Xuxu", 0.01), 
				new Product("Beleza", 0.1), 
				new Product("Beleza", 60.1)
		};
		
		for (int i = 0; i < products.length; i++)
		{
			for (int j = i + 1; j < products.length; j++)
			{
				boolean equals = products[i].equals(products[j]);
				
				assertFalse(equals);
			}
		}
	}
	
	public void test_should_two_equal_products_be_equal()
	{
		Product product = new Product("Xuxu", 0.1);
		
		Product [][] products = {
				{new Product("Beleza", 0.15), new Product("Beleza", 0.15)}, 
				{new Product("Chayote", 10.15), new Product("Chayote", 10.15)}, 
				{new Product("KitKat", 3.19), new Product("KitKat", 3.19)}, 
				{new Product("iPawn", 122.0), new Product("iPawn", 122.0)}
		};
		
		assertEquals(product, product);
		
		for (Product [] twoProducts : products)
		{
			assertEquals(twoProducts[0], twoProducts[1]);
		}
	}
}
