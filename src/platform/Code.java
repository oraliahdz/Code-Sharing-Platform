package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@JsonPropertyOrder(alphabetic=true)
public class Code {

    @JsonProperty("code")
    String codeAll;
    @JsonIgnore
    String codeInsidePreTag;
    @JsonIgnore
    LocalDateTime dateTime;
    String date;
    @Id
    @JsonIgnore
    int id;


    public Code(){}

    public Code (String codeAll){
        this.codeAll = codeAll;
        this.dateTime = LocalDateTime.now();
        this.codeInsidePreTag = obtainPreTagCode(codeAll);
        this.date = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    public String getCodeAll() {
        return codeAll;
    }

    public void setCodeAll(String codeAll) {
        this.codeAll = codeAll;
        this.codeInsidePreTag = obtainPreTagCode(codeAll);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getCodeInsidePreTag() {
        return codeInsidePreTag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String obtainPreTagCode(String code){
        if (code.contains("<pre id=\"code_snippet\">")){
            int start = code.indexOf("<pre id=\"code_snippet\">");
            int end = code.indexOf("</pre");
            String codeInsidePreTag= code.substring(start + 23, end);
            return  codeInsidePreTag;
        }else{
            return "";
        }
    }

    @Override
    public String toString() {
        return "Code{" +
                "codeAll='" + codeAll + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
