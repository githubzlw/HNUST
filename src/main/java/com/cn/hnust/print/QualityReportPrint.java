package com.cn.hnust.print;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

import com.cn.hnust.enums.QualityStatusEnum;
import com.cn.hnust.enums.QualityTypeEnum;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.QualityPicExplain;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.UploadAndDownloadPathUtil;



/**
 * @Description: 鏍规嵁妯℃澘鏂囦欢鎵撳嵃锛岃緭鍑烘姤浠锋ā鏉�
 * @Author: polo
 * @CreateDate:2018/08/08
 */

public class QualityReportPrint {

	private static File tempPath;

	/**
	 * pdf鎵撳嵃,浣跨敤excel缂栬緫锛岀敓鎴恜df
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static String printExcel(HttpServletRequest request, String path,Project project,QualityReport qualityReport,
			List<QualityPicExplain> details,List<QualityPicExplain> bads,List<QualityPicExplain> materials,
			List<QualityPicExplain> packages,List<QualityPicExplain> checks)throws Exception {

        Boolean sampleFlag = (qualityReport.getType() == QualityTypeEnum.SAMPLE.getCode() || qualityReport.getType() == QualityTypeEnum.METAPHASE.getCode());
		Boolean packageFlag = (qualityReport.getType() == QualityTypeEnum.FINAL_TIME.getCode() || qualityReport.getType() == QualityTypeEnum.LARGE_CARGO.getCode()) && (packages == null || packages.size() == 0);
		/*
		 * 鎵撳紑妯℃澘锛屽鍒秙heet锛屽彟瀛�
		 */

        File file = null;
        //鏍峰搧鎴栬�呬腑鏈熸楠岋紝褰撴棤鍖呰鍥炬椂锛屼娇鐢╭uality_excel_sample.xls
        //鏍峰搧鎴栬�呬腑鏈熸楠岋紝褰撴湁鍖呰鍥炬椂锛屼娇鐢╭uality_excel_package.xls
        //澶ц揣鎴栬�呯粓鏈熸楠岋紝褰撴棤鍖呰鍥炬椂锛屼娇鐢╭uality_excel_non_package.xls
        //澶ц揣鎴栬�呯粓鏈熸楠岋紝鏈夊寘瑁呭浘鏃讹紝浣跨敤quality_excel20180807.xls
        //澶ц揣鎴栬�呯粓鏈熸楠岋紝鏈夊寘瑁呭浘鏃犳潗璐ㄥ浘鏃讹紝浣跨敤quality_excel_non_material.xls
        
		if((qualityReport.getType() == QualityTypeEnum.SAMPLE.getCode() || qualityReport.getType() == QualityTypeEnum.METAPHASE.getCode()) && (packages == null || packages.size() == 0)){
			file = new File(path+"demo"+File.separator+"quality_excel_sample.xls");
		}else if((qualityReport.getType() == QualityTypeEnum.SAMPLE.getCode() || qualityReport.getType() == QualityTypeEnum.METAPHASE.getCode()) && (packages != null && packages.size()>0)){
			file = new File(path+"demo"+File.separator+"quality_excel_package.xls");
		}else if((qualityReport.getType() == QualityTypeEnum.FINAL_TIME.getCode() || qualityReport.getType() == QualityTypeEnum.LARGE_CARGO.getCode()) && (packages == null || packages.size() == 0)){
			file = new File(path+"demo"+File.separator+"quality_excel_non_package.xls");
		}else if((qualityReport.getType() == QualityTypeEnum.FINAL_TIME.getCode() || qualityReport.getType() == QualityTypeEnum.LARGE_CARGO.getCode()) && (packages != null && packages.size()>0) && (materials == null || materials.size() == 0) && (bads == null || bads.size() == 0)){
			file = new File(path+"demo"+File.separator+"quality_excel_non_material.xls");
		}else{
			file = new File(path+"demo"+File.separator+"quality_excel20180807.xls");
		}

