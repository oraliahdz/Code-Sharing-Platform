package platform;

public class NewCodeModel {
    private String code;
    public NewCodeModel(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "NewCodeModel{" +
                "code='" + code + '\'' +
                '}';
    }
}
