package manager;

import model.TelephoneNumber;
import storage.IOPhoneNumber;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookManager {
    private IOPhoneNumber ioPhoneNumber=IOPhoneNumber.getInstance();
    List<TelephoneNumber> telephoneNumberList=new ArrayList<>();

    public PhoneBookManager() {
    }

    public PhoneBookManager(List<TelephoneNumber> telephoneNumberList) {
        this.telephoneNumberList = telephoneNumberList;
    }

    public List<TelephoneNumber> getTelephoneNumberList() {
        return telephoneNumberList;
    }

    public void setTelephoneNumberList(List<TelephoneNumber> telephoneNumberList) {
        this.telephoneNumberList = telephoneNumberList;
    }

    public void add(TelephoneNumber telephoneNumber){
        telephoneNumberList.add(telephoneNumber);
    }
    public void remove(TelephoneNumber telephoneNumber){
        telephoneNumberList.remove(telephoneNumber);
    }
    public TelephoneNumber findPhoneBookByPhoneNumber (String phoneNumber){
        for (int i = 0; i < telephoneNumberList.size(); i++) {
            if(telephoneNumberList.get(i).getPhoneNumber().equals(phoneNumber)) {
                return telephoneNumberList.get(i);
            }
        }
        return null;
    }
    public void update(int index,TelephoneNumber telephoneNumber){
        telephoneNumberList.remove(index);
        telephoneNumberList.add(index,telephoneNumber);
    }

    public TelephoneNumber create(String phoneNumber, String group, String fullName, String gender, String address, LocalDate dateOfBirth,String email){
        TelephoneNumber telephoneNumber=new TelephoneNumber(phoneNumber,group,fullName,gender,address,dateOfBirth,email);
        return telephoneNumber;
    }

    public int getIndex(String phone){
        for (int i = 0; i < telephoneNumberList.size(); i++) {
            if (telephoneNumberList.get(i).getPhoneNumber().equals(phone)){
                return i;
            }
        }
        return -1;
    }

    public void writeFile(String path) throws IOException {
        ioPhoneNumber.writeFile(telephoneNumberList,path);
    }
    public void readFile(String path) throws IOException, ClassNotFoundException {
        setTelephoneNumberList(ioPhoneNumber.readFile(path));
    }


}
