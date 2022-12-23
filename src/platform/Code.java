package platform;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
@JsonPropertyOrder(alphabetic=true)
public class Code {

    @JsonIgnore
    String codeAll;
    @JsonProperty("code")
    String codeInsidePreTag;
    LocalDate date;


    public Code(){}

    public Code (String codeAll){
        this.codeAll = codeAll;
        this.date = LocalDate.now();
        this.codeInsidePreTag = obtainPreTagCode(codeAll);
    }

    public String getCodeAll() {
        return codeAll;
    }

    public void setCodeAll(String codeAll) {
        this.codeAll = codeAll;
        this.codeInsidePreTag = obtainPreTagCode(codeAll);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCodeInsidePreTag() {
        return codeInsidePreTag;
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
                "codeInsidePreTag='" + codeInsidePreTag + '\'' +
                ", date=" + date +
                '}';
    }
}
