package edu.iastate.cs.run;

import util.FileIO;

public class RunGenerateOracleFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folderInput="/Users/hungphan/git/PLToCL/data/cliffer_grammars/filter_v2/";
		String folderOutputComment="/Users/hungphan/git/PLToCL/data/cliffer_grammars/filter_v2/oracle_code/";
		
		//String[] arrComment=FileIO.readStringFromFile(folderInput+"comment.txt").trim().split("\n");
		String[] arrFile=FileIO.readStringFromFile(folderInput+"file.txt").trim().split("\n");
		String[] arrLine=FileIO.readStringFromFile(folderInput+"line.txt").trim().split("\n");
		
		for(int i=0;i<arrFile.length;i++){
			String[] arrItemFile=arrFile[i].replace(".java", "").trim().split("/");
			String itemLine=arrLine[i].trim();
			String lineNumber=String.format("%05d", (i+1));
			String itemFileNameA=lineNumber+"_"+"A-"+arrItemFile[arrItemFile.length-2]+"-"+arrItemFile[arrItemFile.length-1]+"-"+itemLine+".txt";
			String itemFileNameB=lineNumber+"_"+"B-"+arrItemFile[arrItemFile.length-2]+"-"+arrItemFile[arrItemFile.length-1]+"-"+itemLine+".txt";
			
			FileIO.writeStringToFile("", folderOutputComment+ itemFileNameB);
			FileIO.writeStringToFile(FileIO.readStringFromFile(arrFile[i]), folderOutputComment+ itemFileNameA);
			
			
		}
		
		
	}

}
