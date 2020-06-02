package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ShippingConfirmation;
import com.cn.hnust.pojo.ShippingConfirmationQuery;

public interface ShippingConfirmationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShippingConfirmation record);

    int insertSelective(ShippingConfirmation record);

    ShippingConfirmation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShippingConfirmation record);

    int updateByPrimaryKey(ShippingConfirmation record);
    
    /**
     * 根据项目号查询当前出运数量
     * type:0  普通出货  1：投诉出货 
     */
    int selectCountByProjectNo(@Param("projectNo")String projectNo,@Param("type")int type);
    /**
     * 根据项目号和阶段查询当前出运数量
     */
    int selectCountByProjectNoAndType(@Param("projectNo")String projectNo,@Param("sampleOrProduct")Integer sampleOrProduct);
    
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
     * 根据项目号查询当前出运数量
     */
    int selectByComplaintId(@Param("complaintId")Integer complaintId);
    
    
    
    
    /**
     * 根据出运编号查询
     * 
     */
    ShippingConfirmation selectBySerialNumber(@Param("serialNumber")String serialNumber);
}