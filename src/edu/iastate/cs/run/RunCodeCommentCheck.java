package edu.iastate.cs.run;

import java.io.File;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;

import edu.iastate.cs.constant.PathConstanct;
import util.FileIO;
import util.JavaASTUtil;

public class RunCodeCommentCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fpInput = PathConstanct.PATH_OUTPUTFOLDER + "checkComment.txt";
		String folderOutput = PathConstanct.PATH_OUTPUTFOLDER + File.separator
				+ "checkCmtResult" + File.separator;
		String[] arrSentences = FileIO.readStringFromFile(fpInput).trim()
				.split("\n");
		File fOut = new File(folderOutput);
		if (!fOut.isDirectory()) {
			fOut.mkdir();
		}

		for (int i = 0; i < arrSentences.length; i++) {
			String strItem = arrSentences[i].replaceFirst("//", "").trim();
			StringBuilder sb = new StringBuilder();
			ASTNode node = JavaASTUtil.parseSource(strItem,
					ASTParser.K_STATEMENTS);
			boolean isCheckComment = node.toString().trim().equals("{\n}");
			String strContent = isCheckComment
					+ "\n"
					+ node.toString() + "\n";
			FileIO.writeStringToFile(strContent + "", folderOutput
					+ File.separator + (i + 1) + ".txt");
		}

	}

}
