package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.PayOthers;



public interface PayOtherService {
/**
 * 
 * @Title:PayOtherService
 * @Description:获取全部待处理数据
   @author wangyang
 * @return List<PayOther>
 * @throws
 */
	List<PayOthers> getAllPending();
/**
 * 
 * @Title:PayOtherService
 * @Description:修改状态
   @author wangyang
 * @param processInstanceId void
 * @throws
 */
void updatePassERP(int id,int num);

}
