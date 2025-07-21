package FrameWorkTraining.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJasonDataToMap(String Filepath) throws IOException
	{
		//Read Jason to String 
		
		String jasoncontent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//FrameWorkTraining//data//purchaseOrder.jason"),StandardCharsets.UTF_8);
		
		//String to HashMap-> need Jackson Databind to be added in dependency//
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jasoncontent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;	
		}
	
	//{map,map}
		
	}

