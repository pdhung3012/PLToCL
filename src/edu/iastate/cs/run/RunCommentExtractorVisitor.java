package edu.iastate.cs.run;

import java.io.File;
import java.util.ArrayList;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.parser.CodeCommentExtractorVisitor;

public class RunCommentExtractorVisitor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arrSource = findAllJavaFiles(PathConstanct.PATH_SOURCECODECOMMENT);
		System.out.println(arrSource.length);
		CodeCommentExtractorVisitor ccev = new CodeCommentExtractorVisitor(
				arrSource, PathConstanct.PATH_JDKSOURCE);
		ccev.loadSourceFiles();
		ccev.saveResultToFile(PathConstanct.PATH_OUTPUTFOLDER+"comment.txt", PathConstanct.PATH_OUTPUTFOLDER+"file.txt", PathConstanct.PATH_OUTPUTFOLDER+"line.txt");

	}

	public static void findFiles(File root, String extension,
			ArrayList<String> lstFilePaths) {
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isFile() && file.getName().endsWith(extension)) {
				lstFilePaths.add(file.getAbsolutePath());
			} else if (file.isDirectory()) {
				findFiles(file, extension, lstFilePaths);
			}
		}
	}

	public static String[] findAllJavaFiles(String inputPath) {
		ArrayList<String> lstRersult = new ArrayList<String>();
		File fileInput = new File(inputPath);
		findFiles(fileInput, "java", lstRersult);

		return convertToArrString(lstRersult);
	}

	public static String[] convertToArrString(ArrayList<String> lstInput) {
		String[] arrResult = new String[lstInput.size()];
		for (int i = 0; i < lstInput.size(); i++) {
			arrResult[i] = lstInput.get(i);
		}
		return arrResult;
	}

}
