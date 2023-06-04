package data;

import main.GamePanel;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveData{
	public String Time;
	
	GamePanel gp;
	
	public SaveData(GamePanel gp) {
		this.gp = gp;
	}
    public void save(double Time) {
		String csvFile = "data.csv";
		String pt = "" + Time;
	    //Initial data, ilisan ni inig apply nas duwa
	    List<String[]> data = new ArrayList<>();
	    data.add(new String[]{""+ pt});
	    
	    writeDataToCSV(csvFile,data);
	}
	
	//mao ni ang mo handle sa CSV code
	public void writeDataToCSV(String csvFile, List<String[]> data) {
	    try (FileWriter writer = new FileWriter(csvFile)) {
	        for (String[] rowData : data) {
	            writer.append(String.join(",", rowData));
	            writer.append("\n");
	        }
	        System.out.println("Data has been written to " + csvFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public void load() {
		try (BufferedReader br = Files.newBufferedReader(Paths.get("data.csv"))){
			String DELIMITER = ",";
			String line;
			
			 while ((line = br.readLine()) != null) {

			        // convert line into columns
			        String[] columns = line.split(DELIMITER);

			        // print all columns
			        System.out.println("Time["+ String.join(", ", columns) +"]");
			 
			        
			        Time = "" + String.join(DELIMITER, columns);
			        
			    }
			
		}catch(IOException e) {
			 e.printStackTrace();
		}
	}
    
	
}
