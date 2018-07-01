package myzx.learn.word.controller;

import myzx.learn.word.domain.User;
import myzx.learn.word.domain.Word;
import myzx.learn.word.service.UserService;
import myzx.learn.word.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/learnword")
public class LearnWordController {
    @Autowired
    private UserService userService;

    @Autowired
    private WordService wordService;

    @RequestMapping("")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/learnword/index");
        User user =(User)request.getSession().getAttribute("user");
        if(user.getHavelearnt()<user.getNum())
            modelAndView.addObject("bool","1");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping("login")
    public String login(){
        return "/learnword/login";
    }


    @RequestMapping("guide")
    public ModelAndView guide(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("/learnword/guide");
        User user =(User)request.getSession().getAttribute("user");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping(value = "learntype", method = RequestMethod.POST)
    public @ResponseBody
    Map<String,String> learntype(HttpServletRequest request, HttpServletResponse response){
        Map<String,String> map =new HashMap<>();
        User user =(User)request.getSession().getAttribute("user");
        String level = request.getParameter("level");
        int num =Integer.valueOf(request.getParameter("num"));
        user.setLevel(level);
        user.setNum(num);
        try {
            if(userService.update(user)==1){
                request.getSession().setAttribute("user", user);
                map.put("result","1");
            }
            else {
                map.put("result","0");
            }
        }catch (Exception e){
            map.put("result","0");
        }
        return map;
    }

    @RequestMapping("word")
    public ModelAndView word(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("/learnword/word");
        User user =(User)request.getSession().getAttribute("user");
        modelAndView.addObject("user",user);
        int num=user.getNum()-user.getHavelearnt();
        if(num<=0)
            return modelAndView;
        List<Word> words = wordService.findWordByName(user.getUsername(),num);
        request.getSession().setAttribute("learnword",words);
        request.getSession().setAttribute("learnnum",0);
        Word word = words.get(0);
        String[] lx=word.getLx().split("/r/n");
        if(lx.length<2) {
            lx = new String[2];
            lx[0]=" ";
            lx[1]=" ";
        }
        modelAndView.addObject("word",word);
        modelAndView.addObject("lx",lx);
        return modelAndView;
    }

    @RequestMapping("review")
    public ModelAndView review(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("/learnword/review");
        User user =(User)request.getSession().getAttribute("user");
        modelAndView.addObject("user",user);
        Word word = wordService.findLearntByName(user.getUsername());
        if (word==null)
            return modelAndView;
        request.getSession().setAttribute("reviewID",word.getID());
        String[] lx=word.getLx().split("/r/n");
        if(lx.length<2) {
            lx = new String[2];
            lx[0]=" ";
            lx[1]=" ";
        }
        modelAndView.addObject("word",word);
        modelAndView.addObject("lx",lx);
        return modelAndView;
    }

    @RequestMapping("knownget")
    public @ResponseBody
    Map<String,String> knownget(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String,String> map =new HashMap<>();
        User user =(User)request.getSession().getAttribute("user");
        int ID = (int)request.getSession().getAttribute("reviewID");
        String known = request.getParameter("known");
        if(known.equals("1"))
            wordService.knownLearnt(user.getUsername(),ID);
        else
            wordService.unknownLearnt(user.getUsername(),ID);
        Word word = wordService.findLearntByName(user.getUsername());
        request.getSession().setAttribute("reviewID",word.getID());
        map.put("word",word.getWord());
        map.put("meaning",word.getMeaning());
        String[] lx=word.getLx().split("/r/n");
        if(lx.length>1) {
            map.put("en", lx[0]);
            map.put("ch", lx[1]);
        }else{
            map.put("en", " ");
            map.put("ch", " ");
        }
        return map;
    }


    @RequestMapping("learndone")
    public ModelAndView learndone(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/learnword/word");
        User user =(User)request.getSession().getAttribute("user");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping("learnmore")
    public void learnmore(HttpServletRequest request,HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        session.removeAttribute("learnnum");
        session.removeAttribute("learnword");
        User user = (User)session.getAttribute("user");
        user.setHavelearnt(0);
        session.setAttribute("user",user);
        userService.update(user);
        response.sendRedirect("word");
    }

    @RequestMapping(value = "addlove",method = RequestMethod.POST)
    public @ResponseBody
    Map<String,String> addlove(HttpServletRequest request, HttpServletResponse response)throws Exception{
        Map<String,String> map =new HashMap<>();
        User user =(User)request.getSession().getAttribute("user");
        if(request.getParameter("word") != null){
            String word = request.getParameter("word");
            Word w = wordService.findByWord(word);
            if(w==null){
                response.sendRedirect("love");
                return map;
            }
            int ID = w.getID();
            wordService.addLove(user.getUsername(),ID);
            response.sendRedirect("love");
            return map;
        }
        List<Word> words = (List<Word>)request.getSession().getAttribute("learnword");
        int num = (int)request.getSession().getAttribute("learnnum");
        try {
            if(wordService.addLove(user.getUsername(),words.get(num).getID())==1){
                map.put("result","1");
            }
            else {
                map.put("result","0");
            }
        }catch (Exception e){
            map.put("result","0");
        }
        return map;
    }

    @RequestMapping(value = "deletelove",method = RequestMethod.GET)
    public @ResponseBody String deleteloveget(HttpServletRequest request,HttpServletResponse response) throws Exception{
        User user =(User)request.getSession().getAttribute("user");
        int ID = Integer.valueOf(request.getParameter("ID"));
        wordService.deleteLove(user.getUsername(),ID);
        response.sendRedirect("love");
        return "";
    }

    @RequestMapping(value = "deletelove",method = RequestMethod.POST)
    public @ResponseBody
    Map<String,String> deletelove(HttpServletRequest request, HttpServletResponse response){
        Map<String,String> map =new HashMap<>();
        User user =(User)request.getSession().getAttribute("user");
        List<Word> words = (List<Word>)request.getSession().getAttribute("learnword");
        int num = (int)request.getSession().getAttribute("learnnum");
        try {
            if(wordService.deleteLove(user.getUsername(),words.get(num).getID())==1){
                map.put("result","1");
            }
            else {
                map.put("result","0");
            }
        }catch (Exception e){
            System.out.println(e);
            map.put("result","0");
        }
        return map;
    }


    @RequestMapping("nextlearnword")
    public @ResponseBody
    Map<String,String> nextlearnword(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String,String> map =new HashMap<>();
        User user =(User)request.getSession().getAttribute("user");
        List<Word> words = (List<Word>)request.getSession().getAttribute("learnword");
        int num = (int)request.getSession().getAttribute("learnnum");
        wordService.addLearnt(user.getUsername(),words.get(num).getID());
        num = num +1;
        request.getSession().setAttribute("learnnum",num);
        user.setHavelearnt(user.getHavelearnt()+1);
        userService.update(user);
        request.getSession().setAttribute("user",user);
        if(num>=words.size()){
            map.put("done","1");
            return map;
        }
        Word word = words.get(num);
        map.put("word",word.getWord());
        map.put("meaning",word.getMeaning());
        String[] lx=word.getLx().split("/r/n");
        if(lx.length>1) {
            map.put("en", lx[0]);
            map.put("ch", lx[1]);
        }else{
            map.put("en", " ");
            map.put("ch", " ");
        }
        return map;
    }

    @RequestMapping("examget")
    public @ResponseBody
    Map<String,String> examget(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String,String> map =new HashMap<>();
        User user =(User)request.getSession().getAttribute("user");
        Word word = wordService.findLearntByNameRandom(user.getUsername());
        map.put("word",word.getWord());
        map.put("meaning",word.getMeaning());
        String[] lx=word.getLx().split("/r/n");
        if(lx.length>1) {
            map.put("en", lx[0]);
            map.put("ch", lx[1]);
        }else{
            map.put("en", " ");
            map.put("ch", " ");
        }
        return map;
    }

    @RequestMapping("test2")
    public @ResponseBody String test2(){
        PageHelper.startPage(0,2);
        List<Word> words=wordService.findLoveByName("admin");
        PageInfo<Word> page = new PageInfo<>(words);
        return String.valueOf(page.getList().size());
    }

    @RequestMapping("love")
    public ModelAndView love(@RequestParam(value = "start", defaultValue = "1") int start,
                             @RequestParam(value = "size", defaultValue = "10") int size,
                             HttpServletRequest request){
        User user =(User)request.getSession().getAttribute("user");
        PageHelper.startPage(start,size);
        List<Word> words=wordService.findLoveByName(user.getUsername());
        PageInfo<Word> page = new PageInfo<>(words);
        ModelAndView modelAndView = new ModelAndView("/learnword/love");
        modelAndView.addObject("page",page);
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping("exam")
    public ModelAndView love(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/learnword/exam");
        User user =(User)request.getSession().getAttribute("user");
        Word word = wordService.findLearntByNameRandom(user.getUsername());
        modelAndView.addObject("user",user);
        if(word==null)
            return modelAndView;
        modelAndView.addObject("word",word);
        String[] lx=word.getLx().split("/r/n");
        if(lx.length<2) {
            lx = new String[2];
            lx[0]=" ";
            lx[1]=" ";
        }
        modelAndView.addObject("lx",lx);
        return modelAndView;
    }

    @RequestMapping("search")
    public ModelAndView search(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/learnword/word");
        User user =(User)request.getSession().getAttribute("user");
        String search = request.getParameter("search");
        Word word = wordService.findByWord(search);
        modelAndView.addObject("user",user);
        modelAndView.addObject("search","1");
        if(word==null)
            return modelAndView;
        String[] lx=word.getLx().split("/r/n");
        if(lx.length<2) {
            lx = new String[2];
            lx[0]=" ";
            lx[1]=" ";
        }
        modelAndView.addObject("word",word);
        modelAndView.addObject("lx",lx);
        return modelAndView;
    }
}

