package edu.ouc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ouc.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Sihang Xie
 * @Description: 地址簿的数据层
 * @Date: 2022/10/23 15:29
 * @Version: 0.0.1
 * @Modified By:
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
