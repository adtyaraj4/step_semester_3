import java.util.*;

public class Problem3_StudentMarksGrid {
    public static void main(String[] args) {
        String[] subjects = {"Math", "Sci", "Eng"};
        String[] students = {"Anu", "Ravi", "Meena"};
        int[][] marks = {
            {80, 90, 70},
            {60, 85, -1},  // -1 means missing
            {95, 75, 88}
        };

        for (int i = 0; i < students.length; i++) {
            int total = 0;
            for (int mark : marks[i]) {
                if (mark >= 0) total += mark;
            }
            System.out.println("Total " + students[i] + " " + total);
        }

        for (int j = 0; j < subjects.length; j++) {
            int bestMark = -1;
            String topper = "N/A";
            for (int i = 0; i < students.length; i++) {
                if (marks[i][j] > bestMark) {
                    bestMark = marks[i][j];
                    topper = students[i];
                }
            }
            if (bestMark >= 0)
                System.out.println("Topper " + subjects[j] + " " + topper + " " + bestMark);
        }
    }
}
