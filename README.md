# springboot-security
springboot
spring security
mybatis
logback
redis
freemarker
多环境配置公共文件application.properties,各个环境的为application-dev.properties,application-test.properties
日志logback(如果logback中需要使用配置中的属性，需要定义<springProperty scope="context" name="logLevel" source="logback.log.level"/>这样)
fastjson进行返回对象转json,在startUp.java中配置
springsecurity验证返回登录界面，如果是ajax请求则在/login中返回ajax数据并做通用增强jquery.ajax.enhancer.js
自动登录remember-me(页面加选中checkbox,配置中配置自动登录功能)