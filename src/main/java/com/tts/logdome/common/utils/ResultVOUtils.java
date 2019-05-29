package com.tts.logdome.common.utils;

import com.tts.logdome.vo.ResultVO;

/**
 * @创建人 zp
 * @创建时间 2019/5/28
 * @描述 返回结果工具
 */
public class ResultVOUtils {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("请求成功");
        return resultVO;
    }

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(null);
        resultVO.setCode(0);
        resultVO.setMsg("请求成功");
        return resultVO;
    }

    public static ResultVO error(){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(null);
        resultVO.setCode(-1);
        resultVO.setMsg("请求失败");
        return resultVO;
    }

}
