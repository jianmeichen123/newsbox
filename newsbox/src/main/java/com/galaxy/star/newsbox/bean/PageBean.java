package com.galaxy.star.newsbox.bean;

public class PageBean {
	private int start;
	private int pageSize;
	
	public PageBean(){
	}
	
	public PageBean(int start,int pageSize){
		this.start = start;
		this.pageSize = pageSize;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
