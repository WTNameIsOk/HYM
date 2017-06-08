package com.zhidisoft.util;

import java.util.UUID;

/**
 * ���������
 * @author ¬����
 *
 */
public class RandomUtil {
	/**
	 * ����UUID
	 * @return -����ȥ��"-"���32λ�ַ����Ĵ�дUUIDֵ
	 */
	public static String randomUUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * ����ĳһ����Χ�ڵ������
	 * @param min 
	 * @param max
	 * @return
	 */
	public static int randomInt(int min,int max){
		return min + (int)(Math.random()*max);
	}
	
	public static int randomInt(int max){
		return randomInt(0,max);
	}

}
