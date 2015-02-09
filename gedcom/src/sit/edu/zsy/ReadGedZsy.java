package sit.edu.zsy;

import java.io.*;

public class Main{
	InputStream in;
	public enum TAG{
		INDI("INDI"),NAME("NAME"),SEX("SEX"),
		BIRT("BIRT"),DEAT("DEAT"),FAMC("FAMC"),
		FAMS("FAMS"),FAM("FAM"),MARR("MARR"),
		HUSB("HUSB"),WIFE("WIFE"),CHIL("CHIL"),
		DIV("DIV"),DATE("DATE"),TRLR("TRLR"),NOTE("NOTE");
		private final String TagName;
		TAG(String TagName) {
		        this.TagName = TagName;
		    }
		    public String getTagName() {return TagName;}
		}
	String[] TAGs={"INDI","NAME","SEX",
			"BIRT","DEAT","FAMC","FAMS","FAM","MARR","HUSB",
			"WIFE","CHIL","DIV","DATE","TRLR","NOTE"};

	public void printLevel(String inLine){
		int pos=inLine.indexOf(" ");
		String[] content=inLine.split(" ");
		if(content[0].matches("\\d*"))
		{
			System.out.println(content[0]);
			String tag=null;
			
			if(content[0].equals("0")){
				if(content[1].matches("\\@[A-Za-z]*[0-9]*\\@")){
					tag=content[2];
				}
			}
			
			if(!content[1].equals(null))
				tag=content[1];				
			
			for(int i=0;i<TAGs.length;i++){
				if(tag.equals(TAGs[i]))
				{
					break;
				}
				else
				{
					if(i==TAGs.length-1)
						tag="Invalid";
				}

			}
			System.out.println(tag);


		}
		
	}
	
	public static void main(String[] args) throws IOException{
		FileReader file = new FileReader("./MyFam_SiyuanZheng.ged");
		BufferedReader br = new BufferedReader(file);
		String currLine;
		Main th = new Main();
		while((currLine=br.readLine())!=null)
		{
			System.out.println(currLine);
			th.printLevel(currLine);
		}
		br.close();
		file.close();
	}
	

}