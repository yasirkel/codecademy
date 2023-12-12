
import java.time.LocalDate;

public class Cursist {
    private String emailAddress;
    private String name;
    private LocalDate birthDate;
    private boolean sex;
    private String address;
    private String residence;
    private String country;
    private int contentItemID;

    // Getter voor emailAddress
    public String getEmailAddress() {
        return emailAddress;
    }

    // Setter voor emailAddress
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // Getter voor name
    public String getName() {
        return name;
    }

    // Setter voor name
    public void setName(String name) {
        this.name = name;
    }

    // Getter voor birthDate
    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Setter voor birthDate
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // Getter voor sex
    public boolean isSex() {
        return sex;
    }

    // Setter voor sex
    public void setSex(boolean sex) {
        this.sex = sex;
    }

    // Getter voor address
    public String getAddress() {
        return address;
    }

    // Setter voor address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter voor residence
    public String getResidence() {
        return residence;
    }

    // Setter voor residence
    public void setResidence(String residence) {
        this.residence = residence;
    }

    // Getter voor country
    public String getCountry() {
        return country;
    }

    // Setter voor country
    public void setCountry(String country) {
        this.country = country;
    }

    // Getter voor contentItemID
    public int getContentItemID() {
        return contentItemID;
    }

    // Setter voor contentItemID
    public void setContentItemID(int contentItemID) {
        this.contentItemID = contentItemID;
    }
}


