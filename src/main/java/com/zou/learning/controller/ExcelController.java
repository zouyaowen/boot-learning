package com.zou.learning.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zou.learning.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author zou
 * @date 2020-03-06
 */
@Slf4j
@RestController
public class ExcelController {
    @PostMapping("/importExcel")
    public void importExcel(MultipartFile file) {
        if (file == null) {
            throw new RuntimeException("文件缺失");
        }
        ArrayList<UserDO> userDOList = new ArrayList<>();
        try {
            EasyExcel.read(file.getInputStream(), UserDO.class, new AnalysisEventListener<UserDO>() {
                /**
                 * 批处理阈值
                 */
                private static final int BATCH_COUNT = 100;


                @Override
                public void invoke(UserDO userDO, AnalysisContext analysisContext) {
                    userDOList.add(userDO);
                    if (userDOList.size() >= BATCH_COUNT) {
                        handleData();
                        userDOList.clear();
                    }
                }

                private void handleData() {
                    //TODO 具体业务处理
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    handleData();
                    userDOList.clear();
                }


            }).sheet().doRead();
        } catch (IOException e) {
            log.error("Excel解析出错", e);
        }
    }

}
