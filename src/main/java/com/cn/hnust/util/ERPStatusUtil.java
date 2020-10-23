package com.cn.hnust.util;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.cn.hnust.util
 * @date:2020-10-23
 */
public class ERPStatusUtil {

    // ERP系统转换成任务系统的状态
    public static int changeToTaskStatus(int erpStatus) {
        int taskStatus = 0;
        switch (erpStatus) {
            case 1://暂停状态
                taskStatus = 4; // 项目暂停
                break;
            case 5://完成项目
                taskStatus = 2;// 大货完结
                break;
            case 2://报价发出状态
            case 8://紧急状态
            case 9://不计时状态
                taskStatus = 1;// 正常进行项目
                break;
            default:
                taskStatus = 0;
        }
        return taskStatus;
    }

    // 任务系统转ERP系统换成的状态
    public static int changeToERPStatus(int taskStatus) {
        int erpStatus = 0;
        switch (taskStatus) {
            case 4:// 项目暂停
                erpStatus = 1; //暂停状态
                break;
            case 2:// 大货完结
                erpStatus = 5;//完成项目
                break;
            case 1:// 正常进行项目
                erpStatus = 9;//跟单状态
                break;
            default:
                erpStatus = 0;
        }
        return erpStatus;
    }
}
