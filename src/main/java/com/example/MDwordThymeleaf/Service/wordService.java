package com.example.MDwordThymeleaf.Service;

import com.example.MDwordThymeleaf.Dto.wordDto;
import com.example.MDwordThymeleaf.repository.WordDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class wordService {
    WordDao dao = new WordDao();
    public int register(List<String> wordKorean, List<String> wordEnglish) {
        int cnt=0;
        for(int i =0; i < wordKorean.size(); i++) {
            wordDto dto = new wordDto(wordKorean.get(i), wordEnglish.get(i));

            dao.insert(dto);
            cnt++;

        }
        return cnt;
    }
   public List<wordDto> listWord() {
        List<wordDto> list=dao.getList();

        return list;
    }

    public wordDto getWord(int id) {
        wordDto dto = dao.getWord(id);
        return dto;
    }

    public void modifyWord(int id, String korean, String english) {
        wordDto dto = new wordDto (id, korean, english);

        dao.modifyWord(dto);

    }

    public void deleteWord(int id) {
        dao.deleteWord(id);

    }

    public List<wordDto> randomWord() {
        List<wordDto> list=dao.getRandomList();

        return list;

    }

    public void deleteAll() {
        dao.deleteAll();


    }

    public List<wordDto> randomWordByWordLimit(int wordLimit) {
        List<wordDto> list=dao.randomWordByWordLimit(wordLimit);

        return list;
    }

    public int registerWordList(List<wordDto> wordList) {
        int cnt=0;
        for(int i =0; i < wordList.size(); i++) {
            wordDto dto = new wordDto(wordList.get(i).getKorean(), wordList.get(i).getEnglish());
            dao.insert(dto);
            cnt++;
        }
        return cnt;
    }

    public int getListCount() {
        int cnt = dao.getListCount();

        return cnt;
    }

    public List<wordDto> findWord(String search) {

        return dao.findWord(search);
    }
}
