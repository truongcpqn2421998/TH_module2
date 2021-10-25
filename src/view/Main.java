package view;

import manager.PhoneBookManager;
import model.TelephoneNumber;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static PhoneBookManager phoneBookManager=new PhoneBookManager();
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int choice =-1;
        do{
            System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ----");
            System.out.println("Chọn chức năng theo số (để tiếp tục):");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("0. Thoát");
            System.out.println("Chọn chức năng");
            choice =scanner.nextInt();
            switch (choice){
                case 1:
                    viewAllPhoneBook();
                    break;
                case 2:
                    addNewPhoneNumber();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    find();
                    break;
                case 6:
                    readFile();
                    break;
                case 7:
                    writeFile();
                    break;
                default:
                    System.out.println("Chọn lại");
            }
        }while (choice!=0);
    }
    public static void viewAllPhoneBook(){
        for (TelephoneNumber telephoneNumber: phoneBookManager.getTelephoneNumberList()
             ) {
            System.out.println(telephoneNumber);
        }
    }
    public static void addNewPhoneNumber(){
        TelephoneNumber telephoneNumber = createPhone();
        phoneBookManager.add(telephoneNumber);
    }

    private static TelephoneNumber createPhone() {
        Scanner scanner= new Scanner(System.in);
        Scanner num=new Scanner(System.in);
        System.out.println("Enter phoneNumber");
        String phone=scanner.nextLine();
        System.out.println("Enter Name:");
        String name=scanner.nextLine();
        System.out.println("Enter group");
        String group=scanner.nextLine();
        System.out.println("Enter gender:");
        String gender=scanner.nextLine();
        System.out.println("Enter Address:");
        String address=scanner.nextLine();
        System.out.println("Enter date of birth");
        System.out.println("Enter day:");
        int day=num.nextInt();
        System.out.println("Enter month");
        int month=num.nextInt();
        System.out.println("Enter Year");
        int year=num.nextInt();
        System.out.println("Enter email");
        String email=scanner.nextLine();
        TelephoneNumber telephoneNumber=phoneBookManager.create(phone,group,name,gender,address, LocalDate.of(year,month,day),email);
        return telephoneNumber;
    }

    public static void update(){
        Scanner scanner=new Scanner(System.in);
        int index=-1;
        do {
            System.out.println("Enter phoneNumber you want update");
            String phone = scanner.nextLine();
            index = phoneBookManager.getIndex(phone);
            if(index==-1){
                System.out.println("Error");
            }
        }while (index==-1);
        TelephoneNumber telephoneNumber=createPhone();
        phoneBookManager.update(index,telephoneNumber);
    }
    public static void delete(){
        Scanner scanner=new Scanner(System.in);
        TelephoneNumber telephoneNumber=null;
        do {
            System.out.println("Enter phone you want delete:");
            String phone = scanner.nextLine();
            telephoneNumber=phoneBookManager.findPhoneBookByPhoneNumber(phone);
            if(telephoneNumber==null){
                System.out.println("Error");
            }
        }while (telephoneNumber==null);
        phoneBookManager.remove(telephoneNumber);
    }
    public static void find(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter phone:");
        String phone=scanner.nextLine();
        TelephoneNumber telephoneNumber=phoneBookManager.findPhoneBookByPhoneNumber(phone);
        if(phone!=null){
            System.out.println(telephoneNumber.toString());
        }else {
            System.out.println("Error");
        }
    }

    public static void readFile(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter file you want to read");
        String read=scanner.nextLine();
        try {
            phoneBookManager.readFile(read);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter file you want to write");
        String write=scanner.nextLine();
        try {
            phoneBookManager.writeFile(write);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
