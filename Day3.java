import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter age,gender,salary");
        int age = sc.nextInt();
        String gender = sc.next();
        double salary = sc.nextDouble();
        if(gender=="male"){
            if(age>=50){
                if(salary>=30000){
                    System.out.println("Gender :"+gender);
                    System.out.println("Age :"+age);
                    System.out.println("Salary is above 30K : "+salary);
                }else{
                    if(salary<30000 && salary>10000){
                        System.out.println("Gender :"+gender);
                        System.out.println("Age :"+age);
                        System.out.println("Salary (30K >salary <10K) :"+salary);
                    }else{
                        System.out.println("Gender :"+gender);
                        System.out.println("Age :"+age);
                        System.out.println("Salary is less than 10,000");
                    }
                }
            }else{ // male below age 50
                if(salary>=30000){
                    System.out.println("Gender :"+gender);
                    System.out.println("Age :"+age);
                    System.out.println("Salary is above 30K : "+salary);
                }else{
                    if(salary<30000 && salary>10000){
                        System.out.println("Gender :"+gender);
                        System.out.println("Age :"+age);
                        System.out.println("Salary (30K >salary <10K) :"+salary);
                    }else{
                        System.out.println("Gender :"+gender);
                        System.out.println("Age :"+age);
                        System.out.println("Salary is less than 10,000");
                    }
                }
            }
        }else{  //gender = female
            if(age>=50){
                if(salary>=30000){
                    System.out.println("Gender :"+gender);
                    System.out.println("Age :"+age);
                    System.out.println("Salary is above 30K : "+salary);
                }else{
                    if(salary<30000 && salary>10000){
                        System.out.println("Gender :"+gender);
                        System.out.println("Age :"+age);
                        System.out.println("Salary (30K >salary <10K) :"+salary);
                    }else{
                        System.out.println("Gender :"+gender);
                        System.out.println("Age :"+age);
                        System.out.println("Salary is less than 10,000");
                    }
                }
            }else{ //below age 50 females
                if(age>=50){
                    if(salary>=30000){
                        System.out.println("Gender :"+gender);
                        System.out.println("Age :"+age);
                        System.out.println("Salary is above 30K : "+salary);
                    }else {
                        if (salary < 30000 && salary > 10000) {
                            System.out.println("Gender :" + gender);
                            System.out.println("Age :" + age);
                            System.out.println("Salary (30K >salary <10K) :" + salary);
                        } else {
                            System.out.println("Gender :" + gender);
                            System.out.println("Age :" + age);
                            System.out.println("Salary is less than 10,000");
                        }
                    }
                }
            }
        }
    }
}
