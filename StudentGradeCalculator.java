import javax.swing.JOptionPane;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        // Define the subjects
        String[] subjects = {
            "Operating Systems",
            "AIML",
            "Discrete Mathematics",
            "Computer Networks",
            "Web Frameworks",
            "Software Engineering"
        };

        int numSubjects = subjects.length;
        int totalMarks = 0;
        int failedSubjectsCount = 0;
        StringBuilder failedSubjects = new StringBuilder();

        // Input marks for each subject and convert them to 80 marks scale
        for (int i = 0; i < numSubjects; i++) {
            int mark = -1;
            while (true) {
                String markStr = JOptionPane.showInputDialog(null, "Enter marks obtained in " + subjects[i] + " (out of 100):");
                try {
                    mark = Integer.parseInt(markStr);
                    if (mark > 100) {
                        JOptionPane.showMessageDialog(null, "Marks can't be more than 100", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Enter the Marks only in Numbers", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Convert the mark to a scale of 80
            int convertedMark = (mark * 80) / 100;
            int totalSubjectMark = convertedMark + 20; // Add 20 internal marks
            totalMarks += totalSubjectMark;

            // Check if the subject is failed (assuming passing marks is 35 out of 100, converted to 28 out of 80)
            if (totalSubjectMark < 50) {
                failedSubjectsCount++;
                failedSubjects.append(subjects[i]).append(" (").append(totalSubjectMark).append(")\n");
            }
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;

        // Determine the grade based on average percentage
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        // Prepare the results string
        StringBuilder results = new StringBuilder();
        results.append("Total Marks: ").append(totalMarks).append(" out of ").append(numSubjects * 100).append("\n");
        results.append("Average Percentage: ").append(String.format("%.2f", averagePercentage)).append("%\n");
        results.append("Grade: ").append(grade).append("\n");
        results.append("Internal Marks for all Subjects: 20\n");

        // Add information about failed subjects
        if (failedSubjectsCount > 0) {
            results.append("Number of Subjects Failed: ").append(failedSubjectsCount).append("\n");
            results.append("Failed Subjects are:\n").append(failedSubjects.toString());
        } else {
            results.append("Congratulations! You passed all subjects.\n");
        }

        // Display the results
        JOptionPane.showMessageDialog(null, results.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);
    }
}
