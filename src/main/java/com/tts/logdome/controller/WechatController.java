package com.tts.logdome.controller;

import com.tts.logdome.common.enums.SellExceptionEnum;
import com.tts.logdome.common.exception.SellException;
import com.tts.logdome.common.utils.ResultVOUtils;
import com.tts.logdome.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
        String url = "http://02ed3bac.ngrok.io/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code,result={}",redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam(value = "code") String code,
                         @RequestParam(value = "state") String state){
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
        return "redirect:" + state + "?openid=" + openid;
    }

    @GetMapping("/sendSms")
    @ResponseBody
    public ResultVO<Void> sendSms() throws WxErrorException {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setToUser("");
        templateMessage.setTemplateId("j39ldhv2EUrI_9ps_PSbYARJnVTRpTVC8f7svxoxNh4");
        List<WxMpTemplateData> data = new ArrayList<>();
        WxMpTemplateData wxMpTemplateData = new WxMpTemplateData();
//        wxMpTemplateData.setName();
        wxMpTemplateData.setValue("12345678");
        wxMpTemplateData.setColor("#173177");
        templateMessage.setData(data);
        String s = wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        return ResultVOUtils.success(s);
    }

}
