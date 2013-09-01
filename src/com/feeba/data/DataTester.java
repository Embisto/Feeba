package com.feeba.data;


public class DataTester {

	public static void main(String[] args) {
		
		Survey s1 = new Survey("Fragebogen 1");
		Question ques1 = new Question("Frage 1", "Wie alt bist du?", QuestionType.FREETEXT);
		Question ques2 = new Question("Frage 2","hast du schon einmal gesungen?",QuestionType.SINGLE_SELECTION);
		Question ques3 = new Question("Frage 3", "Wieviel is 2*3?",QuestionType.MULTIPLE_CHOICE);
		Question ques4 = new Question("Frage 4", "Haus?", QuestionType.FREETEXT);
		Question ques5 = new Question("Frage 5", "Elefant?", QuestionType.FREETEXT);
		ques3.addChoice("2", "3","6","9");
		ques2.addChoice("Ja","Nein");
		s1.addQuestion(ques1,ques2,ques3,ques4,ques5);
		s1.moveItemToPosition(1, 3);
		DataController dc = new DataController();
		dc.saveAsJson(s1);
		System.out.println(dc.loadFromJson().toString());

	}

}
