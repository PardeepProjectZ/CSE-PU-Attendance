package my.pu.cse_pu_attendance2;

public class DataEntryStudent {
//    ArrayList<String> student_name = new ArrayList<>();
//    ArrayList<String > student_image= new ArrayList<>();
//
    String student_name;
    String student_image;

    public DataEntryStudent(String student_name, String student_image) {
        this.student_name = student_name;
        this.student_image = student_image;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setStudent_image(String student_image) {
        this.student_image = student_image;
    }
    //    public DataEntryStudent(ArrayList<String> student_name, ArrayList<String> student_image) {
//        this.student_name = student_name;
//        this.student_image = student_image;
//    }
//
//    public ArrayList<String> getStudent_name() {
//        return student_name;
//    }
//
//    public void setStudent_name(ArrayList<String> student_name) {
//        this.student_name = student_name;
//    }

//    public ArrayList<String> getStudent_image() {
//        return student_image;
//    }
//
//    public void setStudent_image(ArrayList<String> student_image) {
//        this.student_image = student_image;
//    }
}
