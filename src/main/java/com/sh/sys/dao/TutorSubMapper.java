package com.sh.sys.dao;

import com.sh.sys.model.TutorSub;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TutorSubMapper {
    int deleteByPrimaryKey(Integer tutorSubId);

    int insert(TutorSub record);

    int insertSelective(TutorSub record);

    TutorSub selectByPrimaryKey(Integer tutorSubId);

    int updateByPrimaryKeySelective(TutorSub record);

    int updateByPrimaryKey(TutorSub record);

    @Select("select * from sys_tutor_sub where tutor_sub_id = #{id} and status=0")
    TutorSub selectById(int id);

    List<TutorSub> selectByIds(List<Integer> ids);

    List<TutorSub> selectByIdsAndLimit(@Param("ids") List<Integer> ids,
                                       @Param("offset")int offset,
                                       @Param("size")int size);

    @Select("select * from sys_tutor_sub where sub_category_id = #{id} and status=0 limit #{offset},#{size}")
    List<TutorSub> selectByCatId(@Param("id") int id,
                                 @Param("offset")int offset,
                                 @Param("size") int size);

    @Select("select count(1) from sys_tutor_sub where sub_category_id = #{id} and status=0")
    int selectCountByCatId(int id);

    List<TutorSub> selectByKeyword(@Param("keyword") String keyword,
                                   @Param("offset")int offset,
                                   @Param("size") int size);

    @Select("select count(1) from sys_tutor_sub where sub_name like concat('%', #{keyword}, '%') or tutor_name like concat('%', #{keyword}, '%') and status=0")
    int selectCountByKeyword(String keyword);

    @Select("select count(*) from sys_tutor_sub")
    int selectCount();

    @Select("select * from sys_tutor_sub order by tutor_sub_id desc limit #{offset},#{size}")
    List<TutorSub> selectByPage(@Param("offset") int offset,
                                @Param("size") int size);

    int updateStatusByIds(@Param("status") int status,@Param("ids") List<Integer> ids);

    @Select("select count(1) from sys_tutor_sub where tutor_id = #{tutorId}")
    int selectCountByTutorId(int tutorId);

    @Select("select * from sys_tutor_sub where tutor_id = #{tutorId} limit #{offset},#{size}")
    List<TutorSub> selectByTutorId(@Param("tutorId") int tutorId,
                                   @Param("offset") int offset,
                                   @Param("size") int size);

    @Update("update sys_tutor_sub set status=1 where tutor_sub_id = #{id}")
    int deleteById(int id);
}