package com.desmond.v1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.desmond.v1.bean.Channel;
import com.desmond.v1.bean.Rss;

public class V1Main {
	
	@SuppressWarnings("unchecked")
	public static <T> T readString(Class<T> clazz, String fullPath)
			throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Unmarshaller u = jc.createUnmarshaller();

		return (T) u.unmarshal(new File(fullPath));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T readConfig(Class<T> clazz, String config, Object...args ) throws IOException, JAXBException  {
		InputStream is = null;
		if(args.length > 0) {
			config = MessageFormat.format(config, args);
		}
		
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			is = new FileInputStream(config);
			
			return (T) u.unmarshal(is);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			if(is != null) {
				is.close();
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static<T> T readConfigFromStream(Class<T> clazz, InputStream dataStream) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz);
		
		Unmarshaller u = jc.createUnmarshaller();
		return (T) u.unmarshal(dataStream);
	}
	
	
	public static void main(String[] args) throws JAXBException {
		Rss rss = V1Main.readString(Rss.class, "G:/gitHub/projects/Feed-System/src/com/desmond/v1/files/finance.xml");
		
		System.out.println(rss);
	}
	
	
	
}
