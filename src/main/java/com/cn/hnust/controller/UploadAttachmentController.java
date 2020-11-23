package com.cn.hnust.controller;


import com.cn.hnust.service.ComplaintInspectionReportService;
import com.cn.hnust.service.QualityAnalysisService;
import com.cn.hnust.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RequestMapping("/upload")
@Controller
public class UploadAttachmentController {

    private static final Log LOG = LogFactory.getLog(UploadAttachmentController.class);

    private static final String QUALITY_ANALYSIS = "1";
    private static final String TECHNOLOGY_ANALYSIS = "2";

    @Autowired
    QualityAnalysisService qualityAnalysisService;
    @Autowired
    ComplaintInspectionReportService complaintInspectionReportService;


    @RequestMapping("/uploadProductPicAndChangeName.do")
    @ResponseBody
    public JsonResult uploadProductPicAndChangeName(HttpServletRequest request,
                                                    HttpServletResponse response) {

        Map<String, String> compressNameMap = null;

        JsonResult jsonResult = new JsonResult();
        String fileName = "";
        List<String> result = new ArrayList<String>();

        String projectNo = request.getParameter("projectNo");

        try {

            String drawingName = request.getParameter("fileName");

            String path = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator + "1" + File.separator;

            String compressPath = path + "compressImg" + File.separator;

            File file = new File(path);
            File compressImg = new File(compressPath);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
            if (!compressImg.exists() && !compressImg.isDirectory()) {
                compressImg.mkdirs();
            }

            Map<String, Map<String, String>> multiFileUpload = OperationFileUtil
                    .multiFileUpload2(request, path, compressPath, null);

            String compressName = "";
            if (multiFileUpload != null && multiFileUpload.size() > 0) {
                compressNameMap = multiFileUpload.get("compressFilePaths");
                boolean isWindow = System.getProperty("os.name").startsWith(
                        "Windows");

                if (compressNameMap != null) {
                    if (isWindow) {

                        for (Map.Entry<String, String> pic : compressNameMap
                                .entrySet()) {
                            String compressfileName = "";
                            compressfileName = (UploadAndDownloadPathUtil
                                    .getProjectStatic() + pic.getValue()
                                    .substring(
                                            UploadAndDownloadPathUtil
                                                    .getProjectImg().length()))
                                    .replaceAll("\\\\", "/");

                            result.add(compressfileName);

                        }
                    } else {

                        for (Map.Entry<String, String> pic : compressNameMap
                                .entrySet()) {
                            String compressfileName = "";
                            compressfileName = (UploadAndDownloadPathUtil
                                    .getProjectStatic() + pic.getValue().substring(
                                    UploadAndDownloadPathUtil.getProjectImg()
                                            .length()));
                            result.add(compressfileName);

                        }

                    }
                    jsonResult.setData(result);
                    jsonResult.setOk(true);

                }

            }
        } catch (Exception e) {

            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage(e.getMessage());

        }

        return jsonResult;

    }

