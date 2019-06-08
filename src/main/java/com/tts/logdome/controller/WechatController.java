package com.tts.logdome.controller;

import com.tts.logdome.common.enums.SellExceptionEnum;
import com.tts.logdome.common.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam(value = "returnUrl") String returnUrl){
        //1.配置
        //2.调用方式
        String url = "http://9d0fe299.ngrok.io/sell/wachat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code,result={}",redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam(value = "code") String code,
                         @RequestParam(value = "returnUrl") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
            //获取access_token包含userInfo得信息
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            //获取微信用户信息
            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,null);

        } catch (WxErrorException e) {
            log.error("【微信网页授权】 {}",e);
            throw new SellException(SellExceptionEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        //获取微信openid
        String openid = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openid;
    }

}
