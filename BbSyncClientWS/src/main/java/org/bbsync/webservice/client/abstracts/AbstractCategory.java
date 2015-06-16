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

import org.bbsync.webservice.client.generated.CourseWSStub.CategoryVO;
import org.bbsync.webservice.client.generated.CourseWSStub.VersionVO;
import org.bbsync.webservice.client.proxytool.CourseProxyTool;


public abstract class AbstractCategory extends CourseProxyTool {
    private static final long serialVersionUID = 3333000000005555L;
    protected CategoryVO _category_vo = null;
    
	///////////////////////////////////////////////////////////////////////////
	//  Required ClientWebService Methods  ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////	
	
	
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
     * @return Returns the Category ID.
     */
    public String getId(){
    	return _category_vo.getId();
    }

    /**
     * You should not try to 'make up' an id here - only use the ids that you 
     * are given from another api call. The actual format of the id is Internal
     * to AS: Treat an id as a series of characters.
     * 
     * @param id - the id to set - only required if you want to update an 
     *             existing category item. This ID should be generated by 
     *             Blackboard, in the form "_nnn_1" where nnn is an integer.
     */
    public void setId(String id){
    	_category_vo.setId(id);
    }

    /**
     * @return Returns the Category's Batch UID (aka Category ID in the UI).
     */
    public String getBatchUid(){
       	return _category_vo.getBatchUid();
    }

    /**
     * Set Category's Batch UID.  This is known as the "Category ID" in the UI.
     * @param batch_uid - the batchUid to set
     */
    public void setBatchUid(String batch_uid){
    	_category_vo.setBatchUid(batch_uid);
    }

    /**
     * @return Returns the ID of the Data Source that this Category 
     *         is associated with. 
     */
    public String getDataSourceId(){
    	return _category_vo.getDataSourceId();
    }

    /**
     * Sets the Data Source ID value for this Category.
     * @param data_source_id - the Data Source that this Category is associated
     *                         with.
     */
    public void setDataSourceId(java.lang.String data_source_id){
    	_category_vo.setDataSourceId(data_source_id);
    }

    /**
     * @return Returns the Category description.
     */
    public String getDescription(){
        return _category_vo.getDescription();
    }

    /**
     * Sets that description of this Category.
     * @param description - the category's description.
     */
    public void setDescription(String description){
    	_category_vo.setDescription(description);
    }

    /**
     * @return Returns the Batch UID of the parent Category.
     */
    public String getParentId(){
    	return _category_vo.getParentId();
    }

    /**
     * Set Category's parent ID. If top level, this value is set null.
     * @param parent_id - The Batch UID of the parent Category.
     */
    public void setParentId(String parent_id){
    	_category_vo.setParentId(parent_id);
    }

    /**
     * @return Returns the Category title (aka "Category Name" in the UI).
     */
    public String getTitle(){
    	return _category_vo.getTitle();
    }

    /**
     * Set the Category Title.  This is known as the "Category Name" in the UI.
     * @param title
     */
    public void setTitle(String title){
    	_category_vo.setTitle(title);
    }
    
    /**
     * @return Returns the availability of the Category
     */
    public boolean isAvailable(){
    	return _category_vo.getAvailable();
    }

    /**
     * Set the Category availability status.
     * @param available - Set to true if the category is available.
     */
    public void setAvailable(boolean available){
    	_category_vo.setAvailable(available);
    }

    /**
     * @return Returns the front page status of the category.
     */
    public boolean isFrontPage(){
    	return _category_vo.getFrontPage();
    }

    /**
     * Sets the category front page status.  E.g. displays the Category on the 
     * top level of the Course Catalog.
     * @param front_page - Sets the category front page status.
     */
    public void setFrontPage(boolean front_page){
    	_category_vo.setFrontPage(front_page);
    }

    /**
     * @return Returns true if this Category belongs to an Organization.
     */
    public boolean isOrganization(){
    	return _category_vo.getOrganization();
    }
    
    /**
     * @param organization - Set to true if this Category belongs to an 
     *                       Organization.
     */
    public void setOrganization(boolean organization){
    	_category_vo.setOrganization(organization);
    }

    /**
     * No information available.  Method inherited from 
     * blackboard.data.category.BbAdminCategory.
     *  
     * @return Returns true if this is a restricted Category.
     */
    public boolean isRestricted(){
        return _category_vo.getRestricted();
    }

    /**
     * No information available.  Method inherited from 
     * blackboard.data.category.BbAdminCategory.
     *  
     * @param restricted - Set to true if this is a restricted Category. 
     */
    public void setRestricted(boolean restricted){
    	_category_vo.setRestricted(restricted);
    }


    /**
     * Expansion data is currently ignored. In future versions it may be used 
     * to add additional attributes without breaking the wsdl contract.
     * 
     * @return Returns the expansion data.
     */
    public String[] getExpansionData(){
    	return _category_vo.getExpansionData();
    }

    /**
     * Expansion data is currently ignored. In future versions it may be used 
     * to add additional attributes without breaking the wsdl contract.
     * 
     * @param expansion_data - the expansion data to set (For Future Use)
     */
    public void setExpansionData(String[] expansion_data){
    	_category_vo.setExpansionData(expansion_data);
    }
    
    protected CategoryVO getVO(){
    	return _category_vo;
    }    
}
