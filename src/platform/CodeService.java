package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeService {
    String baseCode = """
    <html>
    <head>
    <title>Code</title>
    <link rel="stylesheet"
                           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
                    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
                    <script>hljs.initHighlightingOnLoad();</script>
    </head>
    <body>
        <span id="load_date"> %s </span>
        <pre id="code_snippet"><code>%s</code></pre>
    </body>
    </html>""";

    String initialCode = "";

    String initiaiPreCode = """
            public static void main(String[] args) {
            SpringApplication.run(CodeSharingPlatform.class, args);
            }
            """;
    LocalDateTime date = LocalDateTime.now();
    int codeId = 0;

    String baseCodeGetNew = """
            <html>
            <head>
            <title>Create</title>
                    <link rel="stylesheet"
                           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
                    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
                    <script>hljs.initHighlightingOnLoad();</script>
            </head>
            <body>
                <span id="load_date"> %s </span>
                <textarea id="code_snippet">
                %s</textarea>
                <button id="send_snippet" type="submit" onclick="send()">Submit</button>
            </body>
            </html>""";

    String baseCodeLatest = """
            <html>
            <head>
                    <link rel="stylesheet"
                           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
                    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
                    <script>hljs.initHighlightingOnLoad();</script>
            <title>Latest</title>
            </head>
            <body>
                %s
            </body>
            </html>""";



    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository){
        this.initialCode = baseCode.formatted(date, initiaiPreCode);
        this.codeRepository = codeRepository;
    }

    public String getInitialCode (){
        return initialCode;
    }

    public void setNewCodeFormatted(String newCode){
        this.initiaiPreCode = newCode;
        this.date = LocalDateTime.now();
        this.initialCode = baseCode.formatted(date, initiaiPreCode);
    }

    public LocalDateTime getUpdatedDate(){
        return this.date;
    }

    public String getBaseCodeGetNew(){
        String message = "//Write new code here";
        return baseCodeGetNew.formatted(LocalDateTime.now(),message);
    }

    public int saveNewCode(String code){
        this.codeId++;
        Code newCode = new Code(code);
        newCode.setId(this.codeId);
        //this.codes.add(newCode);
        codeRepository.save(newCode);
        return codeId;
    }

    public Code getCodeById(int id){
        for(Code code : codeRepository.findAll()){
            if (code.getId() == id){
                return code;
            }
        }
        return null;
    }

    public String getCodeFormatted(int id){
        Code code = getCodeById(id);
        if (code != null){
            String codeText = code.getCodeAll();
            String date = code.getDate();
            return baseCode.formatted(date,codeText);
        }else{
            return null;
        }
    }

    public List<Code> getLastElements() {
        List<Code> list = codeRepository.findAll();
        List<Code> filtered = new ArrayList<>();
        if(list.size()<=10){
            filtered = sortList(list);
            return filtered;
        }else{
            //filtered = codes.stream().skip(codes.size()-10).limit(10).collect(Collectors.toList());
            filtered = list.stream().skip(list.size()-10).limit(10).collect(Collectors.toList());
            filtered = sortList(filtered);
            return filtered;
        }
    }

    public String getPreTagLoopText (List<Code> list){
        String loopText = "";
        String baseText = """
                <span id="load_date"> %s </span>
                <pre id="code_snippet"><code>%s</code></pre>
                """;
        for(Code code : list){
            loopText += baseText.formatted(code.getDate(), code.getCodeAll());
        }
        return loopText;
    }

    public String getCodeLatestFormatted(List<Code> list){
        String codeFormatted = getPreTagLoopText(list);
        return baseCodeLatest.formatted(codeFormatted);
    }

    public List<Code> sortList(List<Code> list){
        List<Code> reversed = new ArrayList<>();
        for(int i = list.size(); i>0; i-- ){
            reversed.add(list.get(i-1));
        }
        return reversed;
    }

}
