package com.qimeng.main.util;
/** 
* @author  作者 E-mail: 
* @date 创建时间：2019年8月6日 下午10:59:14 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class StaticGlobal {
	
	//app权限
	public static final byte READ=0; 
	public static final byte ADD=1;
	public static final byte SUB=2;
	public static final byte UPDATE=4;
	
	//是否可用
	public static final byte ACTIVE=1;
	public static final byte CLOSED=2;
	
	//机器状态
	public static final byte UNUSED=0;
	public static final byte NORMAL=1;
	public static final byte ERROR=2;
	public static final byte REPAIR=3;
	
	//学生类型
	public static final byte STUDENT=0;
	public static final byte TEACHER=1;
	
	//是否使用手机参数
	public static final byte PHONE=2;
	public static final byte NOPHONE=0;
	
	//学生绑定
	public static final byte BIND=1;
	public static final byte NOBIND=0;
	
	//机器开仓状态
	public static final byte OPEN=1;
	public static final byte CLOSE=0;
	public static final byte UPLOAD=2;
	
	//开仓单类型
	public static final byte RECORDER=1;
	public static final byte REPORDER=0;
}

