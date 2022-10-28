package edu.ouc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.ouc.entity.AddressBook;

import java.util.List;

/**
 * @Author: Sihang Xie
 * @Description: 地址簿业务层接口
 * @Date: 2022/10/23 15:30
 * @Version: 0.0.1
 * @Modified By:
 */
public interface IAddressBookService extends IService<AddressBook> {

    // 新增地址
    AddressBook saveAdd(AddressBook addressBook);

    // 地址展示
    List<AddressBook> getList();

    // 设为默认地址
    AddressBook setDefault(AddressBook addressBook);

    // 获取当前用户的默认地址
    AddressBook getDefault();
}
