package edu.iastate.cs.entities.nlp;


/**
 * @author hungphan
 * WHNP - Wh-noun Phrase. Introduces a clause with an NP gap. May be null (containing the 0 complementizer) or lexical, containing some wh-word, e.g. who, which book, whose daughter, none of which, or how many leopards.
 */
public class WHNP extends NLPNode{
	public WHNP(){
		name="WHNP";
	}
}
