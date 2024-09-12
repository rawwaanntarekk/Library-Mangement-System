package Models;

public class Member {
    String name;
    static Integer memberShipId = 0;
    ContactInformation contactInformation;

    public Member(String name, String phone, String email) {
        this.name = name;
        memberShipId++;
        this.contactInformation = new ContactInformation(phone, email);
    }
    public String getName() {
        return name;
    }



    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return "Member: " +
                "name='" + name + '\'' +
                ", " + contactInformation ;
    }
}
