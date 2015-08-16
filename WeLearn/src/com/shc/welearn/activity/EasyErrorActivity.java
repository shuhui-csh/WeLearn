package com.shc.welearn.activity;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.shc.welearn.R;
import com.shc.welearn.R.id;
import com.shc.welearn.R.layout;
import com.shc.welearn.R.string;
import com.shc.welearn.model.Choice;
import com.shc.welearn.util.JsonUtil;
import com.shc.welearn.util.PreferenceUtil;

public class EasyErrorActivity extends Activity implements
		OnCheckedChangeListener, OnClickListener {

	TextView mTilte;
	TextView mRatio;

	RadioGroup mRadioGroup;
	RadioButton mA, mB, mC, mD;

	Button mBefore, mAfter;

	String jsonFileName;
	String prefersFileName;
	int mCurIndex = 0;
	boolean isExchange = false;
	List<Choice> choices = new ArrayList<Choice>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_choice);
		initView();
		try {
			jsonFileName = getIntent().getStringExtra("json_file_name");
			prefersFileName = getIntent().getStringExtra("prefers_file_name");
			choices = JsonUtil.getEasyErrorChoicesByName(this, jsonFileName,
					prefersFileName);
			Log.d("----", choices.size() + "");
			if (choices.size() > 0) {
				setChoice(mCurIndex);
			} else {
				Toast.makeText(this, "本章没有易错题目！", Toast.LENGTH_SHORT).show();
				finish();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void initView() {
		mTilte = (TextView) findViewById(R.id.title);
		mRadioGroup = (RadioGroup) findViewById(R.id.group);
		mA = (RadioButton) findViewById(R.id.a);
		mB = (RadioButton) findViewById(R.id.b);
		mC = (RadioButton) findViewById(R.id.c);
		mD = (RadioButton) findViewById(R.id.d);
		mBefore = (Button) findViewById(R.id.before);
		mRatio = (TextView) findViewById(R.id.ratio);
		mAfter = (Button) findViewById(R.id.after);

		mRadioGroup.setOnCheckedChangeListener(this);
		mBefore.setOnClickListener(this);
		mAfter.setOnClickListener(this);
	}

	private void setChoice(int position) {
		float ratio = PreferenceUtil.getRightRatio(this, prefersFileName,
				mCurIndex);
		mRatio.setText(getResources().getString(R.string.correct) + ":"
				+ String.format("%.2f", ratio * 100) + "%");
		mTilte.setText(1 + position + "." + choices.get(position).getTitle());
		mA.setText(choices.get(position).getA());
		mB.setText(choices.get(position).getB());
		mC.setText(choices.get(position).getC());
		mD.setText(choices.get(position).getD());
		// Log.d("setChoice", "1");
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// Log.d("checkId", checkedId + "");
		// Log.d("setChoice", "2");
		if (isExchange) {
			return;
		}

		String answer = choices.get(mCurIndex).getAnswer();
		boolean isRight = false;
		switch (checkedId) {
		case R.id.a:
			if (answer.equalsIgnoreCase("A")) {
				isRight = true;
				Toast.makeText(this, "答案正确", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "答案出错", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.b:
			if (answer.equalsIgnoreCase("B")) {
				isRight = true;
				Toast.makeText(this, "答案正确", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "答案出错", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.c:
			if (answer.equalsIgnoreCase("C")) {
				isRight = true;
				Toast.makeText(this, "答案正确", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "答案出错", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.d:
			if (answer.equalsIgnoreCase("D")) {
				isRight = true;
				Toast.makeText(this, "答案正确", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "答案出错", Toast.LENGTH_SHORT).show();
			}
			break;
		}
		PreferenceUtil.insert(this, prefersFileName, isRight, mCurIndex);
		float ratio = PreferenceUtil.getRightRatio(this, prefersFileName,
				mCurIndex);
		mRatio.setText(getResources().getString(R.string.correct) + ":"
				+ String.format("%.2f", ratio * 100) + "%");
		isRight = false;
		// Log.d("setChoice", "3");
	}

	@Override
	public void onClick(View v) {
		isExchange = true;
		// Log.d("Click", "-1");
		mRadioGroup.check(-1);

		// Log.d("Click", "0");
		switch (v.getId()) {
		case R.id.before:
			if (mCurIndex - 1 != -1) {
				setChoice(--mCurIndex);
				mAfter.setEnabled(true);
			} else {
				mBefore.setEnabled(false);
			}
			break;
		case R.id.after:
			if (mCurIndex != choices.size() - 1) {
				setChoice(++mCurIndex);
				mBefore.setEnabled(true);
			} else {
				mAfter.setEnabled(false);
			}
			break;
		}
		isExchange = false;
	}
}
