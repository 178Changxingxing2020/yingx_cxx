package com.baizhi.cxx.dto;


import com.baizhi.cxx.entity.Log;
import com.baizhi.cxx.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDto implements Serializable {

    private Integer page;   // 当前页
    private Integer total;  // 总页数
    private Integer records;    // 总行数
    private List<Log> rows;  //该页总数据行
}
