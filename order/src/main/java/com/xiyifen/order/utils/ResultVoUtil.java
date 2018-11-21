package com.xiyifen.order.utils;


import com.xiyifen.order.VO.ResultVO;

public class ResultVoUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
