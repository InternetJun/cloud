package com.example.stock.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import org.junit.Test;

import java.util.Collections;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 可快速生成mapper，Service，entity等实体类
 * @time: 2023/12/14 16:03
 */
public class CodeGenerate {
    @Test
    public void run() {

        FastAutoGenerator.create(
                        //数据源配置，url需要修改
                        new DataSourceConfig.Builder("url","username","password")
                                .dbQuery(new MySqlQuery())
                                .schema("schema")
                                .typeConvert(new MySqlTypeConvert())
                                .keyWordsHandler(new MySqlKeyWordsHandler())
                )

                //全局配置
                .globalConfig(builder -> {
                    builder.author("wuKeFan") // 设置作者
                            //.disableOpenDir()//禁止打开输出目录
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir")+"/src/main/java"); // 指定输出目录
                })

                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.wkf.workrecord.tools.autocode") // 设置父包名，根据实制项目路径修改
                            .moduleName("web")      // 父包名路径下再新建的文件夹
                            .entity("entity")         // 后面这些是sys文件夹里新建的各分类文件夹
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            //.other("other")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"/src/main/java/com/wkf/workrecord/tools/autocode/web/mapper/xml")); // 存放mapper.xml路径
                })

                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("jm_fxy_apply_staging_plan_code") // 设置需要生成的表名
                            .addTablePrefix("jm_") // 设置过滤表前缀
                            .entityBuilder() //实体类配置
                            .enableLombok() //使用lombok
                            .enableTableFieldAnnotation()//实体类字段注解
                            .controllerBuilder()//controller配置
                            .enableRestStyle()//开启restcontroller
                            .mapperBuilder()
                            .enableMapperAnnotation()//开启mapper注解
                            .enableBaseResultMap()//启用 BaseResultMap 生成
                            .enableBaseColumnList();//启用 BaseColumnList
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
