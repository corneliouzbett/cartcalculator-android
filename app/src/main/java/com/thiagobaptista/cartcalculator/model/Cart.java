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

package com.thiagobaptista.cartcalculator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.thiagobaptista.cartcalculator.util.CurrencyStringUtil;

public class Cart implements Serializable
{
	private static final long serialVersionUID = -7764200800949285163L;
	
	private List<CartItem> items;
	
	public Cart()
	{
		//generateCartItemArray();
		items = new ArrayList<CartItem>(0);
	}
	
	public void add(CartItem item)
	{
		items.add(item);
	}
	
	public void remove(CartItem item)
	{
		items.remove(item);
	}
	
	public int getSize()
	{
		return items.size();
	}
	
	public List<CartItem> getItems()
	{
		return items;
	}
	
	public double getTotalDue()
	{
		double total = 0.0;
		for (CartItem item : items)
		{
			total += item.getTotalPrice();
		}
		
		return total;
	}
	
	public String getTotalDueText()
	{		
		return new CurrencyStringUtil().formattedTextFrom( getTotalDue() );
	}
	
	public void clear() 
	{
		items = new ArrayList<CartItem>(0);	
	}

	//for debug purposes
	@SuppressWarnings("unused")
	private void generateCartItemArray()
	{
		CartItem [] itens = {
				new CartItem(new Product("Cucumber", 0.5), 4), 
				new CartItem(new Product("Coffee", 4.95), 2), 
				new CartItem(new Product("Phone Battery", 30.0), 1), 
				new CartItem(new Product("Android Book", 27.00), 1), 
				new CartItem(new Product("KitKat", 1.65), 3), 
				new CartItem(new Product("Cocoa", 7.13), 2), 
				new CartItem(new Product("Jelly Beans", 0.55), 5), 
				new CartItem(new Product("Avocado", 2.18), 2), 
				new CartItem(new Product("Banana", 0.75), 12), 
				new CartItem(new Product("Bleach", 7.19), 1), 
				new CartItem(new Product("Keychain", 0.99), 1), 
				new CartItem(new Product("Bread", 1.12), 6), 
		};
		this.items = new ArrayList<CartItem>( Arrays.asList(itens) );
	}
}
