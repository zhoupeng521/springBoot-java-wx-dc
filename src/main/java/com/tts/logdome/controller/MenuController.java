package com.tts.logdome.controller;

import com.tts.logdome.common.config.WechatAccountConfig;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 zp
 * @创建时间 2019/8/7
 * @描述 自定义菜单
 */
@RestController
public class MenuController {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Autowired
    private WxMpService wxMpService;

    @RequestMapping("/setMenu")
    public String setMenu(){
        //1、根据appid和appsecret和回调地址配置微信授权
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(wechatAccountConfig.getMpAppId());
        wxMpInMemoryConfigStorage.setSecret(wechatAccountConfig.getMpAppSecret());
        wxMpService.setWxMpConfigStorage(wxMpInMemoryConfigStorage);

        //2、自定义菜单；menu对象是总的按钮,button是具体的按钮
        WxMenu wxMenu = new WxMenu();
        WxMenuButton wxMenuButton = new WxMenuButton();
        wxMenuButton.setType(WxConsts.MenuButtonType.VIEW);
        wxMenuButton.setName("你是猪");
        wxMenuButton.setUrl("http://www.baidu.com");
        WxMenuButton wxMenuButton2 = new WxMenuButton();
        wxMenuButton2.setType(WxConsts.MenuButtonType.VIEW);
        wxMenuButton2.setName("你是狗");
        wxMenuButton2.setUrl("http://www.4399.com");
        wxMenu.getButtons().add(wxMenuButton);
        wxMenu.getButtons().add(wxMenuButton2);
        String result = wxMenu.toJson();

        try {
            wxMpService.getMenuService().menuCreate(result);
            return "SUCCESS"+" "+result;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "FAILURE"+result;

    }

}
