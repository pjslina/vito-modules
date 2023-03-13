package com.vito.portal;

import cn.hutool.json.JSONUtil;
import com.vito.portal.dto.UserLayoutAddCmd;
import com.vito.portal.dto.req.ComponentReq;
import com.vito.portal.dto.req.UserLayoutReq;

import java.util.ArrayList;
import java.util.List;

public class JSONParseTest {

    public static void main(String[] args) {
        UserLayoutAddCmd userLayoutAddCmd = new UserLayoutAddCmd();
        UserLayoutReq userLayoutReq = new UserLayoutReq();
        userLayoutReq.setLayoutId(10000L);
        userLayoutReq.setUserId(1000001L);
        userLayoutReq.setTenantId(100002L);
        userLayoutReq.setCreateBy(992299L);
        userLayoutReq.setUpdateBy(880800L);
        List<ComponentReq> componentReqList = new ArrayList<>();
        ComponentReq componentReq = new ComponentReq();
        componentReq.setComponentId(88888L);
        componentReq.setAbscissa(0);
        componentReq.setOrdinate(0);
        componentReq.setWidth(3);
        componentReq.setHigh(3);
        componentReqList.add(componentReq);
        ComponentReq componentReq2 = new ComponentReq();
        componentReq2.setComponentId(99999L);
        componentReq2.setAbscissa(3);
        componentReq2.setOrdinate(0);
        componentReq2.setWidth(6);
        componentReq2.setHigh(3);
        componentReqList.add(componentReq2);
        userLayoutReq.setComponentList(componentReqList);
        userLayoutAddCmd.setUserLayoutReq(userLayoutReq);
        String jsonStr = JSONUtil.toJsonStr(userLayoutAddCmd);
        System.out.println(jsonStr);
    }
}
