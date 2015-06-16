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

package org.bbsync.webservice.client.coursemembership;

import java.io.Serializable;
import java.util.ArrayList;

import org.bbsync.webservice.client.abstracts.AbstractCourseMembership;
import org.bbsync.webservice.client.generated.CourseMembershipWSStub.CourseMembershipVO;
import org.bbsync.webservice.client.generated.CourseMembershipWSStub.MembershipFilter;

public class BbOrganizationMembership extends AbstractCourseMembership {
    private static final long serialVersionUID = 5555000000004444L;
    
	///////////////////////////////////////////////////////////////////////////
	//  Constructors  /////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
    public BbOrganizationMembership() {
        _cmvo = new CourseMembershipVO();
    }

    private BbOrganizationMembership(CourseMembershipVO cmvo) {
        _cmvo = cmvo;
    }
    
	///////////////////////////////////////////////////////////////////////////
	//  Required ClientWebService Methods  ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
    /**
     * Creates this Organization Membership in Blackboard. The following fields 
     * must be set prior to calling persist():
     *  - Organization ID: set the ID of the Organization that will contain the 
     *                     User. This parameter can be null if invoked by a 
     *                     System Administrator. Otherwise, Organization ID is 
     *                     required. The Organization ID should be generated by 
     *                     Blackboard, in the form "_nnn_1" where nnn is an 
     *                     integer.
     *  - User ID: The ID of the user that will be enrolled in the 
     *  		   Organization. This ID should be generated by Blackboard, in 
     *             the form "_nnn_1" where nnn is an integer.
     *  - Role ID: The ID of the User's Role in the Organization. Role IDs can 
     *             be obtained via BbMembershipRole. You can also see 
     *             "Course/Organization Roles" in System Admin Panel for more
     *             information about Course/Organization Roles.
     *  - Available: Availability must be set to true in order to create a 
     *               Organization Membership. 
     * @Return Returns a String representing the OrganizationMembership ID, or null 
     *         if the persist() was not successful.
     */    
	public Serializable persist() {
		String[] result = super.saveCourseMembership(getOrganizationId(), new CourseMembershipVO[]{getVO()});
		if(result==null || result.length==0) return null;
		return result[0];
	}

	/** 
	 * This method will retrieve a single membership from Blackboard. In order 
	 * to use this method you must set the ID value using the Organization  
	 * Membership ID value provided by Blackboard.  Alternatively, this method  
	 * will retrieve a single Organization Membership provided that the User ID
	 * and Organization ID fields have been set.
     *  - Organization ID: this Organization Membership's Organization ID. This
     *                     parameter can be null if the Orgnization Membership 
     *                     ID field has been set and this method is invoked by 
     *                     a System Administrator. Otherwise, Organization ID 
     *                     is required. The Organization ID should be generated
     *                     by Blackboard, in the form "_nnn_1" where nnn is an 
     *                     integer.
     *  - Org Membership ID: the ID representing the Organization Membership 
     *                       object to be retrieved from Blackboard.  The ID 
     *                       should be generated by Blackboard, in the form 
     *                       "_nnn_1" where nnn is an integer.
     *  - User ID:  If not attempting to retrieve() by Organization Membership 
     *              ID, User ID is set along with Organization ID to retrieve a 
     *              Organization Membership ID. The ID should be generated by 
     *              Blackboard, in the form "_nnn_1" where nnn is an integer.
     * @Return Returns a BbOrganizationMembership, or null if the retrieve() 
     *         was not successful.
     */
	public Object retrieve() {
		BbOrganizationMembership[] result = null;
		if(getId() != null){
			result = getOrgMembershipsByIds(getOrganizationId(), new String[]{getId()});
			if(result==null || result.length==0) return null;
			return result[0];
		}
		if(getUserId()!=null && getOrganizationId()!=null){
			result = getOrgMembershipsByOrgIdAndUserIds(getOrganizationId(), new String[]{getUserId()});
			if(result==null || result.length==0) return null;
			return result[0];
		}
		return null;
	}
	
