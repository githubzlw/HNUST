package com.cn.hnust.print;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cn.hnust.pojo.*;
import org.apache.bcel.generic.I2F;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.enums.QualityStatusEnum;
import com.cn.hnust.enums.QualityTypeEnum;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.UploadAndDownloadPathUtil;


/**
 * @Description: ????????????????????????excel
 * @Author: polo
 * @CreateDate:2018/08/24
 */

public class ProjectStatisticsPrint {

    private static File tempPath;

    /**
     * pdf??????,??????excel???????????????pdf
     *
     * @param
     * @throws Exception
     */
    public static String printExcel(HttpServletRequest request, List<Project> sampleFinishes
            , List<Project> productFinishs, List<Project> normals, List<Project> suspension, List<Project> cancellation) throws Exception {
        //??????????????????
        int sample_tl = 0;
        if (sampleFinishes != null && sampleFinishes.size() > 0) {
            sample_tl = sampleFinishes.size();
        }
        //??????????????????
        int product_tl = 0;
        if (productFinishs != null && productFinishs.size() > 0) {
            product_tl = productFinishs.size();
        }
        //?????????????????????
        int normal_tl = 0;
        if (normals != null && normals.size() > 0) {
            normal_tl = normals.size();
        }
        //????????????
        int suspension_tl = 0;
        if (suspension != null && suspension.size() > 0) {
            suspension_tl = suspension.size();
        }
        //????????????
        int cancellation_tl = 0;
        if (cancellation != null && cancellation.size() > 0) {
            cancellation_tl = cancellation.size();
        }

        //??????workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        //??????sheet
        HSSFSheet sheet = wb.createSheet("????????????????????????");

        HSSFFont font = wb.createFont();
        font.setFontName("??????");
        font.setFontHeightInPoints((short) 16);//??????????????????


        HSSFFont font2 = wb.createFont();
        font2.setFontName("??????");
        font2.setFontHeightInPoints((short) 16);//??????????????????
        font2.setColor(HSSFColor.RED.index);


        //?????????row:????????????0???
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy/m/d"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(HSSFColor.BLACK.index);

        //??????????????????
        HSSFCellStyle boderStyle = wb.createCellStyle();
        boderStyle.setAlignment(HorizontalAlignment.CENTER);
        boderStyle.setBorderBottom(BorderStyle.THIN);
        boderStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderLeft(BorderStyle.THIN);
        boderStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderRight(BorderStyle.THIN);
        boderStyle.setRightBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderTop(BorderStyle.THIN);
        boderStyle.setTopBorderColor(HSSFColor.BLACK.index);


        //????????????????????????
        HSSFCellStyle lastStyle = wb.createCellStyle();
        lastStyle.setAlignment(HorizontalAlignment.CENTER);
        lastStyle.setBorderBottom(BorderStyle.THIN);
        lastStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderLeft(BorderStyle.THIN);
        lastStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderRight(BorderStyle.THIN);
        lastStyle.setRightBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderTop(BorderStyle.THIN);
        lastStyle.setTopBorderColor(HSSFColor.BLACK.index);
        lastStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// ???????????????
        lastStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);


        //???????????????
        HSSFCell cell = row.createCell(0); //??????????????????
        cell.setCellValue("????????????");
        cell.setCellStyle(style);
        style.setFont(font2);


        //??????????????????
        int factoryNum = 0;
        int startCol = 0;
        String factoryName = "";
        Date endDate = null;
        //????????????????????????????????????15?????????????????????????????????15??????15?????????15??????????????????????????????15???
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        month = cal.get(Calendar.MONTH);
        cal.add(cal.MONTH, -1);
        int maxCurrentMonthDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //??????15?????????
        String d = year + "-" + month + "-" + maxCurrentMonthDay;
        //???????????????15?????????
        endDate = DateUtil.StrToDate(d);

        DecimalFormat df = new DecimalFormat("??###,##0.00");

        for (int i = 0; i < sample_tl; i++) {
            if (i == 0) {
                row = sheet.createRow(1);
                cell = row.createCell(0); //???????????????
                cell.setCellValue("??????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(3); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(4); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(5); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("???????????????");
                cell = row.createCell(6); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(7); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");
                cell = row.createCell(8); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(9); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(10); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellStyle(style);
                cell.setCellValue("???????????????????????????");
                cell = row.createCell(11); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(12); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????" + maxCurrentMonthDay + "???????????????");


            }

            int spendDays = 0; //?????????
            int delayDays = 0; //????????????

            //?????????
            if (sampleFinishes.get(i).getSampleFinishTime() != null && sampleFinishes.get(i).getDateSampleUploading() != null) {
                spendDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(), sampleFinishes.get(i).getDateSampleUploading());
            } else if (sampleFinishes.get(i).getDateSampleUploading() == null && sampleFinishes.get(i).getActualStartDate() != null) {
                spendDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(), sampleFinishes.get(i).getActualStartDate()) - 7;
            }

            //????????????
            if (sampleFinishes.get(i).getSampleFinishTime() != null && sampleFinishes.get(i).getSampleScheduledDate() != null && sampleFinishes.get(i).getDelayDay() == 0) {
                if (sampleFinishes.get(i).getLateDeliveryDate() != null) {
                    delayDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(), sampleFinishes.get(i).getLateDeliveryDate());

                } else {
                    delayDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(), sampleFinishes.get(i).getSampleScheduledDate()) - 7;
                }
            } else if (sampleFinishes.get(i).getSampleFinishTime() != null && sampleFinishes.get(i).getSampleScheduledDate() != null && sampleFinishes.get(i).getDelayDay() != 0) {
                if (sampleFinishes.get(i).getLateDeliveryDate() != null) {
                    delayDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(), sampleFinishes.get(i).getLateDeliveryDate());

                } else {
                    delayDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(), sampleFinishes.get(i).getSampleScheduledDate()) - 7 - sampleFinishes.get(i).getDelayDay();
                }
            }


            row = sheet.createRow(i + 2);
            cell = row.createCell(0); //???????????????
            cell.setCellValue(i + 1);
            cell.setCellStyle(boderStyle);
            cell = row.createCell(1); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getProjectNo());

            cell.setCellStyle(boderStyle);
            cell = row.createCell(2); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getPurchaseName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(3); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getSellName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(4); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getCompanyName());
            if (factoryName != null && factoryName.equals(sampleFinishes.get(i).getCompanyName())) {
                factoryNum++;

            } else {
                if (factoryNum != 0) {
                    CellRangeAddress region = new CellRangeAddress(startCol, startCol + factoryNum, (short) 4, (short) 4);
                    sheet.addMergedRegion(region);
                }
                factoryNum = 0;
                factoryName = sampleFinishes.get(i).getCompanyName();
                startCol = 2 + i;
            }
            cell.setCellStyle(boderStyle);
            cell = row.createCell(5); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getProjectName() == null ? "" : sampleFinishes.get(i).getProjectName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(6); //???????????????
            cell.setCellStyle(cellStyle);
            if (sampleFinishes.get(i).getOriginalSampleScheduledDate() != null) {
                cell.setCellValue(sampleFinishes.get(i).getOriginalSampleScheduledDate());
            }


            //??????????????????
            cell = row.createCell(7); //???????????????
            cell.setCellStyle(boderStyle);
            cell.setCellValue(sampleFinishes.get(i).getDelayDay());

            //??????????????????
            cell = row.createCell(8); //???????????????

            if (sampleFinishes.get(i).getInterpretationDocument() != null) {
                String name = "https://www.kuaizhizao.cn/product_img/" + sampleFinishes.get(i).getProjectNo() + "/" + sampleFinishes.get(i).getInterpretationDocument();
                HSSFWorkbook workbook = new HSSFWorkbook();
                CreationHelper createHelper = workbook.getCreationHelper();
                cell.setCellValue("????????????");
                HSSFHyperlink link = (HSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_URL);
                link.setAddress(name);
                cell.setHyperlink((HSSFHyperlink) link);
                cell.setCellValue("????????????");
                cell.setCellStyle(boderStyle);
            } else {
                cell.setCellValue("");
                cell.setCellStyle(cellStyle);
            }

            cell = row.createCell(9); //???????????????
            cell.setCellStyle(boderStyle);
            if (sampleFinishes.get(i).getLateDeliveryDate() != null) {
                cell.setCellValue(sampleFinishes.get(i).getLateDeliveryDate());
            } else {
                cell.setCellValue("");
            }
            //?????????
            cell = row.createCell(10); //???????????????
            cell.setCellValue(spendDays);
            cell.setCellStyle(boderStyle);
            //???????????????????????????????????????????????????15????????????15???
            //????????????????????????15????????????????????????
            int chargeDays = 0;
            int chargeDays2 = 0;
            if (sampleFinishes.get(i).getSampleFinishTime().getTime() >= endDate.getTime() && delayDays > 30) {
                chargeDays = DateFormat.calDays(endDate, DateUtil.getPrevMonthDate(endDate));
            } else if (sampleFinishes.get(i).getSampleFinishTime().getTime() > DateUtil.getPrevMonthDate(endDate).getTime()) {
                chargeDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(), DateUtil.getPrevMonthDate(endDate)) - 1;
            }
            //?????????????????????????????????????????????????????????
            if (chargeDays > delayDays) {
                chargeDays = delayDays;
            }
            //????????????????????????15???????????????????????????????????????
            if (sampleFinishes.get(i).getSampleScheduledDate() != null && sampleFinishes.get(i).getSampleScheduledDate().getTime() >= DateUtil.getPrevMonthDate(endDate).getTime()) {
                chargeDays = delayDays;
            }


            //????????????
            cell = row.createCell(11); //???????????????
            cell.setCellStyle(cellStyle);
            cell.setCellValue("??????" + delayDays + "???");

            //????????????????????????
            cell = row.createCell(12); //???????????????
            cell.setCellStyle(lastStyle);
            if (chargeDays != 0) {
                String money = chargeDays + "???*50??? =" + chargeDays * 50 + "???";
                cell.setCellValue(money);
            } else {
                cell.setCellValue(0);
            }
        }


        int extend_tl = 0; //??????????????????
        extend_tl = sample_tl + 3;
        row = sheet.createRow(extend_tl);
        cell = row.createCell(0);
        cell.setCellValue("????????????");
        cell.setCellStyle(style);
        style.setFont(font2);
        for (int i = 0; i < product_tl; i++) {
            if (i == 0) {
                row = sheet.createRow(extend_tl + 1);
                cell = row.createCell(0); //???????????????
                cell.setCellValue("??????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(3); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(4); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(5); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("???????????????");
                cell = row.createCell(6); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(7); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");
                cell = row.createCell(8); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(9); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(10); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellStyle(style);
                cell.setCellValue("???????????????????????????");
                cell = row.createCell(11); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(12); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????" + maxCurrentMonthDay + "???????????????");
            }


            int spendDays = 0;   //?????????
            int delayDays = 0;   //????????????
            //?????????
            if (productFinishs.get(i).getFinishTime() != null && productFinishs.get(i).getDateSampleUploading() != null) {

                spendDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getDateSampleUploading());

            } else if (productFinishs.get(i).getDateSampleUploading() == null && productFinishs.get(i).getActualStartDate() != null) {

                spendDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getActualStartDate()) - 7;

            }

            //????????????
            if (productFinishs.get(i).getFinishTime() != null && productFinishs.get(i).getDeliveryDate() != null && productFinishs.get(i).getDelayDay() == 0) {
                if (productFinishs.get(i).getLateDeliveryDate() != null) {
                    delayDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getLateDeliveryDate());
                } else {
                    delayDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getDeliveryDate()) - 7;
                }
            } else if (productFinishs.get(i).getFinishTime() != null && productFinishs.get(i).getDeliveryDate() != null && productFinishs.get(i).getDelayDay() != 0) {
                if (productFinishs.get(i).getLateDeliveryDate() != null) {
                    delayDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getLateDeliveryDate());
                } else {
                    delayDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getDeliveryDate()) - 7 - productFinishs.get(i).getDelayDay();
                }
            }
