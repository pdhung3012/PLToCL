package edu.iastate.cs.run;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.parser.CodeCommentExtractorVisitor;

public class RunCEVMultipleProjects {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pathInput="C:\\gitProjects\\";
		String pathOutput="C:\\gitCommentOutput\\";
		File folderProject=new File(pathInput);
		File[] arrProjects=folderProject.listFiles();
		
		for(int i=0;i<arrProjects.length;i++){
			boolean isRunSuccess=false;
			if(!arrProjects[i].isDirectory()){
				continue;
			}
			try{

				String[] arrSource = RunCommentExtractorVisitor.findAllJavaFiles(arrProjects[i].getAbsolutePath()+File.separator);
				//	System.out.println(arrSource.length);
					CodeCommentExtractorVisitor ccev = new CodeCommentExtractorVisitor(
							arrSource, PathConstanct.PATH_JDKSOURCE);
					ccev.loadSourceFiles();
					String fopOutFolder=pathOutput+arrProjects[i].getName()+File.separator;
					File fout=new File(fopOutFolder);
					if(!fout.isDirectory()){
						fout.mkdir();
					}
					ccev.saveResultToFile(fout.getAbsolutePath()+File.separator+"comment.txt", fout.getAbsolutePath()+File.separator+"file.txt", fout.getAbsolutePath()+File.separator+"line.txt");

				isRunSuccess=true;
			}catch(Exception ex){
				//ex.printStackTrace();
			}
			System.out.println("Parse project "+i+" "+arrProjects[i]+" "+isRunSuccess);
			
		}
	}

}
