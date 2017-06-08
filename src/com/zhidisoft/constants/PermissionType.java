package com.zhidisoft.constants;

import java.util.ArrayList;
import java.util.List;

public enum PermissionType {
	/**
	 * ��������ԱȨ��
	 */
	SUPERADMIN("1","��������Ա"),
	/**
	 * ��ͨ����Ա
	 */
	ADMIN("2","����Ա"),
	/**
	 * ˰��רԱ
	 */
	TAXER("3","˰��רԱ");
	
	
	private PermissionType(String code,String text){
		this.code = code;
		this.text = text;
	}
	
	private String code;
	
	private String text;

	public String getCode() {
		return code;
	}

	public String getText() {
		return text;
	}
	
	public static List<PermissionType> getList(){
		List<PermissionType> list = new ArrayList<PermissionType>();
		list.add(SUPERADMIN);
		list.add(ADMIN);
		list.add(TAXER);
		return list;
	}
	
	public static PermissionType getPermissionType(String code){
		for(PermissionType type : getList()){
			if(type.getCode().equals(code)){
				return type;
			}
		}
		return null;
	}
	
	public String toString(){
		return "PermissionType [code="+code+",text="+text+"]";
	}

}
