package com.galaxy.star.newsbox.bean;

public class News {
	private String newId;			//id
	private String newCaption;		//标题
	private String newSubTitle;		//子标题
	private String publishTime;		//发布时间
	private String newSource;		//来源
	private String newAthors;		//作者
	private String newKeyWord;		//关键字
	private String newEditor;		//责任编辑
	private String createTime;		//创建时间
	private String createUser;		//创建用户
	private String newType;			//新闻类型
	private int isWheel;			//是否轮播
	private String wheelTitle;		//轮播标题
	private String wheelImg;		//轮播图片
	private String newListImg1;		//列表图片1
	private String newListImg2;		//列表图片2
	private String newListImg3;		//列表图片3
	private String newContent;		//新闻内容
	private String newUrl;			//生成html后的地址
	private int isPublish;			//是否发布
	private int showIndex;			//发布后显示在列表的顺序
	private int isShowBigImg;		//列表页是否只显示为一张大图
	private int isDel;				//删除标识
	
	public News(){
	}
	
	public String getNewId() {
		return newId;
	}
	public void setNewId(String newId) {
		this.newId = newId;
	}
	public String getNewCaption() {
		return newCaption;
	}
	public void setNewCaption(String newCaption) {
		this.newCaption = newCaption;
	}
	public String getNewSubTitle() {
		return newSubTitle;
	}
	public void setNewSubTitle(String newSubTitle) {
		this.newSubTitle = newSubTitle;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getNewSource() {
		return newSource;
	}
	public void setNewSource(String newSource) {
		this.newSource = newSource;
	}
	public String getNewAthors() {
		return newAthors;
	}
	public void setNewAthors(String newAthors) {
		this.newAthors = newAthors;
	}
	public String getNewKeyWord() {
		return newKeyWord;
	}
	public void setNewKeyWord(String newKeyWord) {
		this.newKeyWord = newKeyWord;
	}
	public String getNewEditor() {
		return newEditor;
	}
	public void setNewEditor(String newEditor) {
		this.newEditor = newEditor;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getNewType() {
		return newType;
	}
	public void setNewType(String newType) {
		this.newType = newType;
	}
	public int getIsWheel() {
		return isWheel;
	}
	public void setIsWheel(int isWheel) {
		this.isWheel = isWheel;
	}
	public String getWheelTitle() {
		return wheelTitle;
	}
	public void setWheelTitle(String wheelTitle) {
		this.wheelTitle = wheelTitle;
	}
	public String getWheelImg() {
		return wheelImg;
	}
	public void setWheelImg(String wheelImg) {
		this.wheelImg = wheelImg;
	}
	public String getNewListImg1() {
		return newListImg1;
	}
	public void setNewListImg1(String newListImg1) {
		this.newListImg1 = newListImg1;
	}
	public String getNewListImg2() {
		return newListImg2;
	}
	public void setNewListImg2(String newListImg2) {
		this.newListImg2 = newListImg2;
	}
	public String getNewListImg3() {
		return newListImg3;
	}
	public void setNewListImg3(String newListImg3) {
		this.newListImg3 = newListImg3;
	}
	public String getNewContent() {
		return newContent;
	}
	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}
	public String getNewUrl() {
		return newUrl;
	}
	public void setNewUrl(String newUrl) {
		this.newUrl = newUrl;
	}
	public int getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(int isPublish) {
		this.isPublish = isPublish;
	}
	public int getShowIndex() {
		return showIndex;
	}
	public void setShowIndex(int showIndex) {
		this.showIndex = showIndex;
	}
	public int getIsShowBigImg() {
		return isShowBigImg;
	}
	public void setIsShowBigImg(int isShowBigImg) {
		this.isShowBigImg = isShowBigImg;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}
