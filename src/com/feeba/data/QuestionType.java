package com.feeba.data;

public enum QuestionType {
	
	MULTIPLE_CHOICE,SINGLE_SELECTION,FREETEXT;
	
	/*
	 * returns a String with an equivalent HTML-InputType
	 */
	public String getJsonText() {
		
		switch(this) {
		
		case MULTIPLE_CHOICE:
			return "checkbox";
		case SINGLE_SELECTION:
			return "radio";
		case FREETEXT:
			return "text";
		default:
			return"";
		}
	}
	
	public String toString() {
		
switch(this) {
		
		case MULTIPLE_CHOICE:
			return "Multiple Choice";
		case SINGLE_SELECTION:
			return "Einfachauswahl";
		case FREETEXT:
			return "Freitext";
		default:
			return"";
		}
		
	}
	
}
