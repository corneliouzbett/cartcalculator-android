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

package com.thiagobaptista.cartcalculator.activity.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;

import com.thiagobaptista.cartcalculator.R;

public class AboutAlertDialog
{
	private Activity activity;
	
	public AboutAlertDialog(Activity activity)
	{
		this.activity = activity;
	}

	public void show()
	{
		View view = setupAlertDialogView();
		
		showAlertDialog(view);
	}

	private void showAlertDialog(View view)
	{
		new AlertDialog.Builder(activity)
			.setTitle(R.string.label_about)
			.setView(view)
			.setPositiveButton(android.R.string.ok, null)
			.show();
	}

	private View setupAlertDialogView()
	{
		ViewGroup parent = (ViewGroup) activity.findViewById(R.id.view_about);
		
		View aboutAlertDialogView = activity.getLayoutInflater()
				.inflate(R.layout.alert_dialog_about, parent);
		
		return aboutAlertDialogView;
	}
}
