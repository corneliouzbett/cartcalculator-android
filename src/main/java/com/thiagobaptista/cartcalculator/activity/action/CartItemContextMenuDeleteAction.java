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

package com.thiagobaptista.cartcalculator.activity.action;

import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

import com.thiagobaptista.cartcalculator.activity.HomeActivity;
import com.thiagobaptista.cartcalculator.model.Cart;
import com.thiagobaptista.cartcalculator.model.CartItem;

public class CartItemContextMenuDeleteAction implements OnMenuItemClickListener
{
	private HomeActivity activity;
	
	private Cart cart;
	
	public CartItemContextMenuDeleteAction(HomeActivity activity)
	{
		this.activity = activity;
		
		this.cart = this.activity.getCart();
	}

	@Override
	public boolean onMenuItemClick(MenuItem menuItem)
	{
		CartItem cartItem = activity.getSelectedCartItem();
		cart.remove(cartItem);
		
		activity.reloadList();
		
		return true;
	}
}
