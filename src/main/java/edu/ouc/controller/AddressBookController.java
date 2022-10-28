package edu.ouc.controller;

import edu.ouc.common.R;
import edu.ouc.entity.AddressBook;
import edu.ouc.service.impl.AddressBookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Sihang Xie
 * @Description: 地址簿控制层
 * @Date: 2022/10/23 15:33
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    private AddressBookServiceImpl addressBookService;

    // 新增地址
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook) {
        return R.success(addressBookService.saveAdd(addressBook));
    }

    // 地址展示
    @GetMapping("/list")
    public R<List<AddressBook>> getList() {
        return R.success(addressBookService.getList());
    }

    // 设为默认地址
    @PutMapping("/default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook) {
        return R.success(addressBookService.setDefault(addressBook));
    }

    // 获取当前用户的默认地址
    @GetMapping("/default")
    public R<AddressBook> getDefault() {
        return R.success(addressBookService.getDefault());
    }

    // 查询单个地址的信息，用于编辑回显
    @GetMapping("/{id}")
    public R<AddressBook> getAdd(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        if (addressBook != null) {
            return R.success(addressBook);
        }
        return R.error("没有找到该对象");
    }

    // 更新
    @PutMapping
    public R<String> updateAdd(@RequestBody AddressBook addressBook) {
        if (addressBookService.updateById(addressBook)) {
            return R.success("保存成功");
        }
        return R.success("保存失败");
    }

    // 删除
    @DeleteMapping
    public R<String> remove(@RequestParam Long ids) {
        if (addressBookService.removeById(ids)) {
            return R.success("删除成功");
        }
        return R.error("删除失败");
    }
}
