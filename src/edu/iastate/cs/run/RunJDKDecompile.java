package edu.iastate.cs.run;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import util.FileIO;
import jd.core.Decompiler;
import jd.core.DecompilerException;

public class RunJDKDecompile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Decompiler decompile=new Decompiler();
		Map<String,String> mapDecompile=new HashMap<>();
		String folderOut="/Users/hungphan/git/jdk-jar/out/";
		try {
			///Library/Java/JavaVirtualMachines/jdk1.8.0_141.jdk/Contents/Home/jre/lib/resources.jar
			//jar cvf mylib.jar src_classes
			mapDecompile= decompile.decompile("/Users/hungphan/Downloads/myLib.jar");
		//	System.out.println(mapDecompile.toString());
			for(String itemKey:mapDecompile.keySet()){
				String itemValue=mapDecompile.get(itemKey);
				if(itemValue!=null){
					FileIO.writeStringToFile(itemValue, folderOut+itemKey.replaceAll(".","/")+".txt");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
