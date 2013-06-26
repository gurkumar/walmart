package main.java;

import java.io.Serializable;

public class ChangeLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**corresponding to the DB column name **/
	private String field;
	/** corresponding to the changed value which needs to be persisted **/
	private Object value;
	
	
	
	public ChangeLog () {
		
	}
	public ChangeLog (String field , Object value) {
		this.field = field;
		this.value = value;
	}
	public String getField () {
		return this.field;
	}
	
	public Object getValue () {
		return this.value;
	}
	
	

}
