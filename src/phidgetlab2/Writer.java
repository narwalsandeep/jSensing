/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


*/

package phidgetlab2;

/**
 *
 * @author sandeepnarwal
 */
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
 
public class Writer {
	
	private String content;
	private String toFile;
	
	
	/**
	 *
	 * @param content
	 */
	public void setContent(String content){
		
		this.content = content;
		
	}
	
	/**
	 *
	 * @return
	 */
	public String getContent(){
		
		return this.content;
		
	}
	
	/**
	 *
	 * @param toFile
	 */
	public void setFile(String toFile){
		
		this.toFile = toFile;
	}
	
	/**
	 *
	 * @return
	 */
	public String getFile(){
		
		return this.toFile;
	}
	
	/**
	 *
	 */
	public boolean write() throws IOException{
		
		File file = new File(this.toFile);

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(this.content);
		bw.write("\n");
		bw.close();
		
		System.out.println();

		return true;
		

	}
}