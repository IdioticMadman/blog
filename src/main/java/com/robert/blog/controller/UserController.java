package com.robert.blog.controller;

import com.robert.blog.domain.User;
import com.robert.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户控制器
 *
 * @author robert
 */
@RestController("/users")
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * 查询所有用户
     *
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(Model model) {
        model.addAttribute("userList", repository.getAllUser());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("/user/list", "userModel", model);
    }

    /**
     * 查询用户
     *
     * @param model
     * @param id    用户Id
     * @return
     */
    @GetMapping("/{id}")
    public ModelAndView view(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", repository.getUser(id));
        model.addAttribute("title", "查看用户");
        return new ModelAndView("/user/view", "userModel", model);
    }

    /**
     * 获取创建表单页面
     *
     * @param model
     * @return
     */
    @GetMapping("/from")
    public ModelAndView createFrom(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("/user/form", "userModel", model);
    }

    /**
     * 创建用户，跳转用户列表
     *
     * @param user
     * @return
     */
    @PostMapping
    public ModelAndView create(User user) {
        user = repository.insertOrUpdate(user);
        return new ModelAndView("redirect:/users");
    }

    /**
     * 删除用户，跳转用户列表
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public ModelAndView delete(Model model, @PathVariable("id") Long id) {
        repository.delete(id);
        model.addAttribute("userList", repository.getAllUser());
        model.addAttribute("title", "删除用户");
        return new ModelAndView("/user/list", "userModel", model);
    }

    /**
     * 修改用户
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/modify/{id}")
    public ModelAndView modify(Model model, @PathVariable("id") Long id) {
        User user = repository.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "修改用户");
        return new ModelAndView("/user/form", "userModel", model);
    }

}
