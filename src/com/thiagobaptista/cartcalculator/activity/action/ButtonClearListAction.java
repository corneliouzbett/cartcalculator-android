package com.thiagobaptista.cartcalculator.activity.action;

import android.view.View;
import android.view.View.OnClickListener;

import com.thiagobaptista.cartcalculator.activity.HomeActivity;

public class ButtonClearListAction implements OnClickListener
{
	private HomeActivity activity;
	
	public ButtonClearListAction(HomeActivity activity)
	{
		this.activity = activity;
	}

	@Override
	public void onClick(View view)
	{
		activity.clearList();
		
		activity.reloadList();
	}
}
