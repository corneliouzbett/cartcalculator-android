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

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.thiagobaptista.cartcalculator.activity.AddItemActivity;
import com.thiagobaptista.cartcalculator.activity.HomeActivity;

public class ButtonAddItemAction implements OnClickListener
{
	public static final int REQUEST_CODE = 12;
	
	private HomeActivity activity;
	
	public ButtonAddItemAction(HomeActivity activity)
	{
		this.activity = activity;
	}

	@Override
	public void onClick(View view)
	{
		Intent intent = new Intent(activity, AddItemActivity.class);
		intent.putExtra("cart", activity.getCart());
		
		activity.startActivityForResult(intent, REQUEST_CODE);
	}
}
