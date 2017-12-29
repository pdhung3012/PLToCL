package edu.iastate.cs.run;

import java.io.File;

import edu.iastate.cs.constant.PathConstanct;
import util.FileIO;

public class RunMixComment {

	public static boolean isUnrelatedComment(String comment) {
		boolean result = true;
		comment = comment.trim();
		if (comment.equals("// TODO Auto-generated method stub") || comment.contains("$NON-NLS-1$")
				|| comment.startsWith("// *") || comment.startsWith("// ---------------") || comment.startsWith("// @")
				|| comment.startsWith("// http://") || comment.startsWith("//$ANTLR") || comment.contains("{")
				|| comment.contains("}") || comment.endsWith(";")) {

			result = false;
		}
		comment = comment.replaceFirst("// ", "").trim();
		if (comment.contains(".g:")||comment.contains("$ANTLR")) {
			result = false;
		}
		if (comment.replaceFirst("// ", "").trim().split("\\s+").length <= 2) {
			result = false;
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folderTrainingIn = "C:\\gitCommentOutput\\";
		String folderTrainingOut = "data/apacheComments/";
		String folderTestOut = "data/eclipseComments/";

		File fTrainIn = new File(folderTrainingIn);
		File fTrainOut = new File(folderTrainingOut);
		File fTestOut = new File(folderTestOut);
		if (!fTrainOut.isDirectory()) {
			fTrainOut.mkdir();
		}
		if (!fTestOut.isDirectory()) {
			fTestOut.mkdir();
		}

		StringBuilder sbComment = new StringBuilder();
		StringBuilder sbFile = new StringBuilder();
		StringBuilder sbLine = new StringBuilder();
		StringBuilder sbTestComment = new StringBuilder();
		StringBuilder sbTestFile = new StringBuilder();
		StringBuilder sbTestLine = new StringBuilder();

		File[] arrTrainIn = fTrainIn.listFiles();
		for (int i = 0; i < arrTrainIn.length; i++) {
			if (arrTrainIn[i].isDirectory() && arrTrainIn[i].getName().startsWith("apache_")) {
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
					boolean isUnrelatedComment = isUnrelatedComment(arrComment[j]);
					if (isUnrelatedComment) {
						String inputComment = arrComment[j].trim().replaceAll("//", "").trim();
						sbComment.append(inputComment + "\n");
						sbFile.append(arrFile[j] + "\n");
						sbLine.append(arrLine[j] + "\n");
					}
				}

			} else if (arrTrainIn[i].isDirectory() && arrTrainIn[i].getName().startsWith("eclipse_")) {
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
					boolean isUnrelatedComment = isUnrelatedComment(arrComment[j]);
					if (isUnrelatedComment) {
						String inputComment = arrComment[j].trim().replaceAll("//", "").trim();
						sbTestComment.append(inputComment + "\n");
						sbTestFile.append(arrFile[j] + "\n");
						sbTestLine.append(arrLine[j] + "\n");
					}
				}

			}

		}

		FileIO.writeStringToFile(sbComment.toString(), folderTrainingOut + "comment.txt");
		FileIO.writeStringToFile(sbLine.toString(), folderTrainingOut + "line.txt");
		FileIO.writeStringToFile(sbFile.toString(), folderTrainingOut + "file.txt");
		FileIO.writeStringToFile(sbTestComment.toString(), folderTestOut + "comment.txt");
		FileIO.writeStringToFile(sbTestLine.toString(), folderTestOut + "line.txt");
		FileIO.writeStringToFile(sbTestFile.toString(), folderTestOut + "file.txt");
	}

}
