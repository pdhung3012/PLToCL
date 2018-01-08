package edu.iastate.cs.run;

import util.FileIO;

public class RunDuplicateTestLine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folderInput="/Users/hungphan/git/PLToCL/data/cliffer_grammars/filter_v1/";
		String fpFile=folderInput+"file.txt";
		String fpLine=folderInput+"line.txt";
		String fpOutFile=folderInput+"test_file.txt";
		String fpOutLine=folderInput+"test_line.txt";
		
		String[] arrLine=FileIO.readStringFromFile(fpLine).trim().split("\n");
		String[] arrFile=FileIO.readStringFromFile(fpFile).trim().split("\n");
		
		StringBuilder sbFile=new StringBuilder();
		StringBuilder sbLine=new StringBuilder();
		
		for(int i=0;i<arrLine.length;i++){
			sbFile.append(arrFile[i]+"\n");
			sbFile.append(arrFile[i]+"\n");
			sbLine.append(arrLine[i]+"\n");
			sbLine.append(arrLine[i]+"\n");
		}
		FileIO.writeStringToFile(sbFile.toString(), fpOutFile);
		FileIO.writeStringToFile(sbLine.toString(), fpOutLine);
	}

}
