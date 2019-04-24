package com.jfhealthcare.common.entity;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class MyPageInfo<T> extends PageInfo<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private T value;
	
	private List<?> otherValue;
	
	private Integer isRemind;

	private Integer isRepulse;
	
	public MyPageInfo(List<T> list) {
		super(list);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public List<?> getOtherValue() {
		return otherValue;
	}

	public void setOtherValue(List<?> otherValue) {
		this.otherValue = otherValue;
	}

	public Integer getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(Integer isRemind) {
		this.isRemind = isRemind;
	}

	public Integer getIsRepulse() {
		return isRepulse;
	}

	public void setIsRepulse(Integer isRepulse) {
		this.isRepulse = isRepulse;
	}
}
