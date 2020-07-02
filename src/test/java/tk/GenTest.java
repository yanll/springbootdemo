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


    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Test
    public void gen() {
        generate("user", "tbl_", "tbl_user");
    }

    /**
     * @param module      模块
     * @param tablePrefix 表名前缀（用于生成的实体消除前缀）
     * @param tableNames  表名
     */
    private void generate(String module, String tablePrefix, String... tableNames) {
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
        pc.setParent("tk.techforge.springdemo.modules");
        pc.setModuleName(module);
        pc.setEntity("bean");
        mpg.setPackageInfo(pc);


        // 自定义输出配置
        List<FileOutConfig> list = new ArrayList<>();


        String dto_flag = "DTO";
        Map<String, Object> params = new HashMap<>();
        params.put("dto_flag", dto_flag);
        params.put("dto_package", "tk.techforge.springdemo.modules." + module + ".bean");

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                setMap(params);
            }
        };
        String entity_tmp_path = "/templates/mybatisplus/entity.java.vm.my";
        if (GenDTO) {
            String dto_tmp_path = "/templates/mybatisplus/dto.java.vm.my";

            // 自定义配置会被优先输出
            list.add(new FileOutConfig(dto_tmp_path) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath + "/src/main/java/tk/techforge/springdemo/modules/" + module + "/bean/" + tableInfo.getEntityName() + dto_flag + StringPool.DOT_JAVA;
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
        if (!GenService) {
            templateConfig.setService(null);
            templateConfig.setServiceImpl(null);
        }
        templateConfig.setController(null);
        templateConfig.setXml(null);
        templateConfig.setEntity(entity_tmp_path);
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
