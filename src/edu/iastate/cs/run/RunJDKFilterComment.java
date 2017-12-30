package edu.iastate.cs.run;

import java.io.File;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.parser.CodeCommentExtractorVisitor;
import util.FileIO;

public class RunJDKFilterComment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folderIn =PathConstanct.PATH_OUTPUTFOLDER+"\\jdkRawComments\\";
		String folderOut = PathConstanct.PATH_OUTPUTFOLDER+"\\jdkFilterComments\\";;
	
		File fTrainIn = new File(folderIn);
		File fTrainOut = new File(folderOut);
		if (!fTrainOut.isDirectory()) {
			fTrainOut.mkdir();
		}
		

		StringBuilder sbComment = new StringBuilder();
		StringBuilder sbFile = new StringBuilder();
		StringBuilder sbLine = new StringBuilder();
		StringBuilder sbTestComment = new StringBuilder();
		StringBuilder sbTestFile = new StringBuilder();
		StringBuilder sbTestLine = new StringBuilder();

		File[] arrTrainIn = fTrainIn.listFiles();
		for (int i = 0; i < arrTrainIn.length; i++) {
			if (arrTrainIn[i].isDirectory()) {
				String[] arrComment = FileIO
						.readStringFromFile(arrTrainIn[i].getAbsolutePath() + File.separator + "comment.txt").trim()
						.split("\n");
				String[] arrLine = FileIO
						.readStringFromFile(arrTrainIn[i].getAbsolutePath() + File.separator + "line.txt").trim()
						.split("\n");
				String[] arrFile = FileIO
						.readStringFromFile(arrTrainIn[i].getAbsolutePath() + File.separator + "file.txt").trim()
						.split("\n");
				for (int j = 0; j < arrComment.length; j++) {
					boolean isUnrelatedComment = RunMixComment.isUnrelatedComment(arrComment[j]);
					if (isUnrelatedComment) {
						String inputComment = arrComment[j].trim().replaceAll("//", "").trim();
						sbComment.append(inputComment + "\n");
						sbFile.append(arrFile[j] + "\n");
						sbLine.append(arrLine[j] + "\n");
					}
				}

			}

		}

		FileIO.writeStringToFile(sbComment.toString(), folderOut + "comment.txt");
		FileIO.writeStringToFile(sbLine.toString(), folderOut + "line.txt");
		FileIO.writeStringToFile(sbFile.toString(), folderOut + "file.txt");
	}

}
