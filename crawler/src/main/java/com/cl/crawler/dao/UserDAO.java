package com.cl.crawler.dao;




import com.cl.crawler.model.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;
@Mapper
@Component
public interface UserDAO {

    int insert(UserDO record);

    UserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKey(UserDO record);

    int deleteByPrimaryKey(Long id);

    List<UserDO> selectList(Map<String, Object> map);

    int countList(Map<String, Object> map);

    UserDO selectByUserNameAndPassword(Map<String, Object> map);

}