package com.qinlong.notepad;

import java.util.HashMap;
import java.util.Map;

public class TestNotePad {

	public static void main(String[] args)
	{
		Map<String, String> myMap=new HashMap<>();
		myMap.put("2017 4 5", "看了一场电影");
		NotePad mNotePad=new NotePad(myMap);
		mNotePad.add("2015 6 5", "去操场打球");
		mNotePad.add("2017 5 3","在宿舍看电视");
		mNotePad.delete("2017 4 5");
		mNotePad.search("2015 5 6");
		mNotePad.search("2015 6 5");
		mNotePad.showNote();
	}

}
