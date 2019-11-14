package org.openpaas.paasta.marketplace.web.admin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openpaas.paasta.marketplace.web.admin.service.AdminCategoryServiceTest;
import org.openpaas.paasta.marketplace.web.admin.service.AdminSellerProfileServiceTest;
import org.openpaas.paasta.marketplace.web.admin.service.AdminSoftwareServiceTest;
import org.openpaas.paasta.marketplace.web.admin.service.AdminStatsServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
        // @formatter:off
        AdminCategoryServiceTest.class,
        AdminSellerProfileServiceTest.class,
        AdminSoftwareServiceTest.class,
        AdminStatsServiceTest.class,
        // @formatter:on
})
public class ServiceTests {
}
