package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseV1() {
        ModelAndView mav = new ModelAndView("/response/hello")
                .addObject("data", "hello spring!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseV2(Model model) {
        model.addAttribute("data", "hello java!");
        return "/response/hello";
    }

    /**
     * void를 반환하는 경우
     * 참고로 이 방식은 명시성이 너무 떨어지고 이렇게 딱 맞는 경우도 많이 없어서, 권장하지 않는다.
     * @param model
     */
    @RequestMapping("/response/hello")
    public void responseV3(Model model) {
        model.addAttribute("data", "too much!");
    }
}
