package com.shopme.admin.user;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.shopme.common.entity.User;

import jakarta.servlet.http.HttpServletResponse;

public class AbstractExporter {
	public void setResponseHeader(HttpServletResponse response, String contentType, String extension) throws IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd_HH-mm-ss");
		String timestamp = dateFormatter.format(new Date());
		String fileName = "users_"+timestamp+ extension;
		
		response.setContentType(contentType);
		
		String headerKey = "Content-Disposition";
		String headrValue = "attachment; filename="+fileName;
		response.setHeader(headerKey,headrValue);
	}
}
