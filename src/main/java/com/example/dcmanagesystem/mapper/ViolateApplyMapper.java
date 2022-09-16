package com.example.dcmanagesystem.mapper;

import com.example.dcmanagesystem.bean.ViolateApply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@Component
public interface ViolateApplyMapper {
    void insertViolateApply(ViolateApply violateApply);
    List<ViolateApply> queryAllViolateApply();
    ViolateApply queryViolateApplyById(Integer id_);
    void updateViolateApply(ViolateApply violateApply);
    void deleteViolateApplyById(Integer id_);

    List<ViolateApply> queryViolateApplyByCustomerIds(List<Integer> customer_ids);

    List<ViolateApply> queryViolateApplyByViolateStatus(Integer violate_status);

    List<ViolateApply> queryViolateApplyByViolateReason(List<Integer> reasons);

    List<ViolateApply> queryViolateApplyByViolateLevel(Integer violate_level);

    List<ViolateApply> queryViolateApplyByApplyPerson(String apply_person);

    ViolateApply queryLastViolateApplyByCustomerId(Integer customer_id);
}
