package com.cn.hnust.component;


import org.apache.log4j.Logger;

import com.cn.hnust.util.ConvertVideo;
import com.cn.hnust.util.PropertiesUtils;

/**
 * 用于异步转换视频
 * @ClassName SynConvertVideo 
 * @Description 
 * @author zj
 * @date 2018年11月7日 下午12:02:26
 */

public class SynConvertVideo {

	private final static Logger log = Logger.getLogger(SynConvertVideo.class);
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	
	public static void sendRequest(String inputPath,String outputPath,String name) {

		try {			
			new Thread(new SynConvertVideo().new SendHttp(inputPath,reader.getProperty("ffmpeg_path"),outputPath,name)).start();			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("视频转换失败", e);
		}

	}
	
	
	
	class SendHttp implements Runnable{
		
		private String inputPath;		
		private String ffmpegPath;
		private String outputPath;
		private String name;
		
		SendHttp(String inputPath,String ffmpegPath,String outputPath,String name){
			this.inputPath = inputPath;
			this.ffmpegPath = ffmpegPath;
			this.outputPath = outputPath;
			this.name = name;
		}

		@Override
		public void run() {			
			ConvertVideo.process(inputPath, ffmpegPath, outputPath, name);			
		}
		
		
		
		
	}
	
	

}