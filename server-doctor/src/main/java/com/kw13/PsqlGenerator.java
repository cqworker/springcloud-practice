package com.kw13;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * postgresql 代码生成器
 * </p>
 *
 * @author cq
 * @since 2019-08-16
 */
public class PsqlGenerator {

    /**
     * <p>
     * 根据控制台提示输入
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        //        String dburl = "jdbc:postgresql://47.112.10.53:5432/zy";
//        String username = "pg";
//        String password = "97f0-54b5@7bc1a6L";

        String dburl = "jdbc:postgresql://127.0.0.1:5432/mytest";
        String username = "kw_video";
        String password = "root";
        String drivername = "org.postgresql.Driver";
        //模块上级目录
        String parent = "com.kw13";

        String author = "cq";
        //controller父类
        String basecontroller = "com.kw13.common.BaseController";
        //entity父类
        String baseentity = "com.kw13.common.BaseEntity";
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //文件输出位置
        gc.setOutputDir(projectPath + "/src/main/java");
        System.err.println("文件生成位置: " + projectPath + "/src/main/java");
//      gc.setOutputDir(projectPath + "/mybatis-plus-sample-generator/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dburl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(drivername);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));

        pc.setParent(parent);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return projectPath + "/mybatis-plus-sample-generator/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 全局大写命名 ORACLE 注意
        // strategy.setCapitalMode(true);
        // 设置统一的表前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");
        // 表名生成策略,驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 字段生成策略,驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude(scanner("表名"));
        // 排除xx表
        // strategy.setExclude(new String[]{"test"});
        // 自定义实体父类
        strategy.setSuperEntityClass(baseentity);
        // 自定义实体，公共字段
//        strategy.setSuperEntityColumns("id");
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.authentication.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.authentication.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.authentication.TestServiceImpl");
        // 自定义 controller 父类
        strategy.setSuperControllerClass(basecontroller);
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);
        strategy.setEntityLombokModel(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
