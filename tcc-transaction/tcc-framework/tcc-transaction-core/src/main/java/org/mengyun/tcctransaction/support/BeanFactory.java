
package org.mengyun.tcctransaction.support;

/**
 * Bean 容器的公共接口
 * <p>从 1.2.X 版本中复制过来</p>
 * Created by changmingxie on 11/20/15.
 */
public interface BeanFactory {

    /**
     * 根据 Class 对象获取对应的实例
     *
     * @param var1 Class 对象
     * @param <T> Class 的类型
     * @return Class 对象的实例
     */
    <T> T getBean(Class<T> var1);

    /**
     * 判断 Class 对象 是否存在于容器中
     *
     * @param clazz Class 对象
     * @param <T> Class 的类型
     * @return true 存在，false 不存在
     */
    <T> boolean isFactoryOf(Class<T> clazz);
}