    /**
     * This method allows this Organization Membership record to be deleted 
     * (i.e. remove a user from an Organization).  The following fields must be
     * set prior to calling delete():
     * 
     * Organization ID: This Organization Membership's Organization ID This 
     *                  parameter can be null if invoked by a System 
     *                  Administrator. Otherwise, Organization ID is required.
     *                  The Organization ID should be generated by Blackboard,
     *                  in the form "_nnn_1" where nnn is an integer.
     * OrganizationMembership ID: This OrganizationMembership's ID. The 
     *                            Organization Membership ID should be 
     *                            generated by Blackboard, in the form 
     *                            "_nnn_1" where nnn is an integer.
     * @return Returns true if the Organization Membership was successfully 
     *         deleted. Otherwise returns false.
     */
	public boolean delete() {
		String[] result = super.deleteCourseMembership(getOrganizationId(), new String[]{getId()});
		if(result!=null && result.length==1)return true;
		return false;
	}
	
	///////////////////////////////////////////////////////////////////////////
	//  Implemented ProxyTool Methods  ////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////        
    /**
     * This method allows multiple BbOrganizationMembership records to be added
     * to an Organization (i.e. add Users to an Organization). If the Role of 
     * the user invoking this method is *System Administrator* then the org_id 
     * parameter can be null, allowing for Organization Memberships from 
     * different Organizations. Otherwise, the org_id is required and all 
     * Organization Memberships must be associated with the specified 
     * Organization.
     * 
     * @param org_id - the ID of the Organization that is associated with the 
     *                 OrganizationMemberships to save.  This parameter can be 
     *                 null if invoked by a System Administrator. Otherwise, 
     *                 org_id is required. The org_id should be generated by 
     *                 Blackboard, in the form "_nnn_1" where nnn is an 
     *                 integer.
     * @param memberships - an array of BbOrganizationMemberships to save
     * 
     * @return Returns an array of successfully saved OrganizationMembership 
     *         IDs.
     */
	public String[] saveOrganizationMembership(String org_id, BbOrganizationMembership[] memberships){
		if(memberships==null) return null;
		CourseMembershipVO[] membership_vos = BbOrgMembershipArray_to_CourseMembershipVOArray(memberships);
		return super.saveCourseMembership(org_id, membership_vos);
	}
		
	/**
     * This method allows Organization Membership objects to be loaded based on 
     * OrganizationMembership IDs. This method will return both Active and Inactive 
     * records.  If the Role of the user invoking this method is 
     * *System Administrator* then the org_id parameter can be null, 
     * allowing for Organization Memberships from different Organizations. Otherwise, the 
     * org_id is required and all requested Organization Memberships 
     * (org_membership_ids) must be associated with the specified Organization.
     *  
     * @param org_id - the ID of the Organization that is associated with the 
     *                    OrganizationMemberships to load.  This parameter can be 
     *                    null if invoked by a System Administrator.  
     *                    Otherwise, org_id is required. The org_id 
     *                    should be generated by Blackboard, in the form 
     *                    "_nnn_1" where nnn is an integer.
     * @param org_membership_ids - an array of Organization Membership IDs 
     * 								  representing the GroupMembership objects
     * 								  to be retrieved from Blackboard.  The ids 
     *                                should be generated by Blackboard, in the 
     *                                form "_nnn_1" where nnn is an integer.
     * @return Returns an array of BbOrganizationMembership objects.
     */	
	public BbOrganizationMembership[] getOrgMembershipsByIds(String org_id, String[] cm_ids){
		MembershipFilter filter = new MembershipFilter();
		filter.setFilterType(FILTER_TYPE_BY_ID);
		filter.setCourseMembershipIds(cm_ids);
		CourseMembershipVO[] result = super.getCourseMembership(org_id, filter);
		return CourseMembershipVOArray_to_BbOrgMembershipArray(result);
	}

