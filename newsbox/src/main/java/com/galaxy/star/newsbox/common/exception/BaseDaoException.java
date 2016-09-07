package com.galaxy.star.newsbox.common.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.slf4j.Logger;


/**
 * @功能：DAO异常处理类
 * @注意：
 * @开发者：乔乔
 * @加注者：乔乔
 * @时间：2016年8月11日 下午6:26:44
 */
public class BaseDaoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private Throwable cause;   		//记录起因异常
	
	public BaseDaoException (String msg){   
		super(msg);   
	}   
	
	public BaseDaoException(Throwable ex,Logger log){   
		super(ex);   
		log.error("自定义：", ex);
		ex.printStackTrace();
		this.cause = ex;
	}   
	
	public BaseDaoException(Throwable ex){   
		super(ex);   
		ex.printStackTrace();
		this.cause = ex;
	}  
	    
	public BaseDaoException(String msg, Throwable ex){   
		super(msg);   
		this.cause = ex;   
	}   
	    
	public Throwable getCause(){   
		return (this.cause == null ? this :this.cause);   
	}   
	    
	public String getMessage(){   
		String message = super.getMessage();   
		Throwable cause = getCause();   
		if(cause != null){   
			message = message + ";nested Exception is " + cause;   
		}   
		return message;   
	}  
	
	public void printStackTrace(PrintStream ps){   
		if(this.getCause() == null){   
			super.printStackTrace(ps); 
		}else{   
			ps.println(this);   
			getCause().printStackTrace(ps);   
		}   
	}
	
	public void printStackTrace(PrintWriter pw){   
		if(getCause() == null){   
			super.printStackTrace(pw);   
		}else{   
			pw.println(this);   
			getCause().printStackTrace(pw);   
		}   
	} 
	
	public void printStackTrace(){   
		printStackTrace(System.err);   
	}   
}
