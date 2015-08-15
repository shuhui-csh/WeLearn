package com.shc.welearn.activity;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.shc.welearn.R;
import com.shc.welearn.R.id;
import com.shc.welearn.R.layout;
import com.shc.welearn.model.Choice;
import com.shc.welearn.util.JsonUtil;

public class TestActivity extends Activity implements OnCheckedChangeListener,
		OnClickListener {
	TextView mTilte;
	TextView mRatio;

	RadioGroup mRadioGroup;
	RadioButton mA, mB, mC, mD;

	Button mBefore, mAfter;

	Choice choice;

	List<Choice> choices = new ArrayList<Choice>();

	int mCurIndex = 0;

	int index = 0;

	int rightTimes = 0;

	boolean isDone = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_choice);
		initTitleBar();
		initView();
		setChoice(mCurIndex);
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

		mBefore.setVisibility(View.INVISIBLE);

		mRadioGroup.setOnCheckedChangeListener(this);
		mBefore.setOnClickListener(this);
		mAfter.setOnClickListener(this);
		mBefore.setEnabled(false);
	}

	private void setChoice(int position) {
		try {
			if (position >= index) {
				choice = JsonUtil.getRandomChoice(this);
				choices.add(choice);
			} else {
				choice = choices.get(position);
			}

			mTilte.setText(1 + position + "." + choice.getTitle());
			mA.setText(choice.getA());
			mB.setText(choice.getB());
			mC.setText(choice.getC());
			mD.setText(choice.getD());
		} catch (JSONException e) {
			Toast.makeText(this, "读取数据出错！", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		mRadioGroup.check(-1);

		switch (v.getId()) {
		case R.id.before:
			if (mCurIndex - 1 != -1) {
				// setChoice(--mCurIndex);
				// mAfter.setEnabled(true);
			} else {
				mBefore.setEnabled(false);
			}
			break;
		case R.id.after:
			if (mCurIndex != 50 - 1) {
				++index;
				isDone = false;
				setChoice(++mCurIndex);
				// mBefore.setEnabled(true);
			} else {
				mAfter.setEnabled(false);
			}
			break;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		if (isDone) {
			return;
		}
		String answer = choice.getAnswer();
		switch (checkedId) {
		case R.id.a:
			if (answer.equalsIgnoreCase("A")) {
				++rightTimes;
				Toast.makeText(this, "答案正确", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "答案出错,正确答案是:" + choice.getAnswer(),
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.b:
			if (answer.equalsIgnoreCase("B")) {
				++rightTimes;
				Toast.makeText(this, "答案正确", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "答案出错,正确答案是:" + choice.getAnswer(),
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.c:
			if (answer.equalsIgnoreCase("C")) {
				++rightTimes;
				Toast.makeText(this, "答案正确", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "答案出错,正确答案是:" + choice.getAnswer(),
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.d:
			if (answer.equalsIgnoreCase("D")) {
				++rightTimes;
				Toast.makeText(this, "答案正确", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "答案出错,正确答案是:" + choice.getAnswer(),
						Toast.LENGTH_SHORT).show();
			}
			break;
		}
		isDone = true;
		mRatio.setText("得分：" + rightTimes * 2);
	}

	/**
	 * 初始化顶部栏
	 * 
	 * @param view
	 */
	private void initTitleBar() {
		// TODO Auto-generated method stub
		ImageView title_img = (ImageView) findViewById(R.id.title_img);
		TextView title_text = (TextView) findViewById(R.id.title_text);
		title_text.setText(getResources().getString(R.string.test));
		title_img.setImageDrawable(getResources().getDrawable(R.drawable.test));
	}
}
