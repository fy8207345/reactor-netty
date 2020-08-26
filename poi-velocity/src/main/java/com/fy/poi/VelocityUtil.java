package com.fy.poi;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class VelocityUtil {

    public static void main(String[] args) throws IOException {
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("template.html");
        String result = template.render(Dict.create().set("list", Reader.read()));
        System.out.println(result);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(result.getBytes());
        FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\output.html"));
        IoUtil.copy(byteArrayInputStream, fileOutputStream);
        byteArrayInputStream.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
