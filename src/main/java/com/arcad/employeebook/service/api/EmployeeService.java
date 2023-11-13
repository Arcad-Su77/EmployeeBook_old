package com.arcad.employeebook.service.api;

import com.arcad.employeebook.dataproperties.DataProperties;
import com.arcad.employeebook.elementaryClasses.Department;
import com.arcad.employeebook.elementaryClasses.Employee;
import com.arcad.employeebook.service.exception.EmployeeAlreadyAddedException;
import com.arcad.employeebook.service.impl.EmployeeServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

@Service
public class EmployeeService implements EmployeeServiceImpl {

    private double miniSalary;
    private double midleSalary;
    private double maxiSalary;
    private final Map<Integer, Employee> employees;
    private Map<Integer, Department> departments;

    public EmployeeService() {
        this.employees = DataProperties.InitialEmployee();
        this.departments = DepartmentService.getDepartments();
    }

    public void setDepartments(DepartmentService departmentService) {
        this.departments = DepartmentService.getDepartments();
    }

    public double getMiniSalary() {
        return miniSalary;
    }
    public void setMiniSalary(double miniSalary) {
        this.miniSalary = miniSalary;
    }
    public double getMidleSalary() {
        return midleSalary;
    }
    public void setMidleSalary(double midleSalary) {
        this.midleSalary = midleSalary;
    }
    public double getMaxiSalary() {
        return maxiSalary;
    }
    public void setMaxiSalary(double maxiSalary) {
        this.maxiSalary = maxiSalary;
    }
    public void updateVolume() {
        setMiniSalary(getSalary(employees.get(1)));
        setMidleSalary(getSumSalary()/Employee.getCount());
        setMaxiSalary(getSalary(employees.get(1)));
        for (Map.Entry<Integer, Employee> empMap : employees.entrySet()) {
            if (empMap != null) {
                double salary = getSalary(empMap.getValue());
                if (salary < getMiniSalary()) {
                    setMiniSalary(salary);
                }
                if (salary > getMaxiSalary()) {
                    setMaxiSalary(salary);
                }
            }
        }
    }

    // Реализуем метод printAllEmployee (распечатать всех сотрудников)
    public String printAllEmployee() {
        String result = "<tr><th>ID</th><th>Сотрудник</th><th>Зарплата</th><th>Отдел</th></tr>>";
        for (Map.Entry<Integer, Employee> empMap : employees.entrySet()) {
            if (empMap != null) {
                result += "<tr><td>" + empMap.getValue().getEmployeeID() +
                        "</td><td>" + empMap.getValue().getEmployeeFIO() +
                        "</td><td>" + getSalary(empMap.getValue()) +
                        "</td><td>" + empMap.getValue().getDepartmentID()+ " " +
                        departments.get(empMap.getValue().getDepartmentIndexID()).getName() + "</td></tr>";
            }
        }
        return result;
    }

    @Override
    public String addEmploeey(List<String> args) throws EmployeeAlreadyAddedException {
        String result = "<tr><th>Добавляем сотрудника с параметрами</th></tr>";
        Employee addNew = new Employee(args.get(0), args.get(1), args.get(2), Integer.parseInt(args.get(3)), Float.parseFloat(args.get(4)));
        Employee emplNew = employees.putIfAbsent(addNew.hashCode(), addNew);
        if (emplNew == null){
           return result + "<tr><td>" + args + "</td></tr>" +
                    "<tr><td>" + addNew + "</td></tr>";
        } else {
            Employee.decCount();
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже добавлен!!! \n" + emplNew);
        }
    }

    public void printEditEmployee() {
        System.out.println("ID\tСотрудник\t\tОтдел\tКоэф.Зарплата");
        for (Map.Entry<Integer, Employee> empMap : employees.entrySet()) {
            if (empMap != null) {
                System.out.println(empMap.getValue().getEmployeeID() +
                        "\t" + empMap.getValue().getEmployeeFIO() +
                        "\t" + empMap.getValue().getDepartmentID()+ " " + departments.get(empMap.getValue().getDepartmentIndexID()).getName() +
                        "\t" + empMap.getValue().getScaleRatio());
            }
        }
    }

