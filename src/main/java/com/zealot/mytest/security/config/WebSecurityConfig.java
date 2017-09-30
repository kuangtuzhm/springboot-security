package com.zealot.mytest.security.config;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import com.zealot.mytest.security.handler.ClearSessionRegistryLogoutHandler;



@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;//自定义用户服务
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
//	@Autowired
//    SessionRegistry sessionRegistry;
	
//	@Autowired
//	private MyAuthenticationProvider provider;//自定义验证
	
	@Bean
    public SessionRegistry getSessionRegistry(){
        SessionRegistry sessionRegistry=new SessionRegistryImpl();
        return sessionRegistry;
    }
	
	@Bean
	public LogoutHandler getLogoutHandler()
	{
		LogoutHandler logoutHandler = new ClearSessionRegistryLogoutHandler();
		return logoutHandler;
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService);
		//将验证过程交给自定义验证工具
		//auth.authenticationProvider(provider);
	}
	
	//spring security 内部都写死了，这里要把 这个DAO 注入
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){        
        JdbcTokenRepositoryImpl j=new JdbcTokenRepositoryImpl();
        j.setJdbcTemplate(jdbcTemplate);
        return j;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		// 例如以下代码指定了/和/index不需要任何认证就可以访问，其他的路径都必须通过身份验证。
        //.antMatchers("/", "/login").permitAll()
        .antMatchers(
                    "/css/**",
                    "/js/**")
                .permitAll()
        .anyRequest().authenticated()
        .and().formLogin().loginPage("/login")
        //设置默认登录成功跳转页面
        .defaultSuccessUrl("/mainFrame").failureUrl("/login").permitAll()
        .and()
        //如果没禁用掉会禁用iframe
		.headers().frameOptions().disable()
        /*
        .and()
        //开启cookie保存用户数据
        .rememberMe()
        //设置cookie有效期
        .tokenValiditySeconds(60 * 60 * 24 * 7)
        //设置cookie的私钥
        .key("")
        */
        .and()
        .logout()
        //默认注销行为为logout，可以通过下面的方式来修改
        .logoutUrl("/logout")
        //设置注销成功后跳转页面，默认是跳转到登录页面
        .logoutSuccessUrl("/login")
        .addLogoutHandler(getLogoutHandler())
        .permitAll()
        .invalidateHttpSession(true)
        .and()
            //登录后记住用户，下次自动登录
            //数据库中必须存在名为persistent_logins的表
            //建表语句见code15
            
           // 这里是核心
            .rememberMe()
            .tokenValiditySeconds(604800) //一周
            //指定记住登录信息所使用的数据源
            .tokenRepository(tokenRepository())//code4
        //用来管理登录的session内容,可以用来控制一个账号只能登录1次或者在线踢账户下线或者统计所有在线账户等等. 
        //用法为sessionRegistry.getAllPrincipals();
        .and().sessionManagement().maximumSessions(1)
        .expiredUrl("/login").sessionRegistry(getSessionRegistry());
		//关闭csrf 防止循环定向
        http.csrf().disable();
	}
}
