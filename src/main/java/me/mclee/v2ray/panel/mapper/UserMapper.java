package me.mclee.v2ray.panel.mapper;

import java.util.List;
import me.mclee.v2ray.panel.entity.User;
import me.mclee.v2ray.panel.entity.UserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(UserExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(UserExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(User record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(User record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<User> selectByExampleWithLock(UserExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<User> selectByExample(UserExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    User selectByPrimaryKeyWithLock(Integer id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    User selectByPrimaryKey(Integer id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(User record);
}