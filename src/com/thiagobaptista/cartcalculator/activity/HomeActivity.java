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

package com.thiagobaptista.cartcalculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thiagobaptista.cartcalculator.R;
import com.thiagobaptista.cartcalculator.activity.action.ButtonAddItemAction;
import com.thiagobaptista.cartcalculator.activity.action.ButtonClearListAction;
import com.thiagobaptista.cartcalculator.activity.adapter.CartItemsListAdapter;
import com.thiagobaptista.cartcalculator.model.Cart;

public class HomeActivity extends Activity 
{
	private Cart cart;
	
	private Button addItemButton;
	private Button clearListButton;
	
	private TextView totalTextView;
	
	private ListView itensListView;
	
	private CartItemsListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		cart = new Cart();
		
		setupViews();
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		
		reloadList();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == ButtonAddItemAction.REQUEST_CODE)
		{
			if (resultCode == RESULT_OK)
			{
				Bundle extras = data.getExtras();
				
				Cart cart = (Cart) extras.get("cart");
				
				if (cart != null)
				{
					this.cart = cart;
				}
			}
		}
	}
	
	public Cart getCart()
	{
		return cart;
	}
	
	public void clearList()
	{
		if (cart != null)
		{
			cart.clear();
		}
	}
	
	public void reloadList()
	{
		if (adapter != null)
		{
			adapter.updateByCart(cart);
		}
		
		reloadTotalDueText();
	}

	private void reloadTotalDueText()
	{
		if (totalTextView != null)
		{			
			String totalText = "Total: " + cart.getTotalPriceText();
			
			totalTextView.setText(totalText);
		}
	}

	private void setupViews()
	{
		setupCartItemListView();		
		setupTotalTextView();		
		setupAddItemButton();
		setupClearListButton();
	}

	private void setupClearListButton()
	{
		clearListButton = (Button) findViewById(R.id.button_home_clear_list);
		
		if (clearListButton != null)
		{
			clearListButton.setOnClickListener( new ButtonClearListAction(this) );
		}
	}

	private void setupAddItemButton()
	{
		addItemButton = (Button) findViewById(R.id.button_home_add_item);
		
		if (addItemButton != null)
		{
			addItemButton.setOnClickListener( new ButtonAddItemAction(this) );
		}
	}

	private void setupTotalTextView()
	{
		totalTextView = (TextView) findViewById(R.id.text_view_home_total_price);
	}

	private void setupCartItemListView()
	{
		itensListView = (ListView) findViewById(R.id.list_view_home_cart_items);
		
		if (itensListView != null)
		{
			adapter = new CartItemsListAdapter(this, cart);
			
			itensListView.setAdapter(adapter);			
		}
	}
}
