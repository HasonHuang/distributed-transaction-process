package com.hason.dtp.tcc.integral.dao;

import com.hason.dtp.tcc.integral.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 积分 Repository
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/8
 */
public interface PointRepository extends JpaRepository<Point, Long> {

    /**
     * 根据用户ID，获取积分记录
     *
     * @param userId 用户ID
     * @return Point
     */
    Point findByUserId(Long userId);

}
