# 🎓 Smart Student Management System

A console-based Java application to store, search, delete, and sort students using efficient data structures and recursive algorithms.

---

## 📋 Features

- **Add Student** — Insert a new student with name, age, and marks; grade is auto-calculated
- **Delete Student** — Remove a student from the list using their ID
- **Search by ID** — Find and display a specific student using their unique ID
- **Sort by Marks** — Sort all students from highest to lowest marks using Recursive Merge Sort
- **Display All** — View all students in a formatted table with grades
- **Preloaded Data** — 5 sample students are loaded on startup for immediate testing

---

## 🧠 Concepts Used

| Concept | Usage |
|--------|-------|
| LinkedList | Stores students; efficient for insert and delete operations |
| Recursive Merge Sort | Sorts students by marks using divide-and-conquer recursion |
| Linear Search | Searches for a student by ID |
| Inner Class | `Student` class defined inside main class |
| Iterator | Used for safe deletion while iterating the LinkedList |

---

## 📁 File Structure

```
3_StudentManagement/
├── src/
│   └── StudentManagement.java   ← Main source file
├── out/                         ← Compiled .class files (auto-generated)
├── run.sh                       ← Run script for Linux/Mac
└── run.bat                      ← Run script for Windows
```

---

## ▶️ How to Run

### Linux / Mac
```bash
cd 3_StudentManagement
bash run.sh
```

### Windows
```
cd 3_StudentManagement
run.bat
```

### Manual (Any OS)
```bash
javac -d out src/StudentManagement.java
java -cp out StudentManagement
```

---

## 🖥️ Sample Output

```
╔══════════════════════════════════════════╗
║  🎓  SMART STUDENT MANAGEMENT SYSTEM  🎓 ║
╚══════════════════════════════════════════╝
  ✅ 5 sample students loaded.

  Enter choice: 4   ← Sort by Marks

  ✅ Students sorted by marks (Highest First):

  ┌───────┬──────────────────────┬─────┬────────┬───────┐
  │  ID   │  Name                │ Age │ Marks  │ Grade │
  ├───────┼──────────────────────┼─────┼────────┼───────┤
  │ 2     │ Priya Sharma         │ 21  │ 92.00  │ A+    │
  │ 1     │ Arjun Kumar          │ 20  │ 88.50  │ A     │
  │ 3     │ Rohit Singh          │ 19  │ 74.00  │ B     │
  │ 5     │ Karthik Raj          │ 20  │ 65.00  │ C     │
  │ 4     │ Sneha Patel          │ 22  │ 45.50  │ F     │
  └───────┴──────────────────────┴─────┴────────┴───────┘
```

---

## 📊 Grade Scale

| Marks | Grade |
|-------|-------|
| 90–100 | A+ |
| 80–89  | A  |
| 70–79  | B  |
| 60–69  | C  |
| 50–59  | D  |
| Below 50 | F |

---

## ⚙️ Requirements

- Java JDK 17 or above
- Terminal / Command Prompt

---

## 📌 Notes

- Data is stored in memory only (resets on exit)
- Student IDs are unique and auto-incremented
- Merge Sort is implemented **recursively** with divide-and-conquer approach
- Marks must be between 0 and 100
