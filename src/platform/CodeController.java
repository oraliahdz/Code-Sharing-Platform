package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CodeController {
    @Autowired
    public CodeController(){

    }

    String code = """
    <html>
    <head>
    <title>Code</title>
    </head>
    <body>
    <pre>
        public static void main(String[] args) {
            SpringApplication.run(CodeSharingPlatform.class, args);
        }</pre>
    </body>
    </html>""";

    int start = code.indexOf("<pre>");
    int end = code.indexOf("</pre");

    String codeInsidePreTag= code.substring(start + 6, end);

    @GetMapping("/code")
    public ResponseEntity<String> showCode(){
        if(true){
            return ResponseEntity.ok().body(code);
        }
        return ResponseEntity.badRequest().body("r");
    }

    @GetMapping("/api/code")
    public ResponseEntity showApiCode(){
        return new ResponseEntity<>(Map.of("code", codeInsidePreTag), HttpStatus.OK);
    }
}
