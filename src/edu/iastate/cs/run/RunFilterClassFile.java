package edu.iastate.cs.run;

import java.io.File;

public class RunFilterClassFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folderInput="/Users/hungphan/Downloads/src_classes/";
		String[] arrJavaFiles=RunCommentExtractorVisitor.findAllJavaFiles(folderInput);
		System.out.println("Handle "+arrJavaFiles.length);
		for(int i=0;i<arrJavaFiles.length;i++){
			File f=new File(arrJavaFiles[i]);
			f.delete();
		}
	}

}
