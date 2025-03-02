package utils;

import lombok.Getter;

@Getter
public enum StatusCodeAndValue {

    /**
     * 1.Informational  (信息性状态码)
     */
    CONTINUE(100, "(服务器已收到请求头)客户端可以继续发送请求体。"),  // (服务器已收到请求头)客户端可以继续发送请求体。
    SWITCHING_PROTOCOLS(101, "(服务器同意客户端的协议切换请求。"),   // (服务器同意客户端的协议切换请求。
    PROCESSING(102, "(请求正在处理中)客户端需等待最终响应"),   // (请求正在处理中)客户端需等待最终响应。

    /**
     * 2.Authenticator.Success（成功状态码）
     */
    OK(200, "(请求成功)服务器返回了请求的内容。"),    // (请求成功)服务器返回了请求的内容。
    CREATED(201, "(请求成功且服务器创建了新的资源。"),    // (请求成功且服务器创建了新的资源。
    ACCEPTED(202, "(请求已被接受)但处理尚未完成。"),    // (请求已被接受)但处理尚未完成。
    NO_CONTENT(204, "(请求成功)但没有返回内容。"),    // (请求成功)但没有返回内容。
    RESET_CONTENT(205, "(请求成功)客户端需要重置（重新加载）当前文档视图。"),    // (请求成功)客户端需要重置（重新加载）当前文档视图。
    PARTIAL_CONTENT(206, "(服务器完成了部分GET请求)并返回了请求范围内的内容。"),    // (服务器完成了部分GET请求)并返回了请求范围内的内容。

    /**
     * 3.Redirection（重定向状态码）
     */
    MULTIPLE_CHOICES(300, " (请求的资源对应多个位置)客户端需选择一个。"),    // (请求的资源对应多个位置)客户端需选择一个。
    MOVED_PERMANENTLY(301, "(请求的资源已永久移动到新的URL。"),    // (请求的资源已永久移动到新的URL。
    FOUND(302, "(请求的资源临时移动到新的URL。"),    // (请求的资源临时移动到新的URL。
    SEE_OTHER(303, "(请求已处理)客户端需通过GET请求获取响应。"),    // (请求已处理)客户端需通过GET请求获取响应。
    NOT_MODIFIED(304, "(请求的资源未修改)客户端可使用本地缓存。"),    // (请求的资源未修改)客户端可使用本地缓存。
    TEMPORARY_REDIRECT(307, "(请求的资源临时移动到新的URL)但请求方法不变。"),    // (请求的资源临时移动到新的URL)但请求方法不变。
    PERMANENT_REDIRECT(308, "(请求的资源永久移动到新的URL)但请求方法不变。"),    // (请求的资源永久移动到新的URL)但请求方法不变。

    /**
     * 4.Client Error（客户端错误状态码）
     */
    BAD_REQUEST(400, "(请求语法错误)服务器无法理解。"),    // (请求语法错误)服务器无法理解。
    UNAUTHORIZED(401, "(请求未授权)需要身份验证。"),    // (请求未授权)需要身份验证。
    PAYMENT_REQUIRED(402, "保留用于未来使用。"),  //保留用于未来使用。
    FORBIDDEN(403, "(请求被理解)但服务器拒绝执行。"),    // (请求被理解)但服务器拒绝执行。
    NOT_FOUND(404, "请求的资源未找到。"),    // 请求的资源未找到。
    METHOD_NOT_ALLOWED(405, "请求的HTTP方法不被允许。"),    // 请求的HTTP方法不被允许。
    NOT_ACCEPTABLE(406, "请求的资源无法满足客户端的Accept头指定的格式。"),    // 请求的资源无法满足客户端的Accept头指定的格式。
    PROXY_AUTHENTICATION_REQUIRED(407, "需要代理身份验证。"),    // 需要代理身份验证。
    REQUEST_TIMEOUT(408, "客户端未在服务器指定的时间内发送请求。"),    // 客户端未在服务器指定的时间内发送请求。
    CONFLICT(409, "请求与服务器资源的当前状态冲突。"),    // 请求与服务器资源的当前状态冲突。
    GONE(410, "请求的资源已永久删除。"),    // 请求的资源已永久删除。
    LENGTH_REQUIRED(411, "请求缺少“Content-Length”头。"),    // 请求缺少“Content-Length”头。
    PRECONDITION_FAILED(412, "请求头中的条件未满足。"),    // 请求头中的条件未满足。
    REQUEST_ENTITY_TOO_LARGE(413, "请求体过大。"),    // 请求体过大。
    REQUEST_URI_TOO_LONG(414, "请求的URI过长。"),    // 请求的URI过长。
    UNSUPPORTED_MEDIA_TYPE(415, "请求的媒体类型不被支持。"),    // 请求的媒体类型不被支持。
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "请求的范围无效。"),    // 请求的范围无效。
    EXPECTATION_FAILED(417, "服务器无法满足“Expect”请求头的要求。"),    // 服务器无法满足“Expect”请求头的要求。

    /**
     * 5.Server Error （服务器错误状态码）
     */
    INTERNAL_SERVER_ERROR(500, "(服务器内部错误)无法完成请求。"),    // (服务器内部错误)无法完成请求。
    NOT_IMPLEMENTED(501, "服务器不支持请求的功能。"),    // 服务器不支持请求的功能。
    BAD_GATEWAY(502, "(服务器作为网关时)从上游服务器收到无效响应。"),    // (服务器作为网关时)从上游服务器收到无效响应。
    SERVICE_UNAVAILABLE(503, "(服务器暂时无法处理请求)通常是由于服务器过载或维护。"),    // (服务器暂时无法处理请求)通常是由于服务器过载或维护。
    GATEWAY_TIMEOUT(504, "(服务器作为网关时)未在规定时间内从上游服务器收到响应。"),    // (服务器作为网关时)未在规定时间内从上游服务器收到响应。
    HTTP_VERSION_NOT_SUPPORTED(505,"服务器不支持请求的HTTP版本。");    // 服务器不支持请求的HTTP版本。

    private final int code;
    private final String message;
    
    StatusCodeAndValue(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
