package com.zhidisoft.base;

/**
 * ��װ��������Ӧ��Ϣ�Ļ�����,����Ӧ���Ϊjson��ʽʱ��ʹ�øû���������ݽ��з�װ
 * @author ¬����
 *
 */
public class ResponseResult {

	private boolean success = true;
	
	private String msg = "";
	
	private Object result;
	
	public ResponseResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public ResponseResult(boolean success, String msg, Object result) {
		super();
		this.success = success;
		this.msg = msg;
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	
}
