package com.zhidisoft.constants;

import java.util.ArrayList;
import java.util.List;

public enum UserState {

	/**
	 * ¿ÉÓÃ×´Ì¬
	 */
	ENABLE("1","¿ÉÓÃ×´Ì¬"),
	/**
	 * ½ûÓÃ×´Ì¬
	 */
	DISABLE("0","½ûÓÃ×´Ì¬"),
	/**
	 * ÀëÖ°×´Ì¬
	 */
	DIMISSION("-1","ÀëÖ°×´Ì¬"),
	
	/**
	 * ÍËÐÝ×´Ì¬
	 */
	RETIREMENT("-2","ÍËÐÝ×´Ì¬"),
	/**
	 * ÐÝ¼Ù×´Ì¬
	 */
	VACATION("-3","ÐÝ¼Ù×´Ì¬");
	
	private UserState(String code,String text){
		this.code = code;
		this.text = text;
	}
	
	private String code;
	
	private String text;
	
	public String getCode(){
		return code;
	}
	
	public String getText(){
		return text;
	}
	
	public static List<UserState> getList(){
		List<UserState> list = new ArrayList<UserState>();
		list.add(ENABLE);
		list.add(DISABLE);
		list.add(DIMISSION);
		list.add(VACATION);
		list.add(RETIREMENT);
		return list;
	}
	
	public static UserState getUserState(String code){
		for(UserState state : getList()){
			if(state.getCode().equals(code)){
				return state;
			}
		}
		return null;
	}
	
	
}
