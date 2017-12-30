package edu.iastate.cs.run;

import java.io.File;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.parser.CodeCommentExtractorVisitor;

public class RunJDKExtractComment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folderInput="C:\\sourceJDK\\src\\";
		String folderOutput=PathConstanct.PATH_OUTPUTFOLDER+"jdkComments\\";
		String[] arrSource = RunCommentExtractorVisitor.findAllJavaFiles(folderInput);
		System.out.println(arrSource.length);
		CodeCommentExtractorVisitor ccev = new CodeCommentExtractorVisitor(
				arrSource, folderInput);
		ccev.loadSourceFiles();
		ccev.saveResultToFile(folderOutput+"comment.txt", folderOutput+"file.txt", folderOutput+"line.txt");

	}

}
