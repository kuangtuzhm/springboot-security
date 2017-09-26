package com.zealot.mytest.security.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;



@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;//自定义用户服务
	
//	@Autowired
//    SessionRegistry sessionRegistry;
	
//	@Autowired
//	private MyAuthenticationProvider provider;//自定义验证
	
	@Bean
    public SessionRegistry getSessionRegistry(){
        SessionRegistry sessionRegistry=new SessionRegistryImpl();
        return sessionRegistry;
    }
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService);
		//将验证过程交给自定义验证工具
		//auth.authenticationProvider(provider);
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
        .defaultSuccessUrl("/mainFrame").failureUrl("/login?error").permitAll()
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
        .logoutSuccessUrl("/index")
        .permitAll()
        //用来管理登录的session内容,可以用来控制一个账号只能登录1次或者在线踢账户下线或者统计所有在线账户等等. 
        //用法为sessionRegistry.getAllPrincipals();
        .and().sessionManagement().maximumSessions(1).sessionRegistry(getSessionRegistry());
		//关闭csrf 防止循环定向
        http.csrf().disable();
	}
}
