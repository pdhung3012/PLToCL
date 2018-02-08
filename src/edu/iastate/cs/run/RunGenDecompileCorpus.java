package edu.iastate.cs.run;

import java.util.HashMap;

import util.FileIO;

public class RunGenDecompileCorpus {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String srcPath="/Users/hungphan/git/jdk-jar/source";
		String targetPath="/Users/hungphan/git/jdk-jar/decompiled";
		String folderInput="/Users/hungphan/git/jdk-jar/";
		String[] arrSourceSig=FileIO.readStringFromFile(folderInput+"source_sig.txt").trim().split("\n");
		String[] arrSourceToken=FileIO.readStringFromFile(folderInput+"source_token.txt").trim().split("\n");
		String[] arrOutSig=FileIO.readStringFromFile(folderInput+"out_sig.txt").trim().split("\n");
		String[] arrOutToken=FileIO.readStringFromFile(folderInput+"out_token.txt").trim().split("\n");
		HashMap<String,Integer> mapSource=new HashMap<String,Integer>();
		for(int i=0;i<arrSourceSig.length;i++){
			mapSource.put(arrSourceSig[i].replaceAll(srcPath, ""), i);
		}
		
		StringBuilder sbSig=new StringBuilder();
		StringBuilder sbOrigin=new StringBuilder();
		StringBuilder sbDecompiled=new StringBuilder();
		for(int i=0;i<arrOutSig.length;i++){
			if(mapSource.containsKey(arrOutSig[i].replaceAll(targetPath, ""))){
				int lineSource=mapSource.get(arrOutSig[i].replaceAll(targetPath, ""));
				sbSig.append(arrOutSig[i].replaceAll(targetPath, "")+"\n");
				sbOrigin.append(arrSourceToken[lineSource]+"\n");
				sbDecompiled.append(arrOutToken[i]+"\n");
			}
		}
		
		FileIO.writeStringToFile(sbSig.toString(),folderInput+"sig.txt");
		FileIO.writeStringToFile(sbOrigin.toString(),folderInput+"origin.txt");
		FileIO.writeStringToFile(sbDecompiled.toString(),folderInput+"decompiled.txt");
		
	}

}
