package com.fy.poi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Pollute implements Serializable {
    private String index;
    private String wname;
    private String sname;
    private String mname;
    private String now;
    private String cuoshi;
    private String manzu;
    private String dname;
    private String zhenggai;
    private String guanli;
    private String jiance;
}
