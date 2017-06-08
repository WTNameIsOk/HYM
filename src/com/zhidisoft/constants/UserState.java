package com.zhidisoft.constants;

import java.util.ArrayList;
import java.util.List;

public enum UserState {

	/**
	 * ����״̬
	 */
	ENABLE("1","����״̬"),
	/**
	 * ����״̬
	 */
	DISABLE("0","����״̬"),
	/**
	 * ��ְ״̬
	 */
	DIMISSION("-1","��ְ״̬"),
	
	/**
	 * ����״̬
	 */
	RETIREMENT("-2","����״̬"),
	/**
	 * �ݼ�״̬
	 */
	VACATION("-3","�ݼ�״̬");
	
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
