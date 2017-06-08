package com.zhidisoft.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zhidisoft.base.BaseException;
import com.zhidisoft.base.ResponseResult;
import com.zhidisoft.exception.DataAccessException;
import com.zhidisoft.exception.NoSuchActionException;
import com.zhidisoft.exception.NoSuchParameterException;

/**
 * ͳһ�쳣���������
 * 
 * @author ¬����
 *
 */
@WebFilter("/*")
public class ExceptionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;

		ResponseResult result = null;
		// ͨ��try-catch��׽��������г��ֵ��쳣
		try {
			chain.doFilter(httpReq, httpResp);
		} catch (DataAccessException e) {
			e.printStackTrace();
			result = new ResponseResult(false, e.getMessage());
		} catch (NoSuchParameterException e) {
			e.printStackTrace();
			result = new ResponseResult(false, e.getMessage());
		} catch (NoSuchActionException e) {
			e.printStackTrace();
			result = new ResponseResult(false, e.getMessage());
		} catch (BaseException e) {
			e.printStackTrace();
			result = new ResponseResult(false, e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseResult(false, e.getMessage());
		}

		// ���result����Ϊnullֵ��˵��Ӧ�ó�������쳣��Ϣ
		if (result != null) {
			String requestType = httpReq.getHeader("x-requested-with");
			// �жϱ��������Ƿ�Ϊajax��������ǽ�result����ת��Ϊjson��ʽ����������ͻ���,�����ض���500����404ҳ��
			if ("XMLHttpRequest".equals(requestType)) {
				JSONObject json = JSONObject.fromObject(result);
				PrintWriter out = httpResp.getWriter();
				out.print(json.toString());
				out.flush();
				out.close();
				return;
			}

			if (httpResp.getStatus() == 404) {
				httpResp.sendRedirect(httpReq.getContextPath() + "/404.html");
				return;
			}

			if (httpResp.getStatus() >= 500) {
				httpResp.sendRedirect(httpReq.getContextPath() + "/500.html");
				return;
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
