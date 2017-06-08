package com.zhidisoft.util;
/**
 * ��ѯ����������
 * @author ¬����
 *
 */
public class QueryCriteria {
	
	//���ڴ洢�������
	private StringBuffer criteria = new StringBuffer();
	
	
	public QueryCriteria(){
		
	}
	/**
	 * ����������ʽ������ʹ��and���ñ��ʽ���Ѿ����ڵı��ʽ��������
	 * @param ex
	 * @return
	 */
	public QueryCriteria add(Expression ex){
		if(ex.getExpression().length()>0){
			if(criteria.length()>0){
				criteria.append(" and ");
			}
			criteria.append(ex.getExpression());
		}
		return this;
	}
	
	/**
	 * ����������ʽ������ʹ��or���ñ��ʽ���Ѿ����ڵı��ʽ��������
	 * @param ex
	 * @return
	 */
	public QueryCriteria or(Expression ex){
		if(ex.getExpression().length()>0){
			if(criteria.length()>0){
				criteria.append(" or ");
			}
			criteria.append(ex.getExpression());
		}
		return this;
	}
	
	public boolean isEmpty(){
		return this.criteria.length()>0 ? false : true;
	}
	
	public String toString(){
		return criteria.toString();
	}
	
}
