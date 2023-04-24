package springMvc.controller;

import springMvc.api.UserInfoDTO;
import springMvc.service.LCAppServiceImpl;
import springMvc.service.LcAppService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("userInfo")
public class LCAppController {

    private LcAppService lcAppService = new LCAppServiceImpl();

    @RequestMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("userInfo", new UserInfoDTO());
        return "home-page";
    }

    @RequestMapping("/process-homepage")
    public String showResultPage(Model model, @Valid UserInfoDTO userInfoDTO, BindingResult result) {

        model.addAttribute("userInfo", userInfoDTO);
        model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "userInfo", result);

        if (!userInfoDTO.isTermAndCondition()) {
            return "home-page";
        }

        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError temp : allErrors) {
                System.out.println(temp);
            }
            return "home-page";
        }

//        HttpSession session = request.getSession();
//        session.setAttribute("userName",userInfoDTO.getUserName());
//        session.setMaxInactiveInterval(120);

        // write a service which will calculate the love % between user and crushName

        String appResult = lcAppService.calculateLove(userInfoDTO.getUserName(), userInfoDTO.getCrushName());
        userInfoDTO.setResult(appResult);

        return "result-page";
    }

    @RequestMapping("/playAgain")
    public String playAgain(){
        return "home-page";
    }
}
