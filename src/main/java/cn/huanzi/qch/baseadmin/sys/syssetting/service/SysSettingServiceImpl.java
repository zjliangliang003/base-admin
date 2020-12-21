package cn.huanzi.qch.baseadmin.sys.syssetting.service;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.common.service.CommonServiceImpl;
import cn.huanzi.qch.baseadmin.sys.syssetting.pojo.SysSetting;
import cn.huanzi.qch.baseadmin.sys.syssetting.repository.SysSettingRepository;
import cn.huanzi.qch.baseadmin.sys.syssetting.vo.SysSettingVo;
import cn.huanzi.qch.baseadmin.util.SysSettingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
@Transactional
public class SysSettingServiceImpl extends CommonServiceImpl<SysSettingVo, SysSetting, String> implements SysSettingService{

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private SysSettingRepository sysSettingRepository;

    @Override
    public Result<SysSettingVo> save(SysSettingVo entityVo) {
        //调用父类
        Result<SysSettingVo> result = super.save(entityVo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.getData().setCreateTime(Timestamp.valueOf(sdf.format(result.getData().getCreateTime())));
        result.getData().setUpdateTime(Timestamp.valueOf(sdf.format(result.getData().getUpdateTime())));
        //更新系统设置时同步更新公用静态集合sysSettingMap
        SysSettingUtil.setSysSettingMap(result.getData());

        return result;
    }
}
