package com.cn.hnust.service;


import java.text.ParseException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ShippingConfirmation;
import com.cn.hnust.pojo.ShippingConfirmationQuery;

public interface ShippingConfirmationService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ShippingConfirmation record);

    ShippingConfirmation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShippingConfirmation record, String userName) throws ParseException;
    
    
    
    /**
     * 根据项目号查询当前出运数量
     */
    int selectCountByProjectNo(String projectNo,int type);
    
    
    /**
     * 根据项目号查询
     * @Title selectByProjectNo 
     * @Description
     * @param projectNo
     * @return
     * @return List<ShippingConfirmation>
     */
    List<ShippingConfirmation> selectByProjectNo(ShippingConfirmationQuery shippingConfirmationQuery);
    
    
    /**
     * 查询数量
     * @Title count 
     * @Description
     * @param shippingConfirmationQuery
     * @return
     * @return int
     */
    int count(ShippingConfirmationQuery shippingConfirmationQuery);
    
    
    
    /**
     * 根据项目号和阶段查询当前出运数量
     */
    int selectCountByProjectNoAndType(String projectNo,Integer sampleOrProduct);
    
    
    
    /**
     * 根据出运编号查询
     * 
     */
    ShippingConfirmation selectBySerialNumber(String serialNumber);

    void updateByPrimaryKeySelective1(ShippingConfirmation projectComplaint);

    // 钉钉审批列表
    List<ShippingConfirmation> queryShippingConfirmationByNo(String projectNo);

}
