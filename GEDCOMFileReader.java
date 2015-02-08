package gedcomfilereader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Savio Dcruz
 */
public class GEDCOMFileReader {
    public static void main(String[] args) {
        try {
            File dir = new File(".");
            
            //Clear the contents of output.txt
            String outputFileLoc = dir.getCanonicalPath() + File.separator + "output.txt";
            FileWriter fw = new FileWriter(outputFileLoc);
            fw.close();
            
            //Read from gedcom.ged file
            File fin = new File(dir.getCanonicalPath() + File.separator + "gedcom.ged");
            readFile(fin);
        } catch(IOException ex) {
            Logger.getLogger(GEDCOMFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void readFile(File fin) throws IOException {
        //List of valid tag names
	      String[] tags = {"INDI", "NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", 
            "FAM", "MARR", "HUSB", "WIFE", "CHIL", "DIV", "DATE", "TRLR", "NOTE"};
        ArrayList<String> tagList = new ArrayList<>(Arrays.asList(tags));
        
        FileInputStream fis = new FileInputStream(fin);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
	
        while((line = br.readLine()) != null) {
            writeToFile(line);
            
            int counter = 0;
            boolean invalidTag = false;
            String tagStr = "";
            
            for(String retval: line.split(" ", 3)) {
                counter++;
                
                switch(counter)
                {
                    case 1 :
                        writeToFile("Level Number: " + retval);
                        break;
                    case 2 :
                        if(tagList.contains(retval)) {
                            tagStr = "Tag: " + retval + "\n";
                        } else {
                            tagStr = "Invalid tag\n";
                            invalidTag = true;
                        }
                        break;
                    case 3 : 
                        if(invalidTag && tagList.contains(retval))
                            tagStr = "Tag: " + retval + "\n";
                    default :
                        break;
                }
            }
            writeToFile(tagStr);
  	}
        br.close();
    }
    
    private static void writeToFile(String line) throws IOException {
        File dir = new File(".");
        String outputFileLoc = dir.getCanonicalPath() + File.separator + "output.txt";
        FileWriter fw = new FileWriter(outputFileLoc, true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(line);
        bw.newLine();
        bw.close();
        fw.close();
    }
}