	/**
     * This method allows Organization Membership objects to be loaded based on 
     * Organization IDs. This method will return both Active and Inactive records.  
     * If the Role of the user invoking this method is *System Administrator*
     * then the org_id parameter can be null, allowing for Organization 
     * Memberships from different Organizations. Otherwise, the org_id is required
     * and all requested Organization Memberships (org_ids) must be associated 
     * with the specified Organization.
     *  
     * @param org_id - the ID of the Organization that is associated with the 
     *                    OrganizationMemberships to load.  This parameter can be 
     *                    null if invoked by a System Administrator.  
     *                    Otherwise, org_id is required. The org_id 
     *                    should be generated by Blackboard, in the form 
     *                    "_nnn_1" where nnn is an integer.
     * @param org_ids - an array of Organization IDs from which the the 
     *                     OrganizationMembership objects will be retrieved.  This 
     *                     is a required parameter, even if there is only 
     *                     one Organization ID (the same value as org_id).  The 
     *                     IDs should be generated by Blackboard, in the form 
     *                     "_nnn_1" where nnn is an integer.
     * @return Returns an array of BbOrganizationMembership objects representing all
     *         of the Organization Memberships associated with the specified 
     *         Organization(s). One Organization can return many Organization Memberships.
     */
	public BbOrganizationMembership[] getOrgMembershipsByOrgIds(String org_id, String[] org_ids){
		MembershipFilter filter = new MembershipFilter();
		filter.setFilterType(FILTER_TYPE_BY_CRS_ID);
		filter.setCourseIds(org_ids);
		CourseMembershipVO[] result = super.getCourseMembership(org_id, filter);
		return CourseMembershipVOArray_to_BbOrgMembershipArray(result);
	}

	/**
     * This method allows Organization Membership objects to be loaded based on User
     * IDs. This method will return both Active and Inactive records.  If the 
     * Role of the user invoking this method is *System Administrator* then the
     * org_id parameter can be null, allowing for Organization Memberships from 
     * different Organizations. Otherwise, the org_id is required and all 
     * requested Organization Memberships (user_ids) must be associated with the 
     * specified Organization.
     *  
     * @param org_id - the ID of the Organization that is associated with the 
     *                    OrganizationMemberships to load.  This parameter can be 
     *                    null if invoked by a System Administrator.  
     *                    Otherwise, org_id is required. The org_id 
     *                    should be generated by Blackboard, in the form 
     *                    "_nnn_1" where nnn is an integer.
     * @param user_ids - an array of User IDs representing the Users enrolled
     *                   in a Organization.  The IDs should be generated by 
     *                   Blackboard, in the form "_nnn_1" where nnn is an 
     *                   integer.
     * @return Returns an array of BbOrganizationMembership objects representing all
     *         of the Organization Memberships associated with the specified User(s).
     *         One User can be a member of many Organizations.
     */	
	public BbOrganizationMembership[] getOrgMembershipsByUserIds(String org_id, String[] user_ids){
		MembershipFilter filter = new MembershipFilter();
		filter.setFilterType(FILTER_TYPE_BY_USER_ID);
		filter.setUserIds(user_ids);
		CourseMembershipVO[] result = super.getCourseMembership(org_id, filter);
		return CourseMembershipVOArray_to_BbOrgMembershipArray(result);
	}

	/**
     * This method allows Organization Membership objects to be loaded based on User
     * IDs, but limited to the Organization specified by the org_id  parameter. 
     * For this method, the org_id is required and all requested Organization 
     * Memberships (user_ids) must be associated with the specified Organization. 
     * This method will return both Active and Inactive records.
     *  
     * @param org_id - the ID of the Organization that is associated with the 
     *                    OrganizationMemberships to load. The org_id should be 
     *                    generated by Blackboard, in the form "_nnn_1" where 
     *                    nnn is an integer.
     * @param user_ids - an array of User IDs representing the Users enrolled
     *                   in a Organization.  The IDs should be generated by 
     *                   Blackboard, in the form "_nnn_1" where nnn is an 
     *                   integer.
     * @return Returns an array of BbOrganizationMembership objects representing all
     *         of the Organization Memberships associated with the specified User(s)
     *         and the specified Organization.
     */	
	public BbOrganizationMembership[] getOrgMembershipsByOrgIdAndUserIds(String org_id, String[] user_ids){
		MembershipFilter filter = new MembershipFilter();
		filter.setFilterType(FILTER_TYPE_BY_CRS_ID_AND_USER_ID);
		filter.setUserIds(user_ids);
		filter.setCourseIds(new String[]{org_id});
		CourseMembershipVO[] result = super.getCourseMembership(org_id, filter);
		return CourseMembershipVOArray_to_BbOrgMembershipArray(result);
	}

