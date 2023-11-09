package com.example.jsf01.views;

import com.example.jsf01.models.entities.Department;
import com.example.jsf01.services.DepartmentService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class DepartmentDashboard implements Serializable {
    private static final long serialVersionUID = 5L;
    @Inject
    private DepartmentService departmentService;
    @Inject
    private ExternalContext externalContext;

    private List<Department> departments;

    @PostConstruct
    public void init() {
        departments = departmentService.findAll();
    }

    public List<Department> findAllDepartments() {
        return departments;
    }
    public void onRowUpdateRequest(RowEditEvent<Department> event) {
        FacesMessage message;
        try {
            Department department = event.getObject();
            String departmentDetail = department.getId() + "\n" + department.getName();
            departmentService.update(department);
            message = new FacesMessage("Department Updated", departmentDetail);
        } catch (Exception exception) {
            exception.printStackTrace();
            message = new FacesMessage("Updating Department Failed", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowDeleteRequest(Department department) throws IOException {
        System.out.println("\n\n\n*** Entered onDeleteReqyest 3\n\n\n");
        FacesMessage message;
        try {
            departmentService.delete(department);
            String departmentDetail = department.getId() + "\n" + department.getName();
            message = new FacesMessage("Department Deleted", departmentDetail);
        } catch (Exception exception) {
            exception.printStackTrace();
            message = new FacesMessage("Deleting Department Failed", "");
        }
        departments = departmentService.findAll();
        FacesContext.getCurrentInstance().addMessage(null, message);
        externalContext.redirect("/JSF01-1.0-SNAPSHOT/departmentDashboard.xhtml");
    }

    public void onRowCancel(RowEditEvent<Department> event) {
        Department department = event.getObject();
        String departmentDetail = department.getId() + "\n" + department.getName();
        FacesMessage msg = new FacesMessage("Edit Cancelled", departmentDetail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
