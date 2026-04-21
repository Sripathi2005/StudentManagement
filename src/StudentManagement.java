import java.util.*;

public class StudentManagement {

    static class Student {
        int id;
        String name;
        int age;
        double marks;
        String grade;

        Student(int id, String name, int age, double marks) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.marks = marks;
            this.grade = calculateGrade(marks);
        }

        static String calculateGrade(double marks) {
            if (marks >= 90) return "A+";
            else if (marks >= 80) return "A";
            else if (marks >= 70) return "B";
            else if (marks >= 60) return "C";
            else if (marks >= 50) return "D";
            else return "F";
        }

        void display() {
            System.out.printf("  │ %-5d │ %-20s │ %-3d │ %-6.2f │ %-5s │%n",
                    id, name, age, marks, grade);
        }
    }

    // Using LinkedList for insert/delete efficiency
    static LinkedList<Student> students = new LinkedList<>();
    static int idCounter = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  🎓  SMART STUDENT MANAGEMENT SYSTEM  🎓 ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // Preload some students
        preload();

        boolean running = true;
        while (running) {
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│            MAIN MENU            │");
            System.out.println("├─────────────────────────────────┤");
            System.out.println("│  1. Add Student                 │");
            System.out.println("│  2. Delete Student              │");
            System.out.println("│  3. Search Student by ID        │");
            System.out.println("│  4. Sort Students by Marks      │");
            System.out.println("│  5. Display All Students        │");
            System.out.println("│  6. Exit                        │");
            System.out.println("└─────────────────────────────────┘");
            System.out.print("  Enter choice: ");

            int choice = -1;
            try { choice = Integer.parseInt(sc.nextLine().trim()); }
            catch (Exception e) { System.out.println("  ❌ Invalid input!"); continue; }

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> deleteStudent(sc);
                case 3 -> searchById(sc);
                case 4 -> sortByMarks();
                case 5 -> displayAll();
                case 6 -> { System.out.println("\n  👋 Goodbye!"); running = false; }
                default -> System.out.println("  ❌ Invalid choice!");
            }
        }
        sc.close();
    }

    static void preload() {
        students.add(new Student(idCounter++, "Arjun Kumar", 20, 88.5));
        students.add(new Student(idCounter++, "Priya Sharma", 21, 92.0));
        students.add(new Student(idCounter++, "Rohit Singh", 19, 74.0));
        students.add(new Student(idCounter++, "Sneha Patel", 22, 45.5));
        students.add(new Student(idCounter++, "Karthik Raj", 20, 65.0));
        System.out.println("  ✅ 5 sample students loaded.");
    }

    static void addStudent(Scanner sc) {
        System.out.println("\n━━━━━━━━━━  ADD STUDENT  ━━━━━━━━━━");
        System.out.print("  Enter Name  : ");
        String name = sc.nextLine().trim();
        System.out.print("  Enter Age   : ");
        int age;
        try { age = Integer.parseInt(sc.nextLine().trim()); }
        catch (Exception e) { System.out.println("  ❌ Invalid age!"); return; }

        System.out.print("  Enter Marks (0-100): ");
        double marks;
        try { marks = Double.parseDouble(sc.nextLine().trim()); }
        catch (Exception e) { System.out.println("  ❌ Invalid marks!"); return; }

        if (marks < 0 || marks > 100) { System.out.println("  ❌ Marks must be 0-100!"); return; }

        Student s = new Student(idCounter++, name, age, marks);
        students.add(s);

        System.out.println("\n  ✅ Student Added!");
        System.out.printf("  ID: %d | Name: %s | Marks: %.2f | Grade: %s%n",
                s.id, s.name, s.marks, s.grade);
    }

    static void deleteStudent(Scanner sc) {
        System.out.println("\n━━━━━━━━━━  DELETE STUDENT  ━━━━━━━━━━");
        System.out.print("  Enter Student ID to delete: ");
        int id;
        try { id = Integer.parseInt(sc.nextLine().trim()); }
        catch (Exception e) { System.out.println("  ❌ Invalid ID!"); return; }

        Iterator<Student> iter = students.iterator();
        while (iter.hasNext()) {
            Student s = iter.next();
            if (s.id == id) {
                iter.remove();
                System.out.println("  ✅ Student '" + s.name + "' (ID: " + id + ") deleted.");
                return;
            }
        }
        System.out.println("  ❌ Student with ID " + id + " not found!");
    }

    static void searchById(Scanner sc) {
        System.out.println("\n━━━━━━━━━━  SEARCH STUDENT  ━━━━━━━━━━");
        System.out.print("  Enter Student ID: ");
        int id;
        try { id = Integer.parseInt(sc.nextLine().trim()); }
        catch (Exception e) { System.out.println("  ❌ Invalid ID!"); return; }

        // Linear search
        for (Student s : students) {
            if (s.id == id) {
                System.out.println("\n  ✅ Student Found!");
                printTableHeader();
                s.display();
                printTableFooter();
                return;
            }
        }
        System.out.println("  ❌ No student found with ID: " + id);
    }

    static void sortByMarks() {
        if (students.isEmpty()) { System.out.println("  ❌ No students to sort!"); return; }

        // Recursive merge sort
        List<Student> sorted = mergeSort(new ArrayList<>(students));
        students = new LinkedList<>(sorted);

        System.out.println("\n  ✅ Students sorted by marks (Highest First):");
        displayAll();
    }

    // Recursive Merge Sort
    static List<Student> mergeSort(List<Student> list) {
        if (list.size() <= 1) return list;

        int mid = list.size() / 2;
        List<Student> left = mergeSort(new ArrayList<>(list.subList(0, mid)));
        List<Student> right = mergeSort(new ArrayList<>(list.subList(mid, list.size())));
        return merge(left, right);
    }

    static List<Student> merge(List<Student> left, List<Student> right) {
        List<Student> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).marks >= right.get(j).marks)
                result.add(left.get(i++));
            else
                result.add(right.get(j++));
        }
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }

    static void displayAll() {
        System.out.println("\n━━━━━━━━━━  ALL STUDENTS  ━━━━━━━━━━");
        if (students.isEmpty()) { System.out.println("  📭 No students found."); return; }
        printTableHeader();
        for (Student s : students) s.display();
        printTableFooter();
        System.out.println("  Total students: " + students.size());
    }

    static void printTableHeader() {
        System.out.println("  ┌───────┬──────────────────────┬─────┬────────┬───────┐");
        System.out.println("  │  ID   │  Name                │ Age │ Marks  │ Grade │");
        System.out.println("  ├───────┼──────────────────────┼─────┼────────┼───────┤");
    }

    static void printTableFooter() {
        System.out.println("  └───────┴──────────────────────┴─────┴────────┴───────┘");
    }
}
