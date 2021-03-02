package sample;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    public TableColumn Midterm;
    public TableView tableView;
    public TableColumn Assignments;
    public TableColumn Mark;
    public TableColumn FinalExam;
    public TableColumn LetterGrade;
    public TableColumn StudentID;

    public void initialize() {
        StudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        Midterm.setCellValueFactory(new PropertyValueFactory<>("Midterm"));
        Assignments.setCellValueFactory(new PropertyValueFactory<>("Assignments"));
        FinalExam.setCellValueFactory(new PropertyValueFactory<>("FinalExam"));
        Mark.setCellValueFactory(new PropertyValueFactory<>("Mark"));
        LetterGrade.setCellValueFactory(new PropertyValueFactory<>("LetterGrade"));

        tableView.setItems(DataSource.getAllMarks());
    }

}
