import java.io.*; 
import java.util.ArrayList;
 
public class GradeAnalyzer {
 
    public static void main(String[] args) {
        // Step 1: read scores from file
        // Step 2: calculate statistics
        // Step 3: write and print report

        ArrayList<Integer> scores = readScores("Module2/scores.txt");

        if (scores.isEmpty()) {
            System.out.println("No valid scores were found. Report not updated.");
            return;
        }

        double avg = calculateAverage(scores);

        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }

            if (score < lowest) {
                lowest = score;
            }
        }

        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        writeReport(scores, avg, highest, lowest,
                    countA, countB, countC, countD, countF,
                    "Module2/report.txt");
    } 
 
    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        // your code here
        ArrayList<Integer> scores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                try {
                    int score = Integer.parseInt(line);
                    scores.add(score);
                } catch (NumberFormatException e) {
                    System.out.println("Warning: Invalid score: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }

        return scores;
    }
 
    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        // your code here
        if (scores.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (int score : scores) {
            total += score;
        }

        return total / scores.size();
    } 
 
    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   int countA, int countB, int countC,
                                   int countD, int countF,
                                   String outputFile) {
        String[] reportLines = {
            "=== Grade Analysis Report ===",
            String.format("Total scores processed: %d", scores.size()),
            String.format("Average score:          %.2f", avg),
            String.format("Highest score:          %d", high),
            String.format("Lowest score:           %d", low),
            "Grade distribution:",
            String.format("  A (90-100):    %d", countA),
            String.format("  B (80-89):     %d", countB),
            String.format("  C (70-79):     %d", countC),
            String.format("  D (60-69):     %d", countD),
            String.format("  F (below 60):  %d", countF)
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String line : reportLines) {
                writer.write(line);
                writer.newLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error writing report: " + outputFile);
        }
    }
}
