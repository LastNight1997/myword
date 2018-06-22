package myzx.learn.word.controller;

import myzx.learn.word.domain.User;
import myzx.learn.word.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
public class LoginContorller {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> register(HttpServletRequest request, HttpServletResponse response){
        Map<String,String> map =new HashMap<>();
        String userName=request.getParameter("username");
        String password=request.getParameter("password");
        if(userName.equals("")||password.equals("")){
            map.put("result","0");
            return map;
        }
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        try {
            if(userService.add(user)==1){
                request.getSession().setAttribute("user", user);
                map.put("result","1");
            }
            else
                map.put("result","0");
        }catch (Exception e){
            map.put("result","0");
        }

        return map;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String,String> map =new HashMap<>();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user =userService.findByName(username);
        if (user == null){
            map.put("result","0");
        }
        else if(user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            map.put("result","1");
        }
        else
            map.put("result","0");
        return map;
    }

    @RequestMapping("/")
    public String index(){
        return "/learnword/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        return "/learnword/login";
    }
}