		FileInputStream fi = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(fi);
		wb.cloneSheet(0); // 澶嶅埗宸ヤ綔绨�
		wb.setSheetName(1, "quality report"); // 璁剧疆宸ヤ綔绨垮悕绉�

		// 璁剧疆鐩稿悓鍐呭
		int rowNo = 3;
		int colNo = 0;
		Row nRow = null;
		Cell nCell = null;

		HSSFSheet sheet = wb.getSheetAt(1); // 瀹氫綅鍒板綋鍓嶅伐浣滆〃
		sheet.setForceFormulaRecalculation(true); // 寮哄埗鍏紡鑷姩璁＄畻锛屽埄鐢ㄦā鏉挎椂锛屾ā鏉夸腑鐨勫叕寮忎笉浼氬洜鍊煎彂鐢熷彉鍖栬�岃嚜鍔ㄨ绠椼��

		nRow = sheet.getRow(rowNo);
		nCell = nRow.getCell(2);
		nCell.setCellValue(project.getProjectName() == null ? "" : project.getProjectName());

		nRow = sheet.getRow(++rowNo);
		nCell = nRow.getCell(2);
		nCell.setCellValue(project.getProjectNo());
		nCell = nRow.getCell(6);
		nCell.setCellValue(qualityReport.getPicNum() == null ? "" : qualityReport.getPicNum());

		nRow = sheet.getRow(++rowNo);
		nCell = nRow.getCell(3);
		nCell.setCellValue(qualityReport.getPlace() == null ? "" : qualityReport.getPlace());
		nCell = nRow.getCell(7);
		nCell.setCellValue(qualityReport.getSpendTime() == null ? "" : qualityReport.getSpendTime());

		nRow = sheet.getRow(++rowNo);
		nCell = nRow.getCell(3);
		nCell.setCellValue(DateFormat.date2String(qualityReport.getCheckDate()));
		nCell = nRow.getCell(7);
		nCell.setCellValue(qualityReport.getOrders());
		
		nRow = sheet.getRow(++rowNo);
		nCell = nRow.getCell(2);
		nCell.setCellValue(QualityStatusEnum.getEnum(qualityReport.getStatus()).getValue() + (qualityReport.getCheckExplain() == null ? "" : qualityReport.getCheckExplain().replaceAll("\\s*", "")) + (qualityReport.getExplainCause() == null ? "" : qualityReport.getExplainCause().replaceAll("\\s*", "")));
		

		rowNo = 11;	
		String serverPath = UploadAndDownloadPathUtil.getProjectImg() + File.separator + project.getProjectNo()+ File.separator + "1" + File.separator;

