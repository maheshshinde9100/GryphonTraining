import java.util.*;
class Course{
    String courseName;
    int credits;
    Course(String courseName,int credits){
        this.courseName = courseName;
        this.credits = credits;
    }
}
class Professor{
    String profName;
    List<Course> courses;
    Professor(String profName){
        this.profName=profName;
        this.courses = new ArrayList<>();
    }

    void addCourse(Course course){
        courses.add(course);
    }

}
class Department{
    String deptName;
    List<Professor> professors;
    Department(String deptName){
        this.deptName = deptName;
        professors = new ArrayList<>();
    }
    void addProfessor(Professor professor){
        professors.add(professor);
    }
}
class University{
    String universityName;
    List<Department> departments;
    University(String universityName){
        this.universityName = universityName;
        this.departments = new ArrayList<>();
    }
    void addDepartment(Department department){
        departments.add(department);
    }
    void showUniversity(){
        System.out.println("\n---------------------------------");
        System.out.println("University : "+universityName);
        for(Department dept: departments){
            System.out.println("Department : "+dept.deptName);
            for(Professor prof: dept.professors){
                System.out.println("Professor : "+prof.profName);
                for(Course course: prof.courses){
                    System.out.println("Course : "+course.courseName);
                    System.out.println("Credits : "+course.credits);
                }
            }
        }
    }
}
public class Day3_3 {
    public static void main(String[] args) {
        Course java = new Course("JAVA_PROGRAMMING",4);
        Course dbms = new Course("DATABASE SYSTEM",3);
        Course ai = new Course("AI",5);
        Course ml = new Course("MACHINE LEARNING",5);
        Course ds = new Course("DATA SCIENCE",5);

//      creating professors
        Professor p1 = new Professor("Dr.Sharma");
        p1.addCourse(java);
        p1.addCourse(ml);
        Professor p2 = new Professor("Dr.Mehta");
        p2.addCourse(ds);
        p2.addCourse(ai);
        Professor p3 = new Professor("Dr.Gupta");
        p3.addCourse(dbms);
        p3.addCourse(ai);

//        create departments
        Department cs = new Department("COMPUTER_SCIENCE");
        cs.addProfessor(p1);
        cs.addProfessor(p2);

        Department it = new Department("INFORMATION_TECHNOLOGY");
        it.addProfessor(new Professor("Dr.VERMA"));

        Department aids = new Department("ARTIFICIAL INTELLIGENCE & DATA SCIENCE");
        aids.addProfessor(p3);

//        create universities and assign departments
        University university1 = new University("PUNE UNIVERSITY");
        university1.addDepartment(cs);
        university1.addDepartment(it);
        University university2 = new University("MUMBAI UNIVERSITY");
        university2.addDepartment(aids);

        university1.showUniversity();
        university2.showUniversity();
    }
}
