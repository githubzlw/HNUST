package com.cn.hnust.util;

import java.util.Comparator;
import com.cn.hnust.pojo.ProjectFactory;


public class ComplaintSort implements Comparator<ProjectFactory>{

     
	     
	     //重写list比较方法（地区排序）
	     @Override
	     public int compare(ProjectFactory p1, ProjectFactory p2) {
	    	   	 
	    	
	    	 if(p1==null){
	    		 return -1;
	    	 }
	    	 if(p2==null){
	    		 return 1;
	    	 }
	    	 Double complaintRate1 = p1.getComplaintRate();
	    	 Double complaintRate2 = p2.getComplaintRate();
	    	 if(complaintRate1 != null && complaintRate2 !=null){	    	
				if(p1.getDelayRate() > p2.getDelayRate()){
					return -1;
				}
				if(p1.getDelayRate() < p2.getDelayRate()){
					return 1;
				}								
	    	 }else if(complaintRate1 != null && complaintRate2 ==null){
	    		 return -1;
	    	 }else if(complaintRate1 == null && complaintRate2 !=null){
	    		 return 1;
	    	 }
	    	 return 0;
	     }
	
		
    }
    
