package org.mengyun.tcctransaction.support;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂构造器，根据 Class 对象构造出对应的单例工厂，单例工厂负责实例化一个对象
 * <p>从 1.2.X 版本中复制过来</p>
 * Created by changming.xie on 2/23/17.
 */
public final class FactoryBuilder {


    private FactoryBuilder() {

    }

    /** 容器列表 */
    private static List<BeanFactory> beanFactories = new ArrayList<BeanFactory>();

    /** 以 Class 对象为 key，映射 Class 对象对应的单例工厂 */
    private static ConcurrentHashMap<Class, SingeltonFactory> classFactoryMap = new ConcurrentHashMap<Class, SingeltonFactory>();

    /**
     * 获取 Class 对象的单例工厂
     *
     * @param clazz Class 对象
     * @param <T> Class 的类型
     * @return 单例工厂
     */
    public static <T> SingeltonFactory<T> factoryOf(Class<T> clazz) {

        if (!classFactoryMap.containsKey(clazz)) {

            // 遍历 Bean 容器，为 clazz 创建单例工厂，并与之映射
            for (BeanFactory beanFactory : beanFactories) {
                // 如果在容器中找到实例，根据 Class 对象与实例创建单例工厂，并创建映射关系
                if (beanFactory.isFactoryOf(clazz)) {
                    classFactoryMap.putIfAbsent(clazz, new SingeltonFactory<T>(clazz, beanFactory.getBean(clazz)));
                }
            }

            // 如果找不到实例，根据 Class 对象创建单例工厂，并创建映射关系
            if (!classFactoryMap.containsKey(clazz)) {
                classFactoryMap.putIfAbsent(clazz, new SingeltonFactory<T>(clazz));
            }
        }

        // 返回 clazz 对应的单例工厂
        return classFactoryMap.get(clazz);
    }

    /**
     * 把容器添加到 FactoryBuilder
     *
     * @param beanFactory
     */
    public static void registerBeanFactory(BeanFactory beanFactory) {
        beanFactories.add(beanFactory);
    }


    /**
     * 单例工厂，根据类的全限定名进行实例化
     *
     * @param <T>
     */
    public static class SingeltonFactory<T> {

        /** 类名对应的实例 */
        private volatile T instance = null;

        /** 类名 */
        private String className;

        public SingeltonFactory(Class<T> clazz, T instance) {
            this.className = clazz.getName();
            this.instance = instance;
        }

        public SingeltonFactory(Class<T> clazz) {
            this.className = clazz.getName();
        }

        /**
         * 如果 instance 为 null，根据类名在容器中查找实例，找到则保存到 instance
         *
         * @return
         */
        public T getInstance() {

            if (instance == null) {
                synchronized (SingeltonFactory.class) {
                    if (instance == null) {
                        try {
                            // 获取当前线程的类加载器
                            ClassLoader loader = Thread.currentThread().getContextClassLoader();

                            // 根据类名，加载 Class 对象
                            Class<?> clazz = loader.loadClass(className);

                            // 遍历 Bean 容器，找出 clazz 对应的实例
                            for (BeanFactory beanFactory : beanFactories) {
                                if (beanFactory.isFactoryOf(clazz)) {
                                    // 找到实例后，保存起来
                                    instance = (T) beanFactory.getBean(clazz);
                                }
                            }
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to create an instance of " + className, e);
                        }
                    }
                }
            }

            return instance;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;

            SingeltonFactory that = (SingeltonFactory) other;

            if (!className.equals(that.className)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return className.hashCode();
        }
    }
}