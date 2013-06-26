package main.java;

public class ChangeLogException extends Exception{
	


	    private static final long serialVersionUID = 1L;
	    private String errorMessage;   
	  
	    public ChangeLogException (String errorMessage) {
	        super();
	        this.errorMessage = errorMessage;
	    }
	    
	    public String getErrorMessage () {
	    	return this.errorMessage;
	    }



}
