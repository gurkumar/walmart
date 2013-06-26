package main.java;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenerateChangeLog {
	
	private static final Set<Class<?>> LANGUAGE_DEFINED_TYPE = getLanguageDefinedTypes();
	/***
	 * {{@link #getChangeLog(Object, Object)} returns the list of changed fields between two objects.
	 * composed objects are queried recursively to get the changed fields.
	 * @param newObj
	 * @param oldObj
	 * @return
	 * @throws ChangeLogException
	 */
	public List<ChangeLog> getChangeLog (Object newObj, Object oldObj) throws ChangeLogException{
		
		/** precondition to check if both objects belong to same class **/
		
		   if (newObj.getClass() == oldObj.getClass()) {
			
		/** As the fields are not returned in any specific order, fields from oldObj are put in a Map**/
			Map <String , Object> oldObjfieldNameToValueMap = new HashMap <String, Object> ();
		    
			Field[] oldObjFields = oldObj.getClass().getDeclaredFields();
		    
		    for (int i=0; i<oldObjFields.length; i++) {
		    	
		    	oldObjFields[i].setAccessible(true);
		    	
		    	oldObjfieldNameToValueMap.put(oldObjFields[i].getName(), oldObjFields[i]);
		    	
		    	
		    }
		    
		    Field[] newObjFields = newObj.getClass().getDeclaredFields();
		    
		    List<ChangeLog> changeLogList = new ArrayList <ChangeLog> ();
		    
		    for (int i = 0; i< newObjFields.length ; i++){
		    	
		    	newObjFields[i].setAccessible(true);
		   
		    	if (isLanguageDefinedType (newObjFields[i].getType())) {
		    	
		          ChangeLog changeLog = getChangeValue (newObjFields[i] , newObj, oldObjfieldNameToValueMap.get(newObjFields[i].getName()), oldObj);
		        
		          if (changeLog != null) {
		         		        	
		           changeLogList.add(changeLog);	
		        	
		          } 
		          
		    	 } else {
		        	//assuming it is composed object or user defined type
		            
		        	try {
						changeLogList.addAll(getChangeLog 
								(newObjFields[i].get(newObj), 
							((Field)oldObjfieldNameToValueMap.get(newObjFields[i].getName())).get(oldObj)));
					} catch (IllegalArgumentException e) {
						
						 throw new ChangeLogException ("Illegal Access Exception for field " + newObjFields[i].getName());
						 
					} catch (IllegalAccessException e) {
						
						 throw new ChangeLogException ("Illegal Access Exception for field " + newObjFields[i].getName());
						 
					}
		          
		        }  
		        		 
		     
		    }    
			
		    return changeLogList;
		 	
		} else {
			throw new ChangeLogException ("obejcts being compared do not belong to the same class");
		}
		
		
	}
	
	/**
	 * {{@link #getChangeValue(Object, Object, Object, Object)} returns the change value and name of the changed
	 * field from one object to another.
	 * @param newField
	 * @param newFieldParentObject
	 * @param oldField
	 * @param oldFieldParentObject
	 * @return
	 * @throws ChangeLogException
	 */
	
	private ChangeLog getChangeValue (Object newField, Object newFieldParentObject, 
			Object oldField, Object oldFieldParentObject) throws ChangeLogException{
	
   try {		
    if (newFieldParentObject.getClass() == newFieldParentObject.getClass()
    	&& newField.getClass() == oldField.getClass()) {
	
	  if ( newField instanceof Field) {
		  
		 	/** taking data types most commonly used in DataBases. This can be further extended to include more types**/ 
		   
    	 
   	    if ( ((Field) newField).getType().isAssignableFrom(String.class)){
		    	
	    	 String oldObjValue = (String)((Field)oldField).get (oldFieldParentObject);
	    		
	    	 String newObjValue = (String)((Field)newField).get (newFieldParentObject);
	    		
	    	 if ( oldObjValue != null && newObjValue != null && (! oldObjValue.equals(newObjValue))) {
	    		 
	    		 return new ChangeLog (((Field) newField).getName() , newObjValue);
	    			
	    		   
	    		} 
		         
		    } else if (((Field) newField).getType().isAssignableFrom(Integer.class)) {
		    	
              Integer oldObjValue = (Integer)((Field)oldField).get (oldFieldParentObject);
	    		
	    	  Integer newObjValue = (Integer)((Field)newField).get (newFieldParentObject);
	    		
	    		if ( oldObjValue != null && newObjValue != null &&  oldObjValue != newObjValue) {
	    		
	    		    return new ChangeLog (((Field) newField).getName() , newObjValue);
		    			
		    	} 
	    		
		    } else if (((Field) newField).getType().isAssignableFrom(Date.class)) {
		    	
              Date oldObjValue = (Date)((Field)oldField).get (oldFieldParentObject);
	    		
	    	  Date newObjValue = (Date)((Field)newField).get (newFieldParentObject);
	    	
	    	  if (oldObjValue != null && newObjValue != null) { 
	    	    int result = oldObjValue.compareTo(newObjValue);
	    		
	    		if ( result != 0 ) {
	    			
	    			return new ChangeLog (((Field) newField).getName() , newObjValue);
		    		
	    		}		
	    			
	    	  } 
		    } else {
		    	/**assuming that all inbuilt data types defined in the object classes working with ChangeLog are either String, Integer, Date type
		    	   if new data type has to be inserted, it needs to be handled above or else it is a user defined class
		    	**/
		    	throw new ChangeLogException ("Unsupported language defined type. Please add support!!");
		    }
	     }
       }
      } catch (IllegalAccessException illegalAccessException) {
    	  throw new ChangeLogException ("Illegal Access Exception for field " + ((Field)newField).getName());
      }
      return null;
	}
	
	
	
	 private boolean isLanguageDefinedType(Class<?> clazz)
	    {
	        return LANGUAGE_DEFINED_TYPE.contains(clazz);
	    }
	
	 /**
		 * set defining the language supported data types. Can add more support as needed
		 * @param clazz
		 * @return
		 */
	 private static Set<Class<?>> getLanguageDefinedTypes()
	    {
	        Set<Class<?>> ret = new HashSet<Class<?>>();
	        ret.add(Integer.class);
	        ret.add(String.class);
	        ret.add(Date.class);
	        return ret;
	    }
	
}
