package edu.iastate.cs.exp;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

import util.FileIO;

public class FilterClifferComment {

	
	public void filterComment(String folderInput,String folderOutput){
		String[] arrComment=FileIO.readStringFromFile(folderInput+"comment.txt").trim().split("\n");
		String[] arrLine=FileIO.readStringFromFile(folderInput+"line.txt").trim().split("\n");
		String[] arrFile=FileIO.readStringFromFile(folderInput+"file.txt").trim().split("\n");
		
		File foOutput=new File(folderOutput);
		if(!foOutput.isDirectory()){
			foOutput.mkdir();
		}
		
		StringBuilder sbComment=new StringBuilder();
		StringBuilder sbLine=new StringBuilder();
		StringBuilder sbFile=new StringBuilder();
		HashMap<Integer,String> mapTab=new HashMap<Integer,String>();
		for(int i=0;i<arrComment.length;i++){
			// if the comment has tab, mix with the previous comment.
			if(arrComment[i].startsWith("//   ")){
				String startIndex=i-1+"";
				while(arrComment[i].startsWith("//   ")){
					i++;
				}
				String endIndex=(i-1)+"";
				mapTab.put(new Integer(startIndex),startIndex+":"+endIndex);				
			} 
			
		}
		
		//append with mix tab comment
		for(int i=0;i<arrComment.length;i++){
			if(mapTab.containsKey(i)){
				int startIndex=i;
				String strIndexes=mapTab.get(i);
				int endIndex=Integer.parseInt(strIndexes.split(":")[1]);
				sbComment.append(arrComment[startIndex].replaceFirst("// ","").trim()+" ");
				for(int j=startIndex+1;j<=endIndex;j++){
					sbComment.append(arrComment[j].replaceFirst("// ","").trim()+" ");
					i++;
				}
				sbComment.append("\n");
				sbFile.append(arrFile[startIndex]+"\n");
				sbLine.append(arrLine[startIndex]+"\n");
			} else{
				sbComment.append(arrComment[i].replaceFirst("// ","").trim()+"\n");
				sbFile.append(arrFile[i]+"\n");
				sbLine.append(arrLine[i]+"\n");
			}
		}
		
		//save to file
		FileIO.writeStringToFile(sbComment.toString(), folderOutput+"comment.txt");
		FileIO.writeStringToFile(sbLine.toString(), folderOutput+"line.txt");
		FileIO.writeStringToFile(sbFile.toString(), folderOutput+"file.txt");
		
		
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
