package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CodeService {
    String baseCode = """
    <html>
    <head>
    <title>Code</title>
    </head>
    <body>
        <span id="load_date"> %s </span>
        <pre id="code_snippet">%s</pre>
    </body>
    </html>""";

    String initialCode = "";

    String initiaiPreCode = """
            public static void main(String[] args) {
            SpringApplication.run(CodeSharingPlatform.class, args);
            }
            """;
    LocalDate date = LocalDate.now();

    String baseCodeGetNew = """
    <html>
    <head>
    <title>Create</title>
    </head>
    <body>
        <span id="load_date"> %s </span>
        <textarea id="code_snippet">
        %s</textarea>
        <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    </body>
    </html>""";

    @Autowired
    public CodeService(){
        this.initialCode = baseCode.formatted(date, initiaiPreCode);
    }

    public String getInitialCode (){
        return initialCode;
    }

    public void setNewCodeFormatted(String newCode){
        this.initiaiPreCode = newCode;
        this.date = LocalDate.now();
        this.initialCode = baseCode.formatted(date, initiaiPreCode);
    }

    public LocalDate getUpdatedDate(){
        return this.date;
    }

    public String getBaseCodeGetNew(){
        String message = "//Write new code here";
        return baseCodeGetNew.formatted(LocalDate.now(),message);
    }
}
