package com.example.MDwordThymeleaf.controller.word;

import com.example.MDwordThymeleaf.Dto.wordDto;
import com.example.MDwordThymeleaf.Service.wordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;


@Controller
public class wordController {
    @Autowired
    private wordService service;

    @GetMapping("wordPlace")
    public void wordPlace(){}

    @PostMapping("wordPlace")
    public void wordPlace(Model model, @RequestParam(name= "word") String word, RedirectAttributes rttr){
        if(word.equals("")){
            return;
        }

     //단어장db에 인풋값 넣기
        String findWord1 = "";String findWord2 = "";
        List<String> korean = new ArrayList<>();
        List<String> english = new ArrayList<>();
        List<wordDto> wordSplit = new ArrayList<>();
        wordDto dto = new wordDto();
        for(int i=0; i<word.length(); i++){

            if(word.substring(i,i+1).matches("^[a-zA-Z]") ) {
                if(!findWord2.equals("")){
                    dto.setKorean(findWord2);
                    if(dto.getKorean()!=null&&dto.getEnglish()!=null){
                        wordSplit.add(dto);
                        System.out.println("List에 들어가는 단어들 : " + dto.getEnglish() + " 뜻 : " + dto.getKorean());
                        dto = new wordDto();
                        english.add(findWord1);
                        korean.add(findWord2);
                        findWord2="";
                        findWord1="";
                    }
                }
                findWord1 += word.substring(i, i + 1);
            } else {
                if(findWord2.equals("")){
                    dto.setEnglish(findWord1);

                }
                findWord2 += word.substring(i, i + 1);
            }
            if(i ==word.length()-1){
                if(!findWord2.equals("")){
                    dto.setKorean(findWord2);
                    if(dto.getKorean()!=null&&dto.getEnglish()!=null){
                        wordSplit.add(dto);
                        System.out.println("List에 들어가는 단어들 : " + dto.getEnglish() + " 뜻 : " + dto.getKorean());
                        dto = new wordDto();
                        english.add(findWord1);
                        korean.add(findWord2);
                        findWord2="";
                        findWord1="";
                    }
                }
            }
            System.out.println("남은 String 문자 : " + word.substring(i));
        }

        int answer = service.registerWordList(wordSplit);
        System.out.println("등록된 단어의 수 : " + answer);
        model.addAttribute("wordSize", answer);
        model.addAttribute("wordKorean",korean);
        model.addAttribute("wordEnglish",english);
    }

    @GetMapping("list")
    public void list(
            Model model, @RequestParam(value = "search",required = false) String search
    ){
        List<wordDto> list = new ArrayList<wordDto>();
        if(search !=null && !search.isEmpty()){
            list = service.findWord(search);
        } else {
            list = service.listWord();
        }

        model.addAttribute("wordList", list);
    }
    @PostMapping("list")
    public String Postlist(
            @RequestParam(name= "number") int id,
            @RequestParam(name= "korean") String korean,
            @RequestParam(name= "english") String english
    ){
        service.modifyWord(id, korean, english);
        return "redirect:/list";
    }

    @PostMapping("modify")
    public void modify(int id, Model model){
        wordDto dto = service.getWord(id);
        model.addAttribute("word",dto);
    }
    @PostMapping("delete")
    public String delete(@RequestParam(name= "id") int id){
        service.deleteWord(id);
        return "redirect:/list";
    }

    @PostMapping("deleteAll")
    public String deleteAll(){

        service.deleteAll();

        return "redirect:/list";
    }

    @GetMapping("/print")
    public void print(Model model, @RequestParam int wordLimit) {
        int maxWordCount = service.getListCount();

        if(wordLimit==0 || maxWordCount<wordLimit) {
            return ;
        }
        List<wordDto> list = service.randomWordByWordLimit(wordLimit);

        model.addAttribute("wordList", list);

    }
}
