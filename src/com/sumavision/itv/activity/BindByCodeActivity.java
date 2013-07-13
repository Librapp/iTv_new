package com.sumavision.itv.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.sumavision.itv.R;
import com.sumavision.itv.controller.BindByCodeController;
import com.sumavision.itv.data.C;
import com.sumavision.itv.data.CtUserInfo;
import com.sumavision.itv.listener.OnDlnaBindListener;

public class BindByCodeActivity extends BaseActivity implements OnDlnaBindListener, OnClickListener
{

	private EditText input_edit;
	private ProgressDialog tipsDialog;
	private SharedPreferences bindInfo;

	@Override
	public void doInitFindView()
	{

		input_edit = (EditText) findViewById(R.id.bindinput_edit);
		
		findViewById(R.id.bindinput_ok).setOnClickListener(this);
		findViewById(R.id.bindinput_cancel).setOnClickListener(this);
	}

	@Override
	public void doInit(Bundle bundle)
	{

		input_edit.performClick();
		input_edit.requestFocus();
		InputMethodManager inputManager = (InputMethodManager) input_edit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.showSoftInput(input_edit, 0);
		BindByCodeController.setDlnaEventListener(this);

	}

	@Override
	protected int getContentView()
	{

		return R.layout.activity_bindinput;
	}

	@Override
	public void onBindByCode(boolean isOk)
	{

		hideTipsDialog();
		if (isOk)
		{
			Map<String, String> b = new HashMap<String, String>();
			b.put("stb_ua", CtUserInfo.stbId);
			Toast.makeText(BindByCodeActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
			saveBindPreference(CtUserInfo.stbId);
			setResult(RESULT_OK);
			hideSoftPad();
			finish();
		}
		else
		{
			Toast.makeText(BindByCodeActivity.this, CtUserInfo.errMsg, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onClick(View v)
	{

		switch (v.getId())
		{
			case R.id.bindinput_ok:
			{
				String code = input_edit.getText().toString();
				if (!code.equals("") && (code.length() == 8))
				{
					CtUserInfo.twoDimensionalCode = code;
					showTipsDialog();
					BindByCodeController.bindByCode();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "请输入正确的编号", Toast.LENGTH_SHORT).show();
				}
			}
				break;

			case R.id.bindinput_cancel:
			{
				setResult(RESULT_CANCELED);
				finish();
			}
				break;

			default:
				break;

		}

	}

	private void showTipsDialog()
	{

		if (tipsDialog != null)
		{
			tipsDialog.dismiss();
			tipsDialog = null;
		}

		tipsDialog = ProgressDialog.show(BindByCodeActivity.this, "", "处理中，请稍后...", true, true, new DialogInterface.OnCancelListener()
		{

			@Override
			public void onCancel(DialogInterface dialog)
			{

				dialog.dismiss();
			}
		});
		tipsDialog.setCanceledOnTouchOutside(false);
	}

	private void hideTipsDialog()
	{

		if (tipsDialog != null)
		{
			tipsDialog.dismiss();
			tipsDialog = null;
		}
	}

	private void hideSoftPad()
	{

		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(BindByCodeActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}

	private void saveBindPreference(String info)
	{

		bindInfo = getSharedPreferences(C.str.bindInfo, Context.MODE_PRIVATE);
		Editor editor = bindInfo.edit();
		editor.putString(C.str.stdId, info);
		if (info.equals(""))
		{
			editor.putBoolean(C.str.isBinded, false);
		}
		else
		{
			editor.putBoolean(C.str.isBinded, true);
		}
		editor.commit();
	}
	
}
