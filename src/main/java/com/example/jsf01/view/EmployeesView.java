package com.example.jsf01.view;

import com.example.jsf01.model.Employee;
import com.example.jsf01.service.EmployeeService;
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
public class EmployeesView implements Serializable {
    @Inject
    private EmployeeService employeeService;
    @Inject
    private ExternalContext externalContext;

    private List<Employee> employees;

    @PostConstruct
    public void init() {
        employees = employeeService.findAll();
    }

    public List<Employee> findAllEmployees() {
        return employees;
    }
    public void onRowUpdateRequest(RowEditEvent<Employee> event) {
        FacesMessage message;
        try {
            Employee employee = event.getObject();
            String employeeDetail = employee.getId() + "\n" + employee.getFirstName() + employee.getLastName();
            employeeService.update(employee);
            message = new FacesMessage("Employee Updated", employeeDetail);
        } catch (Exception exception) {
            exception.printStackTrace();
            message = new FacesMessage("Updating Employee Failed", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //@ManagedProperty("#{param.deletableEmployee}")
    ///private Employee deletableEmployee;
    /*public void onRowDeleteRequest() {
        System.out.println("\n\n\n*** Entered onRowDeleteRequest 1\n\n\n");
        FacesMessage message;
        try {
            employeeService.deleteEmployee(deletableEmployee);
            String employeeDetail = deletableEmployee.getId() + "\n" + deletableEmployee.getFirstName() + deletableEmployee.getLastName();
            message = new FacesMessage("Employee Deleted", employeeDetail);
        } catch (Exception exception) {
            exception.printStackTrace();
            message = new FacesMessage("Deleting Employee Failed", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowDeleteRequest(RowEditEvent<Employee> event) {
        System.out.println("\n\n\n*** Entered onDeleteReqyest 2\n\n\n");
        FacesMessage message;
        try {
            Employee employee = event.getObject();
            employeeService.delete(employee);
            String employeeDetail = employee.getId() + "\n" + employee.getFirstName() + employee.getLastName();
            message = new FacesMessage("Employee Deleted", employeeDetail);
        } catch (Exception exception) {
            exception.printStackTrace();
            message = new FacesMessage("Deleting Employee Failed", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    */


    public void onRowDeleteRequest(Employee employee) throws IOException {
        System.out.println("\n\n\n*** Entered onDeleteReqyest 3\n\n\n");
        FacesMessage message;
        try {
            employeeService.delete(employee);
            String employeeDetail = employee.getId() + "\n" + employee.getFirstName() + employee.getLastName();
            message = new FacesMessage("Employee Deleted", employeeDetail);
        } catch (Exception exception) {
            exception.printStackTrace();
            message = new FacesMessage("Deleting Employee Failed", "");
        }
        employees = employeeService.findAll();
        FacesContext.getCurrentInstance().addMessage(null, message);
        externalContext.redirect("/JSF01-1.0-SNAPSHOT/employeesView.xhtml");
    }

    public void onRowCancel(RowEditEvent<Employee> event) {
        Employee employee = event.getObject();
        String employeeDetail = employee.getId() + "\n" + employee.getFirstName() + employee.getLastName();
        FacesMessage msg = new FacesMessage("Edit Cancelled", employeeDetail); // getCOde replaced with getCode
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

    /*public Employee getDeletableEmployee() {
        System.out.println("\n\n\n#########\n\n\nEntered getDeletableEmployee");
        return deletableEmployee;
    }

    public void setDeletableEmployee(Employee deletableEmployee) {
        System.out.println("\n\n\n#########\n\n\nEntered setDeletableEmployee");
        this.deletableEmployee = deletableEmployee;
        System.out.println("\n\n\n#########\n\n\nLeaving setDeletableEmployee");

    }*/

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
