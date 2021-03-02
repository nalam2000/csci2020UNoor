package sample;

public class StudentRecord {

    private String StudentID;
    private float Midterm;
    private float Assignments;
    private float FExam;
    private float Mark;
    private String LGrade;

    public StudentRecord(String StudentID, float Assignments, float Midterm, float FExam) {
        this.StudentID = StudentID;
        this.Assignments = Assignments;
        this.Midterm = Midterm;
        this.FExam = FExam;
    }
    public String getStudentID(){
        return StudentID;
    }

    public float getMidterm(){
        return Midterm;
    }

    public float getAssignments(){
        return Assignments;
    }

    public float getFinalExam(){
        return FExam;
    }

    public float getMark(){
        Mark = (Assignments * 0.2f + Midterm * 0.3f + FExam * 0.5f);
        return Mark;
    }

    public String getLetterGrade(){
        LGrade = "";
        if (Mark <= 50) {
            return LGrade = "F";
        } else if (Mark <= 60) {
            return LGrade = "D";
        } else if (Mark <= 70) {
            return LGrade ="C";
        } else if (Mark <= 80) {
            return LGrade ="B";
        } else if (Mark > 80.1){
            return LGrade ="A";
        }
        return LGrade;
    }


}

