package me.mclee.v2ray.panel.entity.convertor;

/**
 * 类转换器
 *
 * @param <S> source
 * @param <T> target
 */
public interface Converter<S, T> {

    /**
     * 转换对象
     *
     * @param source 源对象
     * @return 转换结果
     */
    T doForward(S source);

    /**
     * 反向转换
     *
     * @param target 结果对象
     * @return 反向转换结果
     */
    S doBackward(T target);
}
