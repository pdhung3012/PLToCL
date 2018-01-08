package edu.iastate.cs.run;

import java.io.IOException;
import java.util.ArrayList;

import util.FileIO;
import jd.core.Decompiler;
import jd.core.DecompilerException;

public class RunDecompiler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input="/Users/hungphan/Downloads/src/";
		String[] arrInput=RunCommentExtractorVisitor.findAllJavaFiles(input);
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<arrInput.length;i++){
			sb.append("javac "+arrInput[i]+"\n");
		}
		FileIO.writeStringToFile(sb.toString(), input+"script.sh");
		
	}

}
