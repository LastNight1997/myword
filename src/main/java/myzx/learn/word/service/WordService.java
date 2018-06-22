package myzx.learn.word.service;

import myzx.learn.word.domain.Word;

import java.util.List;

public interface WordService {
    Word findLearntByName(String username);
    Word findLearntByNameRandom(String username);
    List<Word> findLoveByName(String username);
    int addLearnt(String username,int ID);
    int knownLearnt(String username,int ID);
    int unknownLearnt(String username,int ID);
    int addLove(String username,int ID);
    int deleteLove(String username,int ID);
    List<Word> findWordByName(String username, int num);
    Word findByWord(String Word);
}