//        	 if(productFinishs.get(i).getUrgentDeliveryDate() != null){
//        		 delayDays =  DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getUrgentDeliveryDate());
//        	 }
            row = sheet.createRow(extend_tl + i + 2);
            cell = row.createCell(0); //???????????????
            cell.setCellValue(i + 1);

            cell.setCellStyle(boderStyle);
            cell = row.createCell(1); //???????????????
            cell.setCellValue(productFinishs.get(i).getProjectNo());
            cell.setCellStyle(boderStyle);

            // System.out.print(productFinishs.get(i).getProjectNo());
            cell = row.createCell(2); //???????????????
            cell.setCellValue(productFinishs.get(i).getPurchaseName());
            //cell.setCellValue(productFinishs.get(i).getProjectName() ==null? "": productFinishs.get(i).getProjectName() + "  ??????"+delayDays+"???");
            cell.setCellStyle(boderStyle);
            cell = row.createCell(3); //???????????????
            cell.setCellValue(productFinishs.get(i).getSellName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(4); //???????????????
            cell.setCellValue(productFinishs.get(i).getCompanyName());
            cell.setCellStyle(boderStyle);
            if (factoryName != null && factoryName.equals(productFinishs.get(i).getCompanyName())) {
                factoryNum++;
            } else {
                if (factoryNum != 0) {
                    CellRangeAddress region = new CellRangeAddress(startCol, startCol + factoryNum, (short) 4, (short) 4);
                    sheet.addMergedRegion(region);
                }
                factoryNum = 0;
                factoryName = productFinishs.get(i).getCompanyName();
                startCol = extend_tl + 2 + i;
            }
            cell = row.createCell(5); //???????????????
            cell.setCellValue(productFinishs.get(i).getProjectName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(6); //???????????????
            if (productFinishs.get(i).getOriginalDeliveryDate() != null) {
                cell.setCellValue(productFinishs.get(i).getOriginalDeliveryDate());
            } else if (productFinishs.get(i).getOriginalSampleScheduledDate() != null) {
                cell.setCellValue(productFinishs.get(i).getOriginalSampleScheduledDate());
            }
            cell.setCellStyle(cellStyle);

            //??????????????????
            cell = row.createCell(7); //???????????????
            cell.setCellStyle(boderStyle);
            cell.setCellValue(productFinishs.get(i).getDelayDay());
            //?????????????????????
            cell = row.createCell(8); //???????????????

            if (productFinishs.get(i).getInterpretationDocument() != null) {
                String name = "http://117.144.21.74:10010/product_img/" + productFinishs.get(i).getProjectNo() + "/" + productFinishs.get(i).getInterpretationDocument();
                HSSFWorkbook workbook = new HSSFWorkbook();
                CreationHelper createHelper = workbook.getCreationHelper();
                cell.setCellValue("????????????");
                HSSFHyperlink link = (HSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_URL);
                link.setAddress(name);
                cell.setHyperlink((HSSFHyperlink) link);
                cell.setCellValue("????????????");
                cell.setCellStyle(boderStyle);
            } else {
                cell.setCellValue("");
                cell.setCellStyle(cellStyle);
            }


            cell = row.createCell(9); //???????????????
            if (productFinishs.get(i).getLateDeliveryDate() != null) {
                cell.setCellValue(productFinishs.get(i).getLateDeliveryDate());
            } else {
                cell.setCellValue("");
            }
            cell.setCellStyle(cellStyle);
            //???????????????????????????????????????????????????15????????????154???
            //????????????????????????15????????????????????????
            int chargeDays = 0;
            if (productFinishs.get(i).getFinishTime().getTime() >= endDate.getTime() && delayDays > 30) {
                chargeDays = DateFormat.calDays(endDate, DateUtil.getPrevMonthDate(endDate));
            } else if (productFinishs.get(i).getFinishTime().getTime() > DateUtil.getPrevMonthDate(endDate).getTime()) {
                chargeDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(), DateUtil.getPrevMonthDate(endDate)) - 1;
            }
            //?????????????????????????????????????????????????????????
            if (chargeDays > delayDays) {
                chargeDays = delayDays;
            }
            try {
                //????????????????????????15???????????????????????????????????????
                if (productFinishs.get(i).getDeliveryDate().getTime() >= DateUtil.getPrevMonthDate(endDate).getTime()) {
                    chargeDays = delayDays;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cell = row.createCell(10); //???????????????
            cell.setCellValue(spendDays);
            cell.setCellStyle(boderStyle);
            cell = row.createCell(11); //???????????????
            cell.setCellValue("??????" + delayDays + "???");
            cell.setCellStyle(boderStyle);


            //????????????????????????
            cell = row.createCell(12); //???????????????
            cell.setCellStyle(lastStyle);
            if (chargeDays != 0) {
                String money = chargeDays + "???*50??? =" + chargeDays * 50 + "???";
                cell.setCellValue(money);
            } else {
                cell.setCellValue(0);
            }
        }


        extend_tl = extend_tl + product_tl + 3;
        row = sheet.createRow(extend_tl);
        cell = row.createCell(0);
        cell.setCellValue("???????????????");
        cell.setCellStyle(style);
        style.setFont(font2);
        for (int i = 0; i < normal_tl; i++) {
            if (i == 0) {
                row = sheet.createRow(extend_tl + 1);
                cell = row.createCell(0); //???????????????
                cell.setCellValue("??????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(3); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(4); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(5); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("???????????????");
                cell = row.createCell(6); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(7); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");
                cell = row.createCell(8); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(9); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(10); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellStyle(style);
                cell.setCellValue("???????????????????????????");
                cell = row.createCell(11); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(12); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????" + maxCurrentMonthDay + "???????????????");
            }


            int spendDays = 0;   //?????????
            int delayDays = 0;   //????????????
            //?????????
            if (normals.get(i).getDateSampleUploading() != null) {
                spendDays = DateFormat.calDays(new java.util.Date(), normals.get(i).getDateSampleUploading());
            } else if (normals.get(i).getDateSampleUploading() == null && normals.get(i).getActualStartDate() != null) {
                spendDays = DateFormat.calDays(new java.util.Date(), normals.get(i).getActualStartDate()) - 7;
            }
            //??????????????????
            if (normals.get(i).getDeliveryDate() == null && normals.get(i).getSampleScheduledDate() != null) {
                if (normals.get(i).getLateDeliveryDate() != null) {
                    delayDays = DateFormat.calDays(new java.util.Date(), normals.get(i).getLateDeliveryDate());
                } else {
                    delayDays = DateFormat.calDays(new java.util.Date(), normals.get(i).getSampleScheduledDate()) - 7 - normals.get(i).getDelayDay();
                }
            }
            //????????????
            if (normals.get(i).getDeliveryDate() != null) {
                if (normals.get(i).getLateDeliveryDate() != null) {
                    delayDays = DateFormat.calDays(new java.util.Date(), normals.get(i).getLateDeliveryDate());
                } else {
                    delayDays = DateFormat.calDays(new java.util.Date(), normals.get(i).getDeliveryDate()) - 7 - normals.get(i).getDelayDay();
                }
            }


            row = sheet.createRow(extend_tl + i + 2);
            cell = row.createCell(0); //???????????????
            cell.setCellValue(i + 1);
            cell.setCellStyle(boderStyle);
            cell = row.createCell(1); //???????????????
            cell.setCellValue(normals.get(i).getProjectNo());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(2); //???????????????
            cell.setCellValue(normals.get(i).getPurchaseName());
            //cell.setCellValue(normals.get(i).getProjectName()==null?"":normals.get(i).getProjectName() + "  ??????"+delayDays+"???");
            cell.setCellStyle(boderStyle);
            cell = row.createCell(3); //???????????????
            cell.setCellValue(normals.get(i).getSellName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(4); //???????????????
            cell.setCellValue(normals.get(i).getCompanyName());
            cell.setCellStyle(boderStyle);
            if (factoryName != null && factoryName.equals(normals.get(i).getCompanyName())) {
                factoryNum++;
            } else {
                if (factoryNum != 0) {
                    CellRangeAddress region = new CellRangeAddress(startCol, startCol + factoryNum, (short) 4, (short) 4);
                    sheet.addMergedRegion(region);
                }
                factoryNum = 0;
                factoryName = normals.get(i).getCompanyName();
                startCol = extend_tl + 2 + i;
            }
            cell = row.createCell(5); //???????????????
            cell.setCellValue(normals.get(i).getProjectName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(6); //???????????????
            try {
                cell.setCellValue(normals.get(i).getOriginalDeliveryDate() == null ? normals.get(i).getOriginalSampleScheduledDate() : normals.get(i).getOriginalDeliveryDate());
            } catch (Exception e) {
                e.printStackTrace();
            }

            cell.setCellStyle(cellStyle);
            //??????????????????
            cell = row.createCell(7); //???????????????
            cell.setCellStyle(boderStyle);
            cell.setCellValue(normals.get(i).getDelayDay());

            //????????????????????????
            cell = row.createCell(8); //???????????????
            if (normals.get(i).getInterpretationDocument() != null) {
                String name = "http://117.144.21.74:10010/product_img/" + normals.get(i).getProjectNo() + "/" + normals.get(i).getInterpretationDocument();
                HSSFWorkbook workbook = new HSSFWorkbook();
                CreationHelper createHelper = workbook.getCreationHelper();
                cell.setCellValue("????????????");
                HSSFHyperlink link = (HSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_URL);
                link.setAddress(name);
                cell.setHyperlink((HSSFHyperlink) link);
                cell.setCellValue("????????????");
                cell.setCellStyle(boderStyle);


            } else {
                cell.setCellValue("");
                cell.setCellStyle(cellStyle);
            }


            cell = row.createCell(9); //???????????????
            if (normals.get(i).getLateDeliveryDate() != null) {
                cell.setCellValue(normals.get(i).getLateDeliveryDate());
            } else {
                cell.setCellValue("");
            }
            cell.setCellStyle(cellStyle);

            cell = row.createCell(10); //???????????????
            cell.setCellValue(spendDays);
            cell.setCellStyle(boderStyle);
            cell = row.createCell(11); //???????????????
            cell.setCellValue("??????" + delayDays + "???");
            cell.setCellStyle(cellStyle);
            //???????????????????????????????????????????????????15????????????15???
            //????????????????????????15????????????????????????
            int chargeDays = 0;
            chargeDays = DateFormat.calDays(endDate.getTime(), DateUtil.getPrevMonthDate(endDate));
            //????????????????????????15???????????????????????????????????????
            if (normals.get(i).getSampleScheduledDate() != null) {
                //??????7?????????????????????
                Date sampleScheduledDate = DateFormat.addDays(normals.get(i).getSampleScheduledDate(), 7);
                if (sampleScheduledDate.getTime() >= DateUtil.getPrevMonthDate(endDate).getTime()) {
                    //???????????????????????????????????????????????????????????????
                    int calDays = DateFormat.calDays(endDate, sampleScheduledDate);
                    if (calDays > delayDays) {
                        chargeDays = delayDays;
                    } else {
                        if (calDays < 0) {
                            chargeDays = 0;
                        } else {
                            chargeDays = calDays;
                        }
                    }
                }
            }
            //??????????????????????????????????????????
            if (normals.get(i).getDeliveryDate() != null) {
                //??????7?????????????????????
                Date deliveryDate = DateFormat.addDays(normals.get(i).getDeliveryDate(), 7);
                if (deliveryDate.getTime() >= DateUtil.getPrevMonthDate(endDate).getTime()) {
                    //?????????????????????????????????????????????-7?????????????????????
                    int calDays = DateFormat.calDays(endDate, deliveryDate);
                    if (calDays > delayDays) {
                        chargeDays = delayDays;
                    } else {
                        if (calDays < 0) {
                            chargeDays = 0;
                        } else {
                            chargeDays = calDays;
                        }
                    }
                }
            }
            if (chargeDays > delayDays) {
                chargeDays = delayDays;
            }

            //????????????????????????
            cell = row.createCell(12); //???????????????
            cell.setCellStyle(lastStyle);
            if (chargeDays != 0) {
                String money = chargeDays + "???*50??? =" + chargeDays * 50 + "???";
                cell.setCellValue(money);
            } else {
                cell.setCellValue(0);
            }
        }

        extend_tl = extend_tl + normal_tl + 3;
        row = sheet.createRow(extend_tl);
        cell = row.createCell(0);
        cell.setCellValue("????????????");
        cell.setCellStyle(style);
        style.setFont(font2);
        for (int i = 0; i < suspension_tl; i++) {
            if (i == 0) {
                row = sheet.createRow(extend_tl + 1);
                cell = row.createCell(0); //???????????????
                cell.setCellValue("??????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(3); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(4); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(5); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("???????????????");
                cell = row.createCell(6); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(7); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");
                cell = row.createCell(8); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(9); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(10); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellStyle(style);
                cell.setCellValue("???????????????????????????");
                cell = row.createCell(11); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(12); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????" + maxCurrentMonthDay + "???????????????");
            }


            int spendDays = 0;   //?????????
            int delayDays = 0;   //????????????
            //?????????
            if (suspension.get(i).getDateSampleUploading() != null) {
                spendDays = DateFormat.calDays(new java.util.Date(), suspension.get(i).getDateSampleUploading());
            } else if (suspension.get(i).getDateSampleUploading() == null && suspension.get(i).getActualStartDate() != null) {
                spendDays = DateFormat.calDays(new java.util.Date(), suspension.get(i).getActualStartDate()) - 7;
            }
            //??????????????????
            if (suspension.get(i).getDeliveryDate() == null && suspension.get(i).getSampleScheduledDate() != null) {
                delayDays = DateFormat.calDays(new java.util.Date(), suspension.get(i).getSampleScheduledDate()) - 7 - suspension.get(i).getDelayDay();
            }
            //????????????
            if (suspension.get(i).getDeliveryDate() != null) {
                delayDays = DateFormat.calDays(new java.util.Date(), suspension.get(i).getDeliveryDate()) - 7 - suspension.get(i).getDelayDay();
            }

            row = sheet.createRow(extend_tl + i + 2);
            cell = row.createCell(0); //???????????????
            cell.setCellValue(i + 1);
            cell.setCellStyle(boderStyle);
            cell = row.createCell(1); //???????????????
            cell.setCellValue(suspension.get(i).getProjectNo());
            cell.setCellStyle(boderStyle);


            cell = row.createCell(2); //???????????????
            //cell.setCellValue(suspension.get(i).getProjectName()==null?"":suspension.get(i).getProjectName() + "  ??????"+delayDays+"???");
            cell.setCellValue(suspension.get(i).getPurchaseName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(3); //???????????????
            cell.setCellValue(suspension.get(i).getSellName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(4); //???????????????
            cell.setCellValue(suspension.get(i).getCompanyName());
            cell.setCellStyle(boderStyle);
            if (factoryName != null && factoryName.equals(suspension.get(i).getCompanyName())) {
                factoryNum++;
            } else {
                if (factoryNum != 0) {
                    CellRangeAddress region = new CellRangeAddress(startCol, startCol + factoryNum, (short) 4, (short) 4);
                    sheet.addMergedRegion(region);
                }
                factoryNum = 0;
                factoryName = suspension.get(i).getCompanyName();
                startCol = extend_tl + 2 + i;
            }
            cell = row.createCell(5); //???????????????
            cell.setCellValue(suspension.get(i).getProjectName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(6); //???????????????
            Date date = suspension.get(i).getOriginalDeliveryDate();
            Date date1 = suspension.get(i).getOriginalSampleScheduledDate();
            if (date != null || date1 != null) {
                cell.setCellValue(suspension.get(i).getOriginalDeliveryDate() == null ? (suspension.get(i).getOriginalSampleScheduledDate() == null ? null : suspension.get(i).getOriginalSampleScheduledDate()) : suspension.get(i).getOriginalDeliveryDate());
            } else {
                cell.setCellValue("");
            }
            cell.setCellStyle(cellStyle);
            //??????????????????
            cell = row.createCell(7); //???????????????
            cell.setCellStyle(boderStyle);
            cell.setCellValue(suspension.get(i).getDelayDay());

            //????????????????????????
            cell = row.createCell(8); //???????????????
            if (suspension.get(i).getInterpretationDocument() != null) {
                String name = "http://117.144.21.74:10010/product_img/" + suspension.get(i).getProjectNo() + "/" + suspension.get(i).getInterpretationDocument();
                HSSFWorkbook workbook = new HSSFWorkbook();
                CreationHelper createHelper = workbook.getCreationHelper();
                cell.setCellValue("????????????");
                HSSFHyperlink link = (HSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_URL);
                link.setAddress(name);
                cell.setHyperlink((HSSFHyperlink) link);
                cell.setCellValue("????????????");
                cell.setCellStyle(boderStyle);


            } else {
                cell.setCellValue("");
                cell.setCellStyle(cellStyle);
            }


            cell = row.createCell(9); //???????????????
            if (suspension.get(i).getLateDeliveryDate() != null) {
                cell.setCellValue(suspension.get(i).getLateDeliveryDate());
            } else {
                cell.setCellValue("");
            }
            cell.setCellStyle(cellStyle);

            cell = row.createCell(10); //???????????????
            cell.setCellValue(spendDays);
            cell.setCellStyle(boderStyle);
            cell = row.createCell(11); //???????????????
            cell.setCellValue("??????" + delayDays + "???");
            cell.setCellStyle(boderStyle);

            //???????????????????????????????????????????????????15????????????15???
            //????????????????????????15????????????????????????
            int chargeDays = 0;
            chargeDays = DateFormat.calDays(endDate.getTime(), DateUtil.getPrevMonthDate(endDate));
            //????????????????????????15???????????????????????????????????????
            if (suspension.get(i).getSampleScheduledDate() != null) {
                //??????7?????????????????????
                Date sampleScheduledDate = DateFormat.addDays(suspension.get(i).getSampleScheduledDate(), 7);
                if (sampleScheduledDate.getTime() >= DateUtil.getPrevMonthDate(endDate).getTime()) {
                    //???????????????????????????????????????????????????????????????
                    int calDays = DateFormat.calDays(endDate, sampleScheduledDate);
                    if (calDays > delayDays) {
                        chargeDays = delayDays;
                    } else {
                        chargeDays = calDays;
                    }
                }
            }
            //??????????????????????????????????????????
            if (suspension.get(i).getDeliveryDate() != null) {
                //??????7?????????????????????
                Date deliveryDate = DateFormat.addDays(suspension.get(i).getDeliveryDate(), 7);
                if (deliveryDate.getTime() >= DateUtil.getPrevMonthDate(endDate).getTime()) {
                    //?????????????????????????????????????????????-7?????????????????????
                    int calDays = DateFormat.calDays(endDate, deliveryDate);
                    if (calDays > delayDays) {
                        chargeDays = delayDays;
                    } else {
                        chargeDays = calDays;
                    }
                }
            }

            //????????????????????????
            cell = row.createCell(12); //???????????????
            cell.setCellStyle(lastStyle);
            cell.setCellValue(0);


        }

        extend_tl = extend_tl + cancellation_tl + 3;
        row = sheet.createRow(extend_tl);
        cell = row.createCell(0);
        cell.setCellValue("????????????");
        cell.setCellStyle(style);
        style.setFont(font2);
        for (int i = 0; i < cancellation_tl; i++) {
            if (i == 0) {
                row = sheet.createRow(extend_tl + 1);
                cell = row.createCell(0); //???????????????
                cell.setCellValue("??????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(3); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(4); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(5); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("???????????????");
                cell = row.createCell(6); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(7); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");
                cell = row.createCell(8); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(9); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(10); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellStyle(style);
                cell.setCellValue("???????????????????????????");
                cell = row.createCell(11); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(12); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????" + maxCurrentMonthDay + "???????????????");
            }
            int spendDays = 0;   //?????????
            int delayDays = 0;   //????????????
            //?????????
            if (cancellation.get(i).getDateSampleUploading() != null) {
                spendDays = DateFormat.calDays(new java.util.Date(), cancellation.get(i).getDateSampleUploading());
            } else if (cancellation.get(i).getDateSampleUploading() == null && cancellation.get(i).getActualStartDate() != null) {
                spendDays = DateFormat.calDays(new java.util.Date(), cancellation.get(i).getActualStartDate()) - 7;
            }
            //??????????????????
            if (cancellation.get(i).getDeliveryDate() == null && cancellation.get(i).getSampleScheduledDate() != null) {
                if (cancellation.get(i).getLateDeliveryDate() != null) {
                    delayDays = DateFormat.calDays(new java.util.Date(), cancellation.get(i).getLateDeliveryDate());
                } else {
                    delayDays = DateFormat.calDays(new java.util.Date(), cancellation.get(i).getSampleScheduledDate()) - 7 - cancellation.get(i).getDelayDay();
                }
            }
            //????????????
            if (cancellation.get(i).getDeliveryDate() != null) {
                if (cancellation.get(i).getLateDeliveryDate() != null) {
                    delayDays = DateFormat.calDays(new java.util.Date(), cancellation.get(i).getLateDeliveryDate());
                } else {
                    delayDays = DateFormat.calDays(new java.util.Date(), cancellation.get(i).getDeliveryDate()) - 7 - cancellation.get(i).getDelayDay();
                }
            }
            row = sheet.createRow(extend_tl + i + 2);
            cell = row.createCell(0); //???????????????
            cell.setCellValue(i + 1);
            cell.setCellStyle(boderStyle);
            cell = row.createCell(1); //???????????????
            cell.setCellValue(cancellation.get(i).getProjectNo());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(2); //???????????????
            cell.setCellValue(cancellation.get(i).getPurchaseName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(3); //???????????????
            cell.setCellValue(cancellation.get(i).getSellName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(4); //???????????????
            cell.setCellValue(cancellation.get(i).getCompanyName());
            cell.setCellStyle(boderStyle);
            if (factoryName != null && factoryName.equals(cancellation.get(i).getCompanyName())) {
                factoryNum++;
            } else {
                if (factoryNum != 0) {
                    CellRangeAddress region = new CellRangeAddress(startCol, startCol + factoryNum, (short) 4, (short) 4);
                    sheet.addMergedRegion(region);
                }
                factoryNum = 0;
                factoryName = cancellation.get(i).getCompanyName();
                startCol = extend_tl + 2 + i;
            }
            cell = row.createCell(5); //???????????????
            cell.setCellValue(cancellation.get(i).getProjectNo());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(6); //???????????????
            Date date = cancellation.get(i).getOriginalDeliveryDate();
            Date date1 = cancellation.get(i).getOriginalSampleScheduledDate();
            if (date != null || date1 != null) {
                cell.setCellValue(cancellation.get(i).getOriginalDeliveryDate() == null ? (cancellation.get(i).getOriginalSampleScheduledDate() == null ? null : cancellation.get(i).getOriginalSampleScheduledDate()) : cancellation.get(i).getOriginalDeliveryDate());
            } else {
                cell.setCellValue("");
            }
            cell.setCellStyle(cellStyle);
            //??????????????????
            cell = row.createCell(7); //???????????????
            cell.setCellStyle(boderStyle);
            cell.setCellValue(cancellation.get(i).getDelayDay());
            //????????????????????????
            cell = row.createCell(8); //???????????????
            if (cancellation.get(i).getInterpretationDocument() != null) {
                String name = "http://117.144.21.74:10010/product_img/" + cancellation.get(i).getProjectNo() + "/" + cancellation.get(i).getInterpretationDocument();
                HSSFWorkbook workbook = new HSSFWorkbook();
                CreationHelper createHelper = workbook.getCreationHelper();
                cell.setCellValue("????????????");
                HSSFHyperlink link = (HSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_URL);
                link.setAddress(name);
                cell.setHyperlink((HSSFHyperlink) link);
                cell.setCellValue("????????????");
                cell.setCellStyle(boderStyle);
            } else {
                cell.setCellValue("");
                cell.setCellStyle(cellStyle);
            }
            cell = row.createCell(9); //???????????????
            if (cancellation.get(i).getLateDeliveryDate() != null) {
                cell.setCellValue(cancellation.get(i).getLateDeliveryDate());
            } else {
                cell.setCellValue("");
            }
            cell.setCellStyle(cellStyle);
            cell = row.createCell(10); //???????????????
            cell.setCellValue(spendDays);
            cell.setCellStyle(boderStyle);
            cell = row.createCell(11); //???????????????
            cell.setCellValue("??????" + delayDays + "???");
            cell.setCellStyle(boderStyle);
            //???????????????????????????????????????????????????15????????????15???
            //????????????????????????15????????????????????????
            int chargeDays = 0;
            chargeDays = DateFormat.calDays(endDate.getTime(), DateUtil.getPrevMonthDate(endDate));
            //????????????????????????15???????????????????????????????????????
            if (cancellation.get(i).getSampleScheduledDate() != null) {
                //??????7?????????????????????
                Date sampleScheduledDate = DateFormat.addDays(cancellation.get(i).getSampleScheduledDate(), 7);
                if (sampleScheduledDate.getTime() >= DateUtil.getPrevMonthDate(endDate).getTime()) {
                    //???????????????????????????????????????????????????????????????
                    int calDays = DateFormat.calDays(endDate, sampleScheduledDate);
                    if (calDays > delayDays) {
                        chargeDays = delayDays;
                    } else {
                        chargeDays = calDays;
                    }
                }
            }
            //??????????????????????????????????????????
            if (cancellation.get(i).getDeliveryDate() != null) {
                //??????7?????????????????????
                Date deliveryDate = DateFormat.addDays(cancellation.get(i).getDeliveryDate(), 7);
                if (deliveryDate.getTime() >= DateUtil.getPrevMonthDate(endDate).getTime()) {
                    //?????????????????????????????????????????????-7?????????????????????
                    int calDays = DateFormat.calDays(endDate, deliveryDate);
                    if (calDays > delayDays) {
                        chargeDays = delayDays;
                    } else {
                        chargeDays = calDays;
                    }
                }
            }
            //????????????????????????
            cell = row.createCell(12); //???????????????
            cell.setCellStyle(lastStyle);

            cell.setCellValue(0);

        }
        //??????????????????
         /*int i=0;
         while (i<15) {
        	 sheet.autoSizeColumn((short)i);
        	 i++;
		 }*/

        String paths = UploadAndDownloadPathUtil.getFilePath();

        tempPath = new File(paths);
        // deleteFile(tempPath);
        if (!tempPath.exists() || !tempPath.isDirectory()) {
            tempPath.mkdir(); // ???????????????????????????????????????
        }
        FileOutputStream fs = new FileOutputStream(paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls", false);
        wb.write(fs);
        fs.close();

        return paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls";
    }


    /**
     * ?????????????????????????????????
     *
     * @param
     * @throws Exception
     */
    public static String exportMonthProject(HttpServletRequest request, List<Project> productFinishs) throws Exception {
        //??????????????????
        int product_tl = 0;
        if (productFinishs != null && productFinishs.size() > 0) {
            product_tl = productFinishs.size();
        }

        //??????workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        //??????sheet
        HSSFSheet sheet = wb.createSheet("???????????????????????????");

        HSSFFont font = wb.createFont();
        font.setFontName("??????");
        font.setFontHeightInPoints((short) 13);//??????????????????


        HSSFFont font2 = wb.createFont();
        font2.setFontName("??????");
        font2.setFontHeightInPoints((short) 16);//??????????????????
        font2.setColor(HSSFColor.RED.index);


        //?????????row:????????????0???
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font2);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(HSSFColor.BLACK.index);


        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);
        HSSFDataFormat format = wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy/m/d"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(HSSFColor.BLACK.index);

        //??????????????????
        HSSFCellStyle boderStyle = wb.createCellStyle();
        boderStyle.setFont(font);
        boderStyle.setAlignment(HorizontalAlignment.CENTER);
        boderStyle.setBorderBottom(BorderStyle.THIN);
        boderStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderLeft(BorderStyle.THIN);
        boderStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderRight(BorderStyle.THIN);
        boderStyle.setRightBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderTop(BorderStyle.THIN);
        boderStyle.setTopBorderColor(HSSFColor.BLACK.index);


        //????????????????????????
        HSSFCellStyle lastStyle = wb.createCellStyle();
        lastStyle.setAlignment(HorizontalAlignment.CENTER);
        lastStyle.setBorderBottom(BorderStyle.THIN);
        lastStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderLeft(BorderStyle.THIN);
        lastStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderRight(BorderStyle.THIN);
        lastStyle.setRightBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderTop(BorderStyle.THIN);
        lastStyle.setTopBorderColor(HSSFColor.BLACK.index);
        lastStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// ???????????????
        lastStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //??????????????????
        row = sheet.createRow(0);
        HSSFCell cell = null;

        for (int i = 0; i < product_tl; i++) {
            if (i == 0) {
                row = sheet.createRow(0);
                cell = row.createCell(0); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(style);
                cell = row.createCell(1); //???????????????
                cell.setCellValue("??????");
                cell.setCellStyle(style);
                cell = row.createCell(2); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(style);
                cell = row.createCell(3); //???????????????
                cell.setCellValue("??????");
                cell.setCellStyle(style);
                cell = row.createCell(4); //???????????????
                cell.setCellValue("??????1");
                cell.setCellStyle(style);
                cell = row.createCell(5); //???????????????
                cell.setCellValue("??????");
                cell.setCellStyle(style);
                cell = row.createCell(6); //???????????????
                cell.setCellStyle(style);
                cell.setCellValue("??????2");
                cell = row.createCell(7); //???????????????
                cell.setCellStyle(style);
                cell.setCellValue("??????");
                cell = row.createCell(8); //???????????????
                cell.setCellStyle(style);
                cell.setCellValue("??????3");
                cell = row.createCell(9); //???????????????
                cell.setCellStyle(style);
                cell.setCellValue("??????");
                cell = row.createCell(10); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(style);
                cell = row.createCell(11); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(style);
                cell = row.createCell(12); //???????????????
                cell.setCellValue("????????????");
                cell.setCellStyle(style);
                cell = row.createCell(13); //???????????????
                cell.setCellValue("????????????");
                cell.setCellStyle(style);
                cell = row.createCell(14); //???????????????
                cell.setCellValue("????????????");
                cell.setCellStyle(style);
                cell = row.createCell(15); //???????????????
                cell.setCellValue("????????????");
                cell.setCellStyle(style);

            }

            row = sheet.createRow(i + 1);
            cell = row.createCell(0); //???????????????
            cell.setCellValue(productFinishs.get(i).getPurchaseName() == null ? "" : productFinishs.get(i).getPurchaseName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(1); //???????????????
            cell.setCellValue("");
            cell.setCellStyle(lastStyle);
            cell = row.createCell(2); //???????????????
            cell.setCellValue(productFinishs.get(i).getSellName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(3); //???????????????
            cell.setCellValue("");
            cell.setCellStyle(lastStyle);
            cell = row.createCell(4); //???????????????
            cell.setCellValue(productFinishs.get(i).getZhijian1() == null ? "" : productFinishs.get(i).getZhijian1());
            cell.setCellStyle(cellStyle);
            cell = row.createCell(5); //???????????????
            cell.setCellValue("");
            cell.setCellStyle(lastStyle);
            cell = row.createCell(6); //???????????????
            cell.setCellValue(productFinishs.get(i).getZhijian2() == null ? "" : productFinishs.get(i).getZhijian2());
            cell.setCellStyle(cellStyle);
            cell = row.createCell(7); //???????????????
            cell.setCellValue("");
            cell.setCellStyle(lastStyle);
            cell = row.createCell(8); //???????????????
            cell.setCellValue(productFinishs.get(i).getZhijian3() == null ? "" : productFinishs.get(i).getZhijian3());
            cell.setCellStyle(cellStyle);
            cell = row.createCell(9); //???????????????
            cell.setCellValue("");
            cell.setCellStyle(lastStyle);
            cell = row.createCell(10); //???????????????
            cell.setCellValue(productFinishs.get(i).getProjectNo());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(11); //???????????????
            cell.setCellValue(productFinishs.get(i).getCompanyName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(12); //???????????????
            if (productFinishs.get(i).getSampleFinishTime() != null) {
                cell.setCellValue(productFinishs.get(i).getSampleFinishTime());
            }
            if (productFinishs.get(i).getFinishTime() != null) {
                cell.setCellValue(productFinishs.get(i).getFinishTime());
            }
            cell.setCellStyle(cellStyle);
            cell = row.createCell(13); //???????????????
            cell.setCellValue(OrderStatusEnum.getEnum(productFinishs.get(i).getProjectStatus()).getValue());
            cell.setCellStyle(boderStyle);

            cell.setCellStyle(cellStyle);
            cell = row.createCell(14); //???????????????
            //??????????????????
            String delay = "";
            int delayDays = 0;
            if (productFinishs.get(i).getSampleFinishTime() != null && productFinishs.get(i).getProjectStatus() == OrderStatusEnum.SAMPLE_ORDER.getCode()) {
                if (productFinishs.get(i).getSampleFinishTime().getTime() > productFinishs.get(i).getSampleScheduledDate().getTime() + 7 * 24 * 60 * 60 * 1000) {
                    delay = "?????????";
                    delayDays = DateFormat.calDays(productFinishs.get(i).getSampleFinishTime(), productFinishs.get(i).getSampleScheduledDate()) - 7;
                } else {
                    delay = "?????????";
                }
            }
            if (productFinishs.get(i).getFinishTime() != null && productFinishs.get(i).getDeliveryDate() != null && productFinishs.get(i).getProjectStatus() == OrderStatusEnum.COMPLETE_ORDER.getCode()) {
                if (productFinishs.get(i).getFinishTime().getTime() > productFinishs.get(i).getDeliveryDate().getTime() + 7 * 24 * 60 * 60 * 1000) {
                    delay = "?????????";
                    delayDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getDeliveryDate()) - 7;
                } else {
                    delay = "?????????";
                }
            }

            if ("?????????".equals(delay)) {
                cell.setCellValue(delay);
                cell.setCellStyle(style);
                cell = row.createCell(15); //???????????????
                cell.setCellValue(delayDays);
                cell.setCellStyle(style);
            } else {
                cell.setCellValue(delay);
                cell.setCellStyle(boderStyle);
                cell = row.createCell(15); //???????????????
                cell.setCellValue(delayDays);
                cell.setCellStyle(boderStyle);
            }


        }

        //??????????????????
         /*int i=0;
         while (i<16) {
        	 sheet.autoSizeColumn((short)i);
        	 i++;
		 }*/

        String paths = UploadAndDownloadPathUtil.getFilePath();

        tempPath = new File(paths);
        // deleteFile(tempPath);
        if (!tempPath.exists() || !tempPath.isDirectory()) {
            tempPath.mkdir(); // ???????????????????????????????????????
        }
        FileOutputStream fs = new FileOutputStream("????????????????????????????????????.xls", false);
        wb.write(fs);
        fs.close();

        return "????????????????????????????????????.xls";
    }


    public static String printExcel1(HttpServletRequest request,
                                     List<Project> sampleFinishes, List<Project> productFinishs) throws Exception {
        //??????????????????
        int sample_tl = 0;
        if (sampleFinishes != null && sampleFinishes.size() > 0) {
            sample_tl = sampleFinishes.size();
        }
        //??????????????????
        int product_tl = 0;
        if (productFinishs != null && productFinishs.size() > 0) {
            product_tl = productFinishs.size();
        }


        //??????workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        //??????sheet
        HSSFSheet sheet = wb.createSheet("??????????????????????????????");

        HSSFFont font = wb.createFont();
        font.setFontName("??????");
        font.setFontHeightInPoints((short) 16);//??????????????????


        HSSFFont font2 = wb.createFont();
        font2.setFontName("??????");
        font2.setFontHeightInPoints((short) 16);//??????????????????
        font2.setColor(HSSFColor.RED.index);


        //?????????row:????????????0???
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy/m/d"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(HSSFColor.BLACK.index);

        //??????????????????
        HSSFCellStyle boderStyle = wb.createCellStyle();
        boderStyle.setAlignment(HorizontalAlignment.CENTER);
        boderStyle.setBorderBottom(BorderStyle.THIN);
        boderStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderLeft(BorderStyle.THIN);
        boderStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderRight(BorderStyle.THIN);
        boderStyle.setRightBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderTop(BorderStyle.THIN);
        boderStyle.setTopBorderColor(HSSFColor.BLACK.index);


        //????????????????????????
        HSSFCellStyle lastStyle = wb.createCellStyle();
        lastStyle.setAlignment(HorizontalAlignment.CENTER);
        lastStyle.setBorderBottom(BorderStyle.THIN);
        lastStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderLeft(BorderStyle.THIN);
        lastStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderRight(BorderStyle.THIN);
        lastStyle.setRightBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderTop(BorderStyle.THIN);
        lastStyle.setTopBorderColor(HSSFColor.BLACK.index);
        lastStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// ???????????????
        lastStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //???????????????
        HSSFCell cell = row.createCell(0); //??????????????????
        cell.setCellValue("????????????");
        cell.setCellStyle(style);
        style.setFont(font2);


        //??????????????????
        int factoryNum = 0;
        int startCol = 0;
        String factoryName = "";
        Date endDate = null;
        //????????????????????????????????????15?????????????????????????????????15??????15?????????15??????????????????????????????15???
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        if (cal.get(Calendar.DAY_OF_MONTH) >= 15) {
            String d = year + "-" + month + "-15";
            endDate = DateUtil.StrToDate(d);
        } else {
            //??????15?????????
            String d = year + "-" + month + "-15";
            //???????????????15?????????
            endDate = DateUtil.getPrevMonthDate(DateUtil.StrToDate(d));
        }
        DecimalFormat df = new DecimalFormat("??###,##0.00");

        for (int i = 0; i < sample_tl; i++) {
            if (i == 0) {
                row = sheet.createRow(1);
                cell = row.createCell(0); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????????????????");
                cell = row.createCell(3); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????????????????");
                cell = row.createCell(4); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(5); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(6); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");

                cell = row.createCell(7); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");
                cell = row.createCell(8); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");
                cell = row.createCell(9); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("???????????????");
                cell = row.createCell(10); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????(???)");
                cell.setCellStyle(style);
                cell = row.createCell(11); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");


            }

            int spendDays = 0; //?????????
            int delayDays = 0; //????????????

            //?????????
            if (sampleFinishes.get(i).getSampleFinishTime() != null && sampleFinishes.get(i).getDateSampleUploading() != null) {
                spendDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(), sampleFinishes.get(i).getDateSampleUploading());
            } else if (sampleFinishes.get(i).getDateSampleUploading() == null && sampleFinishes.get(i).getActualStartDate() != null) {
                spendDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(), sampleFinishes.get(i).getActualStartDate()) - 7;
            }

            //????????????
            if (sampleFinishes.get(i).getSampleFinishTime() != null && sampleFinishes.get(i).getSampleScheduledDate() != null) {
                delayDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(), sampleFinishes.get(i).getSampleScheduledDate()) - 7 - sampleFinishes.get(i).getDelayDay();
            }


            row = sheet.createRow(i + 2);
            cell = row.createCell(0); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getPurchaseName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(1); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getCompanyName());
            if (factoryName != null && factoryName.equals(sampleFinishes.get(i).getCompanyName())) {
                factoryNum++;

            } else {
                if (factoryNum != 0) {
                    CellRangeAddress region = new CellRangeAddress(startCol, startCol + factoryNum, (short) 1, (short) 1);
                    sheet.addMergedRegion(region);
                }
                factoryNum = 0;
                factoryName = sampleFinishes.get(i).getCompanyName();
                startCol = 2 + i;
            }
            cell.setCellStyle(boderStyle);
            cell = row.createCell(2); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getProjectName() == null ? "" : sampleFinishes.get(i).getProjectName() + "  ??????" + delayDays + "???");
            cell.setCellStyle(boderStyle);
            cell = row.createCell(3); //???????????????
            cell.setCellValue("");
            cell.setCellStyle(boderStyle);
            cell = row.createCell(4); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getProjectNo());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(5); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getSellName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(6); //???????????????
            cell.setCellStyle(cellStyle);
            if (sampleFinishes.get(i).getOriginalSampleScheduledDate() != null) {
                cell.setCellValue(sampleFinishes.get(i).getOriginalSampleScheduledDate());
            }

            cell = row.createCell(7); //???????????????
            cell.setCellStyle(boderStyle);
            cell.setCellValue(sampleFinishes.get(i).getDelayDay());

            cell = row.createCell(8); //???????????????
            cell.setCellStyle(cellStyle);
            cell.setCellValue(sampleFinishes.get(i).getSampleFinishTime());

            cell = row.createCell(9); //???????????????
            if (sampleFinishes.get(i).getUrgentDeliveryDate() != null) {
                cell.setCellValue(sampleFinishes.get(i).getUrgentDeliveryDate());
            }
            cell.setCellStyle(cellStyle);

            //?????????
            cell = row.createCell(10); //???????????????
            cell.setCellValue(spendDays);
            cell.setCellStyle(boderStyle);

            //????????????
            cell = row.createCell(11); //???????????????
            cell.setCellStyle(cellStyle);
            cell.setCellValue(endDate);

        }


        int extend_tl = 0; //??????????????????
        extend_tl = sample_tl + 3;
        row = sheet.createRow(extend_tl);
        cell = row.createCell(0);
        cell.setCellValue("????????????");
        cell.setCellStyle(style);
        style.setFont(font2);
        for (int i = 0; i < product_tl; i++) {
            if (i == 0) {
                row = sheet.createRow(extend_tl + 1);
                cell = row.createCell(0); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2); //???????????????
                cell.setCellValue("?????????????????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(3); //???????????????
                cell.setCellValue("?????????????????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(4); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(5); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(6); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(7); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");

                cell = row.createCell(8); //???????????????
                cell.setCellValue("??????????????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(9); //???????????????
                cell.setCellValue("???????????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(10); //???????????????
                cell.setCellValue("?????????(???)");
                cell.setCellStyle(style);
                cell.setCellStyle(boderStyle);
                cell = row.createCell(11); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");

            }


            int spendDays = 0;   //?????????
            int delayDays = 0;   //????????????
            //?????????
            if (productFinishs.get(i).getFinishTime() != null && productFinishs.get(i).getDateSampleUploading() != null) {
                spendDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getDateSampleUploading());
            } else if (productFinishs.get(i).getDateSampleUploading() == null && productFinishs.get(i).getActualStartDate() != null) {
                spendDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getActualStartDate()) - 7;
            }

            //????????????
            if (productFinishs.get(i).getFinishTime() != null && productFinishs.get(i).getDeliveryDate() != null) {
                delayDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getDeliveryDate()) - 7 - productFinishs.get(i).getDelayDay();
            }
//       	 if(productFinishs.get(i).getUrgentDeliveryDate() != null){
//       		 delayDays =  DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getUrgentDeliveryDate());
//       	 }
            row = sheet.createRow(extend_tl + i + 2);
            cell = row.createCell(0); //???????????????
            cell.setCellValue(productFinishs.get(i).getPurchaseName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(1); //???????????????
            cell.setCellValue(productFinishs.get(i).getCompanyName());
            cell.setCellStyle(boderStyle);
            if (factoryName != null && factoryName.equals(productFinishs.get(i).getCompanyName())) {
                factoryNum++;
            } else {
                if (factoryNum != 0) {
                    CellRangeAddress region = new CellRangeAddress(startCol, startCol + factoryNum, (short) 1, (short) 1);
                    sheet.addMergedRegion(region);
                }
                factoryNum = 0;
                factoryName = productFinishs.get(i).getCompanyName();
                startCol = extend_tl + 2 + i;
            }
            // System.out.print(productFinishs.get(i).getProjectNo());
            cell = row.createCell(2); //???????????????
            cell.setCellValue(productFinishs.get(i).getProjectName() == null ? "" : productFinishs.get(i).getProjectName() + "  ??????" + delayDays + "???");
            cell.setCellStyle(boderStyle);
            cell = row.createCell(3); //???????????????
            cell.setCellValue("");
            cell.setCellStyle(boderStyle);
            cell = row.createCell(4); //???????????????
            cell.setCellValue(productFinishs.get(i).getProjectNo());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(5); //???????????????
            cell.setCellValue(productFinishs.get(i).getSellName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(6); //???????????????
            if (productFinishs.get(i).getOriginalDeliveryDate() != null) {
                cell.setCellValue(productFinishs.get(i).getOriginalDeliveryDate());
            } else if (productFinishs.get(i).getOriginalSampleScheduledDate() != null) {
                cell.setCellValue(productFinishs.get(i).getOriginalSampleScheduledDate());
            }
            cell.setCellStyle(cellStyle);

            cell = row.createCell(7); //???????????????
            cell.setCellStyle(boderStyle);
            cell.setCellValue(productFinishs.get(i).getDelayDay());


            cell = row.createCell(8); //???????????????
            cell.setCellValue(productFinishs.get(i).getFinishTime());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(9); //???????????????
            if (productFinishs.get(i).getUrgentDeliveryDate() != null) {
                cell.setCellValue(productFinishs.get(i).getUrgentDeliveryDate());
            }
            cell.setCellStyle(cellStyle);
            cell = row.createCell(10); //???????????????
            cell.setCellValue(spendDays);
            cell.setCellStyle(boderStyle);


            //????????????
            cell = row.createCell(11); //???????????????
            cell.setCellStyle(cellStyle);
            cell.setCellValue(endDate);


        }


        //??????????????????
        /*int i=0;
        while (i<12) {
       	 sheet.autoSizeColumn((short)i);
       	 i++;
		 }*/

        String paths = UploadAndDownloadPathUtil.getFilePath();

        tempPath = new File(paths);
        // deleteFile(tempPath);
        if (!tempPath.exists() || !tempPath.isDirectory()) {
            tempPath.mkdir(); // ???????????????????????????????????????
        }
        FileOutputStream fs = new FileOutputStream(paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls", false);
        wb.write(fs);
        fs.close();

        return paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls";
    }


    public static String printExcel2(HttpServletRequest request,
                                     List<User> sampleFinishes) throws Exception {
        //??????????????????
        int sample_tl = 0;
        if (sampleFinishes != null && sampleFinishes.size() > 0) {
            sample_tl = sampleFinishes.size();
        }
        //??????workbook
        HSSFWorkbook wb = new HSSFWorkbook();
        //??????sheet
        HSSFSheet sheet = wb.createSheet("????????????????????????");
        HSSFFont font = wb.createFont();
        font.setFontName("??????");
        font.setFontHeightInPoints((short) 16);//??????????????????
        HSSFFont font2 = wb.createFont();
        font2.setFontName("??????");
        font2.setFontHeightInPoints((short) 16);//??????????????????
        font2.setColor(HSSFColor.RED.index);
        //?????????row:????????????0???
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy/m/d"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(HSSFColor.BLACK.index);

        //??????????????????
        HSSFCellStyle boderStyle = wb.createCellStyle();
        boderStyle.setAlignment(HorizontalAlignment.CENTER);
        boderStyle.setBorderBottom(BorderStyle.THIN);
        boderStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderLeft(BorderStyle.THIN);
        boderStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderRight(BorderStyle.THIN);
        boderStyle.setRightBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderTop(BorderStyle.THIN);
        boderStyle.setTopBorderColor(HSSFColor.BLACK.index);


        //????????????????????????
        HSSFCellStyle lastStyle = wb.createCellStyle();
        lastStyle.setAlignment(HorizontalAlignment.CENTER);
        lastStyle.setBorderBottom(BorderStyle.THIN);
        lastStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderLeft(BorderStyle.THIN);
        lastStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderRight(BorderStyle.THIN);
        lastStyle.setRightBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderTop(BorderStyle.THIN);
        lastStyle.setTopBorderColor(HSSFColor.BLACK.index);
        lastStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// ???????????????
        lastStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //???????????????
        HSSFCell cell = row.createCell(0); //??????????????????
        cell.setCellValue("??????????????????");
        cell.setCellStyle(style);
        style.setFont(font2);


        //??????????????????
        int factoryNum = 0;
        int startCol = 0;
        String factoryName = "";
        Date endDate = null;
        //????????????????????????????????????15?????????????????????????????????15??????15?????????15??????????????????????????????15???
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        if (cal.get(Calendar.DAY_OF_MONTH) >= 15) {
            String d = year + "-" + month + "-15";
            endDate = DateUtil.StrToDate(d);
        } else {
            //??????15?????????
            String d = year + "-" + month + "-15";
            //???????????????15?????????
            endDate = DateUtil.getPrevMonthDate(DateUtil.StrToDate(d));
        }
        DecimalFormat df = new DecimalFormat("??###,##0.00");
        String userName1 = "";
        for (int i = 0; i < sample_tl; i++) {
            if (i == 0) {
                row = sheet.createRow(1);
                cell = row.createCell(0); //???????????????
                cell.setCellValue("???????????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1); //???????????????
                cell.setCellValue("????????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????(?????????)");
                cell = row.createCell(3); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");
                cell = row.createCell(4); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????????????????");


                cell = row.createCell(5); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");


            }

            int spendDays = 0; //?????????
            int delayDays = 0; //????????????
            row = sheet.createRow(i + 2);
            String userName = sampleFinishes.get(i).getUserName();
            if (!userName1.equalsIgnoreCase(userName)) {
                userName1 = userName;
                cell = row.createCell(0); //???????????????
                cell.setCellValue(sampleFinishes.get(i).getUserName());
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1); //???????????????
                cell.setCellValue(sampleFinishes.get(i).getProjectNo());
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2); //???????????????
                cell.setCellValue(sampleFinishes.get(i).getAmount());
                cell.setCellStyle(boderStyle);

                cell = row.createCell(3); //???????????????
                cell.setCellValue(sampleFinishes.get(i).getInspectionTimelinessNumber());
                cell.setCellStyle(boderStyle);
                cell = row.createCell(4); //???????????????
                String herf = "/complaint/queryComplaint?id=" + sampleFinishes.get(i).getId();
                HSSFWorkbook workbook = new HSSFWorkbook();
                CreationHelper createHelper = workbook.getCreationHelper();
                cell.setCellValue(sampleFinishes.get(i).getId());
                HSSFHyperlink link = (HSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_URL);
                link.setAddress(herf);
                cell.setHyperlink((HSSFHyperlink) link);
                cell.setCellValue(sampleFinishes.get(i).getId());
                cell.setCellStyle(boderStyle);
                cell = row.createCell(5); //???????????????
                cell.setCellValue(sampleFinishes.get(i).getZhijian1() + ";" + sampleFinishes.get(i).getZhijian2() + ";" + sampleFinishes.get(i).getZhijian3());
                cell.setCellStyle(boderStyle);
            } else if (userName1.equals(userName)) {
                cell = row.createCell(0); //???????????????
                cell.setCellValue("");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1); //???????????????
                cell.setCellValue("");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2); //???????????????
                cell.setCellValue("");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(3); //???????????????
                cell.setCellValue("");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(4); //???????????????
                String herf = "/complaint/queryComplaint?id=" + sampleFinishes.get(i).getId();
                HSSFWorkbook workbook = new HSSFWorkbook();
                CreationHelper createHelper = workbook.getCreationHelper();
                cell.setCellValue(sampleFinishes.get(i).getId());
                HSSFHyperlink link = (HSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_URL);
                link.setAddress(herf);
                cell.setHyperlink((HSSFHyperlink) link);
                cell.setCellValue(sampleFinishes.get(i).getId());
                cell.setCellStyle(boderStyle);

                cell = row.createCell(5); //???????????????
                cell.setCellValue(sampleFinishes.get(i).getZhijian1() + ";" + sampleFinishes.get(i).getZhijian2() + ";" + sampleFinishes.get(i).getZhijian3());
                cell.setCellStyle(boderStyle);
            }
        }


        //??????????????????
        /*int i=0;
        while (i<16) {
       	 sheet.autoSizeColumn((short)i);
       	 i++;
		 }*/

        String paths = UploadAndDownloadPathUtil.getFilePath();

        tempPath = new File(paths);
        // deleteFile(tempPath);
        if (!tempPath.exists() || !tempPath.isDirectory()) {
            tempPath.mkdir(); // ???????????????????????????????????????
        }
        FileOutputStream fs = new FileOutputStream(paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls", false);
        wb.write(fs);
        fs.close();

        return paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls";
    }

    public static String printExcel1(HttpServletRequest request, List<Project> sampleFinishes) throws Exception {
        //??????????????????
        int sample_tl = 0;
        if (sampleFinishes != null && sampleFinishes.size() > 0) {
            sample_tl = sampleFinishes.size();
        }


        //??????workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        //??????sheet
        HSSFSheet sheet = wb.createSheet("????????????????????????");

        HSSFFont font = wb.createFont();
        font.setFontName("??????");
        font.setFontHeightInPoints((short) 16);//??????????????????


        HSSFFont font2 = wb.createFont();
        font2.setFontName("??????");
        font2.setFontHeightInPoints((short) 16);//??????????????????
        font2.setColor(HSSFColor.RED.index);


        //?????????row:????????????0???
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy/m/d"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(HSSFColor.BLACK.index);

        //??????????????????
        HSSFCellStyle boderStyle = wb.createCellStyle();
        boderStyle.setAlignment(HorizontalAlignment.CENTER);
        boderStyle.setBorderBottom(BorderStyle.THIN);
        boderStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderLeft(BorderStyle.THIN);
        boderStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderRight(BorderStyle.THIN);
        boderStyle.setRightBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderTop(BorderStyle.THIN);
        boderStyle.setTopBorderColor(HSSFColor.BLACK.index);


        //????????????????????????
        HSSFCellStyle lastStyle = wb.createCellStyle();
        lastStyle.setAlignment(HorizontalAlignment.CENTER);
        lastStyle.setBorderBottom(BorderStyle.THIN);
        lastStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderLeft(BorderStyle.THIN);
        lastStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderRight(BorderStyle.THIN);
        lastStyle.setRightBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderTop(BorderStyle.THIN);
        lastStyle.setTopBorderColor(HSSFColor.BLACK.index);
        lastStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// ???????????????
        lastStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //???????????????
        HSSFCell cell = row.createCell(0); //??????????????????
        cell.setCellValue("A/B???????????????????????????");
        cell.setCellStyle(style);
        style.setFont(font2);


        DecimalFormat df = new DecimalFormat("##.##");

        for (int i = 0; i < sample_tl; i++) {
            if (i == 0) {
                row = sheet.createRow(1);
                cell = row.createCell(0); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2); //???????????????
                cell.setCellValue("????????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(3); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????");
                cell = row.createCell(4); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????");
                cell = row.createCell(5); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");
                cell = row.createCell(6); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(7); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(8); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
            }

            int spendDays = 0; //?????????
            int delayDays = 0; //????????????


            row = sheet.createRow(i + 2);
            cell = row.createCell(0); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getProjectNo());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(1); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getProjectName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(2); //???????????????
            int num = sampleFinishes.get(i).getPlantAnalysis();
            String PlantAnalysis = "";
            if (num == 1) {
                PlantAnalysis = "A";
            } else if (num == 2) {
                PlantAnalysis = "B";
            } else if (num == 3) {
                PlantAnalysis = "C";
            } else {
                PlantAnalysis = "";
            }
            cell.setCellValue(PlantAnalysis);
            cell.setCellStyle(boderStyle);
            cell = row.createCell(3); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getSellName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(4); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getPurchaseName());
            cell.setCellStyle(boderStyle);
            cell = row.createCell(5); //???????????????

            cell.setCellValue(sampleFinishes.get(i).getActualStartDate());
            cell.setCellStyle(cellStyle);
            cell = row.createCell(6); //???????????????
            cell.setCellStyle(cellStyle);
            Date date = sampleFinishes.get(i).getMoneyDate();
            if (date != null) {
                cell.setCellValue(date);
            } else {
                cell.setCellValue("");
            }
            //?????????????????????
            cell = row.createCell(7); //???????????????
            cell.setCellStyle(cellStyle);
            cell.setCellValue(sampleFinishes.get(i).getOriginalSampleScheduledDate());
            int num1 = sampleFinishes.get(i).getProjectStatus();
            String ProjectStatus = "";
            if (num1 == 1) {
                ProjectStatus = "???????????????";
            } else if (num1 == 2) {
                ProjectStatus = "?????????????????????";
            }
            cell = row.createCell(8); //???????????????
            cell.setCellStyle(boderStyle);
            cell.setCellValue(ProjectStatus);
        }


        //??????????????????
        /*int i=0;
        while (i<16) {
       	 sheet.autoSizeColumn((short)i);
       	 i++;
		 }*/

        String paths = UploadAndDownloadPathUtil.getFilePath();

        tempPath = new File(paths);
        // deleteFile(tempPath);
        if (!tempPath.exists() || !tempPath.isDirectory()) {
            tempPath.mkdir(); // ???????????????????????????????????????
        }
        FileOutputStream fs = new FileOutputStream(paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls", false);
        wb.write(fs);
        fs.close();

        return paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls";
    }

    /**
     * ????????????????????????
     *
     * @param request
     * @param sampleFinishes
     * @return
     * @throws Exception
     */
    public static String printProjectExportProgress(HttpServletRequest request, List<ProjectERP> sampleFinishes) throws Exception {
        //??????????????????
        int sample_tl = 0;
        if (sampleFinishes != null && sampleFinishes.size() > 0) {
            sample_tl = sampleFinishes.size();
        }


        //??????workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        //??????sheet
        HSSFSheet sheet = wb.createSheet("??????????????????????????????");

        HSSFFont font = wb.createFont();
        font.setFontName("??????");
        font.setFontHeightInPoints((short) 16);//??????????????????


        HSSFFont font2 = wb.createFont();
        font2.setFontName("??????");
        font2.setFontHeightInPoints((short) 16);//??????????????????
        font2.setColor(HSSFColor.RED.index);


        //?????????row:????????????0???
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy/m/d"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(HSSFColor.BLACK.index);

        //??????????????????
        HSSFCellStyle boderStyle = wb.createCellStyle();
        boderStyle.setAlignment(HorizontalAlignment.CENTER);
        boderStyle.setBorderBottom(BorderStyle.THIN);
        boderStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderLeft(BorderStyle.THIN);
        boderStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderRight(BorderStyle.THIN);
        boderStyle.setRightBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderTop(BorderStyle.THIN);
        boderStyle.setTopBorderColor(HSSFColor.BLACK.index);


        //????????????????????????
        HSSFCellStyle lastStyle = wb.createCellStyle();
        lastStyle.setAlignment(HorizontalAlignment.CENTER);
        lastStyle.setBorderBottom(BorderStyle.THIN);
        lastStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderLeft(BorderStyle.THIN);
        lastStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderRight(BorderStyle.THIN);
        lastStyle.setRightBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderTop(BorderStyle.THIN);
        lastStyle.setTopBorderColor(HSSFColor.BLACK.index);
        lastStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// ???????????????
        lastStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //???????????????
        HSSFCell cell = row.createCell(0); //??????????????????
        cell.setCellValue("??????????????????????????????");
        cell.setCellStyle(style);
        style.setFont(font2);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("##.##");
        int num1 = 0;
        for (int i = 0; i < sample_tl; i++) {
            if (i == 0) {
                row = sheet.createRow(1);
                cell = row.createCell(0); //???????????????
                cell.setCellValue("??????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);


                cell = row.createCell(3); //???????????????
                cell.setCellValue("????????????");
                cell.setCellStyle(boderStyle);

                cell = row.createCell(4); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");

                cell = row.createCell(5); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????????????????????????????");

                cell = row.createCell(6); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(7); //???????????????
                cell.setCellValue("??????????????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(8); //???????????????
                cell.setCellValue("????????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(9); //???????????????
                cell.setCellValue("??????");
                cell.setCellStyle(boderStyle);

                cell = row.createCell(10); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????");

                cell = row.createCell(11); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????");
                cell = row.createCell(12); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(13); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(14); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
            }

            int spendDays = 0; //?????????
            int delayDays = 0; //????????????


            num1++;
            row = sheet.createRow(i + 2);
            cell = row.createCell(0); //???????????????
            cell.setCellValue(num1);
            cell.setCellStyle(boderStyle);

            cell = row.createCell(1); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getProjectNo());
            cell.setCellStyle(boderStyle);

            cell = row.createCell(2); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getBargainNo());
            cell.setCellStyle(boderStyle);

            cell = row.createCell(3); //???????????????
            int num = sampleFinishes.get(i).getPlantAnalysis();
            String PlantAnalysis = "";
            if (num == 1) {
                PlantAnalysis = "A";
            } else if (num == 2) {
                PlantAnalysis = "B";
            } else if (num == 3) {
                PlantAnalysis = "C";
            } else {
                PlantAnalysis = "";
            }
            cell.setCellValue(PlantAnalysis);
            cell.setCellStyle(boderStyle);

            cell = row.createCell(4); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getProjectNameC());
            cell.setCellStyle(boderStyle);

            cell = row.createCell(5); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getInputDate());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(6); //???????????????
            cell.setCellValue(sampleFinishes.get(i).getGeldObject());
            cell.setCellStyle(cellStyle);


            cell = row.createCell(7); //???????????????
            cell.setCellStyle(cellStyle);
            cell.setCellValue(sampleFinishes.get(i).getFriMoney());
            //?????????????????????
            cell = row.createCell(8); //???????????????
            cell.setCellStyle(cellStyle);
            cell.setCellValue(sampleFinishes.get(i).getCity());


            cell = row.createCell(9); //???????????????
            cell.setCellStyle(cellStyle);
            if (!"0".equalsIgnoreCase(sampleFinishes.get(i).getProductionPrice())) {
                cell.setCellValue("????????????");
            } else if (!"0".equalsIgnoreCase(sampleFinishes.get(i).getSamplePrice())) {
                cell.setCellValue("????????????");
            } else if (!"0".equalsIgnoreCase(sampleFinishes.get(i).getMoldPrice())) {
                cell.setCellValue("??????" + sampleFinishes.get(i).getQuantity1() + "???");
            }


            cell = row.createCell(10); //???????????????
            cell.setCellStyle(cellStyle);
            if (sampleFinishes.get(i).getMaturePurchase() != null && !"".equalsIgnoreCase(sampleFinishes.get(i).getMaturePurchase())) {
                cell.setCellValue(sampleFinishes.get(i).getMaturePurchase());
            } else if (sampleFinishes.get(i).getOriginalPurchase() != null && !"".equalsIgnoreCase(sampleFinishes.get(i).getOriginalPurchase())) {
                cell.setCellValue(sampleFinishes.get(i).getOriginalPurchase());
            } else if (sampleFinishes.get(i).getMerchandManager2() != null && !"".equalsIgnoreCase(sampleFinishes.get(i).getMerchandManager2())) {
                cell.setCellValue(sampleFinishes.get(i).getMerchandManager2());
            } else {
                cell.setCellValue("????????????");
            }
            cell = row.createCell(11); //???????????????
            cell.setCellStyle(cellStyle);
            if (sampleFinishes.get(i).getMerchandising() != null && !"".equalsIgnoreCase(sampleFinishes.get(i).getMerchandising())) {
                cell.setCellValue(sampleFinishes.get(i).getMerchandising());
            } else if (sampleFinishes.get(i).getMerchandManager1() != null && !"".equalsIgnoreCase(sampleFinishes.get(i).getMerchandManager1())) {
                cell.setCellValue(sampleFinishes.get(i).getMerchandManager1());
            } else {
                cell.setCellValue("????????????");
            }
            cell = row.createCell(12); //???????????????
            cell.setCellStyle(cellStyle);

            if (sampleFinishes.get(i).getCompletionTime() != null) {
                boolean flag = sampleFinishes.get(i).getCompletionTime().getTime() == format1.parse("1900-01-01").getTime();
                if (flag != true) {
                    cell.setCellValue(sampleFinishes.get(i).getCompletionTime());
                } else {
                    cell.setCellValue("");
                }
            }
            cell = row.createCell(13); //???????????????
            cell.setCellStyle(cellStyle);

            if (sampleFinishes.get(i).getDateSample() != null) {
                boolean flag = sampleFinishes.get(i).getDateSample().getTime() == format1.parse("1900-01-01").getTime();
                if (flag != true) {
                    cell.setCellValue(sampleFinishes.get(i).getDateSample());
                } else {
                    cell.setCellValue("");
                }
            }

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE, -7);
            Date m = c.getTime();
            cell = row.createCell(14); //???????????????
            cell.setCellStyle(boderStyle);
            Date today = new Date();    //?????? ???????????????  2018-11-12    Debug???Wed Nov 12 12:08:12 CST 2018
            if (sampleFinishes.get(i).getCompletionTime() != null) {
                boolean flag = sampleFinishes.get(i).getCompletionTime().getTime() == format1.parse("1900-01-01").getTime();
                if (flag != true) {
                    boolean flag1 = sampleFinishes.get(i).getCompletionTime().getTime() >= m.getTime();
                    if (flag1 == true) {
                        cell.setCellValue("???????????????");
                    } else {
                        cell.setCellValue("????????????");
                    }
                } else if (sampleFinishes.get(i).getDateSample() != null) {
                    boolean flag3 = sampleFinishes.get(i).getDateSample().getTime() == format1.parse("1900-01-01").getTime();
                    if (flag3 != true) {
                        boolean flag2 = sampleFinishes.get(i).getDateSample().getTime() >= m.getTime();
                        if (flag2 == true) {
                            cell.setCellValue("???????????????");
                        } else {
                            cell.setCellValue("????????????");
                        }
                    } else {
                        cell.setCellValue("");
                    }
                }
            }


        }


        //??????????????????
		/*int i=0;
		while (i<16) {
			sheet.autoSizeColumn((short)i);
			i++;
		}*/

        String paths = UploadAndDownloadPathUtil.getFilePath();

        tempPath = new File(paths);
        // deleteFile(tempPath);
        if (!tempPath.exists() || !tempPath.isDirectory()) {
            tempPath.mkdir(); // ???????????????????????????????????????
        }
        FileOutputStream fs = new FileOutputStream(paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls", false);
        wb.write(fs);
        fs.close();

        return paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls";
    }


    public static String printOngoingProjects(HttpServletRequest request, List<Project> sampleFinishes)
            throws Exception {
        int sample_tl = 0;
        if ((sampleFinishes != null) && (sampleFinishes.size() > 0)) {
            sample_tl = sampleFinishes.size();
        }
        HSSFWorkbook wb = new HSSFWorkbook();


        HSSFSheet sheet = wb.createSheet("??????????????????????????????");

        HSSFFont font = wb.createFont();
        font.setFontName("??????");
        font.setFontHeightInPoints((short) 16);


        HSSFFont font2 = wb.createFont();
        font2.setFontName("??????");
        font2.setFontHeightInPoints((short) 16);
        font2.setColor((short) 10);


        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy/m/d"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor((short) 8);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor((short) 8);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor((short) 8);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor((short) 8);


        HSSFCellStyle boderStyle = wb.createCellStyle();
        boderStyle.setAlignment(HorizontalAlignment.CENTER);
        boderStyle.setBorderBottom(BorderStyle.THIN);
        boderStyle.setBottomBorderColor((short) 8);
        boderStyle.setBorderLeft(BorderStyle.THIN);
        boderStyle.setLeftBorderColor((short) 8);
        boderStyle.setBorderRight(BorderStyle.THIN);
        boderStyle.setRightBorderColor((short) 8);
        boderStyle.setBorderTop(BorderStyle.THIN);
        boderStyle.setTopBorderColor((short) 8);


        HSSFCellStyle lastStyle = wb.createCellStyle();
        lastStyle.setAlignment(HorizontalAlignment.CENTER);
        lastStyle.setBorderBottom(BorderStyle.THIN);
        lastStyle.setBottomBorderColor((short) 8);
        lastStyle.setBorderLeft(BorderStyle.THIN);
        lastStyle.setLeftBorderColor((short) 8);
        lastStyle.setBorderRight(BorderStyle.THIN);
        lastStyle.setRightBorderColor((short) 8);
        lastStyle.setBorderTop(BorderStyle.THIN);
        lastStyle.setTopBorderColor((short) 8);
        lastStyle.setFillForegroundColor((short) 42);
        lastStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);


        HSSFCell cell = row.createCell(0);
        cell.setCellValue("??????????????????????????????");
        cell.setCellStyle(style);
        style.setFont(font2);


        DecimalFormat df = new DecimalFormat("##.##");
        int num1 = 0;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < sample_tl; i++) {
            if (i == 0) {
                row = sheet.createRow(1);
                cell = row.createCell(0);
                cell.setCellValue("??????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(1);
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(2);
                cell.setCellValue("????????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(3);
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(4);
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????????????????????????????");
                cell = row.createCell(5);
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(6);
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????");
                cell = row.createCell(7);
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????");
                cell = row.createCell(8);
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(9);
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(10);
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(11);
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");
                cell = row.createCell(12);
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????????????? ");
            }
            int spendDays = 0;
            int delayDays = 0;


            num1++;
            row = sheet.createRow(i + 2);
            cell = row.createCell(0);
            cell.setCellValue(num1);
            cell.setCellStyle(boderStyle);
            cell = row.createCell(1);
            cell.setCellValue(((Project) sampleFinishes.get(i)).getProjectNo());

            cell.setCellStyle(boderStyle);
            cell = row.createCell(2);
            int num = ((Project) sampleFinishes.get(i)).getPlantAnalysis().intValue();
            String PlantAnalysis = "";
            if (num == 1) {
                PlantAnalysis = "A";
            } else if (num == 2) {
                PlantAnalysis = "B";
            } else if (num == 3) {
                PlantAnalysis = "C";
            } else {
                PlantAnalysis = "";
            }
            cell.setCellValue(PlantAnalysis);
            cell.setCellStyle(boderStyle);
            cell = row.createCell(3);
            cell.setCellValue(((Project) sampleFinishes.get(i)).getProjectName());

            cell.setCellStyle(boderStyle);
            cell = row.createCell(4);
            if (((Project) sampleFinishes.get(i)).getDateSampleUploading() != null) {
                cell.setCellValue(((Project) sampleFinishes.get(i)).getDateSampleUploading());
            } else {
                cell.setCellValue("");
            }
            cell.setCellStyle(cellStyle);
            cell = row.createCell(5);

            cell.setCellValue(((Project) sampleFinishes.get(i)).getCompanyName());
            cell.setCellStyle(cellStyle);
            cell = row.createCell(6);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(((Project) sampleFinishes.get(i)).getSellName());

            cell = row.createCell(7);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(((Project) sampleFinishes.get(i)).getPurchaseName());
            cell = row.createCell(8);
            cell.setCellStyle(cellStyle);
            if (((Project) sampleFinishes.get(i)).getOriginalDeliveryDate() != null) {
                cell.setCellValue(((Project) sampleFinishes.get(i)).getOriginalDeliveryDate());
            } else {
                cell.setCellValue("");
            }
            cell = row.createCell(9);
            cell.setCellStyle(cellStyle);
            if (((Project) sampleFinishes.get(i)).getOriginalSampleScheduledDate() != null) {
                cell.setCellValue(((Project) sampleFinishes.get(i)).getOriginalSampleScheduledDate());
            } else {
                cell.setCellValue("");
            }
            cell = row.createCell(10);
            cell.setCellStyle(boderStyle);


            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(5, -7);
            Date m = c.getTime();
            Date today = new Date();
            if (((Project) sampleFinishes.get(i)).getOriginalDeliveryDate() != null) {
                boolean flag = ((Project) sampleFinishes.get(i)).getOriginalDeliveryDate().getTime() >= m.getTime();
                if (flag == true) {
                    cell.setCellValue("???????????????");
                } else {
                    cell.setCellValue("????????????");
                }
            } else if (((Project) sampleFinishes.get(i)).getOriginalSampleScheduledDate() != null) {
                boolean flag = ((Project) sampleFinishes.get(i)).getOriginalSampleScheduledDate().getTime() >= m.getTime();
                if (flag == true) {
                    cell.setCellValue("???????????????");
                } else {
                    cell.setCellValue("????????????");
                }
            }

            Date tempDate = sampleFinishes.get(i).getActualStartDate();
            cell = row.createCell(11);
            cell.setCellStyle(cellStyle);
            if (tempDate != null) {
                cell.setCellValue(format1.format(tempDate));
            } else {
                cell.setCellValue("");
            }

            tempDate = sampleFinishes.get(i).getIfDate();
            cell = row.createCell(12);
            cell.setCellStyle(cellStyle);
            if (tempDate != null) {
                cell.setCellValue(format1.format(tempDate));
            } else {
                cell.setCellValue("");
            }
        }
        int i = 0;
		/*while (i < 16)
		{
			sheet.autoSizeColumn((short)i);
			i++;
		}*/
        String paths = UploadAndDownloadPathUtil.getFilePath();

        tempPath = new File(paths);
        if ((!tempPath.exists()) || (!tempPath.isDirectory())) {
            tempPath.mkdir();
        }
        FileOutputStream fs = new FileOutputStream(paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls", false);
        wb.write(fs);
        fs.close();

        return paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls";
    }


    /**
     * ????????????????????????
     *
     * @param finishList
     * @return
     */
    public static String exportFinishByTimeExcel(List<Project> finishList) throws Exception {


        //??????workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        //??????sheet
        HSSFSheet sheet = wb.createSheet("???????????????????????????");

        HSSFFont font = wb.createFont();
        font.setFontName("??????");
        font.setFontHeightInPoints((short) 16);//??????????????????


        HSSFFont font2 = wb.createFont();
        font2.setFontName("??????");
        font2.setFontHeightInPoints((short) 16);//??????????????????
        font2.setColor(HSSFColor.RED.index);


        //?????????row:????????????0???
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy/m/d"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(HSSFColor.BLACK.index);

        //??????????????????
        HSSFCellStyle boderStyle = wb.createCellStyle();
        boderStyle.setAlignment(HorizontalAlignment.CENTER);
        boderStyle.setBorderBottom(BorderStyle.THIN);
        boderStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderLeft(BorderStyle.THIN);
        boderStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderRight(BorderStyle.THIN);
        boderStyle.setRightBorderColor(HSSFColor.BLACK.index);
        boderStyle.setBorderTop(BorderStyle.THIN);
        boderStyle.setTopBorderColor(HSSFColor.BLACK.index);


        //????????????????????????
        HSSFCellStyle lastStyle = wb.createCellStyle();
        lastStyle.setAlignment(HorizontalAlignment.CENTER);
        lastStyle.setBorderBottom(BorderStyle.THIN);
        lastStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderLeft(BorderStyle.THIN);
        lastStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderRight(BorderStyle.THIN);
        lastStyle.setRightBorderColor(HSSFColor.BLACK.index);
        lastStyle.setBorderTop(BorderStyle.THIN);
        lastStyle.setTopBorderColor(HSSFColor.BLACK.index);
        lastStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// ???????????????
        lastStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);


        //???????????????
        HSSFCell cell = row.createCell(0); //??????????????????
        cell.setCellValue("??????????????????");
        cell.setCellStyle(style);
        style.setFont(font2);


        DecimalFormat df = new DecimalFormat("??###,##0.00");

        int size = finishList.size();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                int clCount = 0;
                row = sheet.createRow(1);
                cell = row.createCell(clCount++); //???????????????
                cell.setCellValue("??????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(clCount++); //???????????????
                cell.setCellValue("?????????");
                cell.setCellStyle(boderStyle);
                cell = row.createCell(clCount++); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(clCount++); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(clCount++); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(clCount++); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(clCount++); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("?????????");
                cell = row.createCell(clCount++); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(clCount++); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(clCount++); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(clCount++); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????????????????");
                cell = row.createCell(clCount++); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("????????????");
                cell = row.createCell(clCount++); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");
                cell = row.createCell(clCount++); //???????????????
                cell.setCellStyle(boderStyle);
                cell.setCellValue("??????????????????");


            }
            int clCount = 0;
            row = sheet.createRow(i + 2);
            cell = row.createCell(clCount++); //???????????????
            cell.setCellValue(i + 1);
            cell.setCellStyle(boderStyle);

            cell = row.createCell(clCount++); //???????????????
            cell.setCellValue(finishList.get(i).getProjectNo());
            cell.setCellStyle(boderStyle);

            cell = row.createCell(clCount++); //???????????????
            if (null == finishList.get(i).getCustomerGrade()) {
                cell.setCellValue("???");
            } else {
                if (1 == finishList.get(i).getCustomerGrade()) {
                    cell.setCellValue("A");
                } else if (2 == finishList.get(i).getCustomerGrade()) {
                    cell.setCellValue("B");
                } else if (3 == finishList.get(i).getCustomerGrade()) {
                    cell.setCellValue("C");
                } else {
                    cell.setCellValue("???");
                }
            }

            cell.setCellStyle(boderStyle);

            cell = row.createCell(clCount++); //???????????????
            cell.setCellValue(finishList.get(i).getPurchaseName());
            cell.setCellStyle(boderStyle);

            cell = row.createCell(clCount++); //???????????????
            cell.setCellValue(finishList.get(i).getSellName());
            cell.setCellStyle(boderStyle);

            cell = row.createCell(clCount++); //???????????????
            cell.setCellValue(finishList.get(i).getCompanyName());
            cell.setCellStyle(boderStyle);

            cell = row.createCell(clCount++); //???????????????
            cell.setCellValue(finishList.get(i).getProjectName() == null ? "" : finishList.get(i).getProjectName());
            cell.setCellStyle(boderStyle);

            cell = row.createCell(clCount++); //???????????????
            cell.setCellStyle(cellStyle);
            if (null != finishList.get(i).getOriginalSampleScheduledDate()) {
                cell.setCellValue(DateUtil.dateToStr(finishList.get(i).getOriginalSampleScheduledDate()));
            } else {
                cell.setCellValue("");
            }


            cell = row.createCell(clCount++); //???????????????
            cell.setCellStyle(boderStyle);
            if (null != finishList.get(i).getSampleFinishTime()) {
                cell.setCellValue(DateUtil.dateToStr(finishList.get(i).getSampleFinishTime()));
            } else {
                cell.setCellValue("");
            }




            cell = row.createCell(clCount++); //???????????????
            cell.setCellStyle(boderStyle);
            if (null != finishList.get(i).getOriginalDeliveryDate()) {
                cell.setCellValue(DateUtil.dateToStr(finishList.get(i).getOriginalDeliveryDate()));
            } else {
                cell.setCellValue("");
            }

            cell = row.createCell(clCount++); //???????????????
            cell.setCellStyle(boderStyle);
            if (null != finishList.get(i).getFinishTime()) {
                cell.setCellValue(DateUtil.dateToStr(finishList.get(i).getFinishTime()));
            } else {
                cell.setCellValue("");
            }


            cell = row.createCell(clCount++); //???????????????
            cell.setCellStyle(boderStyle);
            String statusStr = "";
            if (6 == finishList.get(i).getProjectStatus()) {
                statusStr += ",????????????";
            }
            if (2 == finishList.get(i).getProjectStatus()) {
                statusStr += ",????????????";
            }
            if (StringUtils.isNotBlank(statusStr)) {
                cell.setCellValue(statusStr.substring(1));
            } else {
                cell.setCellValue("");
            }


            cell = row.createCell(clCount++); //???????????????
            cell.setCellStyle(boderStyle);
            if (null != finishList.get(i).getOriginalSampleScheduledDate() && null != finishList.get(i).getSampleFinishTime()) {

                cell.setCellValue(DateUtil.getIsBefore(finishList.get(i).getOriginalSampleScheduledDate(), finishList.get(i).getSampleFinishTime()));
            } else {
                cell.setCellValue("");
            }

            cell = row.createCell(clCount++); //???????????????
            cell.setCellStyle(boderStyle);
            if (null != finishList.get(i).getOriginalDeliveryDate() && null != finishList.get(i).getFinishTime()) {
                cell.setCellValue(DateUtil.getIsBefore(finishList.get(i).getOriginalDeliveryDate(), finishList.get(i).getFinishTime()));
            } else {
                cell.setCellValue("");
            }

        }

        String paths = UploadAndDownloadPathUtil.getFilePath();

        tempPath = new File(paths);
        // deleteFile(tempPath);
        if (!tempPath.exists() || !tempPath.isDirectory()) {
            tempPath.mkdir(); // ???????????????????????????????????????
        }
        FileOutputStream fs = new FileOutputStream(paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls", false);
        wb.write(fs);
        fs.close();

        return paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls";
    }


}
