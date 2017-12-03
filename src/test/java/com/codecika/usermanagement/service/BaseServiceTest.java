package com.codecika.usermanagement.service;

import com.codecika.usermanagement.persistence.repository.BaseRepository;
import com.codecika.usermanagement.converter.BaseVoConverter;
import com.codecika.usermanagement.persistence.domain.Base;
import com.codecika.usermanagement.vo.BaseVO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by rizkimuhammad on 4/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseServiceTest<T extends Base, V extends BaseVO, Z> {

    @Autowired
    BaseService<T, V, Z> baseService;

    @Autowired
    BaseVoConverter<Z, V, T> voConverter;

    @Autowired
    BaseRepository<T> baseRepository;

    protected void add(Z vo) {
        T t = voConverter.transferVOToModel(vo, null);
        baseService.add(t);
    }

    protected void update(T t, Z vo) {
        baseService.update(t, vo);
    }

    protected void cleanTestData(String secureId) {
        baseService.delete(secureId);
    }

    protected void cleanTestData(T t) {
        baseRepository.delete(t);
    }
}
