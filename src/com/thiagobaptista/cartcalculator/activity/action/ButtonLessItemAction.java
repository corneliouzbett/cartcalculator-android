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

import android.view.View;
import android.view.View.OnClickListener;

import com.thiagobaptista.cartcalculator.activity.HomeActivity;
import com.thiagobaptista.cartcalculator.model.CartItem;

public class ButtonLessItemAction implements OnClickListener
{
	private HomeActivity activity;
	
	private CartItem item;
	
	public ButtonLessItemAction(HomeActivity activity, CartItem item)
	{
		this.activity = activity;
		this.item = item;
	}

	@Override
	public void onClick(View view)
	{
		item.decreaseQuantity();
		
		activity.reloadList();
	}
}
