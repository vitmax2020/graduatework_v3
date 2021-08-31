package ru.itstep.graduatework_v3.model;

public class Users {

    private Integer Id;
    private String name;
    private String password;
    private Integer roule;
    //	private String email;
    private Integer isactive;

    public Users() {}
    public Users(Integer id, String name, String password, Integer roule, Integer isactive) {
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
        this.setRoule(roule);
        this.setIsactive(isactive);
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoule() {
        return roule;
    }

    public void setRoule(Integer roule) {
        this.roule = roule;
    }

    /*	public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    */
    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }


    @Override
    public String toString() {
        return "Users [Id=" + Id + ", Name=" + name + "]";
    }
}