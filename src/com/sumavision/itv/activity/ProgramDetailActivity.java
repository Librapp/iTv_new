package com.sumavision.itv.activity;

import com.sumavision.itv.R;
import com.sumavision.itv.data.C;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ProgramDetailActivity extends BaseActivity implements OnClickListener
{

	private TextView titleTextView;
	@SuppressWarnings("unused")
	private int programId;

	@Override
	public void doInitFindView()
	{

		titleTextView = (TextView) findViewById(R.id.title_text);
		findViewById(R.id.btn_back).setOnClickListener(this);

	}

	@Override
	public void doInit(Bundle bundle)
	{

		bundle = getIntent().getExtras();
		String name = "";
		if (bundle != null)
		{
			programId = bundle.getInt(C.str.programId, -1);
			name = bundle.getString(C.str.programName);
		}
		titleTextView.setText("" + name);

	}

	@Override
	protected int getContentView()
	{

		return R.layout.activity_program_detail;
	}

	@Override
	public void onClick(View v)
	{

		switch (v.getId())
		{
			case R.id.btn_back:
				finish();
				break;
			default:
				break;
		}
	}

}
