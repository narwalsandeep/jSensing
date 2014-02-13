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
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class CsvWriter {
    File file;
    FileWriter fw ;
    BufferedWriter bw;
    
    public void write(int data){
        try{
            bw.write(data + ",");
            System.out.println("writing." + data);
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void open(String to_file){
       
        try{
            file = new File(to_file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            
        }
        catch(Exception e){}
        
    }

    void close() {
        try {
            bw.close();
           //
            //fw.close();
            //file.close();
        } catch (IOException ex) {
            Logger.getLogger(CsvWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}