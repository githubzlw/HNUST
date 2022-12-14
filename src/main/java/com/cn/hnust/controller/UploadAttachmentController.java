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
     * ??????????????????
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
            //????????????????????????????????????????????????(???????????????form????????????)
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
                //??????????????????
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
     * ????????????????????????
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
            //??????????????????
            String path = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator + "1" + File.separator;
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }


            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
            //????????????
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
            jsonResult.setMessage("????????????");
            jsonResult.setOk(false);
        }
        return jsonResult;

    }


    /**
     * ????????????????????????
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
            //??????????????????
            String path = UploadAndDownloadPathUtil.getComplaintPath() + File.separator + projectNo + File.separator;
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
            //????????????
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
            jsonResult.setMessage("????????????");
            jsonResult.setOk(false);
        }
        return jsonResult;

    }


    /**
     * ????????????
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
            //??????????????????
            String path = UploadAndDownloadPathUtil.getProjectImg()
                    + File.separator + projectNo + File.separator + "1" + File.separator;
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            //??????????????????
            String newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            String imgPath = path + newFileName;
            // new????????????????????????????????????????????????????????????????????????
            File imageFile = new File(imgPath);
            // ???????????????
            fos = new FileOutputStream(imageFile);
            // ?????????????????????????????????????????????flex???????????????
            byte[] result = decoder.decodeBuffer(imgdata.split(",")[1]);//??????
            for (int i = 0; i < result.length; ++i) {
                if (result[i] < 0) {// ??????????????????
                    result[i] += 256;
                }
            }
            fos.write(result);
            fos.flush();

            jsonResult.setData(UploadAndDownloadPathUtil.getProjectStatic() + "/" + projectNo + "/" + "1" + "/" + newFileName);
            jsonResult.setMessage("????????????");
            jsonResult.setOk(true);

        } catch (Exception e1) {
            LOG.error("??????????????????", e1);
            jsonResult.setMessage("????????????");
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
     * ???????????????????????????????????????
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
            jsonResult.setMessage("????????????");
            jsonResult.setOk(true);
            return jsonResult;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            jsonResult.setMessage("????????????");
            jsonResult.setOk(true);
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage("????????????");
            jsonResult.setOk(true);
            return jsonResult;
        }
    }


    /**
     * ???????????????????????????????????????
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

            //??????cookie?????????
            String userName = WebCookie.getUserName(request);
            if (StringUtils.isBlank(userName)) {
                jsonResult.setOk(false);
                jsonResult.setMessage("????????????");
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

            //??????????????????????????????????????????  ?????????????????????????????????
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
            jsonResult.setMessage("????????????");
            return jsonResult;
        } catch (IOException e) {
            e.printStackTrace();
            jsonResult.setOk(false);
            jsonResult.setMessage("????????????");
            return jsonResult;
        }
    }

    /**
     * ???????????????????????????????????????
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
            //??????????????????
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
            //????????????
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
            jsonResult.setMessage("????????????");
            jsonResult.setOk(false);
        }
        return jsonResult;

    }


}
