package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    private String delete(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다. id = "+id;
        }

        return "삭제되었습니다.";
    }
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        System.out.println("user = " + user.toString());

        User updateUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("수정에 실패하였습니다."));
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());

//        userRepository.save(updateUser);

//        더티 체킹
        return null;
    }

    @GetMapping("/dummy/user")
    public List<User> list() {
        return userRepository.findAll();
    }

    //페이징
    @GetMapping("/dummy/user/page")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return userRepository.findAll(pageable).getContent();

    }

    @GetMapping("/dummy/user/{id}")
    public User Detail(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. id = " + id));
    }

    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("user = " + user.toString());
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
