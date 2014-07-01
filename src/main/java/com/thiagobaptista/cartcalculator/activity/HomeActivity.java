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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.thiagobaptista.cartcalculator.R;
import com.thiagobaptista.cartcalculator.activity.action.ButtonAddItemAction;
import com.thiagobaptista.cartcalculator.activity.action.ButtonClearListAction;
import com.thiagobaptista.cartcalculator.activity.action.CartItemContextMenuDeleteAction;
import com.thiagobaptista.cartcalculator.activity.action.CartItemListItemLongClickAction;
import com.thiagobaptista.cartcalculator.activity.adapter.CartItemsListAdapter;
import com.thiagobaptista.cartcalculator.activity.helper.AboutAlertDialog;
import com.thiagobaptista.cartcalculator.model.Cart;
import com.thiagobaptista.cartcalculator.model.CartItem;

public class HomeActivity extends ActionBarActivity 
{
	private Cart cart;
	
	private CartItem selectedCartItem;
	
	private TextView totalDueTextView;
	
	private ListView itensListView;
	
	private CartItemsListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		setupCart(savedInstanceState);		
		
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
				Cart cart = (Cart) data.getExtras().get("cart");
				
				if (cart != null)
				{
					this.cart = cart;
				}
			}
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		if (cart != null)
		{
			outState.putSerializable("cart", cart);
		}
		
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.home_options_menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch ( item.getItemId() )
		{
			case R.id.home_options_menu_add_item:
			{
				new ButtonAddItemAction(this).onClick();
				return false;
			}
			case R.id.home_options_menu_clear_list:
			{
				new ButtonClearListAction(this).onClick(null);
				return false;
			}
			case R.id.home_options_menu_about:
			{
				new AboutAlertDialog(this).show();
				return false;
			}
			default:
			{
				return super.onOptionsItemSelected(item);
			}
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo)
	{
		getMenuInflater().inflate(R.menu.home_cart_item_context_menu, menu);
		menu.setHeaderTitle(R.string.title_contextmenu_cart_items);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem menuItem)
	{
		switch ( menuItem.getItemId() )
	    {
	        case R.id.menu_item_cart_item_context_delete:
	        {
	        	return new CartItemContextMenuDeleteAction(this).onMenuItemClick(menuItem);
	        }
	        default:
	        {
	        	return super.onContextItemSelected(menuItem);	        	
	        }
	    }

	}
	
	public Cart getCart()
	{
		return cart;
	}
	
	public CartItem getSelectedCartItem()
	{
		return selectedCartItem;
	}
	
	public void setSelectedCartItem(CartItem selectedCartItem)
	{
		this.selectedCartItem = selectedCartItem;
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
		if (totalDueTextView != null)
		{			
			String totalText = cart.getTotalDueText();
			
			totalDueTextView.setText(totalText);
		}
	}
	
	private void setupCart(Bundle savedInstanceState)
	{
		if (savedInstanceState != null)
		{
			cart = (Cart) savedInstanceState.getSerializable("cart");
		}
		else
		{
			cart = new Cart();			
		}
	}

	private void setupViews()
	{
		setupCartItemListView();		
		setupTotalDueTextView();
	}

	private void setupTotalDueTextView()
	{
		totalDueTextView = (TextView) findViewById(R.id.text_view_home_total_due);
	}

	private void setupCartItemListView()
	{
		itensListView = (ListView) findViewById(R.id.list_view_home_cart_items);
		
		if (itensListView != null)
		{
			adapter = new CartItemsListAdapter(this, cart);
			
			itensListView.setAdapter(adapter);
			
			itensListView.setOnItemLongClickListener( new CartItemListItemLongClickAction(this) );			
			registerForContextMenu(itensListView);
		}
	}
}
