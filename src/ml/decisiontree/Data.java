package ml.decisiontree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Data {
	  
      private List<Record> records  =new ArrayList<>();
      
      
      public Data(String fullFilePath){
    	  loadData(fullFilePath);
    	  
      }


	private void loadData(String fullFilePath) {
		BufferedReader br = null;
    	  try {
			 br  = new BufferedReader(new InputStreamReader(new FileInputStream(fullFilePath)));
			String line = "";
			while(( line = br.readLine())!= null){
				if(line.length() > 0){
					records.add(new Record(line));
				}
				
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			try {
				if(br != null){
					br.close();
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}


	public List<Record> getRecords() {
		return records;
	}
      
      
      
}
