package cn.edu.tju.scs.service;


import cn.edu.tju.scs.base.UnitTestBase;
import cn.edu.tju.scs.domain.Admin;
import cn.edu.tju.scs.web.controller.AdminController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


/**
 * Spring 声明式事务管理第二种：基于AspectJ的xml配置方式
 * @author jack
 *
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TestAdminController extends UnitTestBase {
	
	public TestAdminController() {
		super("classpath:mvc-dispatcher-servlet.xml,applicationContext.xml");
	}
	
	@Test
	public void testAccountService() {
//		AccountService accountService = (AccountService)super.getBean("accountService");
//		accountService.transfer("a", "b", 100d);
	}

	@Test
	public void testCardDao() {
 		AdminController adminController = (AdminController)super.getBean("adminController");
//		adminController.login(new Admin("wutong","123"));
	}

	
}
