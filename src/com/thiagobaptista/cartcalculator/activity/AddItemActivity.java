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
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.thiagobaptista.cartcalculator.R;
import com.thiagobaptista.cartcalculator.activity.action.ButtonSaveItemAction;
import com.thiagobaptista.cartcalculator.helper.CurrencyStringsHelper;
import com.thiagobaptista.cartcalculator.model.Cart;
import com.thiagobaptista.cartcalculator.model.Product;

public class AddItemActivity extends Activity
{
	private EditText name;	
	private EditText price;

	private Button save;

	private Cart cart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		
		cart = (Cart) getIntent().getSerializableExtra("cart");
		
		setupViews();
	}
	
	public Cart getCart()
	{
		return cart;
	}
	
	public Product getProduct()
	{
		String nameText = name.getText().toString();
		String priceText = price.getText().toString();
		
		Product product = new Product();
		product.setName(nameText);
		product.setPrice(
				new CurrencyStringsHelper().numericValueFrom(priceText)
				);
		
		return product;
	}

	private void setupViews()
	{
		setupNameEditText();		
		setupPriceEditText();		
		setupSaveButton();
	}

	private void setupSaveButton()
	{
		save = (Button) findViewById(R.id.button_add_item_save);
		
		save.setOnClickListener( new ButtonSaveItemAction(this) );
	}

	private void setupPriceEditText()
	{
		price = (EditText) findViewById(R.id.edit_text_add_item_price);
		
		price.setText( new CurrencyStringsHelper().formattedTextFrom(0.0) );
		
		price.addTextChangedListener( new CurrencyMaskTextWatcher(price) );
	}

	private void setupNameEditText()
	{
		name = (EditText) findViewById(R.id.edit_text_add_item_name);
	}
}
