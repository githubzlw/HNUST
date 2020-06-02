package com.cn.hnust.util;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;  
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;  
import org.apache.poi.xwpf.usermodel.*;  
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  











import com.cn.hnust.enums.QualityStatusEnum;
import com.cn.hnust.enums.QualityTypeEnum;
import com.cn.hnust.pojo.QualityPicExplain;
import com.cn.hnust.pojo.QualityReport;

import javax.imageio.stream.FileImageInputStream;  
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;
import java.io.IOException;  
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;  
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat; 
import java.util.Calendar;
import java.util.List;
import java.util.Map;
public class ExportWordUtil {  
    
    private static final Logger logger = LoggerFactory.getLogger(ExportWordUtil.class);  
  
  
    
    
    
    public static void main(String[] args) {
    	try {
    		
    		
    		String bgColor = "111111";  
    		CustomXWPFDocument xdoc = new CustomXWPFDocument();  
    		createTitle(xdoc, "质检报告"); 
    		ExportWordUtil exportWordUtil = new ExportWordUtil();
    		XWPFTable dTable = xdoc.createTable(4,4);  
    		exportWordUtil.setTableWidth(dTable, "10000");
    		exportWordUtil.createBaseInfoTable(dTable, xdoc, 4);
    		exportWordUtil.mergeCellsHorizontal(dTable, 0, 0, 1);
    		exportWordUtil.mergeCellsHorizontal(dTable, 0, 1, 2);
    		exportWordUtil.mergeCellsHorizontal(dTable, 1, 0, 1);
    		exportWordUtil.mergeCellsHorizontal(dTable, 1, 1, 2);
    		exportWordUtil.setCellText(xdoc, exportWordUtil.getCellHight(dTable, 0, 0, 600), "Product Name:产品名", bgColor, 600, 14, "仿宋");  
//    		exportWordUtil.setCellText(xdoc, exportWordUtil.getCellHight(dTable, 0, 3, 600), "方管", bgColor, 600, 14, "仿宋");  
//    		exportWordUtil.setCellText(xdoc, exportWordUtil.getCellHight(dTable, 1, 1, 600), "Internal #项目号", bgColor, 600, 14, "仿宋");  
//    		exportWordUtil.setCellText(xdoc, exportWordUtil.getCellHight(dTable, 0, 1, 600), "SHS13071-5", bgColor, 600, 14, "仿宋");  
    		
    		for (int i = 0; i < 3; i++) {
    			String str = i+"测试";
    			byte[] base64Info1 = new ExportWordUtil().image2byte("G:\\apache-tomcat-7.0.57\\wtpwebapps\\quote_file\\10006\\message\\20180404112552.jpg");    			
    			xdoc = new ExportWordUtil().export(base64Info1,i,xdoc,str);
			}


			Calendar c = Calendar.getInstance();  
			String fileName = "生成质检报告" + c.get(Calendar.YEAR) + to2String(String.valueOf((c.get(Calendar.MONTH) + 1)))  
			        + c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.HOUR_OF_DAY) + c.get(Calendar.MILLISECOND) + ".docx";  
			//获取存放路径  
			String classPath = "G:\\apache-tomcat-7.0.57\\wtpwebapps\\quote_file\\10006\\message\\";  

			System.out.println("==============report================" + classPath + fileName);  
			FileOutputStream fos = new FileOutputStream(classPath + fileName);  
			xdoc.write(fos);  
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
    
    private static String to2String(String str) {  
        if (str.length() > 2) {  
            str = str.substring(0, 2);  
        } else {  
            str = "0" + str;  
        }  
        return str;  
    }  
    
    
    public CustomXWPFDocument export(byte[] base64Info1,int num,CustomXWPFDocument xdoc,String str) {  
    	
        // 报表数据分析  
        XWPFTable dataReportTable = xdoc.createTable(num, 0);  
        createDataReportTable(dataReportTable, xdoc, base64Info1,str);  
        return xdoc; 
    }
    
    
    
    
    /** 
     * 导出word 
     * @param base64Info1       报表图片数据 
     * @return 
     */  
    public XWPFDocument export(byte[] base64Info1) {  
        try {  
            CustomXWPFDocument xdoc = new CustomXWPFDocument();  
            
            // 创建页眉  
//            createCtp(xdoc);  
  
            // 标题  
//            createTitle(xdoc);  
  
//            XWPFTable dTable = xdoc.createTable(4, 3);  
//            createBaseInfoTable(dTable, xdoc, "未来科技", "谢谢侬",    "1024", "生成报表201709120056251");  
            // 标题
            createTitle(xdoc, "质检报告");  
  
            // 报表数据分析  
            XWPFTable dataReportTable = xdoc.createTable(0, 0);  
            String str = "测试";
            createDataReportTable(dataReportTable, xdoc, base64Info1,str);  
  
            return xdoc;  
        } catch (Exception e) {  
            logger.error(e.getMessage(), e);  
            throw new RuntimeException("生成文件失败");  
        }  
    }  
    
    
    
    
    /** 
     * 在cell 里面插入图片 
     * @param xdoc 
     * @param paragraph 
     * @param imageByte 
     */  
    private void createPic(CustomXWPFDocument xdoc, XWPFParagraph paragraph, byte[] imageByte) {  
        try {  
            xdoc.addPictureData(imageByte, XWPFDocument.PICTURE_TYPE_JPEG);  
        } catch (InvalidFormatException e) {  
            e.printStackTrace();  
        }  
        xdoc.createPicture(paragraph, xdoc.getAllPictures().size() - 1, 500, 400, "  ");  
    }  
  
    // 图片到byte数组  
    public byte[] image2byte(String path) {  
        byte[] data = null;  
        InputStream input = null;  
        try {  
        	
    	   URL url = new URL(path);
		   // 打开连接
		   URLConnection con = url.openConnection();
		   //设置请求超时为5s
		   con.setConnectTimeout(120*1000);
		   // 输入流
		   input = con.getInputStream();
        	
//            input = new FileImageInputStream(new File(path));  
            ByteArrayOutputStream output = new ByteArrayOutputStream();  
            byte[] buf = new byte[1024];  
            int numBytesRead = 0;  
            while ((numBytesRead = input.read(buf)) != -1) {  
                output.write(buf, 0, numBytesRead);  
            }  
            data = output.toByteArray();  
            output.close();  
            input.close();  
        } catch (FileNotFoundException ex1) {  
            ex1.printStackTrace();  
        } catch (IOException ex1) {  
            ex1.printStackTrace();  
        }  
        return data;  
    }  
  
    /** 
     * 创建标题 
     */  
    private void createTitle(CustomXWPFDocument xdoc) {  
        // 标题  
        XWPFParagraph titleMes = xdoc.createParagraph();  
        titleMes.setAlignment(ParagraphAlignment.CENTER);  
        XWPFRun r1 = titleMes.createRun();  
        r1.setBold(true);  
        r1.setFontFamily("华文仿宋");  
        r1.setText("质检报告");// 活动名称  
        r1.setFontSize(18);  
        r1.setColor("333333");  
        r1.setBold(true);  
    }  
  
    /** 
     * 生成页眉 
     */  
    public void createCtp(CustomXWPFDocument document) {  
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();  
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);  
        // 添加页眉  
        CTP ctpHeader = CTP.Factory.newInstance();  
        CTR ctrHeader = ctpHeader.addNewR();  
        CTText ctHeader = ctrHeader.addNewT();  
        String headerText = "质检报告";  
        ctHeader.setStringValue(headerText);  
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);  
        // 设置为左对齐  
        headerParagraph.setAlignment(ParagraphAlignment.BOTH);  
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];  
        parsHeader[0] = headerParagraph;  
        try {  
            policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 生成基础信息Table 
     * 
     * @param table 
     * @param xdoc 
     */  
    public void createBaseInfoTable(XWPFTable table, CustomXWPFDocument xdoc, int rows) {        
        CTTbl ttbl = table.getCTTbl();  
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();  
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();  
        tblWidth.setW(new BigInteger("8600"));  
        tblWidth.setType(STTblWidth.AUTO); // STTblWidth.AUTO 自动长度  
//        mergeCellsVertically(table, 0, 0, 3);  
//        setCellText(xdoc, getCellHight(table, 0, 0, 2400), "基 础 信 息", bgColor, 600, 14, "仿宋");  
//        setCellText(xdoc, getCellHight(table, 0, 1, 600), "报告周期", bgColor, 1800, 14, "仿宋");  

    }  
  
    /** 
     * 生成标题 
     *  
     * @param xdoc 
     * @param titleText 
     */  
    public static void createTitle(CustomXWPFDocument xdoc, String titleText) {  
        XWPFParagraph headLine2 = xdoc.createParagraph();  
        headLine2.setAlignment(ParagraphAlignment.CENTER);  
        XWPFRun runHeadLine2 = headLine2.createRun();  
        runHeadLine2.setText(titleText);  
        runHeadLine2.setFontSize(16);  
        runHeadLine2.setFontFamily("宋体");  
        runHeadLine2.setBold(true);  
        runHeadLine2.setColor("333333");  
    }  
  
  
    
    
    
    
    /** 
     * 报表数据分析 
     *  
     * @param table 
     * @param xdoc 
     */  
    public void createDataReportTable(XWPFTable table, CustomXWPFDocument xdoc, byte[] base64Info1,int num) {  
        String bgColor = "000000";  
        CTTbl ttbl = table.getCTTbl();  
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();  
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();  
        tblWidth.setW(new BigInteger("8600"));  
        tblWidth.setType(STTblWidth.AUTO); // STTblWidth.AUTO 自动长度  
//        mergeCellsVertically(table, 0, 0, 3);  
        mergeCellsHorizontal(table, 0, 0, 0);  
                
         String  str = "";    
         setCellText(xdoc, getCellHight(table, 0, 0, 1200), str, bgColor, 8600, 14, "仿宋");  
        // 图片信息
        if(base64Info1 == null || base64Info1.length < 100){  
            setCellText(xdoc, getCellHight(table, 0, 0, 1200), "暂无数据", bgColor, 8600, 14, "仿宋");  
        } else {  
            setCellPic(xdoc, getCellHight(table, 0, 0, 1200), base64Info1);  
        }   
        setCellText(xdoc, getCellHight(table, 0, 0, 1400), "这是测试", bgColor, 8600, 14, "仿宋", ParagraphAlignment.LEFT,true);  
  
    }  
    
    
    
    
    
  
    /** 
     * 报表数据分析 
     *  
     * @param table 
     * @param xdoc 
     */  
    public void createDataReportTable(XWPFTable table, CustomXWPFDocument xdoc, byte[] base64Info1,String str) {  
        String bgColor = "111111";  
        CTTbl ttbl = table.getCTTbl();  
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();  
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();  
        tblWidth.setW(new BigInteger("8600"));  
        tblWidth.setType(STTblWidth.AUTO); // STTblWidth.AUTO 自动长度  
        
        CTTblBorders borders = table.getCTTbl().getTblPr().addNewTblBorders();
        CTBorder hBorder = borders.addNewInsideH();
        hBorder.setVal(STBorder.NONE);  // 线条类型
        hBorder.setSz(new BigInteger("0")); // 线条大小
        hBorder.setColor("ffffff"); // 设置颜色
        
        CTBorder vBorder = borders.addNewInsideV();
        vBorder.setVal(STBorder.NONE);
        vBorder.setSz(new BigInteger("0"));
        vBorder.setColor("ffffff");

        CTBorder lBorder = borders.addNewLeft();
        lBorder.setVal(STBorder.NONE);
        lBorder.setSz(new BigInteger("0"));
        lBorder.setColor("ffffff");

        CTBorder rBorder = borders.addNewRight();
        rBorder.setVal(STBorder.NONE);
        rBorder.setSz(new BigInteger("0"));
        rBorder.setColor("ffffff");

        CTBorder tBorder = borders.addNewTop();
        tBorder.setVal(STBorder.NONE);
        tBorder.setSz(new BigInteger("0"));
        tBorder.setColor("ffffff");

        CTBorder bBorder = borders.addNewBottom();
        bBorder.setVal(STBorder.NONE);
        bBorder.setSz(new BigInteger("0"));
        bBorder.setColor("ffffff");


//        mergeCellsVertically(table, 0, 0, 3);  
        mergeCellsHorizontal(table, 0, 0, 0);  
        
          
//         String  str = "";  
//        Double sss = 0.6666666666666666;  
//        DecimalFormat df = new DecimalFormat("0.00");  
  
//        str = "（一）报告时间内误报率"+df.format((sss * 100))+"%";  
  
//         setCellText(xdoc, getCellHight(table, 0, 0, 1200), str, bgColor, 8600, 14, "仿宋");  
  
//        setCellText(xdoc, getCellHight(table, 1, 0, 1200), "报表数据", bgColor, 4300, 14, "仿宋");  
//        setCellText(xdoc, getCellHight(table, 1, 1, 1200), "报表数据", bgColor, 4300, 14, "仿宋");  
  
        // 图片信息
        if(base64Info1 == null || base64Info1.length < 100){  
            setCellText(xdoc, getCellHight(table, 0, 0, 1200), "暂无数据", bgColor, 8600, 14, "仿宋");  
        } else {  
            setCellPic(xdoc, getCellHight(table, 0, 0, 1200), base64Info1);  
        }  

//         mergeCellsHorizontal(table, 3, 0, 1);  
        setCellText(xdoc, getCellHight(table, 0, 0, 1400), str, bgColor, 8600, 14, "仿宋", ParagraphAlignment.LEFT,true);  
  
    }  
  
    // 设置表格高度  
    public  XWPFTableCell getCellHight(XWPFTable xTable, int rowNomber, int cellNumber, int hight) {  
        XWPFTableRow row = null;  
        row = xTable.getRow(rowNomber);  
        row.setHeight(hight);  
        XWPFTableCell cell = null;  
        cell = row.getCell(cellNumber);  
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);  
        return cell;  
    }  
  
    /** 
     * 创建图片 
     */  
    private void setCellPic(CustomXWPFDocument xdoc, XWPFTableCell cell, byte[] imageByte) {  
        createPic(xdoc, cell.addParagraph(), imageByte);  
    }  
  
    private void setCellText(CustomXWPFDocument xDocument, XWPFTableCell cell, String text, String bgcolor, int width,  
            int fontSize, String textType) {  
        setCellText(xDocument, cell, text, bgcolor, width, fontSize, textType, ParagraphAlignment.CENTER);  
    }  
  
    /** 
     *  
     * @param xDocument 
     * @param cell 
     * @param text 
     * @param bgcolor 
     * @param width 
     */  
    private void setCellText(CustomXWPFDocument xDocument, XWPFTableCell cell, String text, String bgcolor, int width,  
            int fontSize, String textType, ParagraphAlignment align) {  
        setCellText(xDocument, cell, text, bgcolor, width, fontSize, textType, align, false);  
    }  
  
    public void setCellText(CustomXWPFDocument xDocument, XWPFTableCell cell, String text, String bgcolor, int width,  
            int fontSize, String textType, ParagraphAlignment align, boolean isBold) {  
        CTTc cttc = cell.getCTTc();  
        CTTcPr cellPr = cttc.addNewTcPr();  
        cellPr.addNewTcW().setW(BigInteger.valueOf(width));  
        XWPFParagraph pIO = cell.addParagraph();  
        if (null == align) {  
            pIO.setAlignment(ParagraphAlignment.CENTER);  
        } else {  
            pIO.setAlignment(align);  
        }  
        cell.removeParagraph(0);  
  
        if (text.contains("\n")) {  
            String[] myStrings = text.split("\n");  
            for (int i = 0; i < myStrings.length; i++) {  
                String temp = myStrings[i];  
                if (isBold) {  
                    if (i == 0) {  
                        setTextStyle(pIO, textType, bgcolor, fontSize, temp, true, true);  
                    } else {  
                        setTextStyle(pIO, textType, bgcolor, fontSize, "      " + temp, true, false);  
                    }  
                } else {  
                    setTextStyle(pIO, textType, bgcolor, fontSize, temp, true, false);  
                }  
            }  
        } else {  
            setTextStyle(pIO, textType, bgcolor, fontSize, text, false, false);  
        }  
    }  
  
    private void setTextStyle(XWPFParagraph pIO, String textType, String bgcolor, int fontSize, String text,  
            boolean isEntery, boolean isBold) {  
        XWPFRun rIO = pIO.createRun();  
        if (textType == null || textType.equals("")) {  
            rIO.setFontFamily("微软雅黑");  
        } else {  
            rIO.setFontFamily(textType);  
        }  
        if (bgcolor == null || bgcolor.equals("")) {  
            rIO.setColor("000000");  
        } else {  
            rIO.setColor(bgcolor);  
        }  
        rIO.setFontSize(fontSize);  
        rIO.setText(text);  
        if (isBold)  
            rIO.setBold(true);  
        if (isEntery)  
            rIO.addBreak();  
    }  
  
    // 设置表格间的空行  
    public void setEmptyRow(CustomXWPFDocument xdoc, XWPFRun r1) {  
        XWPFParagraph p1 = xdoc.createParagraph();  
        p1.setAlignment(ParagraphAlignment.CENTER);  
        p1.setVerticalAlignment(TextAlignment.CENTER);  
        r1 = p1.createRun();  
    }  
  
    // word跨列合并单元格  
    public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {  
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {  
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);  
            if (cellIndex == fromCell) {  
                // The first merged cell is set with RESTART merge value  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);  
            } else {  
                // Cells which join (merge) the first one, are set with CONTINUE  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);  
            }  
        }  
    }  
  
    // word跨行并单元格  
    public void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {  
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {  
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);  
            if (rowIndex == fromRow) {  
                // The first merged cell is set with RESTART merge value  
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);  
            } else {  
                // Cells which join (merge) the first one, are set with CONTINUE  
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);  
            }  
        }  
    }  
    
    
	//删除指定文件夹下所有文件
	//param path 文件夹完整绝对路径
	   public static boolean delAllFile(String path) {
	       boolean flag = false;
	       File file = new File(path);
	       if (!file.exists()) {
	         return flag;
	       }
	       if (!file.isDirectory()) {
	         return flag;
	       }
	       String[] tempList = file.list();
	       File temp = null;
	       for (int i = 0; i < tempList.length; i++) {
	          if (path.endsWith(File.separator)) {
	             temp = new File(path + tempList[i]);
	          } else {
	              temp = new File(path + File.separator + tempList[i]);
	          }
	          if (temp.isFile()) {
	             temp.delete();
	          }
	          if (temp.isDirectory()) {
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件-
	             flag = true;
	          }
	       }
	       return flag;
	     } 
  
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   /**
	    * 替换word中内容
	    * @Title replaceInWord 
	    * @Description
	    * @param replacements
	    * @param doc
	    * @param outfile
	    * @throws IOException
	    * @return void
	    */
	   private void replaceInWord(Map<String, String> replacements, XWPFDocument doc, File outfile) throws IOException {
	        long count1 = 0;
	        long count2 = 0;
	        List<XWPFParagraph> paragraphs = doc.getParagraphs();
	        List<XWPFTable> tables = doc.getTables();
	        count1 = replaceInParagraphs(replacements, paragraphs, false);
	        count2 = replaceInTables(replacements, tables);
	        doc.write(new FileOutputStream(outfile));
	        System.out.println("段落替换数量累计：" + count1);
	        System.out.println("表格替换数量累计：" + count2);
	    }

	    /**
	     * 1.替换段落中的文本
	     * 
	     * @param replacements
	     * @param count
	     * @param paragraphs
	     * @return
	     */
	    private long replaceInParagraphs(Map<String, String> replacements, List<XWPFParagraph> paragraphs, boolean flag) {
	        long count = 0;
	        String pString = flag ? "表格" + "段落清晰：" : "段落：";
	        for (XWPFParagraph paragraph : paragraphs) {
	            System.out.println(pString + paragraph.getText());
	            List<XWPFRun> runs = paragraph.getRuns();
	            for (Map.Entry<String, String> replPair : replacements.entrySet()) {
	                String oldText = replPair.getKey();
	                String newText = replPair.getValue();
	                // 获取文本段
	                TextSegement tSegement = paragraph.searchText(oldText, new PositionInParagraph());
	                if (tSegement != null) {
	                    int beginRun = tSegement.getBeginRun();
	                    int endRun = tSegement.getEndRun();
	                    System.out.println(beginRun + " " + endRun);
	                    count++;
	                    if (beginRun == endRun) {
	                        // whole search string is in one Run
	                        XWPFRun run = runs.get(beginRun);
	                        String runText = run.getText(0);
	                        System.out.println("runText:" + runText);
	                        String replaced = runText.replace(oldText, newText);
	                        run.setText(replaced, 0);
	                    } else {
	                        // The search string spans over more than one Run
	                        // Put the Strings together
	                        StringBuilder b = new StringBuilder();
	                        for (int runPos = beginRun; runPos <= endRun; runPos++) {
	                            XWPFRun run = runs.get(runPos);
	                            b.append(run.getText(0));
	                        }
	                        String connectedRuns = b.toString();
	                        String replaced = connectedRuns.replace(oldText, newText);

	                        // The first Run receives the replaced String of all
	                        // connected Runs
	                        XWPFRun partOne = runs.get(beginRun);
	                        partOne.setText(replaced, 0);
	                        // Removing the text in the other Runs.
	                        for (int runPos = beginRun + 1; runPos <= endRun; runPos++) {
	                            XWPFRun partNext = runs.get(runPos);
	                            partNext.setText("", 0);
	                        }
	                    }
	                }
	            }
	        }
	        return count;
	    }

	    /**
	     * 1.替换表格中的文本
	     * 
	     * @param replacements
	     * @param count
	     * @param paragraphs
	     * @return
	     */
	    private long replaceInTables(Map<String, String> replacements, List<XWPFTable> tables) {

	        long count = 0;
	        for (XWPFTable table : tables) {
	            for (XWPFTableRow row : table.getRows()) {
	                for (XWPFTableCell cell : row.getTableCells()) {
	                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
	                    count += replaceInParagraphs(replacements, paragraphs, true);
	                }
	            }
	        }
	        return count;
	    }
	   

	/***
	* 导出word 设置行宽
	* @param table
	* @param width
	*/
  private  void setTableWidth(XWPFTable table,String width){  
        CTTbl ttbl = table.getCTTbl();  
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();  
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();  
        CTJc cTJc=tblPr.addNewJc();  
        cTJc.setVal(STJc.Enum.forString("center"));  
        tblWidth.setW(new BigInteger(width));  
        tblWidth.setType(STTblWidth.DXA);  
    } 
}  