import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String studentId;
    private String name;
    private int age;

    public Student(String studentId, String name, int age) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + name + ", Age: " + age;
    }
}

class Course {
    private String courseId;
    private String courseName;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId + ", Course Name: " + courseName;
    }
}

class Grade {
    private Student student;
    private Course course;
    private double grade;

    public Grade(Student student, Course course, double grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return student.getName() + " got " + grade + " in " + course.getCourseName();
    }
}

class StudentInformationSystem {
    private List<Student> students;
    private List<Course> courses;
    private List<Grade> grades;

    public StudentInformationSystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        grades = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void enrollStudent(Student student, Course course, double grade) {
        if (!students.contains(student) || !courses.contains(course)) {
            System.out.println("Student or Course does not exist.");
            return;
        }
        grades.add(new Grade(student, course, grade));
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public List<Grade> getGradesByStudent(Student student) {
        List<Grade> studentGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getStudent().equals(student)) {
                studentGrades.add(grade);
            }
        }
        return studentGrades;
    }
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StudentInformationSystem sis = new StudentInformationSystem();

        addStudents(sis);
        addCourses(sis);
        enrollStudents(sis);
        generateReports(sis);

        scanner.close();
    }

    private static void addStudents(StudentInformationSystem sis) {
        System.out.println("Enter student details (ID, Name, Age):");
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter student " + (i + 1) + " details:");
            String id = scanner.next();
            String name = scanner.next();
            int age = scanner.nextInt();
            sis.addStudent(new Student(id, name, age));
        }
    }

    private static void addCourses(StudentInformationSystem sis) {
        System.out.println("Enter course details (ID, Name):");
        for (int i = 0; i < 2; i++) {
            System.out.println("Enter course " + (i + 1) + " details:");
            String id = scanner.next();
            String name = scanner.next();
            sis.addCourse(new Course(id, name));
        }
    }

    private static void enrollStudents(StudentInformationSystem sis) {
        System.out.println("Enroll students in courses (StudentID, CourseID, Grade):");
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter enrollment details for student " + (i + 1) + ":");
            String studentId = scanner.next();
            String courseId = scanner.next();
            double grade = scanner.nextDouble();
            Student student = findStudent(sis, studentId);
            Course course = findCourse(sis, courseId);
            if (student != null && course != null) {
                sis.enrollStudent(student, course, grade);
            } else {
                System.out.println("Student or Course not found.");
                i--;
            }
        }
    }

    private static void generateReports(StudentInformationSystem sis) {
        System.out.println("Enter student ID to generate report:");
        String studentId = scanner.next();
        Student student = findStudent(sis, studentId);
        if (student != null) {
            System.out.println("Report for " + student.getName() + ":");
            List<Grade> studentGrades = sis.getGradesByStudent(student);
            if (studentGrades.isEmpty()) {
                System.out.println("No grades found for this student.");
            } else {
                for (Grade grade : studentGrades) {
                    System.out.println(grade);
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student findStudent(StudentInformationSystem sis, String studentId) {
        for (Student student : sis.getStudents()) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourse(StudentInformationSystem sis, String courseId) {
        for (Course course : sis.getCourses()) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }
}
