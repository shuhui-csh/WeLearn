package com.shc.welearn.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class PreferenceUtil
{

	public static void insert(Context context, String fileName,
			boolean isRight, int position)
	{
		SharedPreferences mPerferences = context.getSharedPreferences(fileName,
				0);
		Editor mEditor = mPerferences.edit();

		int rTimes = mPerferences.getInt(position + "right", 0);
		int eTimes = mPerferences.getInt(position + "error", 0);
		if (isRight)
		{
			if (rTimes == 0)
			{
				mEditor.putInt(position + "right", 1).commit();
			} else
			{
				mEditor.putInt(position + "right", rTimes + 1).commit();
			}
		} else
		{
			if (eTimes == 0)
			{
				mEditor.putInt(position + "error", 1).commit();
			} else
			{
				mEditor.putInt(position + "error", eTimes + 1).commit();
			}
		}
	}

	public static float getErrorRatio(Context context, String fileName)
	{
		SharedPreferences mPerferences = context.getSharedPreferences(fileName,
				0);
		int rTimes = 0;
		int eTimes = 0;
		for (int i = 0; i < 50; i++)
		{
			rTimes += mPerferences.getInt(i + "right", 0);
			eTimes += mPerferences.getInt(i + "error", 0);
		}
		if (eTimes + rTimes == 0)
		{
			return 0;
		}
		return (float) eTimes / (eTimes + rTimes);
	}

	public static float getRightRatio(Context context, String fileName,
			int position)
	{
		SharedPreferences mPerferences = context.getSharedPreferences(fileName,
				0);
		int rTimes = mPerferences.getInt(position + "right", 0);
		int eTimes = mPerferences.getInt(position + "error", 0);
		// Log.d("rTime:", rTimes + "--" + position);
		// Log.d("eTime:", eTimes + "--" + position);
		if (eTimes + rTimes == 0)
		{
			return 0;
		}
		return (float) rTimes / (eTimes + rTimes);
	}
}
