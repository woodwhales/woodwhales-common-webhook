package cn.woodwhales.webhook.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author woodwhales on 2021-07-16 20:46
 * @description
 */
@RestController
@RequestMapping("test")
public class IndexController {

    @GetMapping("/send")
    public String send(@RequestParam("content") String content) {

        return "ok";
    }

}
