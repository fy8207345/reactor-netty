package com.fy.poi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class Pollute implements Serializable {

    private String id;
    //工序
    private String wname;
    //无组织排放源名称
    private String sname;
    //物料名称
    private String mname;
    //目前企业实际情况
    private String now;
    //措施是否满足
    private String cuoshi;
    //现场治理效果是否满足无可见烟尘外溢要求
    private String manzu;
    //治理设备
    private String dname;
    //治理设备状态
    private int dstate = 1;
    //建议整改措施
    private String zhenggai;
    //管理措施
    private String guanli;
    //监测设备建议
    private String jiance;
    //tsp
    private double tsp;
    //生产设备
    private String pname = "";
    //生产设备状态
    private int pstate = 1;
}
