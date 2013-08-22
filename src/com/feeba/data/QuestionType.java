package com.feeba.data;

public enum QuestionType {
	
	MULTIPLE_CHOICE,SINGLE_SELECTION,FREETEXT;
	
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
	
}
