package com.qinlong.notepad;

import java.util.Iterator;
import java.util.Map;

public class NotePad {
	private Map<String, String> map;

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public NotePad(Map<String, String> map) {
		super();
		this.map = map;
	}

	public void add(String time, String story) {
		map.put(time, story);
	}

	public void delete(String timeKey) {
		map.remove(timeKey);
	}

	public void search(String timeKey) {
		int mark = 0;
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String story = map.get(key);
			if (timeKey.equals(key)) {
				mark++;
				System.out.println(key + " : " + story);
			}
		}
		if (mark == 0) {
			System.out.println("û�м�������Ϣ");
		}
	}

	public void showNote() {
		System.out.println("�¼�������¼��");
		System.out.println("------------------------");
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String story = map.get(key);
			System.out.println(key + " : " + story);
		}
	}
}
