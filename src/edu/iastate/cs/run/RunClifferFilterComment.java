package edu.iastate.cs.run;

import java.io.File;

import edu.iastate.cs.constant.PathConstanct;
import edu.iastate.cs.exp.FilterClifferComment;

public class RunClifferFilterComment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fInput=PathConstanct.PATH_OUTPUTFOLDER+"cliffer_grammars"+File.separator;
		String fOutput=PathConstanct.PATH_OUTPUTFOLDER+"cliffer_grammars"+File.separator+"filter"+File.separator;
		FilterClifferComment fcc=new FilterClifferComment();
		fcc.filterComment(fInput, fOutput);
	}

}
