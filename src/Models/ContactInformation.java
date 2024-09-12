package Models;

public class ContactInformation {
    String phone;
    String email;

    public ContactInformation(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }


    @Override
    public String toString() {
        return "ContactInformation: " +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' ;

    }
}
