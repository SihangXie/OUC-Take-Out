package edu.ouc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ouc.common.BaseContext;
import edu.ouc.entity.AddressBook;
import edu.ouc.mapper.AddressBookMapper;
import edu.ouc.service.IAddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sihang Xie
 * @Description: 地址簿业务层接口的实现类
 * @Date: 2022/10/23 15:31
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements IAddressBookService {

    // 新增地址
    @Override
    public AddressBook saveAdd(AddressBook addressBook) {
        // 获取当前登录用户的ID，并设为当前地址的userId
        addressBook.setUserId(BaseContext.getCurrentUserId());
        // 调用数据层保存新地址
        this.save(addressBook);
        return addressBook;
    }

    // 地址展示
    @Override
    public List<AddressBook> getList() {
        // 获取当前用户ID
        Long userId = BaseContext.getCurrentUserId();
        // 查询这个用户所有的地址信息
        LambdaQueryWrapper<AddressBook> lqw = new LambdaQueryWrapper<>();
        lqw.eq(userId != null, AddressBook::getUserId, userId);
        return this.list(lqw);
    }

    // 设为默认地址
    @Override
    public AddressBook setDefault(AddressBook addressBook) {
        // 【我的思路】
//        // 获取想要设为默认地址的地址ID
//        Long id = addressBook.getId();
//        // 获取当前登陆的用户ID
//        Long userId = BaseContext.getCurrentUserId();
//
//        // 先查询当前用户的地址里字段is_default为1的记录
//        LambdaQueryWrapper<AddressBook> lqw = new LambdaQueryWrapper<>();
//        lqw.eq(AddressBook::getIsDefault, 1);
//        lqw.eq(AddressBook::getUserId, userId);
//        AddressBook defaultAdd = this.getOne(lqw);
//
//        // 如果已经存在默认地址，先把它设为0
//        if (defaultAdd != null) {
//            // 取消它的默认地址
//            defaultAdd.setIsDefault(0);
//            // 更新到数据库
//            this.updateById(defaultAdd);
//        }
//
//        // 现在可以直接设为默认地址
//        LambdaQueryWrapper<AddressBook> newLqw = new LambdaQueryWrapper<>();
//        newLqw.eq(AddressBook::getUserId, userId);
//        newLqw.eq(AddressBook::getId, id);
//        defaultAdd = this.getOne(newLqw);
//        defaultAdd.setIsDefault(1);
//        this.updateById(defaultAdd);
//
//        return defaultAdd;

        // 【老师思路】
        // 1.先把当前用户的所有地址的is_default字段设为0
        // 1.1 创建更新条件封装器
        LambdaUpdateWrapper<AddressBook> luw = new LambdaUpdateWrapper<>();
        // 1.2 添加更新条件：指定当前登录用户ID
        luw.eq(AddressBook::getUserId, BaseContext.getCurrentUserId());
        // 1.3 添加更新添加：把所有记录的is_default字段设为0
        luw.set(AddressBook::getIsDefault, 0);
        // 1.4 调用数据层更新方法，入参是更新条件封装器luw
        this.update(luw);

        // 2.再把当前传入的地址设为默认地址
        // 2.1 把传入的地址对象的isDefault属性设为1
        addressBook.setIsDefault(1);
        // 2.2 调用数据层的更新方法，注意null不会参与更新，只会更新不为null的字段，详见下面的SQL语句
        this.updateById(addressBook);   //UPDATE address_book SET is_default=?, update_time=?, update_user=? WHERE id=?
        return addressBook;
    }

    // 获取当前用户的默认地址
    @Override
    public AddressBook getDefault() {
        // 1.获取当前用户ID
        Long userId = BaseContext.getCurrentUserId();
        // 2.创建查询条件封装器
        LambdaQueryWrapper<AddressBook> lqw = new LambdaQueryWrapper<>();
        // 3.添加查询条件：根据用户ID进行查询
        lqw.eq(userId != null, AddressBook::getUserId, userId);
        // 4.添加查询条件：查询是默认地址的地址
        lqw.eq(AddressBook::getIsDefault, 1);
        // 5.调用数据层根据查询条件封装器查询
        return this.getOne(lqw);
    }


}
