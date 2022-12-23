package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        int newId = codeService.saveNewCode(newCode.getCode());
        return new ResponseEntity<>(Map.of("id", "%d".formatted(newId)), HttpStatus.OK);
    }

    @GetMapping(value = "/code/new")
    public ResponseEntity showNewCode(
    ){
        return ResponseEntity.ok().body(codeService.getBaseCodeGetNew());
    }
    @GetMapping(value = "/api/code/{id}")
    public ResponseEntity showCodeById(@PathVariable("id") int id){
        if(id > 0){
            Code selectedCode = codeService.getCodeById(id);
            if(selectedCode != null){
                return ResponseEntity.ok().body(selectedCode);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Code id not found");
            }
        }
        return ResponseEntity.badRequest().body("Bad Request");
    }

    @GetMapping(value = "/code/{id}")
    public ResponseEntity showPageOfCodeN(@PathVariable("id") int id){
        if(id > 0){
            String codeFormatted = codeService.getCodeFormatted(id);
            if(codeFormatted != null){
                return ResponseEntity.ok().body(codeFormatted);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Code id not found");
            }
        }
        return ResponseEntity.badRequest().body("Bad Request");
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity getLatestCodes(){
        List<Code> latest = codeService.getLastElements();
        return ResponseEntity.ok().body(latest);
    }

    @GetMapping("/code/latest")
    public ResponseEntity getLatestCodesFormatted(){
        List<Code> latest = codeService.getLastElements();
        String codesFormatted = codeService.getCodeLatestFormatted(latest);
        return ResponseEntity.ok().body(codesFormatted);
    }


}
