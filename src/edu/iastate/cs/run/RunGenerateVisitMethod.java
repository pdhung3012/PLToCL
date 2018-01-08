package edu.iastate.cs.run;

import util.FileIO;

public class RunGenerateVisitMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folderInput="data/";
		String tagsInput="data/pennTreeTags.txt";
		String fileInput=folderInput+"templateVisit.txt";
		String fileOutput=folderInput+"outTemplateVisit.txt";
		String strContent=FileIO.readStringFromFile(fileInput);
		String[] arrFiles=FileIO.readStringFromFile(tagsInput).trim().split("\n");
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<arrFiles.length;i++){
			String strItem=arrFiles[i].split("\\(")[0].trim();
			String strNewContent=strContent.replace("Template",strItem);
			sb.append(strNewContent+"\n");
		}
		FileIO.writeStringToFile(sb.toString(), fileOutput);

	}

}
