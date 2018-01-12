package com.hason.dtp.tcc.capital.dao;

import com.hason.dtp.tcc.capital.entity.CapitalAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 资金账户 Repository
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/12
 */
public interface CapitalAccountRepository extends JpaRepository<CapitalAccount, Long> {

    /**
     * 根据用户 ID，获取资金账户
     *
     * @param userId 用户 ID
     * @return CapitalAccount
     */
    CapitalAccount findByUserId(Long userId);

}
