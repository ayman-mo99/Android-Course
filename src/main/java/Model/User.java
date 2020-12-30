package Model;

public class User {
    String firstname;
    String lastname;
    String image;
    Boolean admin;

    public User(String firstname, String lastname, String image, Boolean admin) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.image = image;
        this.admin = admin;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
       this.admin = admin;
    }

    public User() {
    }
}
