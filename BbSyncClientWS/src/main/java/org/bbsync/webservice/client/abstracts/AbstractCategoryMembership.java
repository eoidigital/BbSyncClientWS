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

package org.bbsync.webservice.client.abstracts;

import org.bbsync.webservice.client.generated.CourseWSStub.CategoryMembershipVO;
import org.bbsync.webservice.client.generated.CourseWSStub.VersionVO;
import org.bbsync.webservice.client.proxytool.CourseProxyTool;


public abstract class AbstractCategoryMembership extends CourseProxyTool {
    private static final long serialVersionUID = 3333000000004444L; 
    protected CategoryMembershipVO _cmvo = null;
    
    protected static final int GET_CATEGORY_MEMBERSHIP_BY_ID = 1;
    protected static final int GET_CATEGORY_MEMBERSHIP_BY_COURSE_ID = 2;
    
	///////////////////////////////////////////////////////////////////////////
	//  Implemented ProxyTool Methods  ////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
    public boolean initializeCourseWS() {
        return super.initializeCourseWS(true);
        
    }
    
    public Long getServerVersion() {
        return super.getServerVersion(new VersionVO()).getVersion();
    }
	///////////////////////////////////////////////////////////////////////////
	//  Local Methods  ////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
    /**
     * @return Returns the ID for this Category Membership.
     */
    public String getId(){
    	return _cmvo.getId();
    }

    /**
     * Sets the ID for this Category Membership.
     * 
     * @param id - the ID for this Category Membership. The ID should be 
     *             generated by Blackboard, in the form "_nnn_1" where nnn is 
     *             an integer.
     */
    public void setId(String id){
    	_cmvo.setId(id);
    }

    /**
     * @return Returns the Category ID associated with this Category Membership.
     */
    public String getCategoryId(){
    	return _cmvo.getCategoryId();
    }
    
    /**
     * Sets the Category ID associated with this Category Membership.
     * 
     * @param category_id - the Category ID associated with this Category 
     *                      Membership. The Category ID should be generated by 
     *                      Blackboard, in the form "_nnn_1" where nnn is an 
     *                      integer.
     */
    public void setCategoryId(String category_id){
    	_cmvo.setCategoryId(category_id);
    }

    /**
     * @return the Course ID associated with this Category Membership.
     */
    protected String getCourseId(){
    	return _cmvo.getCourseId();
    }

    /**
     * Sets the Course ID associated with this Category Membership. 
     * 
     * @param course_id - the Course ID associated with this Category 
     *                    Membership. The Course ID should be generated by 
     *                    Blackboard, in the form "_nnn_1" where nnn is an 
     *                    integer.
     */
    protected void setCourseId(String course_id){
    	_cmvo.setCourseId(course_id);
    }
    
    /**
     * @return Returns true if this Category Membership is available.  
     *         Otherwise, returns false.
     */
    public boolean isAvailable(){
    	return _cmvo.getAvailable();
    }

    /**
     * Sets the availability of this Category Membership.
     * 
     * @param available - set true to make this Membership available.
     */
    public void setAvailable(boolean available){
    	_cmvo.setAvailable(available);
    }

    /**
     * @return Returns the Data Source ID for this Category Membership.
     */
    public String getDataSourceId(){
    	return _cmvo.getDataSourceId();
    }

    /**
     * Sets the Data Source ID for this Category Membership.
     * 
     * @param data_source_id - the Data Source ID to set. The Data Source ID 
     *                         should be generated by Blackboard, in the form 
     *                         "_nnn_1" where nnn is an integer.
     */
    public void setDataSourceId(String data_source_id){
    	_cmvo.setDataSourceId(data_source_id);
    }

    /**
     * @return Returns true if this Category Membership is associated with an 
     *         organization.  Otherwise, returns false.
     */
    public boolean isOrganization(){
    	return _cmvo.getOrganization();
    }

    /**
     * Set this parameter to true if this Category Membership is associated
     * with an organization.  If a Organization ID is set, this will 
     * automatically be set to true.  Likewise, If a Course ID is set, this
     * will be set to false.
     * 
     * @param organization - set to true if this Category Membership is 
     *                       associated with an organization.  Otherwise, set
     *                       to false.
     */
    public void setOrganization(boolean organization){
    	_cmvo.setOrganization(organization);
    }

    /**
     * @return Returns the expansion data.
     */
    public String[] getExpansionData(){
    	return _cmvo.getExpansionData();
    }

    /**
     * Expansion data is currently ignored. In future versions it may be used 
     * to add additional attributes without breaking the wsdl contract.
     * 
     * @param expansion_data - the expansionData to set (For Future Use).
     */
    public void setExpansionData(String[] expansion_data){
    	_cmvo.setExpansionData(expansion_data);
    }
}
