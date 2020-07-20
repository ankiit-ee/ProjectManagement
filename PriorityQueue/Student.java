package PriorityQueue;

public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;

    public Student(String trim, int parseInt) {
        this.name = trim;
        this.marks = parseInt;
    }


    @Override
    public int compareTo(Student student) {
        return marks-student.getMarks();
    }

    @Override
    public boolean equals(Object obj) {
        if(this.compareTo((Student) obj)==0)return true;
        return false;
    }

    public String getName() {
        return name;
    }

    public Integer getMarks() {
        return marks;
    }
    @Override
    public String toString(){
        return "Student{name="+ "'"+name+"'" + "," + " marks=" + marks +"}";
    }
}
