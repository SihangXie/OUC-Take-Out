package edu.ouc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ouc.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Sihang Xie
 * @Description: Category的数据层
 * @Date: 2022/10/2 14:36
 * @Version: 0.0.1
 * @Modified By:
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
