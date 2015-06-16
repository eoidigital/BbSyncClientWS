/* 
 * Copyright 2014 Kurt Faulknerloser
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bbsync.webservice.client.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBbUser {
	private BbUser bb_user = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bb_user = new BbUser();
	}

	@After
	public void tearDown() throws Exception {
		bb_user = null;
	}
    		
	@Test
	public void testBirthDate(){
		assertTrue(bb_user.getBirthDate()==0L);
		Long now = Calendar.getInstance().getTimeInMillis();
		bb_user.setBirthDate(now);
		//The process of persisting a date in Blackboard removes the milliseconds,
		//so we have to crate a long with no millis to test against.
		String now_string = String.valueOf(now).substring(0, 10);
		long now_no_millis = Long.parseLong(now_string + "000");
		assertEquals(now_no_millis, bb_user.getBirthDate());
	}
	
	@Test
	public void testDataSourceId(){
		assertNull(bb_user.getDataSourceId());
		bb_user.setDataSourceId("test_id_string");
		assertEquals("test_id_string", bb_user.getDataSourceId());
	}

	@Test
	public void testEducationLevel(){
		assertNull(bb_user.getEducationLevel());
		bb_user.setEducationLevel(BbUser.EDUCATION_LEVEL_DEFAULT);
		assertEquals(BbUser.EDUCATION_LEVEL_DEFAULT, bb_user.getEducationLevel());
		bb_user.setEducationLevel(BbUser.EDUCATION_LEVEL_FRESHMAN);
		assertEquals(BbUser.EDUCATION_LEVEL_FRESHMAN, bb_user.getEducationLevel());
		bb_user.setEducationLevel(BbUser.EDUCATION_LEVEL_GRADUATE_SCHOOL);
		assertEquals(BbUser.EDUCATION_LEVEL_GRADUATE_SCHOOL, bb_user.getEducationLevel());
		bb_user.setEducationLevel(BbUser.EDUCATION_LEVEL_HIGH_SCHOOL);
		assertEquals(BbUser.EDUCATION_LEVEL_HIGH_SCHOOL, bb_user.getEducationLevel());
		bb_user.setEducationLevel(BbUser.EDUCATION_LEVEL_JUNIOR);
		assertEquals(BbUser.EDUCATION_LEVEL_JUNIOR, bb_user.getEducationLevel());
		bb_user.setEducationLevel(BbUser.EDUCATION_LEVEL_K_8);
		assertEquals(BbUser.EDUCATION_LEVEL_K_8, bb_user.getEducationLevel());
		bb_user.setEducationLevel(BbUser.EDUCATION_LEVEL_POST_GRADUATE_SCHOOL);
		assertEquals(BbUser.EDUCATION_LEVEL_POST_GRADUATE_SCHOOL, bb_user.getEducationLevel());
		bb_user.setEducationLevel(BbUser.EDUCATION_LEVEL_SENIOR);
		assertEquals(BbUser.EDUCATION_LEVEL_SENIOR, bb_user.getEducationLevel());
		bb_user.setEducationLevel(BbUser.EDUCATION_LEVEL_SOPHOMORE);
		assertEquals(BbUser.EDUCATION_LEVEL_SOPHOMORE, bb_user.getEducationLevel());
		bb_user.setEducationLevel(BbUser.EDUCATION_LEVEL_UNKNOWN);
		assertEquals(BbUser.EDUCATION_LEVEL_UNKNOWN, bb_user.getEducationLevel());
	}
	
	@Test
	public void testUUID(){
		//User UUID - is a unique generated ID (generated by Blackboard)for the user which never changes.
		//This value is read-only, so there is no setter method.  Therefore, this method will only be tested
		//during integration scenarios
		assertTrue(true);
	}
	
	@Test
	public void testGenderType(){
		assertNull(bb_user.getGenderType());
		bb_user.setGenderType(BbUser.GENDER_TYPE_DEFAULT);
		assertEquals(BbUser.GENDER_TYPE_DEFAULT, bb_user.getGenderType());
		bb_user.setGenderType(BbUser.GENDER_TYPE_FEMALE);
		assertEquals(BbUser.GENDER_TYPE_FEMALE, bb_user.getGenderType());
		bb_user.setGenderType(BbUser.GENDER_TYPE_MALE);
		assertEquals(BbUser.GENDER_TYPE_MALE, bb_user.getGenderType());
		bb_user.setGenderType(BbUser.GENDER_TYPE_UNKNOWN);
		assertEquals(BbUser.GENDER_TYPE_UNKNOWN, bb_user.getGenderType());
	}
	
	@Test
	public void testId(){
		assertNull(bb_user.getId());
		bb_user.setId("test_id_string");
		assertEquals("test_id_string", bb_user.getId());
	}
	
	@Test
	public void testInsRoles(){
		assertNull(bb_user.getInsRoles());
		String[] roles = new String[]{BbPortalRole.INSTITUTION_ROLE_ALUMNI};
		bb_user.setInsRoles(roles);
		assertEquals(1, bb_user.getInsRoles().length);
		assertEquals(BbPortalRole.INSTITUTION_ROLE_ALUMNI, bb_user.getInsRoles()[0]);

		roles = new String[]{BbPortalRole.INSTITUTION_ROLE_FACULTY};
		bb_user.setInsRoles(roles);
		assertEquals(1, bb_user.getInsRoles().length);
		assertEquals(BbPortalRole.INSTITUTION_ROLE_FACULTY, bb_user.getInsRoles()[0]);
		
		roles = new String[]{BbPortalRole.INSTITUTION_ROLE_GUEST};
		bb_user.setInsRoles(roles);
		assertEquals(1, bb_user.getInsRoles().length);
		assertEquals(BbPortalRole.INSTITUTION_ROLE_GUEST, bb_user.getInsRoles()[0]);
		
		roles = new String[]{BbPortalRole.INSTITUTION_ROLE_OBSERVER};
		bb_user.setInsRoles(roles);
		assertEquals(1, bb_user.getInsRoles().length);
		assertEquals(BbPortalRole.INSTITUTION_ROLE_OBSERVER, bb_user.getInsRoles()[0]);
		
		roles = new String[]{BbPortalRole.INSTITUTION_ROLE_OTHER};
		bb_user.setInsRoles(roles);
		assertEquals(1, bb_user.getInsRoles().length);
		assertEquals(BbPortalRole.INSTITUTION_ROLE_OTHER, bb_user.getInsRoles()[0]);
		
		roles = new String[]{BbPortalRole.INSTITUTION_ROLE_PROSPECTIVE_STUDENT};
		bb_user.setInsRoles(roles);
		assertEquals(1, bb_user.getInsRoles().length);
		assertEquals(BbPortalRole.INSTITUTION_ROLE_PROSPECTIVE_STUDENT, bb_user.getInsRoles()[0]);
		
		roles = new String[]{BbPortalRole.INSTITUTION_ROLE_STAFF};
		bb_user.setInsRoles(roles);
		assertEquals(1, bb_user.getInsRoles().length);
		assertEquals(BbPortalRole.INSTITUTION_ROLE_STAFF, bb_user.getInsRoles()[0]);
		
		roles = new String[]{BbPortalRole.INSTITUTION_ROLE_STUDENT};
		bb_user.setInsRoles(roles);
		assertEquals(1, bb_user.getInsRoles().length);
		assertEquals(BbPortalRole.INSTITUTION_ROLE_STUDENT, bb_user.getInsRoles()[0]);
		
		roles = new String[]{BbPortalRole.INSTITUTION_ROLE_UNKNOWN};
		bb_user.setInsRoles(roles);
		assertEquals(1, bb_user.getInsRoles().length);
		assertEquals(BbPortalRole.INSTITUTION_ROLE_UNKNOWN, bb_user.getInsRoles()[0]);
	}
			
	@Test
	public void testName(){
		assertNull(bb_user.getName());
		bb_user.setName("test_name_string");
		assertEquals("test_name_string", bb_user.getName());
	}
	
	@Test
	public void testPassword(){
		assertNull(bb_user.getPassword());
		bb_user.setPassword("test_password_string");
		assertEquals("test_password_string", bb_user.getPassword());
	}
	
	@Test
	public void testStudentId(){
		assertNull(bb_user.getStudentId());
		bb_user.setStudentId("test_id_string");
		assertEquals("test_id_string", bb_user.getStudentId());
	}
	
	@Test
	public void testTitle(){
		assertNull(bb_user.getTitle());
		bb_user.setTitle("test_title_string");
		assertEquals("test_title_string", bb_user.getTitle());
	}
	
	@Test
	public void testBatchUid(){
		assertNull(bb_user.getBatchUid());
		bb_user.setBatchUid("test_batchuid_string");
		assertEquals("test_batchuid_string", bb_user.getBatchUid());
	}
	
	@Test
	public void testAvailable(){
		assertFalse(bb_user.isAvailable());
		bb_user.setAvailable(true);
		assertTrue(bb_user.isAvailable());
	}
	
	@Test
	public void testSystemRoles(){
		assertNull(bb_user.getSystemRoles());
		String[] roles = new String[]{BbUser.SYSTEM_ROLE_ACCOUNT_ADMIN};
		bb_user.setSystemRoles(roles);
		assertEquals(1, bb_user.getSystemRoles().length);
		assertEquals(BbUser.SYSTEM_ROLE_ACCOUNT_ADMIN, bb_user.getSystemRoles()[0]);
		
		roles = new String[]{BbUser.SYSTEM_ROLE_COURSE_CREATOR};
		bb_user.setSystemRoles(roles);
		assertEquals(1, bb_user.getSystemRoles().length);
		assertEquals(BbUser.SYSTEM_ROLE_COURSE_CREATOR, bb_user.getSystemRoles()[0]);
		
		roles = new String[]{BbUser.SYSTEM_ROLE_COURSE_SUPPORT};
		bb_user.setSystemRoles(roles);
		assertEquals(1, bb_user.getSystemRoles().length);
		assertEquals(BbUser.SYSTEM_ROLE_COURSE_SUPPORT, bb_user.getSystemRoles()[0]);

		roles = new String[]{BbUser.SYSTEM_ROLE_GUEST};
		bb_user.setSystemRoles(roles);
		assertEquals(1, bb_user.getSystemRoles().length);
		assertEquals(BbUser.SYSTEM_ROLE_GUEST, bb_user.getSystemRoles()[0]);

		roles = new String[]{BbUser.SYSTEM_ROLE_INTEGRATION};
		bb_user.setSystemRoles(roles);
		assertEquals(1, bb_user.getSystemRoles().length);
		assertEquals(BbUser.SYSTEM_ROLE_INTEGRATION, bb_user.getSystemRoles()[0]);

		roles = new String[]{BbUser.SYSTEM_ROLE_LMS_INTEGRATION_ADMIN};
		bb_user.setSystemRoles(roles);
		assertEquals(1, bb_user.getSystemRoles().length);
		assertEquals(BbUser.SYSTEM_ROLE_LMS_INTEGRATION_ADMIN, bb_user.getSystemRoles()[0]);

		roles = new String[]{BbUser.SYSTEM_ROLE_OBSERVER};
		bb_user.setSystemRoles(roles);
		assertEquals(1, bb_user.getSystemRoles().length);
		assertEquals(BbUser.SYSTEM_ROLE_OBSERVER, bb_user.getSystemRoles()[0]);

		roles = new String[]{BbUser.SYSTEM_ROLE_PORTAL};
		bb_user.setSystemRoles(roles);
		assertEquals(1, bb_user.getSystemRoles().length);
		assertEquals(BbUser.SYSTEM_ROLE_PORTAL, bb_user.getSystemRoles()[0]);

		roles = new String[]{BbUser.SYSTEM_ROLE_SYSTEM_ADMIN};
		bb_user.setSystemRoles(roles);
		assertEquals(1, bb_user.getSystemRoles().length);
		assertEquals(BbUser.SYSTEM_ROLE_SYSTEM_ADMIN, bb_user.getSystemRoles()[0]);

		roles = new String[]{BbUser.SYSTEM_ROLE_SYSTEM_SUPPORT};
		bb_user.setSystemRoles(roles);
		assertEquals(1, bb_user.getSystemRoles().length);
		assertEquals(BbUser.SYSTEM_ROLE_SYSTEM_SUPPORT, bb_user.getSystemRoles()[0]);

		roles = new String[]{BbUser.SYSTEM_ROLE_NONE};
		bb_user.setSystemRoles(roles);
		assertEquals(1, bb_user.getSystemRoles().length);
		assertEquals(BbUser.SYSTEM_ROLE_NONE, bb_user.getSystemRoles()[0]);
	}
	
	/**
	 * The following tests exercise user data that is stored as extended info
	 * 
	 */
	
	@Test
	public void testBusinessFax(){
		assertNull(bb_user.getBusinessFax());
		bb_user.setBusinessFax("1234567890_test_string");
		assertEquals("1234567890_test_string", bb_user.getBusinessFax());
	}
	
	@Test
	public void testBusinessPhone1(){
		assertNull(bb_user.getBusinessPhone1());
		bb_user.setBusinessPhone1("1234567890_test_string");
		assertEquals("1234567890_test_string", bb_user.getBusinessPhone1());
	}
	
	@Test
	public void testBusinessPhone2(){
		assertNull(bb_user.getBusinessPhone2());
		bb_user.setBusinessPhone2("1234567890_test_string");
		assertEquals("1234567890_test_string", bb_user.getBusinessPhone2());
	}
	
	@Test
	public void testCity(){
		assertNull(bb_user.getCity());
		bb_user.setCity("test_City_string");
		assertEquals("test_City_string", bb_user.getCity());
	}
	
	@Test
	public void testCompany(){
		assertNull(bb_user.getCompany());
		bb_user.setCompany("test_Company_string");
		assertEquals("test_Company_string", bb_user.getCompany());
	}
	
	@Test
	public void testCountry(){
		assertNull(bb_user.getCountry());
		bb_user.setCountry("test_Country_string");
		assertEquals("test_Country_string", bb_user.getCountry());
	}
	
	@Test
	public void testDepartment(){
		assertNull(bb_user.getDepartment());
		bb_user.setDepartment("test_Department_string");
		assertEquals("test_Department_string", bb_user.getDepartment());
	}
	
	@Test
	public void testEmailAddress(){
		assertNull(bb_user.getEmailAddress());
		bb_user.setEmailAddress("test_EmailAddress_string");
		assertEquals("test_EmailAddress_string", bb_user.getEmailAddress());
	}
	
	@Test
	public void testFamilyName(){
		assertNull(bb_user.getFamilyName());
		bb_user.setFamilyName("test_FamilyName_string");
		assertEquals("test_FamilyName_string", bb_user.getFamilyName());
	}
	
	@Test
	public void testGivenName(){
		assertNull(bb_user.getGivenName());
		bb_user.setGivenName("test_GivenName_string");
		assertEquals("test_GivenName_string", bb_user.getGivenName());
	}
	
	@Test
	public void testHomeFax(){
		assertNull(bb_user.getHomeFax());
		bb_user.setHomeFax("test_HomeFax_string");
		assertEquals("test_HomeFax_string", bb_user.getHomeFax());
	}
	
	@Test
	public void testHomePhone1(){
		assertNull(bb_user.getHomePhone1());
		bb_user.setHomePhone1("test_HomePhone1_string");
		assertEquals("test_HomePhone1_string", bb_user.getHomePhone1());
	}
	
	@Test
	public void testHomePhone2(){
		assertNull(bb_user.getHomePhone2());
		bb_user.setHomePhone2("test_HomePhone2_string");
		assertEquals("test_HomePhone2_string", bb_user.getHomePhone2());
	}
	
	@Test
	public void testJobTitle(){
		assertNull(bb_user.getJobTitle());
		bb_user.setJobTitle("test_JobTitle_string");
		assertEquals("test_JobTitle_string", bb_user.getJobTitle());
	}
	
	@Test
	public void testMiddleName(){
		assertNull(bb_user.getMiddleName());
		bb_user.setMiddleName("test_MiddleName_string");
		assertEquals("test_MiddleName_string", bb_user.getMiddleName());
	}
	
	@Test
	public void testMobilePhone(){
		assertNull(bb_user.getMobilePhone());
		bb_user.setMobilePhone("test_MobilePhone_string");
		assertEquals("test_MobilePhone_string", bb_user.getMobilePhone());
	}
	
	@Test
	public void testState(){
		assertNull(bb_user.getState());
		bb_user.setState("test_State_string");
		assertEquals("test_State_string", bb_user.getState());
	}
	
	@Test
	public void testStreet1(){
		assertNull(bb_user.getStreet1());
		bb_user.setStreet1("test_Street1_string");
		assertEquals("test_Street1_string", bb_user.getStreet1());
	}
	
	@Test
	public void testStreet2(){
		assertNull(bb_user.getStreet2());
		bb_user.setStreet2("test_Street2_string");
		assertEquals("test_Street2_string", bb_user.getStreet2());
	}
	
	@Test
	public void testWebPage(){
		assertNull(bb_user.getWebPage());
		bb_user.setWebPage("test_WebPage_string");
		assertEquals("test_WebPage_string", bb_user.getWebPage());
	}
	
	@Test
	public void testZipCode(){
		assertNull(bb_user.getZipCode());
		bb_user.setZipCode("test_ZipCode_string");
		assertEquals("test_ZipCode_string", bb_user.getZipCode());
	}
	
	/**
	 * The following tests exercise user data that is stored as expansion data in the extended info
	 * 
	 */
	
	@Test
	public void testSuffix(){
		assertNull(bb_user.getSuffix());
		bb_user.setSuffix("test_Suffix_string");
		assertEquals("test_Suffix_string", bb_user.getSuffix());
	}
	
	@Test
	public void testOtherName(){
		assertNull(bb_user.getOtherName());
		bb_user.setOtherName("test_OtherName_string");
		assertEquals("test_OtherName_string", bb_user.getOtherName());
	}
}
