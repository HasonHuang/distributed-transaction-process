package com.hason.dtp.tcc.capital.dao;

import com.hason.dtp.tcc.capital.entity.CapitalOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 资金账户订单 Repository
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/13
 */
public interface CapitalOrderRepository extends JpaRepository<CapitalOrder, Long> {
}
