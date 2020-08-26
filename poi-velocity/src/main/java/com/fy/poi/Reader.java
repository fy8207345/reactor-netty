package com.fy.poi;

import cn.hutool.core.lang.Dict;
import cn.hutool.poi.excel.ExcelReader;
import com.fy.poi.model.Pollute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Reader {

    public static void main(String[] args) {

    }

    public static List<Pollute> read(){
        ExcelReader excelReader = new ExcelReader("飞翔清单无组织清单2020-8-10.xls", 0);
        Map<String, String> header = new HashMap<String, String>();
        header.put("序号", "id");
        header.put("分厂/工序", "wname");
        header.put("无组织排放源名称", "sname");
        header.put("物料名称", "mname");
        header.put("目前企业实际情况", "now");
        header.put("措施是否满足", "cuoshi");
        header.put("现场治理效果是否满足无可见烟尘外溢要求", "manzu");
        header.put("治理设备名称", "dname");
        header.put("建议整改措施", "zhenggai");
        header.put("管理措施", "guanli");
        header.put("监测设备建议", "jiance");
        excelReader.setHeaderAlias(header);
        List<Pollute> pollutes = excelReader.read(1, 2, Pollute.class);
        Random random = new Random();
        for(Pollute pollute : pollutes){
            pollute.setTsp(random.nextDouble() * 100);
        }
        return pollutes;
    }
}
