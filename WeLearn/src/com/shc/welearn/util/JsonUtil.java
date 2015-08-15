package com.shc.welearn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.shc.welearn.model.Choice;

public class JsonUtil {
	
	public static List<Choice> getChoicesByName(Context context, String fileName) 
			throws JSONException {
		List<Choice> choices = new ArrayList<Choice>();
		JSONObject choicesObject = new JSONObject(getJsonString(context, fileName));
		JSONArray choicesArray = choicesObject.getJSONArray("Choices");
		
		for (int i = 0; i < choicesArray.length(); i++) {			
			Choice choice = new Choice();
			JSONObject jsonObject = choicesArray.getJSONObject(i).getJSONObject("choice");
			choice.setTitle(jsonObject.getString("Title"));
			choice.setA(jsonObject.getString("A"));
			choice.setB(jsonObject.getString("B"));
			choice.setC(jsonObject.getString("C"));
			choice.setD(jsonObject.getString("D"));
			choice.setAnswer(jsonObject.getString("Answer"));
			choices.add(choice);
		}		
		return choices;
	}
	
	public static List<Choice> getEasyErrorChoicesByName(Context context, String fileName, String pefsFileName) 
			throws JSONException {
		List<Choice> choices = new ArrayList<Choice>();
		JSONObject choicesObject = new JSONObject(getJsonString(context, fileName));
		JSONArray choicesArray = choicesObject.getJSONArray("Choices");
		List<Integer> lists = getEasyErrorList(context, pefsFileName);
		int size = lists.size();
		for (int i = 0; i < size; i++) {			
			Choice choice = new Choice();
			JSONObject jsonObject = choicesArray.getJSONObject(lists.get(i)).getJSONObject("choice");
			choice.setTitle(jsonObject.getString("Title"));
			choice.setA(jsonObject.getString("A"));
			choice.setB(jsonObject.getString("B"));
			choice.setC(jsonObject.getString("C"));
			choice.setD(jsonObject.getString("D"));
			choice.setAnswer(jsonObject.getString("Answer"));
			choices.add(choice);
		}		
		return choices;
	}	
	
	private static List<Integer> getEasyErrorList(Context context, String pefsFileName) {
		SharedPreferences mPerferences = context.getSharedPreferences(pefsFileName, 0);		
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < 54; i++) {
			int rTimes = mPerferences.getInt(i + "right", 0);
			int eTimes = mPerferences.getInt(i + "error", 0);
			if (eTimes + rTimes == 0) {
				continue;
			}
			float ratio = (float)rTimes / (eTimes + rTimes);
			if (ratio > 0.0f && ratio < 0.5f) {
				nums.add(i);
			}
		}
		Log.d("nums", nums.toString());
		return nums;
	}
	
	public static Choice getRandomChoice(Context context) throws JSONException {
		Choice choice = new Choice();
		JSONObject choicesObject = new JSONObject(
				getJsonString(context, "choices/choices" + getRandomNum(15) + ".json"));
		JSONArray choicesArray = choicesObject.getJSONArray("Choices");
		JSONObject jsonObject = choicesArray.getJSONObject(getRandomNum(53)).getJSONObject("choice");
		choice.setTitle(jsonObject.getString("Title"));
		choice.setA(jsonObject.getString("A"));
		choice.setB(jsonObject.getString("B"));
		choice.setC(jsonObject.getString("C"));
		choice.setD(jsonObject.getString("D"));
		choice.setAnswer(jsonObject.getString("Answer"));
		return choice;
	}
	
	private static int getRandomNum(int size) {
		return (int) (Math.random() * size) + 1; 
	}
	
	private static String getJsonString(Context context, String fileName){ 
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
		try { 
            inputReader = new InputStreamReader(
            		context.getResources().getAssets().open(fileName) ); 
            bufReader = new BufferedReader(inputReader);
            String line="";
            String jsonStr="";
            while((line = bufReader.readLine()) != null) {
            	jsonStr += line;
            }             
            return jsonStr;
        } catch (Exception e) { 
           e.printStackTrace();
        } finally {
        	if (bufReader != null) {
         	   try {
 				bufReader.close();
         	   } catch (IOException e1) {
         		   e1.printStackTrace();
         	   }
            }
            
            if (inputReader != null) {
         	   try {
         		   inputReader.close();
         	   } catch (IOException e1) {
         		   e1.printStackTrace();
         	   }
            }
        }
		return null;
	} 
}
