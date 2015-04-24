//package com.icecub3.recofood;
//
//public class Items {
//
//	int _id;
//	String _dish_name;
//	String _last_date;
//
//	public Items() {
//	}
//
//	public Items(int id, String dish_name, String last_date) {
//		this._id = id;
//		this._dish_name = dish_name;
//		this._last_date = last_date;
//	}
//
//	public Items(String dish_name, String last_date) {
//		this._dish_name = dish_name;
//		this._last_date = last_date;
//	}
//
//	public int getId() {
//		return this._id;
//	}
//
//	public void setId(int id) {
//		this._id = id;
//	}
//
//	public String getDishName() {
//		return this._dish_name;
//	}
//
//	public void setDishName(String dish_name) {
//		this._dish_name = dish_name;
//	}
//
//	public String getLastDate() {
//		return this._last_date;
//	}
//
//	public void setLastDate(String last_date) {
//		this._last_date = last_date;
//	}
//}


package com.icecub3.recofood;

public class Items {
	int _id;
	String _item;
	int _countYes;
	int _countNo;
	int _probYes;
	int _dispThisSession;
	
	public Items() {
	}
	
	public Items(int id, String item, int countYes, int countNo, int probYes, int dispThisSession) {
		this._id = id;
		this._item = item;
		this._countYes = countYes;
		this._countNo = countNo;
		this._probYes = probYes;
		this._dispThisSession = dispThisSession;
	}
	
	public Items(String item, int countYes, int countNo, int probYes, int dispThisSession) {
		this._item = item;
		this._countYes = countYes;
		this._countNo = countNo;
		this._probYes = probYes;
		this._dispThisSession = dispThisSession;
	}
	
	public int getId() {
		return this._id;
	}
	
	public void setId(int id) {
		this._id = id;
	}
	
	public String getItem() {
		return this._item;
	}
	
	public void setItem(String item) {
		this._item = item;
	}
	
	public int getCountYes() {
		return this._countYes;
	}
	
	public void setCountYes(int countYes) {
		this._countYes = countYes;
	}
	
	public int getCountNo() {
		return this._countNo;
	}
	
	public void setCountNo(int countNo) {
		this._countNo = countNo;
	}
	
	public int getProbYes() {
		return this._probYes;
	}
	
	public void setProbYes(int probYes) {
		this._probYes = probYes;
	}
	
	public int getDispThisSession() {
		return this._dispThisSession;
	}
	
	public void setDispThisSession(int dispThisSession) {
		this._dispThisSession = dispThisSession;
	}
}