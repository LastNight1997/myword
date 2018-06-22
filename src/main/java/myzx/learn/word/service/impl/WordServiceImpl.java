package myzx.learn.word.service.impl;

import myzx.learn.word.domain.Word;
import myzx.learn.word.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import myzx.learn.word.dao.WordMapper;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    WordMapper wordMapper;

    @Override
    public Word findLearntByName(String username){
        return this.wordMapper.findLearntByName(username);
    }

    @Override
    public List<Word> findLoveByName(String username){
        return this.wordMapper.findLoveByName(username);
    }

    @Override
    public int addLearnt(String username,int ID){
        return this.wordMapper.addLearnt(username,ID);
    }

    @Override
    public int addLove(String username,int ID){
        return this.wordMapper.addLove(username,ID);
    }

    @Override
    public int deleteLove(String username,int ID){
        return this.wordMapper.deleteLove(username,ID);
    }

    @Override
    public List<Word> findWordByName(String username, int num){
        return this.wordMapper.findWordByName(username,num);
    }

    @Override
    public Word findByWord(String Word){
        return this.wordMapper.findByWord(Word);
    }

    @Override
    public int knownLearnt(String username,int ID){return this.wordMapper.knownLearnt(username,ID);}

    @Override
    public int unknownLearnt(String username,int ID){return this.wordMapper.unknownLearnt(username,ID);}

    @Override
    public Word findLearntByNameRandom(String username){return this.wordMapper.findLearntByNameRandom(username); }

}
