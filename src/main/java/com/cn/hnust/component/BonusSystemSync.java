package com.cn.hnust.component;

import com.cn.hnust.pojo.ErpQualityReport;
import com.cn.hnust.util.DBBonusSystemPoolUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.cn.hnust.component
 * @date:2021-03-26
 */
public class BonusSystemSync {

    private final static Logger log = Logger.getLogger(BonusSystemSync.class);

    /**
     * 插入数据到分红系统
     *
     * @param list
     * @return
     */
    public boolean insertQualityReportData(List<ErpQualityReport> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        Connection conn = null;
        String sql = "insert into erp_project_quality_report(project_no,add_user,add_time,type,type_desc)" +
                "        values(?,?,?,?,?)";
        try {
            conn = DBBonusSystemPoolUtil.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            for (ErpQualityReport qr : list) {
                int count = 1;
                psmt.setString(count++, qr.getProject_no());
                psmt.setString(count++, qr.getAdd_user());
                psmt.setString(count++, qr.getAdd_time());
                psmt.setString(count++, qr.getType());
                psmt.setString(count, qr.getType_desc());
                psmt.addBatch();
            }
            int length = psmt.executeBatch().length;
            return length == list.size();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("<insertQualityReportData>>同步数据失败", e);
        } finally {
            DBBonusSystemPoolUtil.returnConnection(conn);
        }
        return false;
    }


    public List<ErpQualityReport> selectQualityReportData(String project_no) {
        List<ErpQualityReport> list = new ArrayList<>();
        Connection conn = null;
        ResultSet resultSet = null;
        String sql = "select id,project_no,add_user,DATE_FORMAT(add_time,'%Y-%m-%d %H:%i:%S') as add_time,type,type_desc" +
                "        from erp_project_quality_report where project_no = ?";
        try {
            conn = DBBonusSystemPoolUtil.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, project_no);
            resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                ErpQualityReport qr = new ErpQualityReport();

                qr.setProject_no(resultSet.getString("project_no"));
                qr.setAdd_user(resultSet.getString("add_user"));
                qr.setAdd_time(resultSet.getString("add_time"));
                qr.setType(resultSet.getString("type"));
                list.add(qr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("<selectQualityReportData>>同步数据失败", e);
        } finally {
            DBBonusSystemPoolUtil.returnConnection(conn);
            DBBonusSystemPoolUtil.returnResult(resultSet);
        }
        return list;
    }

}
