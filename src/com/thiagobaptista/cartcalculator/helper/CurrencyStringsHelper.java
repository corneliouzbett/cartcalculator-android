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

package com.thiagobaptista.cartcalculator.helper;

import java.text.NumberFormat;

public class CurrencyStringsHelper
{
	private NumberFormat numberFormat;
	
	private String currencySymbol;
	
	public CurrencyStringsHelper()
	{
		numberFormat = NumberFormat.getCurrencyInstance();
		
		currencySymbol = numberFormat.getCurrency().getSymbol();
	}
	
	public String formattedTextFrom(double value)
	{
		return numberFormat.format(value);
	}
	
	public double numericValueFrom(String text)
	{
		double value = 0.0;
		
		String symbolsToStripOff = String.format("[%s,.\\s]", currencySymbol);
		String stripped = text.replaceAll(symbolsToStripOff, "");
		
		value = (Double.parseDouble(stripped) / 100);
		
		return value;
	}
}
