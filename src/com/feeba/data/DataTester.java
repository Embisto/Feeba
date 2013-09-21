package com.feeba.data;

import com.feeba.server.ServerGUI;


public class DataTester {

	public static void main(String[] args) {
		
		Survey s1 = new Survey("Fragebogen 1");
		Question ques1 = new Question("Alter", "Wie alt bist du?", QuestionType.FREETEXT);
		Question ques2 = new Question("Gesang","hast du schon einmal gesungen?",QuestionType.SINGLE_SELECTION);
		Question ques3 = new Question("Mathe", "Wieviel is 2*3?",QuestionType.MULTIPLE_CHOICE);
		Question ques4 = new Question("Architektur", "Haus?", QuestionType.FREETEXT);
		Question ques5 = new Question("Tiere", "Elefant?", QuestionType.FREETEXT);
		ques3.addChoice("2", "3","6","9");
		ques2.addChoice("Ja","Nein");
		s1.addQuestion(ques1,ques2,ques3,ques4,ques5);
		s1.moveItemToPosition(1, 3);
		DataController dc = new DataController();
		ServerGUI.main(null);

	}

}
