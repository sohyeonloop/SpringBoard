package kr.or.ddit.collection;

public class Student extends People{
   
   private int hak;
   private int ban;
   private int no;
   
   public Student(String name, String sex, 
         int age , int hak, int ban, int no) {
      this.hak = hak;
      this.ban = ban;
      this.no = no;
      this.setName(name);
      this.setSex(sex);
      this.setAge(age);
   }
   public Student() {
   
   }
   public int getHak() {
      return hak;
   }
   public void setHak(int hak) {
      this.hak = hak;
   }
   public int getBan() {
      return ban;
   }
   public void setBan(int ban) {
      this.ban = ban;
   }
   public int getNo() {
      return no;
   }
   public void setNo(int no) {
      this.no = no;
   }
   @Override
   public String toString() {
      return "Student: " + super.toString() + "[hak=" + hak + ", ban=" + ban + ", no=" + no + "]";
   }
   

}