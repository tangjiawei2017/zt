package com.shop.utils;

/**
 * 常量配置类
 * 
 */
public class IConstant {
	/**
	 * 微信支付code
	 */
	public final static Integer WECHAT_PAYTYPE_CODE = 0x11;

	/**
	 * 支付宝支付code
	 */
	public final static Integer ALIPAY_PAYTYPE_CODE = 0x12;

	/**
	 * 货到付款
	 */
	public final static Integer CASH_ON_DELIVERY = 0x13;

	// session常量
	/**
	 * 当前登录session id
	 */
	public final static String LOGIN_MEMEBER_ID_SESSION = "members_id";
	/**
	 * 当前登录 session account
	 */
	public final static String LOGIN_MEMBER_ACCOUNT_SESSION = "members_account";

	public final static String LOGIN_MEMBER_ERRORCOUNT_SESSION = "login_error_count";

	public final static String CART_PRODUCTITEMS_SESSION = "productItems_cart";

	/** 下单地址id存储 **/
	public final static String ORDER_ADDRESS_ID_SESSION = "order_address_id";

	/** 购物车选中的商品 **/
	public final static String CART_SELECT_PRODUCTITEMS_SESSION = "productItems_select_cart";

	/** 是否读取cookie中的cart **/
	public final static String HAS_READCOOKIECART_SESSION = "readCookie_cart";

	/**
	 * 是否清除cookie中的cart
	 */
	public final static String HAS_CLEARCOOKIECART_SESSION = "clearCookie_cart";

	/** 购物车 cookie **/
	public final static String CART_PRODUCTITEMS_COOKIE = "productItems_cart";

	/** 商品列表页商品名称最大长度 **/
	public final static Integer PRODUCT_LIST_NAME_MAX_LENGTH = 6;

	// 服务级网站常量配置
	/** 订单配送时间类型字段 数据保存在 T_SITECONFIG表中 **/
	public final static String ORDER_SHIPTIME_TYPE = "shiptime";

	public final static String WECHAT_PAY_CONFIG = "wechatConfig";

	// 网站运行状态
	public final static String SITE_RUN_STATE = "siteRunState";

	public final static String PRODUCT_GOOGREVIEW_DAY = "goodReviewDay";

	// 订单服务
	public final static String ORDER_SERVICE = "orderService";

	// 商品服务
	public final static String PRODUCT_SERVICE = "productService";

	public final static String COMCONTENT_SERVICE = "comContentService";

	// 新闻资讯
	public final static String NEWS_SERVICE = "newsService";

	public final static String CUSTOM_FORM_SERVICE = "customFormService";

	// 评价服务
	public final static String REVIEW_SERVICE = "reviewService";

	// 会员服务
	public final static String CUSTOMER_SERVICE = "customerService";

	// 会员服务
	public final static String PAY_SERVICE = "payService";

	// 配送服务
	public final static String DISPATCH_SERVICE = "dispatchService";

	// 访问统计服务
	public final static String STATISTIC_SERVICE = "statisticService";

	// 开启手机浏览器访问
	public final static String OPEN_MOBILE_BROWSER = "openMobileBrowser";

	// 在线留言服务
	public final static String MESSAGE_SERVICE = "messageService";

	// 网站主题
	public final static String SITE_THEME = "siteTheme";

	// 网站运营状态(系统级别,大于网站后台级别)
	public final static String SYSTEM_SITE_RUN_STATE = "sysSiteRunState";

	// 微信浏览器和手机端访问
	public final static String SITE_RUN_ENVIRONMENT = "wechatAndMobile";

	// 支付宝支付编码
	public final static String ALIPAY_CHARSET_UTF8 = "utf-8";

	public final static String ALIPAY_CHARSET_GBK = "gbk";

	// 日志描述
	public final static String ORDER_TRACK_ADDORDER = "添加了订单";
	public final static String ORDER_TRACK_PAYORDER = "支付了订单";
	public final static String ORDER_TRACK_DELIVERY = "配送了订单";
	public final static String ORDER_TRACK_CANCELORDER = "取消了订单";
	public final static String ORDER_TRACK_COMPLETEORDER = "完成了订单";

	// 发货物流描述
	public final static String ORDER_LOGISTIC_DESC = "您的订单已发货,请耐心等待";
	// 商品已删除时,订单中显示的默认图片路径
	public final static String DEFAULT_SALEOFF_PRODUCT_PATH = "/images/product-saleoff.png";

	/**
	 * home目录在系统属性中的key。
	 */
	public static final String HOME_KEY = "deploy.home.dir";

	/**
	 * cluster目录在系统属性中的key。
	 */
	public static final String CLUSTER_KEY = "deploy.cluster.dir";
	/**
	 * temp目录在系统属性中的key。
	 */
	public static final String TEMP_KEY = "deploy.temp.dir";

	/**
	 * 图片仓库目录在系统属性中的key。
	 */
	public static final String REPOSITORY_KEY = "deploy.repository.dir";

	/*** 部署服务器目录名称定义 **/
	/**
	 * 图片仓库 名称
	 */
	public static final String REPOSITORY = "imageRepository";

	/**
	 * 集群目录名称
	 */
	public static final String CLUSTER = "cluster";

	/**
	 * 临时目录 名称
	 */
	public static final String TEMP = "temp";

	public static final String PRODUCT = "product";

	public static final String CUSTOMER = "customer";

	public static final String INFO = "info";

	public static final String SITE = "site";

	public static final String DESCRIPTION = "description";

	public static final String GALLERY = "gallery";

}