    /**
     * 上传周报图片
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/uploadWeekPicture")
    @ResponseBody
    public JsonResult uploadWeekPicture(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        String weekPicture = request.getParameter("fileNames");
        PropertisUtil prop = new PropertisUtil("config.properties");
        try {
            String path = prop.get("picPath");
            File file = new File(path);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdir();
            }
            //调用保存文件的帮助类进行保存文件(文件上传，form表单提交)
            String fileNames = "";
            String picNames = "";
            List<String> result = new ArrayList<String>();
            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload(request, path + File.separator);
            if (!(multiFileUpload == null || multiFileUpload.size() == 0)) {
                Set<String> keySet = multiFileUpload.keySet();
                for (String key : keySet) {
                    String pic = multiFileUpload.get(key);
                    picNames += pic + ",";
                }
                if (picNames.endsWith(",")) {
                    picNames = picNames.substring(0, picNames.length() - 1);
                }
                //截取出文件名
                if (StringUtils.isNotBlank(picNames)) {
                    String[] picNameArray = picNames.split(",");
                    for (int i = 0; i < picNameArray.length; i++) {
                        File tempFile = new File(picNameArray[i].trim());
                        String fileName = tempFile.getName();
                        fileNames += fileName + ",";
                    }
                }
                if (fileNames.endsWith(",")) {
                    fileNames = fileNames.substring(0, fileNames.length() - 1);
                }
            }
            jsonResult.setData(fileNames);
            jsonResult.setOk(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage(e.getMessage());
        }
        return jsonResult;
    }


    /**
     * 获取上传检验表格
     *
     * @param file
     * @param request
     * @param model
     * @return String
     * @throws IllegalStateException
     * @throws IOException
     * @Title upload
     * @Description
     */
    @ResponseBody
    @RequestMapping(value = "/uploadQualityForm")
    public JsonResult upload(HttpServletRequest request, ModelMap model) {

        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            //获取相对路径
            String path = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator + "1" + File.separator;
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }


            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
            //原始名称
            String originalFilename = "";
            String newFileName = "";
            if (multiFileUpload != null && multiFileUpload.size() > 0) {
                Set<String> keySet = multiFileUpload.keySet();
                for (String key : keySet) {
                    newFileName = multiFileUpload.get(key);
                    originalFilename = key;
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("path", path + newFileName);
            map.put("originalFilename", originalFilename);
            jsonResult.setData(map);
            jsonResult.setOk(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage("上传失败");
            jsonResult.setOk(false);
        }
        return jsonResult;

    }


    /**
     * 获取投诉上传内容
     *
     * @param file
     * @param request
     * @param model
     * @return String
     * @throws IllegalStateException
     * @throws IOException
     * @Title upload
     * @Description
     */
    @ResponseBody
    @RequestMapping(value = "/uploadComplaint")
    public JsonResult uploadComplaint(HttpServletRequest request, ModelMap model) {

        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            //获取相对路径
            String path = UploadAndDownloadPathUtil.getComplaintPath() + File.separator + projectNo + File.separator;
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
            //原始名称
            String originalFilename = "";
            String newFileName = "";
            if (multiFileUpload != null && multiFileUpload.size() > 0) {
                Set<String> keySet = multiFileUpload.keySet();
                for (String key : keySet) {
                    newFileName = multiFileUpload.get(key);
                    originalFilename = key;
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("path", path + newFileName);
            map.put("originalFilename", originalFilename);
            map.put("newFileName", newFileName);
            jsonResult.setData(map);
            jsonResult.setOk(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage("上传失败");
            jsonResult.setOk(false);
        }
        return jsonResult;

    }


    /**
     * 文件上传
     */
    @ResponseBody
    @RequestMapping("fileUploadPicture")
    public JsonResult fileUploadPicture(String imgdata, HttpServletRequest request) {

        JsonResult jsonResult = new JsonResult();
        BASE64Decoder decoder = new BASE64Decoder();
        FileOutputStream fos = null;
        try {
            String projectNo = request.getParameter("projectNo");
            String fileName = request.getParameter("fileName");
            //获取相对路径
            String path = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator + "1" + File.separator;
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            //新的图片名称
            String newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            String imgPath = path + newFileName;
            // new一个文件对象用来保存图片，默认保存当前工程根目录
            File imageFile = new File(imgPath);
            // 创建输出流
            fos = new FileOutputStream(imageFile);
            // 获得一个图片文件流，我这里是从flex中传过来的
            byte[] result = decoder.decodeBuffer(imgdata.split(",")[1]);//解码
            for (int i = 0; i < result.length; ++i) {
                if (result[i] < 0) {// 调整异常数据
                    result[i] += 256;
                }
            }
            fos.write(result);
            fos.flush();

            jsonResult.setData(UploadAndDownloadPathUtil.getProjectStatic() + "/" + projectNo + "/" + "1" + "/" + newFileName);
            jsonResult.setMessage("上传成功");
            jsonResult.setOk(true);

        } catch (Exception e1) {
            LOG.error("上传图片失败", e1);
            jsonResult.setMessage("上传失败");
            jsonResult.setOk(false);
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonResult;
    }


    /**
     * 更新质检图片，旋转图片保存
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateQualityImg")
    @ResponseBody
    public JsonResult updateQualityImg(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String filePath = request.getParameter("filePath");
            String degree = request.getParameter("degree");
            System.err.println("filePath:" + filePath);
            System.err.println("degree:" + degree);
            if (StringUtils.isNotBlank(degree)) {
                ImageUtil.spin(Integer.parseInt(degree), filePath);
            }
            jsonResult.setMessage("更新成功");
            jsonResult.setOk(true);
            return jsonResult;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            jsonResult.setMessage("更新失败");
            jsonResult.setOk(true);
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage("更新失败");
            jsonResult.setOk(true);
            return jsonResult;
        }
    }


    /**
     * 质量分析表、技术分析表上传
     *
     * @param request
     * @param response
     * @return JsonResult
     * @Title upload
     * @Description
     */
    @RequestMapping("/analysisUpload")
    @ResponseBody
    public JsonResult analysisUpload(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            String drawingName = request.getParameter("fileName");

            //查询cookie中用户
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isBlank(userName)) {
                jsonResult.setOk(false);
                jsonResult.setMessage("请先登录");
                return jsonResult;
            }

            String path1 = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator;
            String path = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator + "analysis" + File.separator;

            File file1 = new File(path1);
            File file = new File(path);
            if (!file1.exists() && !file1.isDirectory()) {
                file1.mkdir();
            }
            if (!file.exists() && !file.isDirectory()) {
                file.mkdir();
            }

            //根据文件名获取上传文件的位置  数据库保存原始文件名称
            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
            String fileName = "";
            if (!(multiFileUpload == null || multiFileUpload.size() == 0)) {
                fileName = multiFileUpload.get(drawingName);
            }
            jsonResult.setOk(true);
            jsonResult.setData(fileName);
            return jsonResult;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage("上传失败");
            return jsonResult;
        } catch (IOException e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage("上传失败");
            return jsonResult;
        }
    }

    /**
     * 质检问题上传的产品视频图片
     *
     * @param file
     * @param request
     * @param model
     * @return String
     * @throws IllegalStateException
     * @throws IOException
     * @Title upload
     * @Description
     */
    @ResponseBody
    @RequestMapping(value = "/uploadProductFile")
    public JsonResult uploadProductFile(HttpServletRequest request, ModelMap model) {

        JsonResult jsonResult = new JsonResult();
        try {
            String projectNo = request.getParameter("projectNo");
            //获取相对路径
            String path1 = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator;
            String path = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator + "product" + File.separator;
            File dirFile1 = new File(path1);
            if (!dirFile1.exists()) {
                dirFile1.mkdirs();
            }
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }


            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
            //原始名称
            String newFileName = "";
            if (multiFileUpload != null && multiFileUpload.size() > 0) {
                Set<String> keySet = multiFileUpload.keySet();
                for (String key : keySet) {
                    newFileName = multiFileUpload.get(key);
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("fileName", newFileName);
            jsonResult.setData(map);
            jsonResult.setOk(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage("上传失败");
            jsonResult.setOk(false);
        }
        return jsonResult;

    }


}
