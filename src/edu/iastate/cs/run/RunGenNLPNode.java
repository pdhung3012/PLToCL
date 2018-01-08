package edu.iastate.cs.run;

import org.eclipse.jdt.core.dom.Expression;

import util.FileIO;

public class RunGenNLPNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folderInput="src/edu/iastate/cs/entities/";
		String tagsInput="data/pennTreeTags.txt";
		String fileInput=folderInput+"Template.java";
		String strContent=FileIO.readStringFromFile(fileInput);
		String[] arrFiles=FileIO.readStringFromFile(tagsInput).trim().split("\n");
		
		for(int i=0;i<arrFiles.length;i++){
			String strItem=arrFiles[i].split("\\(")[0].trim();
			String strNewContent=strContent.replace("Template",strItem);
			FileIO.writeStringToFile(strNewContent,folderInput+strItem+".java");
			
		}
		
		
	}

}
