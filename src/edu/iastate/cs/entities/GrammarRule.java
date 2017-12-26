package edu.iastate.cs.entities;

import java.util.ArrayList;

public class GrammarRule {

	private String lhs;
	private ArrayList<String> lstRhs;
	
	
	
	public String getLhs() {
		return lhs;
	}



	public void setLhs(String lhs) {
		this.lhs = lhs;
	}



	public ArrayList<String> getLstRhs() {
		return lstRhs;
	}



	public void setLstRhs(ArrayList<String> lstRhs) {
		this.lstRhs = lstRhs;
	}


	public String print(){
		String strRhs="";
		for(String strItem:lstRhs){
			strRhs+=strItem+" ";
		}
		strRhs=strRhs.trim();
		return (lhs+" : "+strRhs);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}