package com.cn.hnust.util;

import java.util.Comparator;
import com.cn.hnust.pojo.ProjectFactory;


public class DelaySort implements Comparator<ProjectFactory>{

     
	     
	     //重写list比较方法（地区排序）
	     @Override
	     public int compare(ProjectFactory p1, ProjectFactory p2) {
	    	   	 
	    	
	    	 if(p1==null){
	    		 return -1;
	    	 }
	    	 if(p2==null){
	    		 return 1;
	    	 }
	    	 Double delayRate1 = p1.getDelayRate();
	    	 Double delayRate2 = p2.getDelayRate();
	    	 if(delayRate1 != null && delayRate2 !=null){	    	
				if(p1.getDelayRate() > p2.getDelayRate()){
					return -1;
				}
				if(p1.getDelayRate() < p2.getDelayRate()){
					return 1;
				}								
	    	 }else if(delayRate1 != null && delayRate2 ==null){
	    		    return -1;	 
	    	 }else if(delayRate1 == null && delayRate2 !=null){
	    		    return 1;	 
	    	 }
	    	 return 0;
	     }
	
		
    }
    
