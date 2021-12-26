package be.odisee.data;

import be.odisee.domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ExamDataReader {

    private final File eFile;
    private final File sFile;
    private List<Student> students;
    private List<Exam> exams;
    private List<TimeSlot> timeslots;
    private final String eFileName;
    private final String sFileName;

    public ExamDataReader(String examFileName, String studentsFileName) {
        eFile = new File(examFileName);
        sFile = new File(studentsFileName);
        this.eFileName = eFile.getName();
        this.sFileName = sFile.getName();
        readTimeSlots();
        readExams();
        readStudents();
    }

    private void readTimeSlots() {
        if (eFileName.endsWith(".crs")) {
            File timeSlotFile = new File("benchmarks/number_of_timeslots.txt");
            Scanner timeslotScanner;
            try {
                timeslotScanner = new Scanner(timeSlotFile);
                while (timeslotScanner.hasNextLine()) {
                    String nextLine = timeslotScanner.nextLine();
                    if (nextLine.startsWith(eFileName)) {
                        Scanner sc = new Scanner(nextLine);
                        sc.useDelimiter(":");
                        sc.next();
                        int numberOfTimeSlots = sc.nextInt();
                        timeslots = new ArrayList<>();
                        for (int i = 0; i < numberOfTimeSlots; i++) {
                            timeslots.add(new TimeSlot(i));
                        }
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void readExams() {
        Scanner scanner;
        try {
            scanner = new Scanner(eFile);
            this.exams = new LinkedList<Exam>();
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                Scanner sc = new Scanner(nextLine);
                sc.useDelimiter(" ");
                int examID = sc.nextInt();
                int aantal = sc.nextInt();
                Exam exam = new Exam(examID);
                Set<Student> sid = new TreeSet<>();
                exam.setStudents(sid);
                this.exams.add(exam);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void readStudents() {
        if (sFileName.endsWith(".stu")) {
            Scanner scanner;
            students = new LinkedList<Student>();
            try {
                scanner = new Scanner(sFile);
                int teller = 0;
                while (scanner.hasNextLine()) {
                    teller++;
                    String nextLine = scanner.nextLine();
                    Scanner sc = new Scanner(nextLine);
                    sc.useDelimiter(" ");
                    Student student = new Student(teller);
                    Set<Exam> examIDList = new TreeSet<>();

                    while (sc.hasNext()) {
                        Exam ex = new Exam(sc.nextInt());
                        examIDList.add(ex);
                    }
                    for (Exam e : examIDList) {
                        Exam exam = exams.get(e.getId() - 1);
                        exam.addSID(student);
                    }
                    student.setExams(examIDList);
                    students.add(student);
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<TimeSlot> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(List<TimeSlot> timeslots) {
        this.timeslots = timeslots;
    }
}
