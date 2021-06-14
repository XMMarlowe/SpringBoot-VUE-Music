package com.marlowe.music.commons;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器
 *
 * @author Uncle
 * @date 2020.02.01 21:42:32
 */
public class CodeGenerator_zhg {


    public static void main(String[] args) {
        String parentPackage = "com.unclezs.permission.module.system";
        String[] table = scanner("表名，多个英文逗号分割").split(",");
        generate(table,parentPackage);
    }

    /**
     * 数据源配置
     *
     * @return /
     */
    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/permission?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        return dsc;
    }

    /**
     * 生成代码
     *
     * @param parentPackage 父包名
     * @param table         哪些表
     */
    private static void generate(String[] table, String parentPackage) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/resources/code/java");
        gc.setAuthor("Uncle");
        gc.setIdType(IdType.ASSIGN_UUID);
        gc.setOpen(false);
        gc.setSwagger2(true);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setBaseResultMap(true);
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        mpg.setDataSource(getDataSourceConfig());

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage);
        pc.setEntity("model");
        mpg.setPackageInfo(pc);

        // 配置Java代码生产模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("/templates/java/model.java");
        templateConfig.setController("/templates/java/controller.java");
        templateConfig.setMapper("/templates/java/mapper.java");
        templateConfig.setXml("/templates/java/mapper.xml");
        templateConfig.setService("/templates/java/service.java");
        templateConfig.setServiceImpl("/templates/java/serviceImpl.java");
        //Vue代码生成
        String jsTemplate = "/templates/vue/js.ftl";
        String vueTemplate = "/templates/vue/vue.ftl";
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> vueList = new ArrayList<>();
        //Vue文件
        vueList.add(new FileOutConfig(vueTemplate) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/src/main/resources/code/vue/views/%s.vue", projectPath, tableInfo.getEntityName().toLowerCase());
            }
        });
        //js文件
        vueList.add(new FileOutConfig(jsTemplate) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/src/main/resources/code/vue/js/%s.js", projectPath, tableInfo.getEntityName().toLowerCase());
            }
        });
        cfg.setFileOutConfigList(vueList);
        mpg.setCfg(cfg);

        // 配置自定义输出模板
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(table);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StrUtil.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}