    public double getSumSalary() {
        double sumSalary = 0;
        for (Map.Entry<Integer, Employee> empMap : employees.entrySet()) {
            if (empMap != null) {
                sumSalary += getSalary(empMap.getValue());
            }
        }
        return sumSalary;
    }
    public double getSalary(Employee emp) {
        int iDepID = emp.getDepartmentIndexID();
        double oklad = departments.get(iDepID).getSalary();
        double salary = oklad * emp.getScaleRatio();
        return (double) Math.round(salary * 100) / 100;
    }
    public String getEmployee(Employee emp) {
    	return emp.getEmployeeShortFIO() + ", Отдел:" + departments.get(emp.getDepartmentIndexID()).getName() + ", Зарплата: " + getSalary(emp);
    }
    public Employee findEmployeeMiniSalary() {
        Employee retEmployee;
         // MINI_SALARY
        setMiniSalary(getSalary(employees.get(1)));
        retEmployee = employees.get(1);
        for (Map.Entry<Integer, Employee> empMap : employees.entrySet()) {
            if (empMap != null) {
                double empFor = getSalary(empMap.getValue());
                if (empFor < getMiniSalary()) {
                    setMiniSalary(empFor);
                    retEmployee = empMap.getValue();
                }
            }
        }
        return retEmployee;
    }
    public Employee findEmployeeMaxiSalary() { // MAXI_SALARY
        Employee retEmployee;
        setMaxiSalary(getSalary(employees.get(1)));
        retEmployee = employees.get(1);
        for (Map.Entry<Integer, Employee> empMap : employees.entrySet()) {
            if (empMap != null) {
                double empFor = getSalary(empMap.getValue());
                if (empFor > getMaxiSalary()) {
                    setMaxiSalary(empFor);
                    retEmployee = empMap.getValue();
                }
            }
        }
        return retEmployee;
    }
    public Employee findEmployeeMidleSalary() { // MIDLE_SALARY
    	Employee retEmployee = null;
        setMidleSalary(getSumSalary() / Employee.getCount());
        double delta = abs(getMidleSalary() - getSalary(employees.get(0)));
        for (Map.Entry<Integer, Employee> empMap : employees.entrySet()) {
            if (empMap != null) {
                double empFor = abs(getMidleSalary() - getSalary(empMap.getValue()));
                if (empFor < delta) {
                    delta = empFor;
                    retEmployee = empMap.getValue();
                }
            }
        }
        return retEmployee;
    }



//    public void editDepartment(Scanner scan) {
//        printAllDepartment();
//        System.out.println("""
//                Для изменения информации о департаменте, введите новые данные.
//                формат ввода  <ID> <Оклад> <Название отдела>\s
//                Данные разделяйте пробелами, если данные изменять не нужно то используете *""");
//        String[] inEdit = {"*", "*", "*"};
//        try {
//            inEdit = scan.nextLine().split(" ");
//        } catch (Exception e) {
//            //e.printStackTrace(); // Выводит сообщение об ошибке
//        }
//        if (inEdit[0] != null && !inEdit[0].isEmpty() && inEdit.length == 3) {
//            if (!inEdit[0].equals("*")) {
//                int inID = Integer.parseInt(inEdit[0]);
//                for (Department dep : departments) {
//                    if (dep.getDepartmentID() == inID) {
//                        if (inEdit[1] != null)
//                            if (!inEdit[1].equals("*")) {
//                                dep.setSalary(Integer.parseInt(inEdit[1]));
//                            }
//                        if (inEdit[2] != null)
//                            if (!inEdit[2].equals("*")) {
//                                dep.setName(inEdit[2]);
//                            }
//                        break;
//                    }
//                }
//            }
//            printAllDepartment();
//            updateVolume();
//        } else System.out.println("Вводите корректно: <ID> <Оклад> <Наименование>");
//    }
//    public void editEmployee(Scanner scan) {
//        printEditEmployee();
//        System.out.println("""
//                Для изменения информации о сотруднике, введите новые данные.
//                формат ввода  [<ID>] <Фамилия> <Имя> <Отчество> <Отдел> <Коэф.зарплаты>
//                Данные разделяйте пробелами, если данные изменять не нужно то используете *""");
//        String[] inEdit = {"*", "*", "*", "*", "*", "*"};
//        try {
//            inEdit = scan.nextLine().split(" ");
//        } catch (Exception e) {
//            //e.printStackTrace(); // Выводит сообщение об ошибке
//        }
//        if (inEdit[0] != null && !inEdit[0].isEmpty() && inEdit.length == 6) {
//            if (!inEdit[0].equals("*")) {
//                int inID = Integer.parseInt(inEdit[0]);
//                for (Employee emp : employees) {
//                    if (emp.getEmployeeID() == inID) {
//                        if (inEdit[1] != null && !inEdit[1].equals("*")) {
//                                emp.setLastName(inEdit[1]);
//                            }
//                        if (inEdit[2] != null && !inEdit[2].equals("*")) {
//                                emp.setFirstName(inEdit[2]);
//                            }
//                        if (inEdit[3] != null && !inEdit[3].equals("*")) {
//                            emp.setMidleName(inEdit[3]);
//                        }
//                        if (inEdit[4] != null && !inEdit[4].equals("*")) {
//                            emp.setDepartmentID(Integer.parseInt(inEdit[4]));
//                        }
//                        if (inEdit[5] != null && !inEdit[5].equals("*")) {
//                            emp.setScaleRatio(Float.parseFloat(inEdit[5].replace(",", ".")));
//                        }
//                        break;
//                    }
//                }
//            }
//            printAllEmployee();
//            updateVolume();
//        } else System.out.println("[<ID>] <Фамилия> <Имя> <Отчество> <Отдел> <Коэф.зарплаты>");
//    }
}
