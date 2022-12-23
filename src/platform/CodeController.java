package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {
    private final CodeService codeService;
    private Code code;

    @Autowired
    public CodeController(CodeService codeService){
        this.codeService = codeService;
        this.code = new Code(codeService.getInitialCode());
    }

    @GetMapping("/code")
    public ResponseEntity showCode(){
        if(true){
            return ResponseEntity.ok().body(code.getCodeAll());
        }
        return ResponseEntity.badRequest().body("Bad Request");
    }

    @GetMapping("/api/code")
    public ResponseEntity showApiCode(){
        return ResponseEntity.ok().body(code);
    }
    @PostMapping(value = "/api/code/new")
    public ResponseEntity newCode(
            @RequestBody NewCodeModel newCode

    ){
        codeService.setNewCodeFormatted(newCode.getCode());
        String codeFormatted = codeService.getInitialCode();
        code.setDate(codeService.getUpdatedDate());
        code.setCodeAll(codeFormatted);
        return ResponseEntity.ok().body("{}");
    }

    @GetMapping(value = "/code/new")
    public ResponseEntity showNewCode(
    ){
        return ResponseEntity.ok().body(codeService.getBaseCodeGetNew());
    }


}