		if (serverPath.contains("project_img")) {
			serverPath = serverPath.substring(0, serverPath.indexOf("project_img") - 1);
		}
		//娣诲姞缁嗚妭鍥剧墖
		int d_tl = 0;
		int c_tl = 0; //妫�楠岃〃鏍煎浘鐗囨暟閲�
		int b_tl = 0; //鏈夐棶棰樺浘鐗囨暟閲�
		int m_tl = 0; //鏉愯川鍥剧墖鏁伴噺
		int p_tl = 0; //鍖呰鍥剧墖鏁伴噺
		if(details != null && details.size() > 0){
			d_tl = details.size();
			if(d_tl > 1){
				insertRow(wb, sheet, rowNo, 28*(d_tl-1));
			}
			
			//濡傛灉鏄牱鍝佹垨鑰呬腑鏈熸楠�,璐ㄦ琛ㄧ殑鍥剧墖鏁伴噺杩涜鎻掑叆琛�
			if(sampleFlag){
				if(bads != null && bads.size() > 0){
					b_tl = bads.size();
					insertRow(wb, sheet, rowNo, 28*b_tl);
				}
				
				if(materials != null && materials.size() > 0){
					m_tl = materials.size();
					insertRow(wb, sheet, rowNo, 28*m_tl);
				}
				
				//褰撳寘瑁呭浘涓虹┖鏃讹紝鎻掑叆妫�楠屽浘鏁伴噺
				if(packages == null || packages.size() == 0){
					if(checks != null && checks.size() > 0){
						c_tl = checks.size();
						insertRow(wb, sheet, rowNo, 28*c_tl);
					}
				}
			   
			}
			
			for (int i=0;i<d_tl;i++){
				int startRow = rowNo + i*28;
				int startCol = 0;
				int stopRow = rowNo + i*28 + 26;
				int stopCol = 8;
//				File picFile = new File(details.get(i).getPicName());
//				String name = picFile.getName();
				setPicture(serverPath + details.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
				nRow = sheet.getRow(stopRow++);
				nCell = nRow.getCell(0);
				nCell.setCellValue(details.get(i).getPicExplain() == null ? "" : details.get(i).getPicExplain());			
			}
		}
		
		

		//鏍峰搧鍜屼腑鏈熸楠屼笉鏄剧ず闄ょ粏鑺傜殑鍥�
		if(!sampleFlag){
					rowNo = rowNo + d_tl*28 + 18;
			    	//娣诲姞涓嶈壇鍝佸浘鐗�
					if(bads != null && bads.size() > 0){
						b_tl = bads.size();
						if(b_tl > 1){
						    insertRow(wb, sheet, rowNo, 28*(b_tl-1));
						}
						//娣诲姞鏉愯川鍥剧墖
						if(materials != null && materials.size() > 0){
							m_tl = materials.size();
							insertRow(wb, sheet, rowNo, 28*m_tl);
						}
						//娣诲姞妫�娴嬭〃鏍�
						if(packages == null || packages.size() == 0){
							if(checks != null && checks.size() > 0){
								c_tl = checks.size();
								insertRow(wb, sheet, rowNo, 28*c_tl);
							}
						}
						
						
						for (int i=0;i<b_tl;i++){
							int startRow = rowNo + i*28;
							int startCol = 0;
							int stopRow = rowNo + i*28 + 26;
							int stopCol = 8;
							// File picFile = new File(bads.get(i).getPicName());
							//String name = picFile.getName();
							setPicture(serverPath+bads.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
							nRow = sheet.getRow(stopRow++);
							nCell = nRow.getCell(0);
							nCell.setCellValue(bads.get(i).getPicExplain() == null ? "" : bads.get(i).getPicExplain());			
						}
						
						rowNo = rowNo + b_tl*28;
						//娣诲姞鏉愯川鍥剧墖
						for (int i=0;i<m_tl;i++){
							int startRow = rowNo + i*28;
							int startCol = 0;
							int stopRow = rowNo + i*28 + 26;
							int stopCol = 8;
							//File picFile = new File(materials.get(i).getPicName());
							//String name = picFile.getName();
							setPicture(serverPath+materials.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
							nRow = sheet.getRow(stopRow++);
							nCell = nRow.getCell(0);
							nCell.setCellValue(materials.get(i).getPicExplain() == null ? "" : materials.get(i).getPicExplain());			
						}
					}else{
						
						//娣诲姞鏉愯川鍥剧墖
						if(materials != null && materials.size() > 0){
							m_tl = materials.size();
							insertRow(wb, sheet, rowNo, 28*(m_tl-1));
						}
						//娣诲姞妫�娴嬭〃鏍�(褰撳寘瑁呭浘涓虹┖鏃舵坊鍔�)
						if(packages == null || packages.size() == 0){
							if(checks != null && checks.size() > 0){
								c_tl = checks.size();
								insertRow(wb, sheet, rowNo, 28*c_tl);
							}
						}
						
						
						for (int i=0;i<m_tl;i++){
							int startRow = rowNo + i*28;
							int startCol = 0;
							int stopRow = rowNo + i*28 + 26;
							int stopCol = 8;
							//File picFile = new File(materials.get(i).getPicName());
							//String name = picFile.getName();
							setPicture(serverPath+materials.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
							nRow = sheet.getRow(stopRow++);
							nCell = nRow.getCell(0);
							nCell.setCellValue(materials.get(i).getPicExplain() == null ? "" : materials.get(i).getPicExplain());			
						}
						
					}
					


					//娣诲姞鍖呰鍥剧墖
					if(packages != null && packages.size() > 0){
						rowNo = rowNo + m_tl*28 + 1;
						if(m_tl == 0 && (bads == null || bads.size() == 0)){
							rowNo = rowNo - 4;
						}						
						nRow = sheet.getRow(rowNo);
						nCell = nRow.getCell(2);
						nCell.setCellValue(qualityReport.getBoxNumber());
						nCell = nRow.getCell(6);
						nCell.setCellValue(qualityReport.getPerQty());
						rowNo++;
						nRow = sheet.getRow(++rowNo);
						nCell = nRow.getCell(2);
						nCell.setCellValue(qualityReport.getInventoryQty());
						nCell = nRow.getCell(6);
						nCell.setCellValue(qualityReport.getOpenQty());						
						rowNo = rowNo + 3;
						
						
					    p_tl = packages.size();
						if(p_tl > 1){
							insertRow(wb, sheet, rowNo, 28*(p_tl-1));
						}
						
						//娣诲姞妫�楠岃〃鏍�
						if(checks != null && checks.size()>0){
							c_tl = checks.size();
							insertRow(wb, sheet, rowNo, 28*c_tl);
						}
						
						for (int i=0;i<p_tl;i++){
							int startRow = rowNo + i*28;
							int startCol = 0;
							int stopRow = rowNo + i*28 + 26;
							int stopCol = 8;
							//File picFile = new File(packages.get(i).getPicName());
							//String name = picFile.getName();
							setPicture(serverPath+packages.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
							nRow = sheet.getRow(stopRow++);
							nCell = nRow.getCell(0);
							nCell.setCellValue(packages.get(i).getPicExplain() == null ? "" : packages.get(i).getPicExplain());			
						}
						
						rowNo = rowNo + p_tl*28;
						//娣诲姞妫�楠岃〃鏍煎浘鐗�
						for (int i=0;i<c_tl;i++){
							int startRow = rowNo + i*28;
							int startCol = 0;
							int stopRow = rowNo + i*28 + 26;
							int stopCol = 8;
							//File picFile = new File(checks.get(i).getPicName());
							//String name = picFile.getName();
							setPicture(serverPath+checks.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
							nRow = sheet.getRow(stopRow++);
							nCell = nRow.getCell(0);
							nCell.setCellValue(checks.get(i).getPicExplain() == null ? "" : checks.get(i).getPicExplain());			
						}
						
						
					}else{
						
						rowNo = rowNo + m_tl*28;
						//娣诲姞妫�娴嬭〃鏍煎浘鐗�
						for (int i=0;i<c_tl;i++){
							int startRow = rowNo + i*28;
							int startCol = 0;
							int stopRow = rowNo + i*28 + 26;
							int stopCol = 8;
							//File picFile = new File(checks.get(i).getPicName());
							//String name = picFile.getName();
							setPicture(serverPath+checks.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
							nRow = sheet.getRow(stopRow++);
							nCell = nRow.getCell(0);
							nCell.setCellValue(checks.get(i).getPicExplain() == null ? "" : checks.get(i).getPicExplain());			
						}
					}
			
			
			
			
		}else{
			
			if(packages == null || packages.size() == 0){
					//娣诲姞鏈夐棶棰樺浘鐗�
					rowNo = rowNo + d_tl*28;			
					for (int i=0;i<b_tl;i++){
						int startRow = rowNo + i*28;
						int startCol = 0;
						int stopRow = rowNo + i*28 + 26;
						int stopCol = 8;
						//File picFile = new File(bads.get(i).getPicName());
						//String name = picFile.getName();
						setPicture(serverPath+bads.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
						nRow = sheet.getRow(stopRow++);
						nCell = nRow.getCell(0);
						nCell.setCellValue(bads.get(i).getPicExplain() == null ? "" : bads.get(i).getPicExplain());			
					}
					
					//娣诲姞鏉愯川璇佹槑鍥剧墖
					rowNo = rowNo + b_tl*28;			
					for (int i=0;i<m_tl;i++){
						int startRow = rowNo + i*28;
						int startCol = 0;
						int stopRow = rowNo + i*28 + 26;
						int stopCol = 8;
						//File picFile = new File(materials.get(i).getPicName());
						//String name = picFile.getName();
						setPicture(serverPath+materials.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
						nRow = sheet.getRow(stopRow++);
						nCell = nRow.getCell(0);
						nCell.setCellValue(materials.get(i).getPicExplain() == null ? "" : materials.get(i).getPicExplain());			
					}
					
					//娣诲姞妫�楠岃〃鏍煎浘鐗�
					rowNo = rowNo + m_tl*28;			
					for (int i=0;i<c_tl;i++){
						int startRow = rowNo + i*28;
						int startCol = 0;
						int stopRow = rowNo + i*28 + 26;
						int stopCol = 8;
						//File picFile = new File(checks.get(i).getPicName());
						//String name = picFile.getName();
						setPicture(serverPath+checks.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
						nRow = sheet.getRow(stopRow++);
						nCell = nRow.getCell(0);
						nCell.setCellValue(checks.get(i).getPicExplain() == null ? "" : checks.get(i).getPicExplain());			
					}
			}else{
				//娣诲姞鏈夐棶棰樺浘鐗�
				rowNo = rowNo + d_tl*28;			
				for (int i=0;i<b_tl;i++){
					int startRow = rowNo + i*28;
					int startCol = 0;
					int stopRow = rowNo + i*28 + 26;
					int stopCol = 8;
					//File picFile = new File(bads.get(i).getPicName());
					//String name = picFile.getName();
					setPicture(serverPath+bads.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
					nRow = sheet.getRow(stopRow++);
					nCell = nRow.getCell(0);
					nCell.setCellValue(bads.get(i).getPicExplain() == null ? "" : bads.get(i).getPicExplain());			
				}
				
				//娣诲姞鏉愯川璇佹槑鍥剧墖
				rowNo = rowNo + b_tl*28;			
				for (int i=0;i<m_tl;i++){
					int startRow = rowNo + i*28;
					int startCol = 0;
					int stopRow = rowNo + i*28 + 26;
					int stopCol = 8;
					//File picFile = new File(materials.get(i).getPicName());
					//String name = picFile.getName();
					setPicture(serverPath+materials.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
					nRow = sheet.getRow(stopRow++);
					nCell = nRow.getCell(0);
					nCell.setCellValue(materials.get(i).getPicExplain() == null ? "" : materials.get(i).getPicExplain());			
				}
				
				//瑁呯淇℃伅
				rowNo = rowNo + m_tl*28 + 1;
				nRow = sheet.getRow(rowNo);
				nCell = nRow.getCell(2);
				nCell.setCellValue(qualityReport.getBoxNumber());
				nCell = nRow.getCell(6);
				nCell.setCellValue(qualityReport.getPerQty());
				rowNo++;
				nRow = sheet.getRow(++rowNo);
				nCell = nRow.getCell(2);
				nCell.setCellValue(qualityReport.getInventoryQty());
				nCell = nRow.getCell(6);
				nCell.setCellValue(qualityReport.getOpenQty());					
				
				rowNo = rowNo + 3;
				p_tl = packages.size();
				if(p_tl > 1){
					insertRow(wb, sheet, rowNo, 28*(p_tl-1));
				}
				
				if(checks != null && checks.size() > 0){
					c_tl = checks.size();
					insertRow(wb, sheet, rowNo, 28*c_tl);
				}
				for (int i=0;i<p_tl;i++){
					int startRow = rowNo + i*28;
					int startCol = 0;
					int stopRow = rowNo + i*28 + 26;
					int stopCol = 8;
					// File picFile = new File(packages.get(i).getPicName());
					// String name = picFile.getName();
					setPicture(serverPath+packages.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
					nRow = sheet.getRow(stopRow++);
					nCell = nRow.getCell(0);
					nCell.setCellValue(packages.get(i).getPicExplain() == null ? "" : packages.get(i).getPicExplain());			
				}
				
				//娣诲姞妫�楠岃〃鏍煎浘鐗�
				rowNo = rowNo + p_tl*28;			
				for (int i=0;i<c_tl;i++){
					int startRow = rowNo + i*28;
					int startCol = 0;
					int stopRow = rowNo + i*28 + 26;
					int stopCol = 8;
					//File picFile = new File(checks.get(i).getPicName());
					//String name = picFile.getName();
					setPicture(serverPath+checks.get(i).getPicName(), sheet, startRow, startCol, stopRow, stopCol);
					nRow = sheet.getRow(stopRow++);
					nCell = nRow.getCell(0);
					nCell.setCellValue(checks.get(i).getPicExplain() == null ? "" : checks.get(i).getPicExplain());			
				}
			}
		}

		HSSFPrintSetup ps = sheet.getPrintSetup();		 
		ps.setLandscape(false); // 鎵撳嵃鏂瑰悜锛宼rue锛氭í鍚戯紝false锛氱旱鍚�
		ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //绾稿紶
		sheet.setMargin(HSSFSheet.BottomMargin,( double ) 0.5 );// 椤佃竟璺濓紙涓嬶級
		sheet.setMargin(HSSFSheet.LeftMargin,( double ) 0.1 );// 椤佃竟璺濓紙宸︼級
		sheet.setMargin(HSSFSheet.RightMargin,( double ) 0.1 );// 椤佃竟璺濓紙鍙筹級
		sheet.setMargin(HSSFSheet.TopMargin,( double ) 0.5 );// 椤佃竟璺濓紙涓婏級
		sheet.setHorizontallyCenter(true);//璁剧疆鎵撳嵃椤甸潰涓烘按骞冲眳涓�
		sheet.setVerticallyCenter(true);//璁剧疆鎵撳嵃椤甸潰涓哄瀭鐩村眳涓娇鐢≒OI杈撳嚭Excel鏃舵墦鍗伴〉闈㈢殑璁剧疆
		
		String paths = UploadAndDownloadPathUtil.getFilePath();

		tempPath = new File(paths);
		// deleteFile(tempPath);
		if (!tempPath.exists() || !tempPath.isDirectory()) {
			tempPath.mkdir(); // 濡傛灉涓嶅瓨鍦紝鍒欏垱寤鸿鏂囦欢澶�
		}
		wb.removeSheetAt(0); // 鍒犻櫎妯℃澘sheet
		StringBuffer fileName = new StringBuffer();
		fileName.append(DateFormat.currentDate().replace("-", ""));
		fileName.append("_");
		fileName.append(project.getProjectNo());
		fileName.append("_");
		if(StringUtils.isNotBlank(project.getProjectName())){
			String projectName = project.getProjectName();
			projectName = projectName.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
			fileName.append(projectName);
			fileName.append("_");
		}
		if(StringUtils.isNotBlank(qualityReport.getPicNum())){
			String picNum = qualityReport.getPicNum();
			picNum = picNum.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
			fileName.append(picNum);
			fileName.append("_");
		}
		fileName.append(QualityTypeEnum.getEnum(qualityReport.getType()).getValue());
		fileName.append("_");
		fileName.append(qualityReport.getUser());
		fileName.append(".xls");
		paths = paths + File.separator + fileName;
		FileOutputStream fs = new FileOutputStream(paths, false);
		wb.write(fs);
		fs.close();		

		return paths;
	}


	
	
	
	// 鍒犻櫎鏂囦欢鍜岀洰褰�
	private static void clearFiles(String workspaceRootPath) {
		File file = new File(workspaceRootPath);
		if (file.exists()) {
			deleteFile(file);
		}
	}

	public static void deleteFile(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
			}
		}
		file.delete();
	}

	public static void delFolder(String folderPath) {
		try {
			boolean delAllFile = delAllFile(folderPath); // 鍒犻櫎瀹岄噷闈㈡墍鏈夊唴瀹�
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 鍒犻櫎绌烘枃浠跺す
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 鍒犻櫎鎸囧畾鏂囦欢澶逛笅鎵�鏈夋枃浠�
	// param path 鏂囦欢澶瑰畬鏁寸粷瀵硅矾寰�
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
				delAllFile(path + "/" + tempList[i]);// 鍏堝垹闄ゆ枃浠跺す閲岄潰鐨勬枃浠�
				delFolder(path + "/" + tempList[i]);// 鍐嶅垹闄ょ┖鏂囦欢澶�
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * excel鎻掑叆琛屾暟鎹�
	 * 
	 * @param wb
	 * @param sheet
	 * @param starRow
	 * @param rows
	 */
	public static void insertRow(HSSFWorkbook wb, HSSFSheet sheet, int starRow,
			int rows) {

		sheet.shiftRows(starRow + 1, sheet.getLastRowNum(), rows, true, false);

		starRow = starRow - 1;

		for (int i = 0; i < rows; i++) {

			HSSFRow sourceRow = null;
			HSSFRow targetRow = null;
			HSSFCell sourceCell = null;
			HSSFCell targetCell = null;
			short m;

			starRow = starRow + 1;
			sourceRow = sheet.getRow(starRow);
			targetRow = sheet.createRow(starRow + 1);
			targetRow.setHeight(sourceRow.getHeight());

			for (m = sourceRow.getFirstCellNum(); m < sourceRow
					.getLastCellNum(); m++) {

				sourceCell = sourceRow.getCell(m);
				targetCell = targetRow.createCell(m);
				targetCell.setCellStyle(sourceCell.getCellStyle());
				targetCell.setCellType(sourceCell.getCellType());

			}
		}

	}

	

	// 澶勭悊鍥剧墖澶囨敞瀛樻斁 鍋忕Щ閲忎笉鍚� dx1 = 255; dy1 = 125; dx2 = 200; dy2 = 150;
	public static void setPicture(String pic, HSSFSheet sheet, int startRow,
			int startCol, int stopRow, int stopCol) throws IOException {
		pic = pic.replace("\\","/");
		System.err.println("setPicture:" + pic);
		File imageFile = new File(pic);
		if (imageFile.exists()) {
			InputStream is = new FileInputStream(new File(pic));
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = sheet.getWorkbook().addPicture(bytes,
					Workbook.PICTURE_TYPE_JPEG); // 鎵╁睍鍚嶅彲涓�.jpg/.jpeg/.png
			is.close();

			HSSFPatriarch drawing = sheet.createDrawingPatriarch(); // Create
																	// the
																	// drawing
																	// patriarch.
																	// This is
																	// the top
																	// level
																	// container
																	// for all
																	// shapes.
			// 鍓嶉潰鍥涗釜鍙傛暟鏄浘鐗囧亸绉婚噺
			HSSFClientAnchor anchor = new HSSFClientAnchor(150, 20, 200, 0,
					(short) startCol, startRow, (short) stopCol, stopRow); // add
																			// a
																			// picture
																			// shape
			anchor.setRow1(startRow); // set position corner of the picture
			anchor.setCol1(startCol);
			anchor.setRow2(stopRow);
			anchor.setCol2(stopCol);

			drawing.createPicture(anchor, pictureIdx);
		}

	}

	
	


	

	public static void main(String[] args) throws Exception {
		
		String str = "20180830_SHS11548_鐧借壊ABSPC浠禵-01/-02_鏍峰搧妫�楠宊zoumin.xls";
		str = str.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
		System.out.println(str);
		
	}

}
