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

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.thiagobaptista.cartcalculator.helper.CurrencyStringsHelper;

public class CurrencyMaskTextWatcher implements TextWatcher
{
	private EditText editText;

	private boolean alreadyUpdating;

	public CurrencyMaskTextWatcher(EditText editText)
	{
		this.editText = editText;

		alreadyUpdating = false;
	}

	@Override
	public void beforeTextChanged(CharSequence sequence, int start, int count,
			int after) 
	{}

	@Override
	public void afterTextChanged(Editable sequence)
	{}

	@Override
	public void onTextChanged(CharSequence sequence, int start, int before, int count)
	{
		// Prevents an infinite loop and, thus, a StackOverflowError
		if (alreadyUpdating)
		{
			alreadyUpdating = false;			
			return;
		}
		
		String updatedText = parseInput( sequence.toString() );
		
		updateEditText(updatedText);
	}

	private void updateEditText(String updatedText)
	{
		// We're about to call setText() and, thus, we must ensue that this call does not
		// calls onTextChanged() once more, lest the program will go into an infinite loop.
		alreadyUpdating = true;
		editText.setText(updatedText);

		editText.setSelection( updatedText.length() );
	}

	private String parseInput(String input)
	{
		CurrencyStringsHelper helper = new CurrencyStringsHelper();
		
		double numericValue = helper.numericValueFrom(input);			
		String formatedText = helper.formattedTextFrom(numericValue);
		
		return formatedText;
	}
}
