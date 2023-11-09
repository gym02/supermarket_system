package com.example.supermarket.mapper;


import com.example.supermarket.entity.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {
	@Insert("insert into files values(null,#{path},#{md5})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	void add(File file);

	@Select("select * from files where md5=#{md5}")
	File getByMd5(String md5);

	@Select("select * from files where id = #{id}")
	File getById(int id);
}
