package com.fy.poi;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.fy.poi.model.Pollute;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.util.List;

public class VelocityUtil {

    public static void main(String[] args) throws IOException {
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template htmlTemplate = engine.getTemplate("template.html");
        List<Pollute> source = Reader.read();
        Dict param = Dict.create().set("list", source);
        String result = htmlTemplate.render(param);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(result.getBytes());
        FileOutputStream htmlOutputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\output_html.html"));
        IoUtil.copy(byteArrayInputStream, htmlOutputStream);

        Template sqlTemplate = engine.getTemplate("template.sql");
        result = sqlTemplate.render(param);
        byteArrayInputStream = new ByteArrayInputStream(result.getBytes());
        FileOutputStream sqlOutputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\output_sql.sql"));
        IoUtil.copy(byteArrayInputStream, sqlOutputStream);
        sqlOutputStream.flush();
        sqlOutputStream.close();
        htmlOutputStream.flush();
        htmlOutputStream.close();

        byteArrayInputStream.close();
    }
}
