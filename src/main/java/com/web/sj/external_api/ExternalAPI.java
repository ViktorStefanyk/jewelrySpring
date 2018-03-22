package com.web.sj.external_api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.web.sj.domain.Image;

public class ExternalAPI {
	
	private static String IMGUR_API_KEY = "cf3b3b3d5489875";
	private static String IMGUR_API_URI = "https://api.imgur.com/3/image";
	private static String IMGUR_API_ACCESS_TOKEN = "Bearer 29fb5f5e93f7efa38722ac2a102e81ab8fe3f0d0";
	
	public static Image sendImageToHosting(MultipartFile imageFile) {
		Image image = new Image();
		try {
			System.out.println("Writing image...");
			URL url = new URL(IMGUR_API_URI);
			System.out.println("Encoding...");
			String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(Base64.encodeBase64String(imageFile.getBytes()).toString(), "UTF-8");
			data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(IMGUR_API_KEY, "UTF-8");
			
			System.out.println("Connecting...");
			
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//conn.setRequestProperty("Authorization", "Client-ID " + IMGUR_API_KEY);
			conn.setRequestProperty("Authorization", IMGUR_API_ACCESS_TOKEN);
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        System.out.println("Sending data...");
	        wr.write(data);
	        wr.flush();
	        System.out.println("Finished.");
	        
	        //just display the raw response
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = in.readLine()) != null) {
	        	sb.append(line);
	        }
	        in.close();
	        JSONObject json = new JSONObject(sb.toString());
	        
//	        String link = json.getJSONObject("data").getString("link");
//	        String id = json.getJSONObject("data").getString("id");
//	        String deletehash = json.getJSONObject("data").getString("deletehash");
	        image.setLink(json.getJSONObject("data").getString("link"));
	        image.setId(json.getJSONObject("data").getString("id"));
	        image.setDeletehash(json.getJSONObject("data").getString("deletehash"));
	        image.setImageIdentificationInDB("1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return image;
	}

}
