package by.domain;


import java.util.Objects;

public class Role {
    private Long userId;

    private String roleName;

    public Role() {
    }

    public Role(Long userId, String roleName) {
        this.userId = userId;
        this.roleName = roleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        by.domain.Role role = (by.domain.Role) o;
        return Objects.equals(userId, role.userId) &&
                Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "userId=" + userId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
