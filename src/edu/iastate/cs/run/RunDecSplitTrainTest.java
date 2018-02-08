package edu.iastate.cs.run;

import util.FileIO;

public class RunDecSplitTrainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fopInput="";
		String[] arrSigs=FileIO.readStringFromFile(fopInput+"sig.txt").split("\n");
		String[] arrOrigins=FileIO.readStringFromFile(fopInput+"origin.txt").split("\n");
		String[] arrTargets=FileIO.readStringFromFile(fopInput+"decompiled.txt").split("\n");
		
	}

}
