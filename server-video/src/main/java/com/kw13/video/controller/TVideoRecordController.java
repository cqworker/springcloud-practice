package com.kw13.video.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kw13.common.BaseResponse;
import com.kw13.common.ResponseGenerator;
import com.kw13.common.constant.EntityStatus;
import com.kw13.util.LoginInfoUtil;
import com.kw13.video.entity.TDoctor;
import com.kw13.video.service.ITDoctorService;
import com.tencentyun.TLSSigAPIv2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.kw13.common.BaseController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 视频记录表 前端控制器
 * </p>
 *
 * @author cq
 * @since 2019-08-20
 */
@Controller
@RequestMapping("/video")
public class TVideoRecordController extends BaseController {

    @Autowired
    private Environment env;

    @Autowired
    private ITDoctorService doctorService;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 发起视频呼叫
     *
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public BaseResponse search() {
        HashMap<String, Object> data = new HashMap<>();

        String userId = LoginInfoUtil.getLoginInfo().getUserSysid();
        long sdkAppID = env.getProperty("wechat.rtc.sdkappid", long.class);
        String key = env.getProperty("rtc.key", String.class);
        long expire = env.getProperty("wechat.rtc.expire", long.class);

        TLSSigAPIv2 api = new TLSSigAPIv2(sdkAppID, key);
        String userSig = api.genSig(userId, expire);
//获取在线医生名单
        List<TDoctor> availableDoctors = doctorService.list(Wrappers.<TDoctor>lambdaQuery()
                .eq(TDoctor::getStatus, EntityStatus.DOCTOR_AVAILABLE));
//调度到预计用时最短的队列等候医生
        int queueSize = 0;
        int roomId = 0;
        TDoctor doctorInfo = null;
        if (availableDoctors != null && availableDoctors.size() > 0) {
            for (TDoctor doctor : availableDoctors) {
                List<String> patientList = new ArrayList<>();
                StringBuffer sb = new StringBuffer();
                Integer sysId = doctor.getSysId();
                sb.append("kw-video-").append(sysId).append("-queue");
                ArrayList<String> list = (ArrayList<String>) redisTemplate.opsForValue().get(sb.toString());
                //为医生初始化排队队列
                if (list == null) {
                    redisTemplate.opsForValue().set(sb.toString(),patientList);
                    doctorInfo = doctor;
                    roomId = doctor.getSysId();
                    break;
                }else if (list.size() == 0) {
                    //当前医生无其他人排队
                    doctorInfo = doctor;
                    roomId = doctor.getSysId();
                    break;
                } else {
                    //筛选出队伍长度最短
                    queueSize = list.size() < queueSize ? list.size() : queueSize;
                    doctorInfo = doctor;
                    roomId = doctor.getSysId();
                }
            }
        }
        data.put("roomId", roomId);
        data.put("userSig", userSig);
        data.put("userId", userId);
        data.put("sdkAppId", sdkAppID);
        data.put("doctorInfo", doctorInfo);
        //当前队伍长度
        data.put("queueSize",queueSize);
        //userSin有效期/秒
        data.put("expire",expire);

        return ResponseGenerator.genSuccessResult(data);
    }
}
