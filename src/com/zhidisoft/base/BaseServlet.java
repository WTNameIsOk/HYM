package com.zhidisoft.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhidisoft.exception.NoSuchActionException;
import com.zhidisoft.exception.NoSuchParameterException;

public class BaseServlet extends HttpServlet{

	
	private static final long serialVersionUID = -155258685468332583L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ���������action�������ò������ڱ�ʾ����������ķ���
		String action = request.getParameter("action");
		if(action == null || action.isEmpty()){
			throw new NoSuchParameterException("��������δ����action����");
		}
		
		Class<?> servletClass = this.getClass();
		try {
			//����actionֵ��ȡ�����Ӧ����ķ���
			Method actionMethod = servletClass.getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			//���ö�Ӧ���������û�����
			actionMethod.invoke(this, request,response);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new NoSuchActionException("��������ȷ�ٴ����������"+action+"(HttpServletRequest request, HttpServletResponse response)����");
		} catch (Exception e){
			e.printStackTrace();
			throw new BaseException("�������˳���"+e.getMessage());
		}
	}
	
	/**
	 * ���������������ķ���
	 * @param request
	 * @param response
	 */
	protected void save(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * ���������������ķ���
	 * @param request
	 * @param response
	 */
	protected void update(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * �����޸�����ǰ��ȡ������Ϣ�ķ���
	 * @param request
	 * @param response
	 */
	protected void edit(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * ����ɾ��ĳ����Ϣ�����󷽷�
	 * @param request
	 * @param response
	 */
	protected void remove(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * �����ȡ������Ϣ����ķ���
	 * @param request
	 * @param response
	 */
	protected void get(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * �����ҳ����ķ���
	 * @param request
	 * @param response
	 */
	protected void page(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * �����ȡ�����б���Ϣ�����󷽷�
	 * @param request
	 * @param response
	 */
	protected void list(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * ����ַ���
	 * @param content
	 * @param response
	 * @throws IOException
	 */
	protected void print(String content,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.append(content);
		out.flush();
	}
	
	protected void printJSONObject(JSONObject json,HttpServletResponse response) throws IOException{
		response.setContentType("application/json; charset="+response.getCharacterEncoding());
		print(json.toString(), response);
	}
	
	protected void printJSONArray(JSONArray json,HttpServletResponse response) throws IOException{
		response.setContentType("application/json; charset="+response.getCharacterEncoding());
		print(json.toString(), response);
	}
}
