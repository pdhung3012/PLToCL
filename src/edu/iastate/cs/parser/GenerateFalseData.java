package edu.iastate.cs.parser;

import util.FileIO;
import util.NumberUtil;

public class GenerateFalseData {

	public String generateFalseString(String strInput){
		String[] arrInput=strInput.trim().split("\\s+");
		// create random string from input as follow:
		// pick up a position from string
		//duplicate that tokens from a random times.
		int randomNum=NumberUtil.randInt(0, arrInput.length-1);
		StringBuilder sbOut=new StringBuilder();
		
		for(int i=0;i<arrInput.length;i++){
		//	System.out.println(arrInput[i]);
			String strAppend=arrInput[i]+" ";
			if(i==randomNum){
				int times=NumberUtil.randInt(2, 8);
				for(int j=2;j<=times;j++){
					strAppend+=arrInput[i]+" ";
				}
			}
			sbOut.append(strAppend);
		}
		return sbOut.toString().trim();
	}
	
	public void generateFalseData(String fileInput,String fileOutputResult,String fileOutputLabel){
		String[] arrInput=FileIO.readStringFromFile(fileInput).trim().split("\n");
		StringBuilder sbText=new StringBuilder();
		StringBuilder sbLabel=new StringBuilder();
		
		for(int i=0;i<arrInput.length;i++){
			String strItem=arrInput[i].trim();
			String strFalse=generateFalseString(strItem);
			sbText.append(strItem+"\n");
			sbText.append(strFalse+"\n");
			sbLabel.append(true+"\n");
			sbLabel.append(false+"\n");
//			if(i==999){
//				break;
//			}
		}
		FileIO.writeStringToFile(sbText.toString(), fileOutputResult);
		FileIO.writeStringToFile(sbLabel.toString(), fileOutputLabel);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
