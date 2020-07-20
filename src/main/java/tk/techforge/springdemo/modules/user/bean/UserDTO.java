package tk.techforge.springdemo.modules.user.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author YANLL
 * @since 2020-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 迁移用户主键
     */
    private String migrateUserId;

    /**
     * 迁移数据来源
     */
    private String migrateSource;

    /**
     * 迁移数据参数
     */
    private String migrateParams;

    /**
     * 登陆名称
     */
    private String loginName;

    /**
     * 组织机构主键
     */
    private String organizationId;

    /**
     * 密码
     */
    private String password;

    /**
     * SHASALT
     */
    private String shasalt;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 创建者用户主键
     */
    private Long createUserId;

    /**
     * ACTIVE、TEMPORARY_BLOCKED、BLOCKED、DELETED
     */
    private String userStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 宿主用户
     */
    private String hostUser;

    private String hireType;


}