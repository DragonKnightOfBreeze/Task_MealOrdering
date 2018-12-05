package dk_breeze.utils;

/**
 * 单例模式 - 双重校验锁
 */
public class Singleton {
	private static volatile Singleton instance;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}

/**
 * 单例模式 - 懒汉式
 * 线程不安全。
 */
class Singleton1 {
	private static Singleton1 instance;

	public static Singleton1 getInstance() {
		if (instance == null) {
			instance = new Singleton1();
		}
		return instance;
	}
}

/**
 * 单例模式 - 懒汉式
 * 线程不安全，不允许被实例化。
 */
class Singleton2 {
	private static Singleton2 instance;

	private Singleton2() {
	}

	public static Singleton2 getInstance() {
		if (instance == null) {
			instance = new Singleton2();
		}
		return instance;
	}
}

/**
 * 单例模式 - 饿汉式
 * 线程安全，但效率低。
 */
class Singleton3 {
	private static Singleton3 instance;

	private Singleton3() {
	}

	public static synchronized Singleton3 getInstance() {
		if (instance == null) {
			instance = new Singleton3();
		}
		return instance;
	}
}

/**
 * 单例模式 - 双重校验锁
 * 线程安全，使用双重校验锁，禁止指令重排。
 */
class Singleton4 {
	private static volatile Singleton4 instance;

	private Singleton4() {
	}

	public static Singleton4 getInstance() {
		if (instance == null) {
			synchronized (Singleton4.class) {
				if (instance == null) {
					instance = new Singleton4();
				}
			}
		}
		return instance;
	}
}

/**
 * 单例模式 - 静态内部类
 * 使用静态内部类实现，线程安全。
 */
class Singleton5 {
	private static class SingletonHolder {
		private static final Singleton5 INSTANCE = new Singleton5();
	}

	private Singleton5() {
	}

	public static Singleton5 getInstance() {
		return SingletonHolder.INSTANCE;
	}
}

/**
 * 单例模式 - 枚举
 * 使用枚举，保证线程安全，提供序列化机制
 * 使用方法：
 * SingleInstance.INSTANCE.fun1();
 */
enum Singleton6 {
	Instance;

	public void function1() {
		// do something
	}
}






