package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.DingTalkMileStone;

public interface DingTalkMileStoneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DingTalkMileStone record);

    int insertSelective(DingTalkMileStone record);

    DingTalkMileStone selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DingTalkMileStone record);

    int updateByPrimaryKey(DingTalkMileStone record);
    
    
    /**
     * 根据项目号查询里程碑
     * @Title selectByProjectNo 
     * @Description 
     * @param projectNo
     * @return
     * @return List<DingTalkMileStone>
     */
    List<DingTalkMileStone> selectByProjectNo(String projectNo);
    
    /**
     * 根据uid删除
     * @Title deleteByUid 
     * @Description
     * @param projectNo
     * @return
     * @return int
     */
    int deleteByUid(String uid);
    /**
     * 根据processInstanceId删除
     * @Title deleteByProcessInstanceId 
     * @Description
     * @param projectNo
     * @return
     * @return int
     */
    int deleteByProcessInstanceId(String processInstanceId);
    
    /**
     * 批量插入
     * @Title insertBatch 
     * @Description
     * @param list
     * @return
     * @return int
     */
    int insertBatch(List<DingTalkMileStone> list);
    
    
    /**
     * 批量修改
     * @Title updateBatch 
     * @Description
     * @param list
     * @return
     * @return int
     */
    int updateBatch(List<DingTalkMileStone> list);
    
}