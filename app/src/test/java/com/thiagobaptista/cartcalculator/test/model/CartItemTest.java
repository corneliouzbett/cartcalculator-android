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

import com.thiagobaptista.cartcalculator.model.CartItem;
import com.thiagobaptista.cartcalculator.model.Product;

import junit.framework.TestCase;

public class CartItemTest extends TestCase
{
	public CartItemTest()
	{
		super();
	}
	
	public void test_should_a_new_cartitem_have_a_quantity_of_one()
	{
		Product product = new Product("Xuxu", 0.1);
		CartItem item = new CartItem(product);
		
		assertEquals(1, item.getQuantity());
	}
	
	public void test_should_increase_quantity()
	{
		Product product = new Product("Xuxu", 0.1);
		CartItem item = new CartItem(product);
		
		int targetQuantity = 6;
		
		for (int i = 0; i < targetQuantity - 1; i++)
		{
			item.increaseQuantity();
		}
		
		assertEquals(targetQuantity, item.getQuantity());
	}
	
	public void test_should_not_decrease_quantity_below_zero()
	{
		Product product = new Product("Xuxu", 0.1);
		CartItem item1 = new CartItem(product);
		CartItem item2 = new CartItem(product);
		
		item1.decreaseQuantity();
		
		item2.decreaseQuantity();
		item2.decreaseQuantity();
		item2.decreaseQuantity();
		item2.decreaseQuantity();
		item2.decreaseQuantity();
		
		assertEquals(0, item1.getQuantity());
		assertEquals(0, item2.getQuantity());
	}
	
	public void test_should_get_correct_total_price()
	{		
		int [] quantities = {8, 10, 23, 765, 5428, 1};
		double [] prices = {0.25, 1000.76, 0.02, 1.23, 0};
		
		for (int quantity : quantities) 
		{
			for (double price : prices)
			{
				assertCorrectTotalPrice(quantity, price);				
			}
		}		
	}
	
	public void test_should_yeld_the_correct_string_in_toString()
	{
		String [] names = {"Xuxu", "Beleza", "Foo", "Bar", "Droid"};
		double [] prices = {0.12, 125, 4099.01, 0.01, 3.21, 0.00};
		
		for (String name : names)
		{
			for (double price : prices)
			{
				Product product = new Product(name, price);
				CartItem item = new CartItem(product);
				
				String productString = product.toString();
				String itemString = item.toString();
				String expectedString = productString + " (" + 1 + ")";
				
				assertEquals(expectedString, itemString);				
			}
		}		
	}

	private void assertCorrectTotalPrice(int quantity, double price)
	{
		double total = quantity * price;
		
		Product product = new Product("Xuxu", price);
		CartItem item = new CartItem(product);
		
		for (int i = 0; i < quantity - 1; i++)
		{
			item.increaseQuantity();
		}
		
		assertEquals(total, item.getTotalPrice());
	}
}
