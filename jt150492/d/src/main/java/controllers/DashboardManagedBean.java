/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;


/**
 *
 * @author pavle
 */

@Named
@SessionScoped
@FacesConfig(version=FacesConfig.Version.JSF_2_3)
public class DashboardManagedBean implements Serializable {


   private DashboardModel model = new DefaultDashboardModel();

	public DashboardManagedBean(){
		// Initialize the dashboard model
		this.model = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();
		column1.addWidget("Voda");
		column1.addWidget("Temperatura");
		this.model.addColumn(column1);
	

	}

   
    public DashboardModel getModel() {
        return model;
    }

    public void setModel(DashboardModel model) {
        this.model = model;
    }
    


}
    