package cn.edu.tju.scs.domain;

/**
 * Admin domain
 * Created by jack on 2016/1/1.
 */
public class Admin extends BaseDomain {

    private Integer adminId;
    private String adminName;
    private String password;

    public Admin(){

    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (!adminId.equals(admin.adminId)) return false;
        if (!adminName.equals(admin.adminName)) return false;
        if (!password.equals(admin.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adminId.hashCode();
        result = 31 * result + adminName.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
