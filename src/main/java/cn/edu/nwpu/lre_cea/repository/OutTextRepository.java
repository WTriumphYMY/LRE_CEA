package cn.edu.nwpu.lre_cea.repository;

import cn.edu.nwpu.lre_cea.domain.OutputResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName OutTextRepository
 * @Author: wkx
 * @Date: 2019/3/24 13:48
 * @Version: v1.0
 * @Description:
 */
@Repository
public interface OutTextRepository extends JpaRepository<OutputResult, Integer> {
    OutputResult findOutputResultByRocketName(String rocketName);
}
