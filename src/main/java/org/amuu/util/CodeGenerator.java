package org.amuu.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {

    //驱动名
    private String driverName;
   //数据库URL
    private String url;
   //数据库用户名
    private String username;
   //数据库密码
    private String password;
   //开发者
    private String author;
   //表前缀
    private String tablePrefix;
   //表名，多个用逗号隔开
    private String tableName;
   //包名
    private String parent;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public CodeGenerator() {
        super();
    }

    public CodeGenerator(String driverName, String url, String username, String password, String author, String tablePrefix, String tableName, String parent) {
        this.driverName = driverName;
        this.url = url;
        this.username = username;
        this.password = password;
        this.author = author;
        this.tablePrefix = tablePrefix;
        this.tableName = tableName;
        this.parent = parent;
    }

    public void run() {
        try {
            AutoGenerator mpg = new AutoGenerator();
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setDbType(DbType.MYSQL);
            dsc.setDriverName(driverName);
            dsc.setUrl(url);
            dsc.setUsername(username);
            dsc.setPassword(password);
            GlobalConfig gc = new GlobalConfig();
            String projectPath = System.getProperty("user.dir" );
            gc.setOutputDir(projectPath + "/src/main/java" );
            gc.setFileOverride(true);
            gc.setActiveRecord(true);
            // XML 二级缓存
            gc.setEnableCache(false);
            // XML ResultMap
            gc.setBaseResultMap(true);
            // XML columList
            gc.setBaseColumnList(true);
            gc.setAuthor(author);
            gc.setMapperName("%sMapper" );
            gc.setXmlName("%sMapper" );
            gc.setDateType(DateType.ONLY_DATE);
            gc.setSwagger2(true);
            gc.setIdType(IdType.NONE);
            gc.setOpen(false);
            StrategyConfig strategy = new StrategyConfig();
            // 此处可以修改为您的表前缀
            strategy.setTablePrefix(new String[]{tablePrefix});
            // 表名生成策略
            strategy.setNaming(NamingStrategy.underline_to_camel);
            strategy.setEntityLombokModel(true);
            // 需要生成的表
            strategy.setInclude(tableName.split("," ));

            strategy.setRestControllerStyle(true);
            PackageConfig pc = new PackageConfig();
            pc.setParent(parent);
            pc.setEntity("model" );
            pc.setMapper("mapper" );
            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                }
            };

            String templatePath = "/templates/mapper.xml.vm";
            // 自定义输出配置
            List<FileOutConfig> focList = new ArrayList<>();
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath + "/src/main/resources/mapper/" + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            TemplateConfig tc = new TemplateConfig();
            tc.setXml(null);
            //数据源配置
            mpg.setDataSource(dsc);
            //全局配置
            mpg.setGlobalConfig(gc);
            //生成策略配置

            mpg.setStrategy(strategy);
            //包配置
            mpg.setPackageInfo(pc);
            //xml配置
            mpg.setCfg(cfg);
            //
            mpg.setTemplate(tc);
            // 执行生成
            mpg.execute();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

}