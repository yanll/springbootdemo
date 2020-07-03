package tk;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/2/18
 */
//@Ignore
@Slf4j
public class GenTest extends Base {

    private static final boolean GenDTO = true;
    private static final boolean GenService = true;
    private static final boolean GenController = true;
    private static final String pkg = "tk.techforge.springdemo";
    private static final String pkg_filepath = "/src/main/java/tk/techforge/springdemo";


    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Test
    public void gen() {
        generate("tbl_", "user", "tbl_user");
    }

    /**
     * @param tablePrefix 表名前缀（用于生成的实体消除前缀）
     * @param module      生成模块的目录名
     * @param tableNames  表名
     */
    private void generate(String tablePrefix, String module, String... tableNames) {
        if (module == null || module.length() == 0) {
            System.out.println("模块不能为空！");
            return;
        }
        if (tableNames == null || tableNames.length == 0) {
            System.out.println("没有生成！");
            return;
        }
        for (String name : tableNames) {
            if (name == null || name.trim().length() == 0) {
                System.out.println("表名不能为空！");
                return;
            }
            if (!name.startsWith(tablePrefix)) {
                System.out.println("表名必须以" + tablePrefix + "为前缀！");
                return;
            }
        }
        System.out.println("开始生成！");

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("YANLL");
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setServiceName("%sService");
        gc.setEntityName("%s");
        mpg.setGlobalConfig(gc);
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, TableField tableField) {
                return super.processTypeConvert(globalConfig, tableField);
            }
        });
        mpg.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(pkg + ".modules");
        pc.setModuleName(module);
        pc.setEntity("bean");
        mpg.setPackageInfo(pc);


        // 自定义输出配置
        List<FileOutConfig> list = new ArrayList<>();


        String dto_flag = "DTO";
        Map<String, Object> params = new HashMap<>();
        params.put("dto_flag", dto_flag);
        params.put("dto_package", pkg + ".modules." + module + ".bean");

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                setMap(params);
            }
        };
        String entity_tmp_path = "/templates/mybatisplus/entity.java.vm.my";
        String controller_tmp_path = "/templates/mybatisplus/controller.java.my.vm";
        if (GenDTO) {
            String dto_tmp_path = "/templates/mybatisplus/dto.java.vm.my";

            // 自定义配置会被优先输出
            list.add(new FileOutConfig(dto_tmp_path) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath + pkg_filepath + "/modules/" + module + "/bean/" + tableInfo.getEntityName() + dto_flag + StringPool.DOT_JAVA;
                }
            });
        }
        list.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(list);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setEntity(entity_tmp_path);
        templateConfig.setController(controller_tmp_path);
        if (!GenService) {
            templateConfig.setService(null);
            templateConfig.setServiceImpl(null);
        }
        if (!GenController) {
            templateConfig.setController(null);
        }
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(true);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableNames);
        strategy.setTablePrefix(tablePrefix);
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);
        mpg.execute();
    }


}
