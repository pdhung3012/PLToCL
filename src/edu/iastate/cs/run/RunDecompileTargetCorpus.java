package edu.iastate.cs.run;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.visitor.NaiveASTVisitor;

public class RunDecompileTargetCorpus {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folderInput="/Users/hungphan/git/jdk-jar/decompiled/java/";
		String folderOutput="/Users/hungphan/git/jdk-jar/";
		String[] arrInputJava=RunCommentExtractorVisitor.findAllJavaFiles(folderInput);
		System.out.println("Get data from "+arrInputJava.length);
		NaiveASTVisitor navisitor=new NaiveASTVisitor(arrInputJava,PathConstanct.PATH_JDKSOURCE);
		navisitor.loadSourceFiles();
		System.out.println("End load");
		navisitor.saveResultToFile(folderOutput+"out_sig.txt", folderOutput+"out_token.txt");
	}

}
