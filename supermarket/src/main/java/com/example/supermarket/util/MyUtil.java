package com.example.supermarket.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Component
public class MyUtil {

	@Value("${woniu.file-path}")
	String basePath;
	public String upload(MultipartFile file){
		String originalFilename = file.getOriginalFilename();
		String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
		String newFileName= UUID.randomUUID().toString().replaceAll("-","");
		String pg=basePath+"/"+newFileName+suffix;


		try {
			file.transferTo(new File(pg));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return newFileName+suffix;
	}
	public String dateToStr(Date date){
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy年MM月dd日");
		String format = sdf.format(date);
		return format;
	}


	public String getTimeNow(){
		// 获取当前时间的毫秒值
		long milliseconds = System.currentTimeMillis();

		// 将毫秒值转换为 LocalDateTime 对象
		Instant instant = Instant.ofEpochMilli(milliseconds);
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);

		// 格式化 LocalDateTime 对象为指定格式的字符串
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = localDateTime.format(formatter);

		System.out.println(formattedDateTime);
		return formattedDateTime;
	}
}
