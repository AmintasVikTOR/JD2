package by.domain;

public class NotBeanByDefault {
    private String test;

    public NotBeanByDefault() {
    }

    public NotBeanByDefault(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "NotBeanByDefault{" +
                "test='" + test + '\'' +
                '}';
    }
}