	/**
     * This method allows Organization Membership objects to be loaded based on 
     * Organization Roles and limited to the Organization specified by the org_id  
     * parameter. For this method, the org_id is required and all requested 
     * Organization Memberships (role_ids) must be associated with the specified 
     * Organization. This method will return both Active and Inactive records.
     *  
     * @param org_id - the ID of the Organization that is associated with the 
     *                    OrganizationMemberships to load. The org_id should be 
     *                    generated by Blackboard, in the form "_nnn_1" where 
     *                    nnn is an integer.
     * @param role_ids - an array of Role IDs representing the types of Users 
     *                   enrolled in a Organization. Role IDs can be obtained via 
     *                   BbOrganizationMembershipRole. You can also see 
     *                   "Organization/Organization Roles" in System Admin Panel for 
     *                   more information about Organization/Organization Roles.
     * @return Returns an array of BbOrganizationMembership objects representing all
     *         of the Organization Memberships associated with the specified Role(s)
     *         and the specified Organization.
     */	
	public BbOrganizationMembership[] getOrgMembershipsByOrgIdAndRoleId(String org_id, String[] role_ids){
		MembershipFilter filter = new MembershipFilter();
		filter.setFilterType(FILTER_TYPE_BY_CRS_ID_AND_ROLE_ID);
		filter.setRoleIds(role_ids);
		filter.setCourseIds(new String[]{org_id});
		CourseMembershipVO[] result = super.getCourseMembership(org_id, filter);
		return CourseMembershipVOArray_to_BbOrgMembershipArray(result);
	}
	
	/**
     * This method allows specific Organization Membership records to be deleted 
     * (i.e. remove Users from a Organization). If the Role of the user invoking this
     * method is *System Administrator* then the org_id parameter can be 
     * null, allowing for Organization Memberships from different Organizations. Otherwise, 
     * the org_id is required and all Organization Memberships must be associated 
     * with the specified Organization.
     * 
     * @param org_id - the ID of the Organization that is associated with the 
     *                    OrganizationMemberships to load.  This parameter can be 
     *                    null if invoked by a System Administrator.  
     *                    Otherwise, org_id is required. The org_id 
     *                    should be generated by Blackboard, in the form 
     *                    "_nnn_1" where nnn is an integer.
     * @param org_membership_ids - An array of OrganizationMembership IDs to be 
     *                               deleted.  The org_membership_ids should 
     *                               be generated by Blackboard, in the form 
     *                               "_nnn_1" where nnn is an integer.
     * @return Returns an array of org_membership_ids that were successfully 
     *         deleted.
     */
	public String[] deleteOrgMemberships(String org_id, String[] org_membership_ids){
		return super.deleteCourseMembership(org_id, org_membership_ids);
	}

	///////////////////////////////////////////////////////////////////////////
	//  Implemented Abstract Methods  /////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	public String getOrganizationId(){
		return super.getCourseId();
	}
	
	public void setOrganizationId(String org_id){
		super.setCourseId(org_id);
	}
	///////////////////////////////////////////////////////////////////////////
	//  Local Methods  ////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private CourseMembershipVO getVO(){
		return _cmvo;
	}
	
	private CourseMembershipVO[] BbOrgMembershipArray_to_CourseMembershipVOArray(BbOrganizationMembership[] memberships){
		if(memberships==null || memberships.length==0)return null;
		ArrayList<CourseMembershipVO> membership_vos = new ArrayList<CourseMembershipVO>();
		for(BbOrganizationMembership bbom:memberships){
			membership_vos.add(bbom.getVO());
		}
		return membership_vos.toArray(new CourseMembershipVO[]{});
	}
	
	private BbOrganizationMembership[] CourseMembershipVOArray_to_BbOrgMembershipArray(CourseMembershipVO[] memberships){
		if(memberships==null)return null;
		ArrayList<BbOrganizationMembership> bboms = new ArrayList<BbOrganizationMembership>();
		for(CourseMembershipVO cmvo:memberships){
			BbOrganizationMembership membership = new BbOrganizationMembership(cmvo);
			bboms.add(membership);
		}
		return bboms.toArray(new BbOrganizationMembership[]{});
	}	
}
