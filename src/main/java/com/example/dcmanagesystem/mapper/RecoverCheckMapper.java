package com.example.dcmanagesystem.mapper;

import com.example.dcmanagesystem.bean.RecoverCheck;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@Component
public interface RecoverCheckMapper {
    void insertRecoverCheck(RecoverCheck recoverCheck);
    List<RecoverCheck> queryAllRecoverCheck();
    RecoverCheck queryRecoverCheckById(Integer violate_id);
    void updateRecoverCheck(RecoverCheck recoverCheck);
    void deleteRecoverCheckById(Integer violate_id);
}
