package com.shop.framework.context;


/**
 * Context的工厂方法。以单例模式来实现对context的构建。
 * 
 * 
 */
public class ContextHolder {
	/**
	 * 线程化的属性文件处理器。
	 */
	private static ThreadLocal<Context> configHolder = new InheritableThreadLocal<Context>();

	/**
	 * 得到context。
	 * 
	 * @return 上下文信息
	 */
	public static Context getContext() {
		if (configHolder.get() == null) {
			configHolder.set(new Context());
		}
		return configHolder.get();
	}

	public static void clearContext() {
		configHolder.set(null);
	}

	/**
	 * 根据配置属性，创建一个context对象。
	 * 
	 * 
	 */
	public static void setContext(Context context) {
		configHolder.set(context);
	}
}
