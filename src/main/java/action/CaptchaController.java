package action;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static utils.CaptchaUtil.generateVerifyCode;
import static utils.CaptchaUtil.outputImage;


@Controller
public class CaptchaController {
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int w = 75, h = 30;
        String verifyCode = generateVerifyCode(4);
        request.getSession().setAttribute("code", verifyCode);
        outputImage(w, h, verifyCode, response);
    }
